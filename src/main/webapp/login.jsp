<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");

	String nextServlet = "CampList";
	if(request.getAttribute("nextServlet") != null){
		nextServlet= (String) request.getAttribute("nextServlet");
	}
	
	String campName = "";
	if(request.getAttribute("campName") != null){
		campName= (String) request.getAttribute("campName");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>
	<% if(errorMsg != null){ %>
		<label><%= errorMsg %></label>
	<% } %>
	<form action="Auth" method="post">
		<label>ユーザー名：</label>
		<input type="text" name="userName" required><br>
		<label>パスワード：</label>
		<input type="password" name="pass" required><br>
		<input type="submit" value="ログイン">
		<input type="hidden" name="nextServlet" value=<%= nextServlet %>> 
		<input type="hidden" name="campName" value=<%= campName %>> 
	</form>
	<form action="CampList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
	<form action="RegisterUser" method="get">
		<input type="submit" value="新規ユーザー作成">
	</form>
</body>
</html>