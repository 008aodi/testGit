 <%@page contentType="text/html;charset=utf-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery-1.8.3.min.js"></script>
		</script>
		<link href="${pageContext.request.contextPath}/dangdang/css/book.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/second.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">		
		function addCart(bookid){
			var xhr;
			if(window.ActiveXObject){
			xhr = new ActiveXObject('Microsoft.xmlhttp');
			}else{
			xhr = new XMLHttpRequest();
			}
			var aimg = document.getElementById(bookid);
/* 			 aimg.firstChild.remove(); 
			 var img = document.createElement('img'); */
			aimg.firstElementChild.src='${pageContext.request.contextPath}/dangdang/images/load.gif';
			aimg.firstElementChild.setAttribute('width','21px');
			aimg.firstElementChild.setAttribute('height','21px');
/* 			 aimg.appendChild(img);  */	
			xhr.open("GET",'${pageContext.request.contextPath}/cart/addCart?bookid='+bookid);
			xhr.send();
			xhr.onreadystatechange = function(){
				if(xhr.readyState==2){
				var aimg2 = document.getElementById(bookid);
				aimg2.firstElementChild.setAttribute('width','69px');
				aimg2.firstElementChild.setAttribute('height','21px');
				aimg2.firstElementChild.src='${pageContext.request.contextPath}/dangdang/images/buy_success.png';
				}			
			if(xhr.readyState==4&&xhr.status==200){	
				var txt = xhr.responseText;
				console.log(txt);
				if(txt=="购买成功"){
				var aimg1 = document.getElementById(bookid);
				aimg1.firstElementChild.src='${pageContext.request.contextPath}/dangdang/images/buttom_goumai.gif';
				}
			}	
			}
		}
		
		</script>
		
		
		
		
		<script type="text/javascript">
		function changdesc(fid,sid,condition){
				window.location.href="${pageContext.request.contextPath}/b/selectBooksByCategoryId?fid="+fid+'&sid='+sid+"&condition="+condition;
				}		
			function changFid(fid,condition){
			window.location.href="${pageContext.request.contextPath}/b/selectBooksByCategoryId?fid="+fid+"&condition="+condition;
			}
			</script>
	<script type="text/javascript">
	
		$(function(){
		$(".img3").mouseover(function(e){
		
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
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->

		<div style="width: 962px; margin: auto;">
			<a href="${pageContext.request.contextPath}/dangdang/main/main.jsp"><img src="${pageContext.request.contextPath}/dangdang/images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>
		
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='${pageContext.request.contextPath}/dangdang/main/main.jsp'>当当图书</a> &gt;&gt;
			<s:if test="sid==null">
			<font style='color: #cc3300'><strong><s:property value="#request.category.name"/></strong> </font>
			</s:if><s:else>
			<s:iterator value="#request.category.list">
			<s:if test="sid==id">	
			<a href='${pageContext.request.contextPath}/b/selectBooksByCategoryId?fid=<s:property value="#request.category.id"/>'>
			 <strong><s:property value="#request.category.name"/></strong></a>
			  &gt;&gt;
			<font style='color: #cc3300'><strong><s:property value="name"/></strong> </font>			
			</s:if></s:iterator>	
			</s:else>
		</div>
		
		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class=second_l_border2>
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
								<s:if test="sid==null">
									<div class=second_fenlei>
										&middot;<font style='color: #cc3300'><strong>全部</strong> </font>&nbsp;(<s:property value="category.account"/>)
									</div>
									</s:if><s:else>
									<div class=second_fenlei>
										&middot;<font><strong>全部</strong> </font>&nbsp;(<s:property value="category.account"/>)
									</div>
									</s:else>
								</div>
							</li>
							<div class="clear"></div>
							<!--2级分类开始-->
							<s:iterator value="#request.category.list">
							<li>
								<div>
									<div class=second_fenlei>
										&middot;
									</div>
									<s:if test="sid==id">
									<div class=second_fenlei>	
										<a href="${pageContext.request.contextPath}/b/selectBooksByCategoryId?fid=<s:property value="parent_id"/>&sid=<s:property value="id"/>">					
											<font style='color: #cc3300'><strong><s:property value="name"/></strong> </font>&nbsp;(<s:property value="account"/>)</a>				
									</div>
									</s:if><s:else>
									<div class=second_fenlei>
										<a href="${pageContext.request.contextPath}/b/selectBooksByCategoryId?fid=<s:property value="parent_id"/>&sid=<s:property value="id"/>">					
											<s:property value="name"/>&nbsp;(<s:property value="account"/>)</a>
									</div>
									</s:else>
								</div>						
							</li>
						    <div class="clear"></div>
						    </s:iterator>
							<!--2级分类结束-->
							
							<li>
								<div></div>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						
						
		<s:if test="sid!=null">
						<select onchange='changdesc(<s:property value="fid"/>,
						<s:property value="sid"/>,
						this.value)' name='select_order' size='1'
							class='list_r_title_ml'>
								<s:if test="''.equals(condition)">
								<option value="" selected="selected">
							未选择排序条件
							</option>
							</s:if><s:else>
							<option value="">
							未选择排序条件
							</option>
								</s:else>
							<s:if test="condition.equals('create_date')">								
							<option value="create_date" selected="selected">
								按上架时间 降序
							</option>
							</s:if><s:else>
							<option value="create_date" >
								按上架时间 降序
							</option>
							</s:else>
							<s:if test="'sale'.equals(condition+'')">							
							<option value="sale" selected="selected">
								按销售数量 降序
							</option>
							</s:if><s:else>
							<option value="sale" >
								按销售数量 降序
							</option>
							</s:else>
							<s:if test="condition.equals('price')">
							<option value="price" selected="selected">
								按销售价格 降序
							</option>
							</s:if><s:else>
							<option value="price" >
								按销售价格 降序
							</option>
							</s:else>
						</select>
						
		</s:if><s:else>
						<select onchange='changFid(<s:property value="fid"/>,
						this.value)' name='select_order' size='1'
							class='list_r_title_ml'>
								<s:if test="''.equals(condition)">
								<option value="" >
							未选择排序条件
							</option>
							</s:if><s:else>
								<option value="" selected="selected">
							未选择排序条件
							</option>
							</s:else>	
							
							
							<s:if test="condition.equals('create_date')">
							<option value="create_date" selected="selected">
								按上架时间 降序
							</option>
							</s:if><s:else>
							<option value="create_date" >
								按上架时间 降序
							</option>
							</s:else>
							<s:if test="'sale'.equals(condition+'')">
							<option value="sale" selected="selected">
								按销售数量 降序
							</option>
							</s:if><s:else>
							<option value="sale">
								按销售数量 降序
							</option>
							</s:else>
									<s:if test="condition.equals('price')">
							<option value="price" selected="selected">
								按销售价格 降序
							</option>
							</s:if><s:else>
							<option value="price">
								按销售价格 降序
							</option>
						</s:else>		
						</select>
				</s:else>
						
						<div id="divTopPageNavi" class="list_r_title_text3">

							<!--分页导航开始-->
							<s:if test="page.pageIndex>1">
							<s:if test="sid!=null">
							<div class='list_r_title_text3a'>	
								<a name=link_page_next
									href="${pageContext.request.contextPath}/b/selectBooksByCategoryId
									?fid=<s:property value="category.id"/>&sid=<s:property value="sid"/>&condition=<s:property value="condition"/>
									&page.pageIndex=<s:property value="page.pageIndex-1"/>">
								<img src='${pageContext.request.contextPath}/dangdang/images/page_up.gif'/> </a>			
							</div>
							</s:if><s:else>
							<div class='list_r_title_text3a'>	
								<a name=link_page_next
									href="${pageContext.request.contextPath}/b/selectBooksByCategoryId
									?fid=<s:property value="category.id"/>&condition=<s:property value="condition"/>
									&page.pageIndex=<s:property value="page.pageIndex-1"/>">
								<img src='${pageContext.request.contextPath}/dangdang/images/page_up.gif'/> </a>			
							</div>
							</s:else>
							
							</s:if><s:else>
							<div class='list_r_title_text3a'>
								<img src='${pageContext.request.contextPath}/dangdang/images/page_up_gray.gif'/>
							</div>
							</s:else>
							<div class='list_r_title_text3b'>
								第<s:property value="page.pageIndex"/>页/共<s:property value="page.pageCount"/>页
							</div>
							<s:if test="Page.pageIndex<Page.pageCount">
							
							
							<s:if test="sid!=null">
							
							<div class='list_r_title_text3a'>
								<a name=link_page_next
									href="${pageContext.request.contextPath}/b/selectBooksByCategoryId
									?fid=<s:property value="category.id"/>&sid=<s:property value="sid"/>&condition=<s:property value="condition"/>
									&page.pageIndex=<s:property value="page.pageIndex+1"/>">
									<img src='${pageContext.request.contextPath}/dangdang/images/page_down.gif'/></a>
							</div>
							</s:if><s:else>
							<div class='list_r_title_text3a'>
								<a name=link_page_next
									href="${pageContext.request.contextPath}/b/selectBooksByCategoryId
									?fid=<s:property value="category.id"/>&condition=<s:property value="condition"/>
									&page.pageIndex=<s:property value="page.pageIndex+1"/>">
									<img src='${pageContext.request.contextPath}/dangdang/images/page_down.gif'/></a>
							</div>
							</s:else>
							
								</s:if><s:else>
							<div class='list_r_title_text3a'>
								<img src='${pageContext.request.contextPath}/dangdang/images/page_down_gray.gif'/>
							</div>
							</s:else>
							<!--分页导航结束-->
						</div>
					</div>
					
					<!--商品条目开始-->
						<s:iterator value="books" >
						<div class="list_r_line"></div>
						<div class="clear"></div>
						<div class="list_r_list">			
							<span class="list_r_list_book"><a name="link_prd_img" href='${pageContext.request.contextPath}/b/selectBookById?book.id=<s:property value="id"/>'>
								<img class="img3" src="${pageContext.request.contextPath}/dangdang/productImages/<s:property value="cover"/>"/> </a> </span>
							<h2>
								<a name="link_prd_name" href='${pageContext.request.contextPath}/b/selectBookById?book.id=<s:property value="id"/>'><s:property value="name"/></a>
							</h2>
							<h3>
								顾客评分：100
							</h3>
							<h4 class="list_r_list_h4">
								作 者:
								<a href='${pageContext.request.contextPath}/b/selectBookById?book.id=<s:property value="id"/>' name='作者'><s:property value="author"/></a>
							</h4>
							<h4>
								出版社：
								<a href='${pageContext.request.contextPath}/b/selectBookById?book.id=<s:property value="id"/>' name='出版社'><s:property value="press"/></a>
							</h4>
							<h4>
								出版时间：<s:property value="press_date"/>
							</h4>
							<h5>
								<s:property value="edition"/>
							</h5>
							<div class="clear"></div>
							<h6>
								<span class="del">￥定价：<s:property value="price"/></span>
								<span class="red">￥当当价：<fmt:formatNumber><s:property value="price*discount"/></fmt:formatNumber></span>
								节省：￥<fmt:formatNumber><s:property value="price-price*discount"/></fmt:formatNumber>
							</h6>
							<span class="list_r_list_button"/> 
							<a id="<s:property value="id"/>"> 
							<img src='${pageContext.request.contextPath}/dangdang/images/buttom_goumai.gif' onclick="addCart(<s:property value="id"/>)"/> </a>
							<span id="cartinfo"></span>
							
						</div>					
						<div class="clear"></div>
				
					</s:iterator>
						<!--商品条目结束-->

					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>

				</div>
				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
						