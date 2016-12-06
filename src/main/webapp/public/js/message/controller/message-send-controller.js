app.controller('messageSendController', function($scope, $http){
	$scope.send = function(){
		$http({
			method : 'POST',
			url : '/rest/message/send',
			params : {
				targetEmail : $scope.targetEmail,
				content : $scope.content
			}
		}).success(function(data) {
			Materialize.toast(data, 1500);
		}).error(function(error) {
			Materialize.toast("메세지를 보낼 수 없습니다.", 1500);
		});
	};
});