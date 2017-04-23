package viewmodels;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuNhapHang;
import business.service.PhieuNhapHangServiceImpl;
import utils.DateUtil;

public class PhieuNhapHangDSViewModel {

	public static final String SELECTED_PNH_ID = "selected_phieunhaphang_id";
	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Mã phiếu", "Nhân viên lập", "Ngày lập phiếu", "Mã phiếu đặt", "Tên nhà cung cấp" };

	@WireVariable
	private PhieuNhapHangServiceImpl phieuNhapHangService;
	private List<PhieuNhapHang> listOfPhieuNhapHang;

	@Init
	public void init() {
		// get bean from Spring, no need to create new object, Spring framework
		// will manage this bean
		this.phieuNhapHangService = (PhieuNhapHangServiceImpl) SpringUtil.getBean("phieunhaphang_service");
		if (this.phieuNhapHangService != null) {
			this.listOfPhieuNhapHang = this.phieuNhapHangService.getAll(PhieuNhapHang.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}
	}

	@Command
	@NotifyChange("listOfPhieuNhapHang")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		switch (selectedIndex) {
		case 0: // search by id of receive header
			this.listOfPhieuNhapHang = this.phieuNhapHangService.getAll(PhieuNhapHang.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfPhieuNhapHang.clear(); // clear all items
				this.listOfPhieuNhapHang = this.phieuNhapHangService.find(searchString, null, null, null, null);
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuNhapHang.clear(); // clear all items
				this.listOfPhieuNhapHang = this.phieuNhapHangService.find(null, searchString, null, null, null);
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
				this.listOfPhieuNhapHang.clear(); // clear all items
				this.listOfPhieuNhapHang = this.phieuNhapHangService.find(null, null, searchDate, null, null);
			}
			break;
		case 4: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuNhapHang.clear(); // clear all items
				this.listOfPhieuNhapHang = this.phieuNhapHangService.find(null, null, null, searchString, null);
			}
			break;
		case 5: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuNhapHang.clear(); // clear all items
				this.listOfPhieuNhapHang = this.phieuNhapHangService.find(null, null, null, null, searchString);
			}
			break;
		default:
			break;
		}
	}

	@Command
	public void addNewPhieuNhapHangRedirect() {
		Executions.sendRedirect("./PhieuNhapHang_Add.zul");
	}

	@Command
	public void editPhieuNhapHang(@BindingParam("phieunh_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_PNH_ID, id);
		Executions.sendRedirect("./PhieuNhapHang_Edit.zul");
	}

	/**
	 * Create new 'PhieuChi' for selected 'PhieuNhapHang'
	 * 
	 * @param id
	 *            : id of selected 'PhieuNhapHang'
	 */
	@Command
	public void lapPhieuChi(@BindingParam("id_phieunhaphang") Integer id) {
		Sessions.getCurrent().setAttribute(SELECTED_PNH_ID, id);
		Executions.sendRedirect("./PhieuChi_Add.zul");
	}

	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

	public PhieuNhapHangServiceImpl getPhieuNhapHangService() {
		return phieuNhapHangService;
	}

	public void setPhieuNhapHangService(PhieuNhapHangServiceImpl phieuNhapHangService) {
		this.phieuNhapHangService = phieuNhapHangService;
	}

	public List<PhieuNhapHang> getListOfPhieuNhapHang() {
		return listOfPhieuNhapHang;
	}

	public void setListOfPhieuNhapHang(List<PhieuNhapHang> listOfPhieuNhapHang) {
		this.listOfPhieuNhapHang = listOfPhieuNhapHang;
	}

}
