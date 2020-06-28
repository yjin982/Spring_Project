<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">

</head>
<body>

	<!-- 리뷰 작성 테이블 -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document" style="width: auto; display: table">
		 <div class="modal-content">
			<h4 id="modal-title2" class="modal-title2"></h4>
			 <div class="modal-body">
				<div class="container">
						<table class="table table-bordered">
							<tr>
								<td style="width: 20">메뉴명</td>
								<td><input type="text" name="menu_name" id="review_menu_name" readonly="readonly"></td>
							</tr>
							<tr>
								<td style="width: 20">분류</td>
								<td><input type="text" name="menu_category" id="review_menu_category" readonly="readonly"></td>
							</tr>
							<tr>
								<td>아이디</td>
								<td><input type="text" name="review_id" id="review_id" value="${login_id}" readonly="readonly"></td>
							</tr>
							<tr>
								<td>리뷰 작성</td>
								<td><textarea class="form-control rounded-0"  rows="3" name="review_comment" id="review_menu_comment" ></textarea></td>
							</tr>					
						</table>
							<input type="hidden" name="review_menu_num" id="review_menu_num">
							
						<div class="modal-footer">
						<button id="modalSubmit" onclick="revIns()"  class="btn btn-success">추가</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>					
				</div>
			</div>				
		</div>
	</div>
</div>


<!-- 리뷰 수정 테이블 -->
	<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document" style="width: auto; display: table">
		 <div class="modal-content">
			<h4 id="modal-title3" class="modal-title3"></h4>
			 <div class="modal-body">
				<div class="container">
						<table class="table table-bordered">
							<tr>
								<td style="width: 20">메뉴명</td>
								<td><input type="text" name="menu_name" id="review_menu_name3" readonly="readonly"></td>
							</tr>
							<tr>
								<td style="width: 20">분류</td>
								<td><input type="text" name="menu_category" id="review_menu_category3" readonly="readonly"></td>
							</tr>
							<tr>
								<td>아이디</td>
								<td><input type="text" name="review_id" id="review_id3" value="${login_id}" readonly="readonly"></td>
							</tr>
							<tr>
								<td>리뷰 작성</td>
								<td><textarea class="form-control rounded-0"  rows="3" name="review_comment" id="review_menu_comment3" ></textarea></td>
							</tr>					
						</table>
							<input type="hidden" name="review_menu_num" id="review_menu_num3">
							<input type="hidden" name="review_no" id="review_no3">
							
						<div class="modal-footer">
						<button id="modalSubmit" onclick="revUp($('#review_no3').val())"  class="btn btn-success">수정</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>					
				</div>
			</div>				
		</div>
	</div>
</div>
<!-- 전체 목록 -->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog modal-lg" role="document" style="width: auto; display: table">
				<div class="modal-content">
					<div class="modal-body">
					<div class="reviewshowData"></div>

					<div class="reviesResultdiv"></div>					
				</div>
			</div>
		</div>
		
	</div>
	
</body>
</html>