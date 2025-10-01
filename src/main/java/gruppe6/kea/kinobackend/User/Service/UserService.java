package gruppe6.kea.kinobackend.User.Service;


import gruppe6.kea.kinobackend.DTO.LoginRequestDTO;
import gruppe6.kea.kinobackend.DTO.LoginResponseDTO;
import gruppe6.kea.kinobackend.Models.User;
import gruppe6.kea.kinobackend.User.Repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public LoginResponseDTO login(LoginRequestDTO login) {
        User dbUser = userRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (dbUser == null || !checkPassword(login.getPassword(), dbUser.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
            return new LoginResponseDTO(dbUser.getId(),dbUser.getUsername(),dbUser.getAuthlevel(),dbUser.getCinemas());
    }

    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Checker om username findes
        if (userRepository.findByUsername(user.getUsername()).isEmpty()){
            return userRepository.save(user);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new BadCredentialsException("User with id: " + id + " doesnt exist"));
    }
}
