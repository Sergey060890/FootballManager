package project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
