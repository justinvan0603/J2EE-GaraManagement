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
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuDichVu;
import business.entities.Customer;
import business.entities.HieuXe;
import business.entities.PhieuDichVu;
import business.entities.PhieuTiepNhan;
import business.entities.PhuTung;
import business.entities.Tho;
import business.entities.Xe;
import business.service.ChiTietPhieuDichVuServiceImpl;
import business.service.CustomerServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhieuThuServiceImpl;
import business.service.PhieuTiepNhanServiceImpl;
import business.service.PhuTungServiceImpl;
import business.service.ThoServiceImpl;
import business.service.XeServiceImpl;
import utils.SystemParam;

public class PhieuDichVuAddViewModel {
	@WireVariable
	private PhieuDichVuServiceImpl phieuDichVuServiceImpl;
	@WireVariable
	private PhieuThuServiceImpl phieuThuServiceIpml;
	@WireVariable
	private PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl;
	@WireVariable
	private HieuXeServiceImpl hieuXeServiceImpl;
	@WireVariable
	private XeServiceImpl xeServiceImpl;

	@WireVariable
	private CustomerServiceImpl customerServiceImpl;
	private List<PhuTung> listPhuTung;
	private PhuTung selectedPhuTung;
	private Customer currentKhachHang;

	public HieuXeServiceImpl getHieuXeServiceImpl() {
		return hieuXeServiceImpl;
	}

	public void setHieuXeServiceImpl(HieuXeServiceImpl hieuXeServiceImpl) {
		this.hieuXeServiceImpl = hieuXeServiceImpl;
	}

	private PhieuDichVu phieuDichVu;
	private double DonGia;
	private double thanhTien;
	private PhuTungServiceImpl phuTungServiceImpl;
	private ChiTietPhieuDichVuServiceImpl chiTietPhieuDichVuServiceImpl;
	private ThoServiceImpl thoServiceImpl;
	private List<Tho> listTho;
	private Tho selectedTho;

	public PhieuThuServiceImpl getPhieuThuServiceIpml() {
		return phieuThuServiceIpml;
	}

	public void setPhieuThuServiceIpml(PhieuThuServiceImpl phieuThuServiceIpml) {
		this.phieuThuServiceIpml = phieuThuServiceIpml;
	}

	public PhieuTiepNhanServiceImpl getPhieuTiepNhanServiceImpl() {
		return phieuTiepNhanServiceImpl;
	}

	public void setPhieuTiepNhanServiceImpl(PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl) {
		this.phieuTiepNhanServiceImpl = phieuTiepNhanServiceImpl;
	}

	private Set<CT_PhieuDichVu> setofChiTietPhieuDV;

	public double getDonGia() {
		return DonGia;
	}

	public XeServiceImpl getXeServiceImpl() {
		return xeServiceImpl;
	}

	public void setXeServiceImpl(XeServiceImpl xeServiceImpl) {
		this.xeServiceImpl = xeServiceImpl;
	}

