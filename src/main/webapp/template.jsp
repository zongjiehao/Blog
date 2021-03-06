<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/blog.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<title>${pageTitle}-Powered by haozj</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/bitbug_favicon.ico" />
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="/foreground/common/head.jsp"></jsp:include>
		<jsp:include page="/foreground/common/menu.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
        <jsp:include page="${mainPage }"></jsp:include>
			</div>
			<div class="col-md-3">
			<jsp:include page="/foreground/common/right.jsp"></jsp:include>
			</div>
		</div>
		<jsp:include page="/foreground/common/foot.jsp"></jsp:include>
	</div>
</body>
</html>