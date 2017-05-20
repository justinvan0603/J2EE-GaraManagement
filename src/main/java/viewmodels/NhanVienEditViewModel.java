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

public class NhanVienEditViewModel {
	@WireVariable
	private NhanVienServiceImpl nhanVienService;
	private NhomNguoiDungServiceImpl nhomNguoiDungService;
	private List<NhomNguoiDung> listNhomNguoiDung;
	
	private NhanVien currentNhanVien;
	private NhomNguoiDung selectedNhomNguoiDung;
	
	public NhomNguoiDung getSelectedNhomNguoiDung() {
		return selectedNhomNguoiDung;
	}
	public void setSelectedNhomNguoiDung(NhomNguoiDung selectedNhomNguoiDung) {
		this.selectedNhomNguoiDung = selectedNhomNguoiDung;
	}
	public NhanVien getCurrentNhanVien() {
		return currentNhanVien;
	}
	public void setCurrentNhanVien(NhanVien currentNhanVien) {
		this.currentNhanVien = currentNhanVien;
	}
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
		this.nhanVienService = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.nhomNguoiDungService = (NhomNguoiDungServiceImpl) SpringUtil.getBean("nhomnguoidung_service");
		this.listNhomNguoiDung = this.nhomNguoiDungService.getAll(NhomNguoiDung.class);
		Long nhanvienId = (Long) Sessions.getCurrent().getAttribute(NhanVienDSViewModel.SELECTED_STAFF);
		//if(!nhanvienId.isEmpty())
		//{
			this.currentNhanVien = this.nhanVienService.findById(nhanvienId, NhanVien.class);
		//}
		this.selectedNhomNguoiDung = this.nhomNguoiDungService.findById(currentNhanVien.getMaNhomNguoiDung(), NhomNguoiDung.class);
		
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
			if (this.nhanVienService.update(nhanvien.getMaNV(), nhanvien)) {
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
