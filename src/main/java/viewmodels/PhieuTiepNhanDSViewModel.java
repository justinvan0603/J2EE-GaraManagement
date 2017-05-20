package viewmodels;

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

import business.entities.BangThamSo;
import business.entities.PhieuTiepNhan;
import business.service.BangThamSoServiceImpl;
import business.service.PhieuTiepNhanServiceImpl;
import utils.DateUtil;
import utils.SystemParam;

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
	public static final String SELECTED_PHIEUTIEPNHAN_ID = "selected_phieutiepnhan_id";
	// list of search types for view page
	private static final String[] SEARCH_TYPES = new String[] { "Mã phiếu", "Biển số xe", "Ngày tiếp nhận",
			"Ngày hẹn trả", "Tên nhân viên lập" };

	@WireVariable
	private PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl;
	private BangThamSoServiceImpl bangThamSoServiceImpl;
	private List<PhieuTiepNhan> listOfReceiveHeaders;

	@Init
	public void init() {
		// get service bean from Spring framework
		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.phieuTiepNhanServiceImpl = (PhieuTiepNhanServiceImpl) SpringUtil.getBean("phieutiepnhan_service");
		this.bangThamSoServiceImpl = (BangThamSoServiceImpl) SpringUtil.getBean("bangthamso_service");

		if (this.bangThamSoServiceImpl != null) {
			SystemParam.listBTS = this.bangThamSoServiceImpl.getAll(BangThamSo.class);
			System.out.println(SystemParam.listBTS.toString());
		} else {
			throw new NullPointerException("Receive Header Service is null");
		}

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

	@Command
	public void lapPhieuDichVu(@BindingParam("phietiepnhan_id") Integer phieuTiepNhanId) {
		// Send selected id into Session
		Sessions.getCurrent().setAttribute(SELECTED_PHIEUTIEPNHAN_ID, phieuTiepNhanId);
		// Messagebox.show(Sessions.getCurrent().getAttribute(SELECTED_PHIEUTIEPNHAN_ID).toString());
		Executions.sendRedirect("./PhieuDichVu_Add.zul");
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
			case 4: // filter by staff name
				this.listOfReceiveHeaders = this.phieuTiepNhanServiceImpl
						.filterByCreationStaffName(searchString.trim());
				break;
			default:
				break;
			}
		} else {
			this.listOfReceiveHeaders = this.phieuTiepNhanServiceImpl.getAll(PhieuTiepNhan.class);
		}

	}

	/**
	 * Xoa phieu tiep nhan
	 * 
	 * @param id
	 *            : id phieu tiep nhan can xoa
	 */
	@Command
	@NotifyChange("listOfReceiveHeaders")
	public void deletePhieuTiepNhan(@BindingParam("phieutiepnhan_id") final Long id) {
		// show confirmed messagebox to make sure deletion task again
		Messagebox.show("Bạn có chắc muốn xóa dữ liệu đã chọn ? ", "Thông báo", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Integer value = ((Integer) event.getData()).intValue();
						switch (value) {
						case Messagebox.OK:
							phieuTiepNhanServiceImpl.delete(id, PhieuTiepNhan.class);
							// refresh data
							// reload page
							Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
							break;

						default:
							break;
						}
					}
				});
	}

	/**
	 * Xem va sua thong tin phieu tiep nhan
	 * 
	 * @param id
	 *            : id phieu tiep nhan de xem chi tiet.
	 */
	@Command
	public void seePhieuTiepNhanDetail(@BindingParam("phieutiepnhan_id") Long id) {
		// save id into session
		Sessions.getCurrent().setAttribute(SELECTED_PHIEUTIEPNHAN_ID, id);
		Executions.sendRedirect("./PhieuTiepNhan_Edit.zul");
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
