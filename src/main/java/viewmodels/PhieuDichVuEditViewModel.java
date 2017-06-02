package viewmodels;


import java.util.ArrayList;


import java.util.List;


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
import business.entities.Xe;
import business.service.ChiTietPhieuDichVuServiceImpl;
import business.service.CustomerServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhieuTiepNhanServiceImpl;
import business.service.PhuTungServiceImpl;
import business.service.XeServiceImpl;


public class PhieuDichVuEditViewModel {
	@WireVariable
	private HieuXeServiceImpl hieuXeServiceImpl;
	@WireVariable
	private XeServiceImpl xeServiceImpl;
	@WireVariable
	private PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl;
	@WireVariable
	private PhieuDichVuServiceImpl phieuDichVuServiceImpl;
	@WireVariable
	private CustomerServiceImpl customerServiceImpl;
	private List<PhuTung> listPhuTung;
	private PhuTung selectedPhuTung;
	private PhieuDichVu phieuDichVu;
	private double DonGia;
	private double thanhTien;
	private PhuTungServiceImpl phuTungServiceImpl;
	private ChiTietPhieuDichVuServiceImpl chiTietPhieuDichVuServiceImpl;
	private List<CT_PhieuDichVu> setofChiTietPhieuDV;
	private PhieuTiepNhan currentPhieuTiepNhan;
	private Customer currentKhachHang;
	public List<CT_PhieuDichVu> getSetofChiTietPhieuDV() {
		return setofChiTietPhieuDV;
	}
	public void setSetofChiTietPhieuDV(List<CT_PhieuDichVu> setofChiTietPhieuDV) {
		this.setofChiTietPhieuDV = setofChiTietPhieuDV;
	}
	public PhieuDichVuServiceImpl getPhieuDichVuServiceImpl() {
		return phieuDichVuServiceImpl;
	}
	public void setPhieuDichVuServiceImpl(PhieuDichVuServiceImpl phieuDichVuServiceImpl) {
		this.phieuDichVuServiceImpl = phieuDichVuServiceImpl;
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
	public PhieuDichVu getPhieuDichVu() {
		return phieuDichVu;
	}
	public void setPhieuDichVu(PhieuDichVu phieuDichVu) {
		this.phieuDichVu = phieuDichVu;
	}
	public double getDonGia() {
		return DonGia;
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
	public PhuTungServiceImpl getPhuTungServiceImpl() {
		return phuTungServiceImpl;
	}
	public void setPhuTungServiceImpl(PhuTungServiceImpl phuTungServiceImpl) {
		this.phuTungServiceImpl = phuTungServiceImpl;
	}
	public ChiTietPhieuDichVuServiceImpl getChiTietPhieuDichVuServiceImpl() {
		return chiTietPhieuDichVuServiceImpl;
	}
	public void setChiTietPhieuDichVuServiceImpl(ChiTietPhieuDichVuServiceImpl chiTietPhieuDichVuServiceImpl) {
		this.chiTietPhieuDichVuServiceImpl = chiTietPhieuDichVuServiceImpl;
	}
	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		Long id = (Long) Sessions.getCurrent().getAttribute(PhieuDichVuDSViewModel.PDV_ID);
		this.phieuTiepNhanServiceImpl = (PhieuTiepNhanServiceImpl) SpringUtil.getBean("phieutiepnhan_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.xeServiceImpl = (XeServiceImpl) SpringUtil.getBean("xe_service");
		
		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		this.phieuDichVuServiceImpl = (PhieuDichVuServiceImpl) SpringUtil.getBean("phieudichvu_service");
		this.phieuDichVu = this.phieuDichVuServiceImpl.findById(id, PhieuDichVu.class);
		this.chiTietPhieuDichVuServiceImpl = (ChiTietPhieuDichVuServiceImpl) SpringUtil.getBean("chitietphieudichvu_service");
		PhieuTiepNhan ptn = this.phieuTiepNhanServiceImpl.findById(this.phieuDichVu.getMaPhieuTiepNhan(), PhieuTiepNhan.class);
		this.setCurrentPhieuTiepNhan(ptn);
		this.currentKhachHang = this.customerServiceImpl.findById(ptn.getCustomerId(), Customer.class);
		Xe xe = this.xeServiceImpl.findByLicensePlate(ptn.getLicensePlate());
		HieuXe hieuxe = this.hieuXeServiceImpl.findByIdString(xe.getHieuXeReference());
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.listPhuTung = this.phuTungServiceImpl.find(null, null, hieuxe.getMaHieuXe());

		
		//this.selectedPhuTung = new PhuTung();
		this.setThanhTien(0);
		if(this.listPhuTung != null)
		{
			if(this.listPhuTung.size() >0)
				this.selectedPhuTung = this.listPhuTung.get(0);
		}
		//this.selectedTho = this.listTho.get(0);
		this.thanhTien = this.selectedPhuTung.getDonGiaXuat();
		//List<CT_PhieuDichVu> list = this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(id);
		this.setofChiTietPhieuDV = new ArrayList<CT_PhieuDichVu>( this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(id));

		
	}
	public PhieuTiepNhan getCurrentPhieuTiepNhan() {
		return currentPhieuTiepNhan;
	}
	public void setCurrentPhieuTiepNhan(PhieuTiepNhan currentPhieuTiepNhan) {
		this.currentPhieuTiepNhan = currentPhieuTiepNhan;
	}
	@NotifyChange("setofChiTietPhieuDV")
	@Command
	public void xoaChiTiet(@BindingParam("machitiet") Long machitiet)
	{
		//Messagebox.show(maphutung.toString(), "Lỗi", Messagebox.OK, Messagebox.ERROR);
		
		if(this.setofChiTietPhieuDV.size() == 1)
		{
			Messagebox.show("Không thể xóa! Phải có ít nhất 1 chi tiết trong phiếu dịch vụ!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
		CT_PhieuDichVu ctPhieu = this.chiTietPhieuDichVuServiceImpl.findById(machitiet.longValue(), CT_PhieuDichVu.class);
		//Messagebox.show(maphutung.toString(), "Lỗi", Messagebox.OK, Messagebox.ERROR);
		if(this.chiTietPhieuDichVuServiceImpl.delete(machitiet, CT_PhieuDichVu.class))
		{
			
			//for (Iterator<CT_PhieuDichVu> iterator = this.setofChiTietPhieuDV.iterator(); iterator.hasNext();) {
				//ctPhieu= iterator.next();
				//if (ctPhieu.getId() == machitiet) {
					this.phieuDichVu.setTongTien(this.phieuDichVu.getTongTien() - ctPhieu.getThanhTien());
					this.phieuDichVu.setSoTienConLai(this.phieuDichVu.getSoTienConLai() - ctPhieu.getThanhTien());
					//this.phieuDichVuServiceImpl.update(this.phieuDichVu.getIdPhieuDichVu(), this.phieuDichVu);
					//iterator.remove();
					PhuTung pt = this.phuTungServiceImpl.findById(ctPhieu.getMaPhuTung(), PhuTung.class);
					pt.setSoLuongTon(pt.getSoLuongTon() + ctPhieu.getSoLuong());
					this.phuTungServiceImpl.update(pt.getId(),pt);
					this.currentKhachHang.setSoTienNo(this.currentKhachHang.getSoTienNo() - ctPhieu.getThanhTien());
					this.customerServiceImpl.update(this.currentKhachHang.getMaKH(), this.currentKhachHang);
					this.setofChiTietPhieuDV.clear();
					this.setSetofChiTietPhieuDV(this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(this.phieuDichVu.getIdPhieuDichVu()));
					//break;
				//}
				
			
			//this.setofChiTietPhieuDV.remove(ctPhieu);
			
			
			Messagebox.show("Xóa thành công!", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
		}
		else
		{
			Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
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
		CT_PhieuDichVu ctPhieu = new CT_PhieuDichVu();
//		if(soLuong <= 0)
//		{
//			Messagebox.show("Số lượng không hợp lệ!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
//			return;
//		}
		if (Integer.parseInt(soLuong) <= this.selectedPhuTung.getSoLuongTon()) {
			
		ctPhieu.setThanhTien(Integer.parseInt(soLuong) * this.selectedPhuTung.getDonGiaXuat());
		ctPhieu.setDonGia(this.selectedPhuTung.getDonGiaXuat());
		ctPhieu.setSoLuong(Integer.parseInt(soLuong));
		ctPhieu.setMaPT(this.selectedPhuTung.getMaPhuTung());
		ctPhieu.setTenPT(this.selectedPhuTung.getTenPhuTung());
		ctPhieu.setHanBaoHanh(this.selectedPhuTung.getHanBaoHanh());
		ctPhieu.setMaPhuTung(this.selectedPhuTung.getId());
		ctPhieu.setIdPhieuDichVu(this.phieuDichVu.getIdPhieuDichVu());
		this.phieuDichVu.setTongTien(this.phieuDichVu.getTongTien() + ctPhieu.getThanhTien());
		this.phieuDichVu.setSoTienConLai(this.phieuDichVu.getSoTienConLai() + ctPhieu.getThanhTien());
		this.setofChiTietPhieuDV.clear();
		this.chiTietPhieuDichVuServiceImpl.save(ctPhieu);
		this.setofChiTietPhieuDV = new ArrayList<CT_PhieuDichVu>( this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(this.phieuDichVu.getIdPhieuDichVu()));
		this.selectedPhuTung.setSoLuongTon(this.selectedPhuTung.getSoLuongTon() - Integer.parseInt(soLuong));
		this.currentKhachHang.setSoTienNo(this.currentKhachHang.getSoTienNo() + ctPhieu.getThanhTien());
		this.phuTungServiceImpl.update(this.selectedPhuTung.getId(), this.selectedPhuTung);
		this.customerServiceImpl.update(this.currentKhachHang.getMaKH(), this.currentKhachHang);
		}
		else {
			Messagebox.show("Số lượng nhập vượt quá số lượng tồn", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}
	@Command
	public void SavePhieuDichVu(@BindingParam("mapdv") String mapdv,@BindingParam("tiencong") double tiencong)
	{
		double chenhlech =   tiencong-this.phieuDichVu.getTienCong();
		this.phieuDichVu.setTienCong(this.phieuDichVu.getTienCong() + chenhlech);
		this.phieuDichVu.setTongTien(this.phieuDichVu.getTongTien() + chenhlech);
		this.phieuDichVu.setSoTienConLai(this.phieuDichVu.getSoTienConLai() + chenhlech);
		this.phieuDichVu.setMaPhieuDichVu(mapdv);
		if(this.phieuDichVuServiceImpl.update(this.phieuDichVu.getIdPhieuDichVu(), this.phieuDichVu))
		{
			Messagebox.show("Cập nhật thành công!", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
			Executions.sendRedirect("./PhieuDichVu_DS.zul");
		}
		else
		{
			Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}
	@Command
	@NotifyChange("thanhTien")
	public void ontxtSoLuongChanging(@BindingParam("soluong") Integer soluong)
	{
		//this.ThanhTien = 0;
		this.setThanhTien((double)(soluong.intValue() * this.selectedPhuTung.getDonGiaXuat()));
	}
	@Command
	@NotifyChange( {"thanhTien","selectedPhuTung" })
	public void onComboboxPhuTungChange(@BindingParam("soluong") Integer soluong,@BindingParam("selectedPT")PhuTung item) {
		//Messagebox.show(this.selectedPhuTung.getDonGiaXuat().toString(), "asd", Button.ABORT, null);
		//if(soluong.toString() != null && !soluong.toString().isEmpty())
		this.selectedPhuTung = item;
		this.thanhTien = 0;
		this.thanhTien = (double)(this.selectedPhuTung.getDonGiaXuat() * soluong.intValue());
	     
//		this.listOfPhuTungs = this.phuTungServiceImpl.find(null, null, maHieuXe);
//		this.selectedPhutung = this.listOfPhuTungs.get(0);
		
	}
	
}
