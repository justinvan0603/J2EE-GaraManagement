package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhomnhacungcap")
public class NhomNhaCungCap {

	@Id
	@Column(name = "TenNhom")
	private String TenNhom;

	public String getTenNhom() {
		return TenNhom;
	}

	public void setTenNhom(String tenNhom) {
		TenNhom = tenNhom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TenNhom == null) ? 0 : TenNhom.hashCode());
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
		NhomNhaCungCap other = (NhomNhaCungCap) obj;
		if (TenNhom == null) {
			if (other.TenNhom != null)
				return false;
		} else if (!TenNhom.equals(other.TenNhom))
			return false;
		return true;
	}

}
