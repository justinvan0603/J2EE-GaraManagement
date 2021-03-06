package viewmodels;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.Customer;
import business.entities.HieuXe;
import business.entities.PhieuTiepNhan;
import business.entities.Xe;
import business.service.CustomerServiceImpl;
import business.service.HieuXeServiceImpl;
import business.service.PhieuTiepNhanServiceImpl;
import business.service.XeServiceImpl;

/**
 * View model for view :
 * 
 * @author TNS
 *
 */
public class PhieuTiepNhanAddViewModel {

	private String type;
	// all services
	private CustomerServiceImpl customerServiceImpl;
	private HieuXeServiceImpl hieuXeServiceImpl;
	private XeServiceImpl xeServiceImpl;
	private PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl;

	// used for add new receive header from new customer
	private List<HieuXe> listOfHieuXes;

	// used for add new receive header from frequenter
	private List<Customer> listOfCustomers;
	private List<Xe> listOfVehicle; // base on selected customer

	// values to be saved
	private Customer customer;
	private PhieuTiepNhan phieuTiepNhan;
	private Xe xe;

	private HieuXe selectedHieuXe;
	private String selectedGioiTinh = "Nam";

	@Init
	public void init() {
		// get parameter from session
		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.type = (String) Sessions.getCurrent().getAttribute(PhieuTiepNhanDSViewModel.ADD_NEW_TYPE);
		// make sure we have valid session value
		if (this.type == null) {
			Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
		}

		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		this.xeServiceImpl = (XeServiceImpl) SpringUtil.getBean("xe_service");
		this.phieuTiepNhanServiceImpl = (PhieuTiepNhanServiceImpl) SpringUtil.getBean("phieutiepnhan_service");
		// check valid type
		if (!type.isEmpty()) {
			// from new customer
			if (type.equals(PhieuTiepNhanDSViewModel.FROM_NEW_CUSTOMER)) {
				// instance essential values
				this.customer = new Customer();
				this.customer.setGioiTinh(true); // male by default
				this.phieuTiepNhan = new PhieuTiepNhan();
				this.phieuTiepNhan.setGivebackDate(new Date());
				this.xe = new Xe();
				if (this.hieuXeServiceImpl != null) {
					// load all vehicle types except type "Tất cả"
					this.listOfHieuXes = this.hieuXeServiceImpl.getListOfHieuXeExceptTatCa();
					if (this.listOfHieuXes != null && this.listOfHieuXes.size() > 0) {
						this.selectedHieuXe = this.listOfHieuXes.get(0);
						// by default, the first hieuxe will be assigned
						this.xe.setHieuXeReference(this.selectedHieuXe.getMaHieuXe());
					}
				}

			} else if (type.equals(PhieuTiepNhanDSViewModel.FROM_FREQUENTER)) { // from
																				// frequenter
				// instance essential values
				this.phieuTiepNhan = new PhieuTiepNhan();
				this.phieuTiepNhan.setGivebackDate(new Date());
				if (this.customerServiceImpl != null) {
					this.listOfCustomers = this.customerServiceImpl.getAll(Customer.class);
					if (this.listOfCustomers != null && this.listOfCustomers.size() > 0) {
						this.customer = this.listOfCustomers.get(0);
						this.phieuTiepNhan.setCustomerId(this.customer.getMaKH());
					}
					this.listOfVehicle = this.xeServiceImpl
							.loadByCustomerId(Integer.parseInt(this.customer.getMaKH() + ""));
					if (this.listOfVehicle != null && this.listOfVehicle.size() > 0) {
						this.xe = this.listOfVehicle.get(0);
						this.phieuTiepNhan.setLicensePlate(this.xe.getBienSoXe());
					}
				}
			}
		}
	}

	/**
	 * Handle when combobox customer changes selected item
	 */
	@Command
	@NotifyChange({ "listOfVehicle", "xe" })
	public void onCustomerSelectChange(@BindingParam("selected_customer_id") Long customerId,
			@BindingParam("vehicle_combobox") Component component) {
		// get list of vehicle belonging to selected customer
		this.listOfVehicle = this.xeServiceImpl.loadByCustomerId(customerId.intValue());
		// update selected customer id
		this.phieuTiepNhan.setCustomerId(customerId);
		// update selected customer vehicle
		List<Xe> listOfCustomerVehicle = this.xeServiceImpl.loadByCustomerId(customerId.intValue());
		if (listOfCustomerVehicle != null) {
			if (!listOfCustomerVehicle.isEmpty()) {
				this.xe = listOfCustomerVehicle.get(0);
				// update bien so xe phieu tiep nhan
				this.phieuTiepNhan.setLicensePlate(this.xe.getBienSoXe());
			} else {
				this.xe = new Xe();
			}
		} else {
			this.xe = new Xe();
		}
	}

	@Command
	public void onVehicleTypesComboboxChanged(@BindingParam("vehicle_type") String vehicleType) {
		this.xe.setHieuXeReference(vehicleType);
	}

	/**
	 * Bat su kien khi combobox gioi tinh de cap nhat gioi tinh vi combobox
	 * khong binding xuong duoc
	 * 
	 * @param gender
	 */
	@Command
	public void onComboboxGenderChanged(@BindingParam("gender_boolean") boolean gender) {
		this.customer.setGioiTinh(gender);
	}

	@Command
	public void onComboboxVehicleChanged(@BindingParam("vehicle_licenseplate") String licensePlate) {
		System.out.println("onComboboxVehicleChanged licensePlate  :" + licensePlate);
		// update bien so xe cho phieu tiep nhan
		this.phieuTiepNhan.setLicensePlate(licensePlate);
	}

	/**
	 * Luu phieu tiep nhan vao trong database
	 */
	@Command
	public void luuPhieuTiepNhan() {

		if (this.type.equals((PhieuTiepNhanDSViewModel.FROM_NEW_CUSTOMER))) {
			// sau khi luu thong tin xe va luu thong tin khach hang thanh cong,
			// tien
			// hanh luu phieu tiep nhan
			this.customer.setSoTienNo(0.0D);
			if (this.customerServiceImpl.save(this.customer)) {
				// lay id cua khach hang
				// Long lastCustomerId = this.customerServiceImpl.getLastedId();
				// cap nhat ma khach hang cho xe
				this.xe.setMaKH(Integer.parseInt(this.customer.getMaKH() + ""));
				this.xe.setHinhThuc(false); //

				if (this.xeServiceImpl.save(this.xe)) {
					this.phieuTiepNhan.setLicensePlate(this.xe.getBienSoXe());
					this.phieuTiepNhan.setCustomerId(this.customer.getMaKH());
					this.phieuTiepNhan.setStaffId(
							Integer.parseInt(Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) + "")); // current
																														// signed
																														// in
																														// staff
					this.phieuTiepNhan.setState(this.xe.getTinhTrang()); // status
																			// of
																			// voucher

					if (this.phieuTiepNhanServiceImpl.save(this.phieuTiepNhan)) {
						Messagebox.show("Lưu thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
						Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
					}
				}
			}
		} else if (this.type.equals(PhieuTiepNhanDSViewModel.FROM_FREQUENTER)) {
			this.phieuTiepNhan
					.setStaffId(Integer.parseInt(Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) + "")); // current
																															// signed
																															// in
																															// staff
			if (this.phieuTiepNhanServiceImpl.save(this.phieuTiepNhan)) {
				Messagebox.show("Lưu thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
				Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
			}
		}
	}

	public List<HieuXe> getListOfHieuXes() {
		return listOfHieuXes;
	}

	public void setListOfHieuXes(List<HieuXe> listOfHieuXes) {
		this.listOfHieuXes = listOfHieuXes;
	}

	public List<Customer> getListOfCustomers() {
		return listOfCustomers;
	}

	public void setListOfCustomers(List<Customer> listOfCustomers) {
		this.listOfCustomers = listOfCustomers;
	}

	public List<Xe> getListOfVehicle() {
		return listOfVehicle;
	}

	public void setListOfVehicle(List<Xe> listOfVehicle) {
		this.listOfVehicle = listOfVehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PhieuTiepNhan getPhieuTiepNhan() {
		return phieuTiepNhan;
	}

	public void setPhieuTiepNhan(PhieuTiepNhan phieuTiepNhan) {
		this.phieuTiepNhan = phieuTiepNhan;
	}

	public Xe getXe() {
		return xe;
	}

	public void setXe(Xe xe) {
		this.xe = xe;
	}

	public HieuXe getSelectedHieuXe() {
		return selectedHieuXe;
	}

	public void setSelectedHieuXe(HieuXe selectedHieuXe) {
		this.selectedHieuXe = selectedHieuXe;
	}

	public Date getCurrentDate() {
		return new Date();
	}

	public String getSelectedGioiTinh() {
		return selectedGioiTinh;
	}

	public void setSelectedGioiTinh(String selectedGioiTinh) {
		this.selectedGioiTinh = selectedGioiTinh;
	}

}
