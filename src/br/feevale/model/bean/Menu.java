package br.feevale.model.bean;

import javax.faces.context.FacesContext;

public class Menu {

	private String label;
	private String url;

	public Menu(String label, String url) {
		setLabel(label);
		setUrl(url);
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean getSelected() {
		String currentUrl = FacesContext.getCurrentInstance().getViewRoot().getViewId().replaceFirst("/", "");
		return currentUrl.equals(getUrl());
	}
	
}
