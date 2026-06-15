package gym_solution.demo.config;

import gym_solution.demo.model.Role;
import gym_solution.demo.model.SubscriptionType;
import gym_solution.demo.model.User;
import gym_solution.demo.pattern.factory.MemberRole;
import gym_solution.demo.pattern.factory.RoleFactory;
import gym_solution.demo.pattern.factory.RoleFactoryImpl;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.repository.SubscriptionTypeRepository;
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

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //default roles
        RoleFactory roleFactory = RoleFactoryImpl.getInstance();
        for (MemberRole memberRole : MemberRole.values()) {
            String roleName = memberRole.getRoleName();
            if (this.roleRepository.findRoleByRoleName(roleName).isEmpty()) {
                this.roleRepository.save(roleFactory.addRole(roleName));
            }
        }

        //user admin account
        Optional<Role> admin_Role = this.roleRepository.findRoleByRoleName(MemberRole.ADMIN.getRoleName());
        if (admin_Role.isEmpty()) {
            throw new RuntimeException("role is not found !");
        }
        Optional<User> userTest = this.userRepository.findByEmail("admin@admin");
        if (userTest.isEmpty()) {
            User admin = User.builder().email("admin@admin").password(this.passwordEncoder.encode("admin")).role(admin_Role.get()).build();
            this.userRepository.save(admin);
        }

        // default subscription plan catalog
        seedSubscriptionType("Basic", 29, 1,
                "Gym Access,Locker Room,1 Group Class/month");
        seedSubscriptionType("Pro", 59, 1,
                "Unlimited Gym & Classes,Locker Room,Basic App Access,1 PT Session");
        seedSubscriptionType("Elite", 99, 1,
                "All Pro Features,Unlimited PT Sessions,GymFlow AI Coach,Dieting Programs,VIP Sauna");
    }

    private void seedSubscriptionType(String name, double price, int durationMonths, String features) {
        if (this.subscriptionTypeRepository.findByName(name).isEmpty()) {
            this.subscriptionTypeRepository.save(
                    SubscriptionType.builder()
                            .name(name)
                            .price(price)
                            .durationMonths(durationMonths)
                            .features(features)
                            .build());
        }
    }

}
