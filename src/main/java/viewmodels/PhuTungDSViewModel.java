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

import business.entities.PhuTung;
import business.service.PhuTungServiceImpl;

public class PhuTungDSViewModel {

	public static final String SELECTED_PT_ID = "selected_phutung_id";
	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Mã phụ tùng", "Tên phụ tùng", "Hiệu xe" };

	@WireVariable
	private PhuTungServiceImpl phuTungService;
	private List<PhuTung> listOfPhuTung;
	private PhuTung selectedPhuTung;
	
	@Init
	public void init() {
		// get bean from Spring, no need to create new object, Spring framework
		// will manage this bean
		this.phuTungService = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		if (this.phuTungService != null) {
			this.listOfPhuTung = this.phuTungService.getAll(PhuTung.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}
	}
	
	@Command
	@NotifyChange("listOfPhuTung")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		switch (selectedIndex) {
		case 0: // search by id of receive header
			this.listOfPhuTung = this.phuTungService.getAll(PhuTung.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfPhuTung.clear(); // clear all items
				this.listOfPhuTung = this.phuTungService.find(searchString, null, null);
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				this.listOfPhuTung.clear(); // clear all items
				this.listOfPhuTung = this.phuTungService.find(null, searchString, null);
			}
			break;
		case 3: //
			if (!searchString.isEmpty()) {
				this.listOfPhuTung.clear(); // clear all items
				this.listOfPhuTung = this.phuTungService.find(null, null, searchString);
			}
			break;
		default:
			break;
		}
	}
	
	@Command
	public void editPhuTung(@BindingParam("phutung_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_PT_ID, id);
		Executions.sendRedirect("./PhuTung_Edit.zul");
	}

	@Command
	@NotifyChange("listOfPhuTung")
	public void deletePhuTung(@BindingParam("phutung_id") long id) {
		try {
			if (this.phuTungService.delete(id, PhuTung.class)) {
	 			this.listOfPhuTung = this.phuTungService.getAll(PhuTung.class);
			} else {
				Messagebox.show("Không thể xoá phụ tùng đã từng sử dụng trên các phiếu!", "Thông báo", Messagebox.OK,
						Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	public void addNewPhuTungRedirect() {
		Executions.sendRedirect("./PhuTung_Add.zul");
	}

	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

	public List<PhuTung> getListOfPhuTung() {
		return listOfPhuTung;
	}

	public void setListOfPhuTung(List<PhuTung> listOfPhuTung) {
		this.listOfPhuTung = listOfPhuTung;
	}

	public PhuTung getSelectedPhuTung() {
		return selectedPhuTung;
	}

	public void setSelectedPhuTung(PhuTung selectedPhuTung) {
		this.selectedPhuTung = selectedPhuTung;
	}
	
	
}
