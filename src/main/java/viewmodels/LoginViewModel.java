package viewmodels;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.BangThamSo;
import business.entities.NhanVien;
import business.entities.NhomNguoiDung;
import business.service.BangThamSoServiceImpl;
import business.service.NhanVienServiceImpl;
import business.service.NhomNguoiDungServiceImpl;
import utils.Md5Encryptor;
import utils.SystemParam;

public class LoginViewModel {
	@WireVariable
	private NhanVienServiceImpl nhanVienServiceImpl;
	@WireVariable
	private BangThamSoServiceImpl bangThamSoServiceImpl;
	@WireVariable
	private NhomNguoiDungServiceImpl nhomNguoiDungServiceImpl;
	public BangThamSoServiceImpl getBangThamSoServiceImpl() {
		return bangThamSoServiceImpl;
	}

	public void setBangThamSoServiceImpl(BangThamSoServiceImpl bangThamSoServiceImpl) {
		this.bangThamSoServiceImpl = bangThamSoServiceImpl;
	}

	private String username;
	private String password;

	public static String LOGIN_USERNAME = "Username";
	public static String LOGIN_USERID = "UserId";
	public static String LOGIN_PERMISSION = "Permission";
	public NhanVienServiceImpl getNhanVienServiceImpl() {
		return nhanVienServiceImpl;
	}

	public void setNhanVienServiceImpl(NhanVienServiceImpl nhanVienServiceImpl) {
		this.nhanVienServiceImpl = nhanVienServiceImpl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Init
	public void init() {
		Sessions.getCurrent().setAttribute(LOGIN_USERNAME, null);
		Sessions.getCurrent().setAttribute(LOGIN_USERID, null);
		Sessions.getCurrent().setAttribute(LOGIN_PERMISSION,null);
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.bangThamSoServiceImpl = (BangThamSoServiceImpl) SpringUtil.getBean("bangthamso_service");
		this.nhomNguoiDungServiceImpl = (NhomNguoiDungServiceImpl) SpringUtil.getBean("nhomnguoidung_service");
	}

	@Command
	public void SubmitLogin() {
		List<NhanVien> result = this.nhanVienServiceImpl.findByUsername(this.username);
		// Messagebox.show(this.username);
		if (result.isEmpty()) {
			Messagebox.show("Tài khoản không tồn tại!");
		} else {
			if (Md5Encryptor.MD5Hash(this.password).equals(result.get(0).getPassword())) {
				Sessions.getCurrent().setAttribute(LOGIN_USERNAME, result.get(0).getUsername());
				Sessions.getCurrent().setAttribute(LOGIN_USERID, result.get(0).getMaNV());
				NhomNguoiDung userGroup = this.nhomNguoiDungServiceImpl.findById(result.get(0).getMaNhomNguoiDung(), NhomNguoiDung.class);
				Sessions.getCurrent().setAttribute(LOGIN_PERMISSION,userGroup.getTenNhom());
				SystemParam.setListBTS(this.bangThamSoServiceImpl.getAll(BangThamSo.class));
				Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
			} else {
				Messagebox.show("Sai mật khẩu, vui lòng nhập lại!");
			}
		}
	}

}
