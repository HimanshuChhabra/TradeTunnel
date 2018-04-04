$(document).ready(function(){
    //Load a HTTP Request GET for the data
    var request = new XMLHttpRequest();
    var url = "./assets/sample.json";

    request.onreadystatechange = function() {
        if (this.readyState == 4) {
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