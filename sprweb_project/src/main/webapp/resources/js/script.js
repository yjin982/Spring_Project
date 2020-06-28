var loginCheck = "false";
$(document).ready(function() {
	
	$(".ad").dblclick(function() {//footer더블클릭시 어드민 페이지로 이동
		var url = "admin.jsp";
		$(location).attr('href',url);
	});
	
	$(window).scroll( function() { //페이지 상단으로 이동하는 버튼 
		if ($( this ).scrollTop() > 100) {
			$('.top').fadeIn();
		} else {
			$('.top').fadeOut();
		}
	});
	
	$('.top').click( function() {//페이지 상단으로 이동
		$('html, body').animate( { scrollTop : 0 }, 400 );
		return false;
	});
	
	$.ajax({
		type : "post",
		url : "loginCheck",
		dataType:"json",
		success : function(a) {
			if(a.loginCheckResult == "false"){
				loginCheck = "false";
			}
			else{
				loginCheck = "success";
			}
		}, error : function() {
		}
		
	});
	
	$(".btnAddCart").click(function(){ // 카트에 담기버튼
		if(loginCheck == "false"){
			alert("로그인 하세요");
		}else{
			var menuNum = $(this).siblings(".menu_no").val(); //메뉴번호
			var qty = $(this).siblings(".i_qty").val(); //수량
			
			$(".cartlist").empty();
			$.ajax({
				type : "get",
				url : "cart?menu_num=" + menuNum + "&qty=" + qty,
				dataType:"json",
				success : (data) => {				
						var list = data.list;
						var count = 0;
								
						var str = "<table class='table'>";
						str += "<tr><th>제품명</th><th>수량</th><th>가격</th><th></th></tr>";
						
						$(list).each((index,obj) => {
							str += "<tr>";
							str += "<td class='cartitem' data-value='" + obj.menu_no + "'>" + obj.menu_name + "</td>";
							str += "<td class='cartitemq' data-value='" + obj.menu_quantity + "'>" + obj.menu_quantity + "</td>";
							str += "<td>" + obj.menu_price + "</td>";
							str += "<td class='btnDel'><button><i class='far fa-times-circle fa-2x' ></i></button></td>";
							str += "</tr>";
							count = count + (obj.menu_price*obj.menu_quantity);
						});
						str += "<tr><td colspan='4' style='text-align: right;'>총 결제 금액 : " + comma(count)  + "원</td></tr>";
						str += "</table>";
								
						$(".cartlist").append(str);
							
				},error : () => {
					$(".cartlist").text("error!!!");
				}
			});
		}
	});
	
	$(".minus").click(function(){// 수량 감소
		var count = $(this).next(".i_qty").val();
		if(count == 1){
			$(this).next(".i_qty").val(1);
		}else{
			count -= 1;
			$(this).next(".i_qty").val(count);
		}
	});
	$(".plus").click(function(){ // 수량 증가
		var count = $(this).prev(".i_qty").val();
		count++;
		$(this).prev(".i_qty").val(count);
	});
	
	
	$(".btnBuy").click(function(){ //결제하기
		if(loginCheck == "false"){
			alert("로그인하세요");
			location.href="aa";
		}else{
			var re = confirm("결제하시겠습니까?");
			if(re){
				$('#bfrm').attr('action','proceedBuy').submit();
				alert("주문완료됨");
			}else{
				alert("주문취소됨");
				location.href="aa";
			}			
		}
	});
	
	//회원탈퇴 버튼
	$("#deleteMemberbtn").click(function() {
		var result = confirm("정말 탈퇴하시겠습니까?");
		if(result){
			location.href="deleteMember";
			alert("회원탈퇴 되었습니다.");
		}
			
	});
	
	// 개인정보 수정
	$("#myinfoupdatebtn").click(function() {	
		var result = confirm("정말 수정하시겠습니까?");
		if(result){
			update_check();
		}else{}	
	});
	
	// 개인정보 페이지에서 처음에 나오는 비밀번호 확인 통과 후 해당 로그인 아이디 데이터 출력
	$("#myinfoupdatecheck").click(function() {		
		var myinfoupdate_id = $("#myinfoupdate_id").val();
		var myinfoupdate_passwd = $("#myinfoupdate_passwd").val();
		$.ajax({
			type : "post",
			url : "myinfoupdate",
			data : {
				"myinfoupdate_id" : myinfoupdate_id,
				"myinfoupdate_passwd" : myinfoupdate_passwd
			},
			dataType : "json",
			success : function(a) {
				if (a.myinfoupdateResult == "success") {
					$("#myinfoupdate").hide();
					$("#myinfoupdatediv").show();
					$("#my_id").val(a.myinfoupdate_id);
					$("#new_name").val(a.myinfoupdate_name);
					$("#new_address").val(a.myinfoupdate_address);
					$("#new_address_detail").val(a.myinfoupdate_address_detail);
					$("#my_sdate").val(a.myinfoupdate_sdate);
				} else {
					alert("비밀번호가 틀렸습니다..");
					 $("#myinfoupdate_passwd").focus();
				}
			},
			error : function() {
			}
		});
	});
	
	// 로그인화면에서 로그인 버튼 클릭
	$("#loginOk").click(function() {
			login();
	});
	
	// 회원가입 아이디 중복체크 수행 후 아이디 변경감지
	$("#join_id").on("propertychange change keyup paste input", function() {
		$("#check").val("0");
	});
	
	// 회원가입 아이디 중복체크
	$("#join_idCheckbtn").click(function() {		
		if($("#join_id").val() == ""){
			alert("아이디를 입력하세요.");
			$("#join_id").focus();
		}else {
			var join_id = $("#join_id").val();
			$.ajax({
				type : "post",
				url : "joinIdCheck",
				data : {
					"join_id" : join_id
				},
				dataType : "json",
				success : function(a) {
					if (a.joinIdCheckResult == "false") {
						alert("이미 가입된 아이디 입니다.");
						$("#join_id").focus();
						$("#check").val("0");
					} else {
						alert("가능한 아이디입니다.");
						$("#joinCheckdiv").html("");
						$("#join_passwd").focus();
						$("#check").val("1");
						
					}
				},
				error : function() {
				}
			});
		}
	});
});

