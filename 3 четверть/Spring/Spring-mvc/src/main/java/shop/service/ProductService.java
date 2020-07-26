package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.persist.entity.Product;
import shop.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }

    public Page<Product> filterByCost(BigDecimal minCost, BigDecimal maxCost, String names, Pageable pageable) {
        if (names==null || names.length()==0) {
            if (minCost != null && maxCost != null) {
                return repository.findByCostBetween(minCost, maxCost, pageable);
            }
            if (minCost != null && maxCost == null) {
                return repository.findByCostGreaterThanEqual(minCost, pageable);
            }
            if (minCost == null && maxCost != null) {
                return repository.findByCostLessThanEqual(maxCost, pageable);
            }
        }

        if (names!=null && names.length()>0) {
            names = "%" + names + "%";
            if (minCost != null && maxCost != null) {
                return repository.findByTitleLikeAndCostBetween(names, minCost, maxCost, pageable);
            }
            if (minCost != null && maxCost == null) {
                return repository.findByTitleLikeAndCostGreaterThanEqual(names, minCost, pageable);
            }
            if (minCost == null && maxCost != null) {
                return repository.findByTitleLikeAndCostLessThanEqual(names, maxCost, pageable);
            }
            if (minCost == null && maxCost == null) {
                return repository.findByTitleLike(names, pageable);
            }
        }
        return repository.findAll(pageable);
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }
}
