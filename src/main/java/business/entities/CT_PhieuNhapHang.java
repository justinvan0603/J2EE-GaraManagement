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
@Table(name = "ct_phieunh")
public class CT_PhieuNhapHang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long Id;

	@Column(name = "IdPhieuNhapHang")
	private long IdPhieuNhapHang;

	@Column(name = "MaPhuTung")
	private long IdPhuTung;

	@Column(name = "SoLuong")
	private int SoLuong;

	@Column(name = "DonGia")
	private Double DonGia;
	
	@Column(name = "ThanhTien")
	private Double ThanhTien;

	@Transient
	private String TenPT;
	
	@Transient
	private String MaPT;
	
	@ManyToOne
	@JoinColumn(name = "MaPhuTung", referencedColumnName = "Id", insertable = false, updatable = false)
	private PhuTung PhuTung;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getIdPhieuNhapHang() {
		return IdPhieuNhapHang;
	}

	public void setIdPhieuNhapHang(long idPhieuNhapHang) {
		IdPhieuNhapHang = idPhieuNhapHang;
	}

	public long getIdPhuTung() {
		return IdPhuTung;
	}

	public void setIdPhuTung(long idPhuTung) {
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

	public PhuTung getPhuTung() {
		return PhuTung;
	}

	public void setPhuTung(PhuTung phuTung) {
		PhuTung = phuTung;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (IdPhieuNhapHang ^ (IdPhieuNhapHang >>> 32));
		result = prime * result + (int) (IdPhuTung ^ (IdPhuTung >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CT_PhieuNhapHang other = (CT_PhieuNhapHang) obj;
		if (IdPhieuNhapHang != other.IdPhieuNhapHang)
			return false;
		if (IdPhuTung != other.IdPhuTung)
			return false;
		return true;
	}
	
	public String getTenPT() {
		return TenPT;
	}

	public void setTenPT(String tenPT) {
		TenPT = tenPT;
	}

	public String getMaPT() {
		return MaPT;
	}

	public void setMaPT(String maPT) {
		MaPT = maPT;
	}
}
