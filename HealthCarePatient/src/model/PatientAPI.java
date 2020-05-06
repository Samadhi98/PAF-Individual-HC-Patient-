package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Servlet implementation class PatientAPI
 */
@WebServlet("/PatientAPI")
public class PatientAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Patient1 patientObj = new Patient1();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = patientObj.insertPatient1(request.getParameter("fName"),
				request.getParameter("lName"),
				request.getParameter("NIC"),
				request.getParameter("age"),
				request.getParameter("contactNo"));

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> paras = getParasMap(request);
		
		 String output = patientObj.updatePatient1(paras.get("hidPIDSave").toString(),
		 paras.get("fName").toString(),
		 paras.get("lName").toString(),
		paras.get("NIC").toString(),
		paras.get("age").toString(),
		paras.get("contactNo").toString());
		 
		response.getWriter().write(output); 
		

		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, String> paras = getParasMap(request);
		
		 String output = patientObj.deletePatient1(paras.get("PID").toString());
		
		 response.getWriter().write(output); 
	}

	
	// Convert request parameters to a Map
	private static Map<String, String> getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
}
