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

function getServiceDetailsPreReq(){
	var ukscno = document.getElementById("uscno_prereq").value;
	var scope = "prereq";
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 const obj = JSON.parse(this.responseText); 
                 if(obj.valid == "Y"){
					 $("#alert").hide();
					 $("#req_load_prereq").attr('disabled', false);
					 document.getElementById("name_pre").innerHTML = obj.name;
	                 document.getElementById("cat_pre").innerHTML = obj.category;
	                 document.getElementById("load_pre").innerHTML = obj.load;	
	                 if(obj.catcode == "3" || obj.catcode == "5")
	                 	document.getElementById("metric").innerHTML = "Additional Load Required in HP";	
				 }else{
					$("#alert").show();
				 }
                 
             }
                
        };
        xhttp.open("GET", "getService/"+ukscno+"/"+scope, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send();
}

function getServiceDetailsFinal(){
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
                 if(obj.catcode == "3" || obj.catcode == "5")
                 	document.getElementById("metric").innerHTML = "Additional Load Required in HP";	
                 	
                 //below code is used instead of 
                 $("#gstn_flag").prop("selectedIndex", 0);
				 $("#gstno").val("");
				 $("#cnfmgstno").val("");
                 
             }
                
        };
        xhttp.open("GET", "getService/"+ukscno+"/"+scope, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send();
}

function getPreReqAdl(){
    //document.getElementById("prereq-y-n-div").style.display = "none";
    var ukscno = document.getElementById("uscno_prereq").value;
    var req_load_prereq = document.getElementById("req_load_prereq").value;

    
    $("#af_pre").html("0.00");
     	
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			json = JSON.parse(this.responseText);
			document.getElementById("af_pre").innerHTML = json.af_pre;
			document.getElementById("afgst_pre").innerHTML = json.afgst_pre;
			document.getElementById("sd_pre").innerHTML = json.sd_pre;
			document.getElementById("dc_pre").innerHTML = json.dc_pre;
			document.getElementById("dcgst_pre").innerHTML = json.dcgst_pre;
			document.getElementById("tot_pre").innerHTML = json.tot_pre;													
		}
	};
	xhttp.open("POST","getPreReqAdl",true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ukscno:ukscno, req_load_prereq:req_load_prereq}));
}

