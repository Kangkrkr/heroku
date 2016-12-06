var month = [];
var dayOfWeek = [];

var monthList = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
var dayOfWeekList = {'Mon':'월', 'Tue':'화', 'Wed':'수', 'Thu':'목', 'Fri':'금', 'Sat':'토', 'Sun':'일'};

function getConvertedDate(chatDate){
	var date = new Date(chatDate).toString().split(' ');

	var ret = '';

	ret += date[3]+"년 ";
	for(var i=0; i<monthList.length; i++){
		if(date[1] === monthList[i]){
			ret += ((i+1)+"월 ");
			break;
		}
	}
	ret += date[2]+"일 ";
	ret += dayOfWeekList[date[0]]+"요일 ";
	ret += date[4];
	
	return ret;
}