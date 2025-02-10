<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録画面</title>
</head>
<body>
	<h1>新規ユーザー登録</h1>
	<label>エラーメッセージ表示</label>
	<form action="RegisterUser" method="post">
		<label>ユーザー名：</label>
		<input type="text" name="userName"><br>
		<label>パスワード：</label>
		<input type="password" name="pass" minlength="5"><br>
		<input type="submit" value="登録">
	</form>
	<form action="Login" method="get">
		<input type="submit" value="ログイン画面へ戻る">
	</form>
</body>
</html>