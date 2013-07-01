package br.feevale.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.feevale.model.bean.Usuario;
import br.feevale.settings.ApplicationConstants;

public class LoginFilter implements Filter {

	private List<String> allowed = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		allowed.add("login.xhtml");
		allowed.add("javax.faces.resource");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if (isAuthenticationRequired(httpRequest.getRequestURI()) || isAllowed(httpRequest.getSession())) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("login.xhtml");
		}
	}

	protected boolean isAuthenticationRequired(String url) {
		String brokenUrl[] = url.split("/");
		
		if (brokenUrl.length > 1) {
			url = "";
			
			for (int i = 2; i < brokenUrl.length; i++) {
				if ((brokenUrl.length-1) == i) {
					url += brokenUrl[i];
				} else {
					url += brokenUrl[i] + "/";
				}
			}
		} else {
			url = "index.xhtml";
		}

		boolean matches = false;
		for (String string : allowed) {
			if (url.contains(string)) {
				matches = true;
				break;
			}
		}
		
		return matches;
	}
	
	protected boolean isAllowed(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(ApplicationConstants.USER_SESSION);
		return usuario != null;
	}
	
	@Override
	public void destroy() {}

}