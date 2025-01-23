const addlineButton = document.querySelector('#addline');

let line="";
let addlinecheck="false";

const tomorrow = new Date();
tomorrow.setDate(tomorrow.getDate() + 1); 
const yyyy = tomorrow.getFullYear();
const mm = String(tomorrow.getMonth() + 1).padStart(2, '0');
const dd = String(tomorrow.getDate()).padStart(2, '0');

const minDate = `${yyyy}-${mm}-${dd}`;
document.getElementById('startdate').min = minDate; 
document.getElementById('enddate').min = minDate; 

addlineButton.addEventListener('click', () => {
event.preventDefault();   
    console.log("결제자추가");
    if (addlinecheck=="false") {
     alert("선결제자를 정해주세요.");
    } else {
       
       $.ajax({
         url: '/approval/addline',
        type : 'POST',
         data : {
            line:line         
        },beforeSend : function(xhr){
	 	xhr.setRequestHeader(header, token);
	     } ,
         success: function (result, status, xhr) {
           console.log(result.length);
         if(result.length !== 0){  
         console.log(result);
         
       const newRow = document.getElementById('gradetablemember');
      const cell1 = document.createElement('td');
       newRow.appendChild(cell1);
       
       const select = document.createElement('select'); // Create a new select element
       select.setAttribute('name', 'approvalhierarchy');
       let options;
         
         options = result;
            
       const firstoption = document.createElement('option');     
        firstoption .textContent = "";
       firstoption .value = "";
       select.appendChild(firstoption);
            
        options.forEach(optionText => {
       const option = document.createElement('option');
       option.textContent = optionText.userName+" "+optionText.grade;
       option.value = optionText.userId;
       select.appendChild(option);
       });
       cell1.appendChild(select);
       
       const newRow2 = document.getElementById('gradetablesign');
       const cell2 = document.createElement('td');
       newRow2.appendChild(cell2);
       addlinecheck="false";
         }
          },
           error: function (xhr, status, err) {
           }
         });           
    }               
})

$(document).on("change", "select[name='approvalhierarchy']", function(e) {
        console.log("선택");
        if (e.target.value!=="") { 
       line=e.target.value;
       console.log(line);
        
         addlinecheck="true";
          $(this).find("option").not(":selected").attr("disabled", "disabled");
      } 
});

startdate.addEventListener('change', () => {
event.preventDefault(); 
enddate.value="";
 const cstartdate = new Date(startdate.value);
 const cstartday = cstartdate.getDay(); 
  
if(cstartday!==0 && cstartday!==6){
console.log("선택");


const endlimitday = new Date();
endlimitday.setDate(cstartdate.getDate()); 
const yyyy = endlimitday.getFullYear();
const mm = String(endlimitday.getMonth()+1);
const ddNextSaturday=endlimitday.getDate()+5-endlimitday.getDay();
const dd = String(ddNextSaturday);

const maxDate = `${yyyy}-${mm}-${dd}`;
console.log(maxDate);
document.getElementById('enddate').min = startdate.value; 
document.getElementById('enddate').max = maxDate; 

}
else{
alert("휴일선택불가");
startdate.value="";
}

if($("#vacation").val()!="연차"){
console.log("반차선택");
enddate.value=startdate.value;
}

})

enddate.addEventListener('change', () => {
    event.preventDefault();

    if (startdate.value != "") { //시작일 선택
        
        if (startdate.value < enddate.value) {
            const cenddate = new Date(enddate.value);
            const cendday = cenddate.getDay();
            console.log(cendday);
            const cstartdate = new Date(startdate.value);
            const cstartday = cstartdate.getDay();
            console.log(cstartday);
            if (cendday !== 0 && cendday !== 6) { //휴일아닐시
                console.log("선택");

            } else { //휴일선택
                alert("휴일선택불가");
                enddate.value = "";
            }
         }
         else{
         alert("시작일후부터 선택가능");
         enddate.value = "";
         }   
            
        }
     else { //시작일 미선택
        enddate.value = "";
        alert("시작일부터 선택해주세요.");
    }

})

document.getElementById('vacation').addEventListener('change', function() {
  const selectedValue = this.value;
 
  
  if(selectedValue=="연차"){
  console.log("연차");
  startdate.value="";
  enddate.value = "";
  document.getElementById("enddate").setAttribute("readonly", false);
  /*enddate.disabled = false;*/
  }
  if(selectedValue=="반차(오전)"){
  console.log("반차(오전)");
  startdate.value="";
  enddate.value = "";
  document.getElementById("enddate").setAttribute("readonly", true);
  /*enddate.disabled = true;*/
  }
  if(selectedValue=="반차(오후)"){
  console.log("반차(오후)");
   startdate.value="";
  enddate.value = "";
  document.getElementById("enddate").setAttribute("readonly", true);
   /*enddate.disabled = true;*/
  }
  
});
