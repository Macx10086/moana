var email = GetQueryString('user')+'';
$(document).ready(function(){
	getInfo(email);
});

function getInfo(email){
	var param = {'email':email};
	var url = '../../admin/getInfo.do';
	$.post(url,param,function(resp){
		var data = JSON.parse(resp);
		if(data.status==1){
			var admin =data.admin;
			$('#email').val(admin.email);
			$('#password').val(admin.password);
		}else{
			alert(data.message);
		}
	})
}



function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
