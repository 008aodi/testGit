<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <style type="text/css">
        table {
            border-right: 1px solid black;
            border-bottom: 1px solid black;
        }

        table td {
            width:200px;
            text-align:center;
            border-left: 1px solid black;
            border-top: 1px solid black;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $.ajax({
                type:'POST',
                url:'${pageContext.request.contextPath}/student/findall',
                dataType:"JSON",
                success:function(data){
                    for(var i=0;i<data.length;i++){
                        var tr = $("<tr></tr>")
                        var name =$("<td>"+data[i].name+"</td>");
                        var age = $("<td>"+data[i].age+"</td>");
                        var clazzName = $("<td>"+data[i].clazz.name+"</td>");
                        var cityName = $("<td>"+data[i].city.name+"</td>");
                        var tags="";
                        for(var j=0;j<data[i].tags.length;j++){
                           if(j<data[i].tags.length-1){
                               tags = tags+data[i].tags[j].name+",";
                           }
                            if(j==data[i].tags.length-1){
                                tags = tags+data[i].tags[j].name
                            }
                        }
                        console.log(data[i].id);
                        var tagName = $("<td>"+tags+"</td>");
                        var options =$("<td><a href='${pageContext.request.contextPath}/student/detail?id="+data[i].id+"'"+">查看详细</a>|"
                        +"<a href='${pageContext.request.contextPath}/student/delete?id="+data[i].id+"'"+">删除</a>|"
                           +"<a href='${pageContext.request.contextPath}/student/update?id="+data[i].id+"'"+">修改</a></td>");

                        $("#students").append(tr).append(name).append(age).append(clazzName).append(cityName).append(tagName).append(options);
                    }
                }
            });
        });

    </script>

</head>

<body >

<h1>学生管理</h1><hr/>

<div style="margin: 0 auto; width:500px; height:30px" >
    <select>
        <option>姓名</option>
        <option>班级</option>
    </select>
    <input  type="text" placeholder="请输入查询条件" name=""/>
    <input  type="button" value="搜索"/>
</div>
<hr/>
<table border="0" cellspacing="0" cellpadding="0" >
    <thead id="students">
    <td>学生姓名</td>
    <td>学生年龄</td>
    <td>学生班级</td>
    <td>学生就业城市</td>
    <td>学生标签</td>
    <td>操作</td>
    </thead>

</table>

<hr/>
<a href="">导出学生信息</a>
<a href="./add.html">添加学生信息</a>
</body>
</html>
