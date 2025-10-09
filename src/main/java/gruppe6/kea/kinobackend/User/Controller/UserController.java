package gruppe6.kea.kinobackend.User.Controller;


import gruppe6.kea.kinobackend.Cinema.Service.CinemaService;
import gruppe6.kea.kinobackend.DTO.CreateUserDTO;
import gruppe6.kea.kinobackend.DTO.LoginRequestDTO;
import gruppe6.kea.kinobackend.DTO.LoginResponseDTO;
import gruppe6.kea.kinobackend.Models.Cinema;
import gruppe6.kea.kinobackend.Models.User;
import gruppe6.kea.kinobackend.User.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//Endpoint sådan her!!!!!!!!!!!!!!
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final CinemaService cinemaService;

    public UserController(UserService userService, CinemaService cinemaService) {
        this.userService = userService;
        this.cinemaService = cinemaService;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserById(@PathVariable int userID){
        return new ResponseEntity<>(userService.findById(userID),HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO login){
          return userService.login(login);
    }

    @PostMapping("")
    public ResponseEntity<User> createNewUser(@RequestBody CreateUserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAuthlevel(dto.getAuthlevel());

        // fetch managed Cinema entities from DB
        List<Cinema> cinemas = dto.getCinemaIds().stream()
                .map(cinemaService::findById)
                .collect(Collectors.toList());

        // Add user to each cinema's userList (owning side)
        for (Cinema cinema : cinemas) {
            cinema.getUserList().add(user);
        }

        user.setCinemas(cinemas);

        User savedUser = userService.createNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userID){
        userService.deleteUser(userID);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
