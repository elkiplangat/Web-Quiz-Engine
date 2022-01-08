package engine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends CrudRepository<User, Long > {
    Optional<User> findByEmail(String email);
}
