package viewmodels;



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

import business.entities.CT_PhieuBanLe;
import business.entities.Customer;
import business.entities.HieuXe;
import business.entities.PhieuBanLe;
import business.entities.PhuTung;
import business.service.ChiTietPhieuBanLeServiceImpl;
import business.service.CustomerServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.PhieuBanLeServiceImpl;
import business.service.PhuTungServiceImpl;

public class PhieuBanLeEditViewModel {
	@WireVariable
	private PhieuBanLeServiceImpl phieuBanLeServiceImpl;
	private List<PhuTung> listPhuTung;
	private List<HieuXe> listHieuXe;
	private PhuTung selectedPhuTung;
	private HieuXe selectedHieuXe;
	private List<Customer> listKhachHang;
	private Customer selectedKhachHang;
	private Set<CT_PhieuBanLe> setOfChiTietPhieuBL;
	private ChiTietPhieuBanLeServiceImpl chiTietPhieuBanLeServiceImpl;
	public Set<CT_PhieuBanLe> getSetOfChiTietPhieuBL() {
		return setOfChiTietPhieuBL;
	}
	public void setSetOfChiTietPhieuBL(Set<CT_PhieuBanLe> setOfChiTietPhieuBL) {
		this.setOfChiTietPhieuBL = setOfChiTietPhieuBL;
	}
	public ChiTietPhieuBanLeServiceImpl getChiTietPhieuBanLeServiceImpl() {
		return chiTietPhieuBanLeServiceImpl;
	}
	public void setChiTietPhieuBanLeServiceImpl(ChiTietPhieuBanLeServiceImpl chiTietPhieuBanLeServiceImpl) {
		this.chiTietPhieuBanLeServiceImpl = chiTietPhieuBanLeServiceImpl;
	}
	
	
	public PhieuBanLeServiceImpl getPhieuBanLeServiceImpl() {
		return phieuBanLeServiceImpl;
	}
	public void setPhieuBanLeServiceImpl(PhieuBanLeServiceImpl phieuBanLeServiceImpl) {
		this.phieuBanLeServiceImpl = phieuBanLeServiceImpl;
	}
	public List<PhuTung> getListPhuTung() {
		return listPhuTung;
	}
	public void setListPhuTung(List<PhuTung> listPhuTung) {
		this.listPhuTung = listPhuTung;
	}
	public List<HieuXe> getListHieuXe() {
		return listHieuXe;
	}
	public void setListHieuXe(List<HieuXe> listHieuXe) {
		this.listHieuXe = listHieuXe;
	}
	public PhuTung getSelectedPhuTung() {
		return selectedPhuTung;
	}
	public void setSelectedPhuTung(PhuTung selectedPhuTung) {
		this.selectedPhuTung = selectedPhuTung;
	}
	public HieuXe getSelectedHieuXe() {
		return selectedHieuXe;
	}
	public void setSelectedHieuXe(HieuXe selectedHieuXe) {
		this.selectedHieuXe = selectedHieuXe;
	}
	public List<Customer> getListKhachHang() {
		return listKhachHang;
	}
	public void setListKhachHang(List<Customer> listKhachHang) {
		this.listKhachHang = listKhachHang;
	}
	public Customer getSelectedKhachHang() {
		return selectedKhachHang;
	}
	public void setSelectedKhachHang(Customer selectedKhachHang) {
		this.selectedKhachHang = selectedKhachHang;
	}
	public PhieuBanLe getPhieuBanLe() {
		return phieuBanLe;
	}
	public void setPhieuBanLe(PhieuBanLe phieuBanLe) {
		this.phieuBanLe = phieuBanLe;
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
	public HieuXeServiceImpl getHieuXeServiceImpl() {
		return hieuXeServiceImpl;
	}
	public void setHieuXeServiceImpl(HieuXeServiceImpl hieuXeServiceImpl) {
		this.hieuXeServiceImpl = hieuXeServiceImpl;
	}
	public CustomerServiceImpl getCustomerServiceImpl() {
		return customerServiceImpl;
	}
	public void setCustomerServiceImpl(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}
	private PhieuBanLe phieuBanLe;
	private double DonGia;
	private double thanhTien;
	private PhuTungServiceImpl phuTungServiceImpl;
	private HieuXeServiceImpl hieuXeServiceImpl;
	private CustomerServiceImpl customerServiceImpl;
	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		long id = (Long)Sessions.getCurrent().getAttribute(PhieuBanLeDSViewModel.PBL_ID);
		this.chiTietPhieuBanLeServiceImpl =(ChiTietPhieuBanLeServiceImpl) SpringUtil.getBean("chitietphieubanle_service");
		this.phieuBanLeServiceImpl = (PhieuBanLeServiceImpl) SpringUtil.getBean("phieubanle_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		this.listHieuXe = this.hieuXeServiceImpl.getAll(HieuXe.class);
		this.phieuBanLe = this.phieuBanLeServiceImpl.findById(id, PhieuBanLe.class);
		
		//this.phieuBanLe = new PhieuBanLe();
		//this.phieuBanLe.setNgayLap(new Date());
		this.listKhachHang = this.customerServiceImpl.getAll(Customer.class);
		this.setThanhTien(0);
		this.listPhuTung = this.phuTungServiceImpl.getAll(PhuTung.class);
		this.setOfChiTietPhieuBL = new  HashSet<CT_PhieuBanLe>(this.chiTietPhieuBanLeServiceImpl.getByIdPhieuBanLe(id, CT_PhieuBanLe.class));
		for(Iterator<Customer> i = this.listKhachHang.iterator(); i.hasNext();)
		{
			Customer kh = i.next();
			if( kh.getMaKH() == this.phieuBanLe.getMaKH())
			{
				this.setSelectedKhachHang(kh);
				break;
			}
		}
		//this.selectedKhachHang = this.listKhachHang.get(0);
	}
	@Command
	@NotifyChange("setOfChiTietPhieuBL")
	public void xoaChiTiet(@BindingParam("maphutung") Integer maPhuTung) {
		CT_PhieuBanLe ctPhieu = null;
		for (Iterator<CT_PhieuBanLe> iterator = this.setOfChiTietPhieuBL.iterator(); iterator.hasNext();) {
			 ctPhieu = iterator.next();
			if (ctPhieu.getMaPhuTung() == maPhuTung.intValue()) {
				this.chiTietPhieuBanLeServiceImpl.delete(ctPhieu.getId(), CT_PhieuBanLe.class);
				this.phieuBanLe.setTongTien(this.phieuBanLe.getTongTien() - ctPhieu.getThanhTien());
				this.phieuBanLe.setSoTienConLai(this.phieuBanLe.getSoTienConLai() - ctPhieu.getThanhTien());
				//iterator.remove();
				for(Iterator<PhuTung> i = this.listPhuTung.iterator(); i.hasNext();)
				{
					PhuTung pt = i.next();
					if(pt.getId() == ctPhieu.getMaPhuTung())
					{
						pt.setSoLuongTon(pt.getSoLuongTon() + ctPhieu.getSoLuong());
						PhuTung target = this.phuTungServiceImpl.findById(ctPhieu.getMaPhuTung(), PhuTung.class);
						target.setSoLuongTon(target.getSoLuongTon() + ctPhieu.getSoLuong());
						this.phuTungServiceImpl.update(target.getId(), target);
						break;
					}
				}
					
				break;
			}
		}
		this.setOfChiTietPhieuBL.remove(ctPhieu);
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
	@Command
	@NotifyChange( {"selectedHieuXe","listPhuTung" })
	public void onComboboxHieuXeChange(@BindingParam("selectedHX")HieuXe item) {
		
		this.setSelectedHieuXe(item);
		if(this.listPhuTung != null)
			this.listPhuTung.clear();
		this.setListPhuTung(this.phuTungServiceImpl.find(null,null,this.selectedHieuXe.getMaHieuXe()));
		if(this.listPhuTung.size() >=1)
			this.selectedPhuTung = this.listPhuTung.get(0);

	     
//		this.listOfPhuTungs = this.phuTungServiceImpl.find(null, null, maHieuXe);
//		this.selectedPhutung = this.listOfPhuTungs.get(0);
		
	}
	@Command
	@NotifyChange("setOfChiTietPhieuBL")
	public void themChiTiet(@BindingParam("soluong") int soLuong) {
		if(soLuong <= this.selectedPhuTung.getSoLuongTon())
		{
			this.selectedPhuTung.setSoLuongTon(this.selectedPhuTung.getSoLuongTon()-soLuong);
			this.phuTungServiceImpl.update(this.selectedPhuTung.getId(), this.selectedPhuTung);
			CT_PhieuBanLe ctPhieu  = new CT_PhieuBanLe();
			ctPhieu.setIdPhieuBanLe(this.phieuBanLe.getIdPhieuBanLe());
			ctPhieu.setDonGia(this.selectedPhuTung.getDonGiaXuat());
			ctPhieu.setThoiHanBaoHanh(this.selectedPhuTung.getHanBaoHanh());
			ctPhieu.setSoLuong(soLuong);
			ctPhieu.setMaPhuTung(this.selectedPhuTung.getId());
			ctPhieu.setThanhTien(soLuong * this.selectedPhuTung.getDonGiaXuat());
			ctPhieu.setTenPT(this.selectedPhuTung.getTenPhuTung());
			ctPhieu.setMaPT(this.selectedPhuTung.getMaPhuTung());
			this.setOfChiTietPhieuBL.add(ctPhieu);
			this.phieuBanLe.setTongTien(this.phieuBanLe.getTongTien() + ctPhieu.getThanhTien());
			this.phieuBanLe.setSoTienConLai(this.phieuBanLe.getSoTienConLai() + ctPhieu.getThanhTien());
			this.chiTietPhieuBanLeServiceImpl.save(ctPhieu);
			this.setOfChiTietPhieuBL.clear();
			this.setOfChiTietPhieuBL = new HashSet<CT_PhieuBanLe>(this.chiTietPhieuBanLeServiceImpl.getByIdPhieuBanLe(this.phieuBanLe.getIdPhieuBanLe(), CT_PhieuBanLe.class));
		}
		else
		{
			Messagebox.show("Số lượng nhập vượt quá số lượng tồn", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}
	@Command
	public void SavePhieuDichVu(@BindingParam("mapbl") String mapbl)
	{
		if (!mapbl.isEmpty() && this.selectedKhachHang != null)
		{
			//Messagebox.show(this.selectedKhachHang.getHoTen());
			this.phieuBanLe.setMaPhieuBan(mapbl);
			
			//this.phieuBanLe.setNgayLap(new Date());
			//this.phieuBanLe.setMaKH(this.selectedKhachHang.getMaKH());
			//this.phieuBanLe.setMaNV(2);
			//double tongchitiet = 0;
			//for(CT_PhieuBanLe i : this.setOfChiTietPhieuBL)
			//{
			//	tongchitiet += i.getThanhTien();
			//}
			//this.phieuBanLe.setTongTien(tongchitiet);
			//this.phieuBanLe.setSoTienConLai(tongchitiet);
			//Messagebox.show(this.phieuBanLe.getMaPhieuBan());
			if(this.phieuBanLeServiceImpl.update(this.phieuBanLe.getIdPhieuBanLe(), this.phieuBanLe))
			{

				Messagebox.show("Cập nhật thành công");
				Executions.sendRedirect("./PhieuBanLe_DS.zul");
			}
			else
			{
				Messagebox.show("Đã có lỗi xảy ra!", "Lỗi", Messagebox.OK,
						Messagebox.ERROR);
			}
			
		}
		else
		{
			Messagebox.show("Vui lòng nhập đầy đủ thông tin!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}
	
}