function getPayments(){
	 var ukscno = document.getElementById("xukscno").value;
	 var extension = document.getElementById("extension").value;
	 var exist_load = parseInt(document.getElementById("xload").value);
	 var addl_load = parseInt(document.getElementById("additional_load").value);
	 var tot_load = addl_load + exist_load;

	 if(document.getElementById("additional_load").value.length > 3){
		document.adl.total_load.value = tot_load;
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			json = JSON.parse(this.responseText);
			document.getElementById("af").innerHTML = json.af_pre;
			document.getElementById("afgst").innerHTML = json.afgst_pre;
			document.getElementById("sd").innerHTML = json.sd_pre;
			document.getElementById("dc").innerHTML = json.dc_pre;
			document.getElementById("dcgst").innerHTML = json.dcgst_pre;
			document.getElementById("tot").innerHTML = json.tot_pre;													
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


function test(stage,json){
		$("#form-container").show();
		//$("#area_code-div").show();
		//$("#area-div").hide();
		//getDefaultLanguage();
		$("#adjsecname").prop( "class", "form-control alert alert-success custom-alert");

		document.getElementById("adjsecname").value = json.section_name;
		//$("#d-secname").show();
		//document.getElementById("secname").value = json.section_name;
		//$("#secname").prop('readonly', true);							
		document.newreg.section_code.value = json.section_code;
		//document.newreg.ero_code.value = json.ero_code;
		document.newreg.csc_number.value = json.csc_number;
											
		/*var area_code = document.getElementById("area_code");
		for (var i in json.area_data){
			area_code.innerHTML += "<option value='" + json.area_data[i].area_code + "'>" + json.area_data[i].area_name + "</option>";
		}
		document.newreg.area_code.value = json.adj_area_code;
		document.newreg.area_name.value = json.adj_area_name;
		document.newreg.area_group.value = json.adj_area_group; 
		$("#area_code").click();*/
		/*if(stage == 2){
			document.getElementById("area_code").value = document.getElementById("xarea_code").value;
		}*/
}

function changeIcon(inputID)
{	
	//alert("hi");
	let xinputID = '#'+inputID;
	$(xinputID).html("<i class='fa fa-search'></i>");
}


function checkUSCNO(stage,ukscno,idx){
	if(idx!=''){
		if($("#ukscnos"+idx).val() != "")
		$("#ukscnos"+idx).prop("style","  background:url(http://www.xiconeditor.com/image/icons/loading.gif) no-repeat center");
	}else{
		if($("#adj_uscno").val() != "")
		$("#adj_uscno").prop("style","background:url(http://www.xiconeditor.com/image/icons/loading.gif) no-repeat center");
	}
	document.getElementById("circle").innerHTML = "<option value=''></option>";
	$("#circle").click();
	$("#circle").blur();
	document.getElementById("section").innerHTML = "<option value=''></option>";
	$("#section").click();
	$("#section").blur();
	$("#form-container").hide();
	var uscno = $('#'+ukscno).val();
	var xhttp = new XMLHttpRequest();
		
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {	
			const json = JSON.parse(this.responseText);
			//alert(json.ebs_resp);
			if(json.ebs_resp=="VALID"){
				if(idx!=''){
					//$("#area-div").hide();
					var noscnos=$("#noscnos").val();
					$("#wr"+idx).html("<i  class='fa fa-thumbs-up text-success' ></i>");	
					if(idx == noscnos){
						document.getElementById("billStatus"+idx).value = json.billstatus;												
						document.getElementById("category"+idx).value = json.billcategory;
						$("#bt"+idx).removeAttr("style");
						test(1,json);
					}else{
						document.getElementById("billStatus"+idx).value = json.billstatus;												
						document.getElementById("category"+idx).value = json.billcategory;
						$("#ukscnos"+(idx+1)).focus();
					}
					$("#ukscnos"+idx).removeAttr("style");				
				}
				else{				
					test(1,json);
					$("#adj_uscno").removeAttr("style");	
					$("#adj_icon").html("<i class='fa fa-thumbs-up text-success'></i>");
				}
			}
			else {
				$("#form-container").hide();
				if(idx!=''){
					$("#wr"+idx).html("<i class='fa fa-thumbs-down text-danger'></i>");
					$("#ukscnos"+idx).removeAttr("style");		
				}
				else{
					$("#adj_uscno").removeAttr("style");	
					$("#adjsecname").val("Invalid Service Number");	
					$("#adjsecname").prop( "class", "form-control alert alert-danger custom-alert");
					$("#adj_icon").html("<i class='fa fa-thumbs-down text-danger'></i>");
				}	
			}										
		}
	};
	xhttp.open("GET", "checkUscno/"+uscno, true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
	    
	if(stage==1){
		getCircleList(1);
	}

}
//Additional Payment
   

function getAreaName1(){
	var uscno = document.getElementById("adj_uscno").value;
	var area_code = document.getElementById("area_code").value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			$("#form-container").show();
			getDefaultLanguage();
			$("#adjsecname").prop( "class", "form-control alert alert-success custom-alert");
			const json = JSON.parse(this.responseText);
			document.newreg.area_name.value = json.area_name;
			document.newreg.area_group.value = json.area_group;																														
		}
	};
	xhttp.open("GET", "getAreaName1/"+uscno+"/"+area_code, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getCircleList(stage){
	$("#form-container").hide();
	//document.getElementById("circle").innerHTML = "<option value=''></option>";
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				const json = JSON.parse(this.responseText);
				//alert(json.ebs_resp);
				//$("#form-container").show();
				//getDefaultLanguage();
				//$("#adjsecname").prop( "class", "form-control alert alert-success custom-alert");

				var circle = document.getElementById("circle");
				for (var i in json){
					circle.innerHTML += "<option value='" + json[i].circle_id + "'>" + json[i].circle_name + "</option>";
				}
				if(stage == 2){
					//document.getElementById("area_code").value = document.getElementById("xarea_code").value;
				}										
			}
				
	};
	xhttp.open("GET", "getCircleList", true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getSectionList(stage){
	$("#section").prop("style","background:url(http://www.xiconeditor.com/image/icons/loading.gif) no-repeat right center");
	//$("#section_spin").prop( "class", "spinner-border text-warning");
	document.getElementById("section").innerHTML = "<option value=''></option>";
	var circle_id = document.getElementById("circle").value;
	$("#form-container").hide();
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				const json = JSON.parse(this.responseText);
				//alert(json.ebs_resp);
				//$("#form-container").show();
				//getDefaultLanguage();
				//$("#adjsecname").prop( "class", "form-control alert alert-success custom-alert");

				var section = document.getElementById("section");
				for (var i in json){
					section.innerHTML += "<option value='" + json[i].section_id + "'>" + json[i].section_name + "</option>";
				}
				if(stage == 2){
					//document.getElementById("area_code").value = document.getElementById("xarea_code").value;
				}	
			//	$("#section_spin").removeAttr("class");	
				$("#section").removeAttr("style");									
			}
				
	};
	xhttp.open("GET", "getSectionList/"+circle_id, true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function showForm(stage){
	$("#adj_uscno").val('');
	$("#adj_icon").html("<i class='fa fa-search'></i>");
	$("#adjsecname").val('Find your Section Name here');
	$("#adjsecname").prop( "class", "form-control alert alert-primary custom-alert");
	section_id = document.getElementById("section").value;
	document.newreg.section_code.value = section_id;
	$("#form-container").show();
	var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.newreg.csc_number.value = this.responseText;
			}	
	};
	xhttp.open("GET", "getCscNo/"+section_id, true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
	
	}
function getAreaList(stage){
	$("#adj_uscno").val('');
	$("#adj_icon").html("<i class='fa fa-search'></i>");
	$("#adjsecname").val('Find your Section Name here');
	$("#adjsecname").prop( "class", "form-control alert alert-primary custom-alert");
	$("#area-div").show();
	//$("#area-div").click();
	$("#area_code-div").hide();
	document.getElementById("area").innerHTML = "<option value=''></option>";
	$("#area").click();
	$("#area").blur();
	var section_id = document.getElementById("section").value;
	$("#form-container").hide();
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				$("#form-container").show();
				//getDefaultLanguage();
				//$("#adjsecname").prop( "class", "form-control alert alert-success custom-alert");
				const json = JSON.parse(this.responseText);
					//document.getElementById("adjsecname").value = json.section_name;
					//$("#d-secname").show();
					//document.getElementById("secname").value = json.section_name;
					//$("#secname").prop('readonly', true);							
					document.newreg.section_code.value = json.section_code;
					document.newreg.ero_code.value = json.ero_code;
					document.newreg.csc_number.value = json.csc_number;
													
					var area_code = document.getElementById("area");
					for (var i in json.area_data){
						area_code.innerHTML += "<option value='" + json.area_data[i].area_code + "'>" + json.area_data[i].area_name + "</option>";
					}
				if(stage == 2){
					//document.getElementById("area_code").value = document.getElementById("xarea_code").value;
				}										
			}
				
	};
	xhttp.open("GET", "getAreaList/"+section_id, true); 
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getAreaName2(){
	var section_code = document.newreg.section_code.value;
	var area_code = document.getElementById("area").value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			const json = JSON.parse(this.responseText);
			document.newreg.area_name.value = json.area_name;
			document.newreg.area_group.value = json.area_group;																														
		}
	};
	xhttp.open("GET", "getAreaName2/"+area_code+"/"+section_code, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}


