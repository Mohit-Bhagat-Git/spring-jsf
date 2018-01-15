package spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.Admin;



public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	public List<Admin> findByRole(String role);
	public List<Admin> findByAdminIdGreaterThan(int id);

}
