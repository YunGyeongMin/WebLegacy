<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html oncontextmenu="return false">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Blog</title>
	<link rel="shortcut icon" type="image/x-icon" href="/img/icon_goodee.png">
	<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${initParam.bootstrap}css/bootstrap.min.css">
	<script src="${initParam.jquery}jquery.min.js"></script>
	<script src="${initParam.bootstrap}js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/blog/css/commons.css">
	<script src="/blog/js/commons.js"></script>
</head>
<body onload="sessionCheck()">
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
	      	<li><a href="/blog/Main">Home</a></li>
	      	<li><a href="/blog/MyList">고세민</a></li>
	      	<li class="active"><a href="#">Profile</a></li>
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
	      	<%HttpSession sess = request.getSession();%>
	        <li><a href="/blog/MyEdit"><span class="glyphicon glyphicon-user"></span> <%= sess.getAttribute("name") %></a></li>
	        <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
	        <li><a href="/blog/SignUp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      		<li><a href="/blog/Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	      </ul>
	    </div>
	  </div>
	</nav>
	  
	<div class="container text-center">    
	  <div class="row">
	  
		<div class="col-sm-3 well">
		  <div class="well">
	        <img id="myImage" src="/img/man.png" class="img-circle cursor-pointer" height="65" width="65" alt="Avatar">
	      </div>
	    </div>
	    
	    <div class="col-sm-9">
	    
			<form class="form-horizontal">
				<div class="form-group">
			    	<label class="control-label col-sm-2" for="name">Name:</label>
			    	<div class="col-sm-10">
			      		<input type="text" class="form-control" id="name" readonly="readonly" value="고세민">
			    	</div>
			  	</div>
				<div class="form-group">
			    	<label class="control-label col-sm-2" for="email">Email:</label>
			    	<div class="col-sm-10">
			      		<input type="email" class="form-control" id="email" readonly="readonly" value="gsm@goodee.co.kr">
			    	</div>
			  	</div>
			  	<div class="form-group">
			    	<label class="control-label col-sm-2" for="phone">Phone Cell:</label>
			    	<div class="col-sm-10">
			      		<input type="tel" class="form-control" id="phone" readonly="readonly" value="010-1234-5678">
			    	</div>
			  	</div>
			</form>
			<h2>Interests</h2>
			<div class="row">
			
				<div class="col-xs-6 col-sm-4 col-md-3">
					<div class="txt-box txt-active">
				        <h4>Java</h4>
			        </div>
				</div>
				
				<div class="col-xs-6 col-sm-4 col-md-3">
					<div class="txt-box txt-active">
				        <h4>WebService</h4>
			        </div>
				</div>
				
				<div class="col-xs-6 col-sm-4 col-md-3">
					<div class="txt-box txt-active">
				        <h4>DataBase</h4>
			        </div>
				</div>
				
				<div class="col-xs-6 col-sm-4 col-md-3">
					<div class="txt-box txt-active">
				        <h4>HTML</h4>
			        </div>
				</div>
				
				<div class="col-xs-6 col-sm-4 col-md-3">
					<div class="txt-box txt-active">
				        <h4>CSS</h4>
			        </div>
				</div>
				
				<div class="col-xs-6 col-sm-4 col-md-3">
					<div class="txt-box txt-active">
				        <h4>JavaScript</h4>
			        </div>
				</div>
			
			</div>
		  
	    </div>
	    
	  </div>
	</div>
	<br>
	<footer class="container-fluid text-center">
	  <p>Copyright ⓒ GooDee Academy. All rights reserved.</p>
	</footer>
</body>
</html>