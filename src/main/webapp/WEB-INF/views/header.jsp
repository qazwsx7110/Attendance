<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<style>

.header{
   text-align: right;
   height: 60px;
}

#logoutbutton{
   margin-top:0px;
   border: 0;
   background-color: transparent;
    cursor: pointer;
}

#attendance{
   padding:auto;
   margin-top:0px;
   border: 0;
  background-color: transparent;
   cursor: pointer;
   
}

#butonbox{
display: flex;
justify-content: flex-end;
}

</style>

<sec:authentication property="principal.member.todayAttendance" var="todayAttendance"/>

</head>

<script src="/resources/js/jquery-3.7.1.min.js"></script>

<body>

<div class="header">

<p><sec:authentication property="principal.member.userName"/></p>

<div id="butonbox">

     <c:choose>
            <c:when test="${todayAttendance ==null||((todayAttendance.vacation=='반차(오전)'||todayAttendance.vacation=='반차(오후)')&&todayAttendance.state==null)}">
            
            <button id="attendance">출근</button>
           
           </c:when>
           
           <c:otherwise>
           
                   <c:choose>
                      
                          <c:when test="${(todayAttendance.vacation!='연차' )&&(todayAttendance.endTime ==null)}">
      
                          <button id="attendance">퇴근</button>
           
                           </c:when>
           
                           <c:otherwise>
           
                            </c:otherwise>
                      
                      </c:choose>
          
           </c:otherwise>
 
      </c:choose>

     <form action="/customLogout" method='post'>

     <input id="logoutform" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

     <button id="logoutbutton">로그아웃</button>

     </form>
</div>
</div>
<script src ="/resources/js/header.js"></script>
</body>
</html>