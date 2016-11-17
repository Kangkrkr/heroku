function socketInit() {
	sock = new WebSocket('ws://' + location.hostname + ':8080/websocket');

	sock.onopen = function(e) {
		console.log('opened.');
		$.ajax({
			url : '/chat/rest',
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				$.each(data.body, function(idx, item) {
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