package viewmodels;

import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.NhanVien;
import business.entities.PhieuChi;
import business.service.NhanVienServiceImpl;
import business.service.PhieuChiServiceImpl;

public class PhieuChiAddViewModel {
	private PhieuChiServiceImpl phieuChiServiceImpl;
	private NhanVienServiceImpl nhanVienServiceImpl;
	private PhieuChi phieuChi;
	private NhanVien staff;

	@Init
	public void init() {
		this.phieuChiServiceImpl = (PhieuChiServiceImpl) SpringUtil.getBean("phieuchi_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");

		if (this.phieuChiServiceImpl != null && this.nhanVienServiceImpl != null) {
			// initialize view model's 'phieu chi'
			this.phieuChi = new PhieuChi();
			// get session from 'PhieuNhapHang_DS' view page if page is
			// redirected from this page
			Integer selectedPhieuNhapHangId = (Integer) Sessions.getCurrent()
					.getAttribute(PhieuNhapHangDSViewModel.SELECTED_PNH_ID);
			if (selectedPhieuNhapHangId != null) {
				this.phieuChi.setIdPhieuNhapHang(selectedPhieuNhapHangId);
			}
			// initialize essential value
			this.phieuChi.setNgayLap(new Date()); // creation date is today
			this.phieuChi.setMaPhieuChi(System.currentTimeMillis() + ""); // random

			// get current signed in staff
			this.staff = this.nhanVienServiceImpl.findById(1L, NhanVien.class);
			this.phieuChi.setMaNV(1); // TEST

		} else {
			throw new NullPointerException("Service for PhieuChiAddViewModel is null");
		}
	}

	@Command
	public void luuPhieuChi() {
		if (this.phieuChiServiceImpl != null) {

			if (this.phieuChiServiceImpl.save(this.phieuChi)) {
				Messagebox.show("Thông báo", "Lưu thành công", Messagebox.OK, Messagebox.INFORMATION,
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

	public NhanVien getStaff() {
		return staff;
	}

	public void setStaff(NhanVien staff) {
		this.staff = staff;
	}
	
	
}
