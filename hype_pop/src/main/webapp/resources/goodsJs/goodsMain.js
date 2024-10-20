// 공통 슬라이드 설정 함수
function setupSlider(containerClass, itemClass, prevBtnId, nextBtnId, itemsToShow, totalItems) {
    let currentIndex = 0;

    const nextBtn = document.getElementById(nextBtnId);
    const prevBtn = document.getElementById(prevBtnId);

    if (nextBtn) {
        nextBtn.addEventListener('click', () => {
            if (currentIndex < totalItems - itemsToShow) {
                currentIndex += itemsToShow;
                updateDisplay(containerClass, itemClass, currentIndex, itemsToShow);
            }
        });
    }

    if (prevBtn) {
        prevBtn.addEventListener('click', () => {
            if (currentIndex > 0) {
                currentIndex -= itemsToShow;
                updateDisplay(containerClass, itemClass, currentIndex, itemsToShow);
            }
        });
    }

    // 초기 표시 설정
    updateDisplay(containerClass, itemClass, currentIndex, itemsToShow);
}

// 공통 업데이트 디스플레이 함수
function updateDisplay(containerClass, itemClass, currentIndex, itemsToShow) {
    const items = document.querySelectorAll(`.${itemClass}`);
    items.forEach((item, index) => {
        item.style.display = (index >= currentIndex && index < currentIndex + itemsToShow) ? 'block' : 'none';
    });
}

// 모든 슬라이더에 대해 설정 호출
setupSlider('goodsContainer1', 'goodsItem1', 'prevBtn1', 'nextBtn1', 4, 8);
setupSlider('goodsContainer2', 'goodsItem2', 'prevBtn2', 'nextBtn2', 4, 8);
setupSlider('goodsContainer3', 'goodsItem3', 'prevBtn3', 'nextBtn3', 4, 8);
setupSlider('goodsContainer4', 'goodsItem4', 'prevBtn4', 'nextBtn4', 4, 8);

// 상품명 길이 조정
const goodsNames = document.querySelectorAll('.goodsName');
const maxLength = 40;
goodsNames.forEach((element) => {
    let displayName = element.textContent;
    if (displayName.length > maxLength) {
        displayName = displayName.substring(0, maxLength) + "...";
    }
    element.textContent = displayName;
});

// 굿즈 클릭하여 디테일 페이지로 이동
function setupGoodsItemClick(itemClass) {
    document.querySelectorAll(`.${itemClass}`).forEach(item => {
        item.addEventListener('click', () => {
            const gno = item.querySelector('input[type="hidden"]').value; // 숨겨진 input 요소 선택
            location.href = `/goodsStore/goodsDetails?gno=${gno}`;
        });
    });
}

setupGoodsItemClick('goodsItem1');
setupGoodsItemClick('goodsItem2');
setupGoodsItemClick('goodsItem3');
setupGoodsItemClick('goodsItem4');