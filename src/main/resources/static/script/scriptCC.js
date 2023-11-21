
var otp;
function getServiceDetailsInitial(){
	var ukscno = document.getElementById("uscno_initial").value;
	if(ukscno.length == 0){
		$("#uscno_initial").focus();
	}
	var scope = "initial";
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 const obj = JSON.parse(this.responseText); 
                 if(obj.valid == "Y"){
					 $("#name_initial_div").show();
					 $("#alert_initial").hide();
					 $("#mobileno").attr('disabled', false);
					 document.getElementById("name_initial").innerHTML = obj.name;
					 document.getElementById("usc_valid").value = obj.name;
				 }else{
					$("#name_initial_div").hide();
					$("#alert_initial").show();
					document.getElementById("usc_valid").value = "";
				}                 
             }
                
        };
        xhttp.open("GET", "getService/"+ukscno+"/"+scope, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send();
}


function hide(){
	const cnature = [41,42,47];
	let i = 0;
	while (i < cnature.length) {
	    $("#"+"form-container"+cnature[i]).hide();
	    i++;
	}
}

function showComptype(){
	var cnature = document.getElementById("cnature").value;
	if(cnature != ""){
		$("#optionsdiv").hide();
		$("#homediv").show();

		if(cnature == "40" || cnature == "42" || cnature == "69"){
			$("#gstn_flag").val('N');
			$("#gstn_flag").click();
			$("#gstno").attr('disabled', true);
			$("#cnfmgstno").attr('disabled', true);
		}
		if(cnature == "40" || cnature == "69" || cnature == "79"){
			if(getMetric() == "HP")
				$("#lan_req_load").html('Additional Load Required in HP');
		}
		if(cnature == "42"){
			 showCatChange();
		}
		
		if(cnature == "69"){
			document.getElementById("additional_load").disabled = true;		
			$("#form_content").hide();
		}
		if(cnature == "79"){
			$("#form_content").addClass("justify-content-center");
			document.getElementById("lan_req_load").innerHTML = "Derated Load";				
		}
		if(cnature == "73"){
			document.getElementById("pay73").style.display = "block";
		}
		if(cnature == "85" || cnature == "86"){
			document.getElementById("transaction_fail").style.display = "block";
		}
		
	}
}

function showOptions(){
	$("#optionsdiv").show();
	$("#homediv").hide();
}

function loadFormContainer(cnature){   //this method accessed from options.html
	document.adl.cnature.value = cnature;
	var catcode = document.adl.xcatcode.value
	if(cnature == "47" && catcode == "8"){
		alert("Title Transfer cannot be done on Temporary Service Connection");
		$("#form_content").hide();
		return false;
	}
	document.adl.action="showCC";
	document.adl.enctype="multipart/form-data";	
	document.adl.method="POST";
	document.adl.submit();
}

function showCatChange(){
	var exist_load = document.getElementById("xload").value;
	var exist_catcode = document.getElementById("xcatcode").value;
	var metric = " Watts";
	if(exist_catcode == "3" || exist_catcode == "4" || exist_catcode == "5"){
		metric = " HP";
	}

	var exist_cat = document.getElementById("xcategory").value;
	document.getElementById("new_cat").innerHTML = "<option value=''></option>";
	var xhttp = new XMLHttpRequest();		
	xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				document.getElementById("exist_cat").value = exist_cat;
				document.getElementById("exist_load").value = exist_load + metric;
				const json = JSON.parse(this.responseText);
				var new_cat = document.getElementById("new_cat");
				for (var i in json){
					new_cat.innerHTML += "<option value='" + json[i].catid + "'>" + json[i].catdesc + "</option>";
				}	
			}				
	};
	xhttp.open("GET", "getCatChange/"+exist_catcode, true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
	
}

