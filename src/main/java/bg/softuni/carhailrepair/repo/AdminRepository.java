package bg.softuni.carhailrepair.repo;

import bg.softuni.carhailrepair.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Object> findByUserId(Long userId);
}
