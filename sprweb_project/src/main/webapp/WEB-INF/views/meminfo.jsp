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
<script src="resources/js/script.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=David+Libre|Hind:400,700"
	rel="stylesheet">

<link rel="stylesheet" href="resources/css/reset.css">
<!-- CSS reset -->
<link rel="stylesheet" href="resources/css/style.css">
<!-- Resource style -->

<title>Auto-Hiding Navigation - Simple | CodyHouse</title>
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
<div class = "row">
<br>
	<c:choose>
			<c:when test="${count>0}">
			<table class="table">
			<tr>
			<th>주문번호</th><th>주문명</th><th>주문일</th><th>총 가격</th>
			</tr>
		<c:forEach var="o" items="${list}">
			<tr>
				<td>
					${o.order_no}</td><td><a href="javascript:myOrderDetail(${o.order_no});">${o.order_name} 
					<c:choose>
						<c:when test="${o.order_count == 1}">
							
						</c:when>
						<c:otherwise>
						 외	${o.order_count-1}   종
        				</c:otherwise>
					</c:choose>
					</a>
				</td>
				<td>${o.order_sdate}</td><td>${o.order_price }원</td>
				<c:set var="t" value="${t = t + o.order_price}"></c:set>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">총 주문 건수 : ${count} 건</td>
			<td colspan="2">총 주문 금액 : ${t} 원</td>
		</tr>
		</table>				
			</c:when>
			<c:otherwise>
					주문한 상품이 없습니다.
        	</c:otherwise>
		</c:choose>
		<br><br><br>
	<div id="myOrderDetaildiv"></div>
	<%--side영역 --%>
	<div class = "right">
	</div>
</div>
<%--footer영역 --%>
	<p><a href="aa">재홍-대희-지훈-유진-준혜팀</a></p>
</main>
</body>
</html>