<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>

  <head>

  </head>
  
  <body>
  <table id="articledg"></table>
  	<!-- 添加上师文章 -->
  	 <div style="display:none;padding:30px " id="addarticleDialog">
       		<form id="addarticleFom" enctype="multipart/form-data" method="POST">
       			<table>
       				<tr>
       					<td>
			       			文章标题：
       					</td>
       					<td>
       						<input type="text" name="title" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr>       				
       				 <tr>
       					<td>
			       			文章作者：
       					</td>
       					<td>
       						<select id="guruId"  name="guruId" >
       						<option selected="selected" id="firstOption">请选择上师</option>
       						</select>
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			文章内容：
       					</td>
       					<td >
       						<textarea name="content" style="height:150px" id="content">
       							
       						</textarea>
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			文章插图：
       					</td>
       					<td>
	       				
	       						<input type="file" id="upfile" name="upfile" class="easyui-validatebox" data-options="required:true,accept:'image/jpeg'">
       					</td>
       				</tr>      			      				       				
       				<tr align="center">
       					<td colspan="2">
       						<a href="javascript:void(0)" class="easyui-linkbutton" 
       							data-options="iconCls:'icon-ok',text:'提交'" id="subAddarticle"></a>
       					</td>
       				</tr>
       			</table>
       		</form>
       </div>
  	
  	<script type="text/javascript">	
  		$(function(){
  			$('#articledg').edatagrid({
  				url:'${pageContext.request.contextPath}/article/findall.do',
  				fit:true,
  				striped:true,
  				singleSelect:true,
  				rownumbers:true,
  				pagination:true,
  				fitColumns:true,
  				nowrap:false,
  				pagination:true,
  				pageList:[5,10,15,20], 
  				pageSize:5, 
  				pageNumber:1, 
  				pagePosition:'both',
  				columns:[[
  				{field:'id',title:'文章编号',width:100},
  				{field:'title',title:'文章名字',width:100,editor:'text'},
  				{field:'articlePic',title:'文章图片',width:100},
  				{field:'content',title:'文章内容',width:100,editor:'text'},
  				{field:'publicTime',title:'发布时间',width:100,editor:'text'},  
  				{field:'dharnaName',title:'上师法名',width:100,
  				formatter:function(value,row,index){
  				return row.guru.dharnaName;
  				}
  				},		
  				]], 				
  				view: detailview, 
				detailFormatter: function(rowIndex, rowData){ 
				return '<table><tr>' + 
				'<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}'+rowData.articlePic + '" style="height:50px;"></td>' + 
				'<td style="border:0">' + 
				'<p>文章名字: ' + rowData.title + '</p>' + 
				'<p>发布时间: ' + rowData.publicTime + '</p>' + 
				'<p>文章作者: ' + rowData.guru.dharnaName + '</p>' + 
				'</td>' + 
				'</tr></table>'; 
				},  								
  				toolbar:[
  						//添加
  						{
		            	  text:'添加',
		            	  iconCls:'icon-add',
		            	  handler:function(){
		            		  //初始化diglog
		            		  $('#addarticleDialog').dialog({
		            		  	 title:'添加',
		            		  	 iconCls:'icon-add',
		            		  	 width:380,
		            		  	 height:400,		        		  	 
		            		  });
		            		 var a= $("#firstOption+option").val();
		            		 if(a==null){
								$.ajax({
		            		  type:"POST",
		            		  url:'${pageContext.request.contextPath}/guru/find.do',
		            		  dataType:'JSON',
		            		  success:function(data){
		     /*        		  var child=$("#guruId").lastChild();
		            		  if(child=='请选择上师'){
		            		  		$("#guruId").empty();
		            		  		$("#guruId")append($('<option selected="selected" >请选择上师</option>'));
		            		  } */
		            		  	for(var i=0;i<data.length;i++){
								var option = $('<option value="'+data[i].id+'">'+data[i].dharnaName+'</option>');
								$("#guruId").append(option);}
		            		  }
		            		  });	
		            		 }

		            		  
		            		 //初始化form
		            		  $('#addarticleFom').form({
		            		  	url:'${pageContext.request.contextPath}/article/add.do',
		            		  	success:function(data){
		            		  		// 关闭dialog
							   			if(data=='ok'){
							   		  $.messager.alert('提示','添加成功','info');
							   		$('#addarticleDialog').dialog('close');
							   				}
							   			// 刷新数据表格
							   		$('#articledg').edatagrid('load');
							   					
		            		  	},
		            		  	error:function(){
		            		  	$.messager.alert('提示','添加失败','info');
		            		  	},
		            		  	onSubmit:function(){
		            		  		return $('#addarticleFom').form('validate');
		            		  	},
		            		  });		            		  
		            	  }
		              },
		              //删除
		            	{
		               text:'删除',
		               iconCls:'icon-Empty',
		              	handler:function(){
		              	 var selRow = $('#articledg').edatagrid('getSelected');
					   if(selRow==null){// 说明没有选中行， 提示用户一下
						   $.messager.alert('提示','请您选中要删除的行','warning');
					   }else{
					 	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
    							if (r){    
        						$.ajax({
					   			url:'${pageContext.request.contextPath}/article/delete.do',
					   			type:'post',
					   			data:'id='+selRow.id,
					   			success:function(data){
					   			if(data=='ok'){
					   			$.messager.alert('提示','删除成功','info');
					   			$('#articledg').edatagrid('load');
					   			}else{
					   			 $.messager.alert("删除失败");
					   			}					   				
					   			}
					   		});	    
   							}    
							});  
					   }
		              	},
		              },
		              ],
				
  			});
  			$('#articledg').edatagrid('disableEditing');
  			$('#subAddarticle').click(function(){
	  			if(checkguru()==false){
	  			 $.messager.alert('提示','请您选择添加文章的上师','warning');
	  			}else if(checkContent()==false){
	  			 $.messager.alert('提示','请您填写文章内容','warning');
	  			}else{
	  			$('#addarticleFom').submit();
	  			}
  			}); 
  			
  			//检查是否选择大师 			
  			function checkguru(){
  				var a = $("#guruId").val();
		           if(a=='请选择上师'){
		           return false;
		           }else{
		           return true;
		           }
  			}
  			//检查添加文章内容是否位空
  			function checkContent(){
  			var b = $("#content").val();
  				if(b==''){
  				 return false;
		           }else{
		           return true;
		           }
  			}
  		});
  		
  	</script>
  	
  	
  	
  </body>
</html>
