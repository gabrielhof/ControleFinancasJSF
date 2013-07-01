package br.feevale.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.feevale.factory.dao.DAOFactory;
import br.feevale.model.bean.Usuario;
import br.feevale.model.dao.UsuarioDAO;
import br.feevale.settings.ApplicationConstants;
import br.feevale.util.EncryptpUtils;

@ManagedBean
@SessionScoped
public class LoginBean {

	private UsuarioDAO usuarioDAO = DAOFactory.getDefault().getDao(UsuarioDAO.class);
	
	private Usuario usuario = new Usuario();

	public LoginBean() {
		verifyLogin();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void doLogin() {
		try {
			usuario.setSenha(EncryptpUtils.md5(usuario.getSenha()));
			
			List<Usuario> usuarios = usuarioDAO.queryByExample(this.usuario);
			if (usuarios != null && usuarios.size() > 0) {
				ExternalContext context = getExternalContext();
				
				HttpSession session = (HttpSession) context.getSession(true);
				session.setAttribute(ApplicationConstants.USER_SESSION, usuarios.get(0));
				
				context.redirect("index.xhtml");
			} else {
				usuario.setSenha(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doLogoff() {
		try {
			ExternalContext context = getExternalContext();
			HttpSession session = (HttpSession) context.getSession(true);
			session.invalidate();
			
			context.redirect("login.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void verifyLogin() {
		try {
			ExternalContext context = getExternalContext();
			HttpSession session = (HttpSession) context.getSession(true);
			if (session.getAttribute(ApplicationConstants.USER_SESSION) != null) {
				context.redirect("index.xhtml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
}
