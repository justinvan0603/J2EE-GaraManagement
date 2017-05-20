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
import business.entities.NhomNguoiDung;
import business.service.NhanVienServiceImpl;
import utils.Md5Encryptor;

public class ThongTinViewModel {
	@WireVariable
	private NhanVienServiceImpl nhanVienServiceImpl;
	private NhanVien currentNhanVien;
	
	@Init
	public void init() 
	{
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		//Long nhanVienID = (Long)Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID);
		this.currentNhanVien = this.nhanVienServiceImpl.findById(3, NhanVien.class);
		
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
			@BindingParam("permission") NhomNguoiDung permission, @BindingParam("username") String username, @BindingParam("password") String password)
	{
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !gender.isEmpty()
			&&	!username.isEmpty() && !password.isEmpty()) {
			NhanVien nhanvien = new NhanVien();
			
			nhanvien.setMaNhomNguoiDung(permission.getMaNhomNguoiDung());
			nhanvien.setUsername(username);
			nhanvien.setPassword(Md5Encryptor.MD5Hash(password));
			nhanvien.setDiachi(address);
			nhanvien.setGioiTinh(gender == GENDERS.get(0) ? true : false);
			nhanvien.setHoTen(name);
			nhanvien.setSdt(phoneNumber);
			if (this.nhanVienServiceImpl.update(nhanvien.getMaNV(), nhanvien)) {
				Messagebox.show("Thành công");
				Executions.sendRedirect("./NhanVien_DS.zul");
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
