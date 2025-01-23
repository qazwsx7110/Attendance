
 var actionForm = $("#actionForm");

const addButton = document.querySelector('#memberadd');
const prePageButton = document.querySelector('#paginate_button previous');
const nextPageButton = document.querySelector('#paginate_button next');

addButton.addEventListener('click', () => {

   let data = {
            code: $('#code').val(),
            userName:  $('#name').val(),
            grade:  $('#grade').val(),
            userId:  $('#id').val(),
            userPw:  $('#password').val()
        };
   
$.ajax({
    url: '/member/addmember',
    type : 'POST',
      data : JSON.stringify(data) ,
      contentType : "application/json;charset=utf-8"
      ,beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
	} ,
    success: function (result, status, xhr) {
       
        location.href="/member/memberlist";
       
    },
    error: function (xhr, status, err) {
    }
});


})


$("#seachbutton").on("click", function(e){
             
    e.preventDefault();  
    actionForm.find("input[name='name']").val($("#sname").val());
    actionForm.find("input[name='grade']").val($("#sgrade").val());
    actionForm.find("input[name='id']").val($("#sid").val());
    
    actionForm.find("input[name='pageNum']").val("1");
     actionForm.submit();  
});


$("#previouspaginate_button a").on("click", function(e){
              
    e.preventDefault();  
    actionForm.find("input[name='pageNum']").val($(this).attr("href"));
     actionForm.submit();   
});
 

$("#nextpaginate_button a").on("click", function(e){
              
    e.preventDefault();  
    actionForm.find("input[name='pageNum']").val($(this).attr("href"));
     actionForm.submit();   
});

$(".paginate_button a").on("click", function(e){

e.preventDefault();

actionForm.find("input[name='pageNum']").val($(this).attr("href"));
actionForm.submit();
});
