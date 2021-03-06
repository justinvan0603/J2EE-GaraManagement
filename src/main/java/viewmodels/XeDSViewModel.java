package viewmodels;

import java.util.Arrays;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
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

			"Tất cả", "Biển số", "Ngày tiếp nhận" };
	@WireVariable
	private XeServiceImpl xeServiceImpl; // database service
	private List<Xe> listOfVehicel;
	private Xe selectedXe;

	@Init
	public void init() {

		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
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
	public void deleteVehicle(@BindingParam("vehicle_id") final String bienSoXe) {

		// show confirmed messagebox to make sure deletion task again
		Messagebox.show("Bạn có chắc muốn xóa dữ liệu đã chọn ? ", "Thông báo", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Integer value = ((Integer) event.getData()).intValue();
						switch (value) {
						case Messagebox.OK:
							if (xeServiceImpl.delete(bienSoXe)) {
								listOfVehicel = xeServiceImpl.getAll(Xe.class);
								Executions.sendRedirect("./Xe_DS.zul");
							} else {
								Messagebox.show("Không thể xóa, dữ liệu xe này có trong các phiếu khác.", "Lỗi",
										Messagebox.RETRY, Messagebox.ERROR);
							}
							break;

						default:
							break;
						}
					}
				});
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