////////////////////////////////////////////////
//리뷰 목록 모달 호출		
function link(no){ 
	$("#myModal").modal('show');
	$(".reviewshowData").empty();
	$.ajax({
		type:"get",
		url:"review?menu_no=" + no,
		dataType:"json",
		success:function(reviewData){
			var rnum = 1;
			var rList = reviewData.datas;			
			var str = "<div class='container'><div class='table-responsive'>"
		    str += "<table border='1' class='table'>";
		    str += "<h4>리뷰 목록</h4>";
			str += "<tr><th>번호</th><th>리뷰</th><th>작성자</th><th>등록일</th><th>수정</th></tr>"
		 	
				$(rList).each(function(index, obj){
					str += "<tr><td>" + rnum + "</td>";
					str += "<td>" + obj["review_comment"] + "</td>";
					str += "<td>" + obj["review_id"] + "</td>";
					str += "<td>" + obj["review_sdate"] + "</td>";
					str += "<td>" + "<button onclick=upStart(" + obj["review_no"] + ") id='upRe' value='"+ obj["review_no"] +"'>수정</button></td>";
					str += "</tr>";					
					rnum++;					
				});	
			
			str += "</table></div></div>";
			str += "<input type='hidden' id='review_mnum' value='"+ no + "'>";
			$(".reviewshowData").html(str);
		},error:function(){
			$(".reviewshowData").text("에러!!!");
		}
	});	
}

//리뷰 추가 창 호출
function revStart(){
	$("#modal-title2").text("리뷰 추가 ");
	var menu_no = $("#review_mnum").val();
	$.ajax({
		type : "get",
		url : "rev_frm?menu_no="+menu_no,
		dataType : "json",
		success : function(a) {
			$("#myModal").modal('hide');
			$("#myModal2").modal('show');
			$("#review_menu_num").val(a.menu_no);
			$("#review_menu_name").val(a.menu_name);
			$("#review_menu_category").val(a.menu_category);
		},
		error : function() {
			alert("에러");
		}
	});
	
}

//리뷰 추가 
function revIns(){
	var review_comment = $("#review_menu_comment").val();
	var menu_name = $("#review_menu_name").val();
	var menu_category = $("#review_menu_category").val();
	var review_menu_num = $("#review_menu_num").val();
	var review_id = $("#review_id").val();

	if( $("#review_id").val() != "" && $("#review_menu_comment").val() != ""){
		$.ajax({
			type : "post",
			url : "rev_frm",
			data : {"review_menu_comment":review_comment, "review_menu_name":menu_name, "review_menu_category":menu_category,"review_menu_num":review_menu_num,"review_id":review_id },
			dataType : "json",
			success : function(a) {
				if (a.reviewResult == "success") {
					$("#myModal2").modal('hide');
					link(review_menu_num);
				} else {
					alert("추가 실패!!");
				}
			},
			error : function() {
				alert("에러");
			}
		});
	}else if($("#review_id").val() == "" ){
		alert('로그인 해주세요');
		location.href="aa"
	}else if($("#review_menu_comment").val() == ""){
		alert("내용을 입력해주세요");
		
	}
}

