package viewmodels;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuThu;
import business.service.PhieuThuServiceImpl;

public class PhieuThuDSViewModel {

	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Mã phiếu", "Ngày lập phiếu", "Nhân viên lập", "Nội dung" };

	@WireVariable
	private PhieuThuServiceImpl phieuThuService;
	private List<PhieuThu> listOfPhieuThu;

	@Init
	public void init() {
		// get bean from Spring, no need to create new object, Spring framework
		// will manage this bean
		this.phieuThuService = (PhieuThuServiceImpl) SpringUtil.getBean("phieuthu_service");
		if (this.phieuThuService != null) {
			this.listOfPhieuThu = this.phieuThuService.getAll(PhieuThu.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}
	}
	
	@Command
	@NotifyChange("listOfPhieuThu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
//		switch (selectedIndex) {
//		case 0: // search by id of receive header
//			this.listOfTho = this.thoService.getAll(Tho.class);
//			break;
//		case 1: // search by id of receive header
//			if (!searchString.isEmpty()) {
//				this.listOfTho.clear(); // clear all items
//				this.listOfTho = this.thoService.find(searchString, null, null);
//			}
//			break;
//		case 2: //
//			if (!searchString.isEmpty()) {
//				this.listOfTho.clear(); // clear all items
//				this.listOfTho = this.thoService.find(null, searchString, null);
//			}
//			break;
//		case 3: //
//			if (!searchString.isEmpty()) {
//				this.listOfTho.clear(); // clear all items
//				this.listOfTho = this.thoService.find(null, null, searchString);
//			}
//			break;
//		default:
//			break;
//		}
	}
	
	@Command
	@NotifyChange("listOfPhieuThu")
	public void deleteTho(@BindingParam("phieuthu_id") long id) {

		 if (this.phieuThuService.delete(id, PhieuThu.class)) {
 			this.listOfPhieuThu = this.phieuThuService.getAll(PhieuThu.class);
 		} else {
 			Messagebox.show("Lỗi khi xoá");
 		}
	}
	
	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

	public PhieuThuServiceImpl getPhieuThuService() {
		return phieuThuService;
	}

	public void setPhieuThuService(PhieuThuServiceImpl phieuThuService) {
		this.phieuThuService = phieuThuService;
	}

	public List<PhieuThu> getListOfPhieuThu() {
		return listOfPhieuThu;
	}

	public void setListOfPhieuThu(List<PhieuThu> listOfPhieuThu) {
		this.listOfPhieuThu = listOfPhieuThu;
	}
	
	
}
