package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ct_phieunh")
public class CT_PhieuNhapHang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long Id;

	@Column(name = "IdPhieuNhapHang")
	private int IdPhieuNhapHang;

	@Column(name = "MaPhuTung")
	private int IdPhuTung;

	@Column(name = "SoLuong")
	private int SoLuong;

	@Column(name = "DonGia")
	private Double DonGia;
	
	@Column(name = "ThanhTien")
	private Double ThanhTien;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public int getIdPhieuNhapHang() {
		return IdPhieuNhapHang;
	}

	public void setIdPhieuNhapHang(int idPhieuNhapHang) {
		IdPhieuNhapHang = idPhieuNhapHang;
	}

	public int getIdPhuTung() {
		return IdPhuTung;
	}

	public void setIdPhuTung(int idPhuTung) {
		IdPhuTung = idPhuTung;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public Double getDonGia() {
		return DonGia;
	}

	public void setDonGia(Double donGia) {
		DonGia = donGia;
	}

	public Double getThanhTien() {
		return ThanhTien;
	}

	public void setThanhTien(Double thanhTien) {
		ThanhTien = thanhTien;
	}
	
	
}
