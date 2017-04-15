package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import business.entities.PhieuBanLe;
import business.service.PhieuBanLeServiceImpl;

public class PhieuBanLeDSViewModel {
	@WireVariable
	private PhieuBanLeServiceImpl phieuBanLeServiceImpl;
	private List<PhieuBanLe> listPhieuBanLe;
	public static String PBL_ID = "PBL_ID";
	public PhieuBanLeServiceImpl getPhieuBanLeServiceImpl() {
		return phieuBanLeServiceImpl;
	}

	public List<PhieuBanLe> getListPhieuBanLe() {
		return listPhieuBanLe;
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
	public static List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}
	@Command
	@NotifyChange("listPhieuDichVu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listPhieuBanLe = this.phieuBanLeServiceImpl.findByMaPhieu(searchString);
			
		}
//		if (selectedIndex == 1) {
//			this.listNhanVien = this.nhanVienService.findByPhone(searchString);
//			
//		}
//		if (selectedIndex == 2) {
//			this.listNhanVien = this.nhanVienService.findByCMND(searchString);
//			
//		}
//		if (selectedIndex == 3) {
//			this.listNhanVien = this.nhanVienService.findByAddress(searchString);
//			
//		}
//		if (selectedIndex == 4) {
//			this.listNhanVien = this.nhanVienService.findByUsername(searchString);
//			
//		}
	}
	@Command 
	public void editPhieuDichVu(@BindingParam("id") Long id)
	{
		Sessions.getCurrent().setAttribute(PBL_ID, id);
		Executions.sendRedirect("./PhieuBanLe_Edit.zul");
	}
	@Command
	public void ThemPhieuDichVu() {
		Executions.sendRedirect("./PhieuBanLe_Add.zul");
	}
}
