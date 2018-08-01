<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
    
    function checkData(){
        var q=document.getElementById("q").value.trim();
        if(q==null || q==""){
            alert("请输入您要查询的关键字！");
            return false;
        }else{
            return true;
        }
    }
</script>
<div class='row'>
	<div class='col-md-12' style="padding-top: 10px">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/index.html"><strong>首页</strong></a>
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/blogger/aboutMe.html"><strong>关于我</strong></a>
					<%-- <a class="navbar-brand"
						href="${pageContext.request.contextPath}/download.html"><strong>本站源码下载</strong></a>
					<a class="navbar-brand" href="#"><strong>java学习路线图</strong></a> <a
						class="navbar-brand" href="#"><strong>2018学习计划</strong></a> --%>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<form action="${pageContext.request.contextPath}/blog/q.html"
						class="navbar-form navbar-right" method="post" onsubmit="return checkData()">
						<div class="form-group" style="align: right">
							<input type="text" id="q" name="q" value="${q }"class="form-control"
								placeholder="请输入关键字">
						</div>
						<button type="submit" class="btn btn-default">搜索</button>
					</form>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
</div>