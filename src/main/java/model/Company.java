package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Company {
	private static final Logger LOGGER = Logger.getLogger(Company.class);
	private List<Customer> customers = new ArrayList<Customer>();

	@SuppressWarnings("unchecked")
	public void initialize() {
		// set up customers

		Customer cust1 = new Customer(1L, "edmond", "kwong");
		Address addr1 = new Address("1 test st");
		Address addr2 = new Address("2 test st");
		cust1.getAddresses().add(addr1);
		cust1.getAddresses().add(addr2);

		Customer cust2 = new Customer(2L, "edmond", "kwong");
		Address addr3 = new Address("1 test st");
		Address addr4 = new Address("2 test st");
		cust2.getAddresses().add(addr3);
		cust2.getAddresses().add(addr4);

		customers.add(cust1);
		customers.add(cust2);

	}

	public void addCustomer(JSONObject jsonObject) {

		Customer customer = new Customer();
		customer.setFirstName((String) jsonObject.get("firstName"));
		customer.setLastName((String) jsonObject.get("lastName"));
		customer.setAddresses(getAddresses(jsonObject));

		this.customers.add(customer);

	}

	public Customer getCustomerById(Long id) {
		for (Customer customer : customers) {
			if (customer.getId().equals(id)) {
				return customer;
			}
		}
		return null;
	}

	private List<Address> getAddresses(JSONObject jsonObject) {
		JSONArray addresses = (JSONArray) jsonObject.get("addresses");

		if (addresses != null) {

			List<Address> resultAddresses = new ArrayList<Address>();
			for (Iterator i = addresses.iterator(); i.hasNext();) {
				JSONObject addressObj = (JSONObject) i.next();
				Address addr = new Address();
				addr.setAddressLine1((String) addressObj.get("addressLine"));

				resultAddresses.add(addr);

			}
			return resultAddresses;
		}
		return Collections.EMPTY_LIST;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
