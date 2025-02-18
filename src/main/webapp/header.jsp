<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="CSS/header.css">
</head>
<header class="header">
	<!-- ログイン中かで表示内容を分岐 -->
	<% if(userName == null) { %>
		<div class="header__gest">
			<label class="header__userLabel">ユーザー名：ゲスト さん</label>
			<!-- ログイン画面へ遷移 -->
			<form action="Login" method="get">
				<input type="submit" value="ログイン" >
			</form>
		</div>
	<% }else{ %>
		<div class="header__gest">
			<label class="header__userLabel">ユーザー名：<%= userName %> さん</label>
			<!-- ログアウト画面へ遷移 -->
			<form action="" method="get">
				<input type="submit" value="ログアウト">
			</form>
		</div>
	<% } %>
	<!-- 予約確認画面へ遷移 -->
	<form action="Confirmation" method="post">
		<input type="submit" value="予約確認">
		<input type="hidden" name="transition" value="Confirmation"> 
	</form>
</header>
</html>