<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>

  </head>
  
  <body>
  <table id="albumTDG"></table>
  	<script type="text/javascript">
  		$(function(){
  			$('#albumTDG').treegrid({
  			url:'${pageContext.request.contextPath}/album/findall.do',    
    		idField:'id',    
   		 	treeField:'title',
   		 	animate:true,//定义节点在展开或折叠的时候是否显示动画效果。
   		 	checkbox:false,//定义是否在每一个借点之前都显示复选框。
   		 	cascadeCheck:false,//定义层叠选中状态
   		 	lines:true,//定义是否显示树控件上的虚线。
   		 	state:'closed',//节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点
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
			onDblClickRow:function (row) {
   			 $('#music').dialog('open')
 			$('#audio').prop('src','${pageContext.request.contextPath}'+row.url);
				},    
    	columns:[[  
        {title:'名字',field:'title',width:180},     
        {field:'url',title:'下载路径',width:60,align:'right'},    
        {field:'size',title:'文件大小',width:80},    
        {field:'length',title:'文件时长',width:80}    
   			 ]],
   			 toolbar:[
   			 //专辑详情
   			 {
   			 text:'专辑详情',
		    iconCls:'icon-book_open',
   			 handler:function(){
   			var selRoot= $('#albumTDG').treegrid('getSelected');  			
   			if(selRoot==null){
   			 $.messager.alert('提示','您还没有选择','warning');
   			}else{
   			var level= $('#albumTDG').treegrid('getLevel',selRoot.id);
   			if(level==1){
   					$('#showAlbumFom').form('load',{
							 title:selRoot.title,
							  score:selRoot.score, // 字段的key 对应表单元素的name属性值，代表给对应表单元素填充的值
							   brodecast:selRoot.brodecast,
							   author:selRoot.author,
							   description:selRoot.description,
							   publicTime:selRoot.publicTime,
							   count:selRoot.count,
							   coverImg:selRoot.coverImg,
						   });
						   	$('#showAlbum').dialog({
							   title:'专辑详情',
							   width:'450',
							   height:'450',
							   iconCls:'icon-book_open',
							   onOpen:function(){
							   $("#coverImg").attr("src",'${pageConetext.request.contextPath}/cmfz/albumImg/'+selRoot.coverImg);						   
							   if(selRoot.status==1){
							   $("#status").val('上线');
							   }else{
							   $("#status").val('下线');
							   }
							   },
							   buttons:[
							   	{
							   		text:'关闭专辑详情',
							   		iconCls:'icon-cross',
							   		handler:function(){	
							   		$('#showAlbum').dialog('close');		   			
							   		}
							   		}         
							   ],
						   });
						   
						   
   			}else{
   			$.messager.alert('提示','请您选择一个专辑','warning');
   			}
   			}   			
   			 }
   			 },
   			 //添加专辑
   			     					{
   			 		 				text:'添加专辑',
		   							 iconCls:'icon-cd_add',
   					            	  handler:function(){
		            		  //初始化diglog
		            		  $('#addAlbum').dialog({
		            		  	 title:'添加专辑',
		            		  	 iconCls:'icon-cd_add',
		            		  	 width:450,
		            		  	 height:450,		        		  	 
		            		  });
		            		 //初始化form
		            		  $('#addAlbumFom').form({
		            		  	url:'${pageContext.request.contextPath}/album/add.do',
		            		  	success:function(data){
		            		  		// 关闭dialog
							   			if(data=='ok'){
							   		  $.messager.alert('提示','添加成功','info');
							   		$('#addAlbum').dialog('close');
							   				}
							   			// 刷新数据表格
							   		$('#albumTDG').treegrid('load');
							   					
		            		  	},
		            		  	error:function(){
		            		  	$.messager.alert('提示','添加失败','info');
		            		  	},
		            		  	onSubmit:function(){
		            		  		return $('#addAlbumFom').form('validate');
		            		  	},
		            		  });		            		  
		            	  }
   			
   			
   			
   			 },
   			 //添加章节   	
   			 {
   			 	 text:'添加章节',
		   		 iconCls:'icon-cd_add',
	             handler:function(){
				var selchap= $('#albumTDG').treegrid('getSelected');
       		  if(selchap==null){
       		   $.messager.alert('提示','您还没有选择','warning');
       		  }else{
       		  	var level= $('#albumTDG').treegrid('getLevel',selchap.id);
       		  if(level==1){  
       		      		  		       		  
       		  //初始化diglog       		  
       		  $('#addChapter').dialog({
       		  	 title:'添加章节',
       		  	 iconCls:'icon-cd_add',
       		  	 width:350,
       		  	 height:300,		        		  	 
       		  });
       		   $('#addChapterFom').form('load',{      		  	
				AlbumId:selchap.id,
				albumName:selchap.title,
				});	
       		 //初始化form
       		  $('#addChapterFom').form({
       		  	url:'${pageContext.request.contextPath}/chapter/addchapter.do',
       		  	success:function(data){
       		  		// 关闭dialog
   			if(data=='ok'){
   		  $.messager.alert('提示','添加成功','info');
   		$('#addChapter').dialog('close');
   				}
   			// 刷新数据表格
   		$('#albumTDG').treegrid('load');
   					
       		  	},
       		  	error:function(){
       		  	$.messager.alert('提示','添加失败','info');
       		  	},
       		  	onSubmit:function(){
       		  		return $('#addChapterFom').form('validate');
       		  	},
       		  });       		  
       		  		
       		  }else{
       		  $.messager.alert('提示','请您选择一个要添加的专辑','warning');
       		  }       		         		  
       		  }      		  		            		  
       	  }		    		    		    
   			 },
   			 //下载音频
   			 {
   			 	 text:'下载音频',
		    	iconCls:'icon-disk_download',
		    	handler:function(){
   			var seldown= $('#albumTDG').treegrid('getSelected');  			
   			if(seldown==null){
   			 $.messager.alert('提示','您还没有选择','warning');
   			}else{
   			var level= $('#albumTDG').treegrid('getLevel',seldown.id);
   			if(level==2){
			location.href="${pageContext.request.contextPath}/album/download.do?filename="+seldown.title;					   
						   
   			}else{
   			$.messager.alert('提示','请您选择一个章节','warning');
   			}
   			}   			
   			 }
		    
   			 },
   			 //在线播放
   			 {
   			 
   			 }
   			 ],   
  				
  			
  			
  			});
  			$('#submitAlbum').click(function(){
	  			$('#addAlbumFom').submit();
  			});
  			  	$('#submitChapter').click(function(){
	  			$('#addChapterFom').submit();
  			});
  		});	  		

  	</script>
  	<!--  专辑详情展示   -->
  		 <div style="display:none;padding:30px;" id="showAlbum">
       		<form id="showAlbumFom"  method="POST" >
       			<table>
       				<tr>
       					<td>
			       			专辑名字：
       					</td>
       					<td>
       						<input type="text" name="title"  readonly>
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			专辑状态：
       					</td>
       					<td>
       						<input id="status" type="text" name="status"  readonly>	
       					</td>
       				</tr>
       				       				<tr>
       					<td>
			       			专辑播音：
       					</td>
       					<td>
       						<input type="text" name="brodecast"  readonly>	
       					</td>
       				</tr>
       				       				<tr>
       					<td>
			       			专辑作者：
       					</td>
       					<td>
       						<input type="text" name="author"  readonly>	
       					</td>
       				</tr>
       				  <tr>
       					<td>
			       			发布时间：
       					</td>
       					<td>
       						<input type="text" name="publicTime"  readonly>	
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			专辑集数：
       					</td>
       					<td>
       						<input type="text" name="count"  readonly>	
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			专辑评分：
       					</td>
       					<td>
       						<input type="text" name="score" readonly>
       					</td>
       				</tr>
       				<tr>
       				<tr>
       					<td>
			       			专辑描述：
       					</td>
       					<td>
       						<textarea name="description" readonly>
       							
       						</textarea>
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			专辑图片：
       					</td>
       					<td>
       						<div id="Imgdiv" style="width:50px;height:50px;border:solid 1px gray;">
	       							<img src="" style="width:50px;height:50px;" id="coverImg" name="coverImg">
       						</div>
	       					
       					</td>
       				</tr>      			      				       				      			
       			</table>
       		</form>
       </div>	
       
       <!-- 添加专辑 -->
       <div style="display:none;padding:30px;" id="addAlbum">
       		<form id="addAlbumFom"  method="POST" enctype="multipart/form-data">
       			<table>
       				<tr>
       					<td>
			       			专辑名字：
       					</td>
       					<td>
       						<input type="text" name="title"  class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			专辑状态：
       					</td>
       					<td>
       						<input type="radio" name="status" value="1" class="easyui-validatebox" data-options="required:true">上线
     &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="0" class="easyui-validatebox" data-options="required:true">下线	
       					</td>
       				</tr>
       				       				<tr>
       					<td>
			       			专辑播音：
       					</td>
       					<td>
       						<input type="text" name="brodecast"  class="easyui-validatebox" data-options="required:true">	
       					</td>
       				</tr>
       				       				<tr>
       					<td>
			       			专辑作者：
       					</td>
       					<td>
       						<input type="text" name="author"  class="easyui-validatebox" data-options="required:true">	
       					</td>
       				</tr>      				  
       				<tr>
       					<td>
			       			专辑集数：
       					</td>
       					<td>
       						<input type="text" name="count"  class="easyui-validatebox" data-options="required:true">	
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			专辑评分：
       					</td>
       					<td>
       						<input type="text" name="score" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr>
       				<tr>
       				<tr>
       					<td>
			       			专辑描述：
       					</td>
       					<td>
       						<textarea name="description" class="easyui-validatebox" data-options="required:true" style="height:50px;width:200px">
       							
       						</textarea>
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			专辑图片：
       					</td>
       					<td>
       					<div>
       						<input type="file" class="easyui-validatebox" id="upfile" name="upfile" data-options="required:true,accept:'image/jpg'">
       					</div>
	       					
       					</td>
       				</tr>  
       				<tr align="center">
       					<td colspan="2">
       						<a href="javascript:void(0)" class="easyui-linkbutton" 
       							data-options="iconCls:'icon-ok',text:'提交'" id="submitAlbum"></a>
       							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       						
       					</td>
       				</tr>    			      				       				      			
       			</table>
       		</form>
       </div>
       
        <!-- 添加章节 -->
       <div style="display:none;padding:40px;" id="addChapter">
       		<form id="addChapterFom"  method="POST" enctype="multipart/form-data">
       			<table>
       				<tr>
       					<td>
			       			专辑名字：
       					</td>
       					<td>
       						<input type="text" name="albumName"  class="easyui-validatebox" data-options="required:true" readonly>
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			上传：
       					</td>
       					<td>
       						<input type="file" class="easyui-validatebox" id="upfile" name="upfile" data-options="required:true">	
       					</td>
       				</tr> 
       				 <tr>
       					<td>
       						<input type="hidden" name="AlbumId"  class="easyui-validatebox" data-options="required:true">	
       					</td>
       				</tr>        				  
       				<tr align="center">
       					<td colspan="2">
       						<a href="javascript:void(0)" class="easyui-linkbutton" 
       							data-options="iconCls:'icon-ok',text:'提交'" id="submitChapter"></a>
       							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      						
       					</td>
       				</tr>    			      				       				      			
       			</table>
       		</form>
       </div>	
       <!--  在线播放  -->
             <div id="music" class="easyui-dialog" title="My Dialog" style="width:400px;height:300px;text-align: center"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
                buttons:[{
				text:'关闭',
				handler:function(){
				$('#music').dialog('close');
				}
			}]">
    <audio id="audio" src=""  controls="controls"></audio>
</div> 	
  </body>
</html>
