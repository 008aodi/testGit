<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="${pageContext.request.contextPath}/dangdang/css/book.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/second.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/shopping_vehicle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
		function fun1(id){		
		var count=$("#"+id).val();
		var flag = /(^[0-9]*$)/;		
		if(count==""||count==0){
		confirm("您还没有输入数量");
		}else if(!flag.test(count)){
		confirm("您输入的数字含有非法字符请重新输入");
		}else{
		location.href="${pageContext.request.contextPath }/cart/updateCart?bookid="+id+"&account="+count;
		}
		}		  
		</script>
	
	</head>
	<body>
		<br />
		<br />
		<s:if test="#session.showcart.size()>0">
		<div class="my_shopping">
			<img class="pic_shop" src="${pageContext.request.contextPath}/dangdang/images/pic_myshopping.gif" />

		</div>
		
		
		<div id="div_choice" class="choice_merch">
			<h2 id="cart_tips">
				您已选购以下商品
			</h2>
			<div class="choice_bord">
				<table class="tabl_buy" id="tbCartItemsNormal">
					
					<tr class="tabl_buy_title">
						<td class="buy_td_6">
							<span>&nbsp;</span>
						</td>
						<td>
							<span class="span_w1">商品名</span>
						</td>
						<td class="buy_td_5">
							<span class="span_w2">市场价</span>
						</td>
						<td class="buy_td_4">
							<span class="span_w3">当当价</span>
						</td>
						<td class="buy_td_1">
							<span class="span_w2">数量</span>
						</td>
						<td class="buy_td_2">
							<span>变更数量</span>
						</td>
						<td class="buy_td_1">
							<span>删除</span>
						</td>
					</tr>
					<tr class='objhide' over="no">
						<td colspan="8">
							&nbsp;
						</td>
					</tr>
					
                      <!-- 购物列表开始 -->
						<s:iterator value="#session.showcart" status="stu">
						<tr class='td_no_bord'>
							<td style='display: none'>
								<s:property value="key"/>
							</td>
							<td class="buy_td_6">
								<span class="objhide"><img src="${pageContext.request.contextPath}/dangdang/productImages/<s:property value="value.book.cover"/>"/> <s:property value="#stu.index+1"/></span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/b/selectBookById?book.id=<s:property value="value.book.id"/>">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="value.book.name"/></a>
							</td>
							<td class="buy_td_5">
								<span class="c_gray"><s:property value="value.book.price"/></span>
							</td>
							<td class="buy_td_4">
								&nbsp;&nbsp;
								<span>￥<fmt:formatNumber><s:property value="value.book.price*value.book.discount"/></fmt:formatNumber></span>
							</td>
							<td class="buy_td_1">
								&nbsp;&nbsp;<s:property value="value.count"/>
							</td>

							<td >	
								<input class="del_num" type="text" size="3" maxlength="4" id="<s:property value="key"/>"/>
						<a href="javascript:void(0)" onclick="fun1(<s:property value="key"/>)">变更</a>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/cart/removeCart?bookid=<s:property value="value.book.id"/>">删除</a>
							</td>
						</tr>
						</s:iterator>
						
					<!-- 购物列表结束 -->
				</table>
				
		<!-- 		<div id="div_no_choice" class="objhide">
					<div class="choice_title"></div>
					<div class="no_select">
						您还没有挑选商品
					</div>
				</div> -->
				
				
				<div class="choice_balance">
					<div class="select_merch">
						<a href='${pageContext.request.contextPath}/dangdang/main/main.jsp'> 继续挑选商品>></a>
					</div>
					<div class="total_balance">
						<div class="save_total">
							您共节省：
							<span class="c_red"> ￥<span id="total_economy"><fmt:formatNumber><s:property value="#session.save"/></fmt:formatNumber></span>
							</span>
							<span id='total_vip_economy' class='objhide'> ( 其中享有优惠： <span
								class="c_red"> ￥<span id='span_vip_economy'>0.00</span> </span>
								) </span>
							<span style="font-size: 14px">|</span>
							<span class="t_add">商品金额总计：</span>
							<span class="c_red_b"> ￥<span id='total_account'><fmt:formatNumber><s:property value="#session.total"/></fmt:formatNumber></span>
							</span>
						</div>
						<div id="balance" class="balance">
							<a name='checkout' href='${pageContext.request.contextPath}/dangdang/order/order_info.jsp' > 
								<img src="${pageContext.request.contextPath}/dangdang/images/butt_balance.gif" alt="结算" border="0" title="结算" />
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		</s:if><s:else>
		<div id="div_no_choice">
				<div class="choice_title"></div>
				<div class="no_select">
					您还没有挑选商品<a href='${pageContext.request.contextPath}/dangdang/main/main.jsp'> 继续挑选商品&gt;&gt;</a>
				</div>
		</div>
		</s:else>
		
		<!-- 用户删除恢复区 -->

		<s:if test="#session.restorecart.size()>0">
		<div id="divCartItemsRemoved" class="del_merch">
			<div class="del_title">
				您已删除以下商品，如果想重新购买，请点击“恢复”
			</div>
			<table class=tabl_del id=del_table>
				<tbody>

					<s:iterator value="#session.restorecart">
					<tr>
						<td width="58" class=buy_td_6>
							&nbsp;
						</td>
						<td width="365" class=t2>
							<a href="${pageContext.request.contextPath}/b/selectBookById?book.id=<s:property value="value.book.id"/>"><s:property value="value.book.name"/></a>
						</td>
						<td width="106" class=buy_td_5>
							￥<s:property value="value.book.price"/>
						</td>
						<td width="134" class=buy_td_4>
							<span>￥ <fmt:formatNumber><s:property value="value.book.price*value.book.discount"/></fmt:formatNumber></span>
						</td>
						<td width="56" class=buy_td_1>
							<a href="${pageContext.request.contextPath}/cart/restoreCart?bookid=<s:property value="value.book.id"/>">恢复</a>
						</td>
						<td width="16" class=objhide>
							&nbsp;
						</td>
					</tr>
					</s:iterator>
					<tr class=td_add_bord>
						<td colspan=8>
							&nbsp;
						</td>
					</tr>

				</tbody>
			</table>
		</div>
		</s:if>
		<br />
		<br />
		<br />
		<br />
		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>



