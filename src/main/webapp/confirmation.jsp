<%@page import="model.entity.ReservationBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ReservationBean> reservationBeanList = (List<ReservationBean>)request.getAttribute("reservationBeanList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約状況</title>
</head>
<body>
	<h1>予約一覧</h1>
	<!-- 予約済みデータの表示 -->
	<table border="1">
		<tr>
			<th>予約日</th>
			<th>名称</th>
			<th>所在地</th>
			<th>電話番号</th>
			<th>料金</th>
			<th>最大人数</th>
		</tr>
		<% for(ReservationBean item : reservationBeanList){ %>
		<tr>
			<td><%= item.getReserveDate() %></td>
			<td><%= item.getCampName() %></td>
			<td><%= item.getCampBean().getLocation() %></td>
			<td><%= item.getCampBean().getTel() %></td>
			<td><%= item.getCampBean().getCharge() %></td>
			<td><%= item.getCampBean().getCapacity() %></td>
			<td>
				<form action="DeleteReservation" method="get">
					<input type="submit" value="予約取消">
					<input type="hidden" name="reserveId" value=<%= item.getReserveId() %>>
				</form> 
			</td>
		</tr>
		<% } %>
	</table>

	<!-- 一覧表示へ戻る -->
	<form action="CampList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
</body>
</html>