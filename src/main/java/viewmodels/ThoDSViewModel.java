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

import business.entities.Tho;
import business.service.ThoServiceImpl;

public class ThoDSViewModel {
	
	// parameter name of selected id for passing via Executions class
	public static final String SELECTED_THO_ID = "selected_tho_id";
	// array of search types string for this page
	private static final String[] SEARCH_TYPES = new String[] {

				"Tất cả", "Tên", "Số điện thoại", "Địa chỉ" };
	
	@WireVariable
	private ThoServiceImpl thoService;
	private List<Tho> listOfTho;
	private Tho selectedTho;
	
	@Init
	public void init() {
		// get bean from Spring, no need to create new object, Spring framework
		// will manage this bean
		this.thoService = (ThoServiceImpl) SpringUtil.getBean("tho_service");
		if (this.thoService != null) {
			this.listOfTho = this.thoService.getAll(Tho.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}

	}
	
	@Command
	@NotifyChange("listOfTho")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		switch (selectedIndex) {
		case 0: // search by id of receive header
			this.listOfTho = this.thoService.getAll(Tho.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfTho.clear(); // clear all items
				this.listOfTho = this.thoService.find(searchString, null, null);
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				this.listOfTho.clear(); // clear all items
				this.listOfTho = this.thoService.find(null, searchString, null);
			}
			break;
		case 3: //
			if (!searchString.isEmpty()) {
				this.listOfTho.clear(); // clear all items
				this.listOfTho = this.thoService.find(null, null, searchString);
			}
			break;
		default:
			break;
		}
	}
	
	@Command
	public void editTho(@BindingParam("tho_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_THO_ID, id);
		Executions.sendRedirect("./Tho_Edit.zul");
	}

	@Command
	@NotifyChange("listOfTho")
	public void deleteTho(@BindingParam("tho_id") long id) {

		 if (this.thoService.delete(id, Tho.class)) {
 			this.listOfTho = this.thoService.getAll(Tho.class);
 		} else {
 			Messagebox.show("Không thể xoá thợ đã có làm việc với cửa hàng", "Lỗi", Messagebox.OK, Messagebox.ERROR);
 		}
	}
	
//	@Command
//	@NotifyChange("listOfTho")
//	public void confirmDeleteTho(@BindingParam("tho_id") final long id) {
//
//		 Messagebox.show("Bạn có chắc chắn muốn xoá?", "Thông báo", Messagebox.YES | Messagebox.NO, 
//			        Messagebox.QUESTION, new EventListener<Event>() {
//			            @Override
//			            public void onEvent(final Event evt) throws InterruptedException {
//			                if (Messagebox.ON_YES.equals(evt.getName())) {
//			                    deleteTho(id);
//			                }
//			            }
//			        }
//			    );
//	}
	
	@Command
	public void addNewThoRedirect() {
		Executions.sendRedirect("./Tho_Add.zul");
	}

	public List<Tho> getListOfTho() {
		return listOfTho;
	}

	public void setListOfTho(List<Tho> listOfTho) {
		this.listOfTho = listOfTho;
	}

	public Tho getSelectedTho() {
		return selectedTho;
	}

	public void setSelectedTho(Tho selectedTho) {
		this.selectedTho = selectedTho;
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
