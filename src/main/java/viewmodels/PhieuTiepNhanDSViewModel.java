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

import business.entities.PhieuTiepNhan;
import business.service.PhieuTiepNhanServiceImpl;
import utils.DateUtil;

/**
 * View model for page receive_header_list
 * 
 * @author TNS
 *
 */
public class PhieuTiepNhanDSViewModel {

	// name and parameters for passing into sessions
	public static final String ADD_NEW_TYPE = "add_new_type";
	public static final String FROM_NEW_CUSTOMER = "new_customer";
	public static final String FROM_FREQUENTER = "frequenter";
	// list of search types for view page
	private static final String[] SEARCH_TYPES = new String[] { "Mã phiếu", "Biển số xe", "Ngày tiếp nhận",
			"Ngày hẹn trả" };

	@WireVariable
	private PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl;
	private List<PhieuTiepNhan> listOfReceiveHeaders;

	@Init
	public void init() {
		// get service bean from Spring framework
		this.phieuTiepNhanServiceImpl = (PhieuTiepNhanServiceImpl) SpringUtil.getBean("phieutiepnhan_service");
		if (this.phieuTiepNhanServiceImpl != null) {
			this.listOfReceiveHeaders = this.phieuTiepNhanServiceImpl.getAll(PhieuTiepNhan.class);
		} else {
			throw new NullPointerException("Receive Header Service is null");
		}
	}

	//
	// Commands
	//

	@Command
	public void addNewHeaderFromNewCustomer() {
		Sessions.getCurrent().setAttribute(ADD_NEW_TYPE, FROM_NEW_CUSTOMER);
		Executions.sendRedirect("./PhieuTiepNhan_AddKhachMoi.zul");
	}

	@Command
	public void addNewHeaderFromFrequenter() {
		Sessions.getCurrent().setAttribute(ADD_NEW_TYPE, FROM_FREQUENTER);
		Executions.sendRedirect("./PhieuTiepNhan_AddKhachQuen.zul");
	}

	/**
	 * Handle when user filter data from search box
	 * 
	 * @param typeIndex
	 *            : selected index from combobox to determine type of filter
	 * @param searchString
	 *            : criteria for filtering
	 */
	@Command
	@NotifyChange("listOfReceiveHeaders")
	public void filterData(@BindingParam("search_index") int typeIndex,
			@BindingParam("search_string") String searchString) {
		if (!searchString.isEmpty()) {
			switch (typeIndex) {
			case 0: // search by id of receive header

				this.listOfReceiveHeaders.clear(); // clear all items
				PhieuTiepNhan header = this.phieuTiepNhanServiceImpl.findById(Long.parseLong(searchString),
						PhieuTiepNhan.class);
				this.listOfReceiveHeaders.add(header);
				break;
			case 1: // search by license plate
				this.listOfReceiveHeaders = this.phieuTiepNhanServiceImpl.findByLicensePlate(searchString);
				break;
			case 2: // search by creation date
				try {
					String[] dateSplit = searchString.split("/");
					this.listOfReceiveHeaders = this.phieuTiepNhanServiceImpl.findByDate("creationDate",
							DateUtil.parseFromStringArray(dateSplit));
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
				break;
			case 3:
				try {
					String[] dateSplit = searchString.split("/");
					this.listOfReceiveHeaders = this.phieuTiepNhanServiceImpl.findByDate("givebackDate",
							DateUtil.parseFromStringArray(dateSplit));
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
				break;
			default:
				break;
			}
		} else {
			this.listOfReceiveHeaders = this.phieuTiepNhanServiceImpl.getAll(PhieuTiepNhan.class);
		}

	}

	public List<PhieuTiepNhan> getListOfReceiveHeaders() {
		return listOfReceiveHeaders;
	}

	public void setListOfReceiveHeaders(List<PhieuTiepNhan> listOfReceiveHeaders) {
		this.listOfReceiveHeaders = listOfReceiveHeaders;
	}

	/**
	 * returns String[] search types for page view to access
	 * 
	 * @return : SEARCH_TYPES of class
	 */
	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

}