package repository;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import model.Address;
import model.Customer;

public class CustomerRepository {

	private static final Logger LOGGER = Logger.getLogger(CustomerRepository.class);
	private NamedParameterJdbcTemplate jdbcTemplate;

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	public List<Customer> getCustomers() {
		List<Customer> customers = jdbcTemplate.query("select * from customers",
				new BeanPropertyRowMapper(Customer.class));
		for (Customer customer : customers) {
			customer.setAddresses(getAddressesByCustomer(customer.getId()));
		}
		return customers;

	}

	public Customer getCustomerById(Long id) {

		Customer customer = (Customer) jdbcTemplate.queryForObject("select * from customers where id = :id",
				Collections.singletonMap("id", id), new BeanPropertyRowMapper(Customer.class));
		if (customer != null) {
			customer.setAddresses(getAddressesByCustomer(customer.getId()));
		}
		return customer;
	}

	public Customer updateCustomer(Customer customer) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", customer.getId());
		params.addValue("firstName", customer.getFirstName());
		params.addValue("lastName", customer.getLastName());
		jdbcTemplate.update("update customers set first_name = :firstName. last_name = :lastName where id = :id",
				params);

		return customer;
	}

	public void deleteCustomer(Long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		jdbcTemplate.update("delete from customers where id = :id", params);

	}

	public Customer addCustomer(Customer customer) {
		LOGGER.info("adding customer");
		KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", customer.getFirstName());
		params.addValue("lastName", customer.getLastName());
		jdbcTemplate.update("insert into customers (first_name, last_name) values (:firstName, :lastName)", params,
				holder);

		Long newCustomerId = holder.getKey().longValue();

		addAddresses(newCustomerId, customer.getAddresses());

	
		customer.setId(newCustomerId);
		return customer;
	}

	private void addAddresses(Long customerId, List<Address> addresses) {

		if (CollectionUtils.isNotEmpty(addresses)) {
			for (Address addr : addresses) {
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue("customerId", customerId);
				params.addValue("addressLine1", addr.getAddressLine1());
				jdbcTemplate.update(
						"insert into addresses (customer_id, address_line1) values (:customerId, :addressLine1)",
						params);
			}
		}
	}

	public List<Address> getAddressesByCustomer(Long customerId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("customerId", customerId);
		List<Address> addresses = jdbcTemplate.query("select * from addresses where customer_id = :customerId", params,
				new BeanPropertyRowMapper(Address.class));
		return addresses;
	}

	public Customer updateCustomer(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
