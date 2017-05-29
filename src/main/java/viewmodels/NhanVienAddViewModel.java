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
import business.service.NhomNguoiDungServiceImpl;
import utils.Md5Encryptor;
import utils.PermissionChecker;

public class NhanVienAddViewModel {
	@WireVariable
	private NhanVienServiceImpl nhanVienService;
	private NhomNguoiDungServiceImpl nhomNguoiDungService;
	private List<NhomNguoiDung> listNhomNguoiDung;
	public NhomNguoiDungServiceImpl getNhomNguoiDungService() {
		return nhomNguoiDungService;
	}
	public void setNhomNguoiDungService(NhomNguoiDungServiceImpl nhomNguoiDungService) {
		this.nhomNguoiDungService = nhomNguoiDungService;
	}
	public List<NhomNguoiDung> getListNhomNguoiDung() {
		return listNhomNguoiDung;
	}
	public void setListNhomNguoiDung(List<NhomNguoiDung> listNhomNguoiDung) {
		this.listNhomNguoiDung = listNhomNguoiDung;
	}

	private static List<String> GENDERS = new ArrayList<String>();
	static {
		GENDERS.add("Nam");
		GENDERS.add("Nữ");
	}
	public List<String> getGenders() {
		return GENDERS;
	}
	public NhanVienServiceImpl getNhanVienService() {
		return nhanVienService;
	}

	public void setNhanVienService(NhanVienServiceImpl nhanVienService) {
		this.nhanVienService = nhanVienService;
	}
	
	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		if(!PermissionChecker.isAdministrator((String)Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_PERMISSION)))
		{
			Messagebox.show("Bạn không có quyền truy cập vào chức năng này!");
			Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
		}
		this.nhanVienService = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.nhomNguoiDungService = (NhomNguoiDungServiceImpl) SpringUtil.getBean("nhomnguoidung_service");
		this.listNhomNguoiDung = this.nhomNguoiDungService.getAll(NhomNguoiDung.class);
	}
	@Command
	public void SaveNhanVien(@BindingParam("name") String name, @BindingParam("address") String address,
			@BindingParam("phone_number") String phoneNumber, @BindingParam("gender") String gender,
			@BindingParam("permission") NhomNguoiDung permission, @BindingParam("username") String username, @BindingParam("password") String password)
	{
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !gender.isEmpty()
			&&	!username.isEmpty() && !password.isEmpty()) {
			NhanVien nhanvien = new NhanVien();
			//Messagebox.show(username);
			nhanvien.setMaNhomNguoiDung(permission.getMaNhomNguoiDung());
			nhanvien.setUsername(username);
			nhanvien.setPassword(Md5Encryptor.MD5Hash(password));
			nhanvien.setDiachi(address);
			nhanvien.setGioiTinh(gender == GENDERS.get(0) ? true : false);
			nhanvien.setHoTen(name);
			nhanvien.setSdt(phoneNumber);
			if (this.nhanVienService.save(nhanvien)) {
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
