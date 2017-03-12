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

import business.entities.Mechanic;
import business.service.MechanicServiceImpl;

/**
 * View model for add new mechanic page view
 * 
 * @author TNS
 *
 */
public class AddNewMechanicViewModel {

	@WireVariable
	private MechanicServiceImpl mechanicService;
	private static List<String> GENDERS = new ArrayList<String>();
	static {
		GENDERS.add("Nam");
		GENDERS.add("Nữ");
	}

	@Init
	public void init() {
		// get service bean from Spring
		this.mechanicService = (MechanicServiceImpl) SpringUtil.getBean("mechanic_service");
	}

	@Command
	public void saveNewMechanic(@BindingParam("name") String name, @BindingParam("address") String address,
			@BindingParam("phone_number") String phoneNumber, @BindingParam("gender") String gender) {
		// Messagebox.show(name + "-" + phoneNumber + "-" + address + "-" +
		// gender);
		// make sure we have valid param
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !gender.isEmpty()) {
			Mechanic newMechanic = new Mechanic();
			newMechanic.setName(name);
			newMechanic.setAddress(address);
			newMechanic.setPhone(phoneNumber);
			newMechanic.setGender(gender == GENDERS.get(0) ? true : false);
			// start to save
			if (this.mechanicService.save(newMechanic)) {
				Messagebox.show("Thành công");
				Executions.sendRedirect("./mechanic_list.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
		} else {
			Messagebox.show("Một số thông tin nhập chưa đúng, vui lòng kiểm tra lại", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}

	}

	/**
	 * Binding values to combobox
	 * 
	 * @return
	 */
	public List<String> getGenders() {
		return GENDERS;
	}

}
