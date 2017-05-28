package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.BangThamSo;

import business.service.BangThamSoServiceImpl;
import utils.PermissionChecker;

public class BangThamSoDSViewModel {
	@WireVariable
	private BangThamSoServiceImpl bangThamSoServiceImpl;
	public static String SELECTED_THAMSO = "thamso_id";
	private List<BangThamSo> listThamSo;
	private BangThamSo selectedThamSo;

	public BangThamSo getSelectedThamSo() {
		return selectedThamSo;
	}

	public void setSelectedThamSo(BangThamSo selectedThamSo) {
		this.selectedThamSo = selectedThamSo;
	}

	public List<BangThamSo> getListThamSo() {
		return listThamSo;
	}

	public void setListThamSo(List<BangThamSo> listThamSo) {
		this.listThamSo = listThamSo;
	}

	@Init
	public void init() {
		
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		if(!PermissionChecker.isAdministrator((String)Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_PERMISSION)))
		{
			Messagebox.show("Bạn không có quyền truy cập vào chức năng này!");
		}
		this.bangThamSoServiceImpl = (BangThamSoServiceImpl) SpringUtil.getBean("bangthamso_service");
		this.listThamSo = this.bangThamSoServiceImpl.getAll(BangThamSo.class);

	}

	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {

		SEARCH_TYPES.add("Nội dung");

	}

	// dung static nhe
	public List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}

	public BangThamSoServiceImpl getBangThamSoServiceImpl() {
		return bangThamSoServiceImpl;
	}

	public void setBangThamSoServiceImpl(BangThamSoServiceImpl bangThamSoServiceImpl) {
		this.bangThamSoServiceImpl = bangThamSoServiceImpl;
	}

	@Command
	public void editThamSo(@BindingParam("id") String id) {
		Sessions.getCurrent().setAttribute(SELECTED_THAMSO, id);
		Executions.sendRedirect("./BangThamSo_Edit.zul");
	}

	@Command
	@NotifyChange("listNhanVien")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listThamSo = this.bangThamSoServiceImpl.findByName(searchString);

		}

	}
}
