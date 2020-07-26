package ru.geekbrains.repo;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import ru.geekbrains.model.Product;

public interface ProductRepository extends JpaRepositoryImplementation<Product, Long> {

}
