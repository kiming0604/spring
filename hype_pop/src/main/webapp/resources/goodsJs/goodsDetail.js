const rs = replyService;

document.addEventListener("DOMContentLoaded", function () {
    // 댓글 목록 표시
    let currentPage = 1;  // 현재 페이지
    const pageSize = 5;   // 페이지당 댓글 수
    
    // 페이지 처음 로드 시 댓글 목록 표시
    showReplyList(currentPage);
    
    
    const decreaseBtn = document.getElementById('decreaseBtn');
    const increaseBtn = document.getElementById('increaseBtn');
    const quantityInput = document.getElementById('quantity');
    const totalPriceDisplay = document.getElementById('totalPrice');
    const goodsPrice = parseFloat(document.getElementById('goodsPrice').textContent.replace(/[^0-9]/g, '')); // 굿즈
																												// 가격
    displayAvgStars();
    function displayAvgStars() {
        const avgStarsContainer = document.getElementById('avgStarsContainer');
        const avgStarString = document.querySelector('.avgStarString');
        const gno = new URLSearchParams(location.search).get('gno'); // gno
																		// 가져오기
        let stars = '';

        // 평균 별점을 서버에서 fetch로 가져옴
        fetch('/gReply/avgStars/' + gno)
        .then(response => response.json())
        .then(result => {
            const avgScore = parseFloat(result); // 서버에서 받은 avgScore 값 사용
            for (let i = 1; i <= 5; i++) {
                if (i <= avgScore) {
                    stars += '<span style="color: gold;">★</span>'; // 금색 별
                } else {
                    stars += '<span style="color: gray;">★</span>'; // 회색 별
                }
            }
            avgStarsContainer.innerHTML = stars; // 별점 표시
            avgStarString.innerHTML = "평균 별점 : " + avgScore.toFixed(1);
        })
        .catch(err => console.error('Error:', err));
    }
    
    // 수량 조절
    decreaseBtn.addEventListener('click', () => {
        let quantity = parseInt(quantityInput.value);
        if (quantity > 1) {
            quantity--;
            quantityInput.value = quantity;
            totalPriceDisplay.textContent = (quantity * goodsPrice).toLocaleString() + ' 원';
        }
    });

    increaseBtn.addEventListener('click', () => {
        let quantity = parseInt(quantityInput.value);
        quantity++;
        quantityInput.value = quantity;
        totalPriceDisplay.textContent = (quantity * goodsPrice).toLocaleString() + ' 원';
    });

    totalPriceDisplay.textContent = (1 * goodsPrice).toLocaleString() + ' 원';

    const starRating = document.querySelectorAll('#newReviewStars span');
    const ratingInput = document.getElementById('rating');
    const selectedRating = document.getElementById('selectedRating');

    // 기본 별은 회색
    starRating.forEach(star => {
        star.style.color = 'gray';
    });

    // 별점 hover 및 클릭 처리
    starRating.forEach((star, index) => {
        star.addEventListener('mouseover', function () {
            for (let i = 0; i <= index; i++) {
                starRating[i].style.color = 'gold';
            }
        });

        star.addEventListener('mouseout', function () {
            starRating.forEach(s => s.style.color = 'gray');
            const selectedValue = ratingInput.value;
            for (let i = 0; i < selectedValue; i++) {
                starRating[i].style.color = 'gold';
            }
        });

        star.addEventListener('click', function () {
            const rating = this.getAttribute('dataValue');
            ratingInput.value = rating;
            selectedRating.textContent = '선택한 별점: ' + rating;

            starRating.forEach(s => s.style.color = 'gray');
            for (let i = 0; i < rating; i++) {
                starRating[i].style.color = 'gold';
            }
        });
    });

    // gscore에 따라 별점 표시
    function displayStars(score, container) {
        let stars = '';
        for (let i = 1; i <= 5; i++) {
            if (i <= score) {
                stars += '<span style="color: gold;">★</span>'; // 금색 별
            } else {
                stars += '<span style="color: gray;">★</span>'; // 회색 별
            }
        }
        container.innerHTML = stars;
    }

    // 댓글 등록 기능
    document.getElementById('addReply').addEventListener('click', function (event) {
        event.preventDefault();
        const rating = document.getElementById('rating').value;
        const reviewText = document.getElementById('reviewText').value;
        const gno = new URLSearchParams(location.search).get('gno');
        const userId = "유저ID입니다.";

        rs.add({
            gno: gno,
            userNo: 1,
            gcomment: reviewText,
            gscore: rating,
            userId: userId
        }, function (result) {
            if (result === "success") {
                alert("댓글이 등록되었습니다.");
                showReplyList(); // 댓글 목록 새로고침
            } else {
                alert("댓글 등록 실패");
            }
        });
    });



    // 댓글 목록을 보여주는 함수
    function showReplyList(page) {
    	const gno = new URLSearchParams(location.search).get('gno');
        const userNo = 2;

        rs.getList(gno, userNo, page, pageSize, function (data) {
            const replyUlMine = document.querySelector(".myChat");
            const replyUlall = document.querySelector(".allChat");
            replyUlMine.innerHTML = "";
            replyUlall.innerHTML = "";

            let msg = '';
            const myReply = data.myReply;

            if (myReply) {
                msg += `<li dataRno=${myReply.greplyNo} class="myComment">`;
                msg += `<div class="chatHeader">`;
                msg += `<div class="userRating"></div>`;
                msg += `<strong class="primaryFont">내 댓글: ${myReply.userId}</strong>`;
                msg += `<small class="pullRight">${displayTime(myReply.gupdateDate)}</small>`;
                msg += `<div class="kebabMenu">⋮</div>`;
                msg += `<div class="menuOptions">`;
                msg += `<button class="editBtn">수정하기</button>`;
                msg += `<button class="deleteBtn">삭제하기</button>`;
                msg += `</div></div>`;
                msg += `<p>${myReply.gcomment}</p>`;
                msg += `</li>`;
                replyUlMine.innerHTML = msg;
            }

            msg = '';
            const allReplies = data.replyList;
            allReplies.forEach(vo => {
                msg += `<li dataRno=${vo.greplyNo}>`;
                msg += `<div class="chatHeader">`;
                msg += `<div class="userRating"></div>`;
                msg += `<strong class="primaryFont">${vo.userId}</strong>`;
                msg += `<small class="pullRight">${displayTime(vo.gupdateDate)}</small>`;
                msg += `<p>${vo.gcomment}</p>`;
                msg += `</li>`;
            });

            replyUlall.innerHTML = msg;

            displayPagingButtons(data.totalReplies);
            
            // 페이지 버튼 생성
            function displayPagingButtons(totalReplies) {
                const totalPages = Math.ceil(totalReplies / pageSize);
                const paginationDiv = document.querySelector(".pagination");

                if (!paginationDiv) {
                    console.error("paginationDiv 요소를 찾을 수 없습니다.");
                    return; // paginationDiv가 없는 경우 함수를 종료하여 오류를 방지
                }

                paginationDiv.innerHTML = "";  // 기존 페이지 버튼 비우기

                for (let i = 1; i <= totalPages; i++) {
                    const pageBtn = document.createElement("button");
                    pageBtn.textContent = i;
                    pageBtn.classList.add("pageBtn");
                    if (i === currentPage) {
                        pageBtn.classList.add("active");
                    }
                    pageBtn.addEventListener("click", function () {
                        currentPage = i;
                        showReplyList(i);
                    });
                    paginationDiv.appendChild(pageBtn);
                }
            }


            if(document.querySelector('.kebabMenu') != null){
            document.querySelector('.kebabMenu').addEventListener('click', function () {
                const menu = this.nextElementSibling;
                if (menu.style.visibility === 'hidden' || menu.style.visibility === '') {
                    menu.style.visibility = 'visible';
                } else {
                    menu.style.visibility = 'hidden';
                }
            });
            }
            if (myReply) {
                const myUserRatingContainer1 = replyUlMine.querySelector('.myComment .userRating');
                displayStars(myReply.gscore, myUserRatingContainer1);
            }

            allReplies.forEach(vo => {
                const userRatingContainer = replyUlall.querySelector(`li[dataRno="${vo.greplyNo}"] .userRating`);
                displayStars(vo.gscore, userRatingContainer);
            });
            displayAvgStars();
        });
    }

    function displayTime(unixTimeStamp) {
        const myDate = new Date(unixTimeStamp);
        const y = myDate.getFullYear();
        const m = String(myDate.getMonth() + 1).padStart(2, '0');
        const d = String(myDate.getDate()).padStart(2, '0');
        return `${y}-${m}-${d}`;
    }
    
    function likeBtnChange() {
        const likeBtn = document.querySelector('#chkLike'); // 좋아요 버튼
        const gno = new URLSearchParams(location.search).get('gno');
        const userNo = 1;

        fetch('/goodsStore/chkLike/' + gno + '/' + userNo)
            .then(response => response.json())
            .then(result => {
                if (result == 0) {
                	likeIcon.src = "/resources/images/emptyHeart.png"
                } else if (result == 1) {
                	likeIcon.src = "/resources/images/fullHeart.png"
                }
            })
            .catch(err => console.error('Error:', err));
    }
    likeBtnChange();
    
    document.querySelector("#chkLike").addEventListener('click', () => {
    	const likeBtn = document.querySelector('#chkLike');
        const gno = new URLSearchParams(location.search).get('gno');
        const userNo = 1;

        fetch('/goodsStore/changeLike/' + gno + '/' + userNo)
            .then(response => response.json())
            .then(result => {
            	likeBtnChange();
                return fetch('/goodsStore/getLikeCount/' + gno);
            })
            .then(response => response.json())
            .then(likeCount => {
                document.querySelector("#goodsLike").textContent = "좋아요: " + likeCount + "회";
            })
            .catch(err => console.error('Error:', err));
    });
    
    document.addEventListener('click', (event) => {
        if (event.target && event.target.classList.contains('deleteBtn')) {
            const gno = new URLSearchParams(location.search).get('gno');
            const userNo = 1;  // 현재 로그인한 사용자 번호

            fetch('/gReply/deleteReply/' + gno + '/' + userNo,{
            	method:'delete'
            })
            .then(response => response.json())
            .then(result => {
                if (result == 0) {
                    alert("댓글 삭제 실패");
                } else if (result == 1) {
                    alert("댓글이 삭제되었습니다.");
                    showReplyList(); // 댓글 목록을 새로고침
                }
            })
            .catch(err => console.error('Error:', err));
        }
    });
    
    document.addEventListener('click', (event) => {
        if (event.target && event.target.classList.contains('editBtn')) {
            const commentLi = event.target.closest('li.myComment');  // 댓글 li 요소 찾기
            const commentP = commentLi.querySelector('p');           // 기존 댓글 텍스트 요소
            const userRatingDiv = commentLi.querySelector('.userRating');  // 별점 표시 요소

            const originalComment = commentP.textContent;  // 기존 댓글 내용 저장
            const originalRating = ratingInput.value;      // 기존 별점 저장

            // 댓글 텍스트를 input 필드로 변경
            const editInput = document.createElement('input');
            editInput.type = 'text';
            editInput.value = originalComment;
            editInput.classList.add('editCommentInput');  // input에 클래스 추가
            commentP.replaceWith(editInput);

            // 별점 선택란으로 변경
            const newRatingDiv = document.createElement('div');
            newRatingDiv.classList.add('editRating');
            let stars = '';
            for (let i = 1; i <= 5; i++) {
                stars += `<span data-value="${i}" style="color: ${i <= originalRating ? 'gold' : 'gray'};">★</span>`;
            }
            newRatingDiv.innerHTML = stars;
            userRatingDiv.replaceWith(newRatingDiv);

            // 수정 완료 및 수정 취소 버튼 추가
            const saveBtn = document.createElement('button');
            saveBtn.textContent = '수정 완료';
            saveBtn.classList.add('saveEditBtn', 'styledButton');  // CSS 클래스 추가

            const cancelBtn = document.createElement('button');
            cancelBtn.textContent = '수정 취소';
            cancelBtn.classList.add('cancelEditBtn', 'styledButton');  // CSS 클래스 추가

            commentLi.append(saveBtn, cancelBtn);

            // 별점 선택 기능 추가
            newRatingDiv.querySelectorAll('span').forEach((star, index) => {
                star.addEventListener('click', () => {
                    const rating = index + 1;
                    newRatingDiv.querySelectorAll('span').forEach((s, i) => {
                        s.style.color = i < rating ? 'gold' : 'gray';
                    });
                    ratingInput.value = rating;
                });
            });

            // 수정 취소 기능
            cancelBtn.addEventListener('click', () => {
                editInput.replaceWith(commentP);
                newRatingDiv.replaceWith(userRatingDiv);
                saveBtn.remove();
                cancelBtn.remove();
            });

            // 수정 완료 기능
            saveBtn.addEventListener('click', () => {
                const newComment = editInput.value;
                const newRating = ratingInput.value;

                fetch('/gReply/updateReply/', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        gno: new URLSearchParams(location.search).get('gno'),
                        userNo: 2,
                        gcomment: newComment,
                        gscore: newRating,
                    }),
                })
                    .then(response => response.text())
                    .then(result => {
                        if (result === "success") {
                            alert("댓글이 수정되었습니다.");
                            showReplyList();  // 댓글 목록 새로고침
                        } else {
                            alert("댓글 수정 실패");
                        }
                    })
                    .catch(err => console.error('Error:', err));
            });
        }
    });
    
});