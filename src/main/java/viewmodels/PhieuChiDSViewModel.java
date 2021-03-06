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
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuChi;
import business.service.PhieuChiServiceImpl;
import utils.DateUtil;

/**
 * View model for 'PhieuChi' list view page
 * 
 * @author TNS
 *
 */
public class PhieuChiDSViewModel {

	public static final String SELECTED_PHIEUCHI_ID = "selected_phieuchi_id";

	private static String[] SEARCH_TYPES = new String[] { "Tất cả", "Nhân viên lập", "Ngày lập" };
	private PhieuChiServiceImpl phieuChiServiceImpl;
	private List<PhieuChi> listOfPhieuChis;

	@Init
	public void init() {

		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.phieuChiServiceImpl = (PhieuChiServiceImpl) SpringUtil.getBean("phieuchi_service");
		if (this.phieuChiServiceImpl != null) {
			this.listOfPhieuChis = this.phieuChiServiceImpl.getAll(PhieuChi.class);
		}
	}

	/**
	 * Filter data
	 * 
	 * @param searchIndex
	 * @param searchString
	 */
	@Command
	@NotifyChange("listOfPhieuChis")
	public void filterData(@BindingParam("search_index") int searchIndex,
			@BindingParam("search_string") String searchString) {

		// if search string is empty, get all by default
		if (searchString.isEmpty()) {
			this.listOfPhieuChis = this.phieuChiServiceImpl.getAll(PhieuChi.class);
		} else
			switch (searchIndex) {
			case 0: // load all
				this.listOfPhieuChis = this.phieuChiServiceImpl.getAll(PhieuChi.class);
				break;
			case 1: // search by employee
				this.listOfPhieuChis = this.phieuChiServiceImpl.filterByCreationStaffName(searchString.trim());
				break;
			case 2: // search by creation date
				try {
					this.listOfPhieuChis = this.phieuChiServiceImpl
							.findByCreationDate(DateUtil.parseFromStringArray(searchString.trim().split("/")));
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập đúng định dạng ngày. Ví dụ : 25/8/2016", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
				break;
			default:
				break;
			}
	}

	/**
	 * Add new 'Phieu Chi', redirect to add new view page
	 */
	@Command
	public void themMoi() {
		Executions.sendRedirect("./PhieuChi_Add.zul");
	}

	@Command
	public void seePhieuChiDetail(@BindingParam("selected_phieuchi_id") Integer id) {
		// Put selected id into session
		Sessions.getCurrent().setAttribute(SELECTED_PHIEUCHI_ID, id);
		Executions.sendRedirect("./PhieuChi_Edit.zul");
	}

	@Command
	public void xoaPhieuChi(@BindingParam("selected_phieuchi_id") final Long id) {

		// show confirmed messagebox to make sure deletion task again
		Messagebox.show("Bạn có chắc muốn xóa dữ liệu đã chọn ? ", "Thông báo", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Integer value = ((Integer) event.getData()).intValue();
						switch (value) {
						case Messagebox.OK:
							phieuChiServiceImpl.delete(id, PhieuChi.class);
							Executions.sendRedirect("./PhieuChi_DS.zul");
							break;
						default:
							break;
						}
					}
				});

	}

	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

	public List<PhieuChi> getListOfPhieuChis() {
		return listOfPhieuChis;
	}

}
