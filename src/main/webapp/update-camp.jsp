<%@page import="model.entity.CampBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CampBean campBean = (CampBean) request.getAttribute("campBean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>キャンプ場編集</h1>
	<form action="UpdateCamp" method="post">
		<label>名称</label>
		<input type="text" name="campName" value=<%= campBean.getCampName() %>><br>
		<label>所在地</label>
		<input type="text" name="location" value=<%= campBean.getLocation() %>><br>
		<label>電話番号</label>
		<input type="tel" name="tel" value=<%= campBean.getTel() %>><br>
		<label>料金</label>
		<input type="number" name="charge" value=<%= campBean.getCharge() %>><br>
		<label>最大人数</label>
		<input type="number" name="capacity" value=<%= campBean.getCapacity() %>><br>
		<input type="submit"value="編集">
	</form>
	<form action="CampList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
</body>
</html>