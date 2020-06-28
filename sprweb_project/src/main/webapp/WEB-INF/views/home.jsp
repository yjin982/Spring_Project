<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/msc.css"> 

<%-- jquery ui 모달 사용 --%>	
<link rel="stylesheet" href="resources/jquery-ui-1.12.1/jquery-ui.css"> 
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="resources/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="resources/js/script.js"></script>

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<%-- 다음 주소 api 사용 --%>
<script src="https://t1.daumcdn.net/ma	pjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/8c4c6b0e8c.js"></script>
</head>
<body>
<%--header영역--%>
<div class = "header">
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
<%-- 로그인 폼 --%>
	<div id="loginDialog" title="Basic dialog" style="display: none;">
		<div>
		<form method="post" action="login" id="login" autocomplete="off">
			<input type="text" placeholder="ID" name="login_id" id="login_id" class="text ui-widget-content ui-corner-all" onkeydown="checkSpacebar();">
			<input type="password" placeholder="PASSWORD" name="login_passwd" id="login_passwd" class="text ui-widget-content ui-corner-all" onkeydown="checkSpacebar();">
			<input type="button" id="loginOk" name="loginOk" value="로그인" style="width: 100%; height: 40px">
		<br>

		<%-- 아이디 비밀번호 불일치시 표시--%>
		<div id="loginResultdiv" style="color: red;"></div>
	
		</form>
		</div>
		
	</div>
	
	<%-- 회원가입 폼 --%>
	<div id="joinDialog" title="Basic dialog" style="display: none;">
		<form method="post" action="join" id="join" autocomplete="off">
		<div style="height: 60px">
			<label>아이디</label><br><input type="text" name="join_id" id="join_id" maxlength="8" onkeydown="checkSpacebar();"> &nbsp;&nbsp;<input type="button" id="join_idCheckbtn" value="중복확인">&nbsp;&nbsp;<label>최대 8자리까지 가능합니다.</label><br>
		</div>
		<div style="height: 120px">
			<label>비밀번호</label><br><input type="password" name="join_passwd" id="join_passwd" onkeydown="checkSpacebar();"><br>
			<label>비밀번호 확인</label><br><input type="password" name="join_passwd2" id="join_passwd2" onkeydown="checkSpacebar();">
		</div>
		
		<div style="height: 60px">	
			<label>성명</label><br><input type="text" name="join_name" id="join_name" onkeydown="checkSpacebar();"><br>
		</div>
		<div>	
			<label>주소</label>&nbsp;&nbsp;
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			<br>
			<input type="text" id="join_address" readonly="readonly" style="width: 500px"><br><br>
			<input type="text" name="join_address_detail" id="join_address_detail" style="width: 300px" maxlength="50" placeholder="상세주소를 적어주세요" onkeydown="checkSpacebar();"><br>
		</div>
		<input type="hidden" value="0" id="check">
		<br>
		
		<%-- 회원가입 유효성 검사 후 문제 있을 때 표시--%>
		<div id="joinCheckdiv"  style="color: red;"></div>
	
		</form>
	</div>
<%--navigation영역--%>
<div class="btn-group btn-group-justified bg-primary">
	<a class="btn btn-lg btn-warning" role="button" href="category1?check=국" >국</a>
	<a class="btn btn-lg btn-warning" role="button" href="category2?check=반찬">반찬</a>
	<a class="btn btn-lg btn-warning" role="button" href="category3?check=메인">메인요리</a>
	<a class="btn btn-lg btn-warning" role="button" href="category4?check=육류">육류</a>
	<a class="btn btn-lg btn-warning" role="button" href="category5?check=샐러드">샐러드</a>
	<a class="btn btn-lg btn-warning" role="button" href="category6?check=김치">김치</a>
</div>

<br>
<div class="container">
<div class="row">
	<div class="col-lg-2"></div>
	<div class="col-lg-8">
		<form name="frm" class="navbar-form" method="post" action="searchfrm">
		<div class="form-group">
			<input type="text" name="search" class="form-control" size="90" placeholder="검색하기" />
			<input type="submit" class="btn btn-lg-default" value="검색" onclick="search_frm()"/>
		</div>
		</form>
	</div>
	<div class="col-lg-2"></div>
