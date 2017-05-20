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

import business.entities.CT_PhieuBaoHanh;
import business.entities.PhieuBaoHanh;
import business.service.CTPhieuBaoHanhServiceImpl;
import business.service.PhieuBaoHanhServiceImpl;
import utils.DateUtil;

public class PhieuBaoHanhDSViewModel {
	// session parameter keys
	public static final String SELECTED_PHIEUBAOHANH_ID = "selected_phieubaohanh_id";
	// list of search types for view page
	private static final String[] SEARCH_TYPES = new String[] { "Mã phiếu", "Ngày lập", "Nhân viên lập" };
	@WireVariable
	private PhieuBaoHanhServiceImpl phieuBaoHanhServiceImpl;
	private CTPhieuBaoHanhServiceImpl ctPhieuBaoHanhServiceImpl;
	private List<PhieuBaoHanh> listOfPhieuBaoHanhs;

	@Init
	public void init() {
		
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		
		this.phieuBaoHanhServiceImpl = (PhieuBaoHanhServiceImpl) SpringUtil.getBean("phieubaohanh_service");
		this.ctPhieuBaoHanhServiceImpl = (CTPhieuBaoHanhServiceImpl) SpringUtil.getBean("ct_phieubaohanh_service");
		if (this.phieuBaoHanhServiceImpl != null) {
			this.listOfPhieuBaoHanhs = this.phieuBaoHanhServiceImpl.getAll(PhieuBaoHanh.class);
		} else {
			// can not create service bean
			throw new NullPointerException("service for PhieuBaoHanhDSViewModel is null");
		}
	}

	/**
	 * Filter data when user interact with search text box and click search
	 * button
	 * 
	 * @param index
	 */
	@Command
	@NotifyChange("listOfPhieuBaoHanhs")
	public void filterData(@BindingParam("search_index") int index, @BindingParam("search_string") String keyword) {
		if (!keyword.isEmpty()) {
			try {
				switch (index) {
				case 0: // search by id
					this.listOfPhieuBaoHanhs.clear();
					this.listOfPhieuBaoHanhs
							.add(this.phieuBaoHanhServiceImpl.findById(Long.parseLong(keyword), PhieuBaoHanh.class));
					break;

				case 1: // search by creation date
					this.listOfPhieuBaoHanhs = this.phieuBaoHanhServiceImpl
							.filterByCreationDate(DateUtil.parseFromStringArray(keyword.split("/")));
					break;
				case 2: // search by staff name
					this.listOfPhieuBaoHanhs = this.phieuBaoHanhServiceImpl.filterByCreationStaffName(keyword.trim());
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				Messagebox.show("Vui lòng nhập đúng địng dạng với hình thức tìm kiếm bạn đã chọn", "Lỗi", Messagebox.OK,
						Messagebox.ERROR);
				e.printStackTrace();
			}
		} else {
			this.listOfPhieuBaoHanhs = this.phieuBaoHanhServiceImpl.getAll(PhieuBaoHanh.class);
		}
	}

	@Command
	public void seePhieuBaoHanhDetail(@BindingParam("selected_phieubaohanh_id") long id) {
		Sessions.getCurrent().setAttribute(SELECTED_PHIEUBAOHANH_ID, id);
		Executions.sendRedirect("./PhieuBaoHanh_Edit.zul");
	}

	@Command
	public void themMoi() {
		Executions.sendRedirect("./PhieuBaoHanh_Add.zul");
	}

	@Command
	@NotifyChange("listOfPhieuBaoHanhs")
	public void xoaPhieuBaoHanh(@BindingParam("maphieubaohanh") Long id) {
		// delete detail
		List<CT_PhieuBaoHanh> listOfCT_PhieuBaoHanh = this.ctPhieuBaoHanhServiceImpl
				.getAllByPhieuBaoHanhId(id.intValue());
		if (!listOfCT_PhieuBaoHanh.isEmpty()) {
			for (CT_PhieuBaoHanh ct_PhieuBaoHanh : listOfCT_PhieuBaoHanh) {
				this.ctPhieuBaoHanhServiceImpl.delete(Long.valueOf(ct_PhieuBaoHanh.getId().toString()),
						CT_PhieuBaoHanh.class);
			}
		}
		this.phieuBaoHanhServiceImpl.delete(id, PhieuBaoHanh.class);
		this.listOfPhieuBaoHanhs = this.phieuBaoHanhServiceImpl.getAll(PhieuBaoHanh.class);

	}

	public List<PhieuBaoHanh> getListOfPhieuBaoHanhs() {
		return listOfPhieuBaoHanhs;
	}

	public void setListOfPhieuBaoHanhs(List<PhieuBaoHanh> listOfPhieuBaoHanhs) {
		this.listOfPhieuBaoHanhs = listOfPhieuBaoHanhs;
	}

	/**
	 * Get search types for view page to access
	 * 
	 * @return
	 */
	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

}
