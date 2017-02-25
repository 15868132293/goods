function change(){
	$("#vCode").attr("src","/goods/VerifyCodeServlet?a"+new Date().getTime());
}