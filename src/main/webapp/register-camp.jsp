<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キャンプ場新規登録</title>
</head>
<body>
	<h1>キャンプ場新規登録</h1>
	<form action="registr" method="post">
	
		<label>名称</label>
		<input type="text" name="campName"><br>
		<label>所在地</label>
		<input type="text" name="location"><br>
		<label>電話番号</label>
		<input type="tel" name="tel"><br>
		<label>料金</label>
		<input type="number" name="charge"><br>
		<label>最大人数</label>
		<input type="number" name="capacity"><br>
		<input type="submit" name="" value="登録">
	</form>
	<form action="campList" method="get">
		<input type="submit" name="" value="一覧表示へ戻る">
	</form>
</body>
</html>