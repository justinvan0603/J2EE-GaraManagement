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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import utils.StringFormatUtil;

@Entity
@Table(name = "phieunhaphang")
public class PhieuNhapHang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuNhapHang")
	private long IdPhieuNhapHang;

	@Column(name = "MaPhieuNhapHang")
	private String MaPhieuNhapHang;

	@Column(name = "IdPhieuDatHang")
	private long IdPhieuDatHang;

	@Column(name = "NgayLap")
	private Date NgayLap;

	@Column(name = "MaNV")
	private long MaNV;

	@Column(name = "TongTien")
	private Double TongTien;

	@Column(name = "MaNhaCungCap")
	private long MaNhaCungCap;

	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	private NhanVien NhanVien;

	@ManyToOne
	@JoinColumn(name = "MaNhaCungCap", referencedColumnName = "MaNCC", insertable = false, updatable = false)
	private NhaCungCap NhaCungCap;

	@Formula("(select count(*) from phieuchi A where A.IdPhieuNhapHang = IdPhieuNhapHang )")
	private int numberOfPhieuChi;

	@ManyToOne
	@JoinColumn(name = "IdPhieuDatHang", referencedColumnName = "Id_PhieuDatHang", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private PhieuDatHang PhieuDatHang;

	public long getIdPhieuNhapHang() {
		return IdPhieuNhapHang;
	}

	public void setIdPhieuNhapHang(long idPhieuNhapHang) {
		IdPhieuNhapHang = idPhieuNhapHang;
	}

	public String getMaPhieuNhapHang() {
		return MaPhieuNhapHang;
	}

	public void setMaPhieuNhapHang(String maPhieuNhapHang) {
		MaPhieuNhapHang = maPhieuNhapHang;
	}

	public long getIdPhieuDatHang() {
		return IdPhieuDatHang;
	}

	public void setIdPhieuDatHang(long idPhieuDatHang) {
		IdPhieuDatHang = idPhieuDatHang;
	}

	public Date getNgayLap() {
		return NgayLap;
	}

	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
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

	public long getMaNhaCungCap() {
		return MaNhaCungCap;
	}

	public void setMaNhaCungCap(long maNhaCungCap) {
		MaNhaCungCap = maNhaCungCap;
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

	public PhieuDatHang getPhieuDatHang() {
		return PhieuDatHang;
	}

	public void setPhieuDatHang(PhieuDatHang phieuDatHang) {
		PhieuDatHang = phieuDatHang;
	}

	public String getShortDateNgayLap() {
		return StringFormatUtil.shortDateTime(NgayLap);
	}

	public int getNumberOfPhieuChi() {
		return numberOfPhieuChi;
	}

	public void setNumberOfPhieuChi(int numberOfPhieuChi) {
		this.numberOfPhieuChi = numberOfPhieuChi;
	}

	public String getTongTienFormated(){
		NumberFormat formatter = new DecimalFormat("###,###,###");  
		return formatter.format(this.TongTien);
	}
}