	public void setDonGia(double donGia) {
		DonGia = donGia;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public PhieuDichVu getPhieuDichVu() {
		return phieuDichVu;
	}

	public void setPhieuDichVu(PhieuDichVu phieuDichVu) {
		this.phieuDichVu = phieuDichVu;
	}

	public List<PhuTung> getListPhuTung() {
		return listPhuTung;
	}

	public void setListPhuTung(List<PhuTung> listPhuTung) {
		this.listPhuTung = listPhuTung;
	}

	public PhuTung getSelectedPhuTung() {
		return selectedPhuTung;
	}

	public void setSelectedPhuTung(PhuTung selectedPhuTung) {
		this.selectedPhuTung = selectedPhuTung;
	}
	// private PhieuDichVu currentPhieuDichVu;

	public List<Tho> getListTho() {
		return listTho;
	}

	public void setListTho(List<Tho> listTho) {
		this.listTho = listTho;
	}

	public ThoServiceImpl getThoServiceImpl() {
		return thoServiceImpl;
	}

	public void setThoServiceImpl(ThoServiceImpl thoServiceImpl) {
		this.thoServiceImpl = thoServiceImpl;
	}

	public Set<CT_PhieuDichVu> getSetofChiTietPhieuDV() {
		return setofChiTietPhieuDV;
	}

	public void setSetofChiTietPhieuDV(Set<CT_PhieuDichVu> setofChiTietPhieuDV) {
		this.setofChiTietPhieuDV = setofChiTietPhieuDV;
	}

	public Tho getSelectedTho() {
		return selectedTho;
	}

	public void setSelectedTho(Tho selectedTho) {
		this.selectedTho = selectedTho;
	}

	public Set<CT_PhieuDichVu> getSetChiTietPhieuDV() {
		return setofChiTietPhieuDV;
	}

	public void setSetChiTietPhieuDV(Set<CT_PhieuDichVu> setChiTietPhieuDV) {
		this.setofChiTietPhieuDV = setChiTietPhieuDV;
	}

	public ChiTietPhieuDichVuServiceImpl getChiTietPhieuDichVuServiceImpl() {
		return chiTietPhieuDichVuServiceImpl;
	}

	public void setChiTietPhieuDichVuServiceImpl(ChiTietPhieuDichVuServiceImpl chiTietPhieuDichVuServiceImpl) {
		this.chiTietPhieuDichVuServiceImpl = chiTietPhieuDichVuServiceImpl;
	}

	// public PhieuDichVu getCurrentPhieuDichVu() {
	// return currentPhieuDichVu;
	// }
	// public void setCurrentPhieuDichVu(PhieuDichVu currentPhieuDichVu) {
	// this.currentPhieuDichVu = currentPhieuDichVu;
	// }
	public PhuTungServiceImpl getPhuTungServiceImpl() {
		return phuTungServiceImpl;
	}

	public void setPhuTungServiceImpl(PhuTungServiceImpl phuTungServiceImpl) {
		this.phuTungServiceImpl = phuTungServiceImpl;
	}

	@Init
	public void init() {
		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		Integer idPhieuTiepNhan = (Integer) Sessions.getCurrent()
				.getAttribute(PhieuTiepNhanDSViewModel.SELECTED_PHIEUTIEPNHAN_ID);
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.xeServiceImpl = (XeServiceImpl) SpringUtil.getBean("xe_service");
		// this.currentPhieuDichVu = new PhieuDichVu();
		this.phieuTiepNhanServiceImpl = (PhieuTiepNhanServiceImpl) SpringUtil.getBean("phieutiepnhan_service");
		this.phieuThuServiceIpml = (PhieuThuServiceImpl) SpringUtil.getBean("phieuthu_service");
		this.phieuDichVuServiceImpl = (PhieuDichVuServiceImpl) SpringUtil.getBean("phieudichvu_service");
		this.chiTietPhieuDichVuServiceImpl = (ChiTietPhieuDichVuServiceImpl) SpringUtil
				.getBean("chitietphieudichvu_service");
		this.setofChiTietPhieuDV = new HashSet<CT_PhieuDichVu>();
		this.thoServiceImpl = (ThoServiceImpl) SpringUtil.getBean("tho_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		PhieuTiepNhan ptn = this.phieuTiepNhanServiceImpl.findById(idPhieuTiepNhan, PhieuTiepNhan.class);

		Xe xe = this.xeServiceImpl.findByLicensePlate(ptn.getLicensePlate());
		HieuXe hieuxe = this.hieuXeServiceImpl.findByIdString(xe.getHieuXeReference());
		this.currentKhachHang = this.customerServiceImpl.findById(ptn.getCustomerId(), Customer.class);
		//
		this.listPhuTung = this.phuTungServiceImpl.find(null, null, hieuxe.getMaHieuXe());
		this.listTho = this.thoServiceImpl.getAll(Tho.class);
		this.phieuDichVu = new PhieuDichVu();

		// Begin
		// get id-phieutiepanh from Session, if this value is valid
		// set for this.phieuDichVu

		if (idPhieuTiepNhan != null) {
			this.phieuDichVu.setMaPhieuTiepNhan(idPhieuTiepNhan);
		}
		// end get value from Session
		Long nhanVienID = (Long) Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID);
		this.phieuDichVu.setMaNV(nhanVienID);
		this.phieuDichVu.setNgayLap(new Date());
		Date day = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.DATE, Integer.valueOf(SystemParam.getValueByKey("HanChotPhieuDichVu")));
		day = c.getTime();
		this.phieuDichVu.setHanChotThanhToan(day);
		// this.selectedPhuTung = new PhuTung();
		this.setThanhTien(0);
		// if (this.listPhuTung != null && !this.listPhuTung.isEmpty()) {
//		if(this.listPhuTung != null  && this.listPhuTung.size() >0)
//			this.selectedPhuTung = this.listPhuTung.get(0);
		// }
		if(this.listPhuTung != null)
		{
			if(this.listPhuTung.size() >0)
				this.selectedPhuTung = this.listPhuTung.get(0);
		}
		this.selectedTho = this.listTho.get(0);
		this.thanhTien = this.selectedPhuTung.getDonGiaXuat();
	}

	public PhieuDichVuServiceImpl getPhieuDichVuServiceImpl() {
		return phieuDichVuServiceImpl;
	}

	public void setPhieuDichVuServiceImpl(PhieuDichVuServiceImpl phieuDichVuServiceImpl) {
		this.phieuDichVuServiceImpl = phieuDichVuServiceImpl;
	}

