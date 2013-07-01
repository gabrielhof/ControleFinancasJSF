package br.feevale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.feevale.model.bean.Menu;

@ManagedBean
@ApplicationScoped
public class MenuBean {

	private List<Menu> menus = new ArrayList<Menu>();
	
	public MenuBean() {
		menus.add(new Menu("Gastos", "index.xhtml"));
		menus.add(new Menu("Naturezas", "naturezas.xhtml"));
	}
	
	public List<Menu> getMenus() {
		return menus;
	}
	
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
