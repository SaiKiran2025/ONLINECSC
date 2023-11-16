
function mainFunction(){
	checkUSCNO(2);
	document.getElementById('idproof_type').value = document.getElementById('xidproof_type').value.trim();
	changeIdNumLabel(2);
	document.getElementById('gender').value = document.getElementById('xgender').value.trim();
	document.getElementById('social_grp').value = document.getElementById('xsocial_grp').value.trim();
	document.getElementById('category').value = document.getElementById('xcategory').value;
	getSubCategory(2);	
	getCode1(2);
	document.getElementById('cons_status').value = document.getElementById('xcons_status').value;
	document.getElementById('gstn_flag').value = document.getElementById('xgstn_flag').value;
	gstnYes();
	document.getElementById('serv_type').value = document.getElementById('xserv_type').value;
	getCode2(2);
	getHodCode(2);
	document.getElementById('phase').value = document.getElementById('xphase').value;
	document.getElementById('location_type').value = document.getElementById('xlocation_type').value;
	document.getElementById('sez').value = document.getElementById('xsez').value;
	document.getElementById('comnserv').value = document.getElementById('xcomnserv').value;
	document.getElementById('regdoc').value = document.getElementById('xregdoc').value;
	RegisterDoc(2); 
	//getDocsPaymnt(2);
	document.getElementById('regdoctype').value = document.getElementById('xregdoctype').value;	
	document.getElementById('extension').value = document.getElementById('xextension').value;	
	
	var xregno = document.getElementById('regno').value;
	if(!document.getElementById('xaphotoUrl').value.length == 0){
		document.getElementById('preview1').style.display = "";
		loadURLToInputField('xaphotoUrl','applicant_photo', xregno, "Photo_", ".jpg");//function call for setting input type file value to already uploaded document value
		}
	if(!document.getElementById('xiproofUrl').value.length == 0){
		document.getElementById('preview2').style.display = "";
		loadURLToInputField('xiproofUrl','id_proof', xregno, "idproof_", ".jpg");
		}
	if(!document.getElementById('xsdeedUrl').value.length == 0){
		document.getElementById('preview3').style.display = "";	
		loadURLToInputField('xsdeedUrl','premises_proof', xregno, "premisesproof_", ".pdf");
		}
	if(!document.getElementById('xlphotoUrl').value.length == 0){
		document.getElementById('preview4').style.display = "";
		loadURLToInputField('xlphotoUrl','location_photo', xregno, "locationphoto_", ".jpg");
		}
}



function loadURLToInputField(xid, fileid, regno, startlabel, endlabel){
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
}



function showEntryPreReq(){
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
}

function showRegYesNo(){
    document.getElementById("entry-prereq-div").style.display = "none";
    document.getElementById("details-prereq-div").style.display = "none";
    /*document.getElementById("reg-y-n-div").style.display = "block";

    document.getElementById('reg-y-n-div').setAttribute('data-aos', 'slide-up');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });*/
    document.getElementById("apply-div").style.display = "none";
    document.getElementById("apply-div").style.display = "flex";
    document.getElementById('apply-div').setAttribute('data-aos', 'zoom-in');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });
    document.getElementById("mobile_login").focus;
}

