package com.mgnrega.usecases;

import java.util.Scanner;

import com.mgnrega.bean.Employee;
import com.mgnrega.bean.Gpm;
import com.mgnrega.dao.BdoDao;
import com.mgnrega.dao.BdoDaoImple;
import com.mgnrega.dao.GpmDao;
import com.mgnrega.dao.GpmDaoImpl;

public class RegisterEmployee {
	public static void REmployee() {
	     Scanner sc= new Scanner(System.in);
	     
	     
	       	System.out.println("Enter Employee id:");
			int id= sc.nextInt();
			
			System.out.println("Enter Employee  Name:");
			String name= sc.next();
			
			System.out.println("Enter Employee  phone:");
			String phone= sc.next();
			
			System.out.println("Enter Employee  address:");
			String address= sc.next();
			
			System.out.println("Enter Employee  totaldaywork:");
			int daywork= sc.nextInt();
			
			System.out.println("Enter Employee wages:");
			String wages= sc.next();
			
			
			GpmDao dao=new GpmDaoImpl();
			
			
			
			Employee mem= new Employee();
			
			mem.setEid(id);
			mem.setEname(name);
			mem.setEmobile(phone);
			mem.setEaddress(address);
			mem.setDayworked(daywork);
			mem.setEwages(wages);
		
		

			String result= dao.registerEmployee(id, name, phone, address, daywork, wages);
			
			System.out.println(result);
	}

}
