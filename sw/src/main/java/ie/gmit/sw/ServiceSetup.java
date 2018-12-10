package ie.gmit.sw;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServiceSetup extends DatabaseServiceImpl {

	public ServiceSetup() {} 
	   public static void main(String args[]) { 
	      try { 
	         // Instantiating the implementation class 
	    	 DatabaseServiceImpl obj = new DatabaseServiceImpl(); 
	    	 
	         // Exporting the object of implementation class (here we are exporting the remote object to the stub) 
	         DatabaseService stub = (DatabaseService) UnicastRemoteObject.exportObject(obj, 0);  
	         
	         // Binding the remote object (stub) in the registry 
	         LocateRegistry.createRegistry(1099);
	         Registry registry = LocateRegistry.getRegistry("localhost",1099); 
	         
	         registry.bind("databaseservice", stub); 
			 //prints server ready message 
	         System.err.println("Server ready"); 
	      } catch (Exception e) { 
	         System.err.println("Server exception: " + e.toString()); 
	         e.printStackTrace(); 
	      } 
	   } 
	
}
