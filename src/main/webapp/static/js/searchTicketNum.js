function searchNum(){
	var id=$("#id").text();
	$.ajax({
		type:"POST",
		url:"../../movieData/searchTicketNum.do",
		data:{id:id},
		success:function(data){
			
			if(parseInt(data)>0){
				$("#num").text(data);
			}else{
				$('#buttonValue').text('该活动已结束');
				$('#rushTicketButton').css('background',gray);
				$('#rushTicketButton').attr("onclick","");
			}
			
			
			
		},
		error:function(){
			alert("请求失败");
		}
		
	});
	
}