//리뷰 수정 창띄우기
function upStart(upmun){
	if($("#review_id").val() != ""){
	$("#modal-title3").text("리뷰 수정 ");
		var menu_no = upmun;
	$.ajax({
		type : "get",
		url : "up_frm?review_no="+upmun,
		dataType : "json",
		success : function(a) {
			$("#myModal").modal('hide');
			$("#myModal3").modal('show');
			$("#review_no3").val(upmun);
			$("#review_menu_num3").val(a.menu_no);
			$("#review_menu_name3").val(a.menu_name);
			$("#review_menu_category3").val(a.menu_category);
			$("#review_menu_comment3").val(a.review_comment);
		},
		error : function() {
			alert("수정 에러");
		}
	});
	}else{
		alert('로그인 해주세요');
		location.href="aa"	
	}
}

//리뷰 수정 
function revUp(upmun2){
	var review_comment = $("#review_menu_comment3").val();
	var menu_name = $("#review_menu_name3").val();
	var menu_category = $("#review_menu_category3").val();
	var review_menu_num = $("#review_menu_num3").val();
	var review_id = $("#review_id3").val();
	
		$.ajax({
			type : "post",
			url : "rev_up",
			data : {"review_no3":upmun2, "review_comment3":review_comment },
			dataType : "json",
			success : function(a) {
				if (a.reviewResult == "success") {
					$("#myModal3").modal('hide');
					 link(review_menu_num);
				} else {
					alert("수정 실패!!");
				}
			},
			error : function() {
				alert("에러");
			}
		});
}

//전체 목록 보기
function totRev(){
	$("#myModal").modal('hide');
	$("#myModal4").modal('show');
	$.ajax({
		type:"post",
		url:"reviewTot",
		dataType:"json",
		success:function(reviewTotData){
			var rnum = 1;
			var tList = reviewTotData.datas;			
			var str = "<div class='container'><div class='table-responsive'>"
		    str += "<table class='table'>";
		    str += "<h4>전체 리뷰 목록</h4>";
			str += "<tr><th>번호</th><th>메뉴명</th><th>리뷰</th><th>카테고리</th><th>아이디</th><th>등록일</th></tr>"
		 	
				$(tList).each(function(index, obj){
					str += "<tr><td>" + rnum + "</td>";
					str += "<td>" + obj["menu_name"] + "</td>";
					str += "<td>" + obj["review_comment"] + "</td>";
					str += "<td>" + obj["menu_category"] + "</td>";
					str += "<td>" + obj["review_id"] + "</td>";
					str += "<td>" + obj["review_sdate"] + "</td>";
					str += "</tr>";					
					rnum++;					
				});	
			
			str += "</table></div></div>";
	
			$(".reviewshowData").html(str);
		},error:function(){
			$(".reviewshowData").text("에러!!!");
		}
		
	});
}

//내 리뷰 삭제하기
function myreviewdelete(no) {
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		$.ajax({
			type : "post",
			url : "myreviewdelete",
			data : {	"review_no" : no	},
			dataType : "json",
			success : function(a) {
				if(a.myreviewdeleteResult == "success"){
					alert("리뷰 삭제 되었습니다.");
					location.href = "myreview";
				}
				else{
					alert("삭제 실패");
				}
			},
			error : function() {
			}
		});
	}
}

////////////////////////////////////////////////
// 로그인 다이얼로그
$(function() { 
	$("#loginbtn").click(function() {
		$("#login")[0].reset();
		$("#loginResultdiv").html("");
		$("#loginDialog").dialog({
			title : "LOGIN",
			modal : true,
			width : 330,
			height : 300,
			resizable : false,
			draggable : false,
			buttons : {
				회원가입 : join,
				닫기 : function() {
					$("#loginResultdiv").html("");
					$("#loginDialog").dialog("close");
				}
			}
		});
	});
});

