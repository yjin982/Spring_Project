<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>상품 수정</title>
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	margin: 0;
	padding-bottom: 40px;
	font-family: "Nanum Gothic", arial, helvetica, sans-serif;
	background-repeat: no-repeat;
	background: linear-gradient(to bottom right, #A5A8A9);
}

.card {
	margin: 0 auto; /* Added */
	float: none; /* Added */
	margin-bottom: 10px; /* Added */
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: content-box;
	padding: 10px;
	font-size: 13px;
	width: 50%;
}
</style>
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
			<div class="col"
				style="width: 25rem; height: 20rem; border-radius: 20px;">
				<div class="card-title" style="margin-top: 30px;">
					<h2 class="card-title text-center" style="font-size: x-large;">
						상품 내용 수정</h2>
					<br>
					<br>
				</div>
				<div class="card-body">
					<form action="menu_update" method="post" style="">
						상품번호 : ${dto.menu_no} <input type="hidden" name="menu_no"
							value="${dto.menu_no}"><br><br>메뉴이름 : <input
							type="text" name="menu_name" value="${dto.menu_name}" class="form-control"><br>
						가격 : <input type="text" name="menu_price"
							value="${dto.menu_price}" class="form-control"><br> 이미지 : <img
							src="resources/images/${dto.menu_image}" width="100px"> <input
							type="file" name="menu_image" size="50"
							value="${dto.menu_image }" class="form-control"> <br> <input
							type="submit" value="수정 ">
					</form>
				</div>
			</div>

			<%--side영역 --%>
			<div class="right"></div>
		</div>
		<%--footer영역 --%>
		<p><br><br><br><br><br><br><br><br><br></p>
	</main>
</body>
</html>