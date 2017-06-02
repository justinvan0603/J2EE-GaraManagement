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

import business.entities.NhanVien;

import business.service.NhanVienServiceImpl;
import utils.Md5Encryptor;

public class ThongTinViewModel {
	@WireVariable
	private NhanVienServiceImpl nhanVienServiceImpl;
	private NhanVien currentNhanVien;
	private String currentGender;
	public String getCurrentGender() {
		return currentGender;
	}

	public void setCurrentGender(String currentGender) {
		this.currentGender = currentGender;
	}

	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		Long nhanVienID = (Long) Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID);
		this.currentNhanVien = this.nhanVienServiceImpl.findById(nhanVienID, NhanVien.class);
		this.setCurrentGender(this.currentNhanVien.getGioiTinh() ? "Nam" : "Nữ");
	}

	public NhanVienServiceImpl getNhanVienServiceImpl() {
		return nhanVienServiceImpl;
	}

	public void setNhanVienServiceImpl(NhanVienServiceImpl nhanVienServiceImpl) {
		this.nhanVienServiceImpl = nhanVienServiceImpl;
	}

	public NhanVien getCurrentNhanVien() {
		return currentNhanVien;
	}

	public void setCurrentNhanVien(NhanVien currentNhanVien) {
		this.currentNhanVien = currentNhanVien;
	}

	private static List<String> GENDERS = new ArrayList<String>();
	static {
		GENDERS.add("Nam");
		GENDERS.add("Nữ");
	}

	public List<String> getGenders() {
		return GENDERS;
	}

	@Command
	public void EditNhanVien(@BindingParam("name") String name, @BindingParam("address") String address,
			@BindingParam("phone_number") String phoneNumber, @BindingParam("gender") String gender,
			
			@BindingParam("password") String password) {
		
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !gender.isEmpty() 
				&& !password.isEmpty()) {
			//NhanVien nhanvien = new NhanVien();


			//nhanvien.setUsername(username);
			this.currentNhanVien.setPassword(Md5Encryptor.MD5Hash(password));
			this.currentNhanVien.setDiachi(address);
			this.currentNhanVien.setGioiTinh(gender == GENDERS.get(0) ? true : false);
			this.currentNhanVien.setHoTen(name);
			this.currentNhanVien.setSdt(phoneNumber);
			if (this.nhanVienServiceImpl.update(this.currentNhanVien.getMaNV(), this.currentNhanVien)) {
				Messagebox.show("Thành công");
				//Executions.sendRedirect("./NhanVien_DS.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}

		} else {
			Messagebox.show("Vui lòng nhập đầy đủ thông tin!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}
}
