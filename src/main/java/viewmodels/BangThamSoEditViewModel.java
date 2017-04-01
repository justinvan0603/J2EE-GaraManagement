package viewmodels;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;

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
		this.bangThamSoService = (BangThamSoServiceImpl) SpringUtil.getBean("bangthamso_service");
		String thamsoId = (String) Sessions.getCurrent().getAttribute(BangThamSoDSViewModel.SELECTED_THAMSO);
		this.currentThamSo = this.bangThamSoService.findByIdThamSoString(thamsoId, BangThamSo.class);

	}

}
