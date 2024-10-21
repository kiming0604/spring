document.querySelectorAll('.popUpItem').forEach(a => {
  a.addEventListener('click', (event) => {
    event.preventDefault();
    
    let storeName = a.textContent;
    
    console.log(storeName);
    
     location.href = `/hypePop/popUpDetails?storeName=${encodeURIComponent(storeName)}`;
  });
});

document.querySelectorAll('#hamburgerList li').forEach(a => {
	  a.addEventListener('click', (event) => {
	    event.preventDefault();
	    
	    let hamburgerListName = a.textContent;

	    
	    switch (hamburgerListName) {
		case "팝업 스토어 검색":
			 location.href = "/hypePop/search/noData";
			break;

		case "굿즈 검색":
			 location.href = "/goodsStore/goodsSearch";
			break;
	
		case "내 주변":
			 location.href = "/#MapAPI";
			break;
		
		case "캘린더":
			 location.href = "/hypePop/calendar";
			break;	
		
		case "고객센터":
			 location.href = "/hypePop/customerMain";
			break;
		
		case "로그인":
			 location.href = "/member/login";
			break;
		
		case "회원가입":
			 location.href = "/member/login";
			break;
		
		case "회원가입":
			 location.href = "/member/join";
			break;
		
		}
	  });
	});
document.querySelectorAll('.popUpHeader div').forEach(a => {
	  a.addEventListener('click', (event) => {
	    event.preventDefault();
	    
	    let headerClick = a.textContent;
	    


	    
    switch (headerClick) {
     	case "메인 로고":
			 location.href = "/";
			break;
			
      	case "굿즈 메인 로고":
			 location.href = "/goodsStore/goodsMain";
			break;
			
			

		}
	  });
	});
// 검색 버튼 클릭 이벤트 추가
document.getElementById("searchBTN").addEventListener('click', () => {
    let inputText = document.getElementById("popUpSearchInput").value; // ID 변경

    console.log(inputText); // 입력된 텍스트를 콘솔에 출력
    
    if (inputText.trim() !== "") {
        location.href = "/hypePop/search?searchData=" + encodeURIComponent(inputText);
    } else {
        alert("검색어를 입력하세요.");
    }
});

document.addEventListener("DOMContentLoaded", () => {
    // 햄버거 메뉴 관련 설정
    const hamburgerList = document.getElementById('hamburgerList').querySelector('ul');
    hamburgerList.style.display = "none"; // 초기 상태에서 숨김

    // 햄버거 메뉴 클릭 이벤트
    document.getElementById('hamburgerBTN').addEventListener('click', () => {
        hamburgerList.style.display = (hamburgerList.style.display === "none") ? "block" : "none";
    });

    // 슬라이더 관련 설정
    const sliderContainers = [
        { slider: document.getElementById('hotPopUpStore'), leftArrow: document.getElementById('leftArrow'), rightArrow: document.getElementById('rightArrow') },
        { slider: document.getElementById('hotCatSlider1'), leftArrow: document.getElementById('leftArrow1'), rightArrow: document.getElementById('rightArrow1') },
        { slider: document.getElementById('hotCatSlider2'), leftArrow: document.getElementById('leftArrow2'), rightArrow: document.getElementById('rightArrow2') },
        { slider: document.getElementById('hotCatSlider3'), leftArrow: document.getElementById('leftArrow3'), rightArrow: document.getElementById('rightArrow3') }
    ];

    sliderContainers.forEach(({ slider, leftArrow, rightArrow }) => {
        if (slider && leftArrow && rightArrow) { // 슬라이더와 버튼이 존재하는지 확인
            let currentIndex = 0;
            const totalItems = slider.children.length;
            const itemsToShow = 4; // 보여줄 항목 수
            const itemWidth = (100 / itemsToShow); // 각 항목 너비 계산

            function updateSlider() {
                const offset = currentIndex * itemWidth; // 오프셋 계산
                slider.style.transform = `translateX(-${offset}%)`; // 슬라이더 위치 변경
            }

            leftArrow.addEventListener('click', () => {
                currentIndex = Math.max(currentIndex - 1, 0); // 최소값으로 제한
                updateSlider();
            });

            rightArrow.addEventListener('click', () => {
                currentIndex = Math.min(currentIndex + 1, totalItems - itemsToShow); // 최대값으로 제한
                updateSlider();
            });
        } else {
            console.error(`Slider or buttons not found for: ${slider}`);
        }
    });
});
