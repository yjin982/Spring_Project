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

<%-- 다음 주소 api 사용 --%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

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
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
		<%-- 개인정보 클릭시 처음에 나오는 비밀번호 확인 div --%>
		<div id="myinfoupdaterow">
		<div class="form-group">
			<form id="myinfoupdate" autocomplete="off">
				<input type="hidden" id="myinfoupdate_id" value="${login_id}">
				<label for="password">비밀번호 입력</label>
				<input type="password" id="myinfoupdate_passwd" class="form-control"><input type="button" value="확인" id="myinfoupdatecheck"><br>
			</form>
		</div>
		</div>
		
		<%-- 개인정보 수정 시 비밀번호 확인 통과 후 보여주는 div --%>
		<%-- 개인정보 수정 영역 --%>
		<div id="myinfoupdatediv" style="display: none;">
		<form method="POST" name="update" id="update" action="index.jsp" autocomplete="off">
		<div style="height: 60px">
			<label>아이디</label><br><input type="text" name="my_id" id="my_id" readonly="readonly">
		</div>
		<div style="height: 120px">
			<label>변경할 비밀번호</label><br><input type="password" name="new_passwd" id="new_passwd" onkeydown="checkSpacebar();"><br>
			<label>변경할 비밀번호 확인</label><br><input type="password" name="new_passwd2" id="new_passwd2" onkeydown="checkSpacebar();"><br>
		</div>
		<br>
		<div style="height: 60px">	
			<label>성명</label><br><input type="text" name="new_name" id="new_name" onkeydown="checkSpacebar();"><br>
		</div>
		<div>	
			<label>주소</label>&nbsp;&nbsp;
			<input type="button" onclick="sample6_execDaumPostcode2()" value="우편번호 찾기"><br>
			<br>
			<input type="text" name= "new_address" id="new_address" readonly="readonly" style="width: 500px"><br><br>
			<input type="text" name="new_address_detail" id="new_address_detail" style="width: 300px" placeholder="상세주소를 적어주세요" onkeydown="checkSpacebar();"><br>
		</div>
		<div style="height: 60px">	
			<label>가입일</label><br><input type="text" name="my_sdate" id="my_sdate" readonly="readonly"><br>
		</div>
		
		<%-- 업데이트 유효성 검사 후 문제 있을 때 표시--%>
		<div id="updateCheckdiv" style="color: red;"></div>
		
		<div>
		<input type="button" id="myinfoupdatebtn" value="수정">
		<input type="button" id="deleteMemberbtn" value="회원탈퇴">
		<input type="hidden" value="0" id="check">
		</div>
		</form>
		<br>
		</div>
		</div>
		<div class="col-lg-3"></div>
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