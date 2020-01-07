<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
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
<%
	Map<String, Object> userMap = (Map<String, Object>) request.getAttribute("user");
	if(userMap == null){
		userMap = new HashMap<String, Object>();
	}	
%>
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
	      	<li class="active"><a href="#"><%=userMap.get("nm") %></a></li>
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
	        <p><a href="/blog/Profile">Profile</a></p>
	        <img src="/blog/GetFile/<%= userMap.get("img")%>" class="img-circle" height="65" width="65" alt="Avatar">
	      </div>
	      <div class="well">
	        <p><a href="#">Interests</a></p>
	        <p>
<% 
		String interests = userMap.get("interests").toString();
		JSONArray jo = new JSONArray();
		if(!"".equals(interests)){
			jo = JSONArray.fromObject(interests);		
		}		
		String label = "label-default";
		for(int i = 0; i < jo.size(); i++) {
			switch(i % 6){
				case 1:
					label = "label-primary";
					break;
				case 2:
					label = "label-success";
					break;
				case 3:
					label = "label-info";
					break;
				case 4:
					label = "label-warning";
					break;
				case 5:
					label = "label-danger";
					break;
				default :
					label = "label-default";
					break;
			}
%>
	          <span class="label <%=label%>"><%=jo.getString(i) %></span>
<% 		}%>
	        </p>
	      </div>
	      <div class="well">
	        <p><a href="/blog/Message">Message</a></p>
	        <p>
	        	For the loser now<br>
	        	Will be later to win<br>
	        	For the times they are changing.
	        </p>
	      </div>
	    </div>
	    
	    <div class="col-sm-9">
	    	<div>
<% 
	List<Map<String, Object>> rows = (List<Map<String, Object>>) request.getAttribute("rows");
	if(rows != null) {
		for(int i = 0; i < rows.size(); i++) {
%>
				<div class="row">
				  <div class="col-sm-3">
				    <div class="well">
				     <p><%=rows.get(i).get("nm") %></p>
				     <img src="/blog/GetFile/<%= rows.get(i).get("img")%>" class="img-circle" height="55" width="55" alt="Java">
				    </div>
				  </div>
				  <div class="col-sm-9">
				    <div class="well">
				      <p><%=rows.get(i).get("desc") %></p>
<%-- 				      <textarea class="form-control txt-none font-default" rows="5" id="comment" name="comment" readonly="readonly"><%=rows.get(i).get("desc") %></textarea> --%>
				    </div>
				  </div>
				</div>
<%
		}
	}
%>
			</div>
	      
			<ul class="pagination">
<% 
	int size = Integer.parseInt(request.getAttribute("size").toString());
	int point = Integer.parseInt(request.getAttribute("point").toString());
	int paging = 1;
	if(request.getParameter("paging") != null) {
		paging = Integer.parseInt(request.getParameter("paging"));
	}
	int end = (point + 5);
	String active = "active";
	if(size < end) end = size+1;
		if(point > 1) {
%>
				<li class="previous"><a href="/blog/MyList?paging=<%=(point-5)%>">Previous</a></li>
<% 		}
		for(int i = point; i < end; i++) {
			if(paging == i){
				active = "active";
			} else {
				active = "";
			}
%>
			   	<li class="<%=active%>"><a href="/blog/MyList?paging=<%=i%>"><%=i%></a></li>
<% 		
		}
		if(size > end) {
%>
			   	<li class="next"><a href="/blog/MyList?paging=<%=(point+5)%>">Next</a></li>
<% 		}%>
			</ul>
		  
	    </div>
	    
	  </div>
	</div>
	
	<footer class="container-fluid text-center">
	  <p>Copyright ⓒ GooDee Academy. All rights reserved.</p>
	</footer>
</body>
</html>