function afterCatChange(){
	var new_cat = document.getElementById("new_cat").value;
	var ukscno = document.getElementById("xukscno").value;
	var metric = " Watts";
	if(new_cat == "3" || new_cat == "4" || new_cat == "5"){
		metric = " HP";
	}
	var xhttp = new XMLHttpRequest();		
	xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				json = JSON.parse(this.responseText);
				document.getElementById("af1").innerHTML = "&#8377; "+json.af_pre;
				document.getElementById("afgst1").innerHTML = "&#8377; "+json.afgst_pre;
				document.getElementById("sd1").innerHTML = "&#8377; "+json.sd_pre;
				document.getElementById("dc1").innerHTML = "&#8377; "+json.dc_pre;
				document.getElementById("dcgst1").innerHTML = "&#8377; "+json.dcgst_pre;
				document.getElementById("tot1").innerHTML = "&#8377; "+json.tot_pre;	
				
				document.getElementById("new_load").value = parseInt(json.newload) + metric;
			}
				
	};
	xhttp.open("GET", "getCatChangePayments/"+new_cat+"/"+ukscno, true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getPayments(){
	 var ukscno = document.getElementById("xukscno").value;
	 var extension ;
	 var exist_load = parseInt(document.getElementById("xload").value);
	 var addl_load = parseInt(document.getElementById("additional_load").value);	 	 
	 var cnature = document.getElementById("cnature").value;
	 $("#total_load").val('');	 
	 if(getMetric() == "Watts"){
		 if(addl_load < 1000){
			toastmsg("Please enter minimum 1000 Watss",'error');	
			$("#additional_load").focus();
			return false;
		 }
 	 }
 	  if(getMetric() == "HP"){
		 if(addl_load < 1){
			toastmsg("Please enter minimum 1 HP",'error');	
			$("#additional_load").focus();
			return false;
		 }
 	 }
 	 if(cnature == "40"){
		tot_load = addl_load + exist_load;
		document.adl.total_load.value = tot_load;
	 }
	 /*if(cnature == "40"){
		tot_load = addl_load + exist_load;
		document.adl.total_load.value = tot_load;
		if(tot_load > 15000)
			alert("For total load more than 15000 Watts, Security Deposit and Development Charges will be collected at the time of estimate payment");	
      }*/
     if(cnature == "40" || cnature == "69"){
     	 extension = document.getElementById("extension").value
     }
     if(cnature == "69"){ //clubbing
		 setClubbedLoad();
	 }
	 if(cnature == "79"){ //load deration
	 	checkLoadDeration(addl_load, exist_load);
	 	return false;
	 }
	if(extension == 'Y' && $("#is_adl_req").val() !== "N"){		
		$("#est_type").attr('disabled', false);
		toastmsg("Security Deposit and Development Charges will be collected at the time of Estimate Payment",'success');	
	}
	if(extension == 'N'){
		$("#est_type").val('');
		$("#est_type").attr('disabled', true);
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			json = JSON.parse(this.responseText);
			document.getElementById("af1").innerHTML = "&#8377; "+json.af_pre;
			document.getElementById("afgst1").innerHTML = "&#8377; "+json.afgst_pre;
			document.getElementById("sd1").innerHTML = "&#8377; "+json.sd_pre;
			document.getElementById("dc1").innerHTML = "&#8377; "+json.dc_pre;
			document.getElementById("dcgst1").innerHTML = "&#8377; "+json.dcgst_pre;
			document.getElementById("tot1").innerHTML = "&#8377; "+json.tot_pre;													
		}
	};
	xhttp.open("POST","getPreReqAdl",true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ukscno:ukscno, req_load_prereq:addl_load, extension:extension}));
}




function getServiceDetails2(){
	var ukscno = document.getElementById("uscno").value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 const obj = JSON.parse(this.responseText); 
                 if(obj.valid == "Y"){
					 $("#alert").hide();
					 document.getElementById("cons_name").value = obj.name;
	                 document.getElementById("category").value = obj.category;
	                 document.getElementById("exsisting_load").value = obj.load;	
	                 document.getElementById("section_name").value = obj.section_name;	
	                 document.getElementById("conn_address1").value = obj.addr1;	
	                 document.getElementById("conn_address2").value = obj.addr2;	
	                 document.getElementById("conn_address3").value = obj.addr3;	
	                 document.getElementById("conn_address4").value = obj.addr4;	
	                 document.getElementById("conn_pincode").value = obj.pincode;	
	                 if(obj.catcode == "3" || obj.catcode == "5")
	                 	document.getElementById("metric").innerHTML = "Additional Load Required in HP";	
				 }else{
					$("#alert").show();
				}
                 
                 }
                
        };
        xhttp.open("GET", "getService/"+ukscno, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send();
}


function changeIcon(inputID)
{	
	//alert("hi");
	let xinputID = '#'+inputID;
	$(xinputID).html("<i class='fa fa-search'></i>");
}


function estimateReq(){
	if(document.getElementById("extension").value.length==0 ) { bounce("extension"); return false;}
	if($("#extension").val() == 'Y' ){
		toastmsg("Security Deposit and Development Charges will be collected during Estimate Payment",'error');
	}
	getReqPayment(1);
}




function gstnYes(){
	var gstn_flag = document.getElementById("gstn_flag").value;
	if(gstn_flag == "Y"){
		$("#gstno").removeAttr("disabled");
    	$("#cnfmgstno").removeAttr("disabled");
    }else{
		$("#gstno").val('');
        $("#cnfmgstno").val('');
		$("#gstno").attr("disabled", "disabled"); 
        $("#cnfmgstno").attr("disabled", "disabled");
	}	
}

