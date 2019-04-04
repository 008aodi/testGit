<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
			<script type="text/javascript">
	
		$(function(){
		$(".img").mouseover(function(e){
		
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
			var x = e.pageX+20;
			var y = e.pageY+20;
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
	编辑推荐
</h2>
<div id=__bianjituijian/danpin>

	<div class=second_c_02>	
	<c:forEach items="${recommends}" var="rec">
		<div class=second_c_02_b1>
			<div class=second_c_02_b1_1>
				<a href='${pageContext.request.contextPath}/b/selectBookById?book.id=${rec.id}' target='_blank'><img src="../productImages/${rec.cover}" width=70 border=0 class="img"/> </a>
			</div>
			<div class=second_c_02_b1_2>
			
				<h3>
					<a href='${pageContext.request.contextPath}/b/selectBookById?book.id=${rec.id}' target='_blank' title='输赢'>${rec.name}</a>
				</h3>
				<h4>
					作者：${rec.author}
					<br />
					出版社：${rec.press}&nbsp;&nbsp;&nbsp;&nbsp;出版时间：${rec.press_date}
				</h4>
				<h5>
					简介
					${rec.editor_recommend}
				</h5>
				<h6>
					定价：￥${rec.price}&nbsp;&nbsp;当当价：<fmt:formatNumber>${rec.price*rec.discount}</fmt:formatNumber>
				</h6>
				
				<div class=line_xx></div>
			</div>	
		</div>
	</c:forEach>
	</div>
</div>

