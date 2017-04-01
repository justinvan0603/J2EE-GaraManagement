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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utils.StringFormatUtil;

@Entity
@Table(name = "phieubaohanh")
public class PhieuBaoHanh {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaPhieuBH")
	private Integer maPhieuBaoHanh;

	@Column(name = "NgayLap")
	@Temporal(TemporalType.DATE)
	private Date ngayLap;

	@Column(name = "MaNV")
	private Integer maNhanVien;

	@Column(name = "TinhTrang")
	private String tinhTrang;

	// relationships
	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	private NhanVien nhanVien;

	// default constructor
	public PhieuBaoHanh() {

	}

	public Integer getMaPhieuBaoHanh() {
		return maPhieuBaoHanh;
	}

	public void setMaPhieuBaoHanh(Integer maPhieuBaoHanh) {
		this.maPhieuBaoHanh = maPhieuBaoHanh;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Integer getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(Integer maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	/**
	 * Get date value in {@link String}
	 * 
	 * @return
	 */
	public String getShortCreationDate() {
		return StringFormatUtil.shortDateTime(this.ngayLap);
	}

}
