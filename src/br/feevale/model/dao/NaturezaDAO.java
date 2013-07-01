package br.feevale.model.dao;

import br.feevale.model.bean.Natureza;

public class NaturezaDAO extends HibernateDAOImpl<Natureza> {

	@Override
	protected Class<Natureza> getBeanClass() {
		return Natureza.class;
	}

}
