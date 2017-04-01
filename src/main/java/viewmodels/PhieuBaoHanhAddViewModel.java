package viewmodels;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuBaoHanh;
import business.entities.NhanVien;
import business.entities.PhieuBaoHanh;
import business.service.CTPhieuBaoHanhServiceImpl;
import business.service.NhanVienServiceImpl;
import business.service.PhieuBaoHanhServiceImpl;

/**
 * View model for page add new maintenance header
 * 
 * @author TNS
 *
 */
public class PhieuBaoHanhAddViewModel {

	// services for interacting with persistence layer
	private PhieuBaoHanhServiceImpl phieuBaoHanhServiceImpl;
	private CTPhieuBaoHanhServiceImpl ctPhieuBaoHanhDaoImpl;
	private NhanVienServiceImpl nhanVienServiceImpl;

	// entities to be saved
	private PhieuBaoHanh phieuBaoHanh;
	private Set<CT_PhieuBaoHanh> setOfCT_PhieuBaoHanhs; // use Set

	@Init
	public void init() {
		this.phieuBaoHanhServiceImpl = (PhieuBaoHanhServiceImpl) SpringUtil.getBean("phieubaohanh_service");
		this.ctPhieuBaoHanhDaoImpl = (CTPhieuBaoHanhServiceImpl) SpringUtil.getBean("ct_phieubaohanh_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		// make sure services are valid
		if (this.phieuBaoHanhServiceImpl != null && this.ctPhieuBaoHanhDaoImpl != null
				&& this.nhanVienServiceImpl != null) {
			// initialize entities
			this.phieuBaoHanh = new PhieuBaoHanh();
			// set some default values
			this.phieuBaoHanh.setNgayLap(new Date());
			this.phieuBaoHanh.setNhanVien(this.nhanVienServiceImpl.findById(1L, NhanVien.class)); // TEST
			this.phieuBaoHanh.setTinhTrang("Đang trong tình trạng bảo hành");

			// initialize list
			this.setOfCT_PhieuBaoHanhs = new HashSet<CT_PhieuBaoHanh>(); //
		} else {
			if (this.phieuBaoHanhServiceImpl == null) {
				throw new NullPointerException("Service 'Phieu Bao Hanh' is null");
			}
			if (this.ctPhieuBaoHanhDaoImpl == null) {
				throw new NullPointerException("Service 'CT Phieu Bao Hanh' is null");
			}
			if (this.nhanVienServiceImpl == null) {
				throw new NullPointerException("Service 'Nhan vien' is null");
			}
		}
	}

	@Command
	@NotifyChange("listOfCT_PhieuBaoHanhs")
	public void themChiTiet(@BindingParam("maphutung") Integer maPhuTung, @BindingParam("ngayhentra") Date ngayHenTra) {
		CT_PhieuBaoHanh ct_PhieuBaoHanh = new CT_PhieuBaoHanh();
		ct_PhieuBaoHanh.setMaPhuTung(maPhuTung);
		ct_PhieuBaoHanh.setNgayHenTra(ngayHenTra);

		this.setOfCT_PhieuBaoHanhs.add(ct_PhieuBaoHanh);
	}

	@Command
	@NotifyChange("listOfCT_PhieuBaoHanhs")
	public void xoaChiTiet(@BindingParam("maphutung") Integer maPhuTung) {
		for (Iterator<CT_PhieuBaoHanh> iterator = this.setOfCT_PhieuBaoHanhs.iterator(); iterator.hasNext();) {
			CT_PhieuBaoHanh ct_PhieuBaoHanh = iterator.next();
			if (ct_PhieuBaoHanh.getMaPhuTung().intValue() == maPhuTung.intValue()) {
				iterator.remove();
			}
		}
	}

	@Command
	public void luuPhieuBaoHanh(@BindingParam("maphieudv") Integer maPhieuDV,
			@BindingParam("maphieubanle") Integer maPhieuBanLe) {
		// luu phieu bao hanh de lay id, sau do update id nay cho cac chi tiet
		if (this.phieuBaoHanhServiceImpl.save(this.phieuBaoHanh)) {
			// Messagebox.show("Lưu thông công", "Thông báo", Messagebox.OK,
			// update id
			for (Iterator<CT_PhieuBaoHanh> iterator = this.setOfCT_PhieuBaoHanhs.iterator(); iterator.hasNext();) {
				CT_PhieuBaoHanh ct_PhieuBaoHanh = iterator.next();
				ct_PhieuBaoHanh.setMaPhieuBaoHanh(this.phieuBaoHanh.getMaPhieuBaoHanh());
				ct_PhieuBaoHanh.setMaPhieuDichVu(maPhieuDV);
				ct_PhieuBaoHanh.setMaPhieuBanLe(maPhieuBanLe);
				ct_PhieuBaoHanh.setNgayTra(new Date());
				// start to save detail
				if (!this.ctPhieuBaoHanhDaoImpl.save(ct_PhieuBaoHanh)) {
					Messagebox.show("Lưu lỗi", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
				}
			}
			Messagebox.show("Lưu thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
			Executions.sendRedirect("./PhieuBaoHanh_DS.zul");
		} else {
			Messagebox.show("Lưu lỗi", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
		}
	}

	public PhieuBaoHanh getPhieuBaoHanh() {
		return phieuBaoHanh;
	}

	public void setPhieuBaoHanh(PhieuBaoHanh phieuBaoHanh) {
		this.phieuBaoHanh = phieuBaoHanh;
	}

	public Set<CT_PhieuBaoHanh> getListOfCT_PhieuBaoHanhs() {
		return setOfCT_PhieuBaoHanhs;
	}

	public void setListOfCT_PhieuBaoHanhs(Set<CT_PhieuBaoHanh> listOfCT_PhieuBaoHanhs) {
		this.setOfCT_PhieuBaoHanhs = listOfCT_PhieuBaoHanhs;
	}

}
