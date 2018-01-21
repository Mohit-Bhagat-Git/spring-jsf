package spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.model.Product;
import spring.repository.ProductRepository;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductRepository prodRepository;
	
	@Autowired
	private EntityManager em;
	
	
    public List<Product> getAllProduct() {
        return prodRepository.findAll();
    }

    /*public Collection<Product> getAllDummyDataDao(){
    	return dummyDao.getAll();
    }*/

	public Product getById(String id) {
		
		return prodRepository.getOne(id);
	}

	/**
	 * @param admin
	 * 
	 * This method will create db entry if it dosent exist and update the entity if it exits.
	 * @return
	 */
	public Product saveOrUpdateProd(Product prod) {
		
		return prodRepository.saveAndFlush(prod);
	}
	

	/**
	 * @param admin
	 * @return This method executes even an entity is deleted. 
	 */
	public boolean deleteProd(Product prod) {
		prodRepository.delete(prod);
		return true;
	}
	
	public boolean deleteProductbyId(Product prod) {
		prodRepository.delete(prod.getProductId());
		return true;
	}
	
	public boolean emDelete(Product prod){
		boolean deleted = false;
		try{
			em.remove(prod);
			em.flush();
			deleted = true;
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
		}
		return deleted;
	}

	public List<Product> emFindByKeyword(String keyword) {
		String sql = "from Product where keywords=";
		Query query  = em.createQuery(sql+"'"+keyword+"'");
		return query.getResultList();
	}
	

	public List<Product> getAllByIdGreaterThan(int id) {
		// TODO Auto-generated method stub
		return prodRepository.findByProductIdGreaterThan(id);
	}
	
}
