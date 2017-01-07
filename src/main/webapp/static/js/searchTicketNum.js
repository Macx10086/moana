function searchNum(){
	var id=$("#id").text();
	$.ajax({
		type:"POST",
		url:"http://139.199.164.23/moana/movieData/searchTicketNum.do",
		data:{id:id},
		success:function(data){
			$("#num").text(data);
			
		},
		error:function(){
			alert("请求失败");
		}
		
	});
	
}
