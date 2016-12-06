app.controller('postController', function($scope, $http) {

	$scope.page = 1; // 초기 페이지넘버 설정.
	$scope.size = 5; // 페이지당 게시글 수 설정.
	$scope.max = 1; // 초기 page block 수 설정.

	$scope.pageButtons = [];
	$scope.viewAvailable = [];

	$http({
		method : 'POST',
		url : '/rest/member/current'
	}).success(function(res) {
		$scope.currentMember = res;
	}).error(function(err) {
		Materialize.toast("사용자 정보를 불러오는데 실패하였습니다.", 2000);
	})

	$http.get('/rest/post/count').then(function(res) {
		$scope.max = Math.ceil(res.data / $scope.size);
	});

	$scope.getMax = function(max) {
		return new Array(max);
	};

	$scope.prev = function() {
		if ($scope.page - 1 <= 0) {
			$scope.page = 1;
			return;
		}

		$scope.pagination($scope.page - 1);
	};

	$scope.next = function() {
		if ($scope.page + 1 > $scope.max) {
			$scope.page = $scope.max;
			return;
		}

		$scope.pagination($scope.page + 1);
	};

	$scope.pagination = function(page) {
		$scope.page = page;
		$scope.viewAvailable = []; // viewAvailable 초기화.

		var e = $scope.pageButtons[page - 1];

		$(e).addClass('active');
		$(e).parent().find('li.page-button').not(e).removeClass('active');

		$http.get('/rest/post?page=' + page).then(function(res) {
			$scope.posts = res.data;
		});
	};

	$scope.pagination(1);

	$scope.postView = function(id) {
		$scope.viewAvailable[id] ^= true;
		if (!$scope.viewAvailable[id])
			return;
		$http.get('/rest/post/' + id).then(function(res) {
			$scope.items = [];
			$scope.items[id] = res.data;
		});
	};

	$scope.postEdit = function(id) {
		Materialize.toast("수정기능은 아직 제공되지 않습니다.", 1000);
	};

	$scope.postDelete = function(id) {
		$http({
			method : 'DELETE',
			url : '/rest/post/' + id
		}).success(function(res) {
			Materialize.toast("삭제에 성공하였습니다.", 700);
			setTimeout(function() {
				location.reload(true);
			}, 700);
		}).error(function(err) {
			Materialize.toast("해당글을 삭제하는데 실패하였습니다.", 1000);
		})
	};
});