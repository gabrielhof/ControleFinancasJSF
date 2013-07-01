package br.feevale.model.dao;

import java.util.List;

public interface GenericDAO<T> {

	public T save(T bean) throws Exception;
	
	public boolean delete(T bean) throws Exception;
	
	public T get(Integer id) throws Exception;
	
	public List<T> listAll() throws Exception;
	
	public List<T> queryByExample(T example) throws Exception;
	
}
