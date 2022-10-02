package com.mgnrega.dao;

import java.util.List;
import com.mgnrega.bean.Bdo;
import com.mgnrega.bean.EmpDTO;
import com.mgnrega.bean.Gpm;
import com.mgnrega.bean.Project;
import com.mgnrega.exceptions.BDOException;
import com.mgnrega.exceptions.GpmException;
import com.mgnrega.exceptions.ProjectException;

public interface BdoDao {
	
     public Bdo loginBDO(String username, String password)throws BDOException;
	
	public String createProject(int pid, String pname, String pcost, String pissuedate);
	
	public List<Project> getAllProjectDetails()throws ProjectException;

	public String registerGPM(int gid, String gname, String gemail, String gpassword, String phone, String gaddress, int bdoid);

	public List<Gpm> getAllGPM()throws GpmException;
	
	
	public String AssignProjectToGPM(int pid, int gid)throws GpmException,ProjectException ;
	
	public List<EmpDTO> getAllEmployeeByPname(String pname)throws ProjectException;

	


}