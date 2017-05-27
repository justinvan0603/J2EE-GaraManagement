package viewmodels;

import java.util.Calendar;
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
import business.entities.HieuXe;
import business.entities.NhaCungCap;
import business.entities.NhanVien;
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
import utils.StringFormatUtil;
import utils.SystemParam;

public class PhieuDatHangAddViewModel {

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
	private Set<CT_PhieuDatHang> setOfCT_Phieus; // use Set
	private PhuTung selectedPhuTung;
	private NhaCungCap selectedNCC;
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

		this.phieuServiceImpl = (PhieuDatHangServiceImpl) SpringUtil.getBean("phieudathang_service");
		this.ctPhieuServiceImpl = (ChiTietPhieuDatHangServiceImpl) SpringUtil.getBean("ct_phieudathang_service");
		this.nhanVienServiceImpl = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.nhomNhaCungCapServiceImpl = (NhomNhaCungCapServiceImpl) SpringUtil.getBean("nhomncc_service");
		this.nhaCungCapServiceImpl = (NhaCungCapServiceImpl) SpringUtil.getBean("nhacungcap_service");
		this.maNV = ((Long) Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID)).longValue();
		// make sure services are valid
		if (this.phieuServiceImpl != null && this.ctPhieuServiceImpl != null && this.nhanVienServiceImpl != null) {
			this.phieu = new PhieuDatHang();
			Date day = new Date();
			this.phieu.setNgayDat(day);
			this.phieu.setMaNV(maNV);
			this.phieu.setNhanVien(this.nhanVienServiceImpl.findById(maNV, NhanVien.class));
			Calendar c = Calendar.getInstance();
			c.setTime(day);
			c.add(Calendar.DATE, Integer.valueOf(SystemParam.getValueByKey("ThoiGianGiaoHang")));
			day = c.getTime();
			this.phieu.setNgayGiao(day);
			this.tongTien = 0.0;

			this.listOfHieuXes = this.hieuXeServiceImpl.getAll(HieuXe.class);
			this.listOfNhomNCCs = this.nhomNhaCungCapServiceImpl.getAll(NhomNhaCungCap.class);

			this.setOfCT_Phieus = new HashSet<CT_PhieuDatHang>();

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
	public void themChiTiet(@BindingParam("id_pt") String ma_pt, @BindingParam("sl") int sl,
			@BindingParam("dongia") double dongia, @BindingParam("thanhtien") double thanhtien) {
		int soluongdattoida = Integer.valueOf(SystemParam.getValueByKey("SoLuongDatToiDa"));
		if (sl > soluongdattoida) {
			Messagebox.show("Số lượng đặt phải bé hơn số lượng đặt tối đa: " + String.valueOf(soluongdattoida), "Lỗi",
					Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
		long mapt;
		try {
			mapt = Long.valueOf(ma_pt);
		} catch (NumberFormatException e) {
			System.out.println("MaPT is " + e.getMessage());
			Messagebox.show("Bạn phải chọn phụ tùng", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
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
	@NotifyChange("selectedNCC")
	public void onComboboxNCCChange(@BindingParam("ma_ncc") long ma_ncc) {
		this.selectedNCC = this.nhaCungCapServiceImpl.findById(ma_ncc, NhaCungCap.class);
	}

	@Command
	public void luuPhieu() {
		if (this.setOfCT_Phieus.size() > 0) {
			if (selectedNCC == null){
				Messagebox.show("Bạn phải chọn nhà cung cấp", "Lỗi",
						Messagebox.OK, Messagebox.ERROR);
				return;
			}
			
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
		} else {
			Messagebox.show("Mỗi phiếu phải có ít nhất một chi tiết phiếu.", "Thông báo", Messagebox.RETRY,
					Messagebox.ERROR);
		}
	}

	@Command
	public void thoat() {
		Executions.sendRedirect("./PhieuDatHang_DS.zul");
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

	public Set<CT_PhieuDatHang> getSetOfCT_Phieus() {
		return setOfCT_Phieus;
	}

	public void setSetOfCT_Phieus(Set<CT_PhieuDatHang> setOfCT_Phieus) {
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

	public String getTongTienString() {
		return StringFormatUtil.toCurrencyString(this.tongTien);
	}

}