function verifygst(){
	$("#is_gstverified").val("");
	$("#gstno").prop("style","background:url(http://www.xiconeditor.com/image/icons/loading.gif) no-repeat right center");
	var gstno = document.getElementById("gstno").value;
	var result;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			result = this.responseText;
			if(result.includes("Error")){
				$("#is_gstverified").val("no");
				$("#cnfmgstno").val("");
				if(result=="Error:Invalid GSTIN / UID"){
					toastmsg("Invalid GST No.",'error');
					//$("#gstno").val("");	
					$("#gstno").focus();
	       		}
	       		else if(result=="Error:Inactive GSTN"){
					toastmsg("Inactive GST No.",'error');
					$("#gstno").focus();
				}
				else{
					toastmsg(result,'error');
					$("#gstno").focus();
				}
	     	}
	     	else if(result.includes("Failed")){
				$("#is_gstverified").val("error");
	     		toastmsg(result,'error');
	     		$("#gstno").focus();
	     	}
       	 	else {
        		toastmsg("GST No. Verified Successfully",'success');
        		$("#is_gstverified").val("yes");
        		$("#cnfmgstno").focus();
         	}
         	$("#gstno").removeAttr("style");																				
		}
	};
	xhttp.open("GET", "verifyGSTNO/"+gstno, true);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send();
}
function confirmgst(){
	if($("#gstno").val()!=$("#cnfmgstno").val()){
		toastmsg("Please Confirm GST No.",'error');	
		$("#cnfmgstno").focus();
	}
}
//OTP SCRIPT START
function vaildateNum(num){
    num.value = num.value.replace(/[^0-9]/g,'');
}

function shiftInput1(index){
    let elem = document.querySelectorAll(".input_otp");          
    if(elem[index-1].value != ''){
        (index==4) ? document.getElementById("verify-btn1").focus() : elem[index].focus();               
    }else if(elem[index-1].value == ''){
        elem[index-2].focus();
    } 
}

function shiftInput2(index){
    let elem = document.querySelectorAll(".input_otp_reg");           
    if(elem[index-1].value != ''){
        (index==4) ? document.getElementById("verify-btn2").focus() : elem[index].focus();  
    }else if(elem[index-1].value == ''){
        elem[index-2].focus();
    } 
}
//OTP SCRIPT END


function sendOTP(){
	let mobileValue = $("#mobileno").val();
	var xmlHttp = new  XMLHttpRequest();
	if (xmlHttp==null)
	{
		alert ("Browser does not support HTTP Request")
		return
	}
	    		
	xmlHttp.onreadystatechange=function(){
    	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
    	{		
			otp=xmlHttp.responseText;	
			if(otp != ""){
				$("#mobilecheck").html("");
	       		$("#loginPanel").hide();
	       		$("#error_otp").hide();
	       		$("#otpPanel").show();
	       		$("#otp1").focus();
    			timer_type = 1;
				timer11();	 
				   	    				    	    	
    	        /*document.getElementById("overlay_otp_service").style.display = "none";*/							
			}	   			
    	}
    	/*if(this.status == 400)
         {
			document.f1.action="ErrorPage";
			document.f1.submit();
    		return true;
		 }*/
	    				    			
	};
	xmlHttp.open("GET", "getOTP/"+mobileValue, true);
	xmlHttp.setRequestHeader("Content-type", "application/json");
	xmlHttp.send();
}

function validateOTP(){
	var cotp;
	var otp1 = $("#otp1").val();
	var otp2 = $("#otp2").val();
	var otp3 = $("#otp3").val();
	var otp4 = $("#otp4").val();
	cotp = otp1 + otp2 + otp3 + otp4;
	if(cotp == otp){
		document.loginForm.action="regCC";
		document.loginForm.method="post";
		document.loginForm.submit();
	}	
	else{
		$("#error_otp").show();
    	setTimeout(function() {
			$("#error_otp").hide();
		}, 3000);		
	}        
}

function proceedReg(){
	$("#loginPanel").show();
	$("#preqPanel").show(); 
	/*document.loginForm.action="newreg";
	document.loginForm.method="post";
	document.loginForm.submit();*/
}

function generateBox(){
    let count = document.getElementById("existcount").value;
    if(count==0){
        for(i=1;i<=4;i++){
            document.getElementById("uksc-div"+i).style.display = "none";
        }
    }
    if(count>0){
        for(i=1;i<=count;i++){  
            document.getElementById("uksc-div"+i).style.display = "block";
            document.getElementById("uksc-label"+i).innerHTML = "Unique Service No."+i;
            document.getElementById("uksc1").focus();
        }
    } 
}


function reqConnCheck()
{
	if( parseInt($("#tot_req_connections").val()) !=  parseInt($("#tot_dom_connections").val()) +  parseInt($("#tot_com_connections").val())){
			toastmsg('No of required Connections must be equal to sum of new domestic connections and new commercial connections','error');
			$("#tot_req_connections").focus(); 
			return false;
		}
}

function checkFile1(id){	
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
	if(ReceiptKb > 100){
		Receiptelement.value = '';
		toastmsg("Maximum allowed File Size is 100KB only",'error');	
		$("#"+id).prop('class','form-control error');
		return false;
	} 		          
}

function checkFile2(id){	
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
    const ReceiptMb = ReceiptSize /(1024*1024);
		if(ReceiptMb > 5){
			Receiptelement.value = '';
			toastmsg("Maximum allowed File Size is 5MB only",'error');	
			$("#"+id).prop('class','form-control error');
			return false;
		} 	         
}
function checkFile3(id){	
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
    const ReceiptKb = ReceiptSize / 1024;
	if(ReceiptKb > 500){
		Receiptelement.value = '';
		toastmsg("Maximum allowed File Size is 500KB only",'error');	
		$("#"+id).prop('class','form-control error');
		return false;
	}  		          
}

