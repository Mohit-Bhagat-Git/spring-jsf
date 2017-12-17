package spring.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.Admin;

@Repository
public class AdminDao {
	
	private static Map<Integer, Admin> adminMap;
	
	static {
		adminMap = new HashMap<Integer, Admin>();
		Admin one = new Admin(1,"Mohit", "Owner");
		adminMap.put(1, one );
		
		Admin two = new Admin(1,"Bhagat", "Vehla");
		adminMap.put(2, two );
	}
	
	public Collection<Admin> getAll(){
		return adminMap.values();
	}

	public Admin getByid(int id) {
		return adminMap.get(id);
	}

}