function getPreReq(){
    //document.getElementById("prereq-y-n-div").style.display = "none";
    
    var cat_prereq = document.getElementById("category-prereq").value;
    var subcat_prereq = document.getElementById("sub_category-prereq").value;
    var load_prereq = document.getElementById("conn_load-prereq").value;
    var regdoc_prereq = document.getElementById("regdoc-prereq").value;
    var extension1 = "NA";
    
    validateInput1("category-prereq");
    validateInput1("sub_category-prereq");
    validateInput1("conn_load-prereq");
    if(validateInput1("regdoc-prereq"))
    	return false;

    
    if(regdoc_prereq == "N")
    	document.getElementById("p_doc").innerHTML = "3.Indemnity Bond (pdf, max-size : 1MB)";
    else if(regdoc_prereq == "Y" && cat_prereq == "5")
    	document.getElementById("p_doc").innerHTML = "3.Passbook/Patta Document (pdf, max-size : 5MB)";
    else if(regdoc_prereq == "Y")
    	document.getElementById("p_doc").innerHTML = "3.Registered Sale Deed (pdf, max-size : 5MB)";
    	
    
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
												
												if(document.getElementById("details-prereq-div").style.display == "none"){
													document.getElementById("details-prereq-div").style.display = "block";
    												window.scrollBy(0,450);	
												}
													    											
    											document.getElementById('details-prereq-div').setAttribute('data-aos', 'slide-up');
											    AOS.init({
											        initClassName: "aos-init",
											        startEvent: "DOMContentLoaded",
											    });		
											           
   												document.getElementById("apply-div").style.display = "flex";			
											}
	};
	xhttp.open("POST","getPreReq",true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({category:cat_prereq, subcategory:subcat_prereq, load:load_prereq, regdoc:regdoc_prereq, extension:extension1}));
}

function showContinueReg(){
    document.getElementById("mobile-entry-div").style.display = "none";
    document.getElementById("reg-entry-div").style.display = "block";

    document.getElementById('reg-entry-div').setAttribute('data-aos', 'slide-up');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });
    
}

function showFreshReg(){
    document.getElementById("reg-entry-div").style.display = "none";
    document.getElementById("mobile-entry-div").style.display = "block";
    window.scrollBy(0,350);

    document.getElementById('mobile-entry-div').setAttribute('data-aos', 'slide-up');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });

}


function continueRegistration(){
	document.getElementById("otp_block1").style.display = "none";
	var login_regno = document.f1.login_regno.value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
											if (this.readyState == 4 && this.status == 200)
											{
												if(this.responseText !== ""){
													document.getElementById("otp_block2").style.display = "block";
													window.scrollBy(0,250);
													
													document.getElementById("mbl_reg").innerHTML = this.responseText;
													
							    	    			document.getElementById("p").focus();
							    	    			document.getElementsByClassName("input_otp_reg").value = "";
							    	    										    	    			
							    	    			document.getElementById("retry1_reg").style.display="";    
								            		document.getElementById("retry2_reg").style.display="none";
							    	    			
							    	    			timer_type = 2;
													timer11();	 
													
												}
												else{
													document.getElementById("error_reg").style.display = "block";
													setTimeout(function() {
											      		document.getElementById("error_reg").style.display = "none";
											    	}, 3000);
												}
											}
	};
	xhttp.open("GET", "checkLoginRegno/"+login_regno, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}


function showYes(){
    document.getElementById("nodiv").style.display = "none";
    document.getElementById("yesdiv").style.display = "block";
    document.getElementById("existcount").focus();

    document.getElementById('yesdiv').setAttribute('data-aos', 'slide-up');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });
}

function showNo(){
    document.getElementById("yesdiv").style.display = "none";
    document.getElementById("nodiv").style.display = "block";
    document.getElementById("adj_uscno").focus();

    document.getElementById('nodiv').setAttribute('data-aos', 'slide-up');
    AOS.init({
        initClassName: "aos-init",
        startEvent: "DOMContentLoaded",
    });
    document.getElementById("adj_uscno").focus;
}

