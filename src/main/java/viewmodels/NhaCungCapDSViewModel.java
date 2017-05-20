package viewmodels;

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

import business.entities.NhaCungCap;
import business.service.NhaCungCapServiceImpl;
import business.service.PhieuDatHangServiceImpl;
import business.service.PhieuNhapHangServiceImpl;

public class NhaCungCapDSViewModel {

	// parameter name of selected id for passing via Executions class
	public static final String SELECTED_NCC_ID = "selected_ncc_id";
	// array of search types string for this page
	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Tên", "Số điện thoại", "Địa chỉ", "Tên nhóm" };

	@WireVariable
	private NhaCungCapServiceImpl nccService;
	private PhieuDatHangServiceImpl pdhService;
	private PhieuNhapHangServiceImpl pnhService;
	private List<NhaCungCap> listOfNCC;
	private NhaCungCap selectedNCC;

	@Init
	public void init() {

		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		
		this.nccService = (NhaCungCapServiceImpl) SpringUtil.getBean("nhacungcap_service");
		this.pdhService = (PhieuDatHangServiceImpl) SpringUtil.getBean("phieudathang_service");
		this.pnhService = (PhieuNhapHangServiceImpl) SpringUtil.getBean("phieunhaphang_service");
		if (this.nccService != null) {
			this.listOfNCC = this.nccService.getAll(NhaCungCap.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}
	}

	@Command
	@NotifyChange("listOfNCC")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		switch (selectedIndex) {
		case 0: // search by id of receive header
			this.listOfNCC = this.nccService.getAll(NhaCungCap.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfNCC.clear(); // clear all items
				this.listOfNCC = this.nccService.find(searchString, null, null, null);
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				this.listOfNCC.clear(); // clear all items
				this.listOfNCC = this.nccService.find(null, searchString, null, null);
			}
			break;
		case 3: //
			if (!searchString.isEmpty()) {
				this.listOfNCC.clear(); // clear all items
				this.listOfNCC = this.nccService.find(null, null, searchString, null);
			}
			break;
		case 4: //
			if (!searchString.isEmpty()) {
				this.listOfNCC.clear(); // clear all items
				this.listOfNCC = this.nccService.find(null, null, null, searchString);
			}
			break;
		default:
			break;
		}
	}

	@Command
	public void editNCC(@BindingParam("ncc_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_NCC_ID, id);
		Executions.sendRedirect("./NhaCungCap_Edit.zul");
	}

	@Command
	@NotifyChange("listOfNCC")
	public void deleteNCC(@BindingParam("ncc_id") long id) {
		if (this.pdhService.findByIdNCC(id).size() == 0 && this.pnhService.findByIdNCC(id).size() == 0 ){
			if (this.nccService.delete(id, NhaCungCap.class)) {
				this.listOfNCC = this.nccService.getAll(NhaCungCap.class);
			} else {
				Messagebox.show("Lỗi khi xoá");
			}
		} else {
			Messagebox.show("Không thể xoá nhà cung cấp đã có giao dịch với cửa hàng", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
		
	}

	@Command
	public void addNewNCCRedirect() {
		Executions.sendRedirect("./NhaCungCap_Add.zul");
	}

	public List<NhaCungCap> getListOfNCC() {
		return listOfNCC;
	}

	public void setListOfNCC(List<NhaCungCap> listOfNCC) {
		this.listOfNCC = listOfNCC;
	}

	public NhaCungCap getSelectedNCC() {
		return selectedNCC;
	}

	public void setSelectedNCC(NhaCungCap selectedNCC) {
		this.selectedNCC = selectedNCC;
	}

	/**
	 * Get search types for this context. This list will be accessed for combox
	 * items
	 * 
	 * @return : {@link List} of {@link String} search types
	 */
	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}
}
