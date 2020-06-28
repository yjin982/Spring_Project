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
			<th>리뷰번호</th><th>상품명</th><th>댓글 내용</th><th>작성일</th><th></th>
			</tr>
		<c:forEach var="r" items="${list}">
			<tr>
				<td>${r.review_no}</td>
				<td>${r.menu_name}</td>
				<td>${r.review_comment}</td>
				<td>${r.review_sdate}</td>
				<td><a href="javascript:myreviewdelete(${r.review_no});">삭제하기</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">총 댓글 건수 : ${count} 건</td>
		</tr>
		</table>				
			</c:when>
			<c:otherwise>
					작성한 댓글이 없습니다.
        	</c:otherwise>
		</c:choose>
	</div>
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