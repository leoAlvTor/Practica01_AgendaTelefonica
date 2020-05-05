package ec.edu.ups.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
	
	public void createTable();
	
	public boolean create(T entity);
	
	public T read(ID id);
	
	public boolean update(T entity);
	
	public void delete(T entity);
	
	public List<T> find();
	
}
