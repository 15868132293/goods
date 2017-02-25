<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.jsp'/>"></script>
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/desc.css'/>">
	<script src="<c:url value='/jsps/js/book/desc.js'/>"></script>
  </head>
  
  <body>
  <div class="divBookName">${singlebook.bname }</div>
  <div>
    <img align="top" src="<c:url value='/${singlebook.image_w }'/>" class="img_image_w"/>
    <div class="divBookDesc">
	    <ul>
	    	<li>商品编号：4A9574F03A6B40C1B2A437237C17DEEC</li>
	    	<li>现价：<span class="price_n">&yen;${singlebook.price }</span></li>
	    	<li>定价：<span class="spanPrice">&yen;59.0</span>　折扣：<span style="color: #c30;">${singlebook.discount }</span>折</li>
	    </ul>
		<hr class="hr1"/>
		<table>
			<tr>
				<td colspan="3">
					作者：${singlebook.author } 著
				</td>
			</tr>
			<tr>
				<td colspan="3">
					出版社：${singlebook.press }
				</td>
			</tr>
			<tr>
				<td colspan="3">出版时间：${singlebook.publishtime }</td>
			</tr>
			<tr>
				<td>版次：${singlebook.edition }</td>
				<td>页数：${singlebook.pageNum }</td>
				<td>字数：${singlebook.wordNum }</td>
			</tr>
			<tr>
				<td width="180">印刷时间：${singlebook.printtime }</td>
				<td>开本：${singlebook.booksize }</td>
				<td>纸张：${singlebook.paper }</td>
			</tr>
		</table>
		<div class="divForm">
			<form id="form1" action="/goods/cartItemAction?flag=tobuy&pageNum=1" method="post">
				<input type="hidden" name="bid" value="${singlebook.bid}"/>
				<input type="hidden" name="bname" value="${singlebook.bname}"/>
				
  				我要买：<input id="cnt" style="width: 40px;text-align: center;" type="text" name="quantity" value="1"/>件
  			</form>
  			<a id="btn" href="javascript:$('#form1').submit();"></a>
  		</div>	
	</div>
  </div>
  </body>
</html>
