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
<script src="/resources/js/signature_pad-master/signature_pad-master/docs/js/signature_pad.umd.min.js"></script>

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

#viewapploval{
   margin-top: 20px; 
 width: 800px;
 border: 1px solid black;
 height: 650px;
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
 
#reason{
height: 300px;
width: 420px;

}

#title{
width: 420px;

}

#submit{
 display: block; /* 블록 요소로 만들기 */
 margin: 0 auto; /* 수평 중앙 정렬 */
}

#resistertable{
margin-top:10px;
margin-left: 150px;
}

#gradetable{
width: 70px;
margin-left: 150px; 
margin-top:10px;
}

#gradelavel{
width: 20px;
}

#addline{
margin-bottom:10px;

}
#startdatetd{
padding:2px;

}

#buttondiv{
margin-top:10px;
display: flex;
justify-content: center; /* 수평 중앙 정렬 */
gap: 15px;
}
</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>


<body>

<header> <%@ include file="../header.jsp" %> </header>
<aside> <%@ include file="../nav.jsp" %> </aside>

<main > 
 
<div id="viewapploval" >
 
<c:if test="${ sign.size()-1 == viewapproval.turn}"> 

 <input type='hidden' id='complete' value='승인'>

  </c:if>

<table id="gradetable" style="table-layout: fixed">
              
               <tr>                                                             
          <c:forEach var="s" items="${sign}">
           <td style="width: 70px;">${s.userName}</td>                                                                                         
          </c:forEach>
               </tr>                                                                                                                       
               <tr >                                                             
           <c:forEach var="s" items="${sign}">
           <c:choose>
            <c:when test="${s.orderNo == s.turn}">  
            <input type='hidden' id='signnumber' value='${s.signNo}'>          
            <td style="width: 70px; height:80px; position: relative;"><div id="signature-pad" style="width: 100%; height: 100%;"><canvas style="width: 100%; height: 100%;"></canvas></div></td>

            </c:when>
            <c:otherwise>
             <c:choose>
            <c:when test="${s.signFileName != null}">
           <td style="width: 70px; height:80px;"><img src='/approval/viewsign?signname=${s.signFileName}' style="width: 70px; height:80px;"></td>
           </c:when>
           <c:otherwise>
           <td style="width: 70px; height:80px;"></td>
           </c:otherwise>
            </c:choose>
           </c:otherwise>
            </c:choose>
           </c:forEach>
               </tr>                                                                                                                       
</table>

<table id="resistertable">
			
			<tbody >
		
        <tr>
            <td>문서번호</td>
            <td>${viewapproval.approvalNo} </td>
            <td>작성일자</td>
            <td><fmt:formatDate value="${viewapproval.reportingDate}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>           
            <td>이름</td>
            <td>${viewapproval.writerName}</td>
            <td>직급</td>
            <td>${viewapproval.writerGrade}</td>
        </tr>
        <tr>
            
            <td>제목</td>
            <td colspan="3">${viewapproval.title}</td>
        </tr>
        <tr>
            
            <td>종류</td>
            <td>${viewapproval.vacation}</td>
            <td><fmt:formatDate value="${viewapproval.startDate}" pattern="yyyy-MM-dd" /></td>
            <td><c:if test="${viewapproval.vacation == '연차'}">     <fmt:formatDate value="${viewapproval.endDate}" pattern="yyyy-MM-dd" /></c:if>  </td>
            
        </tr>
         <tr >
          <td style="height: 300px;">신청사유</td>
            <td colspan="3" style="height: 300px;">${viewapproval.content}</td>
         </tr>
			</tbody>
		</table>
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div id=buttondiv>
		
		<button type="button"  data-action="signapproval">결제</button>           
		 <button type="button" data-action="rejectapproval">반려</button>
		</div>
		</div>
		
 </main>

<footer> <%@ include file="../footer.jsp" %> </footer>

<input type='hidden' id='writer' value='${viewapproval.writer}'>   

<input type='hidden' id='code' value='${viewapproval.code}'>   

<input type='hidden' id='approvalnumber' value='${viewapproval.approvalNo}'> 

<input type='hidden' id='vacation' value='${viewapproval.vacation}'> 

<input type='hidden' id='startdate' value='${viewapproval.startDate}'> 
  
<input type='hidden' id='enddate' value='${viewapproval.endDate}'>   
  
<script src ="/resources/js/viewapproval.js"></script>
</body>
</html>