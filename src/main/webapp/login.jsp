<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
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
		<input type="text" name="userName"><br>
		<label>パスワード：</label>
		<input type="password" name="pass"><br>
		<input type="submit" value="ログイン">
	</form>
	<form action="CampList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
	<form action="RegisterUser" method="get">
		<input type="submit" value="新規ユーザー作成">
	</form>
<!--	テスト用-->
	<form action="Reservation" method="get">
					<input type="submit" value="予約">
					<input type="hidden" value="モラップキャンプ場" name="campName">
				</form>
</body>
</html>