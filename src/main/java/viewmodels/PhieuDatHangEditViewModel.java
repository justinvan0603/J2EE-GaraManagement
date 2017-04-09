package viewmodels;

import java.util.Iterator;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuDatHang;
import business.entities.HieuXe;
import business.entities.NhaCungCap;
import business.entities.NhomNhaCungCap;
import business.entities.PhieuDatHang;
import business.entities.PhuTung;
import business.service.ChiTietPhieuDatHangServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.NhaCungCapServiceImpl;
import business.service.NhanVienServiceImpl;
import business.service.NhomNhaCungCapServiceImpl;
import business.service.PhieuDatHangServiceImpl;
import business.service.PhuTungServiceImpl;

public class PhieuDatHangEditViewModel {

	// services for interacting with persistence layer
	private PhieuDatHangServiceImpl phieuServiceImpl;
	private ChiTietPhieuDatHangServiceImpl ctPhieuServiceImpl;
	private NhanVienServiceImpl nhanVienServiceImpl;
	private HieuXeServiceImpl hieuXeServiceImpl;
	private PhuTungServiceImpl phuTungServiceImpl;
	private NhomNhaCungCapServiceImpl nhomNhaCungCapServiceImpl;
	private NhaCungCapServiceImpl nhaCungCapServiceImpl;

	// to load on view
	private List<HieuXe> listOfHieuXes;
	private List<PhuTung> listOfPhuTungs;
	private List<NhaCungCap> listOfNhaCungCaps;
	private List<NhomNhaCungCap> listOfNhomNCCs;

	// entities to be saved
	private PhieuDatHang phieu;
	private List<CT_PhieuDatHang> setOfCT_Phieus; // use Set
	private PhuTung selectedPhuTung;
	private NhaCungCap selectedNCC;
	private NhomNhaCungCap selectedNhomNCC;
	private Double thanhTien;
	private Double tongTien;

