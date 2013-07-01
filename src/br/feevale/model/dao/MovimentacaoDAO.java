package br.feevale.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.feevale.model.bean.Movimentacao;
import br.feevale.model.bean.Usuario;

public class MovimentacaoDAO extends HibernateDAOImpl<Movimentacao> {

	@Override
	protected Class<Movimentacao> getBeanClass() {
		return Movimentacao.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimentacao> getMovimentacaoBetween(Date begin, Date end, Usuario usuario) {
		Session session = getHibernateSession();
		Criteria criteria = session.createCriteria(getBeanClass());
		criteria.add(Restrictions.between("data", begin, end));
		criteria.add(Restrictions.eq("usuario", usuario));
		
		List<Movimentacao> list = criteria.list();
		
		session.close();
		
		return list;
	}

}
