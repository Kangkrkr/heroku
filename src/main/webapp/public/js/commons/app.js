sendEnable = true;

app = angular.module('angular-app', [ 'ngRoute' ]);
app.config(function($routeProvider, $locationProvider) {
	$routeProvider
	.when("/message-box", {
		templateUrl : "/public/view/message-box.html"
	})
	.when("/post", {
		templateUrl : "/public/view/post.html",
		controller : "postController"
	}).when("/chat", {
		templateUrl : "/public/view/chat.html",
		controller : "chatController"
	});
});

function initBodySize(){
	$('body').css({
		'width' : screen.width,
		'height' : screen.height - 120
	});
};

function addKeyUpEventToSendButton(){
	$('#message').keyup(function(event) {
		if (event.keyCode == 13) {
			send();
		}
	});
};

function appendDiv(data, date) {

	var $chatItem = document.createElement('div');
	$($chatItem).addClass("chip tooltipped col s8 offset-s2 chat-item grey lighten-5 z-depth-2");
	$($chatItem).attr('data-tooltip', date);
	$($chatItem).text(data);
	$($chatItem).css(chatItemCssOriginal);
	$($chatItem).hover(function() {
		$(this).css(chatItemCssHover);
	}, function() {
		$(this).css(chatItemCssOriginal);
	});

	$('.chat-list').append($chatItem);

	$('.tooltipped').tooltip({
		delay : 50,
		position : 'right'
	});
}