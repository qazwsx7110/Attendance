<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>

main{
  background-color:#f2f3f1;
   float:left;
    text-align: center;
    width: 85%;
   height: 700px;
}
</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<head>
<title>Insert title here</title>
</head>
<body>
<header> <%@ include file="header.jsp" %> </header>
<aside> <%@ include file="nav.jsp" %> </aside>
<main>
<h1>환영합니다!</h1>
 </main>
<footer> <%@ include file="footer.jsp" %> </footer> 
</body>
</html>