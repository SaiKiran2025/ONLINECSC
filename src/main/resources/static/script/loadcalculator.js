var r=1;
function addNewAppliance() 
{
    var table = document.getElementById("loadTable");
    var row = table.insertRow(r);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	var cell7 = row.insertCell(6);
	
    cell1.innerHTML = "<tr><td class='text-center'>"+r+"</td>";
    cell2.innerHTML = "<td><select name='apptype"+r+"' id='apptype"+r+"' onblur='getMinLoad("+r+")' class='form-control w-220'><option value=''>Select Appliance</option><option value='1'>LED Bulbs 5-12 Watts</option><option value='2'>CFL Bulbs 20-60 Watts</option><option value='3'>Incandes Cent Bulbs 60-100 Watts</option><option value='4'>Tube Lights 36 Watts</option><option value='5'>TV 100-200 Watts</option><option value='6'>Mixer 500-750 Watts</option><option value='7'>Refrigerator 250-400 Watts</option><option value='8'>Fans 50-75 Watts</option><option value='9'>Washing M/c 250-400 Watts</option><option value='10'>Cooler 200-300 Watts</option><option value='11'>Water Motor 373 Watts</option><option value='12'>Bore Motor 746-1119 Watts</option><option value='13'>Heater 750-1000 Watts</option><option value='14'>Oven 750-1000 Watts</option><option value='15'>Inverter 300-500 Watts</option><option value='16'>AC 1500-2500 Watts</option><option value='17'>Chimney 180-240 Watts</option><option value='18'>Iron Box 750-1100 Watts</option><option value='19'>Printer 30-50 Watts</option><option value='20'>Rice Cooker 300-600 Watts</option><option value='21'>Others</option></select><input type='hidden' name='minld"+r+"' id='minld"+r+"' ></td>";
	cell3.innerHTML = "<td><input type='number' name='cap"+r+"' id='cap"+r+"' value=0 onblur='getLoad("+r+");' class='form-control w-100'></td>";
	cell4.innerHTML = "<td><input type='number' name='nos"+r+"' id='nos"+r+"' value=0 onblur='getLoad("+r+");' class='form-control w-100'></td>";
	cell5.innerHTML = "<td><input type='number' name='load"+r+"' id='load"+r+"' value=0 class='form-control w-100' readOnly></td>";
	cell6.innerHTML = "<td><button type='button' class='text-center' id='add"+r+"' name='add"+r+"' onclick='addNewAppliance()' title='Add Appliance'><i class='fa fa-plus-circle fa-lg text-success' aria-hidden='true'></i></button></td>";
	cell7.innerHTML = "<td><button type='button' id='delete"+r+"' name='delete"+r+"' onclick='deleteAppliance()' title='Delete Appliance'><i class='fa fa-trash fa-lg text-danger' aria-hidden='true'></button></td></tr>";
	r++;
}

function deleteAppliance() {
	if(r>2)
	{
		r--;
		document.getElementById("loadTable").deleteRow(r);
		getTot();
	}
}

function getLoad(rn)
{
	if(parseInt(document.getElementById("cap"+rn).value)>0&&parseInt(document.getElementById("nos"+rn).value))
	{
		document.getElementById("load"+rn).value=(parseInt(document.getElementById("cap"+rn).value)*parseInt(document.getElementById("nos"+rn).value));
		getTot();
	}
}

function getTot()
{
	total=0;
	for(var l=1;l<r;l++){
		total=total+parseInt(document.getElementById("load"+l).value);
	}
	document.getElementById("gTotal").value=total;
	document.getElementById("loadTotal").innerHTML=total
}

function getMinLoad(ld)
{
	var mld=0;
	var aptp=parseInt(document.getElementById("apptype"+ld).value);
	if(document.getElementById("apptype"+ld).value.length>0)
	{
		switch(aptp) {
		case 1:
			mld=5;
			break;
		case 2:
			mld=20;
			break;
		case 3:
			mld=60;
			break;
		case 4:
			mld=40;
			break;
		case 5:
			mld=100;
			break;
		case 6:
			mld=500;
			break;
		case 7:
			mld=250;
			break;
		case 8:
			mld=50;
			break;
		case 9:
			mld=250;
			break;
		case 10:
			mld=200;
			break;
		case 11:
			mld=373;
			break;
		case 12:
			mld=746;
			break;
		case 13:
			mld=750;
			break;
		case 14:
			mld=750;
			break;
		case 15:
			mld=300;
			break;
		case 16:
			mld=1500;
			break;
		case 17:
			mld=180;
			break;
		case 18:
			mld=750;
			break;
		case 19:
			mld=30;
			break;
		case 20:
			mld=300;
			break;
		case 21:
			mld=0;
			break;
		default:
			mld=0;
		}
	}
	document.getElementById("minld"+ld).value=mld;
}

function captureLoad()
{
	var tot=0;
	var chk=true;
	var i=0;
	for(var l=1;l<r;l++)
	{
		if(parseInt(document.getElementById("minld"+l).value)>parseInt(document.getElementById("cap"+l).value))
		{
			chk=false;
			i=l;
			break;
		}
	}
	if(chk)
	{
		if(document.getElementById("gTotal").value.length!=0&&parseInt(document.getElementById("gTotal").value)>0)
		{
			var catid=document.getElementById("category").value;
			if(catid=="3" || catid=="5" || catid=="6"){
				tot=parseInt(document.getElementById("gTotal").value)/746;
				tot=Math.round(tot);
			}else{
				tot=document.getElementById("gTotal").value;
			}
			document.getElementById("conn_load").value=tot;
			document.getElementById("capture").setAttribute("data-dismiss", "modal");
		}
		else
		{
			document.getElementById("conn_load").value="0";
			alert("Please Select Load Particulars");
			//return false;
		}
	}
	else
	{   document.getElementById("conn_load").value="0";
		alert("Wattage should not be less than Minimum watts");
		document.getElementById("cap"+i).focus();
		document.getElementById("cap"+i).select();
		//return false;
	}
	//$("#modal-load-calc").reload();
}