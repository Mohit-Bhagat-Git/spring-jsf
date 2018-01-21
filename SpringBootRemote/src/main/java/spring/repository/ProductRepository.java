package spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.Admin;
import spring.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	Product saveAndFlush(Product prod);

	List<Product> findByProductIdGreaterThan(int id);

}
