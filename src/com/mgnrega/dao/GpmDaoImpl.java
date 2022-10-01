package com.mgnrega.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mgnrega.bean.EmpDTO;
import com.mgnrega.bean.Employee;
import com.mgnrega.bean.Gpm;
import com.mgnrega.exceptions.EmployeeException;
import com.mgnrega.exceptions.GpmException;
import com.mgnrega.exceptions.ProjectException;
import com.mgnrega.utility.DBUtil;

public class GpmDaoImpl implements GpmDao{

	@Override
	public Gpm loginGPM(String username, String password) throws GpmException {
	  
		Gpm gpm = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
		
			PreparedStatement ps= conn.prepareStatement("select * from gpm where gemail = ? AND gpassword = ?");			
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
		
				if(rs.next()) {
				
				int id= rs.getInt("gid");
				String n= rs.getString("gname");
				String e= rs.getString("gemail");
				String p= rs.getString("gpassword");
				String m = rs.getString("gmobile");
				String a = rs.getString("gaddress");
				int bdoid = rs.getInt("bdoid");
				
				gpm =new Gpm (id, n, e, p, m, a, bdoid);	
			
			}else
				throw new GpmException("Invalid Username or password.. ");
			
		} catch (SQLException e) {
			throw new GpmException(e.getMessage());
		}
		
		return gpm;
	}

	@Override
	public String registerEmployee(int eid, String ename, String emobile, String eaddress, int etotaldaywork,
			String ewages) {
		
         String message = "Not Inserted..";
	
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into employee(eid,ename,emobile,eaddress,etotaldaywork,ewages) values(?,?,?,?,?,?)");
			
			
			ps.setInt(1, eid);
			ps.setString(2, ename);
			ps.setString(3, emobile);
			ps.setString(4, eaddress);
			ps.setInt(5, etotaldaywork);
			ps.setString(6, ewages);
			
			int x= ps.executeUpdate();
			
			if(x > 0)
				message = "Employee Registered Sucessfully !";
		
		} catch (SQLException e) {
			message = e.getMessage();
		}
	
		return message;
	}

	@Override
	public List<Employee> getAllEmp() throws EmployeeException {
	
		List<Employee> emps=new ArrayList<>();
		
		  try( Connection conn=DBUtil.provideConnection()) {
			  PreparedStatement ps=conn.prepareStatement("select * from Employee");
			  
			  ResultSet rs=ps.executeQuery();
			  while(rs.next()) {
				  
				  
				    int id= rs.getInt("eid");
					String n= rs.getString("ename");
					String e= rs.getString("emobile");
					String p= rs.getString("eaddress");
					int m= rs.getInt("etotaldaywork");
					String a = rs.getString("ewages");
					
				  
				  Employee emp=new Employee(id, n, e, p, m, a);
				  
				  emps.add(emp);
			  }
			  
			
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
		  
	    if(emps.size()==0) 
	    	throw new EmployeeException("No Employee found....");
	 
		return emps;
		
	}

	@Override
	public String registerEmployeeToProject(int pid, int eid) throws ProjectException, EmployeeException {
		
		String message ="Not Resgistered";
		
		
		try(Connection conn= DBUtil.provideConnection()) {
			
		 	PreparedStatement ps= conn.prepareStatement("select * from employee where eid =?");
			
		 	ps.setInt(1, eid);
		 	
		 	ResultSet rs= ps.executeQuery();
			
		 	if(rs.next()) {
		 		
		 		PreparedStatement ps2= conn.prepareStatement("select * from project where pid=?");
		 		
		 		ps2.setInt(1, pid);

		 		ResultSet rs2= ps2.executeQuery();
		 		
		 		if(rs2.next()) {
		 	
		 			PreparedStatement ps3= conn.prepareStatement("insert into project_employee values(?,?)");
		 		
		 			ps3.setInt(1, eid);
		 			ps3.setInt(2, pid);
		 			
		 			int x= ps3.executeUpdate();
		 			
		 			if(x > 0)
		 				message = "Student registered inside the Course Sucessfully.. ";
		 			else
		 				throw new EmployeeException("Techical error..");
				
		 		}
		 		else
		 			throw new ProjectException("Invalid Course...");
		
		 	}else
		 		throw new EmployeeException("Invalid Student...");
		
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}

		return message;
	}

	@Override
	public List<Employee> getEmployeeByMobile(String emobile) throws EmployeeException  {
		
	    List<Employee> emp=new ArrayList<>();
	    
		
		try(Connection conn = DBUtil.provideConnection()) {
		
			PreparedStatement ps= conn.prepareStatement("select * from Employee where  emobile= ?");			
			
			ps.setString(1, emobile);
		
			
			ResultSet rs= ps.executeQuery();
		
				while(rs.next()) {
				
				int id= rs.getInt("eid");
				String n= rs.getString("ename");
				String m = rs.getString("emobile");
				String a = rs.getString("eaddress");
				int bdoid = rs.getInt("etotaldaywork");
				String v = rs.getString("ewages");
				
				Employee	emp1 =new Employee (id,n,m,a,bdoid,v);	
				emp.add(emp1);
			
			}
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
			
		if(emp.isEmpty())
			throw new EmployeeException("No Employee found in the Project");
			
		return emp;	
	}

}
