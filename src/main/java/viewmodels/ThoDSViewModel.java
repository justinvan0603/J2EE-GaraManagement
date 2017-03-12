package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;

import business.entities.Tho;
import business.service.ThoServiceImpl;;

public class ThoDSViewModel {
	
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();

	static {
		SEARCH_TYPES.add("Họ tên");
	}
	
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
		// if search type is name
		if (selectedIndex == 0) {
			this.listOfTho = this.thoService.findByName(searchString);
		}
	}
	
	@Command
	public void addNewThoRedirect() {
		Executions.sendRedirect("./new_mechanic.zul");
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
	public List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}
	
}
