package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phieubanle")
public class PhieuBanLe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuBanLe")
	private long IdPhieuBanLe;
	@Column(name = "MaPhieuBan")
	private String MaPhieuBan;
	@Column(name = "NgayLap")
	private Date NgayLap;
	@Column(name = "HanChotThanhToan")
	private Date HanChotThanhToan;
	@Column(name = "MaKH")
	private long MaKH;
	@Column(name = "MaNV")
	private long MaNV;
	@Column(name = "TongTien")
	private double TongTien;
	@Column(name = "SoTienConLai")
	private double SoTienConLai;
	
}
