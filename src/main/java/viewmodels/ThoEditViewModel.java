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

import business.entities.Tho;
import business.service.ThoServiceImpl;

public class ThoEditViewModel {

	@WireVariable
	private ThoServiceImpl thoService;
	private Tho currentTho;
	private String currentGender;
	private static List<String> GENDERS = new ArrayList<String>();
	static {
		GENDERS.add("Nam");
		GENDERS.add("Nữ");
	}

	@Init
	public void init() {
		
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		
		this.thoService = (ThoServiceImpl) SpringUtil.getBean("tho_service");
		long id = ((Long) Sessions.getCurrent().getAttribute(ThoDSViewModel.SELECTED_THO_ID)).longValue();
		if (id != 0) {
			this.setCurrentTho(this.thoService.findById(id, Tho.class));
			setCurrentGender(currentTho.getGender() ? "Nam" : "Nữ");
		}
	}

	@Command
	public void updateTho(@BindingParam("id") long id, @BindingParam("name") String name, @BindingParam("address") String address,
			@BindingParam("phone_number") String phoneNumber, @BindingParam("gender") String gender) {

		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !gender.isEmpty()) {
			Tho tho = new Tho();
			tho.setName(name);
			tho.setAddress(address);
			tho.setPhone(phoneNumber);
			tho.setGender(gender == GENDERS.get(0) ? true : false);
			// start to save
			if (this.thoService.update(id,tho)) {
				Messagebox.show("Cập nhật thành công");
				Executions.sendRedirect("./Tho_DS.zul");
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
	
	public Tho getCurrentTho() {
		return currentTho;
	}

	public void setCurrentTho(Tho currentTho) {
		this.currentTho = currentTho;
	}

	public String getCurrentGender() {
		return currentGender;
	}

	public void setCurrentGender(String currentGender) {
		this.currentGender = currentGender;
	}
}
