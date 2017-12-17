package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
