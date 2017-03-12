package viewmodels;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;

import business.entities.Customer;
import business.service.CustomerServiceImpl;

public class CustomerViewModel {
	@WireVariable
	private CustomerServiceImpl customerService;
	private List<Customer> customers;

	@Init
	public void init() {
		this.customerService = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		this.customers = this.customerService.getAll(Customer.class);
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
