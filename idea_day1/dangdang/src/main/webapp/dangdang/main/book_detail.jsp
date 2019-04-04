<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<title>图书详情</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dangdang/css/book_detail.css"/>
		<link href="${pageContext.request.contextPath}/dangdang/css/public_footer.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">		
		function addCart(bookid){
			var xhr;
			if(window.ActiveXObject){
			xhr = new ActiveXObject('Microsoft.xmlhttp');
			}else{
			xhr = new XMLHttpRequest();
			}
			var img = document.getElementById(bookid);
/* 			 aimg.firstChild.remove(); 
			 var img = document.createElement('img'); */
			img.src='${pageContext.request.contextPath}/dangdang/images/load.gif';
			img.setAttribute('width','21px');
			img.setAttribute('height','21px');
/* 			 aimg.appendChild(img);  */	
			xhr.open("GET", '${pageContext.request.contextPath}/cart/addCart?bookid='+bookid);
			xhr.send();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==2){
				var img2 = document.getElementById(bookid);
				img2.setAttribute('width','60px');
				img2.setAttribute('height','18px');
				img2.src='${pageContext.request.contextPath}/dangdang/images/buy_success.png';
				}			
			if(xhr.readyState==4&&xhr.status==200){	
				var txt = xhr.responseText;
				console.log(txt);
				if(txt=="购买成功"){
				var img1 = document.getElementById(bookid);
				img1.src='${pageContext.request.contextPath}/dangdang/images/buttom_goumai.gif';
				}
			}	
			}
		}		
		</script>
	</head>
	<body>
		<br/>
		<div><h1><s:property value="book.name"/></h1></div>
		丛书名：<s:property value="book.name"/>
		<hr/>
		<table width="80%" border="0" align="center" cellspacing="0" cellpadding="5">
			<tr>
				<td rowspan="9" width="20%" valign="top"><img src="${pageContext.request.contextPath}/dangdang/productImages/<s:property value="book.cover"/>" width="120px" height="170px"/></td>
				<td colspan="2">作者：<s:property value="book.author"/></td>
			</tr>
			<tr>
				<td colspan="2">出版社：<s:property value="book.press"/></td>
			</tr>
			<tr>
			
				<td>出版时间：<s:property value="book.press_date"/> </td>
				<td>字数：<s:property value="book.word_num"/></td>
			</tr>
			<tr>
				<td>版次：<s:property value="book.edition"/></td>
				<td>页数：<s:property value="book.page_num"/></td>
			</tr>
			<tr>
				<td>印刷时间：<s:property value="book.printDate"/></td>
				<td>开本：<s:property value="book.sizes"/></td>
			</tr>
			<tr>
				<td>印次：<s:property value="book.impression"/></td>
				<td>纸张：<s:property value="book.paper"/></td>
			</tr>
			<tr>
				<td>ISBN：<s:property value="book.ISBN"/></td>
				<td>包装：<s:property value="book.pack"/></td>
			</tr>
			<tr>
				<td colspan="2">定价：￥<s:property value="book.price"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<font color="red">当当价：￥<fmt:formatNumber><s:property value="book.price*book.discount"/></fmt:formatNumber></font>&nbsp;&nbsp;&nbsp;&nbsp;
				节省：￥<fmt:formatNumber><s:property value="book.price-book.price*book.discount"/></fmt:formatNumber></td>
			</tr>
			<tr>
				<td colspan="2"><a> 
							<img id="<s:property value="book.id"/>" src='${pageContext.request.contextPath}/dangdang/images/buttom_goumai.gif' onclick="addCart(<s:property value="book.id"/>)"/> </a></td>
			</tr>
		</table>
		<hr style="border:1px dotted #666"/>
		<h2>编辑推荐</h2>
		<p>&nbsp;&nbsp;<s:property value="book.editor_recommend"/></p>
		<hr style="border:1px dotted #666"/>
		<h2>内容简介</h2>
		<p>&nbsp;&nbsp;<s:property value="book.content_abstract"/></p>
		<hr style="border:1px dotted #666"/>
		<h2>作者简介</h2>
		<p>&nbsp;&nbsp;<s:property value="book.author_abstract"/></p>
		<hr style="border:1px dotted #666"/>
		<h2>目录</h2>
		<p>&nbsp;&nbsp;<s:property value="book.directory"/></p>
		<hr style="border:1px dotted #666"/>
		<h2>媒体评论</h2>
		<p>&nbsp;&nbsp;<s:property value="book.media_commentary"/></p>
		<hr style="border:1px dotted #666"/>
		<h2>书摘插图</h2>
		<p>&nbsp;&nbsp;</p>
		<!--页尾开始 -->
		<div class="public_footer">
			<div class="public_footer_bottom">
				<div class="public_footer_icp" style="line-height: 48px;">
					Copyright (C) 当当网 2004-2008, All Rights Reserved
					<a href="#" target="_blank"><img src="${pageContext.request.contextPath}/dangdang/images/bottom/validate.gif" border="0" align="middle" /> </a>
					<a href="#" target="_blank" style="color: #666;">京ICP证041189号</a>
				</div>
			</div>
		</div>
		<!--页尾结束 -->
	</body>
</html>