	@Command
	@NotifyChange("thanhTien")
	public void ontxtSoLuongChanging(@BindingParam("soluong") Integer soluong) {
		// this.ThanhTien = 0;
		try
		{
		this.setThanhTien((double) (soluong.intValue() * this.selectedPhuTung.getDonGiaXuat()));
		}
		catch(Exception ex)
		{
			Messagebox.show("Số lượng không hợp lệ!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
	}

	@Command
	@NotifyChange({ "thanhTien", "selectedPhuTung" })
	public void onComboboxPhuTungChange(@BindingParam("soluong") Integer soluong,
			@BindingParam("selectedPT") PhuTung item) {
		// Messagebox.show(this.selectedPhuTung.getDonGiaXuat().toString(),
		// "asd", Button.ABORT, null);
		// if(soluong.toString() != null && !soluong.toString().isEmpty())
		this.selectedPhuTung = item;
		this.thanhTien = 0;
		this.thanhTien = (double) (this.selectedPhuTung.getDonGiaXuat() * soluong.intValue());

		// this.listOfPhuTungs = this.phuTungServiceImpl.find(null, null,
		// maHieuXe);
		// this.selectedPhutung = this.listOfPhuTungs.get(0);

	}

	@Command
	@NotifyChange("setofChiTietPhieuDV")
	public void xoaChiTiet(@BindingParam("maphutung") Integer maPhuTung) {
		for (Iterator<CT_PhieuDichVu> iterator = this.setofChiTietPhieuDV.iterator(); iterator.hasNext();) {
			CT_PhieuDichVu ctPhieu = iterator.next();
			if (ctPhieu.getMaPhuTung() == maPhuTung.intValue()) {
				iterator.remove();
				for (Iterator<PhuTung> i = this.listPhuTung.iterator(); i.hasNext();) {

					PhuTung pt = i.next();
					if (pt.getId() == ctPhieu.getMaPhuTung()) {
						pt.setSoLuongTon(pt.getSoLuongTon() + ctPhieu.getSoLuong());
						break;
					}
				}
			}
		}
	}

	@Command
	@NotifyChange("setofChiTietPhieuDV")
	public void themChiTiet(@BindingParam("soluong") String soLuong) {
		if(this.selectedPhuTung == null)
		{
			Messagebox.show("Vui lòng chọn phụ tùng!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if(soLuong.isEmpty() || soLuong == null)
		{
			Messagebox.show("Số lượng không hợp lệ!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if (Integer.parseInt(soLuong) <= 0) {
			Messagebox.show("Số lượng không hợp lệ!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if (Integer.parseInt(soLuong) <= this.selectedPhuTung.getSoLuongTon()) {
			CT_PhieuDichVu ctPhieu = new CT_PhieuDichVu();
			this.selectedPhuTung.setSoLuongTon(this.selectedPhuTung.getSoLuongTon() - Integer.parseInt(soLuong));
			ctPhieu.setThanhTien(Integer.parseInt(soLuong) * this.selectedPhuTung.getDonGiaXuat());
			ctPhieu.setMaPhuTung(this.selectedPhuTung.getId());
			ctPhieu.setDonGia(this.selectedPhuTung.getDonGiaXuat());
			ctPhieu.setSoLuong(Integer.parseInt(soLuong));
			ctPhieu.setMaPT(this.selectedPhuTung.getMaPhuTung());
			ctPhieu.setTenPT(this.selectedPhuTung.getTenPhuTung());
			ctPhieu.setHanBaoHanh(this.selectedPhuTung.getHanBaoHanh());
			this.setofChiTietPhieuDV.add(ctPhieu);
		} else {
			Messagebox.show("Số lượng nhập vượt quá số lượng tồn", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Command
	public void SavePhieuDichVu(@BindingParam("mapdv") String mapdv, @BindingParam("tiencong") double tiencong) {
		if (!mapdv.isEmpty() && tiencong >= 0 && this.setofChiTietPhieuDV.size() >0) {
			// PhieuDichVu phieudichvu = new PhieuDichVu();

			this.phieuDichVu.setMaPhieuDichVu(mapdv);
			this.phieuDichVu.setTienCong(tiencong);
			this.phieuDichVu.setMaTho(this.selectedTho.getId());
			this.phieuDichVu.setNgayLap(new Date());
			// this.phieuDichVu.setMaNV(2); // TEST

			double tongchitiet = 0;
			for (CT_PhieuDichVu i : this.setofChiTietPhieuDV) {
				tongchitiet += i.getThanhTien();
			}
			this.phieuDichVu.setTongTien(tiencong + tongchitiet);
			this.phieuDichVu.setSoTienConLai(tiencong + tongchitiet);
			// phieudichvu.setMaTho(this.selectedTho.getId());
			if (this.phieuDichVuServiceImpl.save(this.phieuDichVu)) {
				for (CT_PhieuDichVu i : this.setofChiTietPhieuDV) {
					// CT_PhieuDichVu ctphieu = i;
					i.setIdPhieuDichVu(this.phieuDichVu.getIdPhieuDichVu());
					this.chiTietPhieuDichVuServiceImpl.save(i);
					PhuTung pt = this.phuTungServiceImpl.findById(i.getMaPhuTung(), PhuTung.class);
					// Messagebox.show(String.valueOf(pt.getSoLuongTon()));
					pt.setSoLuongTon(pt.getSoLuongTon() - i.getSoLuong());
					this.phuTungServiceImpl.update(pt.getId(), pt);
				}
				this.currentKhachHang.setSoTienNo(this.phieuDichVu.getTongTien());
				this.customerServiceImpl.update(this.currentKhachHang.getMaKH(), this.currentKhachHang);
				Messagebox.show("Lưu Thành công");
				Executions.sendRedirect("./PhieuDichVu_DS.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}

		} else {
			Messagebox.show("Vui lòng nhập đầy đủ thông tin!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}
}
