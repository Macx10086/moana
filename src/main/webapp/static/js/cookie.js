function setCookie(name,value) 
{ 
    var Days = 30; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
}
function GetCookie (name)   
{   
    var arg = name + "=";   
    var alen = arg.length;   
    var clen = window.document.cookie.length;   
    var i = 0;   
    while (i < clen)   
    {   
        var j = i + alen;   
        if (window.document.cookie.substring(i, j) == arg) return getCookieVal (j);   
        i = window.document.cookie.indexOf(" ", i) + 1;   
        if (i == 0)  
            break;   
    }   
    return null;  
}  
function getCookieVal (offset)  
{   
    var endstr = window.document.cookie.indexOf (";", offset);   
    if (endstr == -1)  
        endstr = window.document.cookie.length;   
    return unescape(window.document.cookie.substring(offset, endstr));  
}  
function delCookie(name) 
{ 
    var exp = new Date(); 
    exp.setTime(exp.getTime() - 1); 
    var cval=GetCookie(name); 
    if(cval!=null) 
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
} 

$("#loginOut").click(function(){
	$.ajax({
		url:"../../auth/loginOut.do",
		success:function(data){
			if(JSON.parse(data)!="error"){
				delCookie("username");
			}
		},
		error:function(){
			alert("服务器出错");
		}
	})
	$("#loginOut").hide();
	$("#username").hide();
})


function timer(intDiff){
	intDiff = parseInt(intDiff);//倒计时总秒数量
    window.setInterval(function(){
    	
    var day=0,
        hour=0,
        minute=0,
        second=0;//时间默认值        
    if(intDiff > 0){
    	$('#buttonValue').text('该活动未开始');
		$('#rushTicketButton').css('background','gray');
		$('#rushTicketButton').attr("onclick","");
        day = Math.floor(intDiff / (60 * 60 * 24));
        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }else{
    	$('.caption').hide();
    	$('#buttonValue').text('抢票');
		$('#rushTicketButton').css('background','lightblue');
		$('#rushTicketButton').attr("onclick","rushTicket();");
    }
    if (minute <= 9) minute = '0' + minute;
    if (second <= 9) second = '0' + second;
    $('#day_show').html(day+"天");
    $('#hour_show').html('<s id="h"></s>'+hour+'时');
    $('#minute_show').html('<s></s>'+minute+'分');
    $('#second_show').html('<s></s>'+second+'秒');
    intDiff--;
    }, 1000);
} 
