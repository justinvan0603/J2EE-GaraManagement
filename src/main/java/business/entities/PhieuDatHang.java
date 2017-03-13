package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phieudathang")
public class PhieuDatHang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_PhieuDatHang")
	private long Id_PhieuDatHang;

	@Column(name = "MaPhieuDat")
	private String MaPhieuDat;

	@Column(name = "NgayDat")
	private Date NgayDat;

	@Column(name = "NgayGiao")
	private Date NgayGiao;

	@Column(name = "MaNV")
	private int MaNV;
	
	@Column(name = "TongTien")
	private Double TongTien;
	
	@Column(name = "MaNCC")
	private int MaNCC;

	public long getId_PhieuDatHang() {
		return Id_PhieuDatHang;
	}

	public void setId_PhieuDatHang(long id_PhieuDatHang) {
		Id_PhieuDatHang = id_PhieuDatHang;
	}

	public String getMaPhieuDat() {
		return MaPhieuDat;
	}

	public void setMaPhieuDat(String maPhieuDat) {
		MaPhieuDat = maPhieuDat;
	}

	public Date getNgayDat() {
		return NgayDat;
	}

	public void setNgayDat(Date ngayDat) {
		NgayDat = ngayDat;
	}

	public Date getNgayGiao() {
		return NgayGiao;
	}

	public void setNgayGiao(Date ngayGiao) {
		NgayGiao = ngayGiao;
	}

	public int getMaNV() {
		return MaNV;
	}

	public void setMaNV(int maNV) {
		MaNV = maNV;
	}

	public Double getTongTien() {
		return TongTien;
	}

	public void setTongTien(Double tongTien) {
		TongTien = tongTien;
	}

	public int getMaNCC() {
		return MaNCC;
	}

	public void setMaNCC(int maNCC) {
		MaNCC = maNCC;
	}
	
	
}
