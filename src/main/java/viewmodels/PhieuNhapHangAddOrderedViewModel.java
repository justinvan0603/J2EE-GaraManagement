package viewmodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuDatHang;
import business.entities.CT_PhieuNhapHang;
import business.entities.HieuXe;
import business.entities.NhaCungCap;
import business.entities.NhanVien;
import business.entities.NhomNhaCungCap;
import business.entities.PhieuDatHang;
import business.entities.PhieuNhapHang;
import business.entities.PhuTung;
import business.service.ChiTietPhieuDatHangServiceImpl;
import business.service.ChiTietPhieuNhapHangServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.NhaCungCapServiceImpl;
import business.service.NhanVienServiceImpl;
import business.service.NhomNhaCungCapServiceImpl;
import business.service.PhieuDatHangServiceImpl;
import business.service.PhieuNhapHangServiceImpl;
import business.service.PhuTungServiceImpl;

public class PhieuNhapHangAddOrderedViewModel {

	// services for interacting with persistence layer
	private PhieuDatHangServiceImpl phieuDatHangServiceImpl;
	private PhieuNhapHangServiceImpl phieuServiceImpl;
	private ChiTietPhieuDatHangServiceImpl ctPhieuDatHangServiceImpl;
	private ChiTietPhieuNhapHangServiceImpl ctPhieuServiceImpl;
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
	private PhieuDatHang phieuDatHang;
	private PhieuNhapHang phieu;
	private List<CT_PhieuDatHang> setOfCT_PhieuDatHangs; // use Set
	private Set<CT_PhieuNhapHang> setOfCT_Phieus; // use Set
	private PhuTung selectedPhuTung;
	private NhaCungCap selectedNCC;
	private NhomNhaCungCap selectedNhomNCC;
	private Double thanhTien;
	private Double tongTien;
	private long maNV = 1;

