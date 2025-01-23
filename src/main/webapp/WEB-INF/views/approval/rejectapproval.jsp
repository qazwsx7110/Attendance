<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.Date" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    display: flex;
  justify-content: center; 
}
#waitapproval{
 
margin-top: 20px; 
 width: 800px;
 border: 1px solid black;
 height: 500px;
 background-color: white;
 
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
 
#waitlist{
margin-top: 20px; 
margin-left: 120px;

}

#page{
margin-top: 10px; 
 display: flex;
  justify-content: center; 
 }
 
 td a{
 color: black;
 }
 
  #page a{

 color: black;
 }
</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>

<body>
<header> <%@ include file="../header.jsp" %> </header>
<aside> <%@ include file="../nav.jsp" %> </aside>

<form id='actionForm' action="/approval/rejectapprovalload" method='get'>

<input type='hidden' name='pageNum' value='${pageMaker.page.pageNum}'>
<input type='hidden' name='amount' value='${pageMaker.page.amount}'>
</form>

<main class="main"> 
<div id="waitapproval">

<table id="waitlist">
			<thead>
				<tr>
					<th>문서번호</th>
					<th>제출일</th>
					<th>제목</th>
					<th>제출자</th>
				</tr>
			</thead>
			<tbody id="waittable">
			
			 <c:forEach var="reject" items="${rejectApprovalList}">
			  <tr value="${reject.approvalNo}">
			            <td>
			               ${reject.approvalNo}
			            </td>
			             <td>
			             <fmt:formatDate value="${reject.reportingDate}" pattern="yyyy-MM-dd" />			               
			            </td>
			             <td>
			                <a href="/approval/viewrejectapproval?ano=${reject.approvalNo}">${reject.title}</a>
			            </td>
			             <td>
			             ${reject.writer}			             
			            </td>
                          </tr>            	
             </c:forEach>
			    
			            
			</tbody>
		</table>

        <div id='page'>
             
        <c:if test="${pageMaker.prev}">
        <button id="previouspaginate_button" ><a href="${pageMaker.startPage-1}">Previous</a></button>
        </c:if>
        
        
        <c:forEach var="num" begin="${pageMaker.startPage}"
        end="${pageMaker.endPage}">
        <button class="paginate_button"  value="${num}"><a href="${num}">${num}</a></button>
        </c:forEach>
        
        
         <c:if test="${pageMaker.next}">
        <button id="nextpaginate_button" ><a href="${pageMaker.endPage+1}">Next</a></button>
        </c:if>
      
        
        </div>
</main>
<footer> <%@ include file="../footer.jsp" %> </footer> 
<script src ="/resources/js/rejectapprovallist.js"></script>
</body>
</html>