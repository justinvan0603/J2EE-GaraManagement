package viewmodels;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuTiepNhan;
import business.service.PhieuTiepNhanServiceImpl;

public class PhieuTiepNhanEditViewModel {
	// all services
	private PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl;
	private PhieuTiepNhan selectedPhieuTiepNhan;

	@Init
	public void init() {
		// get session values from zk container
		Long id = (Long) Sessions.getCurrent().getAttribute(PhieuTiepNhanDSViewModel.SELECTED_PHIEUTIEPNHAN_ID);

		// make sure Session is valid
		if (id == null) {
			// if Session is null, back to page of list
			Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
		} else {
			this.phieuTiepNhanServiceImpl = (PhieuTiepNhanServiceImpl) SpringUtil.getBean("phieutiepnhan_service");
			if (this.phieuTiepNhanServiceImpl != null) {
				this.selectedPhieuTiepNhan = this.phieuTiepNhanServiceImpl.findById(id, PhieuTiepNhan.class);
			} else {
				throw new NullPointerException("service for PhieuTiepNhanEditViewModel class is null");
			}
		}

	}

	public PhieuTiepNhan getSelectedPhieuTiepNhan() {
		return selectedPhieuTiepNhan;
	}

	public void setSelectedPhieuTiepNhan(PhieuTiepNhan selectedPhieuTiepNhan) {
		this.selectedPhieuTiepNhan = selectedPhieuTiepNhan;
	}

	@Command
	public void updatePhieuTiepNhan(@BindingParam("phieutiepnhan_id") Long id) {
		if (this.phieuTiepNhanServiceImpl.update(id, this.selectedPhieuTiepNhan)) {
			Messagebox.show("Cập nhật thành công");
			Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
		}
	}

}
