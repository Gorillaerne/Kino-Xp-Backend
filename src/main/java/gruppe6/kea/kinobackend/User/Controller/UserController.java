package gruppe6.kea.kinobackend.User.Controller;


import gruppe6.kea.kinobackend.DTO.LoginRequestDTO;
import gruppe6.kea.kinobackend.DTO.LoginResponseDTO;
import gruppe6.kea.kinobackend.Models.User;
import gruppe6.kea.kinobackend.User.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//Endpoint sådan her!!!!!!!!!!!!!!
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserById(@PathVariable int userID){
        return new ResponseEntity<>(userService.findById(userID),HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO login){
          return userService.login(login);
    }

    @PostMapping("")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createNewUser(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userID){
        userService.deleteUser(userID);
        return ResponseEntity.noContent().build();
    }


}
