   loadImageSources();

//모든 이미지 요소에 대해 src를 설정하는 함수
   function loadImageSources() {
	    // 모든 상품 이미지 div를 가져옴
	    const itemImages = document.querySelectorAll('.item-image');

	    itemImages.forEach(item => {
	        // 각 상품 이미지에 대한 고유한 ID로 img 태그를 찾음
	        const itemId = item.id.split('-')[1];  // "item-상품번호" 형태로 되어 있으므로 번호를 추출
	        const fileName = item.querySelector('input[type="hidden"]').value; // 숨겨진 input에 저장된 파일 이름

	        // URL 인코딩 처리
	        const encodedFileName = encodeURIComponent(fileName); // 파일 이름을 URL 안전하게 인코딩

	        // img 태그의 src 속성을 동적으로 설정
	        const imgTag = item.querySelector('img');
	        if (imgTag) {
	            // 이미지 로드 전에 경로가 올바른지 확인하고 설정
	            const imageUrl = `/goodsStore/goodsBannerImages/${encodedFileName}`;
	            imgTag.src = imageUrl;

	            // 이미지 로딩 실패 처리
	            imgTag.onerror = () => {
	                imgTag.src = '/path/to/placeholder-image.jpg';  // 로딩 실패 시 대체 이미지 설정
	            };
	        }
	    });
	}
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


// 더보기 처리
document.getElementById("loadMoreBtn").addEventListener("click", function() {
    var page = parseInt(this.getAttribute("data-page"));
    var userNo = 67;  // 사용자 번호 (실제 값으로 대체)

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
        var lastOrderId = null;  // 마지막 처리된 orderId를 저장할 변수

        if (data.length === 0) {
            // 데이터가 없다면 더보기 버튼 숨기기
            loadMoreBtn.style.display = 'none';
            return;
        }

        data.forEach(item => {
            // orderId가 변경되었을 때 새로운 div 생성
            if (lastOrderId !== item.orderId) {
                // 새로운 orderId 섹션 생성
                var orderDiv = document.createElement("div");
                orderDiv.classList.add("purchase-order");
                orderDiv.innerHTML = '<div class="order-id-box">주문 번호: ' + item.orderId + '</div>';
                container.appendChild(orderDiv);  // 새로운 div를 container에 추가
                lastOrderId = item.orderId;  // 마지막 orderId 업데이트
            }

            // orderId가 같더라도 동일한 섹션 내에 항목 추가
            var itemDiv = document.createElement("div");
            itemDiv.classList.add("purchase-item");
            itemDiv.innerHTML = 
                '<div class="item-details">' +
                    '<h3 class="item-name">상품명: ' + item.gname + '</h3>' +
                    '<p class="item-quantity">수량: ' + item.camount + '</p>' +
                    '<p class="item-price">가격: ' + item.gprice + '원</p>' +
                    '<p class="item-date">구매 날짜: ' + item.gbuyDate + '</p>' +
                    '<p class="item-status">상품 현황: ' + item.gsituation + '</p>' +
                '</div>';
            // 마지막 생성된 orderDiv에 항목 추가
            container.lastChild.appendChild(itemDiv);
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
