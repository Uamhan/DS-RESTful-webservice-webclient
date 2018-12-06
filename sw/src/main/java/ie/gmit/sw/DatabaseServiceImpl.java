package ie.gmit.sw;

import java.util.*;

import java.math.BigInteger;
import java.sql.*;
import java.sql.Date;

public class DatabaseServiceImpl implements DatabaseService{

	// JDBC driver name and database URL 
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
    String DB_URL = "jdbc:mysql://localhost:3306/carhire";  
    
    // Database credentials 
    String USER = "root"; 
    String PASS = "password";  
    
    Connection conn = null; 
    Statement stmt = null;  
 
	@Override
	public ArrayList<Car> getCar() throws Exception {
		
		ArrayList<Car> list = new ArrayList<Car>();  
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "SELECT * FROM car"; 
	    //get results set
	    ResultSet rs = stmt.executeQuery(sql);  
	    //iterate over results set
	    while(rs.next()) { 
	        // Retrieve by column name 
	    	
	        int carID  = rs.getInt("carid"); 
	        
	        String make = rs.getString("make"); 
	        String model = rs.getString("model"); 
	        
	        double price = rs.getDouble("price"); 
	         
	        
	        // Setting the values 
	        
	        Car car = new Car();
	        car.setCarID(new BigInteger(Integer.valueOf(carID).toString()));
	        car.setMake(make);
	        car.setModel(model);
	        car.setPrice(price);
	        list.add(car);
	     } 
	     rs.close(); 
	     System.out.println("Cars returned"); 
	     return list;  
	}

	@Override
	public void removeCar(Car car) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "DELETE FROM car WHERE carid = "+car.getCarID()+""; 
	    //get results set
	    stmt.executeUpdate(sql);  
		
	}

	@Override
	public void updateCar(Car car) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "UPDATE car SET make ='"+car.getMake()+"', model = '"+car.getModel()+"', price = "+car.getPrice()+" WHERE carid = "+car.getCarID()+"";
	    //get results set
	    stmt.executeUpdate(sql);  
		
		
		
	}

	@Override
	public void CreateCar(Car car) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "INSERT INTO `carhire`.`car` (`carid`, `make`, `model`, `price`) VALUES ('"+car.getCarID()+"', '"+car.getMake()+"', '"+car.getModel()+"', '"+car.getPrice()+"')"; 
	    //get results set
	    stmt.executeUpdate(sql);  
		
	}

	@Override
	public ArrayList<Customer> getCustomer() throws Exception {
		ArrayList<Customer> list = new ArrayList<Customer>();  
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "SELECT * FROM customer"; 
	    //get results set
	    ResultSet rs = stmt.executeQuery(sql);  
	    //iterate over results set
	    while(rs.next()) { 
	        // Retrieve by column name 
	    	
	        int customerID  = rs.getInt("customerid"); 
	        
	        String Name = rs.getString("name"); 
	        // Setting the values  
	        Customer customer = new Customer();
	        customer.setID(new BigInteger(Integer.valueOf(customerID).toString()));
	        customer.setName(Name);
	        list.add(customer);
	     } 
	     rs.close(); 
	     System.out.println("Customers returned"); 
	     return list;  
	}

	@Override
	public void removeCustomer(Customer customer) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "DELETE FROM customer WHERE customerid = "+customer.getID()+""; 
	    //get results set
	    stmt.executeUpdate(sql);  
		
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "UPDATE customer SET name ='"+customer.getName()+"' WHERE customerid = "+customer.getID()+"";
	    //get results set
	    stmt.executeUpdate(sql);  
		
	}

	@Override
	public void CreateCustomer(Customer customer) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "INSERT INTO `carhire`.`customer` (`customerid`, `name`) VALUES ('"+customer.getID()+"', '"+customer.getName()+"')"; 
	    //get results set
	    stmt.executeUpdate(sql);  
		
	}

	@Override
	public ArrayList<Order> getOrder() throws Exception {
		
		ArrayList<Order> list = new ArrayList<Order>();  
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "SELECT carhire.order.startdate,carhire.order.returndata,carhire.car.carid,carhire.car.make,carhire.car.model,carhire.car.price,carhire.customer.customerid,carhire.customer.name FROM ((carhire.order INNER JOIN customer ON carhire.order.customerid = carhire.customer.customerid) INNER JOIN car ON carhire.order.carid = carhire.car.carid)";
	    //get results set
	    //String sql = "SELECT * FROM car"; 
	    ResultSet rs = stmt.executeQuery(sql);  
	    //iterate over results set
	    while(rs.next()) { 
	        // Retrieve by column name 
	    	
	    	Date start = rs.getDate("startdate");
	        Date end = rs.getDate("returndata");
	    	
	    	int carid = rs.getInt("carid");
	    	String make = rs.getString("make");
	    	String model = rs.getString("model");
	    	double price = rs.getDouble("price");
	    	
	    	int customerid = rs.getInt("customerid");
	    	String name = rs.getString("name");
	    	
	    	Car car = new Car();
	        car.setCarID(new BigInteger(Integer.valueOf(carid).toString()));
	        car.setMake(make);
	        car.setModel(model);
	        car.setPrice(price);
	        
	        Customer customer = new Customer();
	        customer.setID(new BigInteger(Integer.valueOf(customerid).toString()));
	        customer.setName(name);
	        
	        Order order = new Order();
	        order.setCar(car);
	        order.setCustomer(customer);
	        order.setStartDate(start);
	        order.setReturnDate(end);
	    	
	    	

	        //Order order = new Order();
	        //order.setCar(car);
	        //order.setCustomer(customer);
	        //order.setStartDate(start);
	        //order.setReturnDate(end);
	        
	        list.add(order);
	        
	     } 
	     rs.close(); 
	     System.out.println("Orders returned"); 
	     return list;
	}

	@Override
	public void removeOrder(Order order) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "DELETE FROM carhire.order WHERE customerid = "+order.getCustomer().getID()+" AND carid = "+order.getCar().getCarID()+""; 
	    //get results set
	    stmt.executeUpdate(sql); 
		
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	    String sql = "UPDATE carhire.order SET startdate ='"+order.getStartDate()+"', returndata = '"+order.getReturnDate()+"' WHERE customerid = '"+order.getCustomer().getID()+"' AND carid = '"+order.getCar().getCarID()+"'";
	    //get results set
	    stmt.executeUpdate(sql);  
		
	}

	@Override
	public void CreateOrder(Order order) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		//Open a connection
	    System.out.println("Connecting to a selected database..."); 
	    conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    System.out.println("Connected database successfully..."); 
	    //create statement
	    stmt = conn.createStatement();  
	     
	    
	    String sql = "INSERT INTO `carhire`.`order` (`carid`, `customerid`, `startdate`, `returndata`) VALUES ('"+order.getCar().getCarID()+"', '"+order.getCustomer().getID()+"', '"+order.getStartDate()+"', '"+order.getReturnDate()+"')";
	    
	    //get results set
	    stmt.executeUpdate(sql);  
		
	}

}
