let currentSortType = null;

document.querySelectorAll('#popUpName').forEach(option => {
    option.addEventListener('click', (event) => {
        event.preventDefault();

        let popUpStoreName = option.textContent; // 텍스트 가져오기
        console.log(popUpStoreName);

        // GET 방식으로 보내줍니다
        location.href = `/hypePop/popUpDetails?storeName=${encodeURIComponent(popUpStoreName)}`;
    });
});

let currentPage = 1; // 현재 페이지 번호
cutExp();
// 페이지 로드 시 이벤트 리스너 설정
document.addEventListener('DOMContentLoaded', (event) => {
    setSortEventListeners();
    document.querySelector("#loadMoreBtn").addEventListener('click', (event) => {
        loadMoreGoods();
    });
});

// 굿즈 관심사를 문자열로 반환하는 함수
function getGoodsCategory(gcat) {
    // gcat이 null 또는 undefined인 경우 빈 문자열 반환
    if (!gcat) {
        return '';
    }

    let categories = [];
    if (gcat.healthBeauty === 1) categories.push("healthBeauty");
    if (gcat.game === 1) categories.push("game");
    if (gcat.culture === 1) categories.push("culture");
    if (gcat.shopping === 1) categories.push("shopping");
    if (gcat.supply === 1) categories.push("supply");
    if (gcat.kids === 1) categories.push("kids");
    if (gcat.design === 1) categories.push("design");
    if (gcat.foods === 1) categories.push("foods");
    if (gcat.interior === 1) categories.push("interior");
    if (gcat.policy === 1) categories.push("policy");
    if (gcat.character === 1) categories.push("character");
    if (gcat.experience === 1) categories.push("experience");
    if (gcat.collaboration === 1) categories.push("collaboration");
    if (gcat.entertainment === 1) categories.push("entertainment");

    return categories.join(", ");
}

function loadMoreGoods() {
    currentPage++;
    console.log(`Current page: ${currentPage}`);

    fetch('/goodsStore/loadMoreGoods', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            searchText: searchText,
            page: currentPage
        }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    })
    .then(data => {
        console.log('Server response:', data);

        const goodsContainer = document.getElementById("goodsContainer");

        // 데이터를 페이지에 추가
        data.forEach(vo => {
            const goodsElement = document.createElement("div");
            goodsElement.classList.add("goodsResult");
            console.log(vo.gcat);
            goodsElement.innerHTML = `
                <div class="goodsImg">굿즈 이미지</div>
                <div class="goodsInfo">
                    <input type="hidden" value="${vo.gno}">
                    <div class="goodsLike">좋아요: ${vo.likeCount}</div>
                    <div class="goodsName">상품명: ${vo.gname}</div>
                    <div class="goodsPrice">가격: ${vo.gprice} 원</div>
                    <div class="goodsExp">설명: ${vo.gexp}</div>
                    <div class="goodsSellDate">판매종료일: ${displayTime(vo.sellDate)}</div>
                    <div class="goodsCategory">굿즈 관심사: ${getGoodsCategory(vo.gcat)}</div>
                </div>
            `;

            goodsContainer.appendChild(goodsElement);
        });
        cutExp();

        // 모든 데이터를 불러왔을 경우 더보기 버튼을 숨김
        if (data.length === 0 || data.length < 10) {
            document.getElementById("loadMoreBtn").style.display = 'none';
        }

        // 기존에 선택된 정렬 기준에 따라 다시 정렬
        if (currentSortType != null) {
            sortGoods(currentSortType); // 현재 선택된 정렬 기준으로 다시 정렬
        }

        // 정렬 버튼 이벤트 리스너 다시 설정
        setSortEventListeners();
    })
    .catch(error => console.error('Error loading more goods:', error));
}

function displayTime(unixTimeStamp) {
    const myDate = new Date(unixTimeStamp);

    const y = myDate.getFullYear();
    const m = String(myDate.getMonth() + 1).padStart(2, '0'); // 월을 두 자리로 맞춤
    const d = String(myDate.getDate()).padStart(2, '0'); // 일을 두 자리로 맞춤

    return `${y}-${m}-${d}`;
}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelector("#priceHigh").addEventListener('click', () => {
        sortGoods('priceHigh');
    });

    document.querySelector("#priceLow").addEventListener('click', () => {
        sortGoods('priceLow');
    });

    document.querySelector("#likeHigh").addEventListener('click', () => {
        sortGoods('likeHigh');
    });

    document.querySelector("#replyHigh").addEventListener('click', () => {
        sortGoods('replyHigh');
    });

    document.querySelector("#newDate").addEventListener('click', () => {
        sortGoods('newDate');
    });
});

function sortGoods(sortType) {
    // goodsContainer 안에 있는 모든 굿즈 데이터를 가져옵니다.
    const goodsContainer = document.getElementById("goodsContainer");
    const goodsItems = Array.from(goodsContainer.querySelectorAll(".goodsResult"));

    // 데이터 정렬
    goodsItems.sort((a, b) => {
        let aValue, bValue;
        switch (sortType) {
            case 'priceHigh':
                aValue = parseInt(a.querySelector('.goodsPrice').textContent.replace(/[^\d]/g, ''));
                bValue = parseInt(b.querySelector('.goodsPrice').textContent.replace(/[^\d]/g, ''));
                return bValue - aValue; // 내림차순
            case 'priceLow':
                aValue = parseInt(a.querySelector('.goodsPrice').textContent.replace(/[^\d]/g, ''));
                bValue = parseInt(b.querySelector('.goodsPrice').textContent.replace(/[^\d]/g, ''));
                return aValue - bValue; // 오름차순
            case 'likeHigh':
                aValue = parseInt(a.querySelector('.goodsLike').textContent.replace(/[^\d]/g, ''));
                bValue = parseInt(b.querySelector('.goodsLike').textContent.replace(/[^\d]/g, ''));
                return bValue - aValue; // 내림차순
            case 'replyHigh':
                aValue = parseInt(a.querySelector('.goodsReply').textContent.replace(/[^\d]/g, ''));
                bValue = parseInt(b.querySelector('.goodsReply').textContent.replace(/[^\d]/g, ''));
                return bValue - aValue; // 내림차순
            case 'newDate':
                aValue = new Date(a.querySelector('.goodsSellDate').textContent);
                bValue = new Date(b.querySelector('.goodsSellDate').textContent);
                return bValue - aValue; // 최신순 내림차순
        }
    });

    // 정렬된 결과를 화면에 다시 표시
    goodsContainer.innerHTML = '';
    goodsItems.forEach(item => goodsContainer.appendChild(item));
}

function setSortEventListeners() {
    document.querySelector("#priceHigh").addEventListener('click', () => {
        currentSortType = 'priceHigh'; // 현재 정렬 기준 저장
        sortGoods('priceHigh');
    });

    document.querySelector("#priceLow").addEventListener('click', () => {
        currentSortType = 'priceLow'; // 현재 정렬 기준 저장
        sortGoods('priceLow');
    });

    document.querySelector("#likeHigh").addEventListener('click', () => {
        currentSortType = 'likeHigh'; // 현재 정렬 기준 저장
        sortGoods('likeHigh');
    });

    document.querySelector("#replyHigh").addEventListener('click', () => {
        currentSortType = 'replyHigh'; // 현재 정렬 기준 저장
        sortGoods('replyHigh');
    });

    document.querySelector("#newDate").addEventListener('click', () => {
        currentSortType = 'newDate'; // 현재 정렬 기준 저장
        sortGoods('newDate');
    });
}

document.querySelectorAll('.goodsResult').forEach(item => {
    item.addEventListener('click', () => {
        const gno = item.querySelector('input[type="hidden"]').value; // 숨겨진
																		// input
																		// 요소 선택
        location.href = `/goodsStore/goodsDetails?gno=${gno}`;
    });
});
function cutExp(){
	const goodsExp = document.querySelectorAll('.goodsExp');
	const maxLength1 = 40;
	goodsExp.forEach((element) => {
	    let displayName = element.textContent;
	    if (displayName.length > maxLength1) {
	        displayName = displayName.substring(0, maxLength1) + "...";
	    }
	    element.textContent = displayName;
	});
	
	const goodsNames = document.querySelectorAll('.goodsName');
	const maxLength2 = 20;
	goodsNames.forEach((element) =>{
		let displayName = element.textContent;
		if (displayName.length > maxLength2){
		displayName = displayName.substring(0, maxLength2) + "...";
		}
		element.textContent = displayName;
	})
}

