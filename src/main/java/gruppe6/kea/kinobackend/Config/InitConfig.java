package gruppe6.kea.kinobackend.Config;

import gruppe6.kea.kinobackend.Models.Enums.Authlevel;
import gruppe6.kea.kinobackend.Models.Enums.Category;
import gruppe6.kea.kinobackend.Models.User;
import gruppe6.kea.kinobackend.User.Service.UserService;
import jakarta.persistence.Column;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitConfig implements CommandLineRunner {

    private final UserService userService;

    public InitConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            User user1 = new User();
            user1.setAuthlevel(Authlevel.ADMIN);
            user1.setUsername("admin");
            user1.setPassword("123");
            userService.createNewUser(user1);

            //Kan slettes når vi har lavet opret nye brugere panel
            User user2 = new User();
            user2.setAuthlevel(Authlevel.EMPLOYEE);
            user2.setUsername("employee");
            user2.setPassword("123");
            userService.createNewUser(user2);


            User user3 = new User();
            user3.setAuthlevel(Authlevel.EMPLOYEE);
            user3.setUsername("longline");
            user3.setPassword("123");
            userService.createNewUser(user3);

            User user4 = new User();
            user4.setAuthlevel(Authlevel.EMPLOYEE);
            user4.setUsername("gustavo");
            user4.setPassword("123");
            userService.createNewUser(user4);

            User user5 = new User();
            user5.setAuthlevel(Authlevel.EMPLOYEE);
            user5.setUsername("jones");
            user5.setPassword("123");
            userService.createNewUser(user5);
        } catch (Exception e) {
            System.out.println("Admin bruger findes allerede");
        }
    }
}