	@Init
	public void init() {
		long id = ((Long) Sessions.getCurrent().getAttribute(PhieuDatHangDSViewModel.SELECTED_PDH_ID)).longValue();
		this.phieuServiceImpl = (PhieuDatHangServiceImpl) SpringUtil.getBean("phieudathang_service");
		this.ctPhieuServiceImpl = (ChiTietPhieuDatHangServiceImpl) SpringUtil.getBean("ct_phieudathang_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.nhomNhaCungCapServiceImpl = (NhomNhaCungCapServiceImpl) SpringUtil.getBean("nhomncc_service");
		this.nhaCungCapServiceImpl = (NhaCungCapServiceImpl) SpringUtil.getBean("nhacungcap_service");
		// make sure services are valid
		if (this.phieuServiceImpl != null && this.ctPhieuServiceImpl != null && this.nhanVienServiceImpl != null) {
			this.phieu = phieuServiceImpl.findById(id, PhieuDatHang.class);
			this.listOfHieuXes = this.hieuXeServiceImpl.getAll(HieuXe.class);
			this.listOfNhomNCCs = this.nhomNhaCungCapServiceImpl.getAll(NhomNhaCungCap.class);
			this.setOfCT_Phieus = this.ctPhieuServiceImpl.getAllByPhieuDatHangId(id);
			// su dung setter be bind cac selected item thi ben zk view moi
			// access duoc
			this.setSelectedNCC(this.nhaCungCapServiceImpl.findById(phieu.getMaNCC(), NhaCungCap.class));
			this.setSelectedNhomNCC(this.nhomNhaCungCapServiceImpl.find(selectedNCC.getNhomNCC()));
			// list nha cung cap phai co du lieu thi moi binding duoc selected
			// item
			this.listOfNhaCungCaps = this.nhaCungCapServiceImpl.find(null, null, null,
					this.selectedNhomNCC.getTenNhom());
			// Messagebox.show(this.selectedNCC.getTenNCC() +
			// this.selectedNhomNCC.getTenNhom());
		} else {
			if (this.phieuServiceImpl == null) {
				throw new NullPointerException("Service 'PhieuDatHang' is null");
			}
			if (this.ctPhieuServiceImpl == null) {
				throw new NullPointerException("Service 'CT_PDH' is null");
			}
			if (this.nhanVienServiceImpl == null) {
				throw new NullPointerException("Service 'NhanVien' is null");
			}
		}
	}

	@Command
	@NotifyChange({ "setOfCT_Phieus", "tongTien" })
	public void themChiTiet(@BindingParam("id_pt") long mapt, @BindingParam("sl") int sl,
			@BindingParam("dongia") double dongia, @BindingParam("thanhtien") double thanhtien) {
		PhuTung pt = this.phuTungServiceImpl.findById(mapt, PhuTung.class);
		CT_PhieuDatHang ct_Phieu = new CT_PhieuDatHang();
		ct_Phieu.setIdPhuTung(mapt);
		ct_Phieu.setSoLuong(sl);
		ct_Phieu.setDonGia(dongia);
		ct_Phieu.setThanhTien(thanhtien);
		ct_Phieu.setMaPT(pt.getMaPhuTung());
		ct_Phieu.setTenPT(pt.getTenPhuTung());
		if (this.setOfCT_Phieus.add(ct_Phieu)) {
			this.tongTien += thanhTien;
		}
	}

	@Command
	@NotifyChange({ "setOfCT_Phieus", "tongTien" })
	public void xoaChiTiet(@BindingParam("id_pt") long idpt) {
		for (Iterator<CT_PhieuDatHang> iterator = this.setOfCT_Phieus.iterator(); iterator.hasNext();) {
			CT_PhieuDatHang ct_Phieu = iterator.next();
			if (ct_Phieu.getIdPhuTung() == idpt) {
				iterator.remove();
				tongTien -= ct_Phieu.getThanhTien();
			}
		}
	}

	@Command
	@NotifyChange({ "listOfPhuTungs", "selectedPhuTung" })
	public void onComboboxHieuXeChange(@BindingParam("ma_hieuxe") String maHieuXe) {
		this.listOfPhuTungs = this.phuTungServiceImpl.find(null, null, maHieuXe);
		if (!listOfPhuTungs.isEmpty()) {
			this.selectedPhuTung = this.listOfPhuTungs.get(0);
		} else {
			selectedPhuTung = null;
		}
	}

	@Command
	@NotifyChange("selectedPhuTung")
	public void onComboboxPhuTungChange(@BindingParam("id_pt") long id) {

		this.selectedPhuTung = this.phuTungServiceImpl.findById(id, PhuTung.class);
	}

	@Command
	@NotifyChange("thanhTien")
	public void onSlandDongiaChange(@BindingParam("sl") long sl, @BindingParam("dongia") double dongia) {
		this.thanhTien = sl * dongia;
	}

	@Command
	@NotifyChange({ "listOfNhaCungCaps", "selectedNCC" })
	public void onComboboxNhomNCCChange(@BindingParam("tennhom") String tennhom) {
		this.listOfNhaCungCaps = this.nhaCungCapServiceImpl.find(null, null, null, tennhom);
		if (!listOfNhaCungCaps.isEmpty()) {
			this.selectedNCC = this.listOfNhaCungCaps.get(0);
		}
	}

	@Command
	public void luuPhieu() {
		this.phieu.setMaNCC(selectedNCC.getMaNCC());
		this.phieu.setTongTien(tongTien);
		if (this.phieuServiceImpl.save(this.phieu)) {
			for (Iterator<CT_PhieuDatHang> iterator = this.setOfCT_Phieus.iterator(); iterator.hasNext();) {
				CT_PhieuDatHang ct_Phieu = iterator.next();
				ct_Phieu.setIdPhieuDatHang(this.phieu.getId_PhieuDatHang());
				// start to save detail
				if (!this.ctPhieuServiceImpl.save(ct_Phieu)) {
					Messagebox.show("Lưu lỗi", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
				}
			}
			Messagebox.show("Lưu thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
			Executions.sendRedirect("./PhieuDatHang_DS.zul");
		} else {
			Messagebox.show("Lưu lỗi", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
		}
	}

	public List<HieuXe> getListOfHieuXes() {
		return listOfHieuXes;
	}

	public void setListOfHieuXes(List<HieuXe> listOfHieuXes) {
		this.listOfHieuXes = listOfHieuXes;
	}

	public List<PhuTung> getListOfPhuTungs() {
		return listOfPhuTungs;
	}

	public void setListOfPhuTungs(List<PhuTung> listOfPhuTungs) {
		this.listOfPhuTungs = listOfPhuTungs;
	}

	public List<NhaCungCap> getListOfNhaCungCaps() {
		return listOfNhaCungCaps;
	}

	public void setListOfNhaCungCaps(List<NhaCungCap> listOfNhaCungCaps) {
		this.listOfNhaCungCaps = listOfNhaCungCaps;
	}

	public List<NhomNhaCungCap> getListOfNhomNCCs() {
		return listOfNhomNCCs;
	}

	public void setListOfNhomNCCs(List<NhomNhaCungCap> listOfNhomNCCs) {
		this.listOfNhomNCCs = listOfNhomNCCs;
	}

	public List<CT_PhieuDatHang> getSetOfCT_Phieus() {
		return setOfCT_Phieus;
	}

	public void setSetOfCT_Phieus(List<CT_PhieuDatHang> setOfCT_Phieus) {
		this.setOfCT_Phieus = setOfCT_Phieus;
	}

	public PhieuDatHang getPhieu() {
		return phieu;
	}

	public void setPhieu(PhieuDatHang newPhieu) {
		this.phieu = newPhieu;
	}

	public PhuTung getSelectedPhuTung() {
		return selectedPhuTung;
	}

	public void setSelectedPhuTung(PhuTung selectedPhuTung) {
		this.selectedPhuTung = selectedPhuTung;
	}

	public NhaCungCap getSelectedNCC() {
		return selectedNCC;
	}

	public void setSelectedNCC(NhaCungCap selectedNCC) {
		this.selectedNCC = selectedNCC;
	}

	public Double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	public NhomNhaCungCap getSelectedNhomNCC() {
		return selectedNhomNCC;
	}

	public void setSelectedNhomNCC(NhomNhaCungCap selectedNhomNCC) {
		this.selectedNhomNCC = selectedNhomNCC;
	}

}
