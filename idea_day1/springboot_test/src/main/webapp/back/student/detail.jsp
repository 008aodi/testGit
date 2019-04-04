<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <style type="text/css">
        body{
            /* text-align: center; */
        }
        select{
            width:173px;
        }
    </style>

</head>

<body >

<h1>学生详细信息展示</h1><hr/>




<ul>
    <li>学生姓名:${requestScope.student.name}</li>
    <li>学生年龄:${requestScope.student.age}</li>
    <li>学生班级:${requestScope.student.clazz.name}</li>
    <li>学生小组:${requestScope.groups.name}</li>
    <li>学生就业城市:${requestScope.student.city.name}</li>

    <li>学生标签:<c:forEach items="${requestScope.student.tags}" var="tag" varStatus="status">
            <c:if test="${status.last}">${tag.name}</c:if>
        <c:if test="${!status.last}">${tag.name},</c:if>
    </c:forEach>
    </li>
    <li>备注:<font color="red">${requestScope.student.comment}</font></li>
</ul><hr/>





</body>
</html>

