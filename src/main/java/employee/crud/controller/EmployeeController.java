package employee.crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import employee.crud.bean.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.dao.EmployeeDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet(name="servlet2",urlPatterns = "/")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmployeeDAO employeeDAO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		employeeDAO=new EmployeeDAOImpl();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("EmployeeController, doPost method started");
		String action = request.getServletPath();
		System.out.println("doPost, action==>"+action);

		switch (action) {
		case "/add":
			addNewEmployee(request, response);
			break;
		case "/update":
			updateEmployee(request, response);
			break;
		case "/delete":
			deleteEmployee(request, response);
			break;
		case "/list":
			getAllEmployees(request, response);
			break;
		case "/get":
			getEmployee(request, response);
			break;
		default:
			getAllEmployees(request, response);
			break;
		}
	}

	private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Get One Employee method start");
		int id=Integer.parseInt(request.getParameter("employeeId"));
		System.out.println("Employee ID ==> "+id);
		Employee emp=employeeDAO.getEmployee(id);
		System.out.println(emp);
		
		try {
			ObjectWriter mapper=new ObjectMapper().writer().withDefaultPrettyPrinter();
			String employeeStr=mapper.writeValueAsString(emp);
			ServletOutputStream servletOutputStream=response.getOutputStream();
			servletOutputStream.write(employeeStr.getBytes());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	private void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("Employee get all method start");
			List<Employee> empList=employeeDAO.getAll();
			for(Employee e:empList) {
				System.out.println(e);
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher("/employeesview.jsp");
			try {
				request.setAttribute("employees", empList);
				dispatcher.forward(request, response);
				
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			} 
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Employee Delete Method Start");
		String employeeIds=request.getParameter("employeeIds");
		System.out.println("delete employee id ==> "+employeeIds);
		StringTokenizer tokenizer=new StringTokenizer(employeeIds,",");
		while(tokenizer.hasMoreElements()) {
			int id=Integer.parseInt(tokenizer.nextToken());
			boolean result=employeeDAO.deleteEmployee(id);
			System.out.println("Delete Employee, result is ==>"+result);
		}
		List<Employee> empList=employeeDAO.getAll();
		
		  RequestDispatcher
		  dispatcher=request.getRequestDispatcher("/employeesview.jsp");
		 
		try {
			request.setAttribute("employees", empList);
			 dispatcher.forward(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Employee Update Method Start");
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		Employee emp=new Employee(id, name, email, phone, address);
		System.out.println("Update Employee details ==>"+emp);
		boolean result=employeeDAO.updateEmployee(emp);
		System.out.println("Update Employee, result is ==>"+result);
		List<Employee> empList=employeeDAO.getAll();
		RequestDispatcher dispatcher=request.getRequestDispatcher("/employeesview.jsp");
		try {
			request.setAttribute("employees", empList);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
	}

	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Employee Add Method Start");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		Employee emp=new Employee(name, email, phone, address);
		System.out.println("Add Employee object==> "+emp);
		boolean result=employeeDAO.addEmployee(emp);
		System.out.println("Adding Employee, result is ==>"+result);
		List<Employee> empList=employeeDAO.getAll();
		RequestDispatcher dispatcher=request.getRequestDispatcher("/employeesview.jsp");
		try {
			request.setAttribute("employees", empList);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}
}
