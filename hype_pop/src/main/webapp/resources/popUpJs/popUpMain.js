document.addEventListener("DOMContentLoaded", () => {
    

    // 기존 JS 코드 유지
    // 팝업 아이템 클릭 이벤트
    document.querySelectorAll('.popUpItem').forEach(a => {
        a.addEventListener('click', (event) => {
            event.preventDefault();

            let storeName = a.textContent;

            console.log(storeName);

            location.href = `/hypePop/popUpDetails?storeName=${encodeURIComponent(storeName)}`;
        });
    });

    // 햄버거 메뉴 설정
    const hamburgerList = document.getElementById('hamburgerList').querySelector('ul');
    hamburgerList.style.display = "none";

    document.getElementById('hamburgerBTN').addEventListener('click', () => {
        hamburgerList.style.display = (hamburgerList.style.display === "none") ? "block" : "none";
    });

    // 검색 버튼 클릭 이벤트 추가
    document.getElementById("searchBTN").addEventListener('click', () => {
        let inputText = document.getElementById("popUpSearchInput").value;

        console.log(inputText);

        if (inputText.trim() !== "") {
            location.href = "/hypePop/search?searchData=" + encodeURIComponent(inputText);
        } else {
            alert("검색어를 입력하세요.");
        }
    });

    // 슬라이더 관련 설정
    const sliderContainers = [
        { slider: document.getElementById('hotPopUpStore'), leftArrow: document.getElementById('leftArrow'), rightArrow: document.getElementById('rightArrow') },
        { slider: document.getElementById('hotCatSlider1'), leftArrow: document.getElementById('leftArrow1'), rightArrow: document.getElementById('rightArrow1') },
        { slider: document.getElementById('hotCatSlider2'), leftArrow: document.getElementById('leftArrow2'), rightArrow: document.getElementById('rightArrow2') },
        { slider: document.getElementById('hotCatSlider3'), leftArrow: document.getElementById('leftArrow3'), rightArrow: document.getElementById('rightArrow3') }
    ];

    sliderContainers.forEach(({ slider, leftArrow, rightArrow }) => {
        if (slider && leftArrow && rightArrow) {
            let currentIndex = 0;
            const totalItems = slider.children.length;
            const itemsToShow = 4;
            const itemWidth = (100 / itemsToShow);

            function updateSlider() {
                const offset = currentIndex * itemWidth;
                slider.style.transform = `translateX(-${offset}%)`;
            }

            leftArrow.addEventListener('click', () => {
                currentIndex = Math.max(currentIndex - 1, 0);
                updateSlider();
            });

            rightArrow.addEventListener('click', () => {
                currentIndex = Math.min(currentIndex + 1, totalItems - itemsToShow);
                updateSlider();
            });
        } else {
            console.error(`Slider or buttons not found for: ${slider}`);
        }
    });
});
