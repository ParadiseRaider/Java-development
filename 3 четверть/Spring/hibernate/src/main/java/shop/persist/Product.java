package shop.persist;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQuery(name = "deleteProduct", query = "from Product p where p.name = :name")
public class Product {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(length = 128, unique = true, nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(length = 128)
    private Integer cost;

    @Getter
    @Setter
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> carts;

    public Product() { }

    public Product(Long id, String name, Integer cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
}
