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
import business.entities.PhieuNhapHang;
import business.service.NhanVienServiceImpl;
import business.service.PhieuChiServiceImpl;
import business.service.PhieuNhapHangServiceImpl;

public class PhieuChiAddViewModel {
	private PhieuChiServiceImpl phieuChiServiceImpl;
	private NhanVienServiceImpl nhanVienServiceImpl;
	private PhieuNhapHangServiceImpl phieuNhapHangServiceImpl;
	private PhieuChi phieuChi;
	private NhanVien staff;

	@Init
	public void init() {

		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.phieuChiServiceImpl = (PhieuChiServiceImpl) SpringUtil.getBean("phieuchi_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.phieuNhapHangServiceImpl = (PhieuNhapHangServiceImpl) SpringUtil.getBean("phieunhaphang_service");

		if (this.phieuChiServiceImpl != null && this.nhanVienServiceImpl != null
				&& this.phieuNhapHangServiceImpl != null) {
			// initialize view model's 'phieu chi'
			this.phieuChi = new PhieuChi();
			// get session from 'PhieuNhapHang_DS' view page if page is
			// redirected from this page
			Integer selectedPhieuNhapHangId = (Integer) Sessions.getCurrent()
					.getAttribute(PhieuNhapHangDSViewModel.SELECTED_PNH_ID);
			// make sure session value is valid
			if (selectedPhieuNhapHangId != null) {
				System.out.println("Session valid is VALID");
				PhieuNhapHang phieuNhapHang = this.phieuNhapHangServiceImpl.findById(selectedPhieuNhapHangId,
						PhieuNhapHang.class);
				// set essential values if available
				if (phieuNhapHang != null) {
					System.out.println("Phieu nhap hang valid is VALID");
					this.phieuChi.setIdPhieuNhapHang(selectedPhieuNhapHangId);
					this.phieuChi.setGiaTri(phieuNhapHang.getTongTien()); // set
																			// total
																			// of
																			// bill
					this.phieuChi.setNoiDung(
							"Phiếu chi cho phiếu nhập hàng, mã phiếu : " + phieuNhapHang.getMaPhieuNhapHang());
				}

			}
			// initialize essential value
			this.phieuChi.setNgayLap(new Date()); // creation date is today
			this.phieuChi.setMaPhieuChi(System.currentTimeMillis() + ""); // random

			// get current signed in staff
			this.phieuChi.setMaNV((Integer) Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID)); // TEST

		} else {
			throw new NullPointerException("Service for PhieuChiAddViewModel is null");
		}
	}

	@Command
	public void luuPhieuChi() {
		if (this.phieuChiServiceImpl != null) {

			if (this.phieuChiServiceImpl.save(this.phieuChi)) {
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

	public NhanVien getStaff() {
		return staff;
	}

	public void setStaff(NhanVien staff) {
		this.staff = staff;
	}

}
