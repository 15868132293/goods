<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>left</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/zTreeStyle/zTreeStyle.css"
	type="text/css">
</head>

<body>
	<ul id="categoryTree" class="ztree"></ul>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/ztree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript">
	var zTreeObj;
	var setting = {
		async: {
		enable: true,
		url: "/goods/CategoryAction?flag=listCategories",
		autoParam: ["cid"]
		},
	data:{
		simpleData:{
			enable:true,
			idKey:"cid",
			pIdKey:"pid",
			rootPId:null
		},
		key:{
			name:"cname"
		}
	}
	};
	
/* 	var zNodes = [
	   {name:"1", open:true, children:[
	      {name:"11",target:"userAction?flag=find1"}, {name:"12"}]},
	   {name:"2", open:true, children:[
	      {name:"21"}, {name:"22"}]}
	   ]; */
	   /* var zNodes =
		[{"cid":1,"open":true,"pid":null,"cname":"test1"},
		 {"cid":11,"pid":null,"cname":"test11"},
		{"cid":12,"pid":null,"cname":"test12"},	
		{"cid":2,"open":true,"pid":null,"cname":"test2"},
		{"cid":21,"pid":null,"cname":"test21"},
		{"cid":22,"pid":null,"cname":"test21"}
		] */
		var zNodes = [];
	   $(document).ready(function(){
	      zTreeObj = $.fn.zTree.init($("#categoryTree"), setting,zNodes);
	   });
	</script>
</body>
</html>
