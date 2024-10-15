let psNo = document.getElementById('psNo').value;
let userNo = document.getElementById('userNo').value;

// loadUserReviews 함수 정의
function loadUserReviews(reviews) {
    const reviewList = document.getElementById('reviewList');
    reviewList.innerHTML = ''; // 리뷰 목록 초기화

    reviews.forEach((review) => {
        if (review.deleted) return; // 삭제된 리뷰는 건너뜀

        const reviewDiv = document.createElement('div');
        reviewDiv.classList.add('reviewItem');

        const starDiv = document.createElement('div');
        starDiv.classList.add('userStarRating');
        for (let i = 1; i <= 5; i++) {
            const star = document.createElement('span');
            star.textContent = '★';
            star.setAttribute('data-value', i);
            star.style.color = i <= review.psScore ? 'gold' : 'gray';
            starDiv.appendChild(star);
        }

        const commentP = document.createElement('p');
        commentP.textContent = review.psComment;
        commentP.classList.add('reviewComment'); // 클래스 추가
        reviewDiv.appendChild(starDiv);
        reviewDiv.appendChild(commentP); // 별점 아래에 댓글 추가

        // 리뷰가 사용자의 것인 경우에만 케밥 메뉴 추가
        if (review.isMine) {
            const kebabMenu = document.createElement('div');
            kebabMenu.classList.add('kebabMenu');
            kebabMenu.innerHTML = '⋮';
            reviewDiv.appendChild(kebabMenu);

            const kebabMenuOptions = document.createElement('ul');
            kebabMenuOptions.classList.add('kebabMenuOptions');
            kebabMenuOptions.innerHTML = `
                <li class="editReview">수정</li>
                <li class="deleteReview">삭제</li>
            `;
            reviewDiv.appendChild(kebabMenuOptions);

            // 초기 상태에서 숨김
            kebabMenuOptions.style.display = "none";

            // 케밥 메뉴 클릭 이벤트 리스너 추가
            kebabMenu.addEventListener('click', function (event) {
                event.stopPropagation(); // 이벤트 전파를 막아 케밥 메뉴가 닫히지 않도록 함
                const optionsVisible = kebabMenuOptions.style.display === "block";

                // 모든 케밥 메뉴를 숨김
                document.querySelectorAll('.kebabMenuOptions').forEach(menu => menu.style.display = "none");

                // 현재 클릭한 메뉴가 숨겨져 있으면 열고, 아니면 닫음
                kebabMenuOptions.style.display = optionsVisible ? "none" : "block";
            });

            // 문서 클릭 시 모든 케밥 메뉴 닫기
            document.addEventListener('click', function () {
                document.querySelectorAll('.kebabMenuOptions').forEach(menu => menu.style.display = "none");
            });

            // 수정 버튼 클릭 이벤트 리스너 추가
            kebabMenuOptions.querySelector('.editReview').addEventListener('click', function () {
                updateReviewStarRating(reviewDiv, review.psScore);
                const ratingInput = document.getElementById('rating');
                ratingInput.value = review.psScore; // 기존 별점으로 업데이트

                // 선택한 별점 텍스트 업데이트
                const selectedRatingText = document.getElementById('selectedRating');
                selectedRatingText.textContent = '선택한 별점: ' + review.psScore;
            });

            // 삭제 버튼 클릭 이벤트 리스너 추가
            kebabMenuOptions.querySelector('.deleteReview').addEventListener('click', function () {
                if (confirm("리뷰를 삭제하시겠습니까?")) { // 삭제 확인
                    review.deleted = true; // 리뷰를 삭제 처리
                    loadUserReviews(reviews); // 리뷰 목록 다시 로드
                }
            });
        }

        reviewList.appendChild(reviewDiv);
    });
}

