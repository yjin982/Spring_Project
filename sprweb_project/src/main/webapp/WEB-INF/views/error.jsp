<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=David+Libre|Hind:400,700"
	rel="stylesheet">

<link rel="stylesheet" href="resources/css/reset.css">
<!-- CSS reset -->
<link rel="stylesheet" href="resources/css/style.css">
<!-- Resource style -->

<title>에러창</title>
</head>
<body>
	<header class="cd-auto-hide-header">
		<div class="logo">
			<a href="admin.jsp"><img src="resources/images/logo.PNG"
				alt="Logo" width="180"></a>
		</div>

		<nav class="cd-primary-nav">
			<a href="#cd-navigation" class="nav-trigger"> <span> <em
					aria-hidden="true"></em> Menu
			</span>
			</a>
			<!-- .nav-trigger -->

			<ul id="cd-navigation">
				<li><a href="admin_memberlist">회원관리</a></li>
				<li><a href="admin_menulist">메뉴관리</a></li>
				<li><a href="admin_reviewlist">리뷰관리</a></li>
				<%
					if (session.getAttribute("passwd") != null)
						out.print("<li><a href='admin_logout'>로그아웃</a></li>");
				%>
			</ul>
		</nav>
		<!-- .cd-primary-nav -->
	</header>
	<!-- .cd-auto-hide-header -->

	<%--main영역 --%>
	<main class="cd-main-content">
	<div class="row">
	<br>
		<h2>자료를 입력해주세요.</h2>
		<a href="admin_menulist">메뉴관리로 돌아가기</a>
		<%--side영역 --%>
		<div class="right"></div>
	</div>
	<%--footer영역 --%>
		<p>재홍-대희-지훈-유진-준혜팀</p>
	</main>
</body>
</html>