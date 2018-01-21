package spring.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.model.Admin;
import spring.repository.AdminRepository;
import spring.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	
	@GetMapping("/readAll")
	public Collection<Admin> readAll(){
		List<Admin> listAdmin = service.getAllAdmin();
		System.out.println(listAdmin.size() +" Entries found");
		Collection<Admin> collection = listAdmin;
		return collection;
	}
	
	@GetMapping("/emReadByKeyword/{keyword}")
	public Collection<Admin> readAdminByCriteria(@PathVariable String keyword){
		List<Admin> listAdmin = service.emFindByKeyword(keyword);
		System.out.println(listAdmin.size() +" Entries found");
		Collection<Admin> collection = listAdmin;
		return collection;
	}
	
	@GetMapping("/findByRole/{role}")
	public Collection<Admin> findByRole(@PathVariable String role){
		List<Admin> listAdmin = service.findByRole(role);
		System.out.println(listAdmin.size() +" Entries found");
		Collection<Admin> collection = listAdmin;
		return collection;
	}
	
	@GetMapping(value="/getAllByIdGreaterThan/{id}")
	public Collection<Admin> getAllByIdGreaterThan(@PathVariable int id){
		return service.getAllByIdGreaterThan(id);
	}
	
	@GetMapping(value="/getById/{id}")
	public Admin getById(@PathVariable int id){
		Admin admin =  service.getById(id);
		System.out.println(admin.getUserName());
		return admin;
	}
	
	@PostMapping("/saveOrUpdateAdmin")
	public Admin saveOrUpdate(@RequestBody Admin admin){
		Admin resultAdmin =  service.saveOrUpdateAdmin(admin);
		System.out.println(admin.getUserName());
		return resultAdmin;
	}
	
	/**
	 * @param admin
	 * @return This method always return true even if we pass a record which is not present in the database.
	 * 
	 * As of now solution could be to use entity manager directly inside Service class.
	 */
	@DeleteMapping(value = "/deleteAdmin")
	public boolean delete(@RequestBody Admin admin){
		boolean deleted =  service.deleteAdmin(admin);
		return deleted;
	}
	
	
	@DeleteMapping(value = "/emDelete")
	public boolean deleteEm(@RequestBody Admin admin){
		boolean deleted =  service.emDelete(admin);
		return deleted;
	}
	
	@DeleteMapping(value = "/deleteAdminById")
	public boolean deleteAdminbyId(@RequestBody Admin admin){
		boolean deleted =  service.deleteAdminbyId(admin);
		return deleted;
	}
	
	
	@GetMapping("/alive")
	public String aliveCheck(){
		String message = "Admin Controller is alive!!";
		System.out.println(message);
		return message;
	}
}