function checkFile4(id){	
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
	if(ReceiptKb > 500){
		Receiptelement.value = '';
		toastmsg("Maximum allowed File Size is 500KB only",'error');	
		$("#"+id).prop('class','form-control error');
		return false;
	} 		          
}
function changeIdNumLabel(stage){
	if(stage == 1){
		var idtype = document.getElementById("idproof_type").value;  
	}
	if(stage == 2){
		var idtype = document.getElementById("xidproof_type").value.trim();
	}
	
	var doctype = document.getElementById("doctype") ;
	if( $("#regdoctype").val() == "Registered Sale Deed"){
		doctype.innerHTML = "Registered Sale Deed Copy<span style='color:red'>&nbsp;(PDF) Min.Size 1MB to Max.Size 5MB</span>";
	}
	if( $("#regdoctype").val() == "Registered Gift Deed"){
		doctype.innerHTML = "Registered Gift Deed Copy<span style='color:red'>&nbsp;(PDF) Min.Size 1MB to Max.Size 5MB</span>";
	}
	if( $("#regdoctype").val() == "Registered Lease Deed"){
		doctype.innerHTML = "Registered Lease Deed Copy<span style='color:red'>&nbsp;(PDF) Min.Size 1MB to Max.Size 5MB</span>";
	}
	if( $("#regdoctype").val() == "Registered Partnership Deed"){
		doctype.innerHTML = "Registered Partnership Deed Copy<span style='color:red'>&nbsp;(PDF) Min.Size 1MB to Max.Size 5MB</span>";
	}
		   
	
	var id_doc_label = document.getElementById("id_doc_label");
	//var name_label = document.getElementById("applicant_name_label");
	if(idtype == "Aadhar copy"){	
		id_doc_label.innerHTML = "Aadhar Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		$('#idproof_no').prop("maxLength",12);
	}
	else if(idtype == "Driving Licence"){
		id_doc_label.innerHTML = "Driving Licence Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		
	}
	else if(idtype == "PAN Card"){
		
		id_doc_label.innerHTML = "PAN Card Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		
		$('#idproof_no').prop("maxLength",10);
	}
	else if(idtype == "Ration Card"){
		
		id_doc_label.innerHTML = "Ration Card Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		
	}
	else if(idtype == "Voter Card"){
		
		id_doc_label.innerHTML = "Voter Card Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		
	}
	else if(idtype == "Passport"){
		
		id_doc_label.innerHTML = "Passport Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		$('#idproof_no').prop("maxLength",8);
	}
	else {
		
		id_doc_label.innerHTML = "ID Proof Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
	}
	
}

function finalSubmit(stage,event){
	var cnature = document.getElementById("cnature").value;	
	if(cnature == "40"){	
		if(!checkValidation("additional_load","Please Enter Required Additional Load",'error')){return false;}			
	}
	if(cnature == "41"){	
		if(!checkValidation("naddress1","Please Enter Door No., Building Name",'error')){return false;}	
		if(!checkValidation("naddress2","Please Enter Street",'error')){return false;}	
		if(!checkValidation("naddress3","Please Enter Area",'error')){return false;}	
		if(!checkValidation("naddress4","Please Enter Town",'error')){return false;}	
	}
	if(cnature == "42"){	
		if(!checkValidation("new_cat","Please Choose required Required Category",'error')){return false;}	
	}	
	if(cnature == "47"){	
		if(!checkValidation("newtitle_name","Please Enter New Title name",'error')){return false;}	
		if(!checkValidation("careOf","Please Enter S/o or W/o",'error')){return false;}	
	}
	if(cnature == "69"){
		if(!checkClubbingInputValidation()) return false;		
	}
	if(cnature == "40" || cnature == "69"){
		if(!checkValidation("extension","Please Choose  whether estimate requried or not ",'error')){return false;}
		if($("#extension").val() == "Y"){
			if(!checkValidation("est_type","Please Choose  whether work execution by Department or Consumer",'error')){return false;}
		}
	}
	if(cnature == "40" || cnature == "42" || cnature == "69"){
		if(!checkValidation("gstn_flag","Please Choose  GSTN available or not ",'error')){return false;}
		if($("#gstn_flag").val() == "Y"){
			if(!checkValidation("gstno","Please enter GSTIN No.",'error')){return false;}
			if(!checkValidation("cnfmgstno","Please confirm GSTIN No.",'error')){return false;}
			if($("#is_gstverified").val() != "yes"){
				toastmsg('Please enter a valid GSTN No.','error');
				$("#gstno").focus(); 
				return false;
			}
		}
	}
	if(cnature != "48" ){
		if(!checkValidation("pincode","Please Enter PinCode",'error')){return false;}	
		if(document.getElementById("pincode").value.length !== 6){ 
			toastmsg("Please enter a valid 6 digit pincode number",'error'); 
			$("#pincode").focus(); 
			return false;
		}
	}	
	if(cnature == "41" || cnature == "42" || cnature == "47" || cnature == "48" || cnature == "79"){
		if(!checkValidation("idproof_type","Please Choose Type of ID Proof",'error')){return false;}	
		if(!checkValidation("regdoctype","Please Choose Type of Registered Document",'error')){return false;}
		//Documents Upload
		if(!checkValidation("id_proof","Please upload ID proof Document",'error')){return false;}
		if(!checkValidation("registration_proof","Please upload Registration Document",'error')){return false;}
		if(cnature == "41"){
			if(!checkValidation("indeminity_bond","Please upload Latest House Tax Receipt",'error')){return false;}
		}
		if(cnature == "47"){
			if(!checkValidation("indeminity_bond","Please upload Indeminity Bond Document",'error')){return false;}
		}
	}	
	if(cnature == "79"){	
		if(!checkValidation("additional_load","Please Enter Required Derated Load",'error')){return false;}			
	}
			
	document.adl.action="saveCC";
	document.adl.enctype="multipart/form-data";	
	document.adl.method="POST";
	document.adl.submit();
	
}

