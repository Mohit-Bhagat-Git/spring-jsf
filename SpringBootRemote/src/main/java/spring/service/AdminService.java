package spring.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.DummyDao;
import spring.model.Admin;
import spring.repository.AdminRepository;

@Service
@Transactional
public class AdminService {
	
	
	@Autowired
	private DummyDao dummyDao;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private EntityManager em;
	
	
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Collection<Admin> getAllDummyDataDao(){
    	return dummyDao.getAll();
    }

	public Admin getByKey(int id) {
		
		return adminRepository.getOne(id);
	}

	/**
	 * @param admin
	 * 
	 * This method will create db entry if it dosent exist and update the entity if it exits.
	 * @return
	 */
	public Admin saveOrUpdateAdmin(Admin admin) {
		
		return adminRepository.saveAndFlush(admin);
	}
	

	/**
	 * @param admin
	 * @return This method executes even an entity is deleted. 
	 */
	public boolean deleteAdmin(Admin admin) {
		adminRepository.delete(admin);
		return true;
	}
	
	public boolean deleteAdminbyId(Admin admin) {
		adminRepository.delete(admin.getAdminId());
		return true;
	}
	
	public boolean deleteAdminEm(Admin admin){
		boolean deleted = false;
		try{
			em.remove(admin);
			em.flush();
			deleted = true;
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
		}
		return deleted;
	}
	
}
