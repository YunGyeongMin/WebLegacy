function 비동기통신(url, type, func){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = func;
	xhttp.open(type, url, true);
	xhttp.send();
}

function login(){
	var id = document.getElementById("id1").value;
	var pwd = document.getElementById("pwd1").value;
	var url = "/blog/Login?id=" + id +"&pwd=" + pwd;
	var type = "POST";
	비동기통신(url, type, function() {
	    if (this.readyState == 4 && this.status == 200) {
	      if(this.responseText == 1){
	    	  location.href = "/blog/MyEdit";
	      }else {
	    	  alert("로그인 실패");
	    	  location.href = "/blog/Login";
	      }
	    }
	});
}

function logout(){
	var url = "/blog/Logout";
	var type = "POST";
	비동기통신(url, type, function() {
	    if (this.readyState == 4 && this.status == 200) {
	      if(this.responseText == 1){
	    	  var 부모 = document.getElementsByClassName("nav")[1].children;
	    	  부모[0].classList.add("hidden");
	    	  부모[1].classList.add("hidden");
	    	  
	    	  부모[2].classList.remove("hidden");
	    	  부모[3].classList.remove("hidden");
	      }
	    }
	});
}

function sessionCheck(){
	var url = "/blog/LoginCheck";
	var type = "POST";
	비동기통신(url, type, function() {
	    if (this.readyState == 4 && this.status == 200) {
	      var 부모 = document.getElementsByClassName("nav")[1].children;
	      if(this.responseText == 1){
	    	  부모[0].classList.remove("hidden");
	    	  부모[1].classList.remove("hidden");
	    	  
	    	  부모[2].classList.add("hidden");
	    	  부모[3].classList.add("hidden");
	      } else {
	    	  부모[0].classList.add("hidden");
	    	  부모[1].classList.add("hidden");
	    	  
	    	  부모[2].classList.remove("hidden");
	    	  부모[3].classList.remove("hidden");
	      }
	    }
	});
}