function estimateReq(){
	if(document.getElementById("extension").value.length==0 ) { bounce("extension"); return false;}
	if($("#extension").val() == 'Y' ){
		toastmsg("Security Deposit and Development Charges will be collected during Estimate Payment",'error');
	}
	getReqPayment(1);
}


function RegisterDoc(stage){	
	
	var isRegDoc = $("#regdoc").val();
	var doctype = document.getElementById("doctype") ;
	if(stage == 2)	
	    isRegDoc = document.getElementById("xregdoc").value;
	    
	if(isRegDoc == "Y"){		
		doctype.innerHTML = "Registered Document<span style='color:red'>&nbsp;(PDF) Max.Size 5MB</span>";
		$("#regdoctype").removeAttr("disabled");
    	$("#regdocno").removeAttr("disabled");
    	$("#regdocdate").removeAttr("disabled");
    	$("#regdocofc").removeAttr("disabled");
	}
	else if(isRegDoc == "N"){
		doctype.innerHTML = "Indemnity Bond Copy<span style='color:red'>&nbsp;(PDF) Max.Size 1MB</span>";	
		//toastmsg("3 Times Security Deposit will be collected",'success');
		$("#regdoctype").prop('selectedIndex', 0);
		//$("#regdoctype").load();
    	$("#regdocno").val("");
    	$("#regdocdate").val("");
    	$("#regdocofc").val("");
    	
		$("#regdoctype").attr("disabled", "disabled"); 
    	$("#regdocno").attr("disabled", "disabled"); 
    	$("#regdocdate").attr("disabled", "disabled"); 
    	$("#regdocofc").attr("disabled", "disabled"); 
	}
	
	getReqPayment(stage);
}

