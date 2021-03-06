package viewmodels;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.Customer;
import business.entities.HieuXe;
import business.entities.Xe;
import business.service.CustomerServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.XeServiceImpl;

public class XeAddViewModel {
	@WireVariable
	private XeServiceImpl xeServiceImpl;
	@WireVariable
	private HieuXeServiceImpl hieuXeServiceImpl;
	@WireVariable
	private CustomerServiceImpl customerServiceImpl;

	//
	private List<Customer> listOfCustomers;
	private List<HieuXe> listOfHieuXes;

	private HieuXe selectedHieuXe;
	private Customer selectedCustomer;

	@Init
	public void init() {

		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.xeServiceImpl = (XeServiceImpl) SpringUtil.getBean("xe_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");

		if (this.hieuXeServiceImpl != null) {
			// load all vehicle types except type "Tất cả"
			this.listOfHieuXes = this.hieuXeServiceImpl.getListOfHieuXeExceptTatCa();
			if (this.listOfHieuXes != null && this.listOfHieuXes.size() > 0) {
				this.selectedHieuXe = this.listOfHieuXes.get(0);

			}
		}
		if (this.customerServiceImpl != null) {
			this.listOfCustomers = this.customerServiceImpl.getAll(Customer.class);
			if (this.listOfCustomers != null && this.listOfCustomers.size() > 0) {
				this.selectedCustomer = this.listOfCustomers.get(0);
			}
		}
	}

	// Save new vehicle
	@Command
	public void saveNewCustomerVehicle(@BindingParam("bien_so") String bienSo, @BindingParam("ma_khachhang") int maKh,
			@BindingParam("in_date") Date inDate, @BindingParam("hieu_xe") String maHieuXe,
			@BindingParam("so_khung") String soKhung, @BindingParam("so_may") String soMay,
			@BindingParam("so_km") int soKm, @BindingParam("tinh_trang") String tinhTrang,
			@BindingParam("doi_xe") String doiXe) {

		Xe newVehicle = new Xe();
		newVehicle.setBienSoXe(bienSo);
		newVehicle.setDoiXe(doiXe);
		newVehicle.setHieuXeReference(maHieuXe);
		newVehicle.setHinhThuc(false); // customer vehicle
		newVehicle.setMaKH(maKh);
		newVehicle.setNgayTiepNhan(inDate);
		newVehicle.setSoKhung(soKhung);
		newVehicle.setSoMay(soMay);
		newVehicle.setSoKM(soKm);
		newVehicle.setTinhTrang(tinhTrang);
		if (this.xeServiceImpl.save(newVehicle)) {
			Messagebox.show("Thành công");
			Executions.sendRedirect("./Xe_DS.zul");
		} else {
			Messagebox.show("Không thành công");
		}
	}

	public List<Customer> getListOfCustomers() {
		return listOfCustomers;
	}

	public void setListOfCustomers(List<Customer> listOfCustomers) {
		this.listOfCustomers = listOfCustomers;
	}

	public List<HieuXe> getListOfHieuXes() {
		return listOfHieuXes;
	}

	public void setListOfHieuXes(List<HieuXe> listOfHieuXes) {
		this.listOfHieuXes = listOfHieuXes;
	}

	public HieuXe getSelectedHieuXe() {
		return selectedHieuXe;
	}

	public void setSelectedHieuXe(HieuXe selectedHieuXe) {
		this.selectedHieuXe = selectedHieuXe;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	
	

}
