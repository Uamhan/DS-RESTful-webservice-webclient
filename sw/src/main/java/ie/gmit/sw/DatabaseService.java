package ie.gmit.sw;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface DatabaseService extends Remote{

	//Car
	public ArrayList<Car> getCar() throws Exception;
	public void removeCar(Car car) throws Exception;
	public void updateCar(Car car) throws Exception;
	public void CreateCar(Car car) throws Exception;
	//Customer
	public ArrayList<Customer> getCustomer() throws Exception;
	public void removeCustomer(Customer customer) throws Exception;
	public void updateCustomer(Customer customer) throws Exception;
	public void CreateCustomer(Customer customer) throws Exception;
	//Order
	public ArrayList<Order> getOrder() throws Exception;
	public void removeOrder(Order order) throws Exception;
	public void updateOrder(Order order) throws Exception;
	public void CreateOrder(Order order) throws Exception;
	
}
