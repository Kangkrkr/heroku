app.controller('messageSendController', function($scope, $http, $timeout){
	$scope.sendEnable = true;
	
	$scope.send = function(){
		if(!$scope.sendEnable){
			Materialize.toast("연속으로 메시지를 보낼 수 없습니다.", 1500);
			return;
		}
		
		$scope.sendEnable = false;
		
		$http({
			method : 'POST',
			url : '/rest/message/send',
			params : {
				targetEmail : $scope.targetEmail,
				content : $scope.content
			}
		}).success(function(data) {
			Materialize.toast(data, 500);
			
			$timeout(function(){
				$scope.sendEnable = true;
				location.reload(true);
			}, 500);
		}).error(function(error) {
			$scope.sendEnable = true;
			Materialize.toast("메세지를 보낼 수 없습니다.", 1500);
		});
	};
});