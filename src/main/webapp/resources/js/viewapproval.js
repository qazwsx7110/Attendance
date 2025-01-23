
var canvas = $("#signature-pad canvas")[0];


  var sign = new SignaturePad(canvas, {       
     minWidth:1,          
  maxWidth: 1,       
     penColor: "rgb(33,33,33)"     
   });                
           
             function resizeCanvas(){   
         var canvas = $("#signature-pad canvas")[0];         
        var ratio =  Math.max(window.devicePixelRatio || 1, 1);       
     canvas.width = canvas.offsetWidth * ratio;      
      canvas.height = canvas.offsetHeight * ratio;   
         canvas.getContext("2d").scale(ratio, ratio);    
    }               
  $(window).on("resize", function(){       
     resizeCanvas();      
  });       
 resizeCanvas();


$("[data-action]").on("click", function(){      
      if ( $(this).data("action")=="signapproval" ){  
           if (sign.isEmpty()) {       
             alert("사인이 필요합니다.");   
            }
            else{
           console.log("제출")
            $.ajax({                   
             url : "/approval/approval",             
             method : "POST",                                 
            data : { 
             code : $('#code').val(),
             approvalnumber:  $('#approvalnumber').val(),                       
             sign : sign.toDataURL(),
             signnumber : $('#signnumber').val(),
             complete :  $('#complete').val(),
             vacation : $('#vacation').val(),
             startdate : $('#startdate').val(),
              enddate : $('#enddate').val(),
             writer: $('#writer').val()         
              }, beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
	          } ,                   
               success : function(result){   
                        console.log("제출성공");             
                        location.href="/approval/waitapproval";
             }        
              });   
            }
            
      }

 if ( $(this).data("action")=="rejectapproval" ){  
                  
           console.log("반려")
            $.ajax({                   
             url : "/approval/reject",             
             method : "POST",                                 
            data : { 
             code : $('#code').val(),
             approvalnumber:  $('#approvalnumber').val()
              }, beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
	          } ,                   
               success : function(result){   
                        console.log("반려성공");             
                        location.href="/approval/waitapproval";
             }        
              });   
                       
      }

});