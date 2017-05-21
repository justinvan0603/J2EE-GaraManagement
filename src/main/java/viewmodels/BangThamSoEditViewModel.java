package viewmodels;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.BangThamSo;

import business.service.BangThamSoServiceImpl;


public class BangThamSoEditViewModel {
	@WireVariable
	private BangThamSoServiceImpl bangThamSoService;
	private BangThamSo currentThamSo;

	public BangThamSoServiceImpl getBangThamSoService() {
		return bangThamSoService;
	}

	public void setBangThamSoService(BangThamSoServiceImpl bangThamSoService) {
		this.bangThamSoService = bangThamSoService;
	}

	public BangThamSo getCurrentThamSo() {
		return currentThamSo;
	}

	public void setCurrentThamSo(BangThamSo currentThamSo) {
		this.currentThamSo = currentThamSo;
	}

	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		this.bangThamSoService = (BangThamSoServiceImpl) SpringUtil.getBean("bangthamso_service");
		String thamsoId = (String) Sessions.getCurrent().getAttribute(BangThamSoDSViewModel.SELECTED_THAMSO);
		this.currentThamSo = this.bangThamSoService.findByIdThamSoString(thamsoId, BangThamSo.class);

	}
	@Command
	public void updateThamSo()
	{
		if(!this.currentThamSo.getGiaTri().isEmpty() && !this.currentThamSo.getNoiDung().isEmpty())
		{
			if(this.bangThamSoService.update(this.currentThamSo.getTenThamSo(), this.currentThamSo))
			{
				Messagebox.show("Cập nhật thành công");
				Executions.sendRedirect("./BangThamSo_DS.zul");
			}
			else
			{
				Messagebox.show("Đã có lỗi xảy ra!", "Lỗi", Messagebox.OK,
						Messagebox.ERROR);
			}
		}
		else
		{
			Messagebox.show("Vui lòng nhập đầy đủ thông tin!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}

}
