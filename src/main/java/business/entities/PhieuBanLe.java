package business.entities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utils.StringFormatUtil;

@Entity
@Table(name = "phieubanle")
public class PhieuBanLe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuBanLe")
	private long IdPhieuBanLe;
	@Column(name = "MaPhieuBan")
	private String MaPhieuBan;
	@Column(name = "NgayLap")
	@Temporal(TemporalType.DATE)
	private Date NgayLap;
	@Column(name = "HanChotThanhToan")
	@Temporal(TemporalType.DATE)
	private Date HanChotThanhToan;
	@Column(name = "MaKH")
	private long MaKH;
	@Column(name = "MaNV")
	private long MaNV;
	@Column(name = "TongTien")
	private double TongTien;
	@Column(name = "SoTienConLai")
	private double SoTienConLai;
	@ManyToOne
	@JoinColumn(name = "MaKH", referencedColumnName = "MaKH", insertable = false, updatable = false)
	private Customer KhachHang;
	public Customer getKhachHang() {
		return KhachHang;
	}
	public void setKhachHang(Customer khachHang) {
		KhachHang = khachHang;
	}
	public long getIdPhieuBanLe() {
		return IdPhieuBanLe;
	}
	public void setIdPhieuBanLe(long idPhieuBanLe) {
		IdPhieuBanLe = idPhieuBanLe;
	}
	public String getMaPhieuBan() {
		return MaPhieuBan;
	}
	public void setMaPhieuBan(String maPhieuBan) {
		MaPhieuBan = maPhieuBan;
	}
	public Date getNgayLap() {
		return NgayLap;
	}
	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
	}
	public Date getHanChotThanhToan() {
		return HanChotThanhToan;
	}
	public void setHanChotThanhToan(Date hanChotThanhToan) {
		HanChotThanhToan = hanChotThanhToan;
	}
	public long getMaKH() {
		return MaKH;
	}
	public void setMaKH(long maKH) {
		MaKH = maKH;
	}
	public long getMaNV() {
		return MaNV;
	}
	public void setMaNV(long maNV) {
		MaNV = maNV;
	}
	public double getTongTien() {
		return TongTien;
	}
	public void setTongTien(double tongTien) {
		TongTien = tongTien;
	}
	public double getSoTienConLai() {
		return SoTienConLai;
	}
	public void setSoTienConLai(double soTienConLai) {
		SoTienConLai = soTienConLai;
	}
	public String getShortNgayLap() {
		return StringFormatUtil.shortDateTime(this.NgayLap);
	}
	public String getShortHanChotThanhToan() {
		return StringFormatUtil.shortDateTime(this.HanChotThanhToan);
	}
	public String getTongTienFormated(){
		NumberFormat formatter = new DecimalFormat("##,###,###");  
		return formatter.format(this.TongTien);
	}
	public String getSoTienConLaiFormated(){
		NumberFormat formatter = new DecimalFormat("##,###,###");  
		return formatter.format(this.SoTienConLai);
	}
}
