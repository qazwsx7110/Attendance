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

#divbox{
   margin-top:10px;
   margin-left: auto;
   margin-right: auto;

}

.divvacation{
	background-color:  white; /* 버튼 배경색 */
	border: 1px solid black;
	width:600px;
    margin-left: auto;
    margin-right: auto;
     padding:2px;
   
}

#download {
    padding: 5px 10px; /* 내부 여백 */
    font-size: 16px; /* 글꼴 크기 */
    background-color: #007bff; /* 버튼 배경색 */
    color: white; /* 글자색 */    
    border-radius: 4px; /* 모서리를 둥글게 */
    cursor: pointer; /* 커서 모양 */   
    border: 1px solid black;
    margin-top:10px;
    margin-left: auto;
    margin-right: auto;
}

#vacationtable {
    margin-top:10px;
    width:600px;
    background-color: white; /* 테이블 배경색 */
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 테이블 그림자 */
    border: 1px solid black;
}

#vacationtable th {
    background-color: #007bff; /* 헤더 배경색 */
    color: white; /* 헤더 글자색 */
    text-transform: uppercase; /* 헤더 텍스트 대문자 */
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
      <div  id="divbox" >
      <div  class="divvacation">
				<label>연차별 기록:</label> <select id="years" name="years">					
					<c:forEach var="year" items="${yearsList}">
					<option  value="${year.years}">${year.period}</option>
					 </c:forEach>
						
				</select>
			</div>
	 <div class="divvacation" >
	 <span>사용일수: </span><span id="used"></span>
	 <span>남은일수: </span><span id="remaining"></span>
	 <span>총휴가일수: </span><span id="vacation"></span>
	</div>
	</div>	
			<button id="download" >다운로드</button>
	<table align="center" id="vacationtable">
		<tr>
		    <th width="20%">일자</th>
			<th width="10%">휴가 유형</th>
			<th width="10%">휴가일수</th>
			<th width="17%">승인일자</th>
		</tr>
		
	</table>
</main>

<footer> <%@ include file="../footer.jsp" %> </footer>
<script src ="/resources/js/myvacation.js"></script>
</body>
</html>