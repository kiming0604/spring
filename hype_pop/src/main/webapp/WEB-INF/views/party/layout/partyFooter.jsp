<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
html, body {
    height: 100%;
    margin: 0;
    padding: 0;
    box-sizing: border-box; /* 패딩과 보더를 포함한 크기 계산 */
}

body {
    min-height: 100vh; /* 뷰포트 높이를 기준으로 최소 높이 설정 */
    position: relative;
    padding-bottom: 85px; /* 푸터 높이만큼 여백 추가 */
}

.footer-container {
    background-color: #f8f8f8;
    border-top: 1px solid #ccc;
    text-align: center;
    padding: 20px;
    padding-bottom: 65px;
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
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
</body>
</html>