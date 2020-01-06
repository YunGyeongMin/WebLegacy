<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html oncontextmenu="return false">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Blog</title>
	<link rel="shortcut icon" type="image/x-icon" href="/resources/img/icon_goodee.png">
	<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${initParam.bootstrap}css/bootstrap.min.css">
	<script src="${initParam.jquery}jquery.min.js"></script>
	<script src="${initParam.bootstrap}js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/resources/blog/css/commons.css">
	<script src="/resources/blog/js/commons.js"></script>
	<script>
	function previewEvent(input){
   		var reader = new FileReader();
        reader.onload = function (e) {
        	document.getElementById("preview").src = e.target.result;
        }
        reader.readAsDataURL(input.target.files[0]);
	}
    function imgEvent(){
    	var input = document.createElement("INPUT");
    	input.setAttribute("type", "file");
    	input.onchange = previewEvent;
    	input.click();
    }
    function btnEvent(){
    	var src = document.getElementById("preview").src;
    	document.getElementById("myImage").src = src;
    }
    var interests = [];
    function btnEvent2(target){
    	var check = true;
    	for(var i = 0; i < target.classList.length; i++){
    		if(target.classList[i] == "txt-active") {
    			check = false;
    			break;
    		}
    	}
    	if(check) {
    		target.classList.add("txt-active");
    	} else {
    		target.classList.remove("txt-active");
    	}
    	interController(target);
    }
    function interController(target){
    	var check = true;
    	var index = -1;
    	for(var i = 0; i < interests.length; i++){
    		if(interests[i] == target.innerText) {
    			check = false;
    			index = i;
    			break;
    		}
    	}
    	if(check) {
    		interests[interests.length] = target.innerText;
    	} else {
    		interests.splice(index, 1);
    	}
    }
    function update(){
    	var params = {
    		"name": document.getElementById("name").value,
    		"email": document.getElementById("email").value,
    		"phone": document.getElementById("phone").value,
    		"interests": ""
    	};    	
    	if(interests.length > 0) {
    		params.interests = JSON.stringify(interests);
    	}
    	var pwd = document.getElementById("pwd").value;
    	if(pwd != ""){
    		params.pwd = pwd;
    	}
    	console.log(params);
    	
    	$.ajax({
    	  type: "POST",
   		  url: "/blog/UserUpdate",
   		  data: params
   		}).done(function(a, b, c) {
   		    if(a > 0){
   		    	alert("+_+))b");
   		    	interestsView();
   		    } else {
   		    	alert("'ㅅ'))a");
   		    }
   		});
    	
    }
    function interestsView(){
    	var list = document.getElementsByClassName("txt-box");
    	for(var i = 0; i < list.length; i++){
    		for(var j = 0; j < interests.length; j++){
    			if(list[i].innerText == interests[j]){
    				list[i].classList.add("txt-active");
    				break;
    			}
    		}
    	}
    }
    function onload(){
    	interests = '${sessionScope.user.interests}';
    	if(interests == ""){
    		interests = [];
    	} else {
    		interests = JSON.parse(interests);
    	}
    	interestsView();
    }
	</script>
</head>
<body onload="sessionCheck(); onload();">
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">GDJ21</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	      	<li><a href="/blog/">Home</a></li>
	      	<li><a href="/blog/MyList">고세민</a></li>
	      	<li class="active"><a href="#">Edit</a></li>
	      </ul>
	      <form class="navbar-form navbar-right" role="search">
	        <div class="form-group input-group">
	          <input type="text" class="form-control" placeholder="검색..">
	          <span class="input-group-btn">
	            <button class="btn btn-default" type="button">
	              <span class="glyphicon glyphicon-search"></span>
	            </button>
	          </span>        
	        </div>
	      </form>
	      <ul class="nav navbar-nav navbar-right">
	        <li class="hidden"><a href="/blog/MyEdit"><span class="glyphicon glyphicon-user"></span> ${sessionScope.user.nm}</a></li>
	        <li class="hidden"><a href="javascript:logout()"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
	        <li class="hidden"><a href="/blog/SignUp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      		<li class="hidden"><a href="/blog/Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	      </ul>
	    </div>
	  </div>
	</nav>
	  
	<div class="container text-center">    
	  <div class="row">
	  
		<div class="col-sm-3 well">
		  <div class="well">
	        <img id="myImage" src="/resources/img/man.png" class="img-circle cursor-pointer" height="65" width="65" alt="Avatar" data-toggle="modal" data-target="#modal">
	      </div>
	    </div>
	    
	    <div class="col-sm-9">
			<form class="form-horizontal" onsubmit="return false;">
				<h2>Information</h2>
				<div class="form-group">
			    	<label class="control-label col-sm-2" for="name">Name:</label>
			    	<div class="col-sm-10">
			      		<input type="text" class="form-control" id="name" value="${sessionScope.user.nm}" required="required">
			    	</div>
			  	</div>
				<div class="form-group">
			    	<label class="control-label col-sm-2" for="email">Email:</label>
			    	<div class="col-sm-10">
			      		<input type="email" class="form-control" id="email" value="${sessionScope.user.email}" required="required">
			    	</div>
			  	</div>
			  	<div class="form-group">
			    	<label class="control-label col-sm-2" for="phone">Phone Cell:</label>
			    	<div class="col-sm-10">
			      		<input type="tel" class="form-control" id="phone" value="${sessionScope.user.phone}">
			    	</div>
			  	</div>
			  	<div class="form-group">
			    	<label class="control-label col-sm-2" for="pwd">Password:</label>
			    	<div class="col-sm-10">
			      		<input type="password" class="form-control" id="pwd" placeholder="신규 비밀번호를 입력하세요.">
			    	</div>
			  	</div>
			  	<div class="form-group txt-body">
				  	<h2>Interests</h2>
				  	<div class="row">
				  		<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>C</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>C++</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>C#</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>Java</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>WebService</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>DataBase</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>SQL</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>HTML</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>CSS</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>JavaScript</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>Python</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>Rube</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>Go</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>PHP</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>ASP</h4>
					        </div>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer">
							<div class="txt-box" onclick="btnEvent2(this)">
						        <h4>JSP</h4>
					        </div>
						</div>
					</div>
				</div>
			  	<div class="form-group button-body">
			      	<button type="submit" class="btn btn-success btn-block font-default" onclick="update()">수정</button>
				</div>
			</form>
		  
	    </div>
	    
	  </div>
	</div>
	<br>
	<footer class="container-fluid text-center">
	  <p>Copyright ⓒ GooDee Academy. All rights reserved.</p>
	</footer>
	
	<!-- Modal -->
	<div id="modal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Profile Image Edit</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	        	<div class="form-group text-center">
<!-- 	        		<input type="file" class="form-control" name="userImg" id="userImg" onchange="readURL(this)"> -->
<!-- 	        		<br> -->
	        		<img id="preview" src="/resources/img/man.png" alt="your image" class="img-circle cursor-pointer" height="65" width="65" onclick="imgEvent()"/>
	        	</div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary btn-block font-default" data-dismiss="modal" onclick="btnEvent()">적용</button>
	      </div>
	    </div>
	
	  </div>
	</div>
</body>
</html>