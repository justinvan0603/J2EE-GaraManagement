package viewmodels;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;


import business.entities.NhanVien;
import business.service.NhanVienServiceImpl;
import utils.Md5Encryptor;




import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
public class MatKhauEditViewModel {
	private String currentPassword;
	private String newPassword;
	@WireVariable
	private NhanVienServiceImpl nhanVienServiceImpl;
	@Init
	public void Init()
	{
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	@Command
	public void SavePassword() {
		Long id = (Long)Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID);
		NhanVien result = this.nhanVienServiceImpl.findById(id, NhanVien.class);
		//Messagebox.show(this.username);

			 if(Md5Encryptor.MD5Hash(this.currentPassword).equals(result.getPassword()))
			 {
				result.setPassword(Md5Encryptor.MD5Hash(this.newPassword));
				if(this.nhanVienServiceImpl.update(result.getMaNV(), result))
				{
					Messagebox.show("Mật khẩu đã được cập nhật!");
					Executions.sendRedirect("./Login.zul");
				}
				else
				{
					Messagebox.show("Có lỗi xảy ra, vui lòng thử lại sau!");
				}
				 
			 }
			 else
			 {
				 	Messagebox.show("Sai mật khẩu, vui lòng nhập lại!");
			 }
		
	}
	
}
