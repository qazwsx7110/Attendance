<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

 #member{
 margin-top: 20px; 
 width: 800px;
 border: 1px solid black;
 height: 500px;
 background-color: white;
    
}

#addtable{
margin-left: 120px;

}

#seach{
margin-left: 120px;

}

 #memberadd{
  
margin-top: 1px; 
}

 #memberlist{
 margin-left: 280px;
border: 1px solid black;
margin-top: 20px; 

}
  #seachbutton{
 
margin-top: 1px; 
}
 

 #page{
 margin-top: 10px; 
 display: flex;
  justify-content: center; 
 }
 
 #page a{

 color: black;
 }
</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<head>
<input type="hidden" id="code" value=<sec:authentication property="principal.member.code"/>>
</head>
<body>
<header> <%@ include file="../header.jsp" %> </header>
<aside> <%@ include file="../nav.jsp" %> </aside>


<main> 
<form id='actionForm' action="/member/memberlistload" method='get'>

<input type='hidden' name='code' value='${code}'>
<input type='hidden' name='name' value='${name}'>
<input type='hidden' name='grade' value='${grade}'>
<input type='hidden' name='id' value='${id}'>

<input type='hidden' name='pageNum' value='${pageMaker.page.pageNum}'>
<input type='hidden' name='amount' value='${pageMaker.page.amount}'>
</form>

<div id="member">

<table id="addtable">
			<thead>
				<tr>
					<th>이름</th>
					<th>직급</th>
					<th>아이디</th>
					<th>비밀번호</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="insertmember">
			 <tr>
			           <td>
			               <input type="text" id="name">
			            </td>
			             <td>
			                 <select id="grade">   
			                 
                                   <c:forEach var="grade" items="${gradelist}">
                                      	<option value="${grade.gradeName}">${grade.gradeName}</option>
                                       </c:forEach>
                           </select>
			            </td>
			             <td>
			                <input type="text" id="id">
			            </td>
			             <td>
			               <input type="password" id="password">
			            </td>
			            <td>
			            <button id="memberadd">추가</button>
			            </td>
			            </tr>
			</tbody>
		</table>
		
<table id="seach">
			<thead>
				<tr>
					<th>이름</th>
					<th>직급</th>
					<th>아이디</th>
				
				</tr>
			</thead>
			<tbody id="seachtable">
			
			  <tr>
			           <td>
			               <input type="text" id="sname" value="${name}">
			            </td>
			             <td>
			                 <select id="sgrade">   
			                  
			                 <option value=""></option>
                             <c:forEach var="g" items="${gradelist}">
                                                                            
                            <option value="${g.gradeName}" ${g.gradeName == grade ? 'selected' : ''}>${g.gradeName}</option>
                                                                                                                                           
                             </c:forEach>
                                       
                           </select>
                           
			            </td>
			             <td>
			                <input type="text" id="sid" value="${id}">
			            </td>
			            
			            <td>
			            <button id="seachbutton">검색</button>
			            </td>
			            </tr>
			    
			            
			</tbody>
		</table>

<table id="memberlist">
			<thead>
				<tr>
					<th>이름</th>
					<th>직급</th>
					<th>아이디</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody id="membertable">
			
			 <c:forEach var="member" items="${memberList}">
			  <tr>
			            <td>
			               ${member.userName}
			            </td>
			             <td>
			                ${member.grade}
			            </td>
			             <td>
			                ${member.userId}
			            </td>
			             <td>
			             <fmt:formatDate value="${member.regDate}" pattern="yyyy-MM-dd" />
			             
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

</div>

 </main>
<footer> <%@ include file="../footer.jsp" %> </footer> 
<script src ="/resources/js/memberlist.js"></script>
</body>
</html>