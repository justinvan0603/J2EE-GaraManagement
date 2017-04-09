package viewmodels;

import java.util.Date;
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

import business.entities.CT_PhieuDatHang;
import business.entities.PhieuDatHang;
import business.service.ChiTietPhieuDatHangServiceImpl;
import business.service.PhieuDatHangServiceImpl;
import utils.DateUtil;

public class PhieuDatHangDSViewModel {

	public static final String SELECTED_PDH_ID = "selected_phieudathang_id";
	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Mã phiếu", "Nhân viên lập", "Ngày lập phiếu", "Ngày hẹn giao", "Tên nhà cung cấp" };

	@WireVariable
	private PhieuDatHangServiceImpl phieuDatHangService;
	private ChiTietPhieuDatHangServiceImpl chiTietPhieuDatHangService;
	private List<PhieuDatHang> listOfPhieuDatHang;

	@Init
	public void init() {
		// get bean from Spring, no need to create new object, Spring framework
		// will manage this bean
		this.phieuDatHangService = (PhieuDatHangServiceImpl) SpringUtil.getBean("phieudathang_service");
		this.chiTietPhieuDatHangService = (ChiTietPhieuDatHangServiceImpl) SpringUtil
				.getBean("ct_phieudathang_service");
		if (this.phieuDatHangService != null) {
			this.listOfPhieuDatHang = this.phieuDatHangService.getAll(PhieuDatHang.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}
	}

	@Command
	@NotifyChange("listOfPhieuDatHang")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		switch (selectedIndex) {
		case 0: // search by id of receive header
			this.listOfPhieuDatHang = this.phieuDatHangService.getAll(PhieuDatHang.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(searchString, null, null, null, null);
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(null, searchString, null, null, null);
			}
			break;
		case 3: //
			if (!searchString.isEmpty()) {
				Date searchDate = null;
				try {
					String[] dateSplit = searchString.split("/");
					searchDate = DateUtil.parseFromStringArray(dateSplit);
					System.out.println(searchString);
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(null, null, searchDate, null, null);
			}
			break;
		case 4: //
			if (!searchString.isEmpty()) {
				Date searchDate = null;
				try {
					String[] dateSplit = searchString.split("/");
					searchDate = DateUtil.parseFromStringArray(dateSplit);
					System.out.println(searchString);
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(null, null, null, searchDate, null);
			}
			break;
		case 5: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(null, null, null, null, searchString);
			}
			break;
		default:
			break;
		}
	}

	@Command
	@NotifyChange("listOfPhieuDatHang")
	public void deletePhieuDatHang(@BindingParam("phieudh_id") long id) {

		List<CT_PhieuDatHang> listOfCT_PhieuDatHang = this.chiTietPhieuDatHangService
				.getAllByPhieuDatHangId(id);
		if (!listOfCT_PhieuDatHang.isEmpty()) {
			for (CT_PhieuDatHang ct : listOfCT_PhieuDatHang) {
				this.chiTietPhieuDatHangService.delete(ct.getId(),
				CT_PhieuDatHang.class);
			}
		}
		this.phieuDatHangService.delete(id, PhieuDatHang.class);
		this.listOfPhieuDatHang = this.phieuDatHangService.getAll(PhieuDatHang.class);

	}

	@Command
	public void addNewPhieuDatHangRedirect() {
		Executions.sendRedirect("./PhieuDatHang_Add.zul");
	}

	@Command
	public void editPhieuDatHang(@BindingParam("phieudh_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_PDH_ID, id);
		Executions.sendRedirect("./PhieuDatHang_Edit.zul");
	}

	public List<PhieuDatHang> getListOfPhieuDatHang() {
		return listOfPhieuDatHang;
	}

	public void setListOfPhieuDatHang(List<PhieuDatHang> listOfPhieuDatHang) {
		this.listOfPhieuDatHang = listOfPhieuDatHang;
	}

	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

	public PhieuDatHangServiceImpl getPhieuDatHangService() {
		return phieuDatHangService;
	}

	public void setPhieuDatHangService(PhieuDatHangServiceImpl phieuDatHangService) {
		this.phieuDatHangService = phieuDatHangService;
	}

}
