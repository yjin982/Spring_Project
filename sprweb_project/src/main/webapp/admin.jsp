<%@page import="com.fasterxml.jackson.core.io.CharTypes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="chartMgr" class="pack.controller.ChartMgr" />
<%
	request.setCharacterEncoding("utf-8"); // 가장 위에 선언해야 한다.
%>
<%
int gook,kimchi,main,banchan,sallad,meat = 0;

int sumgook,sumkimchi,summain,sumbanchan,sumsallad,summeat = 0;


gook = chartMgr.countGook();
kimchi = chartMgr.countKimchi();
main = chartMgr.countMain();
banchan = chartMgr.countBanchan();
sallad = chartMgr.countSallad();
meat = chartMgr.countMeat();

sumgook= chartMgr.sumGook();
sumkimchi = chartMgr.sumKimchi();
summain = chartMgr.sumMain();
sumbanchan = chartMgr.sumBanchan();
sumsallad = chartMgr.sumSallad();
summeat = chartMgr.sumMeat();
%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://fonts.googleapis.com/css?family=David+Libre|Hind:400,700"
	rel="stylesheet">

<link rel="stylesheet" href="resources/css/reset.css">
<!-- CSS reset -->
<link rel="stylesheet" href="resources/css/style.css">
<!-- Resource style -->

<title>관리자 메인</title>
<style type="text/css">
</style>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
  
  var data = google.visualization.arrayToDataTable([
    ['카테고리', '품목수'],									
    ['국',    <%=gook%>],
    ['김치',      <%=kimchi%>],
    ['메인',  <%=main%>],
    ['반찬', <%=banchan%>],
    ['샐러드', <%=sallad%>],
    ['육류',    <%=meat%>]
  ]);

  var options = {
	title:'카테고리별 품목수',
	width:'100%',
	height:'500px',
    pieHole: 0.4,
  };

  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
  chart.draw(data, options);
}
google.charts.setOnLoadCallback(drawVisualization);

function drawVisualization() {
  var data = google.visualization.arrayToDataTable([
    ['카테고리', '판매 총액', { role: 'style' }], 									
    ['국',    <%=sumgook%> , '#3366cc'],
    ['김치',      <%=sumkimchi%> ,'#dc3912'],
    ['메인',  <%=summain%>, '#ff9900'],
    ['반찬', <%=sumbanchan%>,'#109618'],
    ['샐러드', <%=sumsallad%>,'#990099'],
    ['육류',    <%=summeat%>,'#0099c6']
  ]);

  var options = {
	
	title:'카테고리별 판매액',
	width:'100%',
	height:'500px',
    vAxis: {title: '판매액'},
    hAxis: {title: '카테고리'},
    seriesType: 'bars',
    series: {5: {type: 'line'}}};

  var chart = new google.visualization.ComboChart(document.getElementById('chart_div2'));
  
  chart.draw(data, options);
}
</script>
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

	<main class="cd-main-content" style="padding: 0px">
		<div>
			<%
				if (session.getAttribute("passwd") != null) {
			%>
			<div id="chart_div" style="height:400px;padding-top: 100px; padding-left: 80px; padding-right: 80px;" ></div><br>
			<div id="chart_div2" style="height:400px; padding-left: 80px; padding-right: 80px;"></div>
			<%
				} else {
					out.print(
							"<section class='cd-hero'><div class='cd-hero-content'><img src='resources/images/lunch.jpg' width='100%'></div></section>");
				}
			%>
		</div>

		<%--footer영역 --%>
		<p><a href="aa">재홍-대희-지훈-유진-준혜팀</a></p>
	</main>
	<!-- .cd-main-content -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script>
		if (!window.jQuery)
			document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
	</script>
	<script src="resources/js/main.js"></script>
	<!-- Resource jQuery -->
</body>
</html>