function getReqPayment(stage){
   
   	var dom_conn = $("#tot_dom_connections").val();
	var com_conn = $("#tot_com_connections").val();
	
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			json = JSON.parse(this.responseText);
			document.getElementById("af").innerHTML = json.af;
			document.getElementById("afgst").innerHTML = json.afgst;
			document.getElementById("tot").innerHTML = json.tot;													
															
		}
	};
	xhttp.open("POST","getLTMPreReq",true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({domestic_con:dom_conn, commercial_con:com_conn}));
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
		document.loginForm.action="regAL";
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

function checkFile1(){	
	$("#id_proof").prop('class','form-control');
	 
	var Receiptelement = document.getElementById("id_proof");
	var Receiptname = Receiptelement.value;
	var allowedExtensions = /(\.jpg|\.jpeg)$/i;
    if (!allowedExtensions.exec(Receiptname)) {
    	Receiptelement.value = '';
        toastmsg("Upload jpg or jpeg Format only",'error');	
		$("#id_proof").prop('class','form-control error');
		return false;
    }  
    var Receiptfile = Receiptelement.files;           
    var ReceiptSize = Receiptfile[0].size;    
    const ReceiptKb = ReceiptSize / 1024;
	if(ReceiptKb > 100){
		Receiptelement.value = '';
		toastmsg("Maximum File Size allowed 100KB only",'error');	
		$("#id_proof").prop('class','form-control error');
		return false;
	} 		          
}

