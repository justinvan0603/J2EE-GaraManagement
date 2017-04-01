package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
}
