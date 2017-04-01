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
	
}
