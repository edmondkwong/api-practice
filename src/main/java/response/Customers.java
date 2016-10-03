package response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;

import org.mule.api.annotations.Transformer;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Customer;

public class Customers {
	private List<Customer> customers;

	@XmlElement(name = "customers")
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Transformer(resultMimeType = "application/json")
	public String toJson(Customers customers) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(customers);
	}

	@Transformer(resultMimeType = "text/xml")
	public String toXml(Customers customers) throws IOException, JAXBException {
		JAXBContext context = JAXBContext.newInstance(getClass());

		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		m.marshal(customers, boas);

		return new String(boas.toByteArray());
	}

}
