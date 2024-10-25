document.addEventListener("DOMContentLoaded", function () {
    // 모든 스토어 카드를 선택
    const storeCards = document.querySelectorAll(".store-card");
    const storesArray = [];

    // 사용자 위치 정보
    let userLocation = {
        latitude: null,
        longitude: null
    };

    // 사용자 위치 가져오기
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            userLocation.latitude = position.coords.latitude;
            userLocation.longitude = position.coords.longitude;
            console.log("사용자 위치:", userLocation);
            loadPageAgain(storesArray); // 위치 정보가 설정된 후 페이지 로드
        }, (error) => {
            console.error("위치 정보 가져오기 실패:", error);
        });
    } else {
        console.error("이 브라우저는 Geolocation을 지원하지 않습니다.");
    }

    // 각 카드에 대해 필요한 정보를 추출하여 배열에 저장
    storeCards.forEach((card) => {
        const storeName = card.querySelector(".storeName").textContent;
        const likeCount = parseInt(card.querySelector(".likeCount").textContent.replace(/[^0-9]/g, ""));
        const psNo = card.querySelector(".psNo").value;
        const psAddress = card.querySelector("h3").textContent.replace("주소: ", "");
        const interest = card.querySelector(".popUpCat").textContent;
        const rating = parseFloat(card.querySelector(".rating").value);
        const latitude = parseFloat(card.querySelector(".latitude").value); // 위도
        const longitude = parseFloat(card.querySelector(".longitude").value); // 경도

        // 날짜 정보를 가져오는 부분
        const psStartDate = card.querySelector(".date-info span:nth-child(1)").textContent.replace("시작일: ", "").trim();
        const psEndDate = card.querySelector(".date-info span:nth-child(2)").textContent.replace("종료일: ", "").trim();

        // 저장할 객체 생성
        const storeInfo = {
            name: storeName,
            likeCount: likeCount,
            psNo: psNo,
            address: psAddress,
            interest: interest,
            rating: rating,
            psStartDate: psStartDate,
            psEndDate: psEndDate,
            latitude: latitude, // 추가된 부분
            longitude: longitude // 추가된 부분
        };

        // 배열에 추가
        storesArray.push(storeInfo);
    });

    // 초기 상태 설정
    let currentStoreIndex = 0; // 초기 인덱스 설정
    const itemsPerPage = 10; // 페이지당 표시할 스토어 수

    // 초기 로드 시 스토어 표시
    function loadPageAgain(storesArray) {
        currentStoreIndex = 0; // 초기 인덱스 설정
        displayStores(storesArray); // 초기 10개 스토어 표시
    }

    // 스토어 표시 함수
    function displayStores(filteredStores) {
        const storeContainer = document.querySelector(".searchResultStore");

        if (!storeContainer) {
            console.error("storeContainer 요소를 찾을 수 없습니다.");
            return;
        }

        // 초기에 모든 카드 지우기
        storeContainer.innerHTML = "";

        // 초기에 10개만 보여주기
        const initialStores = filteredStores.slice(currentStoreIndex, currentStoreIndex + itemsPerPage);
        initialStores.forEach((store) => {
            const storeCard = createStoreCard(store);
            storeContainer.appendChild(storeCard);
        });

        // 현재 인덱스 업데이트
        currentStoreIndex += itemsPerPage;

        // 더보기 버튼 추가 조건 수정
        addLoadMoreButton(storeContainer, filteredStores);
    }

    // 더보기 버튼 추가
    function addLoadMoreButton(storeContainer, filteredStores) {
        // 더보기 버튼이 이미 존재하는지 확인
        let loadMoreBtn = document.querySelector(".load-more-btn");
        if (!loadMoreBtn) {
            loadMoreBtn = document.createElement("button");
            loadMoreBtn.textContent = "더보기";
            loadMoreBtn.classList.add("load-more-btn");
            loadMoreBtn.onclick = function () {
                loadMoreStores(filteredStores);
            };
        }

        // 현재 인덱스가 필터링된 스토어 수보다 적으면 버튼을 추가
        if (currentStoreIndex < filteredStores.length) {
            storeContainer.appendChild(loadMoreBtn); // 스토어 카드 아래에 버튼 추가
            loadMoreBtn.style.display = "block"; // 버튼을 보여줌
        } else {
            loadMoreBtn.style.display = "none"; // 더 이상 로드할 항목이 없으면 버튼 숨기기
        }
    }

    // 더보기 기능 구현
    function loadMoreStores(filteredStores) {
        const storeContainer = document.querySelector(".searchResultStore");

        // 다음 스토어 항목 로드
        const nextStores = filteredStores.slice(currentStoreIndex, currentStoreIndex + itemsPerPage);
        nextStores.forEach((store) => {
            const storeCard = createStoreCard(store);
            storeContainer.appendChild(storeCard);
        });

        // 현재 인덱스 업데이트
        currentStoreIndex += itemsPerPage;

        // 버튼의 표시 여부 조정
        addLoadMoreButton(storeContainer, filteredStores);
    }

    // Haversine 거리 계산 함수
    function calculateDistance(lat1, lon1, lat2, lon2) {
        const R = 6371; // 지구의 반경 (km)
        const dLat = (lat2 - lat1) * (Math.PI / 180);
        const dLon = (lon2 - lon1) * (Math.PI / 180);
        const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                  Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) *
                  Math.sin(dLon / 2) * Math.sin(dLon / 2);
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // 거리 (km)
    }

    // 거리 기준으로 정렬하는 함수
    function sortStoresByDistance() {
        if (userLocation.latitude && userLocation.longitude) {
            storesArray.sort((a, b) => {
                const distanceA = calculateDistance(userLocation.latitude, userLocation.longitude, a.latitude, a.longitude);
                const distanceB = calculateDistance(userLocation.latitude, userLocation.longitude, b.latitude, b.longitude);
                return distanceA - distanceB; // 가까운 순서대로 정렬
            });
            loadPageAgain(storesArray); // 정렬된 스토어 표시
        } else {
            console.error("사용자 위치가 설정되지 않았습니다.");
        }
    }

    // 관심사 선택 버튼 클릭 시 관심사 버튼 표시
    document.getElementById("selectInterestsBtn").onclick = function () {
        const interestButtons = document.getElementById("interestButtons");
        interestButtons.style.display = interestButtons.style.display === "none" ? "block" : "none";
    };

    let selectedInterests = [];

    // 관심사 버튼 클릭 시
    document.querySelectorAll("#interestButtons button").forEach((interest) => {
        interest.addEventListener("click", (event) => {
            const selInterest = interest.textContent;

            toggleInterest(selInterest, interest);
            if (selectedInterests.length > 0) {
                filterStoresByInterest();
            } else {
                loadPageAgain(storesArray); // 모든 스토어 표시
            }
        });
    });

    // 관심사 선택 토글
    function toggleInterest(interest, buttonElement) {
        if (selectedInterests.includes(interest)) {
            selectedInterests = selectedInterests.filter((item) => item !== interest);
            buttonElement.classList.remove("selected");
        } else {
            selectedInterests.push(interest);
            buttonElement.classList.add("selected");
        }
        console.log(selectedInterests);
    }

    function filterStoresByInterest() {
        const filteredStores = [];

        // 모든 스토어를 순회
        for (const store of storesArray) {
            // 스토어의 관심사 문자열을 배열로 변환
            const storeInterests = store.interest.split(",").map(s => s.trim()).filter(Boolean); // 빈 문자열 필터링

            // 여기에 관심사 문자열 형식 확인을 위한 로그 추가
            console.log("스토어 관심사:", storeInterests); // 디버깅을 위한 출력

            // 스토어 관심사에 대해 selectedInterests 배열의 각 요소에 대해 일치 여부 확인
            const hasMatchingInterest = storeInterests.some(storeInterest => 
                selectedInterests.includes(storeInterest)
            );

            // 일치하는 관심사가 있으면 필터링된 배열에 추가
            if (hasMatchingInterest) {
                filteredStores.push(store);
            }
        }

        console.log("필터링된 스토어:", filteredStores); // 디버깅을 위한 출력
        displayStores(filteredStores); // 필터링된 스토어 표시
    }
   
 // 스토어 카드 생성 함수
    function createStoreCard(store) {
        const storeCard = document.createElement("div");
        storeCard.classList.add("store-card");

        // 관심사를 콤마로 나눠 배열로 만듦
        const interestsArray = store.interest.split(",").map(s => s.trim());

        storeCard.innerHTML = `
            <div class="store-image">
                <img src="/resources/images/hamburger.png" alt="팝업스토어 배너 이미지">
            </div>
            <div class="store-info">
                <div class="header">
                    <h2><span class="storeName">${store.name}</span></h2>
                    <span class="likeCount">좋아요 수: ${store.likeCount}</span>
                    <input type="hidden" class="psNo" value="${store.psNo}">
                    <input type="hidden" class="rating" value="${store.rating}">
                    <input type="hidden" class="latitude" value="${store.latitude}">
                    <input type="hidden" class="longitude" value="${store.longitude}">
                </div>
                <h3>주소: ${store.address}</h3>
                <div class="date-info">
                    <span>시작일: ${store.psStartDate}</span>
                    <span>종료일: ${store.psEndDate}</span>
                </div>
                <div class="popUpCat">
                    ${interestsArray.map(interest => `<span class="interest-tag">${interest}</span>`).join("")}
                </div>
            </div>
        `;
        return storeCard;
    }

    // 정렬 기준 클릭 이벤트
    document.querySelectorAll(".searchConditions span").forEach((option) => {
        option.addEventListener("click", (event) => {
            event.preventDefault();

            // 모든 버튼의 selected 클래스 제거
            document.querySelectorAll(".searchConditions span").forEach((btn) => {
                btn.classList.remove("selected");
            });

            // 클릭한 버튼에 selected 클래스 추가
            option.classList.add("selected");

            let sortBy = option.id;

            console.log(`정렬 기준: ${sortBy}`);

            if (sortBy === "arrayByDis") {
                console.log("거리순 정렬");
                sortStoresByDistance(); // 거리순 정렬 함수 호출
            } else if (sortBy === "arrayByLike") {
                console.log("좋아요순 정렬");
                sortStoresByLike(); // 좋아요순 정렬 함수 호출
            } else if (sortBy === "arrayByLatest") {
                console.log("최신순 정렬");
                sortStoresByLatest(); // 최신순 정렬 함수 호출
            } else if (sortBy === "arrayByRating") {
                console.log("별점 순 정렬");
                sortStoresByRating(); // 별점 순 정렬 함수 호출
            }
        });
    });
 // 좋아요 수에 따라 정렬하는 함수
    function sortStoresByLike() {
        storesArray.sort((a, b) => b.likeCount - a.likeCount);
        loadPageAgain(storesArray);
    }

    // 최신순 정렬 함수
    function sortStoresByLatest() {
        const today = new Date();
        storesArray.sort((a, b) => {
            const dateA = new Date(a.psStartDate);
            const dateB = new Date(b.psStartDate);
            return dateB - dateA; // 최신순 정렬
        });
        loadPageAgain(storesArray);
    }

    // 별점 순으로 정렬하는 함수
    function sortStoresByRating() {
        storesArray.sort((a, b) => b.rating - a.rating);
        loadPageAgain(storesArray);
    }


});


