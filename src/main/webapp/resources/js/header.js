const attendanceButton = document.querySelector('#attendance');

const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

attendanceButton.addEventListener('click', () => {
    
    event.preventDefault();
    
    $.ajax({
    url: '/attendance/attendance',
    type : 'POST',
      beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
	} ,
    success: function (result) {
       
       if(result=='gotowork'){
       console.log('출근완료');
       attendanceButton.textContent = '퇴근';
       }
       if(result=='getoffwork'){
       console.log('퇴근완료');
       attendanceButton.remove();
       }
       if(result=='maintainance'){
         alert("출석체크점검중");
       }
    },
    error: function (xhr, status, err) {
    console.log("실패");
    }
});
    
});