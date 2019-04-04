<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

</head>

<body >
<div align="center">
<h1>系统主菜单</h1><hr/>
<c:if test="${sessionScope.user.status=='s'}">
<ul>
    <li><a href="../student/index.jsp">学生管理</a></li><ul>
</c:if>
<c:if test="${sessionScope.user.status=='m'}">
<ul>
    <li><a href="../student/index.jsp">学生管理</a></li>
    <li><a href="../group/index.jsp">小组管理</a></li>
    <li><a href="../clazz/index.jsp">班级管理</a></li>
    <li><a href="../tag/index.jsp">标签管理</a></li>
    <li><a href="../city/index.jsp">就业城市管理</a></li>
    <li><a href="../city/index.jsp">系统用户管理</a></li>
</ul>
    </c:if>
</div>
</body>
</html>

