<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.Date" %>
<%
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 원하는 형식으로 포맷
%>
<!DOCTYPE html>
<html>
<script src="/resources/js/jquery-3.7.1.min.js"></script>

<style>

main{
  background-color:#f2f3f1;
   float:left;
    text-align: center;
    width: 85%;
   height: 700px;
    display: flex;
  justify-content: center; 
}

#approval{
 margin-top: 20px; 
 width: 800px;
 border: 1px solid black;
 height: 650px;
 background-color: white;
    
}

#registerapproval{
   margin-top:10px;      
}

table{
width: 500px;
}

td{
height: 30px;
}

table, td, th {
 border:1px solid gray; border-collapse:collapse;
 }
 
#reason{
height: 300px;
width: 420px;

}

#title{
width: 420px;

}

#submit{
 margin-top:10px;
}

#resistertable{
margin-top:10px;
margin-left: 150px;
}

#gradetable{
width: 100px;
 margin-left: 150px;
margin-top: 10px;
}

#gradelavel{
width: 20px;
}

#startdatetd{
padding:2px;
}

</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<head>

</head>
<body>

<header> <%@ include file="../header.jsp" %> </header>
<aside> <%@ include file="../nav.jsp" %> </aside>

<main > 

<div id="approval">

<form id="registerapproval" action="/approval/submitapproval" method='post'>
<input type="hidden" name="approvalhierarchyval"/>
<button id="addline" >결제라인추가</button>

<table id="gradetable">
               
         <tr id="gradetablemember">
            <td  rowspan="2" id="gradelavel">결제</td>
            <td > 
                
                <select name="approvalhierarchy">   
			    <option value=""></option>
                             <c:forEach var="aml" items="${ApprovalMemberList}">
                                                                            
                            <option value="${aml.userId}">${aml.userName} ${aml.grade}</option>
                                                                                                                                           
                             </c:forEach>              
                </select>
                </td>
            
        </tr>
        <tr id="gradetablesign">
            
            <td > </td>
           
            
        </tr>
</table>

<table id="resistertable">
			
			<tbody >
		
        <tr>
            <td>문서번호</td>
            <td> </td>
            <td>작성일자</td>
            <td><%= sdf.format(today) %> </td>
        </tr>
        <tr>           
            <td>이름</td>
            <td><sec:authentication property="principal.member.userName"/></td>
            <td>직급</td>
            <td><sec:authentication property="principal.member.grade"/></td>
        </tr>
        <tr>
            
            <td>제목</td>
            <td colspan="3"><input type = "text" id="title" name="title"></td>
        </tr>
        <tr>
            
            <td>종류</td>
            <td><select id="vacation" name="vacation">   
			    <option value="연차">연차</option>
                <option value="반차(오전)">반차(오전)</option> 
                <option value="반차(오후)">반차(오후)</option>                   
                </select></td>
            <td id="startdatetd"><input type="date" id="startdate" name="startDate"></td>
            <td id="enddatetd"><input type="date" id="enddate" name="endDate"></td>
            
        </tr>
         <tr>
          <td>신청사유</td>
            <td colspan="3"><textarea id="reason" name="content"></textarea></td>
         </tr>
			</tbody>
		</table>
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<button type="submit" id="submit" >제출</button>
		
		</form>
		
		</div>
 </main>

<footer> <%@ include file="../footer.jsp" %> </footer> 
<script src ="/resources/js/approval.js"></script>
</body>
</html>