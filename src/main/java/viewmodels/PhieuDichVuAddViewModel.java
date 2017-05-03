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
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuDichVu;
import business.entities.PhieuDichVu;
import business.entities.PhuTung;
import business.entities.Tho;
import business.service.ChiTietPhieuDichVuServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhieuThuServiceImpl;
import business.service.PhuTungServiceImpl;
import business.service.ThoServiceImpl;



public class PhieuDichVuAddViewModel {
	@WireVariable
	private PhieuDichVuServiceImpl phieuDichVuServiceImpl;
	@WireVariable
	private PhieuThuServiceImpl phieuThuServiceIpml;
	private List<PhuTung> listPhuTung;
	private PhuTung selectedPhuTung;
	private PhieuDichVu phieuDichVu;
	private double DonGia;
	private double thanhTien;
	private PhuTungServiceImpl phuTungServiceImpl;
	private ChiTietPhieuDichVuServiceImpl chiTietPhieuDichVuServiceImpl;
	private ThoServiceImpl thoServiceImpl;
	private List<Tho> listTho;
	private Tho selectedTho;
	private Set<CT_PhieuDichVu> setofChiTietPhieuDV;
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
	//private PhieuDichVu currentPhieuDichVu;

	
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
//	public PhieuDichVu getCurrentPhieuDichVu() {
//		return currentPhieuDichVu;
//	}
//	public void setCurrentPhieuDichVu(PhieuDichVu currentPhieuDichVu) {
//		this.currentPhieuDichVu = currentPhieuDichVu;
//	}
	public PhuTungServiceImpl getPhuTungServiceImpl() {
		return phuTungServiceImpl;
	}
	public void setPhuTungServiceImpl(PhuTungServiceImpl phuTungServiceImpl) {
		this.phuTungServiceImpl = phuTungServiceImpl;
	}
	@Init
	public void init() {
		//this.currentPhieuDichVu = new PhieuDichVu();
		this.phieuThuServiceIpml = (PhieuThuServiceImpl) SpringUtil.getBean("phieuthu_service");
		this.phieuDichVuServiceImpl = (PhieuDichVuServiceImpl) SpringUtil.getBean("phieudichvu_service");
		this.chiTietPhieuDichVuServiceImpl = (ChiTietPhieuDichVuServiceImpl) SpringUtil.getBean("chitietphieudichvu_service");
		this.setofChiTietPhieuDV = new HashSet<CT_PhieuDichVu>();
		this.thoServiceImpl = (ThoServiceImpl) SpringUtil.getBean("tho_service");
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.listPhuTung = this.phuTungServiceImpl.find(null, null, "Ford");
		this.listTho = this.thoServiceImpl.getAll(Tho.class);
		this.phieuDichVu = new PhieuDichVu();
		this.phieuDichVu.setNgayLap(new Date());
		//this.selectedPhuTung = new PhuTung();
		this.setThanhTien(0);
//		if(this.listPhuTung != null)
		this.selectedPhuTung = this.listPhuTung.get(0);
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
	@NotifyChange("setofChiTietPhieuDV")
	public void xoaChiTiet(@BindingParam("maphutung") Integer maPhuTung) {
		for (Iterator<CT_PhieuDichVu> iterator = this.setofChiTietPhieuDV.iterator(); iterator.hasNext();) {
			CT_PhieuDichVu ctPhieu = iterator.next();
			if (ctPhieu.getMaPhuTung() == maPhuTung.intValue()) {
				iterator.remove();
				for(Iterator<PhuTung> i = this.listPhuTung.iterator(); i.hasNext();)
				{
				
					PhuTung pt = i.next();
					if(pt.getId() == ctPhieu.getMaPhuTung())
					{
						pt.setSoLuongTon(pt.getSoLuongTon() + ctPhieu.getSoLuong());
						break;
					}
				}
			}
		}
	}
	@Command
	@NotifyChange("setofChiTietPhieuDV")
	public void themChiTiet(@BindingParam("soluong") int soLuong) {
		if(soLuong <= this.selectedPhuTung.getSoLuongTon())
		{
			CT_PhieuDichVu ctPhieu = new CT_PhieuDichVu();
			this.selectedPhuTung.setSoLuongTon(this.selectedPhuTung.getSoLuongTon() - soLuong);
			ctPhieu.setThanhTien(soLuong * this.selectedPhuTung.getDonGiaXuat());
			ctPhieu.setDonGia(this.selectedPhuTung.getDonGiaXuat());
			ctPhieu.setSoLuong(soLuong);
			ctPhieu.setMaPT(this.selectedPhuTung.getMaPhuTung());
			ctPhieu.setTenPT(this.selectedPhuTung.getTenPhuTung());
			ctPhieu.setHanBaoHanh(this.selectedPhuTung.getHanBaoHanh());
			this.setofChiTietPhieuDV.add(ctPhieu);
		}
		else
		{
			Messagebox.show("Số lượng nhập vượt quá số lượng tồn", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}
	@Command
	public void SavePhieuDichVu(@BindingParam("mapdv") String mapdv, @BindingParam("tiencong") double tiencong)
	{
		if (!mapdv.isEmpty() && tiencong >=0) {
			//PhieuDichVu phieudichvu = new PhieuDichVu();
			
			this.phieuDichVu.setMaPhieuDichVu(mapdv);
			this.phieuDichVu.setTienCong(tiencong);
			this.phieuDichVu.setMaTho(this.selectedTho.getId());
			this.phieuDichVu.setNgayLap(new Date());
			this.phieuDichVu.setMaNV(2);
			
			double tongchitiet = 0;
			for(CT_PhieuDichVu i : this.setofChiTietPhieuDV)
			{
				tongchitiet += i.getThanhTien();
			}
			this.phieuDichVu.setTongTien(tiencong+ tongchitiet);
			this.phieuDichVu.setSoTienConLai(tiencong+ tongchitiet);
			//phieudichvu.setMaTho(this.selectedTho.getId());
			if (this.phieuDichVuServiceImpl.save(this.phieuDichVu)) {
				for(CT_PhieuDichVu i : this.setofChiTietPhieuDV)
				{
					//CT_PhieuDichVu ctphieu = i;
					i.setIdPhieuDichVu(this.phieuDichVu.getIdPhieuDichVu());
					this.chiTietPhieuDichVuServiceImpl.save(i);
					PhuTung pt  = this.phuTungServiceImpl.findById(i.getMaPhuTung(), PhuTung.class);
					pt.setSoLuongTon(pt.getSoLuongTon() - i.getSoLuong());
					this.phuTungServiceImpl.update(pt.getId(), pt);
				}
				Messagebox.show("Lưu Thành công");
				Executions.sendRedirect("./PhieuDichVu_DS.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
			
		}
		else
		{
			Messagebox.show("Vui lòng nhập đầy đủ thông tin!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}
}
