app.controller('messageBoxController', function($scope, $http, $timeout){
	$scope.unreadMessages = 0;
	
	$http({
		method : 'GET',
		url : '/rest/message'
	}).success(function(res) {
		$scope.messages = res;
		$scope.messageLength = res.length;
		
		var i;
		for(i=0; i<res.length; i++){
			if(!$scope.messages[i].read){
				$scope.unreadMessages++;
			}
		}
	}).error(function(error){
		console.log(error);
		Materialize.toast("메세지를 읽어들이는데 실패했습니다.", 1500);
	});
	
	$scope.read = function(id){
		$http({
			method : 'GET',
			url : '/rest/message/'+id
		}).success(function(hasRead) {
			if(hasRead === true)
				Materialize.toast("이미 읽은 메시지 입니다.", 1500);
			else {
				Materialize.toast("메세지가 읽음처리 되었습니다.", 800);
				$timeout(function() {
					location.reload(true);
				}, 800);
			}
		}).error(function(error){
			console.log(error);
			Materialize.toast("문제가 발생하였습니다.", 1500);
		});
	};
	
	$scope.remove = function(id){
		$http({
			method : 'DELETE',
			url : '/rest/message/'+id
		}).success(function(res) {
			Materialize.toast("메세지가 제거되었습니다.", 450);
			$timeout(function() {
				location.reload(true);
			}, 450);
		}).error(function(error){
			console.log(error);
			Materialize.toast("메세지를 삭제하는데 실패하였습니다.", 1500);
		});
	};
});
