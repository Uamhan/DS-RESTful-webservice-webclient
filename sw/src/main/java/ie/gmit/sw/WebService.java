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


@Singleton
@Path("/webservice")
public class WebService {

	
	ArrayList<Car> cars = new ArrayList<Car>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Order> orders = new ArrayList<Order>();
	
	public WebService() {		
		init();
	}

	
	@GET
	@Path("/getcars")
  	@Produces(MediaType.TEXT_XML)
  	public ArrayList<Car> getVehicles() throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			cars = stub.getCar();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  		return cars;
  	}
	
	@POST
	@Path("/createcar")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createCar(@FormParam("CarID") BigInteger CarID,@FormParam("Make") String Make,@FormParam("Model") String Model, @FormParam("Price") Double Price) throws Exception {
		
		Car car = new Car();
		car.setCarID(CarID);
		car.setMake(Make);
		car.setModel(Model);
		car.setPrice(Price);
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			stub.CreateCar(car);
			return "<p>New Car created</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
			
		} catch (NotBoundException e) {
			
			e.printStackTrace();
			return "<p>Car Failed to create</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
		}
	}
	
	@POST
	@Path("/deletecar")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteCar (@FormParam("CarID")BigInteger CarID) throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			cars = stub.getCar();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Car c : cars){
			
			if(c.getCarID().equals(CarID)) {
				
				try {
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.removeCar(c);
					return "<p>Car Deleted</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "<p>Car Matching ID not found please see car table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
	}
	
	@POST
	@Path("/updatecar")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateCar(@FormParam("CarID") BigInteger CarID,@FormParam("Make") String Make,@FormParam("Model") String Model, @FormParam("Price") Double Price) throws Exception {
		
		Car car = new Car();
		car.setCarID(CarID);
		car.setMake(Make);
		car.setModel(Model);
		car.setPrice(Price);
		
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			cars = stub.getCar();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Car c : cars){
			
			if(c.getCarID().equals(car.getCarID())) {
				
				try {
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.updateCar(car);
					return "<p>Car Updated</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "<p>Car Matching ID not found please see car table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/carTable.jsp\">Return to car Table</a></p>";
	}
	
	@GET
	@Path("/getcustomers")
  	@Produces(MediaType.TEXT_XML)
  	public ArrayList<Customer> getCustomers() throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			customers = stub.getCustomer();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  		return customers;
  	}
	
	@POST
	@Path("/createcustomer")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createCustomer(@FormParam("ID") BigInteger ID,@FormParam("Name") String Name) throws Exception {
		
		Customer customer = new Customer();
		customer.setID(ID);
		customer.setName(Name);
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			stub.CreateCustomer(customer);
			return "<p>New Customer created</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
			
		} catch (NotBoundException e) {
			
			e.printStackTrace();
			return "<p>Customer Failed to create</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
		}
	}
	
	@POST
	@Path("/deletecustomer")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteCustomer (@FormParam("ID")BigInteger ID) throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			customers = stub.getCustomer();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Customer c : customers){
			
			if(c.getID().equals(ID)) {
				
				try {
					stub = (DatabaseService) registry.lookup("databaseservice");
					stub.removeCustomer(c);
					return "<p>Customer Deleted</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
					
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "<p>Customer Matching ID not found please see Customer table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/CustomerTable.jsp\">Return to car Table</a></p>";
	}
	
	@POST
	@Path("/updatecustomer")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateCustomer(@FormParam("ID") BigInteger ID,@FormParam("Name") String Name) throws Exception {
		
		Customer customer = new Customer();
		customer.setID(ID);
		customer.setName(Name);
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			customers = stub.getCustomer();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Customer c : customers){
			
			if(c.getID().equals(customer.getID())) {
				
				try {
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
	
	@GET
	@Path("/getorders")
  	@Produces(MediaType.TEXT_XML)
  	public ArrayList<Order> getOrders() throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			orders = stub.getOrder();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		//Order order1 = new Order();
  		//orders.add(order1);
  		return orders;
  	}
	
	@POST
	@Path("/createorder")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createOrder(@FormParam("CustomerID") BigInteger CustomerID,@FormParam("CarID") BigInteger CarID,@FormParam("Start") Date Start,@FormParam("Return") Date Return) throws Exception {
		
		Order order = new Order();
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			cars = stub.getCar();
			customers = stub.getCustomer();
			orders = stub.getOrder();
			
			for(Car c : cars) {
				if(c.getCarID().equals(CarID)) {
					order.setCar(c);
				}
			}
			
			for(Customer cu : customers) {
				if(cu.getID().equals(CustomerID)) {
					order.setCustomer(cu);
				}
			}
			
			order.setStartDate(Start);
			order.setReturnDate(Return);
			
			stub.CreateOrder(order);
			return "<p>New Order created</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
			
		} catch (NotBoundException e) {
			
			e.printStackTrace();
			return "<p>Order Failed to create</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
		}
	}
	
	@POST
	@Path("/deleteorder")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteOrder (@FormParam("ID")BigInteger ID,@FormParam("CarID")BigInteger CarID) throws Exception {
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
		try {
			stub = (DatabaseService) registry.lookup("databaseservice");
			orders = stub.getOrder();
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

		return "<p>Order Matching ID's not found please see Order table for valid ids</p><hr><p><a href=\"http://localhost:8080/sw/OrderTable.jsp\">Return to Order Table</a></p>";
	}
	
	@POST
	@Path("/updateorder")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateOrder(@FormParam("CarID") BigInteger CarID,@FormParam("CustomerID") BigInteger CustomerID,@FormParam("StartDate") Date StartDate,@FormParam("ReturnDate") Date ReturnDate) throws Exception {
		
		Order order = new Order();
		
		Registry registry = LocateRegistry.getRegistry("localhost",1099);
		DatabaseService stub;
		
			try {
				stub = (DatabaseService) registry.lookup("databaseservice");
				cars = stub.getCar();
				customers = stub.getCustomer();
				orders = stub.getOrder();
			
			for(Car c : cars) {
				if(c.getCarID().equals(CarID)) {
					order.setCar(c);
				}
			}
			
			for(Customer cu : customers) {
				if(cu.getID().equals(CustomerID)) {
					order.setCustomer(cu);
				}
			}
			
			order.setStartDate(StartDate);
			order.setReturnDate(ReturnDate);
			
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
