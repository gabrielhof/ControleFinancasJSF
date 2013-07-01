package br.feevale.factory.dao;

import br.feevale.model.dao.GenericDAO;

public abstract class DAOFactory {

	private static DAOFactory INSTANCE = null;
	
	public static DAOFactory getDefault() {
		if (INSTANCE == null) {
			INSTANCE = new DAOFactoryImpl();
		}
		
		return INSTANCE;
	}
	
	public abstract <T extends GenericDAO<?>> T getDao(Class<T> daoClass); 
	
}
