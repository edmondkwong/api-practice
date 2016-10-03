package model;

public class Address {
	private Long id;
	private String addressLine1;

	public Address() {
	}

	public Address(String addressLine1) {

		this.addressLine1 = addressLine1;

	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
