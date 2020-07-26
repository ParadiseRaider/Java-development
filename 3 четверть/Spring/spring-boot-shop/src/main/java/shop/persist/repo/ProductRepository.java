package shop.persist.repo;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import shop.persist.entity.Product;

public interface ProductRepository extends JpaRepositoryImplementation<Product, Long> {

}
