// 미리 지정된 리뷰 데이터 (실제 프로젝트에서는 DB에서 가져옴)
const reviews = [
    { rating: 4, comment: "여기 정말 좋았어요!" },
    { rating: 5, comment: "최고의 경험이었어요!" },
    { rating: 3, comment: "보통이었어요." }
];

// 리뷰 목록을 불러오는 함수
function loadUserReviews() {
    const reviewList = document.getElementById('reviewList');
    reviewList.innerHTML = ''; // 기존 리뷰 목록 초기화

    reviews.forEach(review => {
        // 리뷰 요소를 생성
        const reviewDiv = document.createElement('div');
        reviewDiv.classList.add('reviewItem');

        // 별점 표시
        const starDiv = document.createElement('div');
        starDiv.classList.add('userStarRating');
        for (let i = 1; i <= 5; i++) {
            const star = document.createElement('span');
            star.textContent = '★';
            if (i > review.rating) {
                star.style.color = 'gray'; // 채워지지 않은 별
            } else {
                star.style.color = 'gold'; // 채워진 별
            }
            starDiv.appendChild(star);
        }

        // 댓글 표시
        const commentP = document.createElement('p');
        commentP.textContent = review.comment;

        // 리뷰 요소에 별점과 댓글 추가
        reviewDiv.appendChild(starDiv);
        reviewDiv.appendChild(commentP);

        // 전체 리뷰 목록에 추가
        reviewList.appendChild(reviewDiv);
    });
}

// 별점 기능
const starElements = document.querySelectorAll('.StarRating span');
starElements.forEach(star => {
    star.addEventListener('mouseover', () => {
        highlightStars(star.dataset.value);
    });

    star.addEventListener('click', () => {
        document.querySelector('#rating').value = star.dataset.value; // 선택된 별점을 hidden input에 저장
        document.querySelector('#selectedRating').textContent = `선택한 별점: ${star.dataset.value}`; // 선택한 별점 표시
        highlightStars(star.dataset.value); // 클릭 시 별 하이라이트
    });

    star.addEventListener('mouseout', () => {
        const currentRating = document.querySelector('#rating').value;
        highlightStars(currentRating); // 선택된 별점으로 다시 하이라이트
    });
});

// 별을 하이라이트하는 함수
function highlightStars(ratingValue) {
    starElements.forEach(star => {
        star.classList.remove('selected'); // 모든 별에서 선택 상태 제거
        if (star.dataset.value <= ratingValue) {
            star.classList.add('selected'); // 선택된 별만 색상 변경
            star.style.color = 'gold'; // 채워진 별
        } else {
            star.style.color = 'gray'; // 비어있는 별
        }
    });
}

// 페이지 로드 시 리뷰를 불러옴
window.onload = loadUserReviews;
