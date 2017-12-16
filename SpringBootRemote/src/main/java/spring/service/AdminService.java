package spring.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Admin;

import spring.dao.AdminDao;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public Collection<Admin> getAll(){
		return adminDao.getAll();
	}

	public Admin getByKey(int id) {
		
		return adminDao.getByid(id);
	}

}
