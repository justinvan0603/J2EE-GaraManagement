package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import utils.StringFormatUtil;

@Entity
@Table(name = "phieudathang")
public class PhieuDatHang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_PhieuDatHang")
	private long Id_PhieuDatHang;

	@Column(name = "MaPhieuDat")
	private String MaPhieuDat;

	@Column(name = "NgayDat")
	private Date NgayDat;

	@Column(name = "NgayGiao")
	private Date NgayGiao;

	@Column(name = "MaNV")
	private long MaNV;
	
	@Column(name = "TongTien")
	private Double TongTien;
	
	@Column(name = "MaNCC")
	private long MaNCC;

	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	private NhanVien NhanVien;
	
	@ManyToOne
	@JoinColumn(name = "MaNCC", referencedColumnName = "MaNCC", insertable = false, updatable = false)
	private NhaCungCap NhaCungCap;
	
	public long getId_PhieuDatHang() {
		return Id_PhieuDatHang;
	}

	public void setId_PhieuDatHang(long id_PhieuDatHang) {
		Id_PhieuDatHang = id_PhieuDatHang;
	}

	public String getMaPhieuDat() {
		return MaPhieuDat;
	}

	public void setMaPhieuDat(String maPhieuDat) {
		MaPhieuDat = maPhieuDat;
	}

	public Date getNgayDat() {
		return NgayDat;
	}

	public void setNgayDat(Date ngayDat) {
		NgayDat = ngayDat;
	}

	public Date getNgayGiao() {
		return NgayGiao;
	}

	public void setNgayGiao(Date ngayGiao) {
		NgayGiao = ngayGiao;
	}

	public long getMaNV() {
		return MaNV;
	}

	public void setMaNV(long maNV) {
		MaNV = maNV;
	}

	public Double getTongTien() {
		return TongTien;
	}

	public void setTongTien(Double tongTien) {
		TongTien = tongTien;
	}

	public long getMaNCC() {
		return MaNCC;
	}

	public void setMaNCC(long maNCC) {
		MaNCC = maNCC;
	}

	public NhanVien getNhanVien() {
		return NhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		NhanVien = nhanVien;
	}

	public NhaCungCap getNhaCungCap() {
		return NhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		NhaCungCap = nhaCungCap;
	}
	
	public String getShortDateNgayDat()	{
		return StringFormatUtil.shortDateTime(NgayDat);
	}
	
	public String getShortDateNgayGiao()	{
		return StringFormatUtil.shortDateTime(NgayGiao);
	}
	
	public void AddTongTien(Double tien){
		this.TongTien += tien;
	}
	
	public void SubTongTien(Double tien){
		this.TongTien -= tien;
	}
}
