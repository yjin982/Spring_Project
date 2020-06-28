<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>리뷰 관리</title>
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
	<div class="container">
		<br>
		<div class="table-responsive">
		<table class="table">
		<thead>
			<tr bgcolor="white" align="center">
				<th width="100">리뷰 번호</th>
				<th width="150">리뷰 상품</th>
				<th width="100">작성자</th>
				<th width="500">내용</th>
				<th>리뷰 날짜</th>
				<th>비고</th>
			</tr>
			</thead>
			<c:forEach var="m" items="${list}">
				<tbody>
				<tr>
					<td>${m.review_no}</td>
					<td>${m.menu_name}</td>
					<td>${m.review_id}</td>
					<td>${m.review_comment }</td> 
					<td>${m.review_sdate}</td>
					<td>
					<a href="review_del?review_no=${m.review_no}">삭제</a>
					</td>
				</tr>
				</tbody>
			</c:forEach>
		</table>
		</div>
	</div>
	<%--footer영역 --%>
		<p><a href="aa">재홍-대희-지훈-유진-준혜팀</a></p>
	</main>
</body>
</html>