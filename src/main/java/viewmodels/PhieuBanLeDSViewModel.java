package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuBanLe;
import business.service.PhieuBanLeServiceImpl;
import business.service.PhieuThuServiceImpl;

public class PhieuBanLeDSViewModel {
	@WireVariable
	private PhieuBanLeServiceImpl phieuBanLeServiceImpl;
	@WireVariable
	private PhieuThuServiceImpl phieuThuServiceImpl;
	private PhieuBanLe selectedPhieuBanLe;
	private List<PhieuBanLe> listPhieuBanLe;
	public static String PBL_ID = "PBL_ID";
	public PhieuBanLeServiceImpl getPhieuBanLeServiceImpl() {
		return phieuBanLeServiceImpl;
	}

	public List<PhieuBanLe> getListPhieuBanLe() {
		return listPhieuBanLe;
	}

	public PhieuBanLe getSelectedPhieuBanLe() {
		return selectedPhieuBanLe;
	}

	public void setSelectedPhieuBanLe(PhieuBanLe selectedPhieuBanLe) {
		this.selectedPhieuBanLe = selectedPhieuBanLe;
	}

	public static String getPBL_ID() {
		return PBL_ID;
	}

	public static void setPBL_ID(String pBL_ID) {
		PBL_ID = pBL_ID;
	}

	public void setListPhieuBanLe(List<PhieuBanLe> listPhieuBanLe) {
		this.listPhieuBanLe = listPhieuBanLe;
	}

	public void setPhieuBanLeServiceImpl(PhieuBanLeServiceImpl phieuBanLeServiceImpl) {
		this.phieuBanLeServiceImpl = phieuBanLeServiceImpl;
	}
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {
		
		SEARCH_TYPES.add("Mã phiếu");
		
	}
	public  List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}
	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		this.phieuThuServiceImpl = (PhieuThuServiceImpl) SpringUtil.getBean("phieuthu_service");
		this.phieuBanLeServiceImpl = (PhieuBanLeServiceImpl) SpringUtil.getBean("phieubanle_service");
		this.listPhieuBanLe =  this.phieuBanLeServiceImpl.getAll(PhieuBanLe.class);
	}
	@Command
	@NotifyChange("listPhieuDichVu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listPhieuBanLe = this.phieuBanLeServiceImpl.findByMaPhieu(searchString);
			
		}

	}
	@Command 
	public void editPhieuBanLe(@BindingParam("id") Long id)
	{
		if(this.phieuThuServiceImpl.findByIdPhieuCanThu("pbl", id).isEmpty())
		{
			Sessions.getCurrent().setAttribute(PBL_ID, id);
			Executions.sendRedirect("./PhieuBanLe_Edit.zul");
		}
		else
		{
			Messagebox.show("Không thể thay đổi khi đã lập phiếu thu!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
		
	}
	@Command 
	@NotifyChange("listPhieuBanLe")
	public void deletePhieuBanLe(@BindingParam("id") Long id)
	{
		if(this.phieuThuServiceImpl.findByIdPhieuCanThu("pbl", id).isEmpty())
		{
			if(this.phieuBanLeServiceImpl.delete(id, PhieuBanLe.class))
			{
				this.listPhieuBanLe.clear();
				this.setListPhieuBanLe(this.phieuBanLeServiceImpl.getAll(PhieuBanLe.class));
			}
			else
			{
				Messagebox.show("Đã có lỗi xảy ra!", "Lỗi", Messagebox.OK,
						Messagebox.ERROR);
			}
		}
		else
		{
			Messagebox.show("Không thể xóa khi đã lập phiếu thu!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
		
	}
	@Command
	public void ThemPhieuBanLe() {
		Executions.sendRedirect("./PhieuBanLe_Add.zul");
	}
	
	@Command
	public void createPhieuThu(@BindingParam("phieuthu_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(PhieuThuDSViewModel.PDH_ID, id);
		Sessions.getCurrent().setAttribute(PhieuThuDSViewModel.PDH_TYPE, "pbl");
		Executions.sendRedirect("./PhieuThu_Add.zul");
	}
}
