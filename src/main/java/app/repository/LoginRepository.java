package app.repository;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User,Integer> {
    User findFirstByLoginAndPassword(String login, String password);
}
