package shop;

import org.hibernate.cfg.Configuration;
import shop.persist.Cart;
import shop.persist.Product;
import shop.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main test = new Main();
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emFactory.createEntityManager();

        User user1 = new User(null, "Alex","123321");
        User user2 = new User(null, "Ivan","123321");
        User user3 = new User(null, "Bober","123321");

        Product product1 = new Product(null, "TV", 1000);
        Product product2 = new Product(null, "PC", 2000);
        Product product3 = new Product(null, "HDD", 500);

        Cart cart1 = new Cart(null, user1, product1, product1.getCost());
        Cart cart2 = new Cart(null, user1, product2, product2.getCost());
        Cart cart3 = new Cart(null, user1, product3, product3.getCost());
        Cart cart4 = new Cart(null, user2, product1, product1.getCost());
        Cart cart5 = new Cart(null, user3, product2, product2.getCost());

        em.getTransaction().begin();
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.persist(cart1);
        em.persist(cart2);
        em.persist(cart3);
        em.persist(cart4);
        em.persist(cart5);
        em.getTransaction().commit();
        em.clear();

        System.out.println(test.getProductsFromUser(em,"Ivan"));
        System.out.println(test.getUsersFromProduct(em, "TV"));

        test.deleteUser(em,"Bober");
        test.deleteProduct(em,"HDD");

        System.out.println(test.getCostProductFromUser(em,"Alex"));
    }

    public String getProductsFromUser(EntityManager em, String name) {
        StringBuilder sb = new StringBuilder();
        List<Cart> carts = em.createQuery("SELECT c from Cart c where c.user.name = :name", Cart.class)
            .setParameter("name", name).getResultList();
        for (Cart c: carts) {
            sb.append(c.getProduct().getName()+" ");
        }
        return sb.toString();
    }

    public String getUsersFromProduct(EntityManager em, String name) {
        StringBuilder sb = new StringBuilder();
        List<Cart> carts = em.createQuery("SELECT c from Cart c where c.product.name = :name", Cart.class)
                .setParameter("name", name).getResultList();
        for (Cart c: carts) {
            sb.append(c.getUser().getName()+" ");
        }
        return sb.toString();
    }

    public String getCostProductFromUser(EntityManager em, String nameUser) {
        StringBuilder sb = new StringBuilder();
        sb.append("User = "+nameUser+"\n");
        List<Cart> carts = em.createQuery("SELECT c from Cart c where c.user.name = :nameUser", Cart.class)
                .setParameter("nameUser", nameUser).getResultList();
        for (Cart c: carts) {
            sb.append(c.getProduct().getName()+": "+c.getCost()+"\n");
        }
        return sb.toString();
    }

    public void deleteUser(EntityManager em, String name) {
        List<User> users = em.createNamedQuery("deleteUser", User.class)
                .setParameter("name", name).getResultList();
        if (users!=null) {
            for (User u: users) {
                if (u.getName().equals(name)) {
                    em.getTransaction().begin();
                    em.remove(u);
                    em.getTransaction().commit();
                }
            }
        }
    }

    public void deleteProduct(EntityManager em, String name) {
        List<Product> products = em.createNamedQuery("deleteProduct", Product.class)
                .setParameter("name", name).getResultList();
        if (products!=null) {
            for (Product p: products) {
                if (p.getName().equals(name)) {
                    em.getTransaction().begin();
                    em.remove(p);
                    em.getTransaction().commit();
                }
            }
        }
    }
}
