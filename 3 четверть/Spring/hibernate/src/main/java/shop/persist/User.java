package shop.persist;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
@NamedQuery(name = "deleteUser", query = "from User u where u.name = :name")
public class User {
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
    private String password;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> carts;


    public User() { }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
