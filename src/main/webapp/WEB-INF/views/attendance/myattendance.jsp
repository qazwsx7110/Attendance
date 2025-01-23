<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<script src="/resources/js/jquery-3.7.1.min.js"></script>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
main{
  background-color:#f2f3f1;
   float:left;
    text-align: center;
    width: 85%;
   height: 700px;
 
}
#download {
    padding: 5px 10px; /* 내부 여백 */
    font-size: 16px; /* 글꼴 크기 */
    background-color: #007bff; /* 버튼 배경색 */
    color: white; /* 글자색 */    
    border-radius: 4px; /* 모서리를 둥글게 */
    cursor: pointer; /* 커서 모양 */   
      border: 1px solid black;
}

#calendar {
    border: 1px solid black;
    background-color:white;
     box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 테이블 그림자 */
    width:600px;
    margin-left: auto;
    margin-right: auto;
}

#calendar th {
    background-color: #007bff; /* 헤더 배경색 */
    color: white; /* 헤더 글자색 */
    text-transform: uppercase; /* 헤더 텍스트 대문자 */
}

.month{
	 padding: 5px 10px; /* 내부 여백 */
    font-size: 16px; /* 글꼴 크기 */
    background-color: #007bff; /* 버튼 배경색 */
    color: white; /* 글자색 */    
    border-radius: 4px; /* 모서리를 둥글게 */
    cursor: pointer; /* 커서 모양 */
	 border: 1px solid black;
	 
}

.action-bar {
    display: flex;
    justify-content: center; /* 버튼과 검색 폼을 양쪽 끝에 배치 */
    align-items: center; /* 버튼과 검색 폼의 높이를 맞춤 */
    margin-bottom: 10px; /* 아래쪽 여백 추가 */
     gap: 20px;
     margin-left: 200px;
}
#yearmonth {
    margin-top: 20px;
}

</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<input type='hidden' id='userid' value=<sec:authentication property="principal.member.userId"/>>
<input type='hidden' id='username' value=<sec:authentication property="principal.member.userName"/>>
<body onload="autoReload();">
<header> <%@ include file="../header.jsp" %> </header>
<aside> <%@ include file="../nav.jsp" %> </aside>
<main >
    <div class="action-bar">
	
	<button class="month" id="before"></button>
			
	<h2 id="yearmonth"></h2>
			
	<button class="month" id="next"></button>
				
    <button class="month" id="today" >오늘</button>
    
    <button id="download">다운로드</button>
	
    </div>
			
    
	<table id="calendar">		
		<tr>
		    <th width="14%"><font color="#ed5353">일</font></th>
			<th width="14%"> 월 </th>
			<th width="14%"> 화 </th>
			<th width="14%"> 수 </th>
			<th width="14%"> 목 </th>
			<th width="14%"> 금 </th>
			<th width="14%"><font color="#009de0">토</font></th>
			
		</tr>
	</table>
 
</main>

<footer> <%@ include file="../footer.jsp" %> </footer>
<script src ="/resources/js/myattendance.js"></script>
</body>
</html>