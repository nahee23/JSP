<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Login System</title>
    </head>
 	<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
    <style>
            /* 화면 전체를 채우고 폼을 중앙 정렬 */
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh; /* 뷰포트 높이를 100% 사용 */
            }

            .card {
                width: 100%;
                max-width: 400px; /* 카드의 최대 너비 설정 */
            }
        </style>
    <body class="bg-dark">
    <jsp:include page="/Common/Link.jsp" />
	<!-- 공통 링크 -->
        <%
        String id=(String)session.getAttribute("id");
        
        //redirect user to home page if already logged in
        if(id!=null){
            response.sendRedirect("Main.jsp");
        }
 
        String status=request.getParameter("status");
        
        if(status!=null){
        	if(status.equals("false")){
        		   out.print("Incorrect login details!");	           		
        	}
        	else{
        		out.print("Some error occurred!");
        	}
        }
        %>
    
        <div class="container">
        	<form action="${pageContext.request.contextPath}/loginprocess" method="post"> 
       		<div class="card">
	        	<div class="card-header text-left font-weight-bold">
	        		Login
	        	</div>
	        	<div class="card-body">
	        		 
	                    <div class="form-group">
	                    	<input type="text" name="id" required class="form-control" placeholder="Enter id"/>
	                    </div>
	                	<div class="form-group">
	                		<input type="password" name="password" required class="form-control" placeholder="Enter password"/>
	                	</div>
	        		
	        	</div>
	        	<div class="card-footer text-left">
	        		<input type="submit" value="Login" class="btn btn-primary"/>
	        		<button class="btn btn-primary"
				onclick="window.location.href = 'views/employee-form.jsp'">
				회원 가입</button>
	        	</div>
       		</div>
        	</form>
        </div>
        
        <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
    </body>
</html>