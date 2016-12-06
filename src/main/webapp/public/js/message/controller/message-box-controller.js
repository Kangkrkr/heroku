app.controller('messageBoxController', function($scope, $http){
	$http({
		method : 'GET',
		url : '/rest/message'
	}).success(function(res) {
		$scope.messages = res;
		$scope.messageLength = res.length;
	}).error(function(error){
		console.log(error);
		Materialize.toast("메세지를 읽어들이는데 실패했습니다.", 1500);
	});
});
