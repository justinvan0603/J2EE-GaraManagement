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

import org.hibernate.annotations.Formula;

import utils.StringFormatUtil;

@Entity
@Table(name = "phieubanxe")
public class PhieuBanXe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuBanXe")
	public Integer idPHieuBanXe;

	@Column(name = "MaPhieuBanXe")
	public String maPhieuBanXe;

	@Column(name = "NgayLap")
	@Temporal(TemporalType.DATE)
	public Date ngayLap;

	@Column(name = "HanChotThanhToan")
	@Temporal(TemporalType.DATE)
	public Date hanChotThanhToan;

	@Column(name = "HanBaoHanh")
	public Integer hanBaoHanh;

	@Column(name = "BienSoXe")
	public String bienSoXe;

	@Column(name = "TriGia")
	public Double triGia;

	@Column(name = "SoTienConLai")
	public Double soTienConLai;

	@Column(name = "MAKH")
	public Integer maKH;

	@Column(name = "MaNV")
	public Integer maNV;

	// Number of service headers for this current "Phieu tiep nhan"
	// this field is as a flag for view to add some components or not
	@Formula("(select count(*) from phieuthutien A where A.IdPhieuBanXe = IdPhieuBanXe )")
	private Integer numberofPaymentBills;

	// relationships

	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	public NhanVien nhanVien;

	public PhieuBanXe() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPHieuBanXe() {
		return idPHieuBanXe;
	}

	public void setIdPHieuBanXe(Integer idPHieuBanXe) {
		this.idPHieuBanXe = idPHieuBanXe;
	}

	public String getMaPhieuBanXe() {
		return maPhieuBanXe;
	}

	public void setMaPhieuBanXe(String maPhieuBanXe) {
		this.maPhieuBanXe = maPhieuBanXe;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Date getHanChotThanhToan() {
		return hanChotThanhToan;
	}

	public void setHanChotThanhToan(Date hanChotThanhToan) {
		this.hanChotThanhToan = hanChotThanhToan;
	}

	public Integer getHanBaoHanh() {
		return hanBaoHanh;
	}

	public void setHanBaoHanh(Integer hanBaoHanh) {
		this.hanBaoHanh = hanBaoHanh;
	}

	public String getBienSoXe() {
		return bienSoXe;
	}

	public void setBienSoXe(String bienSoXe) {
		this.bienSoXe = bienSoXe;
	}

	public Double getTriGia() {
		return triGia;
	}

	public void setTriGia(Double triGia) {
		this.triGia = triGia;
	}

	public Double getSoTienConLai() {
		return soTienConLai;
	}

	public void setSoTienConLai(Double soTienConLai) {
		this.soTienConLai = soTienConLai;
	}

	public Integer getMaKH() {
		return maKH;
	}

	public void setMaKH(Integer maKH) {
		this.maKH = maKH;
	}

	public Integer getMaNV() {
		return maNV;
	}

	public void setMaNV(Integer maNV) {
		this.maNV = maNV;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Integer getNumberofPaymentBills() {
		return numberofPaymentBills;
	}

	// additional methods

	@Override
	public String toString() {
		return "PhieuBanXe [idPHieuBanXe=" + idPHieuBanXe + ", maPhieuBanXe=" + maPhieuBanXe + ", ngayLap=" + ngayLap
				+ ", hanChotThanhToan=" + hanChotThanhToan + ", hanBaoHanh=" + hanBaoHanh + ", bienSoXe=" + bienSoXe
				+ ", triGia=" + triGia + ", soTienConLai=" + soTienConLai + ", maKH=" + maKH + ", maNV=" + maNV
				+ ", numberofPaymentBills=" + numberofPaymentBills + ", nhanVien=" + nhanVien + "]";
	}

	public String getShortCreationDate() {
		return StringFormatUtil.shortDateTime(this.ngayLap);
	}

	public String getShortPaymentDate() {
		return StringFormatUtil.shortDateTime(this.hanChotThanhToan);
	}

	public String getCurrencyString() {
		return StringFormatUtil.toCurrencyString(this.triGia);
	}

}
