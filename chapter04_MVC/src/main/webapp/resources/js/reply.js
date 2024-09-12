console.log("리플라이랑께요");


const replyService = (function() {
	
	// add 함수 정의
	function add(reply , callback) {
		// fetch를 사용하여 서버와 통신
		fetch('/reply/new' , 
				{
			  method : 'post',
			  body : JSON.stringify(reply),
			  headers: {
				    'Content-Type': 'application/json; charset=utf-8'
				}
			
		}
			
		)
			.then(response => response.text()) // 첫 번째 then 블록: 응답 처리
			.then(data => {
				callback(data);
			}) // 두 번째 then 블록: 데이터 처리
			.catch(err => console.log(err)); // catch 블록: 에러 처리
	}
	
	function getList(bno , callback) {
		fetch('/reply/pages/'+ bno + ".json")
			.then(response => response.json()) // 첫 번째 then 블록: 응답 처리
			.then(data => {
				callback(data);
			}) // 두 번째 then 블록: 데이터 처리
			.catch(err => console.log(err)); // catch 블록: 에러 처리
			
		
	}
	function remove(rno, callback) {
	    fetch('/reply/' + rno, { // URL에서 '.json' 제거
	        method: 'DELETE', // HTTP DELETE 메서드를 사용
	        headers: {
	            'Content-Type': 'text/plain' // 응답의 Content-Type에 맞추어 설정
	        }
	    })
	    .then(response => response.text()) // 서버 응답을 텍스트로 변환
	    .then(data => {
	        
	        if (data === 'success') {
	            callback({ success: true }); 
	        } else {
	            callback({ success: false }); 
	        }
	    })
	    .catch(err => {console.error(err); 
	    });
	}
	
	function update(rvo, callback) {
	    fetch('/reply/' + rvo.rno , {
	        method: 'PUT', // HTTP PUT 메서드를 사용
	        body: JSON.stringify(rvo), // 전송할 데이터를 JSON 형식으로 변환
	        headers: {
	            'Content-Type': 'application/json; charset=utf-8' // 올바른 Content-Type 설정
	        }
	    })
	    .then(response => response.text()) // 서버 응답을 텍스트로 변환
	    .then(data => {
	        if (data === 'success') { // 서버에서 성공적으로 처리하면 'success' 응답
	            callback({ success: true }); 
	        } else {
	            callback({ success: false }); 
	        }
	    })
	    .catch(err => {
	        console.error(err); // 오류 발생 시 콘솔에 출력
	    });
	}
	
	function get(rno , callback) {
		fetch('/reply/'+ rno + ".json")
			.then(response => response.json()) // 첫 번째 then 블록: 응답 처리
			.then(data => {
				callback(data);
			}) // 두 번째 then 블록: 데이터 처리
			.catch(err => console.log(err)); // catch 블록: 에러 처리
			
		
	}

	// 공개 API를 반환하여 외부에서 add 함수에 접근할 수 있도록 함
	return {
	// replyService.add를 부르면     여기 오른쪽 add라는 이름의 함수가 불러지는 구조
	                     add : add , // 외부에서 호출할 수 있는 add 함수 
	                     getList : getList,
	                     remove : remove,
	                     update : update,
	                     get : get
	};
})();


