
// 활성화된 탭을 추적하기 위한 변수
let activeTab = 'popUp';

// form 객체 가져오기
const f = document.forms[0];

// **** 관리자 Header 영역 (공통) ****
// 탭 클릭 시 해당 기능 활성화
document.getElementById('popUpManage').addEventListener('click', function() {
    activeTab = 'popUp'; // 현재 활성화된 탭 업데이트
    localStorage.setItem('activeTab', activeTab);  // 상태 저장
    activatePopUpTab(); // 팝업 관리 기능 활성화
    document.getElementById('registerBtn').style.visibility = 'visible'; // 등록 버튼 보이기
});
document.getElementById('storeManage').addEventListener('click', function() {
    activeTab = 'store'; // 현재 활성화된 탭 업데이트
    localStorage.setItem('activeTab', activeTab);  // 상태 저장
    activateStoreTab(); // 쇼핑몰 관리 기능 활성화
    document.getElementById('registerBtn').style.visibility = 'visible'; // 등록 버튼 보이기
});
document.getElementById('memberManage').addEventListener('click', function() {
    activeTab = 'member'; // 현재 활성화된 탭 업데이트
    localStorage.setItem('activeTab', activeTab);  // 상태 저장
    activateMemberTab(); // 회원 관리 기능 활성화
});

