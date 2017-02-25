//希望传入的e是一个Jquery对象
function showhideError(e){
	var txt = e.text();
	if(!txt){
		e.css("display","none");
	}else{	
		e.css("display","");
	}
}
	
	
	//密码不能小于六位或长于20位
	$("#loginpass").blur(function(){
		var password = $("#loginpass").val();
		if(password.length<6 || password.length>20){
			//var labelId = $(this).attr("id")+"Error";
			$("#loginpassError").text("密码不能过长或过短");
			//根据实际情况隐藏显示label标签
			showhideError($("#loginpassError"));
		}else{
			$("#loginpassError").after("<img id='passright' src='../../images/right.png'>");
		}
	})
	//密码再次获取焦点，删除勾
	$("#loginpass").focus(function(){
		$("#passright").remove();
	})
	
	//确认密码与登录密码是否一致
	$("#reloginpass").blur(function(){
		var password = $("#loginpass").val();
		var repassword = $("#reloginpass").val();
		if(!(password == repassword)){
			
			$("#reloginpassError").text("确认密码与登录密码要一致");
			showhideError($("#reloginpassError"));
		}else{
			 $("#reloginpassError").after("<img id='loginpassright' src='../../images/right.png'>");
		}
		
	})
	
	//确认密码再次获得焦点
	$("#reloginpass").focus(function(){
		 $("#loginpassright").remove();
	})
	
	//换一张校验码
	$("#repImg").click(function(){
			$("#vCode").attr("src","../../com.vcode.VerifyCodeServlet?a"+new Date().getTime());
		});
	
	//当光标划过立即提交，替换图片
	
	$("#btnSubmit").hover(
			//当光标放入执行
			function(){
				$(this).attr("src",".../../images/regist2.jpg");
			},
			//当光标离开执行
			function(){
				$(this).attr("src","../../images/regist1.jpg");
			}
	);
	
	//先判断label标签中是否有内容，若有，则显示，若无，则隐藏
	$(".errorClass").each(function(){
		showhideError($(this));
	})
	
	//登录名文本框的校验
	//获得焦点
	$(".input").focus(function(){
		//通过当前控件的ID属性推算出对应的label标签的ID号
		var labelId = $(this).attr("id")+"Error";
		$("#"+labelId).text("");
		//根据实际情况隐藏显示label标签
		showhideError($("#"+labelId));
		
	});
	//用户名再次获得焦点，讲后面的勾和文本框清空
	$("#loginname").focus(function(){
		var value = $("#loginname").val().trim();
		$.ajax({
			url:"/goods/userAction?flag=existLoginname",
			type:"POST",
			data:{"loginname":value},
			dataType:"json",
			success:function(data){
				 if(data.status == "exist"||value.length<3 || value.length>20){
					 //已存在，不能注册
					 $("#loginname").val("");
				 }else{
					 $("#loginnameright").remove();
					 
				 }
			}
		});
	})
	//失去焦点
	$("#loginname").blur(function(){
		var value = $(this).val().trim();
		if(!value){
			//用户名未填写
			var labelId = $(this).attr("id")+"Error";
			$("#"+labelId).text("用户名不能为空");
			showhideError($("#"+labelId));
		}else if(value.length<4 || value.length>20){
			//登录名长度超界限
			var labelId = $(this).attr("id")+"Error";
			$("#"+labelId).text("登录名超过界限");
			showhideError($("#"+labelId));
			
		}else{
			$.ajax({
				url:"/goods/userAction?flag=existLoginname",
				type:"POST",
				data:{"loginname":value},
				dataType:"json",
				success:function(data){
					 if(data.status == "exist"){
						 //已存在，不能注册
						 var labelId = $("#loginname").attr("id")+"Error";
						 $("#"+labelId).text("用户名已被注册");
						 showhideError($("#"+labelId));
					 }else{
						 $("#loginnameError").after("<img id='loginnameright' src='../../images/right.png'>");
					 }
				}
			});
		}
	});
	
	//验证码验证
	
	$("#verifyCode").blur(function(){
		var code =$(this).val(); 
		$.ajax({
			url:"/goods/userAction?flag=checkcode",
			type:"POST",
			data:{"code":code},
			dataType:"json",
			success:function(data){
				 if(data.status == "right"){
					 $("#codeError").after("<img id='codenameright' src='../../images/right.png'>"); 
				 }
			}
		})
	})
	

	
	