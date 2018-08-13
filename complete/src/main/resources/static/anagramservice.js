function printAnagrams(){
	var textbox = document.getElementById('printAnagramsTxt');
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
        if (this.readyState == 4 ) {
            document.getElementById("resulttxt").innerHTML =
            this.responseText;
       }
    };
	xhr.open('GET', "http://localhost:8080/anagram/"+textbox.value, true);
	xhr.send();
	xhr.response();
}

function addWord(){
	var textbox = document.getElementById('addWordTxt');
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
        if (this.readyState == 4 ) {
            document.getElementById("resulttxt").innerHTML =
            this.responseText;
       }
    };
    xhr.open("POST", "http://localhost:8080/word");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify({word:textbox.value}));
	xhr.response();
}

function deleteWord(){
	var textbox = document.getElementById('deleteWordTxt');
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
        if (this.readyState == 4 ) {
            document.getElementById("resulttxt").innerHTML =
            this.responseText;
       }
    };
	xhr.open('DELETE', "http://localhost:8080/word/"+textbox.value, true);
	xhr.send();
	xhr.response();
}