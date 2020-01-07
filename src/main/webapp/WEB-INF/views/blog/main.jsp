<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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
	      	<li class="active"><a href="#">Home</a></li>
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
<% 
		List<Map<String, Object>> rows = (List<Map<String, Object>>) request.getAttribute("rows");
		for(int i = 0; i < rows.size(); i++){
%>
		  <div class="col-sm-3 col-xs-6 cursor-pointer" onclick="location.href = '/blog/MyList?user=<%=rows.get(i).get("no")%>';">
		    <div class="well">
		     <p><%=rows.get(i).get("nm") %></p>
		     <img src="/blog/GetFile/<%= rows.get(i).get("img")%>" class="img-circle" height="55" width="55" alt="Java">
		    </div>
		  </div>
<% 		}%>
		</div>
	  
    </div>
	    
	<footer class="container-fluid text-center">
	  <p>Copyright ⓒ GooDee Academy. All rights reserved.</p>
	</footer>
</body>
</html>