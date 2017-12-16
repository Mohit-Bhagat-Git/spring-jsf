package spring.controller;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Admin;

import spring.service.AdminService;

@RestController
@RequestMapping("/app")
@ManagedBean
@SessionScoped
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping("/readAll")
	public Collection<Admin> readAll(){
		int i = service.getAll().size();
		System.out.println(i +"Entries found");
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}")
	public Admin getByKey(@PathVariable int id){
		return service.getByKey(id);
	}
	
	@RequestMapping("/alive")
	public String aliveCheck(){
		String message = "Application is working fine!!";
		System.out.println(message);
		return message;
	}

}
