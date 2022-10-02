package com.mgnrega.usecases;

import java.util.Scanner;

import com.mgnrega.dao.BdoDao;
import com.mgnrega.dao.GpmDao;
import com.mgnrega.dao.GpmDaoImpl;

public class RegistarEmpToProject {
	
	public static void emptoPro() {
		
	
		
		
	Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the ProjectID");
		int pid= sc.nextInt();

		System.out.println("Enter the EmployeeID");
		int eid= sc.nextInt();
		
		 GpmDao dao=new GpmDaoImpl();
		
		try {
		String result = dao.registerEmployeeToProject(pid, eid);
		
		System.out.println(result);
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}
	}

}
