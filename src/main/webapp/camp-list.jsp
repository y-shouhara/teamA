<%@page import="model.entity.CampBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<CampBean> CampList = (List) request.getAttribute("CampList");
%>
<%
int managerId = 0;
if (session.getAttribute("managerId") != null) {
	managerId = (int) session.getAttribute("managerId");
}
%>
<%
String userName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/header.css">
</head>
<body>
	<header class="header">
		<!-- ログイン中かで表示内容を分岐 -->
		<%
		if (userName == null) {
		%>
		<div class="header__gest">
			<label class="header__userLabel">ユーザー名：ゲスト さん</label>
			<!-- ログイン画面へ遷移 -->
			<form action="Login" method="get">
				<input type="submit" value="ログイン">
			</form>
		</div>
		<%
		} else {
		%>
		<div class="header__gest">
			<label class="header__userLabel">ユーザー名：<%=userName%> さん
			</label>
			<!-- ログアウト画面へ遷移 -->
			<form action="Logout" method="get">
				<input type="submit" value="ログアウト">
			</form>
		</div>
		<%
		}
		%>
		<!-- 予約確認画面へ遷移 -->
		<form action="Auth" method="get">
			<input type="submit" value="予約確認">
			<input type="hidden" name="transition" value="Confirmation">
		</form>
	</header>

	<h1>キャンプ場一覧</h1>

	<!-- 絞り込み条件 -->
	<label>絞り込み条件</label>
	<form action="CampList" method="post">
		<label>所在地:</label> <select name="location">
			<option value="">選択してください</option>
			<option value="北海道">北海道</option>
			<option value="青森県">青森県</option>
			<option value="岩手県">岩手県</option>
			<option value="宮城県">宮城県</option>
			<option value="秋田県">秋田県</option>
			<option value="山形県">山形県</option>
			<option value="福島県">福島県</option>
			<option value="茨城県">茨城県</option>
			<option value="栃木県">栃木県</option>
			<option value="群馬県">群馬県</option>
			<option value="埼玉県">埼玉県</option>
			<option value="千葉県">千葉県</option>
			<option value="東京都">東京都</option>
			<option value="神奈川県">神奈川県</option>
			<option value="新潟県">新潟県</option>
			<option value="富山県">富山県</option>
			<option value="石川県">石川県</option>
			<option value="福井県">福井県</option>
			<option value="山梨県">山梨県</option>
			<option value="長野県">長野県</option>
			<option value="岐阜県">岐阜県</option>
			<option value="静岡県">静岡県</option>
			<option value="愛知県">愛知県</option>
			<option value="三重県">三重県</option>
			<option value="滋賀県">滋賀県</option>
			<option value="京都府">京都府</option>
			<option value="大阪府">大阪府</option>
			<option value="兵庫県">兵庫県</option>
			<option value="奈良県">奈良県</option>
			<option value="和歌山県">和歌山県</option>
			<option value="鳥取県">鳥取県</option>
			<option value="島根県">島根県</option>
			<option value="岡山県">岡山県</option>
			<option value="広島県">広島県</option>
			<option value="山口県">山口県</option>
			<option value="徳島県">徳島県</option>
			<option value="香川県">香川県</option>
			<option value="愛媛県">愛媛県</option>
			<option value="高知県">高知県</option>
			<option value="福岡県">福岡県</option>
			<option value="佐賀県">佐賀県</option>
			<option value="長崎県">長崎県</option>
			<option value="熊本県">熊本県</option>
			<option value="大分県">大分県</option>
			<option value="宮崎県">宮崎県</option>
			<option value="鹿児島県">鹿児島県</option>
			<option value="沖縄県">沖縄県</option>
		</select> <input type="submit" value="検索">
	</form>
	<%
	if (managerId == 1) {
	%>
	<form action="RegisterCamp" method="get">
		<input type="submit" value="登録">
	</form>
	<%
	}
	%>
	<!-- データ一覧表示 -->
	<table border="1">
		<tr>
			<th>名称</th>
			<th>所在地</th>
			<th>電話番号</th>
			<th>料金</th>
			<th>最大人数</th>
		</tr>
		<%
		for (CampBean display : CampList) {
		%>
		<tr>
			<td><%=display.getCampName()%></td>
			<td><%=display.getLocation()%></td>
			<td><%=display.getTel()%></td>
			<td><%=display.getCharge()%></td>
			<td><%=display.getCapacity()%></td>

			<%
			if (managerId == 1) {
			%>
			<td>
				<form action="UpdateCamp" method="get">
					<input type="submit" value="編集">
					 <input type="hidden" name="campName" value=<%=display.getCampName()%>>
				</form>
			</td>
			<td>
				<form action="DeleteCamp" method="get">
					<input type="submit" value="削除"> 
					<input type="hidden" name="campName" value=<%=display.getCampName()%>>
				</form>
			</td>
			<%
			} else {
			%>
			<td>
				<form action="Auth" method="get">
					<input type="submit" value="予約"> 
					<input type="hidden"name="transition" value="Reservation">
					<input type="hidden" name="campName" value=<%=display.getCampName()%>>
				</form>
			</td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>