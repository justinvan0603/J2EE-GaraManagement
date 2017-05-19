package viewmodels;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuChi;
import business.service.PhieuChiServiceImpl;

public class PhieuChiEditViewModel {

	private PhieuChiServiceImpl phieuChiServiceImpl;
	private PhieuChi phieuChi;

	@Init
	public void init() {
		this.phieuChiServiceImpl = (PhieuChiServiceImpl) SpringUtil.getBean("phieuchi_service");
		if (this.phieuChiServiceImpl != null) {
			Integer selectedId = (Integer) Sessions.getCurrent().getAttribute(PhieuChiDSViewModel.SELECTED_PHIEUCHI_ID);
			// make sure we have valid session value
			if (selectedId == null) {
				Executions.sendRedirect("./PhieuChi_DS.zul");
			} else {
				this.phieuChi = this.phieuChiServiceImpl.findById(selectedId, PhieuChi.class);
			}
		}
	}

	@Command
	public void luuPhieuChi() {
		if (this.phieuChiServiceImpl != null) {

			if (this.phieuChiServiceImpl.update(Long.valueOf(this.phieuChi.getId().toString()), this.phieuChi)) {
				Messagebox.show("Lưu thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION,
						new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								// TODO Auto-generated method stub
								Integer value = ((Integer) event.getData()).intValue();
								switch (value) {
								case Messagebox.OK:
									Executions.sendRedirect("./PhieuChi_DS.zul");
									break;

								default:
									break;
								}
							}
						});
			}
		}
	}

	public PhieuChi getPhieuChi() {
		return phieuChi;
	}

	public void setPhieuChi(PhieuChi phieuChi) {
		this.phieuChi = phieuChi;
	}

}