// 로그인 정보 LoginController로 전송
function login() { 
	var login_id = $("#login_id").val();
	var login_passwd = $("#login_passwd").val();
	$.ajax({
		type : "post",
		url : "login",
		data : {
			"login_id" : login_id,
			"login_passwd" : login_passwd
		},
		dataType : "json",
		success : function(a) {
			if (a.loginResult == "success") {
				location.href = "aa"
			} else {
				$("#loginResultdiv").html("아이디 혹은 비밀번호를 확인해주세요");
			}
		},
		error : function() {
			alert("에러");
		}
	});
}

// 개인정보 수정 내용 유효성 검사
function update_check() {
	if ($("#new_name").val() == "") {
		$("#updateCheckdiv").html("이름을 입력하세요.");
		
	} else if ($("#new_address").val() == "") {
		$("#updateCheckdiv").html("상세주소를 입력하세요.");
	} else if ($("#new_passwd").val() != $("#new_passwd2").val()) {
		$("#updateCheckdiv").html("변경할 비밀번호와 변경할 비밀번호 확인이 다릅니다.");
	} 
	else {
		update_ok();
	}
}

// 개인정보 수정 내용 유효성 검사 후 MyinfoController로 전송
function update_ok(){ 
	if($("#new_passwd").val() == ""){
		$("#update").attr("action", "update1");
		$("#update").submit();
	}
	else{
		$("#update").attr("action", "update2");
		$("#update").submit();
	}
}

// 회원가입 다이얼로그
function join() {
	$("#loginResultdiv").html("");
	$("#login")[0].reset();
	$("#loginDialog").dialog("close");

	$("#joinCheckdiv").html("");
	$("#join")[0].reset();

	$("#joinDialog").dialog({
		title : "회원가입",
		modal : true,
		width : 600,
		height : 550,
		resizable : false,
		draggable : false,
		buttons : {
			확인 : join_check,
			취소 : function() {
				$("#joinCheckdiv").html("");
				$("#joinDialog").dialog("close");
				$("#loginDialog").dialog("open");
			}
		}
	});
}

// 회원가입 유효성 검사
function join_check() { 
	if ($("#join_id").val() == "") {
		$("#joinCheckdiv").html("아이디를 입력하세요.");
		$("#join_id").focus();
	} else if ($("#join_passwd").val() == "") {
		$("#joinCheckdiv").html("비밀번호를 입력하세요.");
		$("#join_passwd").focus();
	} else if ($("#join_passwd2").val() == "") {
		$("#joinCheckdiv").html("비밀번호확인을 입력하세요.");
		$("#join_passwd2").focus();
	} else if ($("#join_name").val() == "") {
		$("#joinCheckdiv").html("이름을 입력하세요.");
		$("#join_name").focus();
	} else if ($("#join_address").val() == ""){
		$("#joinCheckdiv").html("우편번호 찾기를 해주세요");
	} else if ($("#join_address_detail").val() == "") {
		$("#joinCheckdiv").html("상세주소를 입력하세요.");
		$("#join_address").focus();
	} else if ($("#join_passwd").val() != $("#join_passwd2").val()) {
		$("#joinCheckdiv").html("비밀번호와 비밀번호 확인이 다릅니다.");
		$("#join_passwd").focus();
	} else if($("#check").val() == "0"){
		$("#joinCheckdiv").html("아이디 중복확인을 해주세요.");
	} 
	else {
		join_ok();
	}
}

// 회원가입 유효성 검사 후 JoinController로 전송
function join_ok() {
	var join_id = $("#join_id").val();
	var join_passwd = $("#join_passwd").val();
	var join_name = $("#join_name").val();
	var join_address = $("#join_address").val();
	var join_address_detail = $("#join_address_detail").val();

	$.ajax({
		type : "post",
		url : "join",
		data : {"join_id":join_id, "join_passwd":join_passwd, "join_name":join_name, "join_address":join_address,"join_address_detail":join_address_detail},
		dataType : "json",
		success : function(a) {
			if (a.joinResult == "success") {
				
				alert("회원가입 성공!!");
				$("#join_id").val("");
				$("#join_passwd").val("");
				$("#join_passwd2").val("");
				$("#join_name").val("");
				$("#join_address").val("");
				$("#joinDialog").dialog("close");
				$("#loginDialog").dialog("open");
			} else {
				alert("회원가입 실패!!");
			}
		},
		error : function() {
			alert("에러");
		}
	});
}

// 입력 폼에 스페이스바 허용하지 않기
function checkSpacebar() { 
	var kcode = event.keyCode;
	if (kcode == 32) {
		event.returnValue = false;
	}
}

