// This code dosen't works on Firefox and IE and works on other browsers.
$(document).ready(function () {
$('.animated-icon1,.animated-icon3,.animated-icon4').click(function () {
$(this).toggleClass('open');
});
});

// Works everywhere
$(document).ready(function () {

// Hide/show animation hamburger function
$('.navbar-toggler').on('click', function () {

// Take this line to first hamburger animations
$('.animated-icon1').toggleClass('open');

// Take this line to second hamburger animation
$('.animated-icon3').toggleClass('open');

// Take this line to third hamburger animation
$('.animated-icon4').toggleClass('open');
});

});



document.addEventListener("DOMContentLoaded", init, false);
	
	function init() {
		document.querySelector('#files').addEventListener('change', handleFileSelect, false);
		
	}
		
	function handleFileSelect(e) {
		
		if(!e.target.files) return;		
		var files = e.target.files;
		var fileslist=document.getElementById("fileslist");
		for(var i=0; i<files.length; i++) {
			var f = files[i];
			if(fileslist.value!='')
			{
				fileslist.value=fileslist.value+', '+f.name;
			}
			else
			{
				fileslist.value=f.name;
			}
		}
		
	}
	
	function checkEqual(){
		var p = document.getElementById('pass');
		var rp = document.getElementById('rep-pass');
		var letter = /[a-z]/;
		var upper  =/[A-Z]/;
		var number = /[0-9]/;
		
			

		if(p.value != rp.value)
		{	          
			p.classList.remove("valid");
			// rp.classList.remove("valid");
			if(rp.value!="")
			{
				p.classList.add("invalid");
				rp.classList.add("invalid");
			    var btn = document.getElementById('submit');
			    btn.type="button";
			}
			var btn = document.getElementById('submit');
			btn.type="button";
		}
		else
		{
			p.classList.add("valid");
			rp.classList.add("valid");
			p.classList.remove("invalid");
			rp.classList.remove("invalid");
			var btn = document.getElementById('submit');
			btn.type="submit";
		}
		
	}
	
	
function  editAlert(alertLevel,alertDropDown,alertChangebtn)
{
	var alert=document.getElementById(alertLevel);
	alert.style.display="none";
	document.getElementById(alertDropDown).style.display="inline-block";
	document.getElementById(alertChangebtn).style.display="inline-block";
}
function changeAlert(alert,input) 
{
	document.getElementById(input).value=alert;
}
	

function setModal(button) {
	if(button.id=='red')
	{
		document.getElementById('modal-title').innerHTML='RedAlert';
		document.getElementById('alertLevel').value="RED";
	}
	else
	{		
		document.getElementById('modal-title').innerHTML='OrangeAlert';
		document.getElementById('alertLevel').value="ORANGE";
	}
}

/*Dropdown Menu*/
$('.dropdown').click(function () {
        $(this).attr('tabindex', 1).focus();
        $(this).toggleClass('active');
        $(this).find('.dropdown-menu').slideToggle(300);
    });
    $('.dropdown').focusout(function () {
        $(this).removeClass('active');
        $(this).find('.dropdown-menu').slideUp(300);
    });
    $('.dropdown .dropdown-menu li').click(function () {
        $(this).parents('.dropdown').find('span').text($(this).text());
        $(this).parents('.dropdown').find('input').attr('value', $(this).attr('id'));
    });
/*End Dropdown Menu*/

$(function(){
  $("#meu_div").click(function(){
    $("#iconDown").toggleClass("rotate");
    $("#iconDown").toggleClass("crossRotate");
  });
});


$( document ).ready(function()
		{
	// GET REQUEST
	$("#reportsId").click(function(event)
			{
		event.preventDefault();
		ajaxGet();
			});
	// DO GET
	function ajaxGet(){
		$.ajax({
	
		type:"GET",
		url :"http://localhost:8083/reportController/report",
		success: function(result){
			console.log("success: ", result);
		}
		});
	}
		});

function setComplaintId(val)
{
	id=document.getElementById("complaintId"+val).value;
	document.getElementById("complaintID").value=id;
	
}