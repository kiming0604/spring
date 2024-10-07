document.querySelectorAll('button').forEach(a => {
    a.addEventListener('click', (event) => {
       
    	let buttonName = a.id;

        if (buttonName === "loginBtn") {
            
        	location.href = "/";
            }
      
    });
});

document.querySelectorAll('span').forEach(a => {
    a.addEventListener('click', (event) => {
       
    	let clickVal = a.id;

        if (clickVal === "searchId") {
        	
        	location.href = "/member/searchId";
        	
        }else if (clickVal === "searchPw") {
        	
        	location.href = "/member/searchPw";
        	
		}else if (clickVal === "signIn") {
			
			location.href = "/member/signIn";
			
		}else if (clickVal === "mainLogo") {
			location.href = "/";
		}
      
    });
});