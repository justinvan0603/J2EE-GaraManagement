package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.Customer;
import business.service.CustomerServiceImpl;


public class AddKhachHangViewModel {

	@WireVariable
	private CustomerServiceImpl customerService;
	private static List<String> GENDERS = new ArrayList<String>();
	static {
		GENDERS.add("Nam");
		GENDERS.add("Nữ");
	}

	@Init
	public void init() {
		// get service bean from Spring
		this.customerService = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
	}
	public List<String> getGenders() {
		return GENDERS;
	}
	@Command
	public void SaveKhachHang(@BindingParam("name") String name, @BindingParam("address") String address,
			@BindingParam("phone_number") String phoneNumber, @BindingParam("cmnd") String cmnd,@BindingParam("gender") String gender)
	{
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !gender.isEmpty() && !cmnd.isEmpty()) {
			Customer customer = new Customer();
			customer.setSoTienNo(0.0);
			customer.setCmnd(cmnd);
			customer.setDiachi(address);
			customer.setGioiTinh(gender == GENDERS.get(0) ? true : false);
			customer.setHoTen(name);
			customer.setSdt(phoneNumber);
			if (this.customerService.save(customer)) {
				Messagebox.show("Thành công");
				Executions.sendRedirect("./customer_list.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
			
		}
		else
		{
			Messagebox.show("Vui lòng nhập đầy đủ thông tin!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}
}
