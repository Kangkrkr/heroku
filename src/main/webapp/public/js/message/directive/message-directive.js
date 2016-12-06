app.directive('message', function() {
	return {
		restrict : "C",
		link : function(s, e, a, c) {
			// e 자체를 제이쿼리 엘리먼트처럼 사용할 수 있다.
			var div = angular.element('<div class="col s7 m3 offset-s5 offset-m9 message-panel"></div>');
			div.css({ 'width' : e.width(), 'height' : e.height() });
			
			if(a.read == 'true'){
				//div.css('background-color', 'rgba(0, 0, 0, 0.05)');
			}else{
				console.log('읽지않음');
			}
			
			var readBtn = angular.element('<button class="right-align col s5 m2 message-read-btn deep-orange lighten-4 waves-effect waves-light btn">읽음표시</button>');
			var removeBtn = angular.element('<button class="right-align col s3 m1 message-remove-btn deep-orange lighten-4 waves-effect waves-light btn">제거</button>');
		
			e.parent().find(e).on('mouseover', function(evt){
				div.css('opacity', '1');
			});
			
			e.parent().find(e).on('mouseleave', function(evt){
				div.css('opacity', '0');
			});
			
			readBtn.click(function(){ s.read(a.id); });
			removeBtn.click(function(){ s.remove(a.id); });
			
			div.append(removeBtn);
			div.append(readBtn);
			
			e.append(div);
		}
	};
});