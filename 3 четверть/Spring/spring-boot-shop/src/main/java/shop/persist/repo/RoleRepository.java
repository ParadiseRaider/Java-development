package shop.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.persist.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
