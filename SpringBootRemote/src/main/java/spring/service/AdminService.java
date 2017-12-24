package spring.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.DummyDao;
import spring.model.Admin;
import spring.repository.AdminRepository;

@Service
public class AdminService {
	
	
	@Autowired
	private DummyDao dummyDao;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Collection<Admin> getAllDummyDataDao(){
    	return dummyDao.getAll();
    }

	public Admin getByKey(int id) {
		
		return adminRepository.getOne(id);
	}
	
	
}
