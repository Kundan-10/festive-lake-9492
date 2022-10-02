package com.mgnrega.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mgnrega.bean.Bdo;
import com.mgnrega.bean.EmpDTO;
import com.mgnrega.bean.Gpm;
import com.mgnrega.bean.Project;
import com.mgnrega.exceptions.BDOException;
import com.mgnrega.exceptions.EmployeeException;
import com.mgnrega.exceptions.GpmException;
import com.mgnrega.exceptions.ProjectException;
import com.mgnrega.utility.DBUtil;

public class BdoDaoImple implements BdoDao{

	@Override
	public Bdo loginBDO(String username, String password) throws BDOException {

		Bdo BDO = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("select * from Bdo where bemail = ? AND bpassword = ?");			
			
			ps.setString(1,  username);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			
				if(rs.next()) {
				
				int r= rs.getInt("bid");
				String e= rs.getString("bemail");
				String p= rs.getString("bpassword");
				
				
			BDO=new Bdo(r, e, p);	
				
				
			}else
				throw new BDOException("Invalid Username or password.. ");
		
		} catch (SQLException e) {
			throw new BDOException(e.getMessage());
		} 

		return BDO;
	}




	@Override
	public String createProject(int pid, String pname, String pcost, String pissuedate) {
	String message = "Not Inserted..";
	
		
	
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into project(pid,pname,pcost,pissuedate) values(?,?,?,?)");
			
			
			
			ps.setInt(1, pid);
			ps.setString(2, pname);
			ps.setString(3, pcost);
			ps.setString(4, pissuedate);
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Project Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
	
		return message;
	}




	@Override
	public List<Project> getAllProjectDetails() throws ProjectException {
		  List<Project> projects= new ArrayList<>();
		  
		  
		 
		  try( Connection conn=DBUtil.provideConnection()) {
			  PreparedStatement ps=conn.prepareStatement("select * from Project");
			  
			  ResultSet rs=ps.executeQuery();
			  while(rs.next()) {
				  int r=rs.getInt("pid");
				  String n=rs.getString("pname");
				  String c=rs.getString("pcost");
				  String d=rs.getString("pissuedate");
				  int rr=rs.getInt("gpmID");
				  
				  Project project=new Project(r,n,c,d,rr);
				  
				  projects.add(project);
			  }
			  
			
		} catch (SQLException e) {
			throw new ProjectException(e.getMessage());
		}
		  
	    if(projects.size()==0) 
	    	throw new ProjectException("No Projects found....");
	 
	    return projects;  
	}


	@Override
	public String registerGPM(int gid, String gname, String gemail, String gpassword, String gmobile, String gaddress,
			int bdoid) {
              
		String message="NOt Inserted....";
		
		try (Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into Gpm(gid,gname,gemail,gpassword,gmobile,gaddress) values(?,?,?,?,?,?)");
			
			
			
			ps.setInt(1, gid);
			ps.setString(2, gname);
			ps.setString(3, gemail);
			ps.setString(4, gpassword);
			ps.setString(5, gmobile);
			ps.setString(6, gaddress);
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Grampachayat member Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

	
@Override
	public List<Gpm> getAllGPM() throws GpmException {
	
		 List<Gpm> gpms= new ArrayList<>();
		  
		  try( Connection conn=DBUtil.provideConnection()) {
			  PreparedStatement ps=conn.prepareStatement("select * from Gpm");
			  
			  ResultSet rs=ps.executeQuery();
			  while(rs.next()) {
				  
				  
				    int id= rs.getInt("gid");
					String n= rs.getString("gname");
					String e= rs.getString("gemail");
					String p= rs.getString("gpassword");
					String m= rs.getString("gmobile");
					String a = rs.getString("gaddress");
					int bdoid = rs.getInt("bdoid");
				  
				  Gpm gpm=new Gpm(id, n, e, p, m, a, bdoid);
				  
				  gpms.add(gpm);
			  }
			  
			
		} catch (SQLException e) {
			throw new GpmException(e.getMessage());
		}
		  
	    if(gpms.size()==0) 
	    	throw new GpmException("No Grampanchaytmember found....");
	 
	    return gpms;  
		
	}



// TODO
	
	@Override
	public String AssignProjectToGPM(int pid, int gid) throws GpmException, ProjectException {
		String massage="Not allocated";
		

	try(Connection conn= DBUtil.provideConnection()) {
			
		 	PreparedStatement ps= conn.prepareStatement("select * from project where pid =?");
			
		 	ps.setInt(1, pid);
		 	
		 	ResultSet rs= ps.executeQuery();
			
		 	if(rs.next()) {
		 		
		 		PreparedStatement ps2= conn.prepareStatement("select * from gpm where gid=?");
		 		
		 		ps2.setInt(1, gid);

		 		ResultSet rs2= ps2.executeQuery();
		 		
		 		if(rs2.next()) {
		 	
		 			PreparedStatement ps3= conn.prepareStatement("insert into project_gpm values(?,?)");
		 		
		 			ps3.setInt(1, pid);
		 			ps3.setInt(2, gid);
		 			
		 			int x= ps3.executeUpdate();
		 			
		 			if(x > 0)
		 				massage = "Project registered inside the Gpm Sucessfully.. ";
		 			else
		 				throw new ProjectException("Techical error..");
				
		 		}
		 		else
		 			throw new GpmException("Invalid project id and  gpmid...");
		
		 	}else
		 		throw new GpmException("Invalid gpm id...");
		
		} catch (SQLException e) {
			throw new ProjectException (e.getMessage());
		}

		return massage;
	}




	@Override
	public List<EmpDTO> getAllEmployeeByPname(String pname) throws ProjectException {
	
		List<EmpDTO> edtos = new ArrayList<>();
	
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("select e.ename, e.emobile,e.eaddress, e.etotaldaywork, e.ewages, p.pname "
														+ "from  employee e INNER JOIN project p INNER JOIN project_employee pe "
														+ "ON e.eid = pe.eid AND p.pid = pe.pid AND p.pname= ?");
			
			ps.setString(1, pname);
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				String n= rs.getString("ename");
				String m= rs.getString("emobile");
				
				String a= rs.getString("eaddress");
				int d= rs.getInt("etotaldaywork");
				int w = rs.getInt("ewages");
				String p = rs.getString("pname");
//				int c = rs.getInt("pid");
				
				EmpDTO dto = new EmpDTO(n, m, a, d, w, p);
				
				edtos.add(dto);
				
				
				
			}		
			
		} catch (SQLException e) {
			throw new ProjectException(e.getMessage());
		}
			
		if(edtos.isEmpty())
			throw new ProjectException("No Employee found in the Project");
			
		return edtos;	
		
	}
}





	
      


