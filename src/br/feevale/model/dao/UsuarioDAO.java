package br.feevale.model.dao;

import br.feevale.model.bean.Usuario;

public class UsuarioDAO extends HibernateDAOImpl<Usuario> {

	@Override
	protected Class<Usuario> getBeanClass() {
		return Usuario.class;
	}

}
