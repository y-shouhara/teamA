<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>キャンプ場情報を削除しますか？</h1>
	<!-- 削除対象データの表示 -->
	<table border="1">
		<tr>
			<th>名称</th>
			<th>所在地</th>
			<th>電話番号</th>
			<th>料金</th>
			<th>最大人数</th>
		</tr>
		<tr>
			<td>モラップキャンプ場</td>
			<td>北海道</td>
			<td>0123-25-2201</td>
			<td>1300</td>
			<td>6</td>
		</tr>
	</table>
	<!-- 削除実行 -->
	<form action="DeleteCamp" method="post">
		<input type="hidden" name="location">
	<input type="submit"value="削除する">
	</form>
	<!-- 一覧表示画面へ戻る -->
	<form action="CampList" method="get">
		<input type="submit"value="一覧表示へ戻る">
	</form>
</body>
</html>
