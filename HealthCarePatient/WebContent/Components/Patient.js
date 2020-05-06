$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") 
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click","#btnSave",function(event) {
			
	// Clear status messages-------------
			$("#alertSuccess").text("");
			$("#alertSuccess").hide();
			$("#alertError").text("");
			$("#alertError").hide();
			
			// Form validation----------------
			var status = validatePatientForm();
			
			// If not valid-------------------
			if (status != true) {
				$("#alertError").text(status);
				$("#alertError").show();
				return;
			}
			// If valid----------------------
			var t = ($("#hidPIDSave").val() == "") ? "POST" : "PUT";
			
			$.ajax(
			{
				url : "PatientAPI",
				type : t,
				data : $("#formPatient").serialize(),
				dataType : "text",
				complete : function(response, status)
				{
					onPatientSaveComplete(response.responseText, status);
				}
			});
		
		});

function onPatientSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divPatientsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidPIDSave").val("");
	$("#formPateint")[0].reset();
}
			
			
			
	// UPDATE==========================================
				$(document).on("click", ".btnUpdate", function(event)
				{
					$("#hidPIDSave").val($(this).closest("tr").find('#hidPIDUpdate').val());
				
				 $("#fName").val($(this).closest("tr").find('td:eq(0)').text());
				 $("#lName").val($(this).closest("tr").find('td:eq(1)').text());
				 $("#NIC").val($(this).closest("tr").find('td:eq(2)').text());
				 $("#age").val($(this).closest("tr").find('td:eq(3)').text());
				 $("#contactNo").val($(this).closest("tr").find('td:eq(4)').text());
				});	
				
				
				//Remove Operation
				$(document).on("click", ".btnRemove", function(event){
					$.ajax(
					{
						url : "PatientAPI",
						type : "DELETE",
						data : "PID=" + $(this).data("patientid"),
						dataType : "text",
						complete : function(response, status)
						{
							onPatientDeletedComplete(response.responseText, status);
						}
					});
				});
				
				function onPatientDeletedComplete(response, status)
				{
					if(status == "success")
					{
						var resultSet = JSON.parse(response);
							
						if(resultSet.status.trim() == "success")
						{
							$("#alertSuccess").text("Successfully Deleted.");
							$("#alertSuccess").show();
									
							$("#divPatientsGrid").html(resultSet.data);
					
						}else if(resultSet.status.trim() == "error"){
							$("#alertError").text(resultSet.data);
							$("#alertError").show();
						}
					}else if(status == "error"){
						$("#alertError").text("Error While Deleting.");
						$("#alertError").show();
					}else{
						$("#alertError").text("Unknown Error While Deleting.");
						$("#alertError").show();
					}
				}
				
				
			
			// CLIENT-MODEL=================================================================
			function validatePatientForm()
			{
			// FRIST NAME
			if ($("#fName").val().trim() == "")
			 {
			 return "Insert patient first name.";
			 }
			// LAST NAME
			if ($("#lName").val().trim() == "")
			 {
			 return "Insert patient last name.";
			 }
			// NIC
			if ($("#NIC").val().trim() == "")
			 {
			 return "Insert patient National Identity Card number.";
			 }
			// AGE
			if ($("#age").val().trim() == "")
			 {
			 return "Insert patient age";
			 }
				// is numerical value
			
			var tmpAge = $("#age").val().trim();
			if (!$.isNumeric(tmpAge))
			 {
			 return "Insert a numerical value for your age.";
			 }
			// CONTACT NUMBER
			if ($("#contactNo").val().trim() == "")
			 {
			 return "Insert patient contact number.";
			 }
			
			// is a numerical value
			var tmpContactNo = $("#contactNo").val().trim();
			if (!$.isNumeric(tmpContactNo))
			 {
			 return "Insert a numerical value for contact number.";
			 }
			
			/*
			// max length exceeded
			var maxlen=$ ("#contactNo").val().trim();
			if ($.lenght(maxlen)<10)
			{
				return "Please check contact number ";
			}*/ //Not working
			
			
			return true;
			} 
			
			
			
			/*function getPatientCard(fName,lName,NIC,age,contactNo )
			{
				var patient = "";
				patient += "<div class=\"patient card bg-light m-2\"style=\"max-width: 10rem; float: left;\">";
				patient += "<div class=\"card-body\">";
				patient += fName;
				patient += "<br>";
				patient += lName;
				patient += "<br>";
				patient += NIC;
				patient += "<br>";
				patient += age;
				patient += "<br>";
				patient += contactNo;
				patient += "<br>";
				
				patient += "</div>";
				patient+= "<input type=\"button\" value=\"Remove\"class=\"btn btn-danger remove\">";
				patient += "</div>";
				return patient;
				}*/