<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約画面</title>
<link rel="stylesheet" href="CSS/reservation.css">
</head>
<body>
	<!--	ヘッダー部分の読み込み-->
	<jsp:include page="header.jsp" />
	
	<h1>予約画面</h1>
	<h2>モラップキャンプ場</h2>
	<div class="dateChangeBox">
		<!--	前の週を表示-->
		<form action="Reservation" method="post">
			<input type="submit" value="前の週へ">
			<input type="hidden" name="dateChange" value="previous">
		</form>
		<!--	次の週を表示-->
		<form action="Reservation" method="post">
			<input type="submit" value="次の週へ">
			<input type="hidden" name="dateChange" value="next">
		</form>
	</div>
	<label>【予約状況】　◎：予約可　×：予約不可</label>
	<table border="1" class="table">
		<thead>
			<tr>
				<th class="table__year" colspan="3">2025年</th>
				<% if( 4 > 0) { %>
					<th class="table__year" colspan="4">2026年</th>
				<% } %>
			</tr>
			<tr>
				<th class="table__Month" colspan="3">12月</th>
				<% if( 4 > 0) { %>
					<th class="table__Month" colspan="4">1月</th>
				<% } %>
			</tr>
			<tr>
				<td class="table__data">25<br>(火)</td>
				<td class="table__data">30<br>(水)</td>
				<td class="table__data">31<br>(木)</td>
				<td class="table__data">1<br>(金)</td>
				<td class="table__data">2<br>(土)</td>
				<td class="table__data">3<br>(日)</td>
				<td class="table__data">4<br>(月)</td>
			</tr>
			<tr>
				<td class="table__data"><a>×</a></td>
				<td class="table__data"><a>×</a></td>
				<td class="table__data"><a>×</a></td>
				<td class="table__data"><a href="#">◎</a></td>
				<td class="table__data"><a href="#">◎</a></td>
				<td class="table__data"><a href="#">◎</a></td>
				<td class="table__data"><a href="#">◎</a></td>
			</tr>
		</thead>
	</table>
	
	<!-- 一覧表示へ戻る -->
	<form action="CampList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
</body>
</html>