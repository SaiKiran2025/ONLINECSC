var otp ; 
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
    
    $("#af_pre").html("0.00");
    //if(!checkValidation("category-prereq","Please choose Category of Supply")){return false;}
    //if(!checkValidation("sub_category-prereq","Please choose Sub-Category")){return false;}
    //if(!checkValidation("conn_load-prereq","Pleaseenter connected load")){return false;}
   // if(!checkValidation("regdoc-prereq","Please choose registered documents available or not")){return false;}
     	
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

function test(stage,json){
		$("#form-container").show();
		$("#area_code-div").show();
		$("#area-div").hide();
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
		document.newreg.area_code.value = json.adj_area_code;
		document.newreg.area_name.value = json.adj_area_name;
		document.newreg.area_group.value = json.adj_area_group; 
		$("#area_code").click();
		if(stage == 2){
			document.getElementById("area_code").value = document.getElementById("xarea_code").value;
		}
}

function changeIcon(inputID)
{	
	//alert("hi");
	let xinputID = '#'+inputID;
	$(xinputID).html("<i class='fa fa-search'></i>");
}

function checkUSCNO(stage,ukscno,idx){
	if(idx!=''){
		/* if($("#ukscnos"+idx).val() == ""){
			toastmsg("Please enter Adjacent Unique Service Number", "error");
			$("#ukscnos"+idx).focus();
			return false; 
		} */
		if($("#ukscnos"+idx).val() != "")
		$("#ukscnos"+idx).prop("style","background:url(http://www.xiconeditor.com/image/icons/loading.gif) no-repeat center");
	}else{
		//document.getElementById("noscnos").value = 0;
		/*if($("#adj_uscno").val() == ""){
			toastmsg("Please enter Adjacent Unique Service Number", "error");
			$("#adj_uscno").focus();
			return false;
		}*/
		if($("#adj_uscno").val() != "")
		$("#adj_uscno").prop("style","background:url(http://www.xiconeditor.com/image/icons/loading.gif) no-repeat center");
	}
	//$("#circle-div").load(location.href + " #circle-div");
	//$("#section-div").load(location.href + " #section-div");
	document.getElementById("circle").innerHTML = "<option value=''></option>";
	$("#circle").click();
	$("#circle").blur();
	document.getElementById("section").innerHTML = "<option value=''></option>";
	$("#section").click();
	$("#section").blur();
	//alert("hai");
	//document.getElementById("area_code").innerHTML = "<option value=''></option>";
	$("#form-container").hide();
	//var uscno = document.getElementById("adj_uscno").value;
	var uscno = $('#'+ukscno).val();
	//let uscno_length = document.getElementById("adj_uscno").value.length;
	//alert(document.getElementById("adj_uscno").value.length);
	//if(uscno_length == 9){
	var xhttp = new XMLHttpRequest();
		
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {	
			const json = JSON.parse(this.responseText);
			//alert(json.ebs_resp);
			if(json.ebs_resp=="VALID"){
				if(idx!=''){
					$("#area-div").hide();
					var noscnos=$("#noscnos").val();
					$("#wr"+idx).html("<i class='fa fa-thumbs-up text-success'></i>");	
					if(idx == noscnos){
						document.getElementById("billStatus"+idx).value = json.billstatus;												
						document.getElementById("category"+idx).value = json.billcategory;
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
	//}
}
//Additional Payment
   

/*function checkUSCNO(stage,ukscno,idx){
	//$("#circle-div").load(location.href + " #circle-div");
	//$("#section-div").load(location.href + " #section-div");
	document.getElementById("circle").innerHTML = "<option value=''></option>";
	$("#circle").click();
	$("#circle").blur();
	document.getElementById("section").innerHTML = "<option value=''></option>";
	$("#section").click();
	$("#section").blur();
	//alert("hai");
	//document.getElementById("area_code").innerHTML = "<option value=''></option>";
	$("#form-container").hide();
	//var uscno = document.getElementById("adj_uscno").value;
	var uscno = $('#'+ukscno).val();
	//let uscno_length = document.getElementById("adj_uscno").value.length;
	//alert(document.getElementById("adj_uscno").value.length);
	//if(uscno_length == 9){
	var xhttp = new XMLHttpRequest();
		
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {	
			const json = JSON.parse(this.responseText);
			//alert(json.ebs_resp);
			if(json.ebs_resp=="VALID"){
				if(idx!=''){
					var noscnos=$("#noscnos").val();
					count++;
					$("#wr"+idx).html("<i class='fa fa-thumbs-up text-success'></i>");					
				}
				else{
					$("#form-container").show();
					$("#area_code-div").show();
					$("#area-div").hide();
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
						document.newreg.area_code.value = json.adj_area_code;
						$("#area_code").click();
					}
					if(stage == 2){
						document.getElementById("area_code").value = document.getElementById("xarea_code").value;
					}
				}
			}
			else {
				$("#form-container").hide();
				//<i class="fa fa-exclamation-triangle fa-lg" aria-hidden="true"></i>
				if(idx!=''){
					$("#wr"+idx).html("<i class='fa fa-thumbs-down text-danger'></i>");
				}
				else{
					$("#adjsecname").val("Invalid Service Number");	
					$("#adjsecname").prop( "class", "form-control alert alert-danger custom-alert");
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
	//}
}*/

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


function getAreaList(stage){
	$("#adj_uscno").val('');
	$("#adj_icon").html("<i class='fa fa-search'></i>");
	$("#adjsecname").val('Find your Section Name here');
	$("#adjsecname").prop( "class", "form-control alert alert-primary custom-alert");
	$("#area-div").show();
	$("#area-div").click();
	$("#area_code-div").hide();
	document.getElementById("area").innerHTML = "<option value=''></option>";
	var section_id = document.getElementById("section").value;
	$("#form-container").hide();
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {	
				$("#form-container").show();
				getDefaultLanguage();
				//$("#adjsecname").prop( "class", "form-control alert alert-success custom-alert");
				const json = JSON.parse(this.responseText);
					//document.getElementById("adjsecname").value = json.section_name;
					//$("#d-secname").show();
					document.getElementById("secname").value = json.section_name;
					$("#secname").prop('readonly', true);							
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

function changeIdNumLabel(stage){
	if(stage == 1){
		var idtype = document.getElementById("idproof_type").value;  
	}
	if(stage == 2){
		var idtype = document.getElementById("xidproof_type").value.trim();
	}
	
	var doctype = document.getElementById("doctype") ;
	if( $("#regdoctype").val() == "Registered Sale Deed"){
		doctype.innerHTML = "Registered Sale Deed Copy<span style='color:red'>&nbsp;(PDF) Max.Size 5MB</span>";
	}
	if( $("#regdoctype").val() == "Registered Gift Deed"){
		doctype.innerHTML = "Registered Gift Deed Copy<span style='color:red'>&nbsp;(PDF) Max.Size 5MB</span>";
	}
	if( $("#regdoctype").val() == "Registered Lease Deed"){
		doctype.innerHTML = "Registered Lease Deed Copy<span style='color:red'>&nbsp;(PDF) Max.Size 5MB</span>";
	}
	
	if( $("#category").val() == 5){
		doctype.innerHTML = "Passbook/Patta Document Copy<span style='color:red'>&nbsp;(PDF) Max.Size 5MB</span>";
	}
	
	   
	var idnolabel = document.getElementById("lan_id_no");
	var id_doc_label = document.getElementById("id_doc_label");
	//var name_label = document.getElementById("applicant_name_label");
	if(idtype == "Aadhar copy"){
		idnolabel.innerHTML = "Aadhar Number";
		id_doc_label.innerHTML = "Aadhar Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		//name_label.innerHTML = "Applicant Name as in Aadhar";
		$('#idproof_no').prop("maxLength",12);
	}
	else if(idtype == "Driving Licence"){
		idnolabel.innerHTML = "Driving Licence Number";
		id_doc_label.innerHTML = "Driving Licence Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		//name_label.innerHTML = "Applicant Name as in Driving Licence";
	}
	else if(idtype == "PAN Card"){
		idnolabel.innerHTML = "PAN Card Number";
		id_doc_label.innerHTML = "PAN Card Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		//name_label.innerHTML = "Applicant Name as in PAN Card";
		$('#idproof_no').prop("maxLength",10);
	}
	else if(idtype == "Ration Card"){
		idnolabel.innerHTML = "Ration Card Number";
		id_doc_label.innerHTML = "Ration Card Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		//name_label.innerHTML = "Applicant Name as in Ration Card";
	}
	else if(idtype == "Voter Card"){
		idnolabel.innerHTML = "Voter Card Number";
		id_doc_label.innerHTML = "Voter Card Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		//name_label.innerHTML = "Applicant Name as in Voter Card";
	}
	else if(idtype == "Passport"){
		idnolabel.innerHTML = "Passport Number";
		id_doc_label.innerHTML = "Passport Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
		//name_label.innerHTML = "Applicant Name as in Passport";
		$('#idproof_no').prop("maxLength",8);
	}
	else {
		idnolabel.innerHTML = "ID Proof Number";
		id_doc_label.innerHTML = "ID Proof Copy<span style='color:red'>&nbsp;(JPG/JPEG) Max.Size 100KB</span>";
	}
	
}

function getSubCategory(stage){	
	if(stage==1){
		
		/*document.getElementById("gov-hod-div").style.display = "none";
		document.getElementById("temp-date-div").style.display = "none";
		document.getElementById("municipal-div").style.display = "none";
		document.getElementById("constatus-div").style.display = "none";
		document.getElementById("gstn-y-n-div").style.display = "none";
		document.getElementById("gstn-y-div").style.display = "none";
		document.getElementById("eqpoints-div").style.display = "none";
		document.getElementById("nature_div").style.display = "none";
		document.getElementById("regdoc").value = "";*/
		$("#conn_load").prop('class','form-control'); // removing error border class for load 
		setIntialSetup(2);
		var serv_type = $("#serv_type").val();
		var cat=$("#category").val();
		if(serv_type == "NG" && (cat == 2 || cat == 3 || cat==4 || cat==7)){
		//$("#gov_code").removeAttr("disabled");
    	$("#supply_nature").removeAttr("disabled");
		getSupNatureCode(1);
	}
		
		//$("sub_category").html = "";
		/*document.getElementById("serv_type").value = "<option value=''></option>";*/
	
		/*$("#subcat-div").load(location.href+" #subcat-div>*","");
		$("#serv-type-div").load(location.href+" #serv-type-div>*","");*/		
	}
	//var category = document.getElementById("category").value;
	var category = $("#category").val();

if(category !== "1"){
	
	}
	if(category !== "1"){
		//document.getElementById("constatus-div").style.display = "block";
		//document.getElementById("gstn-y-n-div").style.display = "block";
		$("#cons_status").removeAttr("disabled");
		$("#gstn_flag").removeAttr("disabled");
		//$("#gstno").removeAttr("disabled");
    	//$("#cnfmgstno").removeAttr("disabled");
    	
	}
	if(category == "8"){
		//document.getElementById("temp-date-div").style.display = "flex";
		$("#temp_fmdt").removeAttr("disabled");
    	$("#temp_todt").removeAttr("disabled");
	}
	
	if(category == "3" || category == "4" || category == "5") {
		//document.getElementById("load-label").innerHTML = "Connected Load in HP";
		document.getElementById("lan_con_load").innerHTML = "Connected Load (in HP)";
	}	
	else {
		//document.getElementById("load-label").innerHTML = "Connected Load in Watts";
		document.getElementById("lan_con_load").innerHTML = "Connected Load (in Watts)";	
	}	
		
	if(category == "5" || category == "8" || category == "9"){
		//$("#extension").val("<option value=''></option>");
		$("#extension").val("Y");
		$( "#extension" ).trigger( "click" );
		//$("#extension").prop("selectedIndex", 1);
		//$("#extension option:eq(1)").prop("selected", true);
		//document.getElementById("extension").options[2].disabled = true;
		$("#extension option:eq(0)").prop("disabled", true);
		$("#extension option:eq(2)").prop("disabled", true);
	}
	
	//alert(category);	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			//var sub_category = "";
			var sub_category = "<option value=''></option>";
			json = JSON.parse(this.responseText);
			for (var i in json){
				//alert(json[i].subCatDesc);
				sub_category += "<option value='" + json[i].subCatCode + "'>" + json[i].subCatDesc + "</option>";
			}
			$("#sub_category").html(sub_category);
			//alert(sub_category);
			if(stage == 2){
				document.getElementById('sub_category').value = document.getElementById('xsub_category').value;
			}									
		}
	};
	xhttp.open("GET", "getSubCat/"+category, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}
function getPhase(){
	if($("#category").val()=="6"){
		  if(($("#sub_category").val()=="1" || $("#sub_category").val()=="2" || $("#sub_category").val()=="3" || 
		  $("#sub_category").val()=="4" || $("#sub_category").val()=="31" || $("#sub_category").val()=="32")
		   && (parseInt($("#conn_load").val())>=5000)){
			  //$("#phase").val("3");
			  $("#phase").html("<option value='3'>3</option>")
			  //$("#phase").prop('readonly', true);
		  }
		  else if(($("#sub_category").val()=="5" || $("#sub_category").val()=="6" || $("#sub_category").val()=="9" ||
		   $("#sub_category").val()=="12" )  && (parseInt($("#conn_load").val())>=5)){
			  //$("#phase").val("3");
			  $("#phase").html("<option value='3'>3</option>")
			  //$("#phase").prop('readonly', true);
		  }
		  else{
			$("#phase").html("<option value='1'>1</option>")
		}
		  
	  }else  if( $("#category").val()=="5"){
		 /* $("#phase").val("3");
		  $("#phase").prop('readonly', true);*/
		  $("#phase").html("<option value='3'>3</option>")
	  }else if(($("#category").val()=="1" || $("#category").val()=="7" || $("#category").val()=="2" || $("#category").val()=="9")&&(parseInt($("#conn_load").val())>=5000)){
		 /* $("#phase").val("3");
		  $("#phase").prop('readonly', true);*/
		  $("#phase").html("<option value='3'>3</option>")
	  }else if($("#category").val()=="8"&&parseInt($("#conn_load").val())>=5000){
		  /*$("#phase").val("3");
		  $("#phase").prop('readonly', true);*/
		  $("#phase").html("<option value='3'>3</option>")
	  }
	  else if($("#category").val()=="3"&&parseInt($("#conn_load").val())>=5){
		  /*$("#phase").val("3");
		  $("#phase").prop('readonly', true);*/
		  $("#phase").html("<option value='3'>3</option>")
	  }
	  else if($("#category").val()=="4"&&parseInt($("#conn_load").val())>=5){
		  /*$("#phase").val("3");
		  $("#phase").prop('readonly', true);*/
		  $("#phase").html("<option value='3'>3</option>")
	  }
	  else{
		  $("#phase").html("<option value='1'>1</option>")
	  }
	  $("#phase").click();
}
function getCode1(stage){		
	//document.getElementById("municipal-div").style.display = "none";
	//document.getElementById("eqpoints-div").style.display = "none";
	$("#municipality").attr("disabled", "disabled"); 
	$("#eqpoints").attr("disabled", "disabled"); 
			
	var cat = $("#category").val();
	//alert("cat="+cat);
	if(cat == "6"){
		if($("#sub_category").val()=="5" || $("#sub_category").val()=="6" || $("#sub_category").val()=="9" 
		|| $("#sub_category").val()=="12") {
		//document.getElementById("load-label").innerHTML = "Connected Load in HP";
		document.getElementById("lan_con_load").innerHTML = "Connected Load (in HP)";		
	}
	else{
		document.getElementById("lan_con_load").innerHTML = "Connected Load (in Watts)";
	}
	
	getMuncipalCode(stage);		
	}
}

function getMuncipalCode(stage){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			$("#municipality").removeAttr("disabled");
			//document.getElementById("municipal-div").style.display = "block";
								
			//var municipality = document.getElementById("municipality");
			var municipality = "<option value=''></option>";
			json = JSON.parse(this.responseText);
			for (var i in json){
				municipality += "<option value='" + json[i].mcode + "'>" + json[i].mname + "</option>";
			}	
			if(stage ==2){
				document.getElementById("municipality").value = document.getElementById('xmunicipality').value;
			}	
			$("#municipality").html(municipality);
			var subcat = $("#sub_category").val();
			//alert("subcat"+subcat);
			if(subcat == 1 || subcat == 2 || subcat == 3 || subcat == 4 || subcat == 31 || subcat == 32 ){
				//document.getElementById("eqpoints-div").style.display = "block";
				$("#eqpoints").removeAttr("disabled");
			}								
		}
	};
	xhttp.open("GET", "getMcode", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function getCode2(stage){
	//document.getElementById("gov-hod-div").style.display = "none";
	//document.getElementById("nature_div").style.display = "none";
	
   	//var cat = document.getElementById("category").value;
	var cat = $("#category").val();
	var serv_type = $("#serv_type").val();
	//alert(cat);
	//alert(serv_type);
	
	//$("#gov_code").prop("selectedIndex", 0);
	//$("#hod_code").prop("selectedIndex", 0);
	
	if(serv_type == "G"){
		//$("#gov_code").removeAttr("disabled");
    	$("#gov_code").removeAttr("disabled");
		$("#hod_code").removeAttr("disabled");
		$("#supply_nature").attr("disabled", "disabled"); 
		$("#supply_nature").prop("selectedIndex", 0);
		getGovCode(stage);
	}	
	else if(serv_type == "NG" && (cat == 2 || cat == 3 || cat==4 ||cat==7)){
		//$("#gov_code").removeAttr("disabled");
		$("#gov_code").attr("disabled", "disabled"); 
    	$("#hod_code").attr("disabled", "disabled"); 
    	$("#supply_nature").removeAttr("disabled");
		getSupNatureCode(stage);
	}
	else{
		$("#gov_code").attr("disabled", "disabled"); 
    	$("#hod_code").attr("disabled", "disabled"); 
    	$("#supply_nature").attr("disabled", "disabled"); 
    	
    	$("#gov_code").prop("selectedIndex", 0);
		$("#hod_code").prop("selectedIndex", 0);
		$("#supply_nature").prop("selectedIndex", 0);
	}
}

function getGovCode(stage){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			//document.getElementById("gov-hod-div").style.display = "flex";
			$("#gov_code").removeAttr("disabled");
    		$("#hod_code").removeAttr("disabled");
    	
			var gov_code = "<option value=''></option>";
			
			json = JSON.parse(this.responseText);
			for (var i in json){
				gov_code += "<option value='" + json[i].govCode + "'>" + json[i].govCodeDesc + "</option>";
			}
			//alert(gov_code);	
			$("#gov_code").html(gov_code);
				
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
			//document.getElementById("nature_div").style.display = "block";
			//$("#supply_nature").removeAttr("disabled"); 
			var supply_nature = "<option value=''></option>";
			
			json = JSON.parse(this.responseText);
			for (var i in json){
				supply_nature += "<option value='" + json[i].supNatureCode + "'>" + json[i].supNatureDesc + "</option>";
			}
			//alert(supply_nature);
			$("#supply_nature").html(supply_nature);
					
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
	//$("#hod_div").load(location.href+" #hod_div>*",""); 
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
	if($("#extension").val() == 'Y' ){
		toastmsg("Security Deposit and Development Charges will be collected during Estimate Payment",'error');
	}
	getReqPayment(1);
}


function RegisterDoc(stage){	
	if(document.getElementById("category").value.length == 0){
		toastmsg("Please choose Category",'error');	
		$("#category").focus();
		return false;
	}
	else if(document.getElementById("sub_category").value.length == 0){
		toastmsg("Please choose Sub-Category",'error');	
		$("#sub_category").focus();
		return false;
	}
	else if(document.getElementById("conn_load").value.length == 0){
		toastmsg("Please Enter Connected load",'error');	
		$("#conn_load").focus();
		return false;
	}	
	//document.getElementById("reg-doc-div").style.display = "none";
	//document.getElementById("reg-doc-div2").style.display = "none";	
	var isRegDoc = $("#regdoc").val();
	if(stage == 2)	
	    isRegDoc = document.getElementById("xregdoc").value;
	    
	if(isRegDoc == "Y"){		
		//document.getElementById("reg-doc-div").style.display = "flex";
		//document.getElementById("reg-doc-div2").style.display = "flex";
		$("#regdoctype").removeAttr("disabled");
    	$("#regdocno").removeAttr("disabled");
    	$("#regdocdate").removeAttr("disabled");
    	$("#regdocofc").removeAttr("disabled");
	}
	else if(isRegDoc == "N"){
		toastmsg("3 Times Security Deposit will be collected",'success');
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
    //document.getElementById("prereq-y-n-div").style.display = "none";
    getPhase();
	var category = $("#category").val();
	var subcategory = $("#sub_category").val();
	var load = $("#conn_load").val();
	var regdoc = $("#regdoc").val();
	var extension = $("#extension").val();
	
	if(stage == 2){
		var subcategory = document.getElementById("xsub_category").value;
	    var regdoc = document.getElementById("xregdoc").value;
	    var extension = document.getElementById("xextension").value;
	}
    		                                                       
    if(regdoc == "N")
    	document.getElementById("doctype").innerHTML = "Indemnity Bond <span style='color:red'>&nbsp;(PDF)&nbsp;Max.Size 1MB</span>";
    else if(regdoc == "Y")
    	document.getElementById("doctype").innerHTML = "Registered Sale Deed<span style='color:red'>&nbsp;(PDF)&nbsp;Max.Size 5MB</span>";
    else	
    	document.getElementById("doctype").innerHTML = "Registered Document<span style='color:red'>&nbsp;(PDF)&nbsp;Max.Size 5MB</span>";
    	
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
	/*document.getElementById("gstn-y-div").style.display = "none";
	
	var gstn_flag = document.getElementById("gstn_flag").value;
	if(gstn_flag == "Y"){
		document.getElementById("gstn-y-div").style.display = "flex";
	}*/
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
			document.loginForm.action="regNR";
	        //document.loginForm.action="services";
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
/*function validateOTP(){
	var check, cotp;
		var otp1 = $("#otp1").val();
		var otp2 = $("#otp2").val();
		var otp3 = $("#otp3").val();
		var otp4 = $("#otp4").val();
		var xmlHttp = new  XMLHttpRequest();
		cotp = otp1 + otp2 + otp3 + otp4;
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
				//alert("OTP Verified");	
		        document.loginForm.action="regNR";
		        //document.loginForm.action="services";
				document.loginForm.method="post";
				document.loginForm.submit();
	        }
	        else{
				//alert("OTP failed");	
				$("#error_otp").show();
		    	setTimeout(function() {
					$("#error_otp").hide();
				}, 3000);
		  	}
		}

	};
	xmlHttp.open("GET", "validateOTP/"+cotp, true);
	xmlHttp.setRequestHeader("Content-type", "application/json");
	xmlHttp.send();
}*/


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

function checkFile1(){	
	$("#id_proof").prop('class','form-control');
	 
	var Receiptelement = document.getElementById("id_proof");
	var Receiptname = Receiptelement.value;
	var allowedExtensions = /(\.jpg|\.jpeg)$/i;
    if (!allowedExtensions.exec(Receiptname)) {
    	Receiptelement.value = '';
        toastmsg("Upload jpg or jpeg Format only",'error');	
		$("#id_proof").prop('class','form-control error');
		return false
    }  
    var Receiptfile = Receiptelement.files;           
    var ReceiptSize = Receiptfile[0].size;    
    const ReceiptKb = ReceiptSize / 1024;
	if(ReceiptKb > 100){
		Receiptelement.value = '';
		toastmsg("Maximum File Size allowed 100KB only",'error');	
		$("#id_proof").prop('class','form-control error');
		return false
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
		if(ReceiptMb > 5){
			Receiptelement.value = '';
			toastmsg("Maximum File Size allowed 5MB only",'error');	
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

function finalSubmit(stage,event){
	/*if(document.getElementById("idproof_type").value.length==0 ) { bounce("idproof_type"); }
	if(document.getElementById("idproof_no").value.length==0 ) 	 { bounce("idproof_no"); }
	if(document.getElementById("applicant_name").value.length==0 ) { bounce("applicant_name"); }
	if(document.getElementById("father_name").value.length==0 ) { bounce("father_name"); }
	if(document.getElementById("social_grp").value.length==0 ) 	 { bounce("social_grp"); }
	if(document.getElementById("area_code").value.length==0 ) { bounce("area_code"); }
	
	if(document.getElementById("conn_address1").value.length==0 ) { bounce("conn_address1"); }*/
	//event.preventDefault();
 	if(stage == 1){	
		if($("#scnoext").val() == "no"){
			if(!checkValidation("adj_uscno","Please enter Adjacent Unique Service Number",'error')){return false;}
		}
		if($("#scnoext").val() == "yes"){
			var nsc = $("#noscnos").val();
			for(var i=1 ; i < nsc ; i++){
				if(!checkValidation("ukscnos"+i,"Please enter Adjacent Unique Service Number "+i,'error')){return false;}
			}
		}
		//Applicant Details
		//alert(!checkValidation("idproof_type","Please choose ID proof type"));
		if(!checkValidation("idproof_type","Please choose ID Proof Type",'error')){return false;}
		if(!checkValidation("idproof_no","Please Enter ID Proof Number",'error')){return false;}
		if(!checkValidation("gender","Please Select Honorifics",'error')){return false;}
		if(!checkValidation("applicant_name","Please Enter Applicant Name",'error')){return false;}
		if(!checkValidation("father_name","Please Enter Father/Wife/Husband Name",'error')){return false;}
		if(!checkValidation("social_grp","Please choose Social Group",'error')){return false;}
	
		if(document.getElementById("adj_uscno").value.length != 0){
			if(!checkValidation("area_code","Please choose Area/Village",'error')){return false;}
		}else if(document.getElementById("noscnos").value.length !=0){
			if(!checkValidation("area_code","Please choose Area/Village",'error')){return false;}
		}else{
			if(!checkValidation("area","Please choose Area/Village",'error')){return false;}
		}		
		
		//Address for Connection
		if(!checkValidation("conn_address1","Please Enter Door No./Building Name ",'error')){return false;}
		if(!checkValidation("conn_address2","Please Enter Street/Landmark ",'error')){return false;}
		if($("#conn_address2").val().length < 3){ 
			toastmsg("Please enter minimum 3 characters in Street/Landmark",'error');
		 	$("#conn_address2").focus(); 
		 	return false;
		}
		if(!checkValidation("conn_address3","Please Enter Village/City",'error')){return false;}
		if(!checkValidation("conn_address4","Please Enter Mandal/District",'error')){return false;}
		if(!checkValidation("conn_pincode","Please Enter Pincode",'error')){return false;}
		if(document.getElementById("conn_pincode").value.length < 6){ 
			toastmsg("Please enter 6 digit pincode number",'error'); 
			$("#conn_pincode").focus(); 
			return false;
		}
		if(!checkValidation("mobileno","Please Enter Mobile Number",'error')){return false;}

 		//Address for Communication
		if(!checkValidation("comm_address1","Please Enter Door No./Building Name ",'error')){return false;}
		if(!checkValidation("comm_address2","Please Enter Street/Landmark ",'error')){return false;}
		if(!checkValidation("comm_address3","Please Enter Village/City",'error')){return false;}
		if(!checkValidation("comm_address4","Please Enter Mandal/District",'error')){return false;}
		if(!checkValidation("comm_pincode","Please Enter Pincode",'error')){return false;}
		if($("#comm_pincode").val().length < 6){ 
			toastmsg("Please enter 6 digit pincode number",'error');
		 	$("#comm_phoneno").focus(); 
		 	return false;
		}
		if(!checkValidation("comm_phoneno","Please Enter Mobile Number",'error')){return false;}
		if($("#comm_phoneno").val().length < 10){ 
			toastmsg("Please enter 10 digit mobile number",'error'); 
			$("#comm_phoneno").focus();
			return false;
		}
	
		 
		//Connection Details	 
		if(!checkValidation("category","Please Choose Category",'error')){return false;}
		if(!checkValidation("sub_category","Please Choose Sub-Category",'error')){return false;}
		if(!checkValidation("conn_load","Please Enter Connected Load",'error','error')){return false;}
		if(!checkValidation("serv_type","Please Choose Service type")){return false;}
		if(!checkValidation("location_type","Please Choose Location Type",'error')){return false;}
		if(!checkValidation("sez","Please Choose Special Economic Zone(SEZ) option",'error')){return false;}
		if(!checkValidation("comnserv","Please Choose Common Connection",'error')){return false;}
			 
		//Registration Details	
		if(!checkValidation("gstn_flag","Please Choose GSTN available or not",'error')){return false;}
		if($("#gstn_flag").val() == "Y"){
			if(!checkValidation("gstno","Please enter GSTIN No.",'error')){return false;}
			if(!checkValidation("cnfmgstno","Please confirm GSTIN No.",'error')){return false;}
			if($("#is_gstverified").val() != "yes"){
				toastmsg('Please enter a valid GSTN No.','error');
				$("#gstno").focus(); 
				return false;

			}
		}
		if(!checkValidation("extension","Please Choose whether Extension of Line Required",'error')){return false;}
		if(!checkValidation("regdoc","Please Choose whether registered documents available",'error')){return false;}

		//Conditional fields
		if($("#category").val() == 8){
			if(!checkValidation("temp_fmdt","Please enter Temperory supply starting date",'error')){return false;}
			if(!checkValidation("temp_todt","Please enter Temperory supply ending date",'error')){return false;}
		}
		if($("#category").val() != 1){
			if(!checkValidation("cons_status","Please Choose consumer status type",'error')){return false;}
		}
		if($("#category").val() == 2 || $("#category").val() == 3 || $("#category").val() == 4 || $("#category").val() == 7){	
			if($("#serv_type").val() == "NG"){
				if(!checkValidation("supply_nature","Please Choose nature of premises",'error')){return false;}
			}
		}
		if($("#category").val() == 6){
			if(!checkValidation("sub_category","Please Choose Sub Category",'error')){return false;}
			if(!checkValidation("municipality","Please Choose Municipality",'error')){return false;}
			var sub_category = document.getElementById("sub_category").value;
			if(sub_category == 1 || sub_category == 2 || sub_category == 3 || sub_category == 4 || sub_category == 31 || sub_category == 32){
				if(!checkValidation("eqpoints","Please enter EQ Points",'error')){return false;}
			}
			
		}
		if($("#serv_type").val() == "G"){
			if(!checkValidation("gov_code","Please Choose Govt. Code",'error')){return false;}
			if(!checkValidation("hod_code","Please Choose HOD Code",'error')){return false;}
		}
		
	
		if(document.getElementById("regdoc").value == "Y"){	
			if(!checkValidation("regdoctype","Please Choose registered document type",'error')){return false;}
			if(!checkValidation("regdocno","Please enter registered document no.",'error')){return false;}
			if(!checkValidation("regdocdate","Please enter registered document date",'error')){return false;}
		}	

		//Documents Upload
		if(!checkValidation("id_proof","Please upload ID proof Document",'error')){return false;}
		if(!checkValidation("registration_proof","Please upload Registration Document",'error')){return false;}
		
		
		//ACTION		
		document.newreg.action="saveNR";
	}
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

function getSubCategoryPreReq(){	
	//alert("hi");
	document.getElementById("sub_category-prereq").innerHTML = "<option value=''>Select Sub-Category</option>";

	//$("#subcat-div").load(location.href+" #subcat-div>*","");

	//var category = document.getElementById("category-prereq").value;
	var category = $("#category-prereq").val();
	//alert(category);
	if(category == 3 || category == 4 || category == 5){
		//document.getElementById("load-label-prereq").innerHTML = "Connected Load in HP";
		$("#conn_load-prereq").attr("placeholder", "Connected Load in HP");
		//document.getElementById("conn_load-prereq").value = "";
	}
	else {
		$("#conn_load-prereq").attr("placeholder", "Connected Load in Watts");
		//document.getElementById("load-label-prereq").innerHTML = "Connected Load in Watts";	
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200)
		{	
			//var sub_category_prereq = document.getElementById("sub_category-prereq");
			var sub_category_prereq = "<option value=''>Select Sub-Category</option>";
			json = JSON.parse(this.responseText);
			for (var i in json){
				sub_category_prereq += "<option value='" + json[i].subCatCode + "'>" + json[i].subCatDesc + "</option>";
			}	
			$("#sub_category-prereq").html(sub_category_prereq);									
		}
	};
	xhttp.open("GET", "getSubCat/"+category, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
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

function getHindiLanguage(){
	document.getElementById("lan_id_no").innerHTML="ID नंबर दर्ज करें";
    document.getElementById("lan_app_name").innerHTML="आईडी प्रूफ में आवेदक का नाम";
    document.getElementById("lan_father_name").innerHTML="पिता/पत्नी/पति का नाम";
    document.getElementById("lan_sec_name").innerHTML="सेक्शन नाम";
    document.getElementById("lan_con_addr1").innerHTML="दरवाज़ा नं./भवन का नाम";
    document.getElementById("lan_con_addr2").innerHTML=" स्ट्रीट/लैंडमार्क";
    document.getElementById("lan_con_addr3").innerHTML="गाँव/शहर";
    document.getElementById("lan_con_addr4").innerHTML=" मंडल/जिला";
    document.getElementById("lan_con_pin").innerHTML="पिनकोड";
    document.getElementById("lan_con_mobile").innerHTML="मोबाइल न।";
    document.getElementById("lan_com_addr1").innerHTML="दरवाज़ा नं./भवन का नाम";
    document.getElementById("lan_com_addr2").innerHTML=" स्ट्रीट/लैंडमार्क";
    document.getElementById("lan_com_addr3").innerHTML="गाँव/शहर";
    document.getElementById("lan_com_addr4").innerHTML=" मंडल/जिला";
    document.getElementById("lan_com_pin").innerHTML="पिनकोड";
    document.getElementById("lan_com_mobile").innerHTML="मोबाइल न।";
    document.getElementById("lan_con_load").innerHTML="कनेक्टेड लोड (वाट्स में)";
    document.getElementById("lan_gst").innerHTML="GSTIN नंबर";  
    document.getElementById("lan_con_gst").innerHTML="GSTIN नंबर की पुष्टि करें";    
    document.getElementById("lan_eq_points").innerHTML="EQ पॉइंट्स";      
    document.getElementById("lan_doc_no").innerHTML="डॉक्यूमेंट नंबर";
    document.getElementById("lan_doc_dt").innerHTML="डॉक्यूमेंट तारीख";
    //document.getElementById("lan_reg_off").innerHTML="रजिस्ट्रार ऑफिस";
}

function getTeluguLanguage(){
	document.getElementById("lan_id_no").innerHTML="ID సంఖ్యను నమోదు చేయండి";
    document.getElementById("lan_app_name").innerHTML="ID ప్రూఫ్‌లో ఉన్నట్లుగా దరఖాస్తుదారుని పేరు";
    document.getElementById("lan_father_name").innerHTML="తండ్రి/భార్య/భర్త పేరు";
    document.getElementById("lan_sec_name").innerHTML="సెక్షన్ పేరు";
    document.getElementById("lan_con_addr1").innerHTML="డోర్ నెం./భవనం పేరు";
    document.getElementById("lan_con_addr2").innerHTML="వీధి/ల్యాండ్‌మార్క్";
    document.getElementById("lan_con_addr3").innerHTML="గ్రామం/నగరం";
    document.getElementById("lan_con_addr4").innerHTML=" మండలం/జిల్లా";
    document.getElementById("lan_con_pin").innerHTML="పిన్ కోడ్";
    document.getElementById("lan_con_mobile").innerHTML="మొబైల్ నెం.";
    document.getElementById("lan_com_addr1").innerHTML="డోర్ నెం./భవనం పేరు";
    document.getElementById("lan_com_addr2").innerHTML="వీధి/ల్యాండ్‌మార్క్";
    document.getElementById("lan_com_addr3").innerHTML="గ్రామం/నగరం";
    document.getElementById("lan_com_addr4").innerHTML=" మండలం/జిల్లా";
    document.getElementById("lan_com_pin").innerHTML="పిన్ కోడ్";
    document.getElementById("lan_com_mobile").innerHTML="మొబైల్ నెం.";
    document.getElementById("lan_con_load").innerHTML="కనెక్టెడ్ లోడ్ (వాట్స్ లో)";
    document.getElementById("lan_gst").innerHTML="GSTIN నం.";   
    document.getElementById("lan_con_gst").innerHTML="GSTIN నం. నిర్ధారించండి";  
    document.getElementById("lan_eq_points").innerHTML="EQ పాయింట్స్";
    document.getElementById("lan_doc_no").innerHTML="డాక్యుమెంట్ నెంబర్";
    document.getElementById("lan_doc_dt").innerHTML="డాక్యుమెంట్ తేదీ";
    //document.getElementById("lan_reg_off").innerHTML="రిజిస్ట్రార్ ఆఫీసు";
}

function setIntialSetup(stage){
	//document.getElementById("noscnos").value = 0;
	if(stage==1){
	getCircleList(1);
	}
	
	$("#gov_code").attr("disabled", "disabled"); 
    $("#hod_code").attr("disabled", "disabled"); 
    $("#temp_fmdt").attr("disabled", "disabled"); 
    $("#temp_todt").attr("disabled", "disabled"); 
    $("#municipality").attr("disabled", "disabled"); 
    $("#cons_status").attr("disabled", "disabled"); 
    //$("#gstn_flag").attr("disabled", "disabled"); 
    $("#gstno").attr("disabled", "disabled"); 
    $("#cnfmgstno").attr("disabled", "disabled");
    $("#eqpoints").attr("disabled", "disabled"); 
    //$("#regdoc").attr("disabled", "disabled"); 
    //$("#regdoctype").attr("disabled", "disabled"); 
    //$("#regdocno").attr("disabled", "disabled"); 
    //$("#regdocdate").attr("disabled", "disabled"); 
    //$("#regdocofc").attr("disabled", "disabled"); 
    $("#supply_nature").attr("disabled", "disabled"); 
    
    $("#sub_category").prop("selectedIndex", 0);
	$("#gov_code").prop("selectedIndex", 0);
	$("#hod_code").prop("selectedIndex", 0);
	$("#municipality").prop("selectedIndex", 0);
	$("#conn_load").val("");
	$("#eqpoints").val("");
	$("#extension").prop("selectedIndex", 0);
	//$("#extension").load();
	$("#extension option:eq(0)").prop("disabled", false);
	$("#extension option:eq(2)").prop("disabled", false);
	//document.getElementById("extension").options[2].disabled = false;
	
	$("#regdoc").prop("selectedIndex", 1);
	$("#regdoc").click();
	$("#location_type").prop("selectedIndex", 1);
	$("#location_type").click();
	
	$("#serv_type").prop("selectedIndex", 2);
	
	$("#sez").prop("selectedIndex", 2);
	$("#comnserv").prop("selectedIndex", 2);
	$("#cons_status").prop("selectedIndex", 0);
	$("#gstn_flag").prop("selectedIndex", 0);
	$("#gstno").val("");
	$("#cnfmgstno").val("");
	$("#regdoctype").prop("selectedIndex", 0);
	$("#regdocno").val("");
	$("#regdocofc").val("");
	$("#supply_nature").prop("selectedIndex", 0);
	$("#supply_nature").prop("selectedIndex", 0);regdoc
	$("mobileno").prop("readonly","readonly");
	$("comm_phoneno").prop("readonly","readonly");
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


