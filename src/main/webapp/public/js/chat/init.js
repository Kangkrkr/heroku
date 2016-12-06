$(document).ready(function(){
	init();
});

var chatItemCssOriginal = {
	'overflow' : 'hidden',
	'white-space' : 'nowrap',
	'height' : '35px',
	'text-overflow' : 'ellipsis',
	'line-height' : '33px',
};

var chatItemCssHover = {
	'height': '75px',
	'overflow': 'auto',
	'white-space' : 'normal',
	'word-break' : 'break-word',
	'transition' : '0.2s'
};

function init() {
	addKeyUpEventToSendButton();
};