function checkFile2(){	
	$("#registration_proof").prop('class','form-control');
	var Receiptelement = document.getElementById("registration_proof");
	var Receiptname = Receiptelement.value;
	var allowedExtensions = /(\.pdf)$/i;
    if (!allowedExtensions.exec(Receiptname)) {
        Receiptelement.value = '';
        toastmsg("Upload pdf Format only",'error');	
		$("#registration_proof").prop('class','form-control error');
		return false;
    }  
    var Receiptfile = Receiptelement.files;           
    var ReceiptSize = Receiptfile[0].size;    
    const ReceiptMb = ReceiptSize /(1024*1024);
    if( document.getElementById("regdoc").value == "Y"){
		if(ReceiptMb < 1 || ReceiptMb > 5){
			Receiptelement.value = '';
			toastmsg("File Size must be between 1MB to 5MB",'error');	
			$("#registration_proof").prop('class','form-control error');
			return false;
		} 	
	}
	if( document.getElementById("regdoc").value == "N"){
		if(ReceiptMb > 1){
			Receiptelement.value = '';
			toastmsg("File Size must be less than 1MB",'error');	
			$("#registration_proof").prop('class','form-control error');
			return false;
		}
	}	          
}
function checkFile3(){	
	$("#approval_plan").prop('class','form-control');
	 
	var Receiptelement = document.getElementById("approval_plan");
	var Receiptname = Receiptelement.value;
	var allowedExtensions = /(\.pdf)$/i;
    if (!allowedExtensions.exec(Receiptname)) {
        Receiptelement.value = '';
        toastmsg("Upload pdf Format only",'error');	
		$("#approval_plan").prop('class','form-control error');
		return false;
    }   
    var Receiptfile = Receiptelement.files;           
    var ReceiptSize = Receiptfile[0].size;    
    const ReceiptMb = ReceiptSize /(1024*1024);
	if(ReceiptMb > 5){
		Receiptelement.value = '';
		toastmsg("File Size must be less than 5 MB",'error');	
		$("#approval_plan").prop('class','form-control error');
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
 	if(stage == 1){	
		
		if(!checkValidation("additional_load","Please Enter required Additional Load",'error')){return false;}	
		if(!checkValidation("extension","Please Choose Work whether CT Meter/Transformer Required or not",'error')){return false;}
		if(!checkValidation("est_type","Please Choose Work Execution type ",'error')){return false;}
		if(!checkValidation("pincode","Please Enter PinCode",'error')){return false;}			
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
		
	document.adl.action="saveAL";
	document.adl.enctype="multipart/form-data";	
	document.adl.method="POST";
	document.adl.submit();
	}
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



/*function loadURLToInputField(xid, fileid, regno, startlabel, endlabel){
	var url = document.getElementById(xid).value;
	//alert(url);
  	getImgURL(url, (imgBlob)=>{
    // Load img blob to input
    let fileName = startlabel + regno + endlabel;// should .replace(/[/\\?%*:|"<>]/g, '-') for remove special char like / \
    let file = new File([imgBlob], fileName,{type:"image/jpeg", lastModified:new Date().getTime()}, 'utf-8');
    let container = new DataTransfer(); 
    container.items.add(file);
    document.getElementById(fileid).files = container.files;
    // document.querySelector('#status').files = container.files;
    	//alert(document.getElementById(fileid).files[0].size);
  })
}


function getImgURL(url, callback){
	  var xhr = new XMLHttpRequest();
	  xhr.onload = function() {
	      callback(xhr.response);
	  };
	  xhr.open('GET', url);
	  xhr.responseType = 'blob';
	  xhr.send();
}

function hidePreview(id){
	if(id == "applicant_photo")
		document.getElementById('preview1').style.display = "none";
	if(id == "id_proof")
		document.getElementById('preview2').style.display = "none";
	if(id == "premises_proof")
		document.getElementById('preview3').style.display = "none";
	if(id == "location_photo")
		document.getElementById('preview4').style.display = "none";
}*/



/*function showEntryPreReq(){
    //document.getElementById("prereq-y-n-div").style.display = "none";  
    document.getElementById("apply-div").style.display = "none";  
    document.getElementById("reg-y-n-div").style.display = "none";
    document.getElementById("reg-entry-div").style.display = "none";  
    document.getElementById("mobile-entry-div").style.display = "none";  
    
    document.getElementById("entry-prereq-div").style.display = "block";

    document.getElementById('entry-prereq-div').setAttribute('data-aos', 'slide-up'); 
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });
}*/

/*function showRegYesNo(){
    document.getElementById("entry-prereq-div").style.display = "none";
    document.getElementById("details-prereq-div").style.display = "none";
    /*document.getElementById("reg-y-n-div").style.display = "block";

    document.getElementById('reg-y-n-div').setAttribute('data-aos', 'slide-up');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });
    document.getElementById("apply-div").style.display = "none";
    document.getElementById("apply-div").style.display = "flex";
    document.getElementById('apply-div').setAttribute('data-aos', 'zoom-in');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });
    document.getElementById("mobile_login").focus;
}*/

