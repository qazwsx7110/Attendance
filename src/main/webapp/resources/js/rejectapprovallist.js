
 var actionForm = $("#actionForm");
 
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
 