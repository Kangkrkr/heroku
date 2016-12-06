app.directive('pageButton', function() {
	return {
		restrict : "C",
		link : function(s, e, a, c) {
			// scope 변수에 해당 directive가 적용된 엘리먼트들을 모두 담는다. 
			s.$parent.pageButtons.push(e);
		}
	};
});