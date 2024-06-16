package com.fse.blog.bootstrap;

import com.fse.blog.repository.CategoryRepository;
import com.fse.blog.repository.RoleRepository;
import com.fse.blog.repository.UserRepository;
import com.fse.blog.model.Category;
import com.fse.blog.model.Role;
import com.fse.blog.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.fse.blog.model.Role.Roles.ROLE_ADMIN;
import static com.fse.blog.model.Role.Roles.ROLE_USER;

/**
 * DataLoader is responsible for initializing data in the application.
 * It implements the CommandLineRunner interface, which allows it to run
 * custom logic when the application starts.
 */
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    /**
     * Constructor for DataLoader.
     *
     * @param userRepository     The repository for managing user data.
     * @param roleRepository     The repository for managing role data.
     * @param passwordEncoder    The password encoder for securely storing passwords.
     * @param categoryRepository The repository for managing category data.
     */
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
    }

    /**
     * This method is used to load the roles, admin data and categories during the application startup.
     * It initializes data if no users exist in the database.
     *
     * @param args Command line arguments (not used in this case).
     * @throws Exception Any exception that occurs during data initialization.
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Loading initial data");
        if (userRepository.count() == 0) {
            //saving roles
            Role adminRole = new Role(ROLE_ADMIN);
            Role userRole = new Role(ROLE_USER);
            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            //saving admin user
            User user = User.builder()
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("secret"))
                    .isAccExpired(false)
                    .isAccLocked(false)
                    .isCredExpired(false)
                    .isEnabled(true)
                    .roles(Set.of(adminRole, userRole))
                    .build();
            userRepository.save(user);

            //saving categories
            Category general = new Category("General");
            Category java = new Category("Java");
            categoryRepository.save(general);
            categoryRepository.save(java);
        }

    }
}
