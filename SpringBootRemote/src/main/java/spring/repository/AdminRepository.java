package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.Admin;



public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