function finalSubmit1(stage){
	if(!checkValidation("cdetails","Please Enter Complaint Details in brief",'error')){return false;}	
	var cnature = document.getElementById("cnature").value;	
	if(cnature == "62" || cnature == "70" || cnature == "43"){	
		if(document.adl.consent.checked == false){
			toastmsg("Please give consent to pay shifting charges",'error');	
			document.adl.consent.focus();
			return false;
		}
	}
	if(cnature == "71"){	
		if(!checkValidation("tempload","Please Enter Required Load in Watts",'error')){return false;}	
	}
	document.adl.action="saveCC";
	document.adl.enctype="multipart/form-data";	
	document.adl.method="POST";
	document.adl.submit();
}

function validateInput(id){
	if(document.getElementById(id).value.length==0 ){
		bounce(id);
	}
}

function validateInput1(id, Type){
	if(document.getElementById(id).value.length==0 ){
		bounce(id);
		return true;
	}
}

function bounce(id){
	document.getElementById(id).classList.add("bounce");
    setTimeout(function() {
    	document.getElementById(id).classList.remove("bounce");
    }, 3000); 
    document.getElementById(id).focus();
}

function changeMetricPreReq(){
	var category = $("#category-prereq").val();
	var sub_category = $("#sub_category-prereq").val();
	if(category == 3 || category == 5 || (category == 6 && (sub_category == 5 || sub_category == 6 || sub_category == 9 || sub_category == 12))){
		//document.getElementById("load-label-prereq").innerHTML = "Connected Load in HP";
		//document.getElementById("conn_load-prereq").value = "";
		$("#conn_load-prereq").attr("placeholder", "Connected Load in HP");
	}
	else {
		//document.getElementById("load-label-prereq").innerHTML = "Connected Load in Watts";
		$("#conn_load-prereq").attr("placeholder", "Connected Load in Watts");
	}		
}

function changeMetric(){
	var category = document.getElementById("category-prereq").value;
	var sub_category = document.getElementById("sub_category-prereq").value;
	if(category == 3 || category == 5 || (category == 6 & (sub_category == 5 || sub_category == 6 || sub_category == 9 || sub_category == 12))){
				document.getElementById("load-label-prereq").innerHTML = "Connected Load in HP";
				document.getElementById("conn_load-prereq").value = "";
	}
	else
		document.getElementById("load-label-prereq").innerHTML = "Connected Load in Watts";	
}


var time = 20;
var timer;
var t;
var timer_type;

function downTimer(){
	
	time-- ;
    if(time<10){
		t = "0";
	}
	  if(time<1){
		$("#resendotp").removeAttr("disabled");
		$("#resendotp").html('Resend OTP');
		stopit();						
	}
    document.getElementById("timer").textContent = "00"+":"+ t + time;
  
}
            
function stopit(){
    clearInterval(timer);
    
}

function timer11(){
	stopit();
	time = 60;
	t="";
    timer = setInterval(downTimer,1000);   
}

//Not using 
function loadInputFieldToPreview(){
	var imgElement = document.querySelector('#image_preview');
  // Load preview to img tag
  var reader = new FileReader();
  reader.onload = function(e) {
    imgElement.src = e.target.result
  }
  reader.readAsDataURL(document.querySelector('#applicant_photo').files[0]); // convert to base64 string
}

