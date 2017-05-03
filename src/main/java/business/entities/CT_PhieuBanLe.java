package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ct_phieubanle")
public class CT_PhieuBanLe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long Id;
	@Column(name = "IdPhieuBanLe")
	private long IdPhieuBanLe;
	@Column(name = "MaPhuTung")
	private long MaPhuTung;
	@Column(name = "SoLuong")
	private int SoLuong;
	@Column(name = "DonGia")
	private double DonGia;
	@Column(name = "ThanhTien")
	private double ThanhTien;
	@Column(name = "ThoiHanBaoHanh")
	private int ThoiHanBaoHanh;
	@ManyToOne
	@JoinColumn(name = "MaPhuTung", referencedColumnName = "Id", insertable = false, updatable = false)
	private PhuTung PhuTung;
	@Transient
	private String MaPT;
	public String getMaPT() {
		return MaPT;
	}

	public void setMaPT(String maPT) {
		MaPT = maPT;
	}

	public String getTenPT() {
		return TenPT;
	}
	@Transient
	private String TenPT;
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getIdPhieuBanLe() {
		return IdPhieuBanLe;
	}

	public void setIdPhieuBanLe(long idPhieuBanLe) {
		IdPhieuBanLe = idPhieuBanLe;
	}

	public long getMaPhuTung() {
		return MaPhuTung;
	}

	public void setMaPhuTung(long maPhuTung) {
		MaPhuTung = maPhuTung;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public double getDonGia() {
		return DonGia;
	}

	public void setDonGia(double donGia) {
		DonGia = donGia;
	}

	public double getThanhTien() {
		return ThanhTien;
	}

	public void setThanhTien(double thanhTien) {
		ThanhTien = thanhTien;
	}

	public int getThoiHanBaoHanh() {
		return ThoiHanBaoHanh;
	}

	public void setThoiHanBaoHanh(int thoiHanBaoHanh) {
		ThoiHanBaoHanh = thoiHanBaoHanh;
	}

	public PhuTung getPhuTung() {
		return PhuTung;
	}

	public void setPhuTung(PhuTung phuTung) {
		PhuTung = phuTung;
	}

	public void setTenPT(String tenPT) {
		TenPT = tenPT;
	}
	
}
