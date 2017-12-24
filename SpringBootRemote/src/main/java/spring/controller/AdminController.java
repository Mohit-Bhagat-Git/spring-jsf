package spring.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	@RequestMapping(value="/{id}")
	public Admin getByKey(@PathVariable int id){
		Admin admin =  service.getByKey(id);
		System.out.println(admin.getUserName());
		return admin;
	}
	
	@RequestMapping("/alive")
	public String aliveCheck(){
		String message = "Application is working fine!!";
		System.out.println(message);
		return message;
	}

}
