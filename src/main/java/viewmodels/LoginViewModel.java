package viewmodels;

import java.util.List;

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

public class LoginViewModel {
	@WireVariable
	private NhanVienServiceImpl nhanVienServiceImpl;

	private String username;
	private String password;

	public static String LOGIN_USERNAME = "Username";
	public static String LOGIN_USERID = "UserId";

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
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");

	}

	@Command
	public void SubmitLogin() {
		List<NhanVien> result = this.nhanVienServiceImpl.findByUsername(this.username);
		//Messagebox.show(this.username);
		if (result.isEmpty()) {
			Messagebox.show("Tài khoản không tồn tại!");
		} else {
			 if(Md5Encryptor.MD5Hash(this.password).equals(result.get(0).getPassword()))
			 {
			Sessions.getCurrent().setAttribute(LOGIN_USERNAME, result.get(0).getUsername());
			Sessions.getCurrent().setAttribute(LOGIN_USERID, result.get(0).getMaNV());
			//Messagebox.show(Sessions.getCurrent().getAttribute(LOGIN_USERNAME).toString());
			//Messagebox.show(Sessions.getCurrent().getAttribute(LOGIN_USERID).toString());
			Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
			 }
			 else
			 {
				 	Messagebox.show("Sai mật khẩu, vui lòng nhập lại!");
			 }
		}
	}

}
