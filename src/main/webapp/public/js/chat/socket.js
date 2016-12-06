$(document).ready(function(){
	socketInit();
});

function socketInit() {
	sock = new SockJS("http://"+location.host+"/websocket");

	sock.onopen = function(e) {
		console.log('opened.');
		$.ajax({
			url : '/rest/chat',
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				$.each(data, function(idx, item) {
					appendDiv(item.content, getConvertedDate(item.chatDate));
				});
			},
			error : function(err) {
				console.log(err);
			}
		});
	};

	sock.onmessage = function(e) {
		appendDiv(e.data, getConvertedDate(new Date()));
	};

	sock.onclose = function(e) {
		console.log('closed.');
	}

	sock.onerror = function(e) {
		console.log('error');
	}
};

function send() {
	if(!sendEnable){
		Materialize.toast("연속으로 메시지를 보낼 수 없습니다.", 1500);
		return;
	}
	
	var message = $('#message').val();

	if(!message || message.trim() === ''){
		$('#message').val('');
		$('#message').focus();
		return;
	}
	
	$.ajax({
		url : '/rest/chat',
		type : 'POST',
		data : {
			content : message,
			chatDate : new Date()
		},
		success : function(data) {
			if(data){
				Materialize.toast(data, 1500);
				return;
			}
			
			$('#message').val('');
			$('#message').focus();
			
			sock.send(message);
			sendEnable = false;
			
			setTimeout(function(){
				sendEnable = true;
			}, 1500);
		},
		error : function(err) {
			console.log(err);
		}
	});
}