<%@page import="java.util.HashMap"%>
<%@page import="model.entity.ReservationBean"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HashMap<String,String> availabilityMap = (HashMap)request.getAttribute("availabilityMap");
	LocalDate targetDay = (LocalDate) request.getAttribute("targetDay");
	String campName = (String)request.getAttribute("campName");
	List<String> allDateList = (List<String>)request.getAttribute("allDateList");
	List<String> yearList = (List<String>)request.getAttribute("yearList");
	List<String> monthList = (List<String>)request.getAttribute("monthList");
	List<String> dateList = (List<String>)request.getAttribute("dateList");
	HashMap<String,String> dayOfWeekMap = (HashMap)request.getAttribute("dayOfWeekMap");
	int yearFirstColSpan = (int)request.getAttribute("yearFirstColSpan");
	int yearSecondColSpan = (int)request.getAttribute("yearSecondColSpan");
	int monthFirstColSpan = (int)request.getAttribute("monthFirstColSpan");
	int monthSecondColSpan = (int)request.getAttribute("monthSecondColSpan");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約画面</title>
<link rel="stylesheet" href="CSS/reservation.css">
</head>
<body>
	<h1>予約画面</h1>
	<h2><%= campName %></h2>
	<div class="dateChangeBox">
		<!--	前の週を表示-->
		<form action="Reservation" method="get">
			<input type="submit" value="前の週へ">
			<input type="hidden" name="dateChange" value="previous">
			<input type="hidden" name="targetDay" value=<%= targetDay %>>
			<input type="hidden" name="campName" value=<%= campName %>>
		</form>
		<!--	次の週を表示-->
		<form action="Reservation" method="get">
			<input type="submit" value="次の週へ">
			<input type="hidden" name="dateChange" value="next">
			<input type="hidden" name="targetDay" value=<%= targetDay %>>
			<input type="hidden" name="campName" value=<%= campName %>>
		</form>
	</div>
	<label>【予約状況】　◎：予約可　×：予約不可</label>
	<table border="1" class="table">
		<thead>
			<tr>
				<th class="table__year" colspan=<%= yearFirstColSpan %>><%= yearList.get(0) %>年</th>
				<% if( yearSecondColSpan > 0) { %>
					<th class="table__year" colspan=<%= yearSecondColSpan %>><%= yearList.get(1) %>年</th>
				<% } %>
			</tr>
			<tr>
				<th class="table__Month" colspan=<%= monthFirstColSpan %>><%= monthList.get(0) %>月</th>
				<% if( monthSecondColSpan > 0) { %>
					<th class="table__Month" colspan=<%= monthSecondColSpan %>><%= monthList.get(1) %>月</th>
				<% } %>
			</tr>
			<tr>
				<% for(String date : dateList){%>
					<td class="table__data"><%= date %><br><%= dayOfWeekMap.get(date)  %></td>
				<% } %>
			</tr>
			<tr>
				<% for(String allDate : allDateList){%>
					<% if(availabilityMap.get(allDate) == null){ %>
						<td class="table__data">
							<a href="#" name="link">◎</a>
							<form action="Reservation" method="post" name="link-form" >
								<input type="hidden" name="campName" value=<%= campName %>>
								<input type="hidden" name="reserveDate" value=<%= allDate %>>
							</form>
						</td>
					<% }else{ %>
						<td class="table__data"><a>×</a></td>
					<% } %>
				<% } %>
			</tr>
		</thead>
	</table>
	
	<!-- 一覧表示へ戻る -->
	<form action="CampList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
	
	<script src="JavaScript/reservation.js"></script>
</body>
</html>