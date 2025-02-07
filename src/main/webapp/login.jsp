<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>
	<form action="Auth" method="post">
		<label>ユーザー名：</label>
		<input type="text" name="userName"><br>
		<label>パスワード：</label>
		<input type="password" name="pass"><br>
		<input type="submit" value="ログイン">
	</form>
	<form action="campingList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
</body>
</html>