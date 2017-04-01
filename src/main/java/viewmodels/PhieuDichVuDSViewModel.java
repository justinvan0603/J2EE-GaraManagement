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

import business.entities.PhieuDichVu;
import business.service.PhieuDichVuServiceImpl;

public class PhieuDichVuDSViewModel {
	@WireVariable
	private PhieuDichVuServiceImpl phieuDichVuService;
	
	private List<PhieuDichVu> listPhieuDichVu;
	private PhieuDichVu selectedPDV;
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {
		
		SEARCH_TYPES.add("Mã phiếu");
		
	}
	public static String PDV_ID = "PDV_ID";
	public PhieuDichVu getSelectedPDV() {
		return selectedPDV;
	}
	public void setSelectedPDV(PhieuDichVu selectedPDV) {
		this.selectedPDV = selectedPDV;
	}
	public PhieuDichVuServiceImpl getPhieuDichVuService() {
		return phieuDichVuService;
	}
	public void setPhieuDichVuService(PhieuDichVuServiceImpl phieuDichVuService) {
		this.phieuDichVuService = phieuDichVuService;
	}
	public List<PhieuDichVu> getListPhieuDichVu() {
		return listPhieuDichVu;
	}
	public void setListPhieuDichVu(List<PhieuDichVu> listPhieuDichVu) {
		this.listPhieuDichVu = listPhieuDichVu;
	}
	public static List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}
	@Init
	public void init() {
		this.phieuDichVuService = (PhieuDichVuServiceImpl)SpringUtil.getBean("phieudichvu_service");
	}
	@Command
	@NotifyChange("listPhieuDichVu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listPhieuDichVu = this.phieuDichVuService.findByMaPhieu(searchString);
			
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
		Sessions.getCurrent().setAttribute(PDV_ID, id);
		Executions.sendRedirect("./PhieuDichVu_Edit.zul");
	}
	@Command
	public void ThemPhieuDichVu() {
		Executions.sendRedirect("./PhieuDichVu_Add.zul");
	}
}
