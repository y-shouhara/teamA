<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--<%-->
<!--	int managerId = Integer.parseInt((String)session.getAttribute("managerId")) ;-->
<!--%>-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--	ヘッダー部分の読み込み-->
	<jsp:include page="header.jsp" />
	
	<h1>キャンプ場一覧</h1>
	
	<!-- 絞り込み条件 -->
	<label>絞り込み条件</label>
	<form action="CampList" method="post">
		<label>所在地:</label>
		<select name="">
			<option value="">都道府県名を選択</option>
		</select>
		<input type="submit" value="検索">
	</form>
	<!-- データ一覧表示 -->
	<table border="1">
		<tr>
			<th>名称</th>
			<th>所在地</th>
			<th>電話番号</th>
			<th>料金</th>
			<th>最大人数</th>
		</tr>
		<tr>
			<td>キャンプ場</td>
			<td>北海道</td>
			<td>0909</td>
			<td>254</td>
			<td>10</td>
			 <td>
				<form action="" method="">
				<input type="submit" value="予約">
				</form>
			</td>
				<td>
					<form action="UpdateCamp" method="get">
					<input type="submit" value="編集">
					<input type="hidden" value="">
					</form>
				</td>
				<td>
					<form action="DeleteCamp" method="get">
					<input type="submit" value="削除">
					</form>
				</td> 
		</tr>
	</table>
</body>
</html>