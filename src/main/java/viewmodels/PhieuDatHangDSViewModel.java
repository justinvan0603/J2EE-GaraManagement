package viewmodels;

import java.util.Date;
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

import business.entities.CT_PhieuDatHang;
import business.entities.PhieuDatHang;
import business.service.ChiTietPhieuDatHangServiceImpl;
import business.service.PhieuDatHangServiceImpl;
import utils.DateUtil;

public class PhieuDatHangDSViewModel {

	public static final String SELECTED_PDH_ID = "selected_phieudathang_id";
	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Mã phiếu", "Nhân viên lập", "Ngày lập phiếu", "Ngày hẹn giao", "Tên nhà cung cấp" };

	@WireVariable
	private PhieuDatHangServiceImpl phieuDatHangService;
	private ChiTietPhieuDatHangServiceImpl chiTietPhieuDatHangService;
	private List<PhieuDatHang> listOfPhieuDatHang;

	@Init
	public void init() {

		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.phieuDatHangService = (PhieuDatHangServiceImpl) SpringUtil.getBean("phieudathang_service");
		this.chiTietPhieuDatHangService = (ChiTietPhieuDatHangServiceImpl) SpringUtil
				.getBean("ct_phieudathang_service");
		if (this.phieuDatHangService != null) {
			this.listOfPhieuDatHang = this.phieuDatHangService.getAll(PhieuDatHang.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}
	}

	@Command
	@NotifyChange("listOfPhieuDatHang")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		switch (selectedIndex) {
		case 0: // search by id of receive header
			this.listOfPhieuDatHang = this.phieuDatHangService.getAll(PhieuDatHang.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(searchString, null, null, null, null);
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(null, searchString, null, null, null);
			}
			break;
		case 3: //
			if (!searchString.isEmpty()) {
				try {
					String[] dateSplit = searchString.split("/");
					System.out.println(DateUtil.parseFromStringArray(dateSplit));
					this.listOfPhieuDatHang = this.phieuDatHangService.find(null, null,
							DateUtil.parseFromStringArray(dateSplit), null, null);
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
			}
			break;
		case 4: //
			if (!searchString.isEmpty()) {
				Date searchDate = null;
				try {
					String[] dateSplit = searchString.split("/");
					searchDate = DateUtil.parseFromStringArray(dateSplit);
					System.out.println(DateUtil.parseFromStringArray(dateSplit));
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(null, null, null, searchDate, null);
			}
			break;
		case 5: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuDatHang.clear(); // clear all items
				this.listOfPhieuDatHang = this.phieuDatHangService.find(null, null, null, null, searchString);
			}
			break;
		default:
			break;
		}
	}

	@Command
	@NotifyChange("listOfPhieuDatHang")
	public void deletePhieuDatHang(@BindingParam("phieudh_id") final long id) {
		Messagebox.show("Bạn có chắc muốn xóa dữ liệu đã chọn ? ", "Thông báo", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Integer value = ((Integer) event.getData()).intValue();
						switch (value) {
						case Messagebox.OK:
							try {
								List<CT_PhieuDatHang> listOfCT_PhieuDatHang = chiTietPhieuDatHangService.getAllByPhieuDatHangId(id);
								if (!listOfCT_PhieuDatHang.isEmpty()) {
									for (CT_PhieuDatHang ct : listOfCT_PhieuDatHang) {
										chiTietPhieuDatHangService.delete(ct.getId(), CT_PhieuDatHang.class);
									}
								}
								phieuDatHangService.delete(id, PhieuDatHang.class);
								listOfPhieuDatHang = phieuDatHangService.getAll(PhieuDatHang.class);
							} catch (Exception e) {
								Messagebox.show("Có lỗi xảy ra khi xoá phiếu", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
							}
							Executions.sendRedirect("./PhieuDatHang_DS.zul");
							break;

						default:
							break;
						}
					}
				});
	}

	@Command
	public void addNewPhieuDatHangRedirect() {
		Executions.sendRedirect("./PhieuDatHang_Add.zul");
	}

	@Command
	public void editPhieuDatHang(@BindingParam("phieudh_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_PDH_ID, id);
		Executions.sendRedirect("./PhieuDatHang_Edit.zul");
	}

	@Command
	public void createPhieuNhapHang(@BindingParam("phieudh_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(SELECTED_PDH_ID, id);
		Executions.sendRedirect("./PhieuNhapHang_Add_Ordered.zul");
	}

	public List<PhieuDatHang> getListOfPhieuDatHang() {
		return listOfPhieuDatHang;
	}

	public void setListOfPhieuDatHang(List<PhieuDatHang> listOfPhieuDatHang) {
		this.listOfPhieuDatHang = listOfPhieuDatHang;
	}

	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

	public PhieuDatHangServiceImpl getPhieuDatHangService() {
		return phieuDatHangService;
	}

	public void setPhieuDatHangService(PhieuDatHangServiceImpl phieuDatHangService) {
		this.phieuDatHangService = phieuDatHangService;
	}

}
