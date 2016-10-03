package techexercise;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import model.Company;
import model.Customer;
import repository.CustomerRepository;
import response.Customers;

public class CompanyAPI {
	
	private static final Logger LOGGER = Logger.getLogger(CompanyAPI.class);

	private Company company;
	private CustomerRepository customerRepository;

	public void initialize() {
		company = new Company();
		company.initialize();
	}

	public Customers getCustomers() {
		Customers customerResp = new Customers();
		customerResp.setCustomers(customerRepository.getCustomers());
		return customerResp;
	}

	public Customers getCustomerById(Long customerId) {
		Customers customerResp = new Customers();
		customerResp.setCustomers(Collections.singletonList(customerRepository.getCustomerById(customerId)));
		return customerResp;
	}

	public Customers addCustomer(Customer customer) {
		Customers customerResp = new Customers();		
		customerResp.setCustomers(Collections.singletonList(customerRepository.addCustomer(customer)));
		return customerResp;
	}

	
	public Customers updateCustomer(JSONObject jsonObject) {
		Customers customerResp = new Customers();
		customerResp.setCustomers(Collections.singletonList(customerRepository.updateCustomer(jsonObject)));
		return customerResp;
	}
	
	public void deleteCustomer(Long id){
		customerRepository.deleteCustomer(id);
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
}
