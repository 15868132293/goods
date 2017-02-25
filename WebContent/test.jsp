<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/zTreeStyle/zTreeStyle.css" type="text/css">
<body>
	<ul id="categoryTree" class="ztree"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript">
	var zTreeObj;
	   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	   var setting = {};
	   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
	   var zNodes = [{"children":[{"cid":"5F79D0D246AD4216AC04E9C5FAB3199E","cname":"Java Javascript","desc":"Java Javascript分类","orderBy":10,"parents":{"$ref":"$[0]"},"pid":"1"},{"cid":"84ECE401C2904DBEA560D04A581A66D9","cname":"HTML XML","desc":"HTML XML分类","orderBy":13,"parents":{"$ref":"$[0]"},"pid":"1"},{"cid":"922E6E2DB04143D39C9DDB26365B3EE8","cname":"C C++ VC VC++","desc":"C C++ VC VC++分类","orderBy":12,"parents":{"$ref":"$[0]"},"pid":"1"},{"cid":"C3F9FAAF4EA64857ACFAB0D9C8D0E446","cname":"PHP","desc":"PHP分类","orderBy":14,"parents":{"$ref":"$[0]"},"pid":"1"},{"cid":"C8E274EE5C99499080A98E24F0BD2E03","cname":".NET","desc":".NET分类","orderBy":15,"parents":{"$ref":"$[0]"},"pid":"1"},{"cid":"D45D96DA359A4FEAB3AB4DCF2157FC06","cname":"JSP","desc":"JSP分类","orderBy":11,"parents":{"$ref":"$[0]"},"pid":"1"}],"cid":"1","cname":"程序设计","desc":"程序设计分类","orderBy":1},{"children":[{"cid":"4D01FFF0CB94468EA907EF42780668AB","cname":"购买指南 组装指南 维修","desc":"购买指南 组装指南 维修分类","orderBy":18,"parents":{"$ref":"$[1]"},"pid":"2"},{"cid":"B92ED191DBE647BE8F75721FB231E207","cname":"因特网 电子邮件","desc":"因特网 电子邮件分类","orderBy":19,"parents":{"$ref":"$[1]"},"pid":"2"},{"cid":"C4DD8CA232864B31A367EE135D86382C","cname":"计算机初级入门","desc":"计算机初级入门分类","orderBy":17,"parents":{"$ref":"$[1]"},"pid":"2"},{"cid":"F5C091B3967442A2B35EFEFC4EF8746F","cname":"微软Office","desc":"微软Office分类","orderBy":16,"parents":{"$ref":"$[1]"},"pid":"2"}],"cid":"2","cname":"办公室用书","desc":"办公室用书","orderBy":2},{"children":[{"cid":"56AD72718C524147A2485E5F4A95A062","cname":"3DS MAX","desc":"3DS MAX分类","orderBy":21,"parents":{"$ref":"$[2]"},"pid":"3"},{"cid":"57DE3C2DDA784B81844029A28217698C","cname":"Dreamweaver","desc":"Dreamweaver分类","orderBy":24,"parents":{"$ref":"$[2]"},"pid":"3"},{"cid":"65640549B80E40B1981CDEC269BFFCAD","cname":"Photoshop","desc":"Photoshop分类","orderBy":20,"parents":{"$ref":"$[2]"},"pid":"3"},{"cid":"757BDAB506A445EC8DEDA4CE04303B9F","cname":"网页设计","desc":"网页设计分类","orderBy":22,"parents":{"$ref":"$[2]"},"pid":"3"},{"cid":"B596ECE0F9BF40288F40A66B35551806","cname":"Flush","desc":"Flush分类","orderBy":23,"parents":{"$ref":"$[2]"},"pid":"3"},{"cid":"DCAD0384A6444C048951C7B36C5D96EE","cname":"Flash","desc":"Flash分类","orderBy":25,"parents":{"$ref":"$[2]"},"pid":"3"}],"cid":"3","cname":"图形 图像 多媒体","desc":"图形图像多媒体","orderBy":3},{"children":[{"cid":"458795C27E7346A8A5F1B942319297E0","cname":"系统开发","desc":"系统开发分类","orderBy":29,"parents":{"$ref":"$[3]"},"pid":"4"},{"cid":"65830AB237EF428BAE9B7ADC78A8D1F6","cname":"Unix","desc":"Unix分类","orderBy":28,"parents":{"$ref":"$[3]"},"pid":"4"},{"cid":"FAB7B7F7084F4D57A0808ADC61117683","cname":"Windows","desc":"Windows分类","orderBy":26,"parents":{"$ref":"$[3]"},"pid":"4"}],"cid":"4","cname":"操作系统/系统开发","desc":"操作系统/系统开发","orderBy":4},{"children":[{"cid":"96F209F79DB242E9B99CC1B98FAB01DB","cname":"数据库理论","desc":"数据库理论分类","orderBy":33,"parents":{"$ref":"$[4]"},"pid":"5"},{"cid":"F4FBD087EB054CA1896093F172AC33D9","cname":"数据仓库与数据挖掘","desc":"数据仓库与数据挖掘分类","orderBy":30,"parents":{"$ref":"$[4]"},"pid":"5"}],"cid":"5","cname":"数据库","desc":"数据库","orderBy":5},{"children":[{"cid":"A9CFBED0F77746C5BD751F2502FAB2CD","cname":"电子商务 电子政务","desc":"电子商务 电子政务分类","orderBy":35,"parents":{"$ref":"$[5]"},"pid":"6"}],"cid":"6","cname":"网络与数据通讯","desc":"网络与数据通讯!","orderBy":6}]
	   $(document).ready(function(){
	      zTreeObj = $.fn.zTree.init($("#categoryTree"), setting, zNodes);
	   });
	</script>
</body>
</html>