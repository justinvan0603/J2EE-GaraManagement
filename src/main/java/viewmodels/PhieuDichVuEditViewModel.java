package viewmodels;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuDichVu;
import business.entities.PhieuDichVu;
import business.entities.PhuTung;

import business.service.ChiTietPhieuDichVuServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhuTungServiceImpl;


public class PhieuDichVuEditViewModel {
	@WireVariable
	private PhieuDichVuServiceImpl phieuDichVuServiceImpl;
	private List<PhuTung> listPhuTung;
	private PhuTung selectedPhuTung;
	private PhieuDichVu phieuDichVu;
	private double DonGia;
	private double thanhTien;
	private PhuTungServiceImpl phuTungServiceImpl;
	private ChiTietPhieuDichVuServiceImpl chiTietPhieuDichVuServiceImpl;
	private Set<CT_PhieuDichVu> setofChiTietPhieuDV;
	public Set<CT_PhieuDichVu> getSetofChiTietPhieuDV() {
		return setofChiTietPhieuDV;
	}
	public void setSetofChiTietPhieuDV(Set<CT_PhieuDichVu> setofChiTietPhieuDV) {
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
		Long id = (Long) Sessions.getCurrent().getAttribute(PhieuDichVuDSViewModel.PDV_ID);
		//long id = 1;
		//this.currentPhieuDichVu = new PhieuDichVu();
		this.phieuDichVuServiceImpl = (PhieuDichVuServiceImpl) SpringUtil.getBean("phieudichvu_service");
		this.chiTietPhieuDichVuServiceImpl = (ChiTietPhieuDichVuServiceImpl) SpringUtil.getBean("chitietphieudichvu_service");
		//this.setofChiTietPhieuDV = new HashSet<CT_PhieuDichVu>();
		//this.thoServiceImpl = (ThoServiceImpl) SpringUtil.getBean("tho_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.listPhuTung = this.phuTungServiceImpl.find(null, null, "Ford");
		//this.listTho = this.thoServiceImpl.getAll(Tho.class);
		//this.phieuDichVu = new PhieuDichVu();
		//this.phieuDichVu.setNgayLap(new Date());
		this.phieuDichVu = this.phieuDichVuServiceImpl.findById(id, PhieuDichVu.class);
		//this.selectedPhuTung = new PhuTung();
		this.setThanhTien(0);
//		if(this.listPhuTung != null)
		this.selectedPhuTung = this.listPhuTung.get(0);
		//this.selectedTho = this.listTho.get(0);
		this.thanhTien = this.selectedPhuTung.getDonGiaXuat();
		//List<CT_PhieuDichVu> list = this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(id);
		this.setofChiTietPhieuDV = new HashSet<CT_PhieuDichVu>( this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(id));

		
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
		//Messagebox.show(maphutung.toString(), "Lỗi", Messagebox.OK, Messagebox.ERROR);
		if(this.chiTietPhieuDichVuServiceImpl.delete(machitiet, CT_PhieuDichVu.class))
		{
			CT_PhieuDichVu ctPhieu = null;
			for (Iterator<CT_PhieuDichVu> iterator = this.setofChiTietPhieuDV.iterator(); iterator.hasNext();) {
				ctPhieu= iterator.next();
				if (ctPhieu.getId() == machitiet) {
					this.phieuDichVu.setTongTien(this.phieuDichVu.getTongTien() - ctPhieu.getThanhTien());
					this.phieuDichVu.setSoTienConLai(this.phieuDichVu.getSoTienConLai() - ctPhieu.getThanhTien());
					//this.phieuDichVuServiceImpl.update(this.phieuDichVu.getIdPhieuDichVu(), this.phieuDichVu);
					//iterator.remove();
					PhuTung pt = this.phuTungServiceImpl.findById(ctPhieu.getMaPhuTung(), PhuTung.class);
					pt.setSoLuongTon(pt.getSoLuongTon() + ctPhieu.getSoLuong());
					this.phuTungServiceImpl.update(pt.getId(),pt);
					break;
				}
				
			}
			this.listPhuTung.remove(ctPhieu);
			
			
			Messagebox.show("Xóa thành công!", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
		}
		else
		{
			Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}
	@Command
	@NotifyChange("setofChiTietPhieuDV")
	public void themChiTiet(@BindingParam("soluong") int soLuong) {
		CT_PhieuDichVu ctPhieu = new CT_PhieuDichVu();
		if(soLuong <= 0)
		{
			Messagebox.show("Số lượng không hợp lệ!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if (soLuong <= this.selectedPhuTung.getSoLuongTon()) {
			
		ctPhieu.setThanhTien(soLuong * this.selectedPhuTung.getDonGiaXuat());
		ctPhieu.setDonGia(this.selectedPhuTung.getDonGiaXuat());
		ctPhieu.setSoLuong(soLuong);
		ctPhieu.setMaPT(this.selectedPhuTung.getMaPhuTung());
		ctPhieu.setTenPT(this.selectedPhuTung.getTenPhuTung());
		ctPhieu.setHanBaoHanh(this.selectedPhuTung.getHanBaoHanh());
		ctPhieu.setMaPhuTung(this.selectedPhuTung.getId());
		ctPhieu.setIdPhieuDichVu(this.phieuDichVu.getIdPhieuDichVu());
		this.phieuDichVu.setTongTien(this.phieuDichVu.getTongTien() - ctPhieu.getThanhTien());
		this.phieuDichVu.setSoTienConLai(this.phieuDichVu.getSoTienConLai() - ctPhieu.getThanhTien());
		this.setofChiTietPhieuDV.clear();
		
		this.setofChiTietPhieuDV = new HashSet<CT_PhieuDichVu>( this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(1));
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
		this.phieuDichVu.setMaPhieuDichVu(mapdv);
		if(this.phieuDichVuServiceImpl.update(this.phieuDichVu.getIdPhieuDichVu(), this.phieuDichVu))
		{
			Messagebox.show("Cập nhật thành công!", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
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
