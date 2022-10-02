package com.mgnrega.usecases;

import java.util.List;
import java.util.Scanner;

import com.mgnrega.bean.EmpDTO;
import com.mgnrega.dao.BdoDao;
import com.mgnrega.dao.BdoDaoImple;

public class getEmpByPname {

	
	public static void getEmplPname() {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the Project Name");
		
		String cname= sc.next();

		
		BdoDao dao = new BdoDaoImple();
		
		try {
		List<EmpDTO> dtos= dao.getAllEmployeeByPname(cname);
		
		dtos.forEach(dto -> System.out.println(dto));
		
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}