function getDefaultLanguage(){
	document.getElementById("lan_id_no").innerHTML="ID Proof Number<span class='er'>*</span>";
    document.getElementById("lan_app_name").innerHTML="Applicant Name as in ID Proof<span class='er'>*</span>";
    document.getElementById("lan_father_name").innerHTML="Name of the Father/Wife/Husband<span class='er'>*</span>";
    document.getElementById("lan_sec_name").innerHTML="Section Name<span class='er'>*</span>";
    document.getElementById("lan_con_addr1").innerHTML="Door No./Building Name<span class='er'>*</span>";
    document.getElementById("lan_con_addr2").innerHTML="Street/Landmark<span class='er'>*</span>";
    document.getElementById("lan_con_addr3").innerHTML="Village/City<span class='er'>*</span>";
    document.getElementById("lan_con_addr4").innerHTML="Mandal/District<span class='er'>*</span>";
    document.getElementById("lan_con_pin").innerHTML="Pincode<span class='er'>*</span>";
    document.getElementById("lan_con_mobile").innerHTML="Mobile No.";
    document.getElementById("lan_com_addr1").innerHTML="Door No./Building Name<span class='er'>*</span>";
    document.getElementById("lan_com_addr2").innerHTML="Street/Landmark<span class='er'>*</span>";
    document.getElementById("lan_com_addr3").innerHTML="Village/City<span class='er'>*</span>";
    document.getElementById("lan_com_addr4").innerHTML="Mandal/District<span class='er'>*</span>";
    document.getElementById("lan_com_pin").innerHTML="Pincode<span class='er'>*</span>";
    document.getElementById("lan_com_mobile").innerHTML="Mobile No.";
    document.getElementById("lan_con_load").innerHTML="Connected Load (in Watts)<span class='er'>*</span>";  
    document.getElementById("lan_gst").innerHTML="GSTIN No."; 
    document.getElementById("lan_con_gst").innerHTML="Confirm GSTIN No.";                                        
    document.getElementById("lan_eq_points").innerHTML="EQ Points";
    document.getElementById("lan_doc_no").innerHTML="Document Number";
    document.getElementById("lan_doc_dt").innerHTML="Document Date";
    //document.getElementById("lan_reg_off").innerHTML="Registrar Office";
}



function setIntialSetup(stage){
	//document.getElementById("noscnos").value = 0;
    $("#gstno").attr("disabled", "disabled"); 
    $("#cnfmgstno").attr("disabled", "disabled");
	
	$("#regdoc").prop("selectedIndex", 1);
	$("#regdoc").click();

	$("#gstn_flag").prop("selectedIndex", 0);
	$("#gstno").val("");
	$("#cnfmgstno").val("");
	$("#regdoctype").prop("selectedIndex", 0);
	$("#regdocno").val("");

	$("mobileno").prop("readonly","readonly");
	$("comm_phoneno").prop("readonly","readonly");
	
	$("#is_dbh").prop("selectedIndex", 2);
	$("#is_dbh").click();
	//$("#scnoextn").prop("checked", true).trigger("click");
}

function getDefaultLanguage1(){
	document.getElementById("lan_comp_name").innerHTML="Company Name<span class='er'>*</span>";
    document.getElementById("lan_mch_id").innerHTML="MCH Registration Idf<span class='er'>*</span>";
    document.getElementById("lan_own_name").innerHTML="Owner Name<span class='er'>*</span>";
    document.getElementById("lan_pan_no").innerHTML="PAN No.<span class='er'>*</span>";
    document.getElementById("lan_con_addr1").innerHTML="Door No./Building Name<span class='er'>*</span>";
    document.getElementById("lan_con_addr2").innerHTML="Street/Landmark<span class='er'>*</span>";
    document.getElementById("lan_con_addr3").innerHTML="Village/City<span class='er'>*</span>";
    document.getElementById("lan_con_addr4").innerHTML="Mandal/District<span class='er'>*</span>";
    document.getElementById("lan_con_pin").innerHTML="Pincode<span class='er'>*</span>";
    document.getElementById("lan_con_mobile").innerHTML="Mobile No.";
    document.getElementById("lan_com_addr1").innerHTML="Door No./Building Name<span class='er'>*</span>";
    document.getElementById("lan_com_addr2").innerHTML="Street/Landmark<span class='er'>*</span>";
    document.getElementById("lan_com_addr3").innerHTML="Village/City<span class='er'>*</span>";
    document.getElementById("lan_com_addr4").innerHTML="Mandal/District<span class='er'>*</span>";
    document.getElementById("lan_com_pin").innerHTML="Pincode<span class='er'>*</span>";
    document.getElementById("lan_com_mobile").innerHTML="Mobile No.";
    document.getElementById("lan_apt_name").innerHTML="Apartment Name<span class='er'>*</span>";  
    document.getElementById("lan_no_flats").innerHTML="No of flats<span class='er'>*</span>";  
    document.getElementById("lan_gst").innerHTML="GSTIN No."; 
    document.getElementById("lan_con_gst").innerHTML="Confirm GSTIN No.";                                        
    document.getElementById("lan_eq_points").innerHTML="EQ Points";
    document.getElementById("lan_doc_no").innerHTML="Document Number";
    document.getElementById("lan_doc_dt").innerHTML="Document Date";
    //document.getElementById("lan_reg_off").innerHTML="Registrar Office";
}

