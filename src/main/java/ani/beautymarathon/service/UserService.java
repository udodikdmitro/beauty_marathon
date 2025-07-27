package ani.beautymarathon.service;

import ani.beautymarathon.entity.User;
import ani.beautymarathon.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User newUser) {
        User user = userRepository.save(newUser);
        log.info("User saved: {} ", user);
        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }
}
