package viewmodels;

import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuNhapHang;
import business.entities.HieuXe;
import business.entities.NhaCungCap;
import business.entities.NhanVien;
import business.entities.NhomNhaCungCap;
import business.entities.PhieuNhapHang;
import business.entities.PhuTung;
import business.service.ChiTietPhieuNhapHangServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.NhaCungCapServiceImpl;
import business.service.NhanVienServiceImpl;
import business.service.NhomNhaCungCapServiceImpl;
import business.service.PhieuNhapHangServiceImpl;
import business.service.PhuTungServiceImpl;

public class PhieuNhapHangEditViewModel {

	// services for interacting with persistence layer
	private PhieuNhapHangServiceImpl phieuServiceImpl;
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
	private PhieuNhapHang phieu;
	private List<CT_PhieuNhapHang> setOfCT_Phieus; // use Set
	private PhuTung selectedPhuTung;
	private NhaCungCap selectedNCC;
	private NhomNhaCungCap selectedNhomNCC;
	private Double thanhTien;
	private Double tongTien;
	private long maNV = 1;
	private String maPhieuDat;

	@Init
	public void init() {
		this.phieuServiceImpl = (PhieuNhapHangServiceImpl) SpringUtil.getBean("phieunhaphang_service");
		this.ctPhieuServiceImpl = (ChiTietPhieuNhapHangServiceImpl) SpringUtil.getBean("ct_phieunhaphang_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.nhomNhaCungCapServiceImpl = (NhomNhaCungCapServiceImpl) SpringUtil.getBean("nhomncc_service");
		this.nhaCungCapServiceImpl = (NhaCungCapServiceImpl) SpringUtil.getBean("nhacungcap_service");
		// make sure services are valid
		try {
			long id = ((Long) Sessions.getCurrent().getAttribute(PhieuNhapHangDSViewModel.SELECTED_PNH_ID)).longValue();
			this.phieu = this.phieuServiceImpl.findById(id, PhieuNhapHang.class);
			this.maPhieuDat = this.phieu.getPhieuDatHang() == null ? "Không đặt trước" : this.phieu.getPhieuDatHang().getMaPhieuDat();
			this.setTongTien(this.phieu.getTongTien());
			this.phieu.setMaNV(maNV);
			this.phieu.setNhanVien(this.nhanVienServiceImpl.findById(maNV, NhanVien.class));
			this.setOfCT_Phieus = new ArrayList<CT_PhieuNhapHang>();
			this.setSetOfCT_Phieus(this.ctPhieuServiceImpl.getAllByPhieuNhapHangId(id));
			
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
		CT_PhieuNhapHang ct_Phieu = new CT_PhieuNhapHang();
		ct_Phieu.setIdPhieuNhapHang(this.phieu.getIdPhieuNhapHang());
		ct_Phieu.setIdPhuTung(mapt);
		ct_Phieu.setSoLuong(sl);
		ct_Phieu.setDonGia(dongia);
		ct_Phieu.setThanhTien(thanhtien);
		if (this.ctPhieuServiceImpl.save(ct_Phieu)) {
			this.tongTien += thanhtien;
			this.setSetOfCT_Phieus(this.ctPhieuServiceImpl.getAllByPhieuNhapHangId(this.phieu.getIdPhieuNhapHang()));
			Messagebox.show("Thêm chi tiết phiếu thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
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
			if (this.phieuServiceImpl.update(this.phieu.getIdPhieuNhapHang(),this.phieu)) {
				Messagebox.show("Cập nhật thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
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

	public PhieuNhapHang getPhieu() {
		return phieu;
	}

	public void setPhieu(PhieuNhapHang phieu) {
		this.phieu = phieu;
	}

	public NhomNhaCungCap getSelectedNhomNCC() {
		return selectedNhomNCC;
	}

	public void setSelectedNhomNCC(NhomNhaCungCap selectedNhomNCC) {
		this.selectedNhomNCC = selectedNhomNCC;
	}

	public List<CT_PhieuNhapHang> getSetOfCT_Phieus() {
		return setOfCT_Phieus;
	}

	public void setSetOfCT_Phieus(List<CT_PhieuNhapHang> setOfCT_Phieus) {
		this.setOfCT_Phieus = setOfCT_Phieus;
	}

	public String getMaPhieuDat() {
		return maPhieuDat;
	}

	public void setMaPhieuDat(String maPhieuDat) {
		this.maPhieuDat = maPhieuDat;
	}

	
	
}
