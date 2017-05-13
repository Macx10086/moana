var email = GetQueryString('user')+"";
var password = '';

var movieHtml = '';
var movieName = '';
var start = '' ;
var end = '';
var grade = '';

var pageIndex=0;

var count=0;
var num_entries = 0;

$(function(){
	$('#user').text(email);
	 getMovieNum();
	// 创建分页
	
	
	
	
})

function pageselectCallback(page_index,jq){
	getPageHtml(page_index);
	pageIndex = page_index;
}
$('#search').click(function(){
	 getMovieNum();
	getPageHtml(pageIndex);
})

$('.caret').click(function(){
	$('.dropdown-menu').show();
});

$('.dropdown-toggle').click(function(){
	$('.dropdown-menu').show();
});

$('#loginOut').click(function(){
	var url = '../../admin/loginOut.do';
	$.post(url,function(resp){
		var data = JSON.parse(resp);
		if(data.status==1){
			alert(data.message);
			location.href='login.html';
		}else{
			alert(data.message);
		}
	})
});
$('#showInfo').click(function(){
	layer.open({
		  title:'修改密码',
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['450px', '300px'], //宽高
		  content: 'editInfo.html?user='+email,
		  btn:['修改','取消'],
		  yes:function(index){
			    var body = layer.getChildFrame('body',index);
			    password = body.find('#password').val();
				var param = {'email':email,
						'password':password};
				var url = '../../admin/editInfo.do';
				$.post(url,param,function(resp){
					var data = JSON.parse(resp);
					if(data.status==1){
						alert('修改成功');
						layer.closeAll();
					}else{
						alert(data.message);
						layer.closeAll();
					}
				})
		  },
		  btn2:function(index){
			  layer.closeAll();
			  
		  }
		});
	$('.dropdown-menu').hide();
});

$(document).delegate('#addMovie','click',function(){
	layer.open({
		title:'添加电影',
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['800px', '600px'], //宽高
		  content: 'addMovie.html',
		  btn:['添加','取消'],
		  yes:function(index){
			  var body = layer.getChildFrame('body',index);
			  var name = body.find("#name").val();
			  var director = body.find('#director').val();
			  var scenario = body.find('#scenario').val();
			  var protagonist = body.find('#protagonist').val();
			  var type = body.find('#type').val();
			  var time = body.find('#time').val();
			  var intro = body.find('#intro').val();
			  var assess = body.find('#assess').val();
			  var grade = body.find('#grade').val();
			  var poster = body.find('#poster').val();
			  var num = body.find('#num').val();
			  if(name==null||name==""||director==null||director==""||scenario==null||scenario==""
				  ||protagonist==null||protagonist==""||type==null||type==""
				  ||time==null||time==""||intro==null||intro==""||assess==null||assess==""
				  ||grade==null||grade==""||poster==null||poster==""||num==null||num==""){
				  alert("请填写参数");
				  return ;
			  }
			  var param = {
					  'name':name,
					  'director':director,
					  'scenario':scenario,
					  'protagonist':protagonist,
					  'type':type,
					  'time':time,
					  'intro':intro,
					  'assess':assess,
					  'grade':grade,
					  'poster':poster,
					  'num':num,
			  }
			  var url = '../../movieData/movieDataUpload.do';
			  $.post(url,param,function(resp){
				  var data = JSON.parse(resp);
				  if(data.status){
					  alert(data.message);
					  location.href="index.html";
				  }else{
					  alert(data.message);
				  }
			  })
		  },
		  btn2:function(index){
			  layer.closeAll();
		  }
	})
});


$(document).delegate('#editMovie','click',function(){
	var id = $(this).parent().parent().attr('id');
	layer.open({
		title:'修改电影信息',
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['800px', '600px'], //宽高
		  content: 'addMovie.html?type=edit&id='+id,
		  btn:['修改','取消'],
		  yes:function(index){
			  var body = layer.getChildFrame('body',index);
			  var name = body.find("#name").val();
			  var director = body.find('#director').val();
			  var scenario = body.find('#scenario').val();
			  var protagonist = body.find('#protagonist').val();
			  var type = body.find('#type').val();
			  var time = body.find('#time').val();
			  var intro = body.find('#intro').val();
			  var assess = body.find('#assess').val();
			  var grade = body.find('#grade').val();
			  var poster = body.find('#poster').val();
			  var num = body.find('#num').val();
			  var rushTime = body.find('#rushTime').val();
			  if(name==null||name==""||director==null||director==""||scenario==null||scenario==""
				  ||protagonist==null||protagonist==""||type==null||type==""
				  ||time==null||time==""||intro==null||intro==""||assess==null||assess==""
				  ||grade==null||grade==""||poster==null||poster==""||num==null||num==""){
				  alert("请填写参数");
				  return ;
			  }
			  var param = {
					  'id':id,
					  'name':name,
					  'director':director,
					  'scenario':scenario,
					  'protagonist':protagonist,
					  'type':type,
					  'time':time,
					  'intro':intro,
					  'assess':assess,
					  'grade':grade,
					  'poster':poster,
					  'num':num,
					  'rushTime':rushTime,
			  }
			  var url = '../../movieData/updateMovie.do';
			  $.post(url,param,function(resp){
				  var data = JSON.parse(resp);
				  if(data.status){
					  alert(data.message);
					  location.href="index.html";
				  }else{
					  alert(data.message);
				  }
			  })
		  },
		  btn2:function(index){
			  layer.closeAll();
		  }
	})
});

$(document).delegate('#deleteMovie','click',function(){
	var id = $(this).parent().parent().attr('id');
	layer.confirm('是否确定删除此条电影信息',{
	   btn: ['确认', '删除'] //可以无限个按钮
	},function(index,layero){
		deleteMovie(id)
	},
	function(index,layero){
		layer.closeAll();
		return ;
	});
	
	
})

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function getMovieNum(){
	movieName = $('#movieName').val();
	start = $('#start').val();
	end = $('#end').val();
	grade = $('#grade').val();
	var url = '../../movieData/getMovieNum.do';
	$.ajax({
		type:'POST',
		async:false,
		url:url,
		data:{
			'movieName':movieName,
			'start':start,
			'end':end,
			'grade':grade,
	},
		success:function(resp){
		var data = JSON.parse(resp);
		if(data.status){
			num_entries= data.num;
			$("#Pagination").pagination(num_entries, {
				callback: pageselectCallback,
				items_per_page:5 //每页显示1项
			});
		}else{
			alert(data.message);
		}
		}
	})
}



function getPageHtml(page_index){
	movieName = $('#movieName').val();
	start = $('#start').val();
	end = $('#end').val();
	grade = $('#grade').val();
	var url = '../../movieData/getPageList.do';
	var param={
			'pageNo':page_index,
			'pageSize':5,
			'movieName':movieName,
			'start':start,
			'end':end,
			'grade':grade,
	};
	$.ajax({
		type:'POST',
		async:true,
		url:url,
		data:param,
		success:function(resp){
		var data = JSON.parse(resp);
		if(data.status){
			getHtml(data.list);
		}else{
			alert(data.message);
		}
	}
	})
	
}
function getHtml(list){
	movieHtml = '';
	for(var i=0;i<list.length;i++){
		var item = list[i];
		movieHtml += '<tr id='+item['id']+'>';
		movieHtml += '<td> '+item['name']+'</td>';
		movieHtml += '<td>'+item['grade']+'</td>';
		movieHtml += '<td>'+item['protagonist']+'</td>';
		movieHtml += '<td>'+formatDate(new Date(item['time']))+'</td>';
		movieHtml += '<td>'+'<a href="javascript:void(0);" id="addMovie">添加</a><br>';
		movieHtml += '<a href="javascript:void(0);" id="editMovie">修改</a><br>';
		movieHtml += '<a href="javascript:void(0);" id="deleteMovie">删除</a><td>';
		movieHtml +='</tr>';
	}
	$("#Searchresult").empty().append(movieHtml); //装载对应分页的内容
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

function deleteMovie(id){
	var param = {
			'id':id,
	}
	var url = '../../movieData/deleteMovie.do';
	$.ajax({
		type:'POST',
		url:url,
		data:param,
		success:function(resp){
			var data = JSON.parse(resp);
			if(data.status){
				location.href="index.html";
			}else{
				alert(data.message);
			}
		}
	})
}
