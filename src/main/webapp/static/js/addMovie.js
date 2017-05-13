$(document).ready(function(){
	var type = GetQueryString('type');
	if(type!=null&&type!=""){
		var id = GetQueryString('id');
		var url = '../../movieData/showMovie.do';
		$.ajax({
			type:'POST',
			async:false,
			data:{
				'id':id,
			},
			url:url,
			success:function(resp){
				var data = JSON.parse(resp);
				if(data!=null&&data!=""){
					$('#name').val(data.name);
					$('#assess').val(data.assess);
					$('#grade').val(data.grade);
					$('#intro').val(data.intro);
					$('#poster').val(data.poster);
					$('#protagonist').val(data.protagonist);
					$('#scenario').val(data.scenario);
					$('#time').val(formatDate(new Date(data.time)));
					$('#type').val(data.type);
					$('#num').val(data.num);
					$("#director").val(data.director);
					$('#rushTime').val(data.rushTime);		
				}else{
					alert('查看电影信息失败');
				}
			}
			
		})
	}
})

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function formatDate(now) { 
	var year=now.getFullYear(); 
	var month=now.getMonth()+1; 
	var date=now.getDate(); 
	var hour=now.getHours(); 
	var minute=now.getMinutes(); 
	var second=now.getSeconds(); 
	return year+"-"+month+"-"+date; 
	} 