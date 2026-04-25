package gym_solution.demo.config;

import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //default roles
        Optional<Role> adminRoleTest = this.roleRepository.findRoleByRoleName("ADMIN");
        if(adminRoleTest.isEmpty())
        {
            Role adminRole = Role.builder().roleName("ADMIN").build();
            this.roleRepository.save(adminRole);
        }
        Optional<Role> memberRoleTest = this.roleRepository.findRoleByRoleName("MEMBER");
        if(memberRoleTest.isEmpty())
        {
            Role memberRole = Role.builder().roleName("MEMBER").build();
            this.roleRepository.save(memberRole);
        }

        Optional<Role> trainerRoleTest = this.roleRepository.findRoleByRoleName("TRAINER");
        if(memberRoleTest.isEmpty())
        {
            Role trainerRole = Role.builder().roleName("TRAINER").build();
            this.roleRepository.save(trainerRole);
        }



        //user admin account
        Optional<Role> admin_Role = this.roleRepository.findRoleByRoleName("ADMIN");
        if(admin_Role.isEmpty())
        {
            throw new RuntimeException("role is not found !");
        }
        Optional<User> userTest = this.userRepository.findByEmail("admin@admin");
        if(userTest.isEmpty())
        {
            User admin = User.builder().email("admin@admin").password(this.passwordEncoder.encode("admin")).role(admin_Role.get()).build();
            this.userRepository.save(admin);
        }


    }


}
