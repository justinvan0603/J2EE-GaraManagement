package viewmodels;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;

import business.entities.Customer;
import business.entities.HieuXe;
import business.entities.Xe;
import business.service.CustomerServiceImpl;
import business.service.HieuXeServiceImpl;

/**
 * View model for view :
 * 
 * @author TNS
 *
 */
public class PhieuTiepNhanAddViewModel {
	// all services
	private CustomerServiceImpl customerServiceImpl;
	private HieuXeServiceImpl hieuXeServiceImpl;

	// used for add new receive header from new customer
	private List<HieuXe> listOfHieuXes;

	// used for add new receive header from frequenter
	private List<Customer> listOfCustomers;
	private List<Xe> listOfVehicle;

	@Init
	public void init() {
		// get parameter from session
		String type = (String) Sessions.getCurrent().getAttribute(PhieuTiepNhanDSViewModel.ADD_NEW_TYPE);

		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		// check valid type
		if (!type.isEmpty()) {
			if (type.equals(PhieuTiepNhanDSViewModel.FROM_NEW_CUSTOMER)) {
				if (this.hieuXeServiceImpl != null) {
					this.listOfHieuXes = this.hieuXeServiceImpl.getAll(HieuXe.class);
				}
			} else if (type.equals(PhieuTiepNhanDSViewModel.FROM_FREQUENTER)) {
				if (this.customerServiceImpl != null) {
					this.listOfCustomers = this.customerServiceImpl.getAll(Customer.class);
				}
			}
		}
	}

	/**
	 * Handle when combobox customer changes selected item
	 */
	@Command
	@NotifyChange("listOfVehicle")
	public void onCustomerSelectChange(@BindingParam("customer_combobox") Component component) {
		// get list of vehicle belonging to selected customer
		Combobox customerCombobox = (Combobox) component;
		Messagebox.show(customerCombobox.getSelectedItem().toString());

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

}
