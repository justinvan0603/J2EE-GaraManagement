package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class BangThamSo {
	@Id
	@Column(name = "TenThamSo")
	String TenThamSo;
	@Column(name = "NoiDung")
	String NoiDung;
	@Column(name = "GiaTri")
	String GiaTri;

	public String getTenThamSo() {
		return TenThamSo;
	}

	public void setTenThamSo(String tenThamSo) {
		TenThamSo = tenThamSo;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public String getGiaTri() {
		return GiaTri;
	}

	public void setGiaTri(String giaTri) {
		GiaTri = giaTri;
	}
}
