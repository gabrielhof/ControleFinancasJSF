package br.feevale.factory.dao;

import java.util.HashMap;
import java.util.Map;

import br.feevale.model.dao.GenericDAO;

public class DAOFactoryImpl extends DAOFactory {

	private Map<Class<?>, GenericDAO<?>> daos = new HashMap<Class<?>, GenericDAO<?>>();
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends GenericDAO<?>> T getDao(Class<T> daoClass) {
		if (!daos.containsKey(daoClass)) {
			try {
				GenericDAO<?> dao = daoClass.newInstance();
				daos.put(daoClass, dao);
			} catch (Exception e) {
				throw new RuntimeException(String.format("%s precisa ter um construtor sem argumentos (POJO).", daoClass.toString()), e);
			}
		}
		//TODO
		return (T) daos.get(daoClass);
	}

}
