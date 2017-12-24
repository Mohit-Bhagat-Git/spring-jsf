package spring.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.model.Admin;


@Repository
public class DummyDao {
	
	private static Map<Integer, Admin> adminMap;
	
	static {
		adminMap = new HashMap<Integer, Admin>();
		Admin one = new Admin();
		one.setAdminId(1);
		one.setPassword("test1");
		one.setUserName("TestUser1");
		one.setRole("testRole1");
		adminMap.put(1, one );
		
		Admin two = new Admin();
		two.setPassword("test2");
		two.setUserName("TestUser2");
		two.setRole("testRole2");
		adminMap.put(2, two );
	}
	
	public Collection<Admin> getAll(){
		return adminMap.values();
	}

	public Admin getByid(int id) {
		return adminMap.get(id);
	}

}
