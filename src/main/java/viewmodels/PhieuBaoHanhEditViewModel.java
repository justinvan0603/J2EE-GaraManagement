package viewmodels;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuBaoHanh;
import business.entities.PhieuBaoHanh;
import business.service.CTPhieuBaoHanhServiceImpl;
import business.service.PhieuBaoHanhServiceImpl;

public class PhieuBaoHanhEditViewModel {
	private PhieuBaoHanhServiceImpl phieuBaoHanhServiceImpl;
	private CTPhieuBaoHanhServiceImpl ct_PhieuBaoHanhServiceImpl;
	private PhieuBaoHanh selectedPhieuBaoHanh;
	private List<CT_PhieuBaoHanh> listOfCT_PhieuBaoHanhs;

	// temporary dates for view
	private Date tempNgayTra;
	private Date tempNgayHenTra;

	@Init
	public void init() {
		
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		
		// get parameter from application system
		Long id = (Long) Sessions.getCurrent().getAttribute(PhieuBaoHanhDSViewModel.SELECTED_PHIEUBAOHANH_ID);
		// if session value is invaid, back to list view page
		if (id == null) {
			Executions.sendRedirect("./PhieuBaoHanh_DS.zul");
		} else {
			this.phieuBaoHanhServiceImpl = (PhieuBaoHanhServiceImpl) SpringUtil.getBean("phieubaohanh_service");
			this.ct_PhieuBaoHanhServiceImpl = (CTPhieuBaoHanhServiceImpl) SpringUtil.getBean("ct_phieubaohanh_service");
			if (this.phieuBaoHanhServiceImpl != null) {
				this.selectedPhieuBaoHanh = this.phieuBaoHanhServiceImpl.findById(id, PhieuBaoHanh.class);
				// make sure we have valid object
				if (this.selectedPhieuBaoHanh != null) {
					this.listOfCT_PhieuBaoHanhs = this.ct_PhieuBaoHanhServiceImpl
							.getAllByPhieuBaoHanhId(this.selectedPhieuBaoHanh.getMaPhieuBaoHanh());
				}
			}
		}
	}

	@Command
	@NotifyChange("selectedPhieuBaoHanh")
	public void updatePhieuBaoHanh() {
		if (this.phieuBaoHanhServiceImpl.update(Long.valueOf(this.selectedPhieuBaoHanh.getMaPhieuBaoHanh().toString()),
				this.selectedPhieuBaoHanh)) {
			Messagebox.show("Cập nhật thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
		} else {
			Messagebox.show("Cập nhật lối", "Lỗi", Messagebox.RETRY, Messagebox.ERROR);
		}
	}

	@Command
	@NotifyChange("listOfCT_PhieuBaoHanhs")
	public void updateChiTietPhieuBaoHanh(@BindingParam("id") Long id) {
		CT_PhieuBaoHanh ct_PhieuBaoHanh = this.ct_PhieuBaoHanhServiceImpl.findById(id, CT_PhieuBaoHanh.class);
		ct_PhieuBaoHanh.setNgayHenTra(this.tempNgayHenTra);
		ct_PhieuBaoHanh.setNgayTra(this.tempNgayTra);
		if (this.ct_PhieuBaoHanhServiceImpl.update(id, ct_PhieuBaoHanh)) {
			this.listOfCT_PhieuBaoHanhs = this.ct_PhieuBaoHanhServiceImpl
					.getAllByPhieuBaoHanhId(this.selectedPhieuBaoHanh.getMaPhieuBaoHanh()); // refresh
																							// list
																							// of
																							// details
			Messagebox.show("Cập nhật thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
		} else {
			Messagebox.show("Cập nhật lối", "Lỗi", Messagebox.RETRY, Messagebox.ERROR);
		}
	}

	/**
	 * Update temporary ngayHenTra when user focus on specified {@link Datebox}
	 * to update Date
	 * 
	 * @param ngayHenTra
	 *            : new value of ngayHenTra
	 */
	@Command
	public void onDateBoxNgayHenTraChange(@BindingParam("ngayhentra") Date ngayHenTra) {
		this.tempNgayHenTra = ngayHenTra;
	}

	/**
	 * Update temporary ngayHenTra when user focus on specified {@link Datebox}
	 * to update Date
	 * 
	 * @param ngayTra
	 *            :new value of ngay tra
	 */
	@Command
	public void onDateBoxNgayTraChange(@BindingParam("ngaytra") Date ngayTra) {
		this.tempNgayTra = ngayTra;
	}

	public PhieuBaoHanh getSelectedPhieuBaoHanh() {
		return selectedPhieuBaoHanh;
	}

	public void setSelectedPhieuBaoHanh(PhieuBaoHanh selectedPhieuBaoHanh) {
		this.selectedPhieuBaoHanh = selectedPhieuBaoHanh;
	}

	public List<CT_PhieuBaoHanh> getListOfCT_PhieuBaoHanhs() {
		return listOfCT_PhieuBaoHanhs;
	}

	public void setListOfCT_PhieuBaoHanhs(List<CT_PhieuBaoHanh> listOfCT_PhieuBaoHanhs) {
		this.listOfCT_PhieuBaoHanhs = listOfCT_PhieuBaoHanhs;
	}

	public Date getTempNgayTra() {
		return tempNgayTra;
	}

	public void setTempNgayTra(Date tempNgayTra) {
		this.tempNgayTra = tempNgayTra;
	}

	public Date getTempNgayHenTra() {
		return tempNgayHenTra;
	}

	public void setTempNgayHenTra(Date tempNgayHenTra) {
		this.tempNgayHenTra = tempNgayHenTra;
	}

}
