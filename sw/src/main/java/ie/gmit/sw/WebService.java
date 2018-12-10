package ie.gmit.sw;

import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// webservice class acts as both the restful server that responds to the webclient but also acts as a rmi client that remotely 
// invokes database functionaility giving persistance to the data. 
@Singleton
@Path("/webservice")
public class WebService {

	//array list of car objects returned from rmii server.
	ArrayList<Car> cars = new ArrayList<Car>();
	//array list of customers objects returned from rmii server.
	ArrayList<Customer> customers = new ArrayList<Customer>();
	//array list of orders objects returned from rmii server.
	ArrayList<Order> orders = new ArrayList<Order>();
	
	public WebService() {		
		init();
	}

	//restful response to get requests at /getcars
	@GET
	@Path("/getcars")
  	@Produces(MediaType.TEXT_XML) //returns as xml using jersey
  	public ArrayList<Car> getVehicles() throws Exception {
		//looks up the remote methods on localhost on port 1099
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//creates a stub to invoke remote methods
			stub = (DatabaseService) registry.lookup("databaseservice");
			//remotely calls getcar method from the rmi interface and fills the car array list
			cars = stub.getCar();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		//returns car array list to webclient as xml
  		return cars;
  	}
	
	//restful response to post requests at /createcar
	@POST
	@Path("/createcar")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createCar(@FormParam("CarID") BigInteger CarID,@FormParam("Make") String Make,@FormParam("Model") String Model, @FormParam("Price") Double Price) throws Exception {
		
		//takes in values from form on the webclient and creates a car object
		Car car = new Car();
		car.setCarID(CarID);
		car.setMake(Make);
		car.setModel(Model);
		car.setPrice(Price);
		
		//looks up the remote methods on localhost on port 1099
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//remotely calls createcar method and returns a conformation message to the webclient
			stub = (DatabaseService) registry.lookup("databaseservice");
			stub.CreateCar(car);
			return "<p>New Car created</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
			
		} catch (NotBoundException e) {
			//if falls returns faill message to the webclient
			e.printStackTrace();
			return "<p>Car Failed to create</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
		}
	}
	//restful response to post requests at /deletecar
	@POST
	@Path("/deletecar")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteCar (@FormParam("CarID")BigInteger CarID) throws Exception {
		
		//looks up the remote methods on localhost on port 1099
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//remotely calls the get car method and fils the car list
			stub = (DatabaseService) registry.lookup("databaseservice");
			cars = stub.getCar();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//searches the car list for a car that matches the delete criteria
		for(Car c : cars){
			
			if(c.getCarID().equals(CarID)) {
				
				try {
					//remotelty calls the remove car method and returns a confermation to the user.
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.removeCar(c);
					return "<p>Car Deleted</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//if criteria not found informs the user.
		return "<p>Car Matching ID not found please see car table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
	}
	
	//restful response to post requests at /updatecar
	@POST
	@Path("/updatecar")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateCar(@FormParam("CarID") BigInteger CarID,@FormParam("Make") String Make,@FormParam("Model") String Model, @FormParam("Price") Double Price) throws Exception {
		//creates new car object matching the form values sent by the webclient
		Car car = new Car();
		car.setCarID(CarID);
		car.setMake(Make);
		car.setModel(Model);
		car.setPrice(Price);
		
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//fills the car list with remotely called get car method
			stub = (DatabaseService) registry.lookup("databaseservice");
			cars = stub.getCar();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//searches car list for car matching update criteria
		for(Car c : cars){
			
			if(c.getCarID().equals(car.getCarID())) {
				
				try {
					//remotely calls updatecar method
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.updateCar(car);
					//returns conformation to the user.
					return "<p>Car Updated</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//if criteria not found  lets uer know.
		return "<p>Car Matching ID not found please see car table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
	}
	//restful response to get requests at /getcustomers
	@GET
	@Path("/getcustomers")
  	@Produces(MediaType.TEXT_XML)
  	public ArrayList<Customer> getCustomers() throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//fills customer list with remotely called getcustomer method.
			stub = (DatabaseService) registry.lookup("databaseservice");
			customers = stub.getCustomer();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		//returns customer list as xml
  		return customers;
  	}
	
	//restful response to post requests at /createcustomer
	@POST
	@Path("/createcustomer")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createCustomer(@FormParam("ID") BigInteger ID,@FormParam("Name") String Name) throws Exception {
		
		//creates customer object from from values passed by web client form
		Customer customer = new Customer();
		customer.setID(ID);
		customer.setName(Name);
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//remotely calls create customer method and returns conformation to the user.
			stub = (DatabaseService) registry.lookup("databaseservice");
			stub.CreateCustomer(customer);
			return "<p>New Customer created</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
			
		} catch (NotBoundException e) {
			//returns failure message
			e.printStackTrace();
			return "<p>Customer Failed to create</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
		}
	}
	
	//restful response to post requests at /deletecustomer
	@POST
	@Path("/deletecustomer")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteCustomer (@FormParam("ID")BigInteger ID) throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//remotely calls get customer method and fills customer list
			stub = (DatabaseService) registry.lookup("databaseservice");
			customers = stub.getCustomer();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//serches customer list for customer matching form criteria
		for(Customer c : customers){
			
			if(c.getID().equals(ID)) {
				
				try {
					//remotely calls remove customer method
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.removeCustomer(c);
					//returns conformation tot he webclient
					return "<p>Customer Deleted</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//if ciriteria not found returns message to webclient.
		return "<p>Customer Matching ID not found please see Customer table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
	}
	
	//restful response to post requests at /updatecustomer
	@POST
	@Path("/updatecustomer")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateCustomer(@FormParam("ID") BigInteger ID,@FormParam("Name") String Name) throws Exception {
		//creates new customer object from values passed by webclient.
		Customer customer = new Customer();
		customer.setID(ID);
		customer.setName(Name);
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//remotely calls get customer to fill customer list
			stub = (DatabaseService) registry.lookup("databaseservice");
			customers = stub.getCustomer();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//searches customer list for customer matching the webclient form details
		for(Customer c : customers){
			
			if(c.getID().equals(customer.getID())) {
				
				try {
					//remotely calls the updatecustomer method.
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.updateCustomer(customer);
					return "<p>Customer Updated</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to customer Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "<p>Customer Matching ID not found please see customer table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
	}
	
	//restful response to post requests at /getorders
	@GET
	@Path("/getorders")
  	@Produces(MediaType.TEXT_XML)
  	public ArrayList<Order> getOrders() throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			//remotely calls the get order method to fill the order lis then returns the order list as xml using jersey to the webclient.
			stub = (DatabaseService) registry.lookup("databaseservice");
			orders = stub.getOrder();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  		return orders;
  	}
	
	//restful response to post requests at /createorder
	@POST
	@Path("/createorder")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createOrder(@FormParam("CustomerID") BigInteger CustomerID,@FormParam("CarID") BigInteger CarID,@FormParam("Start") Date Start,@FormParam("Return") Date Return) throws Exception {
		
		//creates new order
		Order order = new Order();
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		//fils car list customer list and order list
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			cars = stub.getCar();
			customers = stub.getCustomer();
			orders = stub.getOrder();
			//searches cars and sets new orders car to car matching the the carid passed by webclient form
			for(Car c : cars) {
				if(c.getCarID().equals(CarID)) {
					order.setCar(c);
				}
			}
			
			for(Customer cu : customers) {
				//searches customer and sets new orders customer to customer matching the the customerid passed by webclient form
				if(cu.getID().equals(CustomerID)) {
					order.setCustomer(cu);
				}
			}
			//sets start and return dates to values matching the form input values
			order.setStartDate(Start);
			order.setReturnDate(Return);
			//remotely calls the create order method
			stub.CreateOrder(order);
			return "<p>New Order created</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
			
		} catch (NotBoundException e) {
			
			e.printStackTrace();
			return "<p>Order Failed to create</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
		}
	}
	
	//restful response to post requests at /deleteorder
	@POST
	@Path("/deleteorder")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteOrder (@FormParam("ID")BigInteger ID,@FormParam("CarID")BigInteger CarID) throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		//fills orders list from remotely called getorder method
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			orders = stub.getOrder();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//searches order list for order matching serch criteria and deletes order and returns conformation to the user.
		for(Order o : orders){
			
			if(o.getCustomer().getID().equals(ID) && o.getCar().getCarID().equals(CarID)) {
				
				try {
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.removeOrder(o);
					return "<p>Order Deleted</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//returns faillure message if order not found.
		return "<p>Order Matching ID's not found please see Order table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
	}
	
	//restful response to post requests at /updateorder
	@POST
	@Path("/updateorder")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateOrder(@FormParam("CarID") BigInteger CarID,@FormParam("CustomerID") BigInteger CustomerID,@FormParam("StartDate") Date StartDate,@FormParam("ReturnDate") Date ReturnDate) throws Exception {
		//creates new order.
		Order order = new Order();
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
			//fills car list customer list and orders list from remotely called methods.
			try {
				stub = (DatabaseService) registry.lookup("databaseservice");
				cars = stub.getCar();
				customers = stub.getCustomer();
				orders = stub.getOrder();
			//sets car of oreder
			for(Car c : cars) {
				if(c.getCarID().equals(CarID)) {
					order.setCar(c);
				}
			}
			//sets customer of order
			for(Customer cu : customers) {
				if(cu.getID().equals(CustomerID)) {
					order.setCustomer(cu);
				}
			}
			//sets start and return date of order.
			order.setStartDate(StartDate);
			order.setReturnDate(ReturnDate);

			//remotely calls update order and returns conformation message.			
			stub.updateOrder(order);

			return "<p>Order Updated</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
	
			} catch (NotBoundException e) {
				
				e.printStackTrace();
				return "<p>Order Failed to create</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
			}
		}
	
	private void init(){
		
	}
}
