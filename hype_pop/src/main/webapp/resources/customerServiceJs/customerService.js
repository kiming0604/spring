let currentPage = 1;
let totalPages = 0; 

function fetchNotices(pageNum = 1, amount = 5) {
    currentPage = pageNum; 
    fetch(`/support/notices?pageNum=${pageNum}&amount=${amount}`)
        .then(response => response.json())
        .then(data => {
            const noticeList = document.querySelector('.announcement-list');
            noticeList.innerHTML = '';
            totalPages = Math.ceil(data.totalCount / amount); 

            if (data.notices.length === 0) {
                noticeList.innerHTML = '<p>공지 없음</p>';
            } else {
                data.notices.forEach(notice => {
                    const listItem = document.createElement('li');
                    listItem.innerHTML = `
                        <span class="noticeNumber">${notice.noticeNo}</span>
                        <a href="/support/noticeInfo?noticeNo=${notice.noticeNo}" class="noticeTitle">${notice.noticeTitle}</a>
                        <span class="noticeRegDate">${new Date(notice.noticeRegDate).toLocaleDateString()}</span>
                    `;
                    noticeList.appendChild(listItem);
                });
            }
            updateNoticePagination();
        })
        .catch(error => console.error('Fetch error:', error));
}

function fetchInquiries(pageNum = 1, amount = 5) {
    currentPage = pageNum; 
    fetch(`/support/inquiry?pageNum=${pageNum}&amount=${amount}`)
        .then(response => response.json())
        .then(data => {
            const inquiryList = document.querySelector('.inquiry-list');
            inquiryList.innerHTML = '';
            totalPages = Math.ceil(data.totalCount / amount); 

            if (data.inquiries.length === 0) {
                inquiryList.innerHTML = '<p>문의 없음</p>';
            } else {
                data.inquiries.forEach(inquiry => {
                    const listItem = document.createElement('li');
                    listItem.innerHTML = `
                        <span class="inquiryNumber">${inquiry.qnaNo}</span>
                        <a href="/support/inquiryInfo?qnaNo=${inquiry.qnaNo}" class="inquiryTitle">${inquiry.qnaTitle}</a>
                        <span class="inquiryRegDate">${new Date(inquiry.qnaRegDate).toLocaleDateString()}</span>
                    `;
                    inquiryList.appendChild(listItem);
                });
            }
            updateInquiryPagination();
        })
        .catch(error => console.error('Fetch error:', error));
}


function updateNoticePagination() {
    let pagination = document.querySelector('.notice-page-numbers');
    pagination.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.href = '#';
        pageLink.textContent = i;
        pageLink.classList.add('page-link');
        if (i === currentPage) {
            pageLink.classList.add('active');
        }
        pageLink.addEventListener('click', function(e) {
            e.preventDefault();
            fetchNotices(i);
        });
        pagination.appendChild(pageLink);
    }
}

function updateInquiryPagination() {
   let pagination = document.querySelector('.inquiry-page-numbers');
   pagination.innerHTML = '';
   
   for (let i = 1; i <= totalPages; i++) {
      const pageLink = document.createElement('a');
      pageLink.href = '#';
      pageLink.textContent = i;
      pageLink.classList.add('page-link');
      if (i === currentPage) {
         pageLink.classList.add('active');
      }
      pageLink.addEventListener('click', function(e) {
         e.preventDefault();
         fetchInquiries(i);
      });
      pagination.appendChild(pageLink);
   }
}

// 초기 데이터 로드
fetchNotices(1);



// 화면 전환
function switchTab(id) {
    // 모든 섹션 숨기기
    document.querySelectorAll('.content section').forEach(section => {
        section.classList.remove('active');
    });
    
    // 모든 탭 비활성화
    document.querySelectorAll('.tab div').forEach(tab => {
        tab.classList.remove('active');
    });
    
    // 선택한 탭과 해당 섹션 활성화
    document.getElementById(`section-${id}`).classList.add('active');
    document.getElementById(`tab-${id}`).classList.add('active');
    
    switch (id) {
        case 'announcement':
            fetchNotices(); // 공지사항 로드
            break;
        case 'inquiry':
            fetchInquiries(); // 1:1 문의 로드
            break;
    }
}

// 기본적으로 탭 전환
document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    const tab = params.get('tab') || 'announcement'; // 기본값을 'announcement'로 설정
    switchTab(tab);
});

// 공지사항 작성 페이지 이동 함수
function createNotice() {
    location.href = "/support/createNotice";
}

// 문의 작성 페이지 이동 함수
function createInquiry(){
    location.href = "/support/createInquiry";
}

// 검색 버튼 클릭 시 검색 함수 실행
document.getElementById('searchBtn').addEventListener('click', searchAnnouncements);

// 입력 필드에서 "Enter" 키를 누르면 검색 함수 실행
document.getElementById('search').addEventListener('keyup', (event) => {
    if (event.key === 'Enter') {
        searchAnnouncements();
    }
});

// 검색 함수
function searchAnnouncements() {
    // 검색 입력값 가져오기
    const search = document.getElementById('search').value.toLowerCase();
    
    // 공지사항 목록의 리스트 가져오기
    const noticetItems = document.querySelectorAll('.announcement-list li');

    // 각 아이템을 반복해서 검색어 비교
    noticetItems.forEach(item => {
        const title = item.querySelector('.noticeTitle').textContent.toLowerCase();
        
        // 제목에 검색어가 포함되어 있으면 표시하고 없으면 숨김
        if (title.includes(search)) {
            item.style.display = 'flex'; // 표시
        } else {
            item.style.display = 'none'; // 숨김
        }
    });
}

//let pageNum = new URLSearchParams(location.search).get('pageNum');
//let amount = new URLSearchParams(location.search).get('amount');
//
//if (!pageNum || !amount) {
//    pageNum = 1;
//    amount = 5;
//}
//
//// 페이지 링크 클릭 이벤트
//document.querySelectorAll(".page-nation li a").forEach(a => {
//    a.addEventListener('click', e => {
//        e.preventDefault();
//        
//        pageNum = a.getAttribute('href');
//        let sendData = `pageNum=${pageNum}&amount=${amount}`;
//        location.href = `/hypePop/customerMain?${sendData}`;
//    });
//}); 