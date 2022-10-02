package com.mgnrega.usecases;
import java.util.Scanner;
import com.mgnrega.bean.Bdo;
import com.mgnrega.dao.BdoDao;
import com.mgnrega.dao.BdoDaoImple;
import com.mgnrega.exceptions.BDOException;

public class BDOLoginUseCase {
	
        public static void main(String[] args) {
			
        	Scanner sc= new Scanner(System.in);


        	System.out.println("Enter email:");
        	String uname = sc.next();
        	
        	System.out.println("Enter Password:");
        	String pass = sc.next();
        	
        	BdoDao dao = new BdoDaoImple();
        	
        	try {
        		Bdo bd= dao.loginBDO(uname, pass);
        	
         		System.out.println("Welcome to MGNREGA");
         	
         	
        	}catch (BDOException e) {
        		System.out.println(e.getMessage());
        	}
		}	
}




