<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>푸터 예제</title>
<style>
.footer-container {
    background-color: #f8f8f8;
    border-top: 1px solid #ccc;
    text-align: center;
    padding: 20px; /* 내부 여백 */
    padding-bottom: 65px;
    margin-top: 20px;
}
.footer-contact h4 {
    margin-bottom: 10px;
    padding: 5px;
    color: black;
}
</style>
</head>
<body>

    <!-- 푸터 내용 -->
    <div class="footer-container">
	    <div class="footer-contact">
	        <h4>Address. 서울특별시 종로구 종로12길 15, 2층/5층/8~10층(관철동 13-13) | E-mail. hypePop@hypepop.or.kr | 사업자등록번호 123-45-67890호</h4>
	        <h4>Copyrights 2024 by HYPE. All right reserved.</h4>
	    </div>
    </div>

    <!-- 네비게이션 바 -->
    <jsp:include page="popUpNavBar.jsp"/>

</body>
</html>