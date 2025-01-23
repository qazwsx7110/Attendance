<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> 
<!DOCTYPE html>
<html>
<head>
<style>

.nav{
   background-color: #1c4263; 
   width: 15%;
   height: 700px;
   float:left;
   display: flex;
   flex-direction: column;  
}

h3{
  margin-top: 40px;
  text-align: center;
  color:#FFFFFF;
}

 a {
  text-decoration: none;
   text-align: center;
   color:#FFFFFF;
}

</style>
</head>
<body>
<div class="nav">

<sec:authorize access="hasRole('ROLE_ADMIN')">
<h3>사원관리</h3>  
<a href="/member/memberlist">사원관리</a>
</sec:authorize>

<h3>휴가결제</h3>
 <a href="/approval/registerapproval">결제서작성</a>
  <a href="/approval/waitapproval">대기중인결제서</a>
   <a href="/approval/completeapproval">완료된결제서</a>
   <a href="/approval/rejectapproval">반려된결제서</a>
<h3>근태관리</h3>
 <a href="/attendance/myattendance">근태기록조회</a>
  <a href="/attendance/myworktime">근무기록조회</a>
   <a href="/attendance/myvacation">휴가관리</a>
    <!-- <a href="/attendance/mychart">통계</a>  -->  
 <!-- <button>자료실</button> -->  
</div>
</body>
</html>