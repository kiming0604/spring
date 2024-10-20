<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="askTable">
		<table>
			<thead>
				<tr>
					<th>구매 번호</th>
					<th>상품명</th>
					<th>개수</th>
					<th>가격</th>
					<th>구매날짜</th>
					<th>상품현황</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list}">
					<tr>
						<td>${vo.bno}</td>
						<td><a href="${vo.bno}">${vo.title}</a></td>
						<td>${vo.writer}</td>
						<td>${vo.regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 		
<button type="button" id="saveAsk"></button>
        -->

	</div>
</body>
</html>