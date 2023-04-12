package employee.crud.dao;

import java.util.List;

import employee.crud.bean.Employee;

public interface EmployeeDAO {
	
	boolean addEmployee(Employee employee);
	
	boolean updateEmployee(Employee employee);
	
	boolean deleteEmployee(int employeeId);
	
	List<Employee> getAll();
	
	Employee getEmployee(int employeeId);

}
