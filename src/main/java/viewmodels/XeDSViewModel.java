package viewmodels;

import java.util.Arrays;
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

import business.entities.Xe;
import business.service.XeServiceImpl;
import utils.DateUtil;

/**
 * View model for view 'XeDS' page
 * 
 * @author TNS
 *
 */
public class XeDSViewModel {

	// parameter name of selected id for passing via Executions class
	public static final String SELECTED_VEHICLE_ID = "selected_vehicle_id";
	// array of search types string for this page
	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Biển số", "Ngày tiếp nhận", "Xe GARA", "Xe khách hàng" };
	@WireVariable
	private XeServiceImpl xeServiceImpl; // database service
	private List<Xe> listOfVehicel;
	private Xe selectedXe;

	@Init
	public void init() {
		
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		
		this.xeServiceImpl = (XeServiceImpl) SpringUtil.getBean("xe_service");
		if (this.xeServiceImpl != null) {
			// be default, load all entities from table
			this.listOfVehicel = this.xeServiceImpl.getAll(Xe.class);
		}
	}

	@Command
	@NotifyChange("listOfVehicel")
	public void filterData(@BindingParam("search_index") int searchIndex,
			@BindingParam("search_string") String searchString) {
		switch (searchIndex) {
		case 0: // search by id of receive header
			this.listOfVehicel = this.xeServiceImpl.getAll(Xe.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfVehicel.clear(); // clear all items
				this.listOfVehicel.add(this.xeServiceImpl.findByLicensePlate(searchString));
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				try {
					String[] dateSplit = searchString.split("/");
					System.out.println(Arrays.toString(dateSplit));
					this.listOfVehicel = this.xeServiceImpl.filterByDate(DateUtil.parseFromStringArray(dateSplit));
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
			}
			break;
		case 3: //
			this.listOfVehicel = this.xeServiceImpl.filterByType(true);
			break;
		case 4:
			this.listOfVehicel = this.xeServiceImpl.filterByType(false);
			break;
		default:
			break;
		}
	}

	/**
	 * Redirect to vehicle detail page
	 * 
	 * @param vehicleId
	 *            : id of vehicle
	 */
	@Command
	public void seeVehicleDetail(@BindingParam("vehicle_id") String vehicleId) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_VEHICLE_ID, vehicleId);
		Executions.sendRedirect("./Xe_Edit.zul");
	}

	@Command
	@NotifyChange("listOfVehicel")
	public void deleteVehicle(@BindingParam("vehicle_id") String bienSoXe) {
		if (this.xeServiceImpl.delete(bienSoXe)) {
			this.listOfVehicel = this.xeServiceImpl.getAll(Xe.class);
		} else {
			Messagebox.show("Lỗi khi xoá");
		}
	}

	@Command
	public void addNewVehicle() {
		Executions.sendRedirect("./Xe_Add.zul");
	}

	public List<Xe> getListOfVehicel() {
		return listOfVehicel;
	}

	public void setListOfVehicel(List<Xe> listOfVehicel) {
		this.listOfVehicel = listOfVehicel;
	}

	public Xe getSelectedXe() {
		return selectedXe;
	}

	public void setSelectedXe(Xe selectedXe) {
		this.selectedXe = selectedXe;
	}

	// get search types
	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

}
