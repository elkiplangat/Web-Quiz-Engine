package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        var duplicateEmail = userRepository.findByEmail(user.getEmail()).isPresent();
        if (duplicateEmail){
            throw new IllegalStateException("duplicate email");

        }else {
            userRepository.save(user);
        }
    }
    public User updateUser(User user){
        return userRepository.save(user);
    }
}