// 회원가입 폼 우편번호 찾기
function sample6_execDaumPostcode() { 
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("join_address").value = "(" + data.zonecode + ") " + addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("join_address_detail").focus();
        }
    }).open();
}

// 개인정보 수정할 때 우편번호 찾기
function sample6_execDaumPostcode2() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("new_address").value = "(" + data.zonecode + ") " + addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("new_address_detail").focus();
        }
    }).open();
}


////////////////////////////////////////////////
//주문내역 상세보기
function myOrderDetail(no) {
	$("#myOrderDetaildiv").empty();
	$.ajax({
	type : "post",
	url : "myOrderDetail",
	data : {	"order_no" : no },
		dataType : "json",
		success : function(datas) {
			var str = "<table class='table'>";
			var list = datas.list;
			str +="<tr><th>메뉴번호</th><th>메뉴명</th><th>주문수량</th><th>단가</th><th>카테고리</th></tr>";			
			
			$(list).each(function (index, obj) {
				str += "<tr>";
				str += "<td>" + obj.menu_no  +"</td>";
				str += "<td>" + obj.menu_name +"</td>";
				str += "<td>" + obj.menu_count +"</td>";
				str += "<td>" + obj.menu_price +"</td>";
				str += "<td>" + obj.menu_category +"</td>";
				str += "</tr>";
			});
			str+="</table>";
			$("#myOrderDetaildiv").append(str);
		},
		error : function() {
			alert("오류남");
		}
	});
}


function comma(str) { //가격 , 추가
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

////////////////////////////////////////////////
function cart() {
	var menuNum = $(this).siblings(".menu_no").val(); //메뉴번호
	var qty = $(this).siblings(".i_qty").val(); //수량

	$(".cartlist").empty();
	$.ajax({
			type : "get",
			url : "cart?menu_num=" + menuNum + "&qty=" + qty,
			dataType:"json",
			success : (data) => {
			var list = data.list;
			var count = 0;
					
			var str = "<table class='table'>";
			str += "<tr><th>제품명</th><th>수량</th><th>가격</th><th></th></tr>";
			$(list).each((index,obj) => {
				str += "<tr>";
				str += "<td class='cartitem' data-value='" + obj.menu_no + "'>" + obj.menu_name + "</td>";
				str += "<td class='cartitemq' data-value='" + obj.menu_quantity + "'>" + obj.menu_quantity + "</td>";
				str += "<td>" + obj.menu_price + "</td>";
				str += "<td class='btnDel'><button><i class='far fa-times-circle fa-2x' ></i></button></td>";
				str += "</tr>";
				count = count + (obj.menu_price*obj.menu_quantity);
			});
			str += "<tr><td colspan='4' style='text-align: right;'>총 결제 금액 : " + comma(count)  + "원</td></tr>";
			str += "</table>";
			$(".cartlist").append(str);
		},error : () => {
			$(".cartlist").text("error!!!");
		}
	});
}

$(document).on("click",".btnDel",function(){ // 카트물품 삭제
	console.log($(this).siblings(".cartitem").data("value"));
	var menu_num = $(this).siblings(".cartitem").data("value");
	var qty = $(this).siblings(".cartitemq").data("value");
	
	var result = confirm("카트에서 물품을 삭제하시겠습니까?");
	if(result){
		$(".cartlist").empty();
		$.ajax({
			type:"get",
			url:"delCart?menu_num=" + menu_num,
			dataType: "json",
			success : (data) => {
				var list = data.list;
				var count = 0;
				
				var str = "<table class='table'>";
				str += "<tr><th>제품명</th><th>수량</th><th>가격</th><th></th></tr>";
				$(list).each((index,obj) => {
					str += "<tr>";
					str += "<td class='cartitem' data-value='" + obj.menu_no + "'>" + obj.menu_name + "</td>";
					str += "<td>" + obj.menu_quantity + "</td>";
					str += "<td>" + obj.menu_price + "</td>";
					str += "<td class='btnDel'><button><i class='far fa-times-circle fa-2x' ></i></button></td>";
					str += "</tr>";
					count = count + (obj.menu_price*obj.menu_quantity);
				});
				str += "<tr><td colspan='4' style='text-align: right;'>총 결제 금액 : " + comma(count)  + "원</td></tr>";
				str += "</table>";

				$(".cartlist").append(str);
			},error : () => {
				$(".cartlist").text("error!!!");
			}
		});
	}else{
		console.log("error");	
	}
});