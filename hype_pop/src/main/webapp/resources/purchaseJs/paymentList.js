document.querySelectorAll('.image-payList').forEach(item => {
    // data-file-name 속성에서 파일 이름을 가져옴
    const fileName = item.getAttribute('data-file-name');
    console.log(fileName);
    const imgElement = item.querySelector('img'); // 각 image-goodsItem 내부의 img 요소를 찾음

    // 이미지 파일이 존재할 경우에만 요청 수행
    if (fileName && imgElement) {
        fetch(`/member/api/goodsBannerImages/${encodeURIComponent(fileName)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("이미지를 불러오는 데 실패했습니다.");
                }
                return response.blob();
            })
            .then(blob => {
                const imageUrl = URL.createObjectURL(blob); // Blob을 URL로 변환
                imgElement.src = imageUrl; // img 태그의 src로 설정
                imgElement.style.width = "100px"; // 너비 설정
                imgElement.style.height = "100px"; // 높이 설정
            })
            .catch(error => {
                console.error("이미지 불러오기 오류:", error);
            });
    }
});


//더보기 처리
document.getElementById("loadMoreBtn").addEventListener("click", function() {
    var page = parseInt(this.getAttribute("data-page"));
    var userNo = 2;  // 사용자 번호 (실제 값으로 대체)

    // 로딩 상태 표시
    var loadMoreBtn = this;
    loadMoreBtn.disabled = true;  // 버튼 비활성화
    loadMoreBtn.innerText = '로딩 중...';  // 버튼 텍스트 변경

    let pageNumber = page + 1;  // Ensure that page is a number
    if (isNaN(pageNumber) || pageNumber <= 0) {
        console.error("Invalid page number:", pageNumber);
        alert("잘못된 페이지 번호입니다.");
        return; // Stop further execution if the page is invalid
    }

    // AJAX 요청
    fetch(`/purchase/api/loadMoreItems?userNo=${userNo}&page=${page + 1}`)
    .then(response => {
        console.log('Response Status:', response.status);  // 상태 코드 로그 출력
        if (!response.ok) {
            throw new Error('서버 응답이 정상적이지 않습니다.');
        }
        return response.json();  // 응답을 JSON으로 받아옴
    })
    .then(data => {
        console.log('Response Data:', data);  // 서버에서 반환된 데이터 로그 출력

        // 새로운 데이터를 구매 목록에 추가
        var container = document.getElementById("purchase-list-container");
        var orderMap = {};  // 주문 번호를 기준으로 항목을 묶을 객체

        if (data.length === 0) {
            // 데이터가 없다면 더보기 버튼 숨기기
            loadMoreBtn.style.display = 'none';
            return;
        }

        // 데이터 순회하여 같은 orderId끼리 묶기
        data.forEach(item => {
            // 같은 orderId에 대한 항목이 없다면 새로 만들어서 추가
            if (!orderMap[item.orderId]) {
                orderMap[item.orderId] = document.createElement("div");
                orderMap[item.orderId].classList.add("purchase-order");
                orderMap[item.orderId].innerHTML = `<div class="order-id-box">주문 번호: ${item.orderId}</div>`;
                container.appendChild(orderMap[item.orderId]);
            }

            // 해당 orderId에 맞는 div 안에 상품 항목 추가
            var itemDiv = document.createElement("div");
            itemDiv.classList.add("purchase-item");
            itemDiv.innerHTML = `
                <div class="item-details">
                    <h3 class="item-name">상품명: ${item.gname}</h3>
                    <p class="item-quantity">수량: ${item.camount}</p>
                    <p class="item-price">가격: ${item.gprice}원</p>
                    <p class="item-date">구매 날짜: ${item.gbuyDate}</p>
                    <p class="item-status">상품 현황: ${item.gsituation}</p>
                </div>
            `;
            orderMap[item.orderId].appendChild(itemDiv);  // 기존의 orderId에 해당하는 div에 항목 추가
        });

        // "더보기" 버튼의 페이지 번호 업데이트
        loadMoreBtn.setAttribute("data-page", page + 1);

        // 로딩 상태 해제
        loadMoreBtn.disabled = false;
        loadMoreBtn.innerText = '더보기';

    })
    .catch(error => {
        console.error("Error loading more items:", error);
        alert("아이템을 불러오는 데 오류가 발생했습니다. 다시 시도해 주세요.");
        loadMoreBtn.disabled = false;  // 버튼 다시 활성화
        loadMoreBtn.innerText = '더보기';  // 버튼 텍스트 다시 원래대로
    });
});
