package spring.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.model.Admin;
import spring.repository.AdminRepository;
import spring.service.AdminService;

@RestController
@RequestMapping("/app")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("/admin")
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }
	
	/*@GetMapping("/readAll.*")
	public Collection<Admin> readAll(){
		int i = service.getAll().size();
		System.out.println(i +"Entries found");
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}")
	public Admin getByKey(@PathVariable int id){
		Admin admin =  service.getByKey(id);
		System.out.println(admin.getAdminId());
		return admin;
	}*/
	
	@RequestMapping("/alive")
	public String aliveCheck(){
		String message = "Application is working fine!!";
		System.out.println(message);
		return message;
	}

}
