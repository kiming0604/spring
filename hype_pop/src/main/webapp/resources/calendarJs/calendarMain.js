document.addEventListener('DOMContentLoaded', function() {
    let currentMonth = new Date().getMonth(); 
    let currentYear = new Date().getFullYear(); 
    const monthNames = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];

    // 달력 업데이트 함수
    function updateCalendar() {
        const monthLabel = document.getElementById('currentMonth');
        monthLabel.textContent = `${currentYear}년 ${monthNames[currentMonth]}`;
        
        const calendarDays = document.getElementById('calendar-days');
        calendarDays.innerHTML = '<td>HYPEPOP</td>';
        const lastDateOfMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
        for (let i = 1; i <= lastDateOfMonth; i++) {
            calendarDays.insertAdjacentHTML('beforeend', `<td>${i}</td>`); 
        }

        const selectedCategories = Array.from(document.querySelectorAll('.category-checkbox:checked')).map(cb => cb.value);
        const noSelectedCat = selectedCategories.length === 0; 

        if (noSelectedCat && !document.getElementById('selectAll').checked) {
            // 전체보기 체크박스 해제 시 캘린더와 팝업 리스트 비우기
            document.getElementById('calendar-body').innerHTML = '';
            document.getElementById('popUpList').innerHTML = '';
            return; 
        }

        // 팝업스토어 정보 가져오기
        fetch(`/hypePop/calendarData?year=${currentYear}&month=${currentMonth + 1}`)
            .then(response => { 
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(storeData => {
                return fetch('/hypePop/categoryData').then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json().then(categoryData => {
                        return { storeData, categoryData }; 
                    });
                });
            })
            .then(({ storeData, categoryData }) => {
                const calendarBody = document.getElementById('calendar-body');
                const popUpList = document.getElementById('popUpList');  
                calendarBody.innerHTML = ''; 
                popUpList.innerHTML = ''; 

                // 필터링된 팝업스토어를 표시
                const filteredStores = noSelectedCat ? storeData : storeData.filter(store => {
                    const categories = categoryData.filter(category => category.psNo === store.psNo);
                    return selectedCategories.some(category => 
                        categories.length > 0 && categories[0][category] === 1
                    );
                });

                filteredStores.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `<td class="psName">${item.psName}</td>`;  

                    const startDate = new Date(item.psStartDate);
                    const endDate = new Date(item.psEndDate);
                    let schedule = false; 

                    for (let i = 1; i <= lastDateOfMonth; i++) {
                        const currentDate = new Date(currentYear, currentMonth, i);

                        if (currentDate.toDateString() === startDate.toDateString()) {
                            row.innerHTML += `<td class="schedule schedule-start">${new Date(item.psStartDate).toLocaleDateString()}</td>`;
                            schedule = true; 
                        } else if (currentDate.toDateString() === endDate.toDateString()) {
                            row.innerHTML += `<td class="schedule schedule-end">${new Date(item.psEndDate).toLocaleDateString()}</td>`;
                            schedule = true; 
                        } else if (currentDate > startDate && currentDate < endDate) {
                            row.innerHTML += `<td class="schedule schedule-1"></td>`;
                            schedule = true; 
                        } else {
                            row.innerHTML += `<td class="schedule"></td>`;
                        }
                    }

                    if (schedule) {
                        calendarBody.appendChild(row);
                        
                        const popUpItem = document.createElement('div');
                        popUpItem.classList.add('popUpItem');
                        popUpItem.innerHTML = 
                            `<div class="psImage"></div>
                            <div>
                                <span>${item.psName}</span>
                                <span>${item.psAddress}</span>
                                <span>${new Date(item.psStartDate).toLocaleDateString()} ~ ${new Date(item.psEndDate).toLocaleDateString()}</span>
                            </div>`;
                        
                        popUpItem.addEventListener('click', function() {
                            location.href = `/hypePop/popUpDetails?storeName=${item.psName}`;
                        });
                        
                        popUpList.appendChild(popUpItem);  
                    }
                });
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    // 체크박스 상태 저장 및 복원
    function saveCheckboxState() {
        const checkboxStates = {};
        checkboxes.forEach(checkbox => {
            checkboxStates[checkbox.value] = checkbox.checked;
        });
        localStorage.setItem('checkboxStates', JSON.stringify(checkboxStates));
    }

    function loadCheckboxState() {
        const checkboxStates = JSON.parse(localStorage.getItem('checkboxStates'));
        if (checkboxStates) {
            Object.keys(checkboxStates).forEach(key => {
                const checkbox = document.querySelector(`.category-checkbox[value="${key}"]`);
                if (checkbox) {
                    checkbox.checked = checkboxStates[key];
                }
            });
        } else {
            // 체크박스 상태가 없으면 모든 체크박스 선택
            checkboxes.forEach(checkbox => {
                checkbox.checked = true;
            });
            selectAllCheckbox.checked = true; // 전체 선택 체크박스도 체크
        }
    }

    // 체크박스 이벤트 리스너 추가
    const checkboxes = document.querySelectorAll('.category-checkbox');
    const selectAllCheckbox = document.getElementById('selectAll');


    // 전체보기 체크박스 상태 변경 시
    selectAllCheckbox.addEventListener('change', function() {
        const isChecked = this.checked;
        checkboxes.forEach(checkbox => {
            checkbox.checked = isChecked; // 전체보기 체크박스 상태에 따라 모두 체크하거나 해제
        });
        saveCheckboxState(); 
        updateCalendar(); 
    });

    // 개별 체크박스 상태 변경 시 전체보기 체크박스 상태 업데이트
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            const allChecked = Array.from(checkboxes).every(cb => cb.checked);
            selectAllCheckbox.checked = allChecked; 
            saveCheckboxState(); 
            updateCalendar(); 
        });
    });

    // 이전 달로 이동
    document.getElementById('prevMonth').addEventListener('click', function() {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        updateCalendar(); 
    });

    // 다음 달로 이동
    document.getElementById('nextMonth').addEventListener('click', function() {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        updateCalendar(); 
    });

    // 초기 화면 로드시 데이터 불러오기
    loadCheckboxState(); // 체크박스 상태 복원 및 초기화
    updateCalendar();
});