function send(f) {
    let rating = f.rating.value;
    let reviewText = f.reviewText.value;
    let psNo = f.psNo.value;
    let userNo = f.userNo.value;

    let data = {
        rating: rating,
        reviewText: reviewText,
        psNo: psNo,
        userNo: userNo
    };

    // fetch를 이용한 POST 요청
    fetch('/reply/insertReply', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('오류 내용 : ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        if (data.status === "success") {
            // 리뷰가 성공적으로 등록된 후 바로 새로운 리뷰 목록을 가져옴
            fetchUserReviews(psNo, userNo);
        }
    })
    .catch(err => {
        console.error('Error:', err);
    });
}

function fetchUserReviews(psNo, userNo) {
    fetch('/reply/getUserReviews', {
        method: 'POST', // POST 메소드 사용
        headers: {
            'Content-Type': 'application/json' // JSON 형식으로 데이터 전송
        },
        body: JSON.stringify({ psNo: psNo, userNo: userNo }) // psNo와 userNo를 JSON 형식으로 전송
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('리뷰를 가져오는 데 실패했습니다: ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        // 리뷰 데이터 형식 확인
        if (Array.isArray(data.reviews)) {
            // 리뷰 배열이 비어 있는 경우 처리
            if (data.reviews.length > 0) {
                loadUserReviews(data.reviews); // 새로운 리뷰로 업데이트하여 로드
            } else {
                console.log('리뷰가 없습니다.');
            }
        } else {
            console.error('리뷰 데이터 형식이 올바르지 않습니다.');
        }
    })
    .catch(err => {
        console.error('Error:', err);
        alert('리뷰를 가져오는 데 문제가 발생했습니다. 다시 시도해 주세요.'); // 사용자에게 오류 알림
    });
}

document.addEventListener("DOMContentLoaded", function () {
    fetchUserReviews(psNo, userNo);
    
    var linkElement = document.createElement("link");
    linkElement.rel = "stylesheet";
    linkElement.type = "text/css";
    linkElement.href = "/resources/css/popUpStoreDetails.css";  // 경로를 여기에 입력
    document.head.appendChild(linkElement);  // <head>에 추가

    // 리뷰 작성란의 별점 클릭 기능
    const reviewStars = document.querySelectorAll('#newReviewStars span');
    reviewStars.forEach(star => {
        star.addEventListener('click', function () {
            const rating = this.getAttribute('data-value');
            const ratingInput = document.getElementById('rating');
            const selectedRatingText = document.getElementById('selectedRating'); // 선택한 별점 텍스트 업데이트

            reviewStars.forEach(s => {
                s.style.color = 'gray'; // 초기 색상
            });
            for (let i = 1; i <= rating; i++) {
                reviewStars[i - 1].style.color = 'gold'; // 선택된 별
            }

            ratingInput.value = rating;
            selectedRatingText.textContent = '선택한 별점: ' + rating; // 선택한 별점 텍스트 업데이트
        });
    });

    // 굿즈 스토어 이동
    document.querySelectorAll('.hitGoods span').forEach(a => {
        a.addEventListener('click', (event) => {
            event.preventDefault();
            let goodsName = a.textContent;
            location.href = `/goodsStore/goodsDetails?goodsName=${encodeURIComponent(goodsName)}`;
        });
    });

    // 좋아요 클릭 이벤트
    document.querySelectorAll('#likeCount').forEach(a => {
        a.addEventListener('click', (event) => {
            event.preventDefault();
            let psNo = document.getElementById('psNo').value;  // 팝업스토어 번호
            let userNo = document.getElementById('userNo').value; // 사용자 번호
            fetch('/like/insertLike', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ psNo: psNo, userNo: userNo }) // JSON 형식으로 전송
            })
            .then(response => {
                if (response.ok) {
                    alert("좋아요가 추가되었습니다!"); // 성공 알림
                } else {
                    alert("이미 좋아요를 눌렀습니다!"); // 이미 눌렀을 경우 경고
                }
            })
            .catch(err => console.error('Error:', err));
        });
    });
});
