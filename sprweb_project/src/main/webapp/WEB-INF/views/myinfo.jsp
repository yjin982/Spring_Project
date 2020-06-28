<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 로그인 여부에 따라 메인페이지로 이동함--%> 
<c:if test="${login_id == null}">
	<c:redirect url="index.jsp"></c:redirect>
</c:if>

<!DOCTYPE html>
<html>
<head>
<%-- jquery 사용 --%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="resources/js/script.js"></script>

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/8c4c6b0e8c.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--header영역--%>
	<div class="header">
	<%-- 로그인 여부에 따라 로그인, 로그아웃 표시 --%>
		<c:choose>
			<c:when test="${login_id ne null}">
			<a href='logout' style="margin-left: 70%; margin-right: 10px;"><img src="resources/images/logout.png" style="height: 50px;"></a>
			<a href='myinfo'><img src="resources/images/mypage.png" style="height: 50px; padding: 5px;"></a>
			</c:when>
			<c:otherwise>
         	<a href="#" id="loginbtn" style="margin-left: 70%"><img src="resources/images/login.png" style="height: 50px;"></a>
         </c:otherwise>
		</c:choose>
		
		<div class = "row">
			<a href="aa"><img src="resources/images/titlehead.png" style="max-width: 100%; margin-left: 3.5%;"></a>
		</div>
	</div>
	
	<%--navigation영역--%>
	<div class="btn-group btn-group-justified bg-primary">
		<a class="btn btn-lg btn-warning" role="button" href="myinfo">주문내역</a>
		<a class="btn btn-lg btn-warning" role="button" href="myinfoupdate">개인정보수정</a>
		<a class="btn btn-lg btn-warning" role="button" href="myreview">내 리뷰</a>
	</div>
<br><br><br><br><br>
	<%--main영역 --%>
<div class="container">
	<div class="row">
		<div class="col-12">
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
	</div>
	<br><br><br>
	<div id="myOrderDetaildiv"></div>
	</div>
</div>
<br><br><br><br><br>
<%--footer영역 --%>
<div class="row">
	<div class="col-12">
		<img src="resources/images/footer2.png" class="ad" style="max-width: 100%;">
	</div>
</div>
</body>
</html>