function checkUSCNO(stage){
	//alert("hai");
	//document.getElementById("area_code").innerHTML = "<option value=''></option>";
	$("#form-container").hide();
	var uscno = document.getElementById("adj_uscno").value;
	let uscno_length = document.getElementById("adj_uscno").value.length;
	//alert(document.getElementById("adj_uscno").value.length);
	//if(uscno_length == 9){
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				const json = JSON.parse(this.responseText);
				//alert(json.ebs_resp);
				if(json.ebs_resp=="VALID"){
					$("#form-container").show();
					getDefaultLanguage();
					$("#adjsecname").prop( "class", "form-control alert alert-success custom-alert");
					document.getElementById("adjsecname").value = json.section_name;
					//$("#d-secname").show();
					document.getElementById("secname").value = json.section_name;
					$("#secname").prop('readonly', true);							
					document.newreg.section_code.value = json.section_code;
					document.newreg.ero_code.value = json.ero_code;
					document.newreg.csc_number.value = json.csc_number;
													
					var area_code = document.getElementById("area_code");
					for (var i in json.area_data){
						area_code.innerHTML += "<option value='" + json.area_data[i].area_code + "'>" + json.area_data[i].area_name + "</option>";
					}
					if(stage == 2){
						document.getElementById("area_code").value = document.getElementById("xarea_code").value;
					}
				}
				else {
					$("#form-container").hide();
					$("#adjsecname").val("Warning! Invalid Unique Service No.");	
					$("#adjsecname").prop( "class", "form-control alert alert-danger custom-alert");
				}										
			}
				
		};
		xhttp.open("GET", "checkUscno/"+uscno, true); 
	    xhttp.setRequestHeader("Content-type", "application/json");
	    xhttp.send();
	//}
}

function getArea(){
	var uscno = document.getElementById("adj_uscno").value;
	var area_code = document.getElementById("area_code").value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			const json = JSON.parse(this.responseText);
			document.newreg.area_name.value = json.area_name;
			document.newreg.area_group.value = json.area_group;																														
		}
	};
	xhttp.open("GET", "getArea/"+uscno+"/"+area_code, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function changeIdNumLabel(stage){
	if(stage == 1){
		var idtype = document.getElementById("idproof_type").value;
	}
	if(stage == 2){
		var idtype = document.getElementById("xidproof_type").value.trim();
	}
	var idnolabel = document.getElementById("lan_id_no");
	//var name_label = document.getElementById("applicant_name_label");
	if(idtype == "AC"){
		idnolabel.innerHTML = "Aadhar Number";
		//name_label.innerHTML = "Applicant Name as in Aadhar";
	}
	else if(idtype == "DL"){
		idnolabel.innerHTML = "Driving Licence Number";
		//name_label.innerHTML = "Applicant Name as in Driving Licence";
	}
	else if(idtype == "PC"){
		idnolabel.innerHTML = "PAN Card Number";
		//name_label.innerHTML = "Applicant Name as in PAN Card";
	}
	else if(idtype == "RC"){
		idnolabel.innerHTML = "Ration Card Number";
		//name_label.innerHTML = "Applicant Name as in Ration Card";
	}
	else if(idtype == "VC"){
		idnolabel.innerHTML = "Voter Card Number";
		//name_label.innerHTML = "Applicant Name as in Voter Card";
	}
	else if(idtype == "PP"){
		idnolabel.innerHTML = "Passport Number";
		//name_label.innerHTML = "Applicant Name as in Passport";
	}
	else {
		idnolabel.innerHTML = "ID Proof Number";
	}
	
}


function getSubCategory(stage){	
	alert(stage);
	if(stage==1){
		
		document.getElementById("gov-hod-div").style.display = "none";
		document.getElementById("temp-date-div").style.display = "none";
		document.getElementById("municipal-div").style.display = "none";
		document.getElementById("constatus-div").style.display = "none";
		document.getElementById("gstn-y-n-div").style.display = "none";
		document.getElementById("gstn-y-div").style.display = "none";
		document.getElementById("eqpoints-div").style.display = "none";
		document.getElementById("nature_div").style.display = "none";
		document.getElementById("regdoc").value = "";
			
		document.getElementById("sub_category").innerHTML = "<option value=''></option>";
		/*document.getElementById("serv_type").value = "<option value=''></option>";*/
	
		/*$("#subcat-div").load(location.href+" #subcat-div>*","");
		$("#serv-type-div").load(location.href+" #serv-type-div>*","");*/		
	}
	var category = document.getElementById("category").value;
	alert(category);

	if(category !== "1"){
		document.getElementById("constatus-div").style.display = "block";
		document.getElementById("gstn-y-n-div").style.display = "block";
	}
	if(category == "8"){
		document.getElementById("temp-date-div").style.display = "flex";
	}
	
	if(category == "3" || category == "5") {
		//document.getElementById("load-label").innerHTML = "Connected Load in HP";
		document.getElementById("lan_con_load").innerHTML = "Connected Load in HP";
	}	
	else {
		//document.getElementById("load-label").innerHTML = "Connected Load in Watts";
		document.getElementById("lan_con_load").innerHTML = "Connected Load in Watts";	
	}	
		
		
	if(category == "5" || category == "8" || category == "9"){
		document.getElementById("extension").value = "Y";
		document.getElementById("extension").options[2].disabled = true;
	}
		
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			var sub_category = document.getElementById("sub_category");
			json = JSON.parse(this.responseText);
			for (var i in json){
				sub_category.innerHTML += "<option value='" + json[i].subCatCode + "'>" + json[i].subCatDesc + "</option>";
			}
			if(stage == 2){
				document.getElementById('sub_category').value = document.getElementById('xsub_category').value;
			}									
		}
	};
	xhttp.open("GET", "getSubCat/"+category, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getCode1(stage){
	document.getElementById("municipal-div").style.display = "none";
	document.getElementById("eqpoints-div").style.display = "none";
	
	var cat = document.getElementById("category").value;
	
	if(cat == 6){
		getMuncipalCode(stage);		
	}
}

function getMuncipalCode(stage){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
											if (this.readyState == 4 && this.status == 200)
											{	
												document.getElementById("municipal-div").style.display = "block";
												
												var municipality = document.getElementById("municipality");
												json = JSON.parse(this.responseText);
												for (var i in json){
													municipality.innerHTML += "<option value='" + json[i].mcode + "'>" + json[i].mname + "</option>";
												}	
												if(stage ==2){
														document.getElementById("municipality").value = document.getElementById('xmunicipality').value;
												}	
												var subcat = document.getElementById("sub_category").value;
												if(subcat == 1 || subcat == 2 || subcat == 3 || subcat == 4 || subcat == 31 || subcat == 32 ){
													document.getElementById("eqpoints-div").style.display = "block";
												}								
											}
	};
	xhttp.open("GET", "getMcode", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getCode2(stage){
	document.getElementById("gov-hod-div").style.display = "none";
	document.getElementById("nature_div").style.display = "none";
	
	var cat = document.getElementById("category").value;
	var serv_type = document.getElementById("serv_type").value;
	if(serv_type == "G"){
		getGovCode(stage);
	}
	if(serv_type == "NG" && (cat == 2 || cat == 3)){
		getSupNatureCode(stage);
	}
}

function getGovCode(stage){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
											if (this.readyState == 4 && this.status == 200)
											{	
												document.getElementById("gov-hod-div").style.display = "flex";
												var gov_code = document.getElementById("gov_code");
												json = JSON.parse(this.responseText);
												for (var i in json){
													gov_code.innerHTML += "<option value='" + json[i].govCode + "'>" + json[i].govCodeDesc + "</option>";
												}		
												if(stage == 2){
													document.getElementById("gov_code").value = document.getElementById('xgov_code').value;
												}									
											}
	};
	xhttp.open("GET", "getGov", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getSupNatureCode(stage){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
											if (this.readyState == 4 && this.status == 200)
											{	
												document.getElementById("nature_div").style.display = "block";
												var supply_nature = document.getElementById("supply_nature");
												json = JSON.parse(this.responseText);
												for (var i in json){
													supply_nature.innerHTML += "<option value='" + json[i].supNatureCode + "'>" + json[i].supNatureDesc + "</option>";
												}		
												if(stage ==2){
													document.getElementById("supply_nature").value = document.getElementById('xsupply_nature').value;													
												}									
											}
	};
	xhttp.open("GET", "getSupNat", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getHodCode(stage){
	document.getElementById("hod_code").innerHTML = "<option value=''></option>";
	//reloading hod-div for proper label display
	$("#hod_div").load(location.href+" #hod_div>*",""); 
	var govtcd = document.getElementById("gov_code").value;
	if(stage == 2)
	var govtcd = document.getElementById("xgov_code").value;

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
											if (this.readyState == 4 && this.status == 200)
											{	
												var hod_code = document.getElementById("hod_code");
												json = JSON.parse(this.responseText);
												for (var i in json){
													hod_code.innerHTML += "<option value='" + json[i].hodCode + "'>" + json[i].hodCodeDesc + "</option>";
												}	
												if(stage == 2){
													document.getElementById("hod_code").value = document.getElementById('xhod_code').value;
													}																					
											}
	};
	xhttp.open("GET", "getHod/"+govtcd, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    
}


function estimateReq(){
	if(document.getElementById("extension").value.length==0 ) { bounce("extension"); return false;}
	getDocsPaymnt(1);
}


function RegisterDoc(stage){
	document.getElementById("reg-doc-div").style.display = "none";
	//document.getElementById("reg-doc-div2").style.display = "none";	
	var isRegDoc = document.getElementById("regdoc").value;
	if(stage == 2)	
	    isRegDoc = document.getElementById("xregdoc").value;
	if(isRegDoc == "Y"){		
		document.getElementById("reg-doc-div").style.display = "flex";
		//document.getElementById("reg-doc-div2").style.display = "flex";
	}
	getDocsPaymnt(stage);
}

function getDocsPaymnt(stage){
    //document.getElementById("prereq-y-n-div").style.display = "none";
	var category = document.getElementById("category").value;
	var subcategory = document.getElementById("sub_category").value;
	var load = document.getElementById("conn_load").value;
	var regdoc = document.getElementById("regdoc").value;
	var extension = document.getElementById("extension").value;
	
	if(stage == 2){
		var subcategory = document.getElementById("xsub_category").value;
	    var regdoc = document.getElementById("xregdoc").value;
	    var extension = document.getElementById("xextension").value;
	}
       
    if(regdoc == "N")
    	document.getElementById("p_doc").innerHTML = "Indemnity Bond (pdf, max-size : 1MB)";
    if(regdoc == "Y")
    	document.getElementById("p_doc").innerHTML = "Registered Sale Deed (pdf, max-size : 5MB)";
    
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			json = JSON.parse(this.responseText);
			document.getElementById("af").innerHTML = json.af;
			document.getElementById("afgst").innerHTML = json.afgst;
			document.getElementById("sd").innerHTML = json.sd;
			document.getElementById("dc").innerHTML = json.dc;
			document.getElementById("dcgst").innerHTML = json.dcgst;
			document.getElementById("tot").innerHTML = json.tot;													
												
			/*if(document.getElementById("details-prereq-div").style.display == "none"){
				document.getElementById("details-prereq-div").style.display = "block";
				window.scrollBy(0,450);	
			}
				    											
			document.getElementById('details-prereq-div').setAttribute('data-aos', 'slide-up');
		    AOS.init({
		        initClassName: "aos-init",
		        startEvent: "DOMContentLoaded",
		    });		
											           
   			document.getElementById("apply-div").style.display = "flex";	*/		
		}
	};
	xhttp.open("POST","getPreReq",true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({category:category, subcategory:subcategory, load:load, regdoc:regdoc, extension:extension}));
}



function gstnYes(){
	document.getElementById("gstn-y-div").style.display = "none";
	
	var gstn_flag = document.getElementById("gstn_flag").value;
	if(gstn_flag == "Y"){
		document.getElementById("gstn-y-div").style.display = "flex";
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
	document.getElementById("otp_block2").style.display = "none";
	var mobile = document.getElementById("mobile_login").value;
	var xmlHttp = new  XMLHttpRequest();
	if (xmlHttp==null)
		{
			alert ("Browser does not support HTTP Request")
			return
		}
	    		
	xmlHttp.onreadystatechange=function(){
							    	    	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
							    	    	{		
												if(this.response == "BBQES"){
													document.getElementById("otp_block1").style.display = "block";
													window.scrollBy(0,250);
							    	    			document.getElementById("a").focus();
							    	    			document.getElementsByClassName("input_otp").value = "";
							    	    			
							    	    			
							    	    			document.getElementById("retry1").style.display="";    
								            		document.getElementById("retry2").style.display="none";
							    	    			
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
	xmlHttp.open("GET", "getOTP/"+mobile, true);
	xmlHttp.setRequestHeader("Content-type", "application/json");
	xmlHttp.send();
}

function validateOTP(value){
	var check, cotp, a, b, c, d, p, q, r, s;
	if(value == 1){
		var a = document.getElementById("a").value;
		var b = document.getElementById("b").value;
		var c = document.getElementById("c").value;
		var d = document.getElementById("d").value;
		var xmlHttp = new  XMLHttpRequest();
		cotp = a + b + c + d;
	}
	if(value == 2){
		var p = document.getElementById("p").value;
		var q = document.getElementById("q").value;
		var r = document.getElementById("r").value;
		var s = document.getElementById("s").value;
		var xmlHttp = new  XMLHttpRequest();
		cotp = p + q + r + s;
	}	
	if (xmlHttp==null)
	{
		alert ("Browser does not support HTTP Request")
		return
	}	
	xmlHttp.onreadystatechange=function(){					
									    	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
									    	{	
									    		check=xmlHttp.responseText;
									    		if(check == "success"){
										        		//document.getElementById("pageblocker").style.display = "none";
										        		//document.getElementById("otp_entrydiv_service").style.display = "none";  
										        		if(value==1)
											        		document.f1.action="Modify";
											        	if(value==2)
											        		document.f1.action="Modify1";
														document.f1.method="POST";
														document.f1.submit();
										        		return true; 
										        	}
										        	else{
														if(value==1)
											        		document.getElementById("error_otp").style.display = "block";
											        		setTimeout(function() {
													      		document.getElementById("error_otp").style.display = "none";
													    	}, 3000);
											        	if(value==2)
										        			document.getElementById("error_otp_reg").style.display = "block";
										        			setTimeout(function() {
													      		document.getElementById("error_otp_reg").style.display = "none";
													    	}, 3000);
										        	}
											
									    	}
									    	/*if(this.status == 400)
									         {
												document.f1.action="ErrorPage";
												document.f1.submit();
									    		return true;
											 }*/
			    			
	};
	xmlHttp.open("GET", "validateOTP/"+cotp, true);
	xmlHttp.setRequestHeader("Content-type", "application/json");
	xmlHttp.send();
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


function sameAddress(){
	document.getElementById("same-addr-div").style.display = "block";
	
	var checkbox = document.getElementById("same_addr"); 
	if(checkbox.checked ==true){
		document.getElementById("same-addr-div").style.display = "none";
	}

}



function finalSubmit(stage){
	if(document.getElementById("idproof_type").value.length==0 ) { bounce("idproof_type"); }
	if(document.getElementById("idproof_no").value.length==0 ) 	 { bounce("idproof_no"); }
	if(document.getElementById("applicant_name").value.length==0 ) { bounce("applicant_name"); }
	if(document.getElementById("father_name").value.length==0 ) { bounce("father_name"); }
	if(document.getElementById("social_grp").value.length==0 ) 	 { bounce("social_grp"); }
	if(document.getElementById("area_code").value.length==0 ) { bounce("area_code"); }
	
	if(document.getElementById("conn_address1").value.length==0 ) { bounce("conn_address1"); }
	
	if(stage == 1)
		document.newreg.action="save";
	else{
		document.newreg.action="save1";
	}		
	document.newreg.method="POST";
	document.newreg.enctype="multipart/form-data";
	document.newreg.submit();
	
}

function validateInput(id){
	if(document.getElementById(id).value.length==0 ){
		bounce(id);
	}
}

function validateInput1(id,Type){
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

function getSubCategoryPreReq(){	
	
	document.getElementById("sub_category-prereq").innerHTML = "<option value=''></option>";

	$("#subcat-div").load(location.href+" #subcat-div>*","");

	var category = document.getElementById("category-prereq").value;
	if(category == 3 || category == 5){
		document.getElementById("load-label-prereq").innerHTML = "Connected Load in HP";
		document.getElementById("conn_load-prereq").value = "";
		}
	else
		document.getElementById("load-label-prereq").innerHTML = "Connected Load in Watts";	
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
											if (this.readyState == 4 && this.status == 200)
											{	
												var sub_category_prereq = document.getElementById("sub_category-prereq");
												json = JSON.parse(this.responseText);
												for (var i in json){
													sub_category_prereq.innerHTML += "<option value='" + json[i].subCatCode + "'>" + json[i].subCatDesc + "</option>";
												}										
											}
	};
	xhttp.open("GET", "getSubCat/"+category, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function changeMetricPreReq(){
	var category = document.getElementById("category-prereq").value;
	var sub_category = document.getElementById("sub_category-prereq").value;
	if(category == 3 || category == 5 || (category == 6 & (sub_category == 5 || sub_category == 6 || sub_category == 9 || sub_category == 12))){
				document.getElementById("load-label-prereq").innerHTML = "Connected Load in HP";
				document.getElementById("conn_load-prereq").value = "";
	}
	else
		document.getElementById("load-label-prereq").innerHTML = "Connected Load in Watts";	
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


var time = 60;
        var timer = setInterval(downTimer,1000);
        var t;
        var timer_type;

            function downTimer(){
                time-- ;
                if(time<10){
					 t = "0";
				}
				if(timer_type==1){
                	document.getElementById("countdownRetry").textContent = "00"+":"+ t + time;
                }
                if(timer_type==2){
                	document.getElementById("countdownRetry_reg").textContent = "00"+":"+ t + time;
                }
                if(time<1){
					stopit();
					if(timer_type==1){
	                	document.getElementById("retry1").style.display="none";    
			            document.getElementById("retry2").style.display="";
                	}
                	if(timer_type==2){
	                	document.getElementById("retry1_reg").style.display="none";    
			            document.getElementById("retry2_reg").style.display="";
                	}
										
				}
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
	document.getElementById("lan_id_no").innerHTML="ID Proof Number";
    document.getElementById("lan_app_name").innerHTML="Applicant Name as in ID Proof";
    document.getElementById("lan_father_name").innerHTML="Name of the Father/Wife/Husband";
    document.getElementById("lan_sec_name").innerHTML="Section Name";
    document.getElementById("lan_con_addr1").innerHTML="Door No./Building Name";
    document.getElementById("lan_con_addr2").innerHTML="Street/Landmark";
    document.getElementById("lan_con_addr3").innerHTML="Village/City";
    document.getElementById("lan_con_addr4").innerHTML="Mandal/District";
    document.getElementById("lan_con_pin").innerHTML="Pincode";
    document.getElementById("lan_con_mobile").innerHTML="Mobile No.";
    document.getElementById("lan_com_addr1").innerHTML="Door No./Building Name";
    document.getElementById("lan_com_addr2").innerHTML="Street/Landmark";
    document.getElementById("lan_com_addr3").innerHTML="Village/City";
    document.getElementById("lan_com_addr4").innerHTML="Mandal/District";
    document.getElementById("lan_com_pin").innerHTML="Pincode";
    document.getElementById("lan_com_mobile").innerHTML="Mobile No.";
    document.getElementById("lan_con_load").innerHTML="Connected Load (in Watts)";  
    document.getElementById("lan_gst").innerHTML="GSTIN No."; 
    document.getElementById("lan_con_gst").innerHTML="Confirm GSTIN No.";                                        
    document.getElementById("lan_eq_points").innerHTML="EQ Points";
    document.getElementById("lan_doc_no").innerHTML="Document Number";
    document.getElementById("lan_doc_dt").innerHTML="Document Date";
    document.getElementById("lan_reg_off").innerHTML="Registrar Office";
}