</div>
<%--main영역 --%>
<br><br>
<div class="row">
	<div class="col-lg-9"></div>
	<div class="col-lg-1"><a href="sortReg?check=${cate}&sort=reg"><img src="resources/images/reg.png" style="width: 50px;"></a></div>
	<div class="col-lg-1" ><a href="sortLow?check=${cate}&sort=low"><img src="resources/images/low.png" style="width: 70px"></a></div>
	<div class="col-lg-1" ><a href="sortHigh?check=${cate}&sort=high"><img src="resources/images/high.png" style="width: 70px"></a></div>
</div>

<div class = "row">
	<c:forEach var="i" items="${item}">
	<c:set var="col" value="3"/>
	<div class="col-lg-${col}" style="text-align: center;">
		<h2>${i.menu_name}</h2>
		<div class="imgs" style="height:200px;">
			<img src="resources/images/${i.menu_image}" >
		</div>
		<br><br>
		<p><jfmt:formatNumber value="${i.menu_price}" pattern="#,##0" />원</p>
		<button class="minus"><i class="fas fa-minus fa-2x"></i></button>
		<input type="number" min="1" value="1" class="i_qty" maxlength="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'1');" style="text-align: center; width: 50px;">
		<button class="plus"><i class="fas fa-plus fa-2x"></i></button>
		<br><br>
		<input type="hidden" class="menu_no" value="${i.menu_no}">
		<input type="button" class="btnAddCart btn btn-default btn-lg" value="담기">
		<a href="#" class="btn btn-default btn-lg" onclick="link(${i.menu_no})">리뷰</a><br><br><br>
	</div>
	</c:forEach>
</div>

<!-- 리뷰 모달 	-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog modal-lg" role="document" style="width: auto; display: table">
				<div class="modal-content">
					<div class="modal-body">
					<div class="reviewshowData"></div>
					<button onclick=revStart() id="insRev">추가</button>					
					<button onclick=totRev() id="totRev" >전체목록</button>
					<div class="reviesResultdiv"></div>					
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="modal.jsp" />
<br><br>

<!-- --------------카트 부분---------------- -->
<div class="row">
	<div class="col-1"></div>
	<div class="col-10">
			<h3>결제하기</h3>
			<div class="cartlist">
			<c:set var="cnt" value="0" />
			<table class="table"><!-- 카드 담은 상품 목록들 -->
			<tr><th>제품명</th><th>수량</th><th>가격</th><th></th></tr>
			<c:forEach var="c" items="${carts}">
				<tr>
					<td class="cartitem" data-value="${c.menu_no}">${c.menu_name}</td>
					<td class="cartitemq" data-value="${c.menu_quantity}">${c.menu_quantity}</td>
					<td>${c.menu_price}</td>
					<td class="btnDel"><button><i class="far fa-times-circle fa-2x" ></i></button></td>
					<c:set var="cnt" value="${cnt + (c.menu_price*c.menu_quantity)}" />
				</tr>
			</c:forEach>
			<tr><td colspan="4" style="text-align: right;">총 결제 금액 : <jfmt:formatNumber value="${cnt}" pattern="#,##0" />원</td></tr>
			</table>
			</div>
			<div style="text-align: right;">
			<form id="bfrm" name="bfrm" method="post">
				<input type="hidden" value="${login_id }" name="id"> <!-- id를 가지고 가서 결제진행 -->
				<input type="submit" class="btnBuy btn btn-success" value="결제하기">
			</form>
			</div>
		</div>
		<div class="col-1"></div>
	</div>
</div>

<button class="top"><i class="fas fa-angle-double-up fa-4x"></i></button>

<br><br>
<%--footer영역 --%>
<div class="row">
	<div class="col-12">
		<img src="resources/images/footer2.png" class="ad" style="max-width: 100%;">
	</div>
</div>



</body>
</html>