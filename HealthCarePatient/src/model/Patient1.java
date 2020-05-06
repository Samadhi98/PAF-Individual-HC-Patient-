package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import com.mysql.cj.xdevapi.Statement;
//import java.sql.Connection;

public class Patient1 {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&serverTimezone=UTC",
				"root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	public String insertPatient1(String fName, String lName, String NIC, String age ,String contactNo)// ID is not allowed to insert
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = "insert into patient(`PID`,`fName`,`lName`,`NIC`,`age`,`contactNo`)"
	 + " values (?, ?, ?, ?, ?,?)";
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1,0);
	 preparedStmt.setString(2, fName);
	 preparedStmt.setString(3, lName);
	 preparedStmt.setString(4, NIC);
	 preparedStmt.setInt(5, Integer.parseInt (age)); 
	 preparedStmt.setInt(6, Integer.parseInt (contactNo)); 
	
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newPatients = readPatient1();
	 output = "{\"status\":\"success\", \"data\": \"" +
			 newPatients + "\"}"; 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the details.\"}"; 
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	//Show elements

	public String readPatient1()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Patient Frist Name</th><th>Patient Last Name</th><th>National Identity Card No</th><th>Age</th><th>Contact Number</th><th>Update</th><th>Remove</th></tr>";
	
	 String query = "select * from patient";
	 Statement stmt =  con.createStatement();//statement added
	 ResultSet rs = stmt.executeQuery(query);// java .sql was added
	 
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String PID = Integer.toString(rs.getInt("PID"));
	 String fName = rs.getString("fName");
	 String lName = rs.getString("lName");
	 String NIC = rs.getString("NIC");
	 String age = Integer.toString(rs.getInt("age"));
	 String contactNo=Integer.toString(rs.getInt("contactNo"));
	 
	 
	 // Add into the html table
	 output += "<tr><td><input id='hidPIDUpdate' name='hidPIDUpdate' type='hidden' value='" + PID +  "'>" + fName+ "</td>";
	 
	 output += "<td>" + lName + "</td>";
	 output += "<td>" + NIC + "</td>";
	 output += "<td>" + age + "</td>";
	 output += "<td>" + contactNo + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
		  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-patientid='"+ PID +"'>"+"</td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Patient Details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String updatePatient1(String PID, String fName, String lName, String NIC, String age, String contactNo)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 // create a prepared statement
	 String query = "UPDATE patient SET fName=?,lName=?,NIC=?,age=?,contactNo=? WHERE PID=?";
	
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 
	 // binding values
	 preparedStmt.setString(1, fName);
	 preparedStmt.setString(2, lName);
	 preparedStmt.setString(3, NIC);
	 preparedStmt.setInt(4, Integer.parseInt(age));
	 preparedStmt.setInt(5, Integer.parseInt(contactNo));
	 preparedStmt.setInt(6, Integer.parseInt(PID));
	 
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newPatients = readPatient1();
	 output = "{\"status\":\"success\", \"data\": \"" +
			 newPatients + "\"}"; 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the patient details\"}"; 
		 
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String deletePatient1(String PID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 
	 
	 // create a prepared statement
	 String query = "DELETE FROM patient WHERE PID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(PID));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newPatients = readPatient1();
	 output = "{\"status\":\"success\", \"data\": \"" + newPatients + "\"}"; 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the patient details.\"}"; 
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
}
