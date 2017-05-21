package viewmodels;

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
import utils.StringFormatUtil;
import utils.SystemParam;

public class PhieuNhapHangAddViewModel {

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
	private Set<CT_PhieuNhapHang> setOfCT_Phieus; // use Set
	private PhuTung selectedPhuTung;
	private NhaCungCap selectedNCC;
	private NhomNhaCungCap selectedNhomNCC;
	private Double thanhTien;
	private Double tongTien;
	private long maNV;

	@Init
	public void init() {

		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.phieuServiceImpl = (PhieuNhapHangServiceImpl) SpringUtil.getBean("phieunhaphang_service");
		this.ctPhieuServiceImpl = (ChiTietPhieuNhapHangServiceImpl) SpringUtil.getBean("ct_phieunhaphang_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.nhomNhaCungCapServiceImpl = (NhomNhaCungCapServiceImpl) SpringUtil.getBean("nhomncc_service");
		this.nhaCungCapServiceImpl = (NhaCungCapServiceImpl) SpringUtil.getBean("nhacungcap_service");
		this.maNV = ((Long) Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID)).longValue();
		// make sure services are valid
		try {
			this.phieu = new PhieuNhapHang();
			Date day = new Date();
			this.phieu.setNgayLap(day);
			this.phieu.setMaNV(maNV);
			this.phieu.setNhanVien(this.nhanVienServiceImpl.findById(maNV, NhanVien.class));
			this.setTongTien(0.0);

			this.setOfCT_Phieus = new HashSet<CT_PhieuNhapHang>();
			this.listOfHieuXes = this.hieuXeServiceImpl.getAll(HieuXe.class);
			this.listOfNhomNCCs = this.nhomNhaCungCapServiceImpl.getAll(NhomNhaCungCap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange({ "setOfCT_Phieus", "tongTien" })
	public void themChiTiet(@BindingParam("id_pt") long mapt, @BindingParam("sl") int sl,
			@BindingParam("dongia") double dongia, @BindingParam("thanhtien") double thanhtien) {
		int soluongnhaptoida = Integer.valueOf(SystemParam.getValueByKey("SoLuongNhapHangToiDa"));
		if (sl > soluongnhaptoida) {
			Messagebox.show("Số lượng nhập phải bé hơn số lượng nhập tối đa: " + String.valueOf(soluongnhaptoida),
					"Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
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
	@NotifyChange({ "listOfNhaCungCaps", "selectedNCC" })
	public void onComboboxNhomNCCChange(@BindingParam("tennhom") String tennhom) {
		this.listOfNhaCungCaps = this.nhaCungCapServiceImpl.find(null, null, null, tennhom);
		if (!listOfNhaCungCaps.isEmpty()) {
			this.selectedNCC = this.listOfNhaCungCaps.get(0);
		}
	}

	@Command
	@NotifyChange("selectedNCC")
	public void onComboboxNCCChange(@BindingParam("ma_ncc") long ma_ncc) {
		this.selectedNCC = this.nhaCungCapServiceImpl.findById(ma_ncc, NhaCungCap.class);
	}

	@Command
	public void luuPhieu() {
		if (this.setOfCT_Phieus.size() > 0) {
			this.phieu.setTongTien(tongTien);
			this.phieu.setMaNhaCungCap(selectedNCC.getMaNCC());
			if (this.phieuServiceImpl.save(this.phieu)) {
				for (Iterator<CT_PhieuNhapHang> iterator = this.setOfCT_Phieus.iterator(); iterator.hasNext();) {
					try {
						CT_PhieuNhapHang ct_Phieu = iterator.next();
						ct_Phieu.setIdPhieuNhapHang(this.phieu.getIdPhieuNhapHang());
						// start to save detail
						if (this.ctPhieuServiceImpl.save(ct_Phieu)) {
							PhuTung pt = phuTungServiceImpl.findById(ct_Phieu.getIdPhuTung(), PhuTung.class);
							double a = pt.getDonGiaXuat() * pt.getSoLuongTon()
									+ ct_Phieu.getDonGia() * ct_Phieu.getSoLuong();
							int b = pt.getSoLuongTon() + ct_Phieu.getSoLuong();
							pt.setDonGiaXuat(a / b);
							pt.setSoLuongTon(pt.getSoLuongTon() + ct_Phieu.getSoLuong());
							phuTungServiceImpl.update(pt.getId(), pt);
						} else {
							Messagebox.show("Có lỗi xảy ra khi lưu chi tiết phiếu!", "Thông báo", Messagebox.RETRY,
									Messagebox.ERROR);
							break;
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
			Messagebox.show("Mỗi phiếu phải có ít nhất một chi tiết phiếu.", "Thông báo", Messagebox.RETRY,
					Messagebox.ERROR);
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

	public String getTongTienString() {
		return StringFormatUtil.toCurrencyString(this.tongTien);
	}
}
