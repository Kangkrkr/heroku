sendEnable = true;

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

function send() {
	if(!sendEnable){
		alert("연속으로 메시지를 보낼 수 없습니다.");
		return;
	}
	
	var message = $('#message').val();

	if(!message || message.trim() === ''){
		$('#message').val('');
		$('#message').focus();
		return;
	}
	
	$.ajax({
		url : '/chat/rest',
		type : 'POST',
		data : {
			content : message,
			chatDate : new Date()
		},
		success : function(data) {
			if(data.body){
				alert(data.body);
				return;
			}
			
			$('#message').val('');
			$('#message').focus();
			
			sock.send(message);
			sendEnable = false;
			
			setTimeout(() => {
				sendEnable = true;
			}, 1500);
			
		},
		error : function(err) {
			console.log(err);
		}
	});
}

function appendDiv(data, date) {

	var $chatItem = document.createElement('div');
	$($chatItem).addClass("chip tooltipped col s8 offset-s2 chat-item");
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