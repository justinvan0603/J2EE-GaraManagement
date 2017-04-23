package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utils.StringFormatUtil;

/**
 * Phieu chi entity
 * 
 * @author TNS
 *
 */
@Entity
@Table(name = "phieuchi")
public class PhieuChi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(name = "MaPhieuChi")
	private String maPhieuChi;

	@Column(name = "NgayLap")
	@Temporal(TemporalType.DATE)
	private Date ngayLap;

	@Column(name = "NoiDung")
	private String noiDung;

	@Column(name = "SoTien")
	private Double giaTri;

	@Column(name = "MaNV")
	private Integer maNV;

	@Column(name = "IdPhieuNhapHang")
	private Integer idPhieuNhapHang;

	@Column(name = "IdPhieuBanXe")
	private Integer idPhieuBanXe;

	// relationships

	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	private NhanVien nhanVien;

	@OneToOne
	@JoinColumn(name = "IdPhieuNhapHang", referencedColumnName = "IdPhieuNhapHang", insertable = false, updatable = false)
	private PhieuNhapHang phieuNhapHang;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaPhieuChi() {
		return maPhieuChi;
	}

	public void setMaPhieuChi(String maPhieuChi) {
		this.maPhieuChi = maPhieuChi;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Double getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(Double giaTri) {
		this.giaTri = giaTri;
	}

	public Integer getMaNV() {
		return maNV;
	}

	public void setMaNV(Integer maNV) {
		this.maNV = maNV;
	}

	public Integer getIdPhieuNhapHang() {
		return idPhieuNhapHang;
	}

	public void setIdPhieuNhapHang(Integer idPhieuNhapHang) {
		this.idPhieuNhapHang = idPhieuNhapHang;
	}

	public Integer getIdPhieuBanXe() {
		return idPhieuBanXe;
	}

	public void setIdPhieuBanXe(Integer idPhieuBanXe) {
		this.idPhieuBanXe = idPhieuBanXe;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public PhieuNhapHang getPhieuNhapHang() {
		return phieuNhapHang;
	}

	public void setPhieuNhapHang(PhieuNhapHang phieuNhapHang) {
		this.phieuNhapHang = phieuNhapHang;
	}

	/**
	 * Get creation date in 'dd/MM/yyyy' String format
	 * 
	 * @return
	 */
	public String getShortCreationDate() {
		return StringFormatUtil.shortDateTime(this.ngayLap);
	}

	/**
	 * 
	 * @return
	 */
	public String getCurrencyString() {
		return StringFormatUtil.toCurrencyString(this.giaTri);
	}
}
