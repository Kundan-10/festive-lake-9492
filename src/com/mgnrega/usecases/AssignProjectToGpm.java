package com.mgnrega.usecases;

import java.util.Scanner;

import com.mgnrega.dao.BdoDao;
import com.mgnrega.dao.BdoDaoImple;
import com.mgnrega.dao.GpmDao;
import com.mgnrega.dao.GpmDaoImpl;

public class AssignProjectToGpm {
	
	public static void ProjecttoGpm() {
		
	Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the project id ");
		int eid= sc.nextInt();

		System.out.println("Enter the  Gpm id ");
		int pid= sc.nextInt();
		
		 BdoDao dao=new BdoDaoImple();
		
		try {
		String result = dao.AssignProjectToGPM(eid, pid);
		
		System.out.println(result);
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}
	}

}
