package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.Customer;
import business.service.CustomerServiceImpl;

public class CustomerViewModel {
	@WireVariable
	private CustomerServiceImpl customerService;
	private List<Customer> customers;
	private Customer selectedCustomer;
	public static String SELECTED_CUSTOMER = "Id";
	private Integer dialogResult;
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {
		SEARCH_TYPES.add("Họ tên");
		SEARCH_TYPES.add("Số điện thoại");
		SEARCH_TYPES.add("CMND");
		SEARCH_TYPES.add("Địa chỉ");
	}
	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		this.customerService = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		this.customers = this.customerService.getAll(Customer.class);
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}
	@Command
	@NotifyChange("customers")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.customers = this.customerService.findByName(searchString);
			
		}
		if (selectedIndex == 1) {
			this.customers = this.customerService.findByPhone(searchString);
			
		}
		if (selectedIndex == 2) {
			this.customers = this.customerService.findByCMND(searchString);
			
		}
		if (selectedIndex == 3) {
			this.customers = this.customerService.findByAddress(searchString);
			
		}
		
	}
	@Command
	public void addNewCustomerRedirect() {
		Executions.sendRedirect("./KhachHang_Add.zul");
	}
	@Command 
	public void editCustomer(@BindingParam("id") Long id)
	{
		Sessions.getCurrent().setAttribute(SELECTED_CUSTOMER, id);
		Executions.sendRedirect("./KhachHang_Edit.zul");
	}
	@Command
	@NotifyChange("customers")
	public void deleteCustomer(@BindingParam("id") final Long id)
	{
		Messagebox.show("Bạn có chắc muốn xóa dữ liệu đã chọn ? ", "Thông báo", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						dialogResult =  ((Integer) event.getData()).intValue();
						switch(dialogResult)
						{
							case Messagebox.OK:
							{
								boolean result =customerService.delete(id, Customer.class);
								if(result == true)
								{
									customers = customerService.getAll(Customer.class);
									Messagebox.show("Xóa thành công");
									Executions.sendRedirect("./customer_list.zul");
								}
								else
								{
									Messagebox.show("Có lỗi xảy ra vui lòng thử lại sau");
								}
								break;
							}
						}
					}
			});
//			if(dialogResult != Messagebox.OK)
//			{
//				return;
//			}
		
	}
}
