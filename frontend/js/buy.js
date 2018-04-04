var openModal = function() {
        document.getElementById('modal').style.display = 'block';
        document.getElementById('fade').style.display = 'block';
};

var closeModal = function() {
        document.getElementById('modal').style.display = 'none';
        document.getElementById('fade').style.display = 'none';
};

openModal();

$(document).ready(function(){
    
    //Load a HTTP Request GET for the data
    var request = new XMLHttpRequest();
    var url = "./assets/sample.json";
    
    var sleep = function() {
        var x = 1;
        var y = null; // To keep under proper scope

        setTimeout(function() {
            x = x * 3 + 2;
            y = x / 2;
        }, 9999999);
    };
    
    for (j=0;j<99999;j=j+1) {
        sleep();
    }
    

    request.onreadystatechange = function() {
        if (this.readyState == 4) {
            closeModal();
            var data = JSON.parse(this.responseText);
            
            populateCards(data.products);
        }
    };
    request.open("GET", url, true);
    request.send();
    
    
    var populateCards = function(products) {
        var i = 0;
        var cardTitle = document.querySelectorAll(".card-title");
        var cardDescription = document.querySelectorAll(".card-description");
        var cardPrice = document.querySelectorAll(".price");
        var cardStatus = document.querySelectorAll(".status");
        
        for (i=0;i<products.length;i=i+1) {
            cardTitle[i].textContent = products[i].productName;
            cardDescription[i].textContent = products[i].productDescription;
            cardPrice[i].textContent = products[i].price;
            cardStatus[i].textContent = products[i].stat;
        }
    };
    
    
});