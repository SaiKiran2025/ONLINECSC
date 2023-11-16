

function checkImage(id,maxsize){	
	$("#"+id).prop('class','form-control');
	 
	var Receiptelement = document.getElementById(id);
	var Receiptname = Receiptelement.value;
	var allowedExtensions = /(\.jpg|\.jpeg)$/i;
    if (!allowedExtensions.exec(Receiptname)) {
    	Receiptelement.value = '';
        toastmsg("Upload jpg or jpeg Format only",'error');	
		$("#"+id).prop('class','form-control error');
		return false;
    }  
    var Receiptfile = Receiptelement.files;           
    var ReceiptSize = Receiptfile[0].size;    
    const ReceiptKb = ReceiptSize / 1024;
	if(ReceiptKb > maxsize){
		Receiptelement.value = '';
		toastmsg("Maximum File Size allowed 100KB only",'error');	
		$("#"+id).prop('class','form-control error');
		return false;
	} 		          
}


function checkPdf(id,maxsize,bytes){	
	$("#"+id).prop('class','form-control');
	 
	var Receiptelement = document.getElementById(id);
	var Receiptname = Receiptelement.value;
	var allowedExtensions = /(\.pdf)$/i;
    if (!allowedExtensions.exec(Receiptname)) {
        Receiptelement.value = '';
        toastmsg("Upload pdf Format only",'error');	
		$("#"+id).prop('class','form-control error');
		return false;
    }   
    var Receiptfile = Receiptelement.files;           
    var ReceiptSize = Receiptfile[0].size;   
    var Receiptbytes = 0;
     if(bytes == "kb"){
		Receiptbytes = ReceiptSize /1024;
	}
    if(bytes == "MB"){
		Receiptbytes = ReceiptSize /(1024*1024);
	}   

	if(Receiptbytes > maxsize){
		Receiptelement.value = '';
		toastmsg("File Size must be less than " + maxsize+ (bytes == "kb" ? "KB" : "MB"),'error');	
		$("#"+id).prop('class','form-control error');
		return false;
	} 		          
}

function finalSubmit(){
	if( $("#idproof_flag").val() == "Y"){
		if(!checkValidation("id_proof","Please upload ID proof Document",'error')){return false;}
	}
	if( $("#saledeed_flag").val() == "Y"){
		if(!checkValidation("registration_proof","Please upload Registration Document",'error')){return false;}
	}
	if( $("#occupancy_flag").val() == "Y"){
		if(!checkValidation("occupancy_cert","Please upload Occupancy Certificate Document",'error')){return false;}
	}
	if( $("#approval_plan_flag").val() == "Y"){
		if(!checkValidation("approval_plan","Please upload GHMC/Grampanchayath Approval Plan",'error')){return false;}
	}
	if( $("#ibond_flag").val() == "Y"){
		if(!checkValidation("indeminity_bond","Please upload Indemnity Bond Document",'error')){return false;}
	}
	document.docs.action="../saveDocs";		
	document.docs.method="POST";
	document.docs.enctype="multipart/form-data";
	document.docs.submit();
}