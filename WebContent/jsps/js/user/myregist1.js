//希望传入的e是一个jquery对象
//根据label里是否有内容显示隐藏错误信息
function showHideError(e){
	var txt = e.text();
	//当里面没有内容，则隐藏
	if(!txt){
		e.css("display","none");
	}else{
		//当label有内容则显示
		e.css("display","");
	}
}

//传入文本框jquery对象和需要输入的文本，显示或者隐藏校验信息
function showHideErrorByLabelId(ele,txt){
	var labelId = ele.attr("id")+"Error";
	$("#"+labelId).text(txt);
	showHideError($("#"+labelId));
}
//调用校验登录名函数等等
function invokeValidateFunction(iid){
	iid = iid.substring(0,1).toUpperCase() + iid.substring(1);//Loginname
	var fname = "validate" + iid;//validateLoginname
	return eval(fname + "()");
}

//校验登录密码长度的函数
function validateLoginpass(){
	var bl=true;
	var password = $("#loginpass").val();
	if(password.length<6 || password.length>20){
		showHideErrorByLabelId($("#loginpass"),"密码应大于6位小于12位");
		$("#"+$("#loginpass").attr("id")+"Error").removeClass("successClass");
		bl=false;
	}else{
		showHideErrorByLabelId($("#loginpass"),"可以使用");
		$("#"+$("#loginpass").attr("id")+"Error").addClass("successClass");
	}
	return bl;
}

//校验登录密码与确认密码是否一致
function validateReloginpass(){
	var bl=true;
	var password = $("#loginpass").val();
	var repassword = $("#reloginpass").val();
	if(!(password == repassword)){
		showHideErrorByLabelId($("#reloginpass"),"确认密码与登录密码要一致");
		$("#"+$("#reloginpass").attr("id")+"Error").removeClass("successClass");
		bl=false;
	}if(!repassword){
		showHideErrorByLabelId($("#reloginpass"),"此处不能为空");
		$("#"+$("#reloginpass").attr("id")+"Error").removeClass("successClass");
		bl=false;
	}else{
		showHideErrorByLabelId($("#reloginpass"),"可以使用");
		$("#"+$("#reloginpass").attr("id")+"Error").addClass("successClass");
	}
	return bl;
}
//邮箱正则
function isEmail(str){
    var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
    return reg.test(str);
}
//邮箱验证
function validateEmail(){
	var bl=true;
	var email = $("#email").val();
	if(!isEmail(email)){
		showHideErrorByLabelId($("#email"),"邮箱格式错误");
		bl=false;
	}else{
		$.post(
				"/goods/userAction?flag=checkEmail",
				{"email":email},
				function(data){
					if(data.status == "exist"){
						showHideErrorByLabelId($("#email"),"邮箱已被占用");
						$("#"+$("#email").attr("id")+"Error").removeClass("successClass");
						bl=false;
					}else{
						showHideErrorByLabelId($("#email"),"邮箱可以注册");
						$("#"+$("#email").attr("id")+"Error").addClass("successClass");
					}
				},
				"json"
		);
	}
	return bl;
}

//图形验证码
function validateVerifyCode(){
	var bl=true;
	var code = $("#verifyCode").val();
	if(!code){
		showHideErrorByLabelId($("#verifyCode"),"请输入验证码");
		bl=false;
	}else{
		$.post(
				"/goods/userAction?flag=checkcode",
				{"code":code},
				function(data){
					if(data.status == "wrong"){
						showHideErrorByLabelId($("#verifyCode"),"验证码不正确");
						$("#"+$("#verifyCode").attr("id")+"Error").removeClass("successClass");
						bl=false;
					}else{
						showHideErrorByLabelId($("#verifyCode"),"正确");
						$("#"+$("#verifyCode").attr("id")+"Error").addClass("successClass");
					}
				},
				"json"
		);
	}
	return bl;
}

//校验登录名函数
function validateLoginname(){
	var bl = true;
	
	var value = $.trim($("#loginname").val());
	if(!value){
		//什么都没填的时候
		//var labelId = $(this).attr("id")+"Error";
		//$("#"+labelId).text("用户名不能为空");
		//showHideError($("#"+labelId));
		showHideErrorByLabelId($("#loginname"),"用户名不能为空");
		bl=false;
	}else if(value.length<6 || value.length>20){
		//提示用户登录名的长度必须在6~20之间
		//var labelId = $(this).attr("id")+"Error";
		//$("#"+labelId).text("登录名的长度必须在6~20之间");
		//showHideError($("#"+labelId));
		showHideErrorByLabelId($("#loginname"),"登录名的长度必须在6~20之间");
		bl=false;
	}else{
		//符合不为空条件，并且符合长度在6~20之间，那么进行Ajax校验
		$.post(
				"/goods/userAction?flag=existLoginname",
				{"loginname":value},
				function(data){
					if(data.status == "exist"){
						//数据库中已经存在该登录名
						//var labelId = $("#loginname").attr("id")+"Error";
						//$("#"+labelId).text("登录名已被占用");
						//showHideError($("#"+labelId));
						//$("#"+labelId).removeClass("successClass");
						showHideErrorByLabelId($("#loginname"),"登录名已被占用");
						$("#"+$("#loginname").attr("id")+"Error").removeClass("successClass");
						bl=false;
					}else{
						//数据库中已经不存在该登录名
						//var labelId = $("#loginname").attr("id")+"Error";
						//$("#"+labelId).text("登录名可以注册");
						//showHideError($("#"+labelId));
						//$("#"+labelId).addClass("successClass");
						showHideErrorByLabelId($("#loginname"),"登录名可以注册");
						$("#"+$("#loginname").attr("id")+"Error").addClass("successClass");
					}
				},
				"json"
		);
	}
	
	return bl;
}

$(function(){
	//换一张功能
	$("#repImg").click(function(){
		$("#vCode").attr("src","/goods/VerifyCodeServlet?a"+new Date().getTime());
	});
	//当光标划过按钮的时候，替换相应图片
	$("#btnSubmit").hover(
			//当光标放入执行
			function(){
				$(this).attr("src","/goods/images/regist2.jpg");
			},
			//当光标离开执行
			function(){
				$(this).attr("src","/goods/images/regist1.jpg");
			}
	);
	
	//先判断label标签中是否有内容，若有，则显示，若没有，则隐藏
	$(".errorClass").each(function(){
		showHideError($(this));
	});
	
	//注册页面所有文本框获得焦点的操作
	$(".input").focus(function(){
		//通过当前控件的ID属性推算出对应label标签的ID
		//var labelId = $(this).attr("id")+"Error";
		//通过上一步获取的labelId,获取jquery对象，并且将内容清空
		//$("#"+labelId).text("");
		//根据实际情况隐藏显示label标签
		//showHideError($("#"+labelId));
		showHideErrorByLabelId($(this),"");
	});
	
	//注册页面所有的文本框失去焦点的操作
	$(".input").blur(function(){
		var iid = $(this).attr("id");
		invokeValidateFunction(iid);
	});	
});

	//点击提交
	$("#regform").submit(function(){
		var bool = false;
		$(".input").each(function() {
			var inputName = $(this).attr("id");
			bool =invokeValidateFunction(inputName);
		})
		return bool;
	})