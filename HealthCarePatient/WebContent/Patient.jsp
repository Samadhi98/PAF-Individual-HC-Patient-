<%@page import ="model.Patient1" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.js"></script>
<script src="Components/Patient.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Details</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="m-3">Patient Details</h1>
				
				<form id="formPatient" name="formPatient" >

					Enter First Name:
						<input type="text" id="fName" name="fName"
							class="input-group-text">
					<br>
					
					<!-- LAST NAME -->
					Enter Last Name:
						<input type="text" id="lName" name="lName"
							class="input-group-text">
					<br>
					
					<!-- NIC -->
					Enter NIC No:
						<input type="text" id="NIC" name="NIC" class="input-group-text">
					<br>
					
					<!-- AGE -->
					Patient Age:
						<input type="text" id="age" name="age" class="input-group-text">
					<br>
					
					<!-- contactNo -->
					Contact No:
						<input type="text" id="contactNo" name="contactNo"
							placeholder="10 Digits only" maxlenght="10"
							class="input-group-text">
					<br>
					

					<input type="button" id="btnSave" value="Save"
						class="btn btn-primary">
						 <input type="hidden"
						id="hidPIDSave" name="hidPIDSave" value="">
				</form>
			
		
	
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<div id="divPatientsGrid">
			<%
				Patient1 patientObj = new Patient1();
				out.print(patientObj.readPatient1());
			%>
		</div>
	</div>
</div>
</div>

</body>
</html>