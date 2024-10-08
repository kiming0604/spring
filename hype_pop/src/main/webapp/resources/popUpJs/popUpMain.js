document.addEventListener("DOMContentLoaded", () => {
    // 헤더 스팬 클릭 이벤트
    document.querySelectorAll('.popUpHeader span').forEach(a => {
        a.addEventListener('click', (event) => {
            event.preventDefault(); 

            let headerClick = a.id;
            let searchVal = document.querySelector("#popUpSearchBox").value; 
            let sendSearchData = `searchData=${encodeURIComponent(searchVal)}`; 

            console.log(sendSearchData);
            
            if (headerClick === "searchBTN") {
                location.href = `/hypePop/search?${sendSearchData}`; 
            } else if (headerClick === "notice") {
                console.log("알림 아이콘");
            } else if (headerClick === "mainLogo") {
                location.href = "/"; 
            } else if (headerClick === "goodsMainLogo") {
                // 추가적인 행동 필요 시 작성
            }
        });
    });

    // 햄버거 메뉴 클릭 시 리스트 표시
    document.getElementById('hamburgerBTN').addEventListener('click', () => {
        const hamburgerList = document.getElementById('hamburgerList').querySelector('ul'); // hamburgerList ul 요소 가져오기
        console.log('햄버거 버튼 클릭됨');

        if (hamburgerList.style.display === "block") {
            hamburgerList.style.display = "none"; // 현재가 block이면 none으로 변경
        } else {
            hamburgerList.style.display = "block"; // 현재가 block이 아니면 block으로 변경
        }
    });


    // 추천 팝업 스팬 클릭 이벤트
    document.querySelectorAll('.popUpRecommend span').forEach(a => {
        a.addEventListener('click', (event) => {
            event.preventDefault(); 
            
            let popUpStoreName = a.textContent; 
            console.log(popUpStoreName);
            location.href = `/hypePop/popUpDetails?storeName=${encodeURIComponent(popUpStoreName)}`;
        });
    });
});

