<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約状況</title>
</head>
<body>
	<h1>予約一覧</h1>
	<!-- 予約済みデータの表示 -->
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
			<td>
				<form action="DeleteReservation" method="get">
					<input type="submit" value="予約取消">
					<input type="hidden" name="reserveId" value="予約ID">
				</form> 
			</td>
		</tr>
	</table>

	<!-- 一覧表示へ戻る -->
	<form action="CampList" method="get">
		<input type="submit" value="一覧表示へ戻る">
	</form>
</body>
</html>