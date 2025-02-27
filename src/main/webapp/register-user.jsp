<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録画面</title>
</head>
<body>
	<h1>新規ユーザー登録</h1>
	<% if(errorMsg != null){ %>
		<label><%= errorMsg %></label>
	<% } %>
	<form action="RegisterUser" method="post">
		<label>ユーザー名：</label>
		<input type="text" name="userName" required><br>
		<label>パスワード：</label>
		<input type="password" name="pass" minlength="5" required><br>
		<input type="submit" value="登録">
	</form>
	<form action="Login" method="get">
		<input type="submit" value="ログイン画面へ戻る">
	</form>
</body>
</html>