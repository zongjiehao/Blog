<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="data_list">
    <div class="data_list_title">
        <img
            src="${pageContext.request.contextPath}/static/images/about_icon.png" />
        关于博主
    </div>
    <div style="padding-top:30px">
        ${blogger.profile }
    </div>
</div>
