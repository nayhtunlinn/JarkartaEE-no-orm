package employee.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import employee.crud.bean.Employee;
import employee.crud.db.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private static Connection con=DBConnection.getConnection();

	@Override
	public boolean addEmployee(Employee employee) {
		
		System.out.println("=====Employee start add====");
		
		String insertStatement="insert into employee (name,email,phone,address) values (?,?,?,?)";
		try(PreparedStatement preparedStatement=con.prepareStatement(insertStatement);) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3,employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());
			int result=preparedStatement.executeUpdate();
			return result==1?true:false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		
		System.out.println("======Employee start update=======");
		String updateStatement="update employee set name=?,email=?,phone=?,address=? where id=?";
		try(PreparedStatement preparedStatement=con.prepareStatement(updateStatement)){
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setInt(5, employee.getId());
			int result=preparedStatement.executeUpdate();
			return result==1?true:false;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		
		String deleteStatement="delete from employee where id=?";
		try(PreparedStatement preparedStatement=con.prepareStatement(deleteStatement)){
			preparedStatement.setInt(1, employeeId);
			int result=preparedStatement.executeUpdate();
			return result==1?true:false;	
		}catch(Exception e) {
			e.printStackTrace();
			 return false;	
		}
	}

	@Override
	public List<Employee> getAll() {
		
		String selectAllStatement="select * from employee";
		try(PreparedStatement preparedStatement=con.prepareStatement(selectAllStatement)){
			ResultSet result=preparedStatement.executeQuery();
			List<Employee> empList=new ArrayList<>();
			while(result.next()) {
				Employee emp=new Employee();
				emp.setId(result.getInt("id"));
				emp.setName(result.getString("name"));
				emp.setEmail(result.getString("email"));
				emp.setPhone(result.getString("phone"));
				emp.setAddress(result.getString("address"));
				empList.add(emp);
			}
			
			return empList;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee getEmployee(int employeeId) {
		// TODO Auto-generated method stub
		String selectStatement="select * from employee where id=?";
		try(PreparedStatement preparedStatement=con.prepareStatement(selectStatement)){
			preparedStatement.setInt(1, employeeId);
			ResultSet result=preparedStatement.executeQuery();
			Employee emp=new Employee();
			while(result.next()) {
			emp.setId(result.getInt("id"));
			emp.setName(result.getString("name"));
			emp.setEmail(result.getString("email"));
			emp.setPhone(result.getString("phone"));
			emp.setAddress(result.getString("address"));
			}
			return emp;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		/*
		 * Employee emp=new Employee(); emp.setId(3); emp.setName("John Wick");
		 * emp.setEmail("JohnWick@gmail.com"); emp.setPhone("015696514584");
		 * emp.setAddress("Yangon");
		 */
		
		EmployeeDAOImpl employeeDAOImpl=new EmployeeDAOImpl();
		System.out.println(employeeDAOImpl.getEmployee(1).toString());
		/* List<Employee> empList=employeeDAOImpl.getAll(); */
		
		/*
		 * for(Employee e:empList) { System.out.println(e.toString()); }
		 */
	}

}
