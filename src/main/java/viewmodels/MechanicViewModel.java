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

import business.entities.Mechanic;
import business.service.MechanicServiceImpl;

public class MechanicViewModel {

	// list of search types in view page
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	// set up values for search types statically
	static {
		SEARCH_TYPES.add("Họ tên");
	}

	@WireVariable
	private MechanicServiceImpl mechanicService;
	private List<Mechanic> listOfMechanics;
	private Mechanic selectedMechanic;

	@Init
	public void init() {
		// get bean from Spring, no need to create new object, Spring framework
		// will manage this bean
		this.mechanicService = (MechanicServiceImpl) SpringUtil.getBean("mechanic_service");
		if (this.mechanicService != null) {
			this.listOfMechanics = this.mechanicService.getAll(Mechanic.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}

	}

	// Commands

	/**
	 * Handle when user click search button on view. Service will filter data
	 * from persistence layer and update the list. The change of list data will
	 * notify to grid on view by @NotifyChange annotation.
	 * 
	 * @param searchString
	 *            : search string as criteria
	 * @param selectedIndex
	 *            : selected index of combobox to get type of search
	 */
	@Command
	@NotifyChange("listOfMechanics")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listOfMechanics = this.mechanicService.findByName(searchString);
		}
	}

	/**
	 * Redirect to add new mechanic page
	 */
	@Command
	public void addNewMechanicRedirect() {
		Executions.sendRedirect("./new_mechanic.zul");
	}

	public List<Mechanic> getListOfMechanics() {
		return listOfMechanics;
	}

	public void setListOfMechanics(List<Mechanic> listOfMechanics) {
		this.listOfMechanics = listOfMechanics;
	}

	public Mechanic getSelectedMechanic() {
		return selectedMechanic;
	}

	public void setSelectedMechanic(Mechanic selectedMechanic) {
		this.selectedMechanic = selectedMechanic;
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
