package spring.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/app")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/readDummyData")// To map any page extension for example .xhtml, .jsf
	public Collection<Admin> readAllDummyData(){
		
		return  service.getAllDummyDataDao();
	}
	
	
	@GetMapping("/readAll")// To map any page extension for example .xhtml, .jsf
	public Collection<Admin> readAll(){
		List<Admin> listAdmin = service.getAllAdmin();
		System.out.println(listAdmin.size() +" Entries found");
		Collection<Admin> collection = listAdmin;
		return collection;
	}
	
	@RequestMapping(value="/findById/{id}")
	public Admin getById(@PathVariable int id){
		Admin admin =  service.getByKey(id);
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
	@RequestMapping(value = "/deleteAdmin", method=RequestMethod.DELETE)
	public boolean delete(@RequestBody Admin admin){
		boolean deleted =  service.deleteAdmin(admin);
		return deleted;
	}
	
	
	@RequestMapping(value = "/deleteAdminEm", method=RequestMethod.DELETE)
	public boolean deleteEm(@RequestBody Admin admin){
		boolean deleted =  service.deleteAdminEm(admin);
		return deleted;
	}
	
	@RequestMapping(value = "/deleteAdminById", method=RequestMethod.DELETE)
	public boolean deleteAdminbyId(@RequestBody Admin admin){
		boolean deleted =  service.deleteAdminbyId(admin);
		return deleted;
	}
	
	
	@RequestMapping("/alive")
	public String aliveCheck(){
		String message = "Application is working fine!!";
		System.out.println(message);
		return message;
	}
}
