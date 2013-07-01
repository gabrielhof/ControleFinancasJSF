package br.feevale.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.feevale.factory.dao.DAOFactory;
import br.feevale.model.bean.Movimentacao;
import br.feevale.model.bean.Usuario;
import br.feevale.model.dao.MovimentacaoDAO;
import br.feevale.settings.ApplicationConstants;
import br.feevale.util.DateUtils;

@ManagedBean
public class AgendaBean {
	
	private MovimentacaoDAO movimentacaoDAO = DAOFactory.getDefault().getDao(MovimentacaoDAO.class);
	
	private ScheduleModel scheduleModel;
	
	public AgendaBean() {
		scheduleModel = new DefaultScheduleModel();
		configureSchedule();
	}
	
	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}
	
	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}
	
	protected void configureSchedule() {
		Date begin = DateUtils.getPrimeiroDomingoCalendario();
		Date end = DateUtils.getUltimoSabadoCalendario();
		
		Usuario usuario = (Usuario) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute(ApplicationConstants.USER_SESSION);
		
		List<Movimentacao> movimentacoes = movimentacaoDAO.getMovimentacaoBetween(begin, end, usuario);
		for (Movimentacao movimentacao : movimentacoes) {
			scheduleModel.addEvent(new DefaultScheduleEvent(movimentacao.getNome(), movimentacao.getData(), movimentacao.getData()));
		}
	}

}