	@Init
	public void init() {
		this.phieuDatHangServiceImpl = (PhieuDatHangServiceImpl) SpringUtil.getBean("phieudathang_service");
		this.phieuServiceImpl = (PhieuNhapHangServiceImpl) SpringUtil.getBean("phieunhaphang_service");
		this.ctPhieuDatHangServiceImpl = (ChiTietPhieuDatHangServiceImpl) SpringUtil.getBean("ct_phieudathang_service");
		this.ctPhieuServiceImpl = (ChiTietPhieuNhapHangServiceImpl) SpringUtil.getBean("ct_phieunhaphang_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.nhomNhaCungCapServiceImpl = (NhomNhaCungCapServiceImpl) SpringUtil.getBean("nhomncc_service");
		this.nhaCungCapServiceImpl = (NhaCungCapServiceImpl) SpringUtil.getBean("nhacungcap_service");
		// make sure services are valid
		try {
			long idPhieuDat = 15;//((Long) Sessions.getCurrent().getAttribute(PhieuDatHangDSViewModel.SELECTED_PDH_ID)).longValue();
			this.setPhieuDatHang(this.phieuDatHangServiceImpl.findById(idPhieuDat, PhieuDatHang.class));
			this.phieu = new PhieuNhapHang();
			this.phieu.setIdPhieuDatHang(this.phieuDatHang.getId_PhieuDatHang());
			this.phieu.setTongTien(this.phieuDatHang.getTongTien());
			this.phieu.setMaNhaCungCap(this.phieuDatHang.getMaNCC());
			Date day = new Date();
			this.phieu.setNgayLap(day);
			this.phieu.setMaNV(maNV);
			this.phieu.setNhanVien(this.nhanVienServiceImpl.findById(maNV, NhanVien.class));
			this.setTongTien(this.phieuDatHang.getTongTien());
			
			this.setOfCT_PhieuDatHangs = new ArrayList<CT_PhieuDatHang>();
			this.setOfCT_Phieus = new HashSet<CT_PhieuNhapHang>();
			this.setSetOfCT_PhieuDatHangs(this.ctPhieuDatHangServiceImpl.getAllByPhieuDatHangId(idPhieuDat));
			
			for (Iterator<CT_PhieuDatHang> iterator = this.setOfCT_PhieuDatHangs.iterator(); iterator.hasNext();) {
				CT_PhieuDatHang ct_phieuDatHang = iterator.next();
				CT_PhieuNhapHang ct_phieu = new CT_PhieuNhapHang();
				ct_phieu.setIdPhuTung(ct_phieuDatHang.getIdPhuTung());
				ct_phieu.setSoLuong(ct_phieuDatHang.getSoLuong());
				ct_phieu.setDonGia(ct_phieuDatHang.getDonGia());
				ct_phieu.setThanhTien(ct_phieuDatHang.getThanhTien());
				PhuTung pt = this.phuTungServiceImpl.findById(ct_phieuDatHang.getIdPhuTung(), PhuTung.class);
				ct_phieu.setMaPT(pt.getMaPhuTung());
				ct_phieu.setTenPT(pt.getTenPhuTung());
				setOfCT_Phieus.add(ct_phieu);
			}
			
			this.listOfHieuXes = this.hieuXeServiceImpl.getAll(HieuXe.class);
			this.listOfNhomNCCs = this.nhomNhaCungCapServiceImpl.getAll(NhomNhaCungCap.class);
			this.setSelectedNCC(this.nhaCungCapServiceImpl.findById(phieu.getMaNhaCungCap(), NhaCungCap.class));
			this.setSelectedNhomNCC(this.nhomNhaCungCapServiceImpl.find(selectedNCC.getNhomNCC()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange({ "setOfCT_Phieus", "tongTien" })
	public void themChiTiet(@BindingParam("id_pt") long mapt, @BindingParam("sl") int sl,
			@BindingParam("dongia") double dongia, @BindingParam("thanhtien") double thanhtien) {
		PhuTung pt = this.phuTungServiceImpl.findById(mapt, PhuTung.class);
		CT_PhieuNhapHang ct_Phieu = new CT_PhieuNhapHang();
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
		for (Iterator<CT_PhieuNhapHang> iterator = this.setOfCT_Phieus.iterator(); iterator.hasNext();) {
			CT_PhieuNhapHang ct_Phieu = iterator.next();
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
	public void luuPhieu() {
		if (this.setOfCT_Phieus.size() > 0) {
			this.phieu.setTongTien(tongTien);
			if (this.phieuServiceImpl.save(this.phieu)) {
				for (Iterator<CT_PhieuNhapHang> iterator = this.setOfCT_Phieus.iterator(); iterator.hasNext();) {
					try {
						CT_PhieuNhapHang ct_Phieu = iterator.next();
						ct_Phieu.setIdPhieuNhapHang(this.phieu.getIdPhieuNhapHang());
						// start to save detail
						if (!this.ctPhieuServiceImpl.save(ct_Phieu)) {
							Messagebox.show("Lưu lỗi", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
				Messagebox.show("Lưu thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
				Executions.sendRedirect("./PhieuNhapHang_DS.zul");
			} else {
				Messagebox.show("Lưu lỗi", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
			}
		} else {
			Messagebox.show("Mỗi phiếu phải có ít nhất một chi tiết phiếu.", "Thông báo", Messagebox.RETRY, Messagebox.ERROR);
		}
	}

	@Command
	public void thoat() {
		Executions.sendRedirect("./PhieuNhapHang_DS.zul");
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

	public PhieuDatHang getPhieuDatHang() {
		return phieuDatHang;
	}

	public void setPhieuDatHang(PhieuDatHang phieuDatHang) {
		this.phieuDatHang = phieuDatHang;
	}

	public PhieuNhapHang getPhieu() {
		return phieu;
	}

	public void setPhieu(PhieuNhapHang phieu) {
		this.phieu = phieu;
	}

	public List<CT_PhieuDatHang> getSetOfCT_PhieuDatHangs() {
		return setOfCT_PhieuDatHangs;
	}

	public void setSetOfCT_PhieuDatHangs(List<CT_PhieuDatHang> setOfCT_PhieuDatHangs) {
		this.setOfCT_PhieuDatHangs = setOfCT_PhieuDatHangs;
	}

	public Set<CT_PhieuNhapHang> getSetOfCT_Phieus() {
		return setOfCT_Phieus;
	}

	public void setSetOfCT_Phieus(Set<CT_PhieuNhapHang> setOfCT_Phieus) {
		this.setOfCT_Phieus = setOfCT_Phieus;
	}

	public NhomNhaCungCap getSelectedNhomNCC() {
		return selectedNhomNCC;
	}

	public void setSelectedNhomNCC(NhomNhaCungCap selectedNhomNCC) {
		this.selectedNhomNCC = selectedNhomNCC;
	}
	
}
