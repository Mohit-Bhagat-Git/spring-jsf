package spring.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import spring.dao.AdminDao;
import spring.dao.AdminDaoImpl;
import spring.model.Admin;

@Service
public class AdminService {
	
	
	/*@Autowired
	private AdminDaoImpl adminDaoImpl;*/
	
	/*@Autowired
	private AdminDao adminDaoImpl;
	
	
	public Collection<Admin> getAll(){
		List<Admin> resultList = (List<Admin>) adminDaoImpl.findAll();
		System.out.println(resultList.size());
		return null;
	}
*/
	/*public Admin getByKey(int id) {
		
		return (Admin) adminDaoImpl.findOne(id);
	}
*/
}
