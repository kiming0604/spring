document.querySelectorAll('button').forEach(a => {
    a.addEventListener('click', (event) => {
        let buttonName = a.id;

        if (buttonName === "purchaseBTN") {
            
        	location.href = "/goodsStore/goPurchase";
            }
      
    });
});