$('.form').find('input, textarea').on('keyup blur focus', function (e) {
  
  var $this = $(this),
      label = $this.prev('label');

	  if (e.type === 'keyup') {
			if ($this.val() === '') {
          label.removeClass('active highlight');
        } else {
          label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
    	if( $this.val() === '' ) {
    		label.removeClass('active highlight'); 
			} else {
		    label.removeClass('highlight');   
			}   
    } else if (e.type === 'focus') {
      
      if( $this.val() === '' ) {
    		label.removeClass('highlight'); 
			} 
      else if( $this.val() !== '' ) {
		    label.addClass('highlight');
			}
    }

});

$('.tab a').on('click', function (e) {
  
  e.preventDefault();
  
  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');
  
  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();
  
  $(target).fadeIn(600);
  
});

function isEmail(str){
    var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    return reg.test(str);
}

$("#registerButton").click(function(){
	var email=document.getElementById("registerEmail").value;
	if(!isEmail(email)) {
		alert("邮箱格式错误，请检查后输入");
		return ;
	}
	var password=document.getElementById("registerPassword").value;
	var url="http://139.199.164.23/moana/auth/register.do";
	$.ajax({
		type:'POST',
		url:url,
		data:{
			email:email,
			password:password
		},
		success:function(data){
			if(JSON.parse(data)==="false"){
				alert("请检查是否已注册!");
			}
			
			alert("请登录");
		},
		error:(function(){
			alert("请求失败");
		})
	})
	
});

$("#loginButton").click(function(){
	var email=document.getElementById("loginEmail").value;
	if(!isEmail(email)) {
		alert("邮箱格式错误，请检查后输入");
		return ;
	}
	var password=document.getElementById("loginPassword").value;
	var url="http://139.199.164.23/moana/auth/login.do";
	$.ajax({
		type:'POST',
		url:url,
		data:{
			email:email,
			password:password
		},
		success:function(data){
			if(JSON.parse(data)=="false"){
				alert("请检查密码和邮件地址是否匹配");
			}
			else {
				setCookie("username",JSON.parse(data));
				window.location.href="http://139.199.164.23/moana/static/index.html";
			}
			
		},
		error:(function(){
			alert("请求失败");
		})
	})
	
});

$("#rushTicketButton").click(function(){
	var id=$("#id").text();
	$.ajax({
		type:"POST",
		url:"http://139.199.164.23/moana/rush/rushTicket.do",
		data:{"id":id},
		success:function(data){
			alert(data);
		},
		error:function(){
			alert("请先登录");
			window.location.href="http://139.199.164.23/moana/static/login.html";
		}
	})
})