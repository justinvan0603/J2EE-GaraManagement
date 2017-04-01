package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ct_phieudv")
public class CT_PhieuDichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long Id;
	@Column(name = "IdPhieuDichVu")
	private String IdPhieuDichVu;
	@Column(name = "MaPhuTung")
	private long MaPhuTung;
	@Column(name = "SoLuong")
	private long SoLuong;
	@Column(name = "DonGia")
	private double DonGia;
	@Column(name = "ThanhTien")
	private double ThanhTien;
	@Column(name = "HanBaoHanh")
	private Date HanBaoHanh;

	public CT_PhieuDichVu() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getIdPhieuDichVu() {
		return IdPhieuDichVu;
	}

	public void setIdPhieuDichVu(String idPhieuDichVu) {
		IdPhieuDichVu = idPhieuDichVu;
	}

	public long getMaPhuTung() {
		return MaPhuTung;
	}

	public void setMaPhuTung(long maPhuTung) {
		MaPhuTung = maPhuTung;
	}

	public long getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(long soLuong) {
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

	public Date getHanBaoHanh() {
		return HanBaoHanh;
	}

	public void setHanBaoHanh(Date hanBaoHanh) {
		HanBaoHanh = hanBaoHanh;
	}

}
