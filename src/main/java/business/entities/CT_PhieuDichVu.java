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
@Table(name = "ct_phieudv")
public class CT_PhieuDichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "IdPhieuDichVu")
	private long IdPhieuDichVu;
	@Column(name = "MaPhuTung")
	private long MaPhuTung;
	@Column(name = "SoLuong")
	private int SoLuong;
	@ManyToOne
	@JoinColumn(name = "MaPhuTung", referencedColumnName = "Id", insertable = false, updatable = false)
	private PhuTung PhuTung;
	public PhuTung getPhuTung() {
		return PhuTung;
	}

	public void setPhuTung(PhuTung phuTung) {
		PhuTung = phuTung;
	}

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

	public void setTenPT(String tenPT) {
		TenPT = tenPT;
	}

	@Transient
	private String TenPT;



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (IdPhieuDichVu ^ (IdPhieuDichVu >>> 32));
		result = prime * result + (int) (MaPhuTung ^ (MaPhuTung >>> 32));
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
		CT_PhieuDichVu other = (CT_PhieuDichVu) obj;
		if (IdPhieuDichVu != other.IdPhieuDichVu)
			return false;
		if (MaPhuTung != other.MaPhuTung)
			return false;
		return true;
	}

	@Column(name = "DonGia")
	private double DonGia;
	@Column(name = "ThanhTien")
	private double ThanhTien;
	@Column(name = "HanBaoHanh")
	private long HanBaoHanh;

	public CT_PhieuDichVu() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public long getIdPhieuDichVu() {
		return IdPhieuDichVu;
	}

	public void setIdPhieuDichVu(long idPhieuDichVu) {
		IdPhieuDichVu = idPhieuDichVu;
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

	public long getHanBaoHanh() {
		return HanBaoHanh;
	}

	public void setHanBaoHanh(long hanBaoHanh) {
		HanBaoHanh = hanBaoHanh;
	}

}