// **** 관리자 페이지 영역 ****
// **** 팝업스토어 영역 ****
// 팝업스토어 관리하기  활성화
function activatePopUpTab() {
    activeTab = 'popUp'; // 현재 활성화된 탭 업데이트
    fetch('/admin/psList/')	
        .then(response => {
            if (!response.ok) {
                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            PopUpStoreLists(data);
            console.log(data);
        })
        .catch(err => {
            console.log(err);
        });

    // 검색 기능 연결
    document.getElementById('searchBTN').onclick = searchStores;
}

// 팝업스토어 관리하기 버튼 클릭 시 팝업 스토어 리스트 영역 출력
function PopUpStoreLists(popUpStores) {
    const adminMain = document.querySelector('.adminMain');
    adminMain.innerHTML = '';

    if (popUpStores.length === 0) {
        adminMain.innerHTML = '<p>팝업 스토어가 없습니다.</p>';
        return;
    }

    const list = document.createElement('div');

    popUpStores.forEach(store => {
    	const psList = document.createElement('p');

    	// 팝업스토어 이름 클릭 시 링크로 이동
        const link = document.createElement('a');
        link.href = `popUpUpdate?psNo=${store.psNo}`;
        link.textContent = store.psName; // 팝업스토어 이름에만 링크 걸리게 설정
        link.style.color = 'black'; // 링크 색상 변경
        link.style.textDecoration = 'none'; // 밑줄 제거

        psList.appendChild(document.createTextNode(` ${store.psNo} `));
        psList.appendChild(link); // p 태그에 a 태그 추가
        psList.appendChild(document.createTextNode(` ${store.psStartDate} ${store.psEndDate}`)); // 나머지 텍스트 추가

        list.appendChild(psList);
    });

    adminMain.appendChild(list);
}

// 검색어에 따라 팝업 스토어 리스트 출력
function searchStores() {
    var searchText = document.getElementById('adminSearchBox').value;
    if (searchText) {
        if (activeTab === 'popUp') { // 현재 활성화된 탭이 팝업스토어일 경우
            fetch(`/admin/psList/?searchPs=${encodeURIComponent(searchText)}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('검색 결과가 출력되지 않았습니다: ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    PopUpStoreLists(data);
                    console.log(data);
                })
                .catch(err => {
                    console.log(err);
                });
        } else {
            alert('현재 활성화된 탭에서는 검색이 불가능합니다.');
        }
    } else {
        alert('검색어를 입력하세요.');
    }
}

//**** 쇼핑몰(상품) 영역 ****
// 쇼핑몰 관리하기 활성화
function activateStoreTab() {
    activeTab = 'store'; // 현재 활성화된 탭 업데이트
    fetch('/admin/gList/')
        .then(response => {
            if (!response.ok) {
                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            GoodsLists(data);
            console.log(data);
        })
        .catch(err => {
            console.log(err);
        });

    // 검색 기능 연결
    document.getElementById('searchBTN').onclick = searchGoods;
}

// 쇼핑몰 관리하기 버튼 클릭 시 상품 리스트 영역 출력
function GoodsLists(goods) {
	const adminMain = document.querySelector('.adminMain');
	adminMain.innerHTML = '';
	
	if (goods.length === 0) {
		adminMain.innerHTML = '<p>상품이 없습니다.</p>';
		return;
	}
	
	const list = document.createElement('div');
	
	goods.forEach(store => {
		const gsList = document.createElement('p');
		
		// 굿즈(상품) 이름 클릭 시 굿즈 정보 수정 페이지로 이동
        const link = document.createElement('a');
        link.href = `goodsUpdate?gNo=${store.gno}`;
        link.textContent = store.gname; // 굿즈(상품) 이름에만 링크 걸리게 설정
        link.style.color = 'black'; // 링크 색상 변경
        link.style.textDecoration = 'none'; // 밑줄 제거
       
        gsList.appendChild(document.createTextNode(` ${store.gno} `));
        gsList.appendChild(link); // p 태그에 a 태그 추가
        gsList.appendChild(document.createTextNode(`${store.sellDate} ${store.gprice}`));

        list.appendChild(gsList);
	});
	
	adminMain.appendChild(list);
}

// 검색어에 따라 상품 리스트 출력
function searchGoods() {
	var searchText = document.getElementById('adminSearchBox').value;
	if (searchText) {
		if (activeTab === 'store') { // 현재 활성화된 탭이 쇼핑몰일 경우
			fetch(`/admin/gList/?searchGs=${encodeURIComponent(searchText)}`)
			.then(response => {
				if (!response.ok) {
					throw new Error('검색 결과가 출력되지 않았습니다: ' + response.statusText);
				}
				return response.json();
			})
			.then(data => {
				GoodsLists(data);
				console.log(data);
			})
			.catch(err => {
				console.log(err);
			});
		} else {
			alert('현재 활성화된 탭에서는 검색이 불가능합니다.');
		}
	} else {
		alert('검색어를 입력하세요.');
	}
}

//**** 회원 영역 ****
// 회원 관리하기 활성화
function activateMemberTab() {
    activeTab = 'member'; // 현재 활성화된 탭 업데이트
    document.getElementById('registerBtn').style.visibility = 'hidden';  // 회원 관리 탭에서 등록 버튼 숨기기
    // 회원 관리 탭에서는 팝업스토어, 쇼핑 리스트 검색 불가능
    fetch('/admin/mList/')
	    .then(response => {
	    	if (!response.ok) {
	    		throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
	    	}
	    	return response.json();
	    })
	    .then(data => {
	    	MemberLists(data);
	    })
	    .catch(err => {
	    	console.error(err);
	    });
   
    // 검색 기능 연결
    document.getElementById('searchBTN').onclick = searchMembers;
}

// 회원 관리하기 버튼 클릭 시 회원 리스트 출력
function MemberLists(members) {
	console.log(members);
    const adminMain = document.querySelector('.adminMain');
    adminMain.innerHTML = '';

    if (members.length === 0) {
        adminMain.innerHTML = '<p>회원이 없습니다.</p>';
        return;
    }

    const list = document.createElement('div');

    members.forEach(member => {
        const mList = document.createElement('p');
       
    	// 회원아이디 클릭 시 회원 정보 수정 페이지로 이동
        const link = document.createElement('a');
        link.href = `memberUpdate?userId=${member.userNo}`;
        link.textContent = member.userId; // 회원 아이디에만 링크 걸리게 설정
        link.style.color = 'black'; // 링크 색상 변경	
        link.style.textDecoration = 'none'; // 밑줄 제거
       
        const formattedDate = member.lastLoginDate ?
        		new Date(member.lastLoginDate).toLocaleDateString('ko-KR') : '날짜 없음';
        		
        mList.appendChild(document.createTextNode(` ${member.userNo} `));
        mList.appendChild(link); // p 태그에 a 태그 추가
        mList.appendChild(document.createTextNode(` ${member.userEmail} ${formattedDate} ${member.enabled} ${member.auth}`));
       
        list.appendChild(mList);
    });

    adminMain.appendChild(list);
}

// 검색어에 따라 회원 리스트 출력
function searchMembers() {
    var searchText = document.getElementById('adminSearchBox').value;
    if (searchText) {
        if (activeTab === 'member') { // 현재 활성화된 탭이 팝업스토어일 경우
            fetch(`/admin/mList/?searchMs=${encodeURIComponent(searchText)}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('검색 결과가 출력되지 않았습니다: ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                	MemberLists(data);
                    console.log(data);
                })
                .catch(err => {
                    console.log(err);
                });
        } else {
            alert('현재 활성화된 탭에서는 검색이 불가능합니다.');
        }
    } else {
        alert('검색어를 입력하세요.');
    }
}

// 검색 버튼 클릭 이벤트 추가
document.getElementById('searchBTN').addEventListener('click', function() {
    if (activeTab === 'popUp') {
        searchStores();
    } else if (activeTab === 'store') {
        searchGoods();
    } else if (activeTab === 'member') {
    	searchMembers();
    }
});

//**** footer 영역 ****
// 문의 리스트 확인 버튼 클릭 시 문의 리스트 확인 페이지로 이동
// const로 변수 설정해서 한 이유는 관리자 페이지를 제외한 페이지에서는
// footer을 쓰지 않지만 admin.js는 전체 페이지에서 사용하므로 
// 변수 설정한 후 기본값이 null이므로 만약에 askList가 null이 아니면
// 즉, askList가 있으면 아래 코드 실행하라는 코드임 (footer 영역 전체에 적용)
const askList = document.querySelector('#askList');
if(askList != null){
	document.querySelector('#askList').addEventListener('click', function() {
		location.href = '/admin/askListCheck'; // JSP 페이지로 이동
	});
}

// 상품 상태 조회 버튼 클릭 시 상품 상태 조회 페이지로 이동
const goodsState = document.querySelector('#goodsState');
if(goodsState !== null){
	document.querySelector('#goodsState').addEventListener('click', checkGoodsState);
	
	function checkGoodsState() {
		window.location.href = '/admin/goodsState'; // JSP 페이지로 이동
	}	
}

// 등록하기 버튼 클릭 시 페이지 이동 함수
function goToPage(url) {
	window.location.href = url;
}

// 등록하기 버튼 클릭 시 이동
const registerBtn = document.querySelector('#registerBtn');
if(registerBtn != null){
	document.getElementById('registerBtn').addEventListener('click', function() {
		if (activeTab === 'popUp') {
			goToPage('/admin/popUpRegister'); // 팝업스토어 관리 탭에서 버튼 클릭 시 팝업 스토어 등록 페이지로 이동 (컨트롤러 경로)
		} else if (activeTab === 'store') {
			goToPage('/admin/goodsRegister'); // 쇼핑몰 관리 탭에서 버튼 클릭 시 상품(굿즈) 등록 페이지로 이동 (컨트롤러 경로)
		}	
	});	
}