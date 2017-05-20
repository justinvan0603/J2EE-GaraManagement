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
@Table(name = "phieudichvu")
public class PhieuDichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuDichVu")
	private long IdPhieuDichVu;
	@Column(name = "MaPhieuDichVu")
	private String MaPhieuDichVu;
	@Column(name = "NgayLap")
	@Temporal(TemporalType.DATE)
	private Date NgayLap;
	@Column(name = "HanChotThanhToan")
	private Date HanChotThanhToan;
	@Column(name = "MaPhieuTiepNhan")
	private long MaPhieuTiepNhan;
	@Column(name = "TongTien")
	private double TongTien;
	@Column(name = "SoTienConLai")
	private double SoTienConLai;
	@Column(name = "MaTho")
	private long MaTho;
	@Column(name = "MaNV")
	private long MaNV;
	@Column(name = "TienCong")
	private double TienCong;
	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	private NhanVien nhanVien;
	@ManyToOne
	@JoinColumn(name = "MaTho", referencedColumnName = "MaTho", insertable = false, updatable = false)
	private Tho Tho;
	
	public double getTienCong() {
		return TienCong;
	}
	public void setTienCong(double tienCong) {
		TienCong = tienCong;
	}
	public Tho getTho() {
		return Tho;
	}
	public void setTho(Tho tho) {
		Tho = tho;
	}
	public long getIdPhieuDichVu() {
		return IdPhieuDichVu;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public void setIdPhieuDichVu(long idPhieuDichVu) {
		IdPhieuDichVu = idPhieuDichVu;
	}
	public String getMaPhieuDichVu() {
		return MaPhieuDichVu;
	}
	public void setMaPhieuDichVu(String maPhieuDichVu) {
		MaPhieuDichVu = maPhieuDichVu;
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
	public long getMaPhieuTiepNhan() {
		return MaPhieuTiepNhan;
	}
	public void setMaPhieuTiepNhan(long maPhieuTiepNhan) {
		MaPhieuTiepNhan = maPhieuTiepNhan;
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
	public long getMaTho() {
		return MaTho;
	}
	public void setMaTho(long maTho) {
		MaTho = maTho;
	}
	public long getMaNV() {
		return MaNV;
	}
	public void setMaNV(long maNV) {
		MaNV = maNV;
	}
	
	public String getShortNgayLap() {
		return StringFormatUtil.shortDateTime(this.NgayLap);
	}
	public String getShortHanChotThanhToan() {
		return StringFormatUtil.shortDateTime(this.HanChotThanhToan);
	}
	public String getTienCongFormated(){
		NumberFormat formatter = new DecimalFormat("##,###,###");  
		return formatter.format(this.TienCong);
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
