package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.Customer;
import business.service.CustomerServiceImpl;

public class EditKhachHangViewModel {
	@WireVariable
	private CustomerServiceImpl customerService;
	private static List<String> GENDERS = new ArrayList<String>();
	static {
		GENDERS.add("Nam");
		GENDERS.add("Nữ");
	}
	private Customer currentCustomer;

	public CustomerServiceImpl getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerServiceImpl customerService) {
		this.customerService = customerService;
	}
	public static List<String> getGENDERS() {
		return GENDERS;
	}
	public static void setGENDERS(List<String> gENDERS) {
		GENDERS = gENDERS;
	}
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}
	@Init
	public void init() {
		// get service bean from Spring
		this.customerService = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		String customerId = (String) Sessions.getCurrent().getAttribute(CustomerViewModel.SELECTED_CUSTOMER);
		if(!customerId.isEmpty())
		{
			this.currentCustomer = this.customerService.findById(Long.parseLong(customerId), Customer.class);
		}
	}
	public List<String> getGenders() {
		return GENDERS;
	}
	@Command
	public void updateCustomer(@BindingParam("name") Long id,@BindingParam("name") String name, @BindingParam("address") String address,
			@BindingParam("phone_number") String phoneNumber, @BindingParam("cmnd") String cmnd,@BindingParam("gender") String gender)
	{
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !gender.isEmpty() && !cmnd.isEmpty()) {
			Customer customer = this.customerService.findById(id, Customer.class);
			customer.setCmnd(cmnd);
			customer.setDiachi(address);
			customer.setHoTen(name);
			customer.setSdt(phoneNumber);
			customer.setGioiTinh(gender == GENDERS.get(0) ? true : false);
			if (this.customerService.update(id, customer)) {
				Messagebox.show("Cập nhật thành công");
				Executions.sendRedirect("./customer_list.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
}