package shop.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.persist.entity.Product;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCostLessThanEqual(BigDecimal maxCost, Pageable pageable);
    Page<Product> findByCostBetween(BigDecimal minCost, BigDecimal maxCost, Pageable pageable);
    Page<Product> findByCostGreaterThanEqual(BigDecimal minCost, Pageable pageable);

    Page<Product> findByTitleLikeAndCostLessThanEqual(String names, BigDecimal maxCost, Pageable pageable);
    Page<Product> findByTitleLikeAndCostBetween(String names, BigDecimal minCost, BigDecimal maxCost, Pageable pageable);
    Page<Product> findByTitleLikeAndCostGreaterThanEqual(String names, BigDecimal maxCost, Pageable pageable);
    Page<Product> findByTitleLike(String names, Pageable pageable);
}
