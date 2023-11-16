var headings = {
    //idproof_type: "ID Proof Type",
    idproof_no: "ID Proof Number",
    applicant_name: "Applicant Name",
    father_name: "Name of the Father/Wife/Husband",
};
/*
// Sample variables
var myKey = "fuel";
var myValue = "Petrol";

// Dynamically adding a key/value pair
myCar[myKey] = myValue;
console.log(myCar);
*/

function checkEmpty(inputID)
{
	let xinputID = '#'+inputID;
	$(xinputID).blur(function(){
		
		if($(this).val() == '' || $(this).val().length == 0)
		{
			//$(this).css('border','1px solid red');
			toastmsg("Please Enter "+headings[inputID]);	
			$(this).prop('class','form-control error');
			
			//$("#lan_id_no").html("Enter ID Number");
		}
		/*else if($(this).val().length<12){
			toastmsg("Aadhar Card Number should be 12 digits");	
			$(this).prop('class','form-control error');
		}*/
		else
		{
			//$(this).css('border','1px solid green');
			$(this).prop('class','form-control');
		}
	});
}

function checkValidation(id,message,status){
	if(document.getElementById(id).value.length==0 ) {	
		//alert("id"+document.getElementById(id).value.length);
		toastmsg(message,status);	
		document.getElementById(id).focus();
		return false;
	}else{
		return true;
	}
	
}

function toastmsg(msg,status)
{	
	var x;
	if(status=='error') {
		x = document.getElementById("snackbar");
		x.innerHTML = "<i class='fa fa-exclamation-circle mr-1 align-self-center'></i>"+msg;
	}	
	else {
		x = document.getElementById("snackbar1");
		x.innerHTML = "<i class='fa fa-check-circle mr-1 align-self-center'></i>"+msg;
	}
	//x.innerHTML = "<i class='fa fa-exclamation-circle mr-1 align-self-center'></i>"+msg;	
	x.style.display="block";
  	x.className = "show";
  	setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function checkLoad(){
	//alert($("#category").val());
	if($("#category").val() == 1 ){
		if($("#sub_category").val() == 0){
			if($("#conn_load").val() < 500 || $("#conn_load").val() > 56000){
				toastmsg("Please enter a load between 500 Watts to 56000 Watts",'error');	
				$("#conn_load").prop('class','form-control error');
			}
			else{
				$("#conn_load").prop('class','form-control');
			}		
		}
		if($("#sub_category").val() == 2){
			if($("#conn_load").val() < 250 || $("#conn_load").val() > 500){
				toastmsg("Please enter a load between 250 Watts to 500 Watts",'error');	
				$("#conn_load").prop('class','form-control error');
			}
			else{
				$("#conn_load").prop('class','form-control');
			}		
		}

	}
	if($("#category").val() == 3 || $("#category").val() == 5  ){
		if($("#sub_category").val() == 8  ){
			if($("#conn_load").val() < 1 || $("#conn_load").val() > 125){
				toastmsg("Please enter a load between 1 HP to 125 HP",'error');	
				$("#conn_load").prop('class','form-control error');
			}else{
				$("#conn_load").prop('class','form-control');
			}
		}
		else{
			if($("#conn_load").val() < 1 || $("#conn_load").val() > 99){
				toastmsg("Please enter a load between 1 HP to 99 HP",'error');	
				$("#conn_load").prop('class','form-control error');
			}else{
				$("#conn_load").prop('class','form-control');
			}
		}
	}
			
	if($("#category").val() == 4 ){
		if($("#conn_load").val() < 1 || $("#conn_load").val() > 15){
			toastmsg("Please enter a load between 1 HP to 15 HP",'error');	
			$("#conn_load").prop('class','form-control error');
		}else{
			$("#conn_load").prop('class','form-control');
		}
	}	
	
	if($("#category").val() == 6 ){
		if($("#sub_category").val()=="5" || $("#sub_category").val()=="6" || $("#sub_category").val()=="9" || $("#sub_category").val()=="12") {
			if($("#conn_load").val() < 1 || $("#conn_load").val() > 15){
				toastmsg("Please enter a load between 1 HP to 99 HP",'error');	
				$("#conn_load").prop('class','form-control error');
			}else{
				$("#conn_load").prop('class','form-control');
			}
		}
		else{
			if($("#conn_load").val() < 40 || $("#conn_load").val() > 56000){
				toastmsg("Please enter a load between 40 Watts to 56000 Watts",'error');	
				$("#conn_load").prop('class','form-control error');
			}else{
				$("#conn_load").prop('class','form-control');
			}
		}
		
	}	
}
