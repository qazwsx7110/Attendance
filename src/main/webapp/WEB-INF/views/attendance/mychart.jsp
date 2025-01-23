<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<script src="/resources/js/jquery-3.7.1.min.js"></script>

<style>
</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>


<body>
<header> <%@ include file="../header.jsp" %> </header>
<aside> <%@ include file="../nav.jsp" %> </aside>
<main >
 
</main>

<footer> <%@ include file="../footer.jsp" %> </footer>
<script src ="/resources/js/mychart.js"></script>
</body>
</html>