<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
			<script type="text/javascript">
	
		$(function(){
		$(".img2").mouseover(function(e){
		
		var width = this.width;
		var height = this.height;
		
		var x = e.clientX;
		var y = e.clientY;
		
		var div=$("<div id='imgdiv'/>").css({
		width:width*2,
		height:height*2,
		border:"3px orange solid",
		position : "absolute",
		top:y,
		left:x,
		display:"none"
		});
		var img=$("<img/>").attr(
		{src:this.src}
		).css(
		{width:width*2,
		height:height*2,
		borderRadius:10
		});
		
		div.append(img);
		
		$("body").append(div);
		div.show(500);
		}).mousemove(function(e){
			var x = e.pageX+10;
			var y = e.pageY+10;
			$("#imgdiv").css({
			top:y,
			left:x
			});
		
		}).mouseout(function(e){
			$("#imgdiv").remove();
		});				
		});	
	
		</script>

</head>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>热销图书
</h2>

<div class="book_c_04">
<c:forEach items="${list}" var="hot">
	<!--热销图书A开始-->
	
	<div class="second_d_wai">	
		<div class="img">
			<a href="${pageContext.request.contextPath}/b/selectBookById?book.id=${hot.id}" target='_blank'><img
					src="../productImages/${hot.cover}" border=0 class="img2"/> </a>
		</div>
		<div class="shuming">
			<a href="${pageContext.request.contextPath}/b/selectBookById?book.id=${hot.id}" target="_blank">${hot.name}</a><a href="#" target="_blank"></a>
		</div>
		<div class="price">
			定价：￥${hot.price}
		</div>
		<div class="price">
			当当价：￥<fmt:formatNumber >${hot.price*hot.discount}</fmt:formatNumber>
		</div>		
	</div>
	<div class="book_c_xy_long"></div>
	<!--热销图书A结束-->
</c:forEach>
</div>

<div class="clear"></div>