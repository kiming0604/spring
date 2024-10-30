<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
    /* 기본 스타일 초기화 */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        background-color: #fee7ed; /* 전체 배경 색상 */
    }

    /* 헤더 스타일 */
    .popUpHeader {
        width: 100%;
        display: flex;
        align-items: center;
        padding: 5px 20px;
        background-color: #fee7ed;
        position: relative;
        z-index: 1002;
    }

    /* 메인 로고 및 햄버거 버튼 역할 */
    #mainLogoButton {
        background: none;
        border: none;
        cursor: pointer;
        display: flex;
        align-items: center;
        margin-right: 20px; /* 로고와 검색창 사이의 간격 */
        z-index: 1002;
    }

    #mainLogo img {
        max-height: 35px;
        width: auto;
    }

    /* 검색 및 알림 스타일 */
    #popUpSearchBox {
        display: flex;
        align-items: center; /* 버튼과 검색창 수직 정렬 */
        justify-content: center; /* 중앙 정렬 추가 */
        flex-grow: 1; /* 검색창을 가운데 정렬 */
        margin: 0 20px; /* 좌우 간격 추가 */
    }

    #popUpSearchBox input {
        padding: 12px 20px; /* 패딩을 약간 증가시켜 높이 조정 */
        width: 600px; /* 크기 두 배 증가 */
        border: 1px solid #ccc;
        border-radius: 25px; /* 끝부분 둥글게 */
        outline: none;
        text-align: center; /* 텍스트 중앙 정렬 */
        font-size: 16px;
        margin-right: 10px; /* 검색 버튼과의 간격 */
    }

    #popUpSearchClick, #noticeDiv {
        cursor: pointer;
    }

    /* 알림 버튼 */
    #noticeDiv {
        margin-left: 20px; /* 검색창과 알림 버튼 사이의 간격 */
    }

    /* 슬라이드 메뉴 */
    #logoContainer {
        position: fixed;
        top: 0;
        left: 0;
        height: auto;
        width: 150px;
        background-color: #fee7ed;
        transform: translateX(-100%);
        transition: transform 0.3s ease;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-top: 60px;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        z-index: 1001;
    }

    #logoContainer.show {
        transform: translateX(0);
    }

    #logoContainer div {
        padding: 15px;
        cursor: pointer;
        width: 100%;
        text-align: center;
        transition: background-color 0.3s;
    }

    #logoContainer div:hover {
        background-color: #f0f0f0;
    }

    #logoContainer img {
        max-height: 50px;
        width: auto;
    }

    /* 오버레이 */
    .overlay {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 0.5);
        display: none;
        z-index: 999;
    }

    .overlay.show {
        display: block;
    }

    /* 오버레이에서 메인 로고와 슬라이더 제외 */
    .noOverlay {
        z-index: 1003; /* 최상위로 올려서 슬라이더보다 위에 표시 */
        position: relative;
    }
    </style>
</head>
<body>
    <!-- 오버레이 -->
    <div class="overlay" id="overlay"></div>

    <div class="popUpHeader"> 
        <button id="mainLogoButton" onclick="showLogos()" class="noOverlay">
            <img src="/resources/images/mainLogo.png" alt="메인 로고" id="mainLogo">
        </button>
        <div id="popUpSearchBox">
            <input type="text" id="popUpSearchInput" placeholder="검색어 입력">
            <div id="popUpSearchClick"><span id="searchBTN">검색</span></div>
        </div>
        <div id="noticeDiv"><span id="notice">알림</span></div>
    </div>

    <!-- 슬라이드 메뉴 -->
    <div id="logoContainer" class="noOverlay">
        <div onclick="location.href='/hypePop/popUpMain'">
            <img src="/resources/images/popUpLogo.png" alt="팝업 스토어 로고">
        </div>
        <div onclick="location.href='/goodsStore/goodsMain'">
            <img src="/resources/images/goodsLogo.png" alt="굿즈 스토어 로고">
        </div>
        <div onclick="location.href='/exhibition/exhibitionMain'">
            <img src="/resources/images/exhibition.png" alt="전시관 로고">
        </div>
    </div>

    <!-- JavaScript 코드 -->
    <script>
        function showLogos() {
            var logoContainer = document.getElementById("logoContainer");
            var overlay = document.getElementById("overlay");

            // 슬라이드 메뉴 및 오버레이 표시
            logoContainer.classList.toggle("show");
            overlay.classList.toggle("show");

            // 오버레이 클릭 시 메뉴 숨기기
            overlay.onclick = function() {
                logoContainer.classList.remove("show");
                overlay.classList.remove("show");
            }
        }

        // 검색 버튼 클릭 이벤트
        document.getElementById("searchBTN").addEventListener('click', () => {
            let inputText = document.getElementById("popUpSearchInput").value;

            if (inputText.trim() !== "") {
                location.href = "/hypePop/search?searchData=" + encodeURIComponent(inputText);
            } else {
                alert("검색어를 입력하세요.");
            }
        });
    </script>
</body>
</html>
