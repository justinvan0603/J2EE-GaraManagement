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
@Table(name = "phieuthutien")
public class PhieuThu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuThuTien")
	long IdPhieuThuTien;
	
	@Column(name = "MaPhieuThu")
	String MaPhieuThu;
	
	@Column(name = "NgayLap")
	Date NgayLap;
	
	@Column(name = "IdPhieuDichVu")
	Long IdPhieuDichVu;
	
//	@Column(name = "IdPhieuBanXe")
//	Long IdPhieuBanXe;
	
	@Column(name = "IdPhieuBanLe")
	Long IdPhieuBanLe;
	
	@Column(name = "NoiDung")
	String NoiDung;
	
	@Column(name = "SoTien")
	Double SoTien;
	
	@Column(name = "MaNV")
	long MaNV;
	
	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	private NhanVien NhanVien;

	public long getIdPhieuThuTien() {
		return IdPhieuThuTien;
	}

	public void setIdPhieuThuTien(long idPhieuThuTien) {
		IdPhieuThuTien = idPhieuThuTien;
	}

	public String getMaPhieuThu() {
		return MaPhieuThu;
	}

	public void setMaPhieuThu(String maPhieuThu) {
		MaPhieuThu = maPhieuThu;
	}

	public Date getNgayLap() {
		return NgayLap;
	}

	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
	}

	public Long getIdPhieuDichVu() {
		return IdPhieuDichVu;
	}

	public void setIdPhieuDichVu(Long idPhieuDichVu) {
		IdPhieuDichVu = idPhieuDichVu;
	}

//	public Long getIdPhieuBanXe() {
//		return IdPhieuBanXe;
//	}
//
//	public void setIdPhieuBanXe(Long idPhieuBanXe) {
//		IdPhieuBanXe = idPhieuBanXe;
//	}

	public Long getIdPhieuBanLe() {
		return IdPhieuBanLe;
	}

	public void setIdPhieuBanLe(Long idPhieuBanLe) {
		IdPhieuBanLe = idPhieuBanLe;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public Double getSoTien() {
		return SoTien;
	}

	public void setSoTien(Double soTien) {
		SoTien = soTien;
	}

	public long getMaNV() {
		return MaNV;
	}

	public void setMaNV(long maNV) {
		MaNV = maNV;
	}

	public NhanVien getNhanVien() {
		return NhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		NhanVien = nhanVien;
	}
	
	public String getShortNgayLap() {
		return StringFormatUtil.shortDateTime(this.NgayLap);
	}
	
	
}
