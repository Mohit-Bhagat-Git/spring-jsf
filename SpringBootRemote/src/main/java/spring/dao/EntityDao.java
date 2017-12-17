package spring.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.model.Admin;

@Repository
public interface EntityDao<Admin> extends CrudRepository<Admin, Integer>{
	
	
	public List<Admin> readAll();
	public Object readById(int id);
	public void removeById();
	public void update(Admin a);
	

}
