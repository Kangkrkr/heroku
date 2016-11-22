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