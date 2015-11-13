package main.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.AppException;
import main.java.Employee;
import main.java.DBUtils;

public class EmployeeDAO {

	public List<Employee> fetchAll() throws AppException{

		List<Employee> empList = new ArrayList<Employee>();

		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM employee");

			rs = ps.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("ID"));
				emp.setFirstName(rs.getString("FIRST_NAME"));
				emp.setLastName(rs.getString("LAST_NAME"));
				emp.setAge(rs.getString("AGE"));
				emp.setGender(rs.getString("GENDER"));
				emp.setPhone(rs.getString("PHONE"));

				empList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally{
		DBUtils.closeResource(ps, rs, con);
		}
		return empList;
	}
	
	public Employee fetchOne(int empID) throws AppException{

		Employee emp=null;
		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM employee WHERE ID=?");
			ps.setInt(1, empID);
			rs = ps.executeQuery();

			if (rs.next()) {
				emp= new Employee();
				emp.setId(rs.getInt("ID"));
				emp.setFirstName(rs.getString("FIRST_NAME"));
				emp.setLastName(rs.getString("LAST_NAME"));
				emp.setAge(rs.getString("AGE"));
				emp.setGender(rs.getString("GENDER"));
				emp.setPhone(rs.getString("PHONE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally{
		DBUtils.closeResource(ps, rs, con);
		}
		return emp;
	}

	public Employee create(Employee emp) throws AppException {
		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("INSERT INTO employee (FIRST_NAME, LAST_NAME, AGE, GENDER, PHONE) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, emp.getFirstName());
			ps.setString(2, emp.getLastName());
			ps.setString(3, emp.getAge());
			ps.setString(4, emp.getGender());
			ps.setString(5, emp.getPhone());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				//emp= new Employee();
				emp.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally{
		DBUtils.closeResource(ps, rs, con);
		}
		return emp;
	}
	
	public Employee update(int EmpId,Employee emp) throws AppException {///////////////////////////////////////////////
		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("UPDATE employee SET FIRST_NAME=?, LAST_NAME=?, AGE=?, GENDER=?, PHONE=? WHERE ID=? ");
			ps.setString(1, emp.getFirstName());
			ps.setString(2, emp.getLastName());
			ps.setString(3, emp.getAge());
			ps.setString(4, emp.getGender());
			ps.setString(5, emp.getPhone());
			ps.setInt(10, EmpId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally{
		DBUtils.closeResource(ps, rs, con);
		}
		return emp;
	}
	
	public Employee delete(int empID) throws AppException {
		Employee emp=null;
		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("DELETE FROM employee WHERE ID=?");
			ps.setInt(1, empID);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally{
		DBUtils.closeResource(ps, rs, con);
		}
		return emp;
	}
}
