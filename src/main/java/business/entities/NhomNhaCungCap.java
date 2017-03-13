package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nhomnhacungcap")
public class NhomNhaCungCap {

	@Column(name = "TenNhom")
	private String TenNhom;

	public String getTenNhom() {
		return TenNhom;
	}

	public void setTenNhom(String tenNhom) {
		TenNhom = tenNhom;
	}
	
}