function getClubUkscnoDetails(j){
	var uksc=$("#clbukscno"+j).val();
	for(var k=1;k<j;k++){
		if($("#clbukscno"+k).val() == uksc){
			toastmsg("Please Enter Different Unique Service No.",'error');
			$("#clbukcatcode"+j).focus();
			return false();
		}
	}
	var scope = "final";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             const obj = JSON.parse(this.responseText); 
             if(obj.valid == "Y"){
	             $("#dukname"+j).html(obj.name);
		         $("#clbukname"+j).val(obj.name);
		         $("#dukcat"+j).html(obj.category);
			     $("#clbukcat"+j).val(obj.category);
			     $("#clbukcatcode"+j).val(obj.catcode);//saving for same category condition
				 $("#dukload"+j).html(obj.load);
			     $("#clbukload"+j).val(obj.load);
			     $("#dukaddr"+j).html(obj.addr1+","+obj.addr2+",<br>"+obj.addr3+","+obj.addr4);
			     $("#clbukaddr"+j).val(obj.addr1+","+obj.addr2+",<br>"+obj.addr3+","+obj.addr4);	
			     $("#duksection"+j).html(obj.section_name);
			     $("#clbuksec"+j).val(obj.section_name);
			     $("#clbtmb"+j).html("<span class='fa fa-thumbs-up text-success'></span>");	
			     
			     var nos=$("#club_nos").val();
				 document.getElementById("total_club_load").value = 0;
				 
				 for(var p=1;p<=nos;p++){
					var each_load = 0;
					var tot_club_load = parseInt(document.getElementById("total_club_load").value) ;
					if(document.getElementById("clbukload"+p).value != ""){
						each_load = parseInt(document.getElementById("clbukload"+p).value) ;
						//alert("clbukload"+p);
					}
					document.getElementById("total_club_load").value = tot_club_load + each_load ;
					document.getElementById("total_club_load_final").value = tot_club_load + each_load ;
					//alert(document.getElementById("total_club_load").value);
				 }
				 isValidClubbing(j);
				 getPayments();
				 setClubbedLoad();
			}else{
				$("#clbtmb"+j).html("<span class='fa fa-thumbs-down text-danger'></span>");
				toastmsg("Invalid Unique Service Number",'error');
				$("#dukname"+j).html('');
				$("#dukcat"+j).html('');
				$("#dukaddr"+j).html('');
				$("#duksection"+j).html('');
				$("#dukload"+j).html('');
			}
         }
            
    };
    xhttp.open("GET", "getService/"+uksc+"/"+scope, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function isValidClubbing(j){
	$("#is_valid_clubbing_cat").val('yes');
	$("#is_valid_clubbing_sec").val('yes');
	var catcode=$("#clbukcatcode"+j).val();
	for(var k=1;k<j;k++){
		if($("#clbukcatcode"+k).val() !== catcode){
			toastmsg("All the clubbing services must be of same category",'error');	
			$("#clbukcatcode"+j).focus();
			$("#dukcat"+j).addClass("text-danger");
			$("#is_valid_clubbing_cat").val('no');
			return false;
		}else{
			$("#dukcat"+j).removeClass("text-danger");
		}
	 }
	var section=$("#clbuksec"+j).val();
	for(var k=1;k<j;k++){
		if($("#clbuksec"+k).val() !== section){
			toastmsg("All the clubbing services must belong to same section",'error');	
			$("#clbuksec"+j).focus();
			$("#duksection"+j).addClass("text-danger");
			$("#is_valid_clubbing_sec").val('no');
			return false;
		}else{
			$("#duksection"+j).removeClass("text-danger");
		}
	 }
}

function clubAdl(){
	var is_adl_req = document.getElementById("is_adl_req").value;	
	if(is_adl_req == "Y"){
		$("#additional_load").attr('disabled', false);
	}
	if(is_adl_req == "N"){
		$("#additional_load").attr('disabled', true);
		$("#additional_load").val('');
		document.adl.total_club_load_final.value = document.getElementById("total_club_load").value;
	}
	getPayments();
}

function setClubbedLoad(){		 
	 if(document.getElementById("is_adl_req").value == "Y"){
		var tot_club_load = 0, tot_load =0;;
		var addl_load = $("#additional_load").val() ? parseInt(document.getElementById("additional_load").value) : 0;
	    tot_club_load = parseInt(document.getElementById("total_club_load").value);
		tot_load = addl_load + tot_club_load;
		document.adl.total_club_load_final.value = tot_load;
	}
}

function checkLoadDeration(addl_load, exist_load){
		if(addl_load >= exist_load){
			toastmsg("Please enter a load less than existing load",'error');	
			$("#additional_load").focus();
		}else{
			document.adl.total_load.value = addl_load;
		}
}

function checkClubbingInputValidation(){
	if(!checkValidation("club_nos","Please Enter No.of Services to be clubbed",'error')){return false;}	
	
	var nos = document.getElementById("club_nos").value;
	for(var i=1;i<=nos;i++){
		if(!checkValidation("clbukscno"+i,"Please Enter Unique Service No."+i,'error')){return false;}	
	}	
	
	if($("#is_valid_clubbing_cat").val() == "no"){
		toastmsg('All the clubbing services must be of same category','error');
		return false;
	}
	if($("#is_valid_clubbing_sec").val() == "no"){
		toastmsg('All the clubbing services must belong to the same section','error');
		return false;
	}
	
	if(!checkValidation("is_adl_req","Please Choose whether additional required or not",'error')){return false;}	
	var is_adl_req = document.getElementById("is_adl_req").value;
	if(is_adl_req == "Y")
		if(!checkValidation("additional_load","Please Enter Required Additional Load",'error')){return false;}	
				
	var a =  "", b= "" , req_load=0;
	for(var i=1;i<=nos;i++){
		var uscno = document.getElementById("clbukscno"+i).value;
		var usc_load = parseInt(document.getElementById("clbukload"+i).value);
		if(i==1){
			a = uscno;
		}	
		if(i>1){
			b = a + "," + uscno;
			a = b; 
			
			req_load = req_load + usc_load; 
			document.getElementById("req_load_club").value = req_load; //sending hidden parametet to insertCC
			
		}				
	}
	document.getElementById("xclubbedUscnos").value =  b; // sending clubbed uscnos string to insertCC
	return true;
}

function tempService(cnature){
	document.adl.cnature.value = cnature;
	document.getElementById("pay65").style.display = "block";
	document.getElementById("pay71").style.display = "none";
	
}

function tempSupply(cnature){
	document.adl.cnature.value = cnature;
	document.getElementById("pay65").style.display = "none";
	document.getElementById("pay71").style.display = "block";
	getPaymentsTempSupply();
}

function getPaymentsTempSupply(){
	document.getElementById("amount").innerHTML = "&#8377; 0.00" ;
	
	if(document.adl.tempload.value.length>0)
		{
			var conload=parseInt(document.adl.tempload.value);
			if( conload <= 250)
			{
				document.adl.othamount.value="500";
			}
			else if(conload>250 && conload<=500)
			{
				document.adl.othamount.value="1000";
			}
			else
			{
				var dev1=(conload-500)/500;
				dev1=Math.ceil(dev1);
				document.adl.othamount.value=1500+dev1*750;
			}
			
			document.getElementById("amount").innerHTML = "&#8377; " + document.adl.othamount.value;
		}
	
}

function getMetric(){
	var cat =  parseInt(document.getElementById("xcatcode").value);
	var subcat = parseInt(document.getElementById("xsubcatcode").value);
	var metric = "";
	if(cat == 3 || cat == 4 || cat == 5 || (cat == 6 &&  (subcat ==5 || subcat ==6 || subcat ==9 || subcat ==12)) ) 
		metric = "HP";
	else
		metric = "Watts";
		
	return metric;
}





/*function getCnatureList(stage){
	document.getElementById("cnature").innerHTML = "<option value=''></option>";
	var ctype = document.getElementById("ctype").value;
	$("#form-container").hide();
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				const json = JSON.parse(this.responseText);
				var xcnature = document.getElementById("xcnature").value;
				var cnature = document.getElementById("cnature");
				for (var i in json){
					
					if(json[i].canture_id == xcnature)
						cnature.innerHTML += "<option value='" + json[i].canture_id + "' selected>" + json[i].cnature_desc + "</option>";
					else
						cnature.innerHTML += "<option value='" + json[i].canture_id + "'>" + json[i].cnature_desc + "</option>";	
				}	
				$("#cnature").click();
				//$("#form-container"+xcnature).show();

			}
				
	};
	xhttp.open("GET", "getCnatureList/"+ctype, true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}
function getServiceDetailsFinal(){
	//hide();//hidding all form containers
	var ukscno = document.getElementById("xukscno").value;
	var scope = "final";
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 const obj = JSON.parse(this.responseText); 
				 document.getElementById("cons_name").innerHTML = obj.name;
                 document.getElementById("category").innerHTML = obj.category;
                 document.getElementById("exsisting_load").innerHTML = obj.load;	
                 document.getElementById("conn_address1").innerHTML = obj.addr1;	
                 document.getElementById("conn_address2").innerHTML = obj.addr2;	
                 document.getElementById("conn_address3").innerHTML = obj.addr3;	
                 document.getElementById("conn_address4").innerHTML = obj.addr4;	
                 document.getElementById("section_name").innerHTML = obj.section_name;	
                 document.getElementById("xload").value =  obj.load;
                 document.getElementById("xcatcode").value =  obj.catcode;
                  document.getElementById("xcategory").value =  obj.category;		
                 //if(obj.catcode == "3" || obj.catcode == "5")
                 	//document.getElementById("metric").innerHTML = "Additional Load Required in HP";	
                 	
                 //below code is used instead of 
                 //$("#gstn_flag").prop("selectedIndex", 0);
				 //$("#gstno").val("");
				// $("#cnfmgstno").val("");
                 
             }
                
        };
        xhttp.open("GET", "getService/"+ukscno+"/"+scope, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send();
}

*/



