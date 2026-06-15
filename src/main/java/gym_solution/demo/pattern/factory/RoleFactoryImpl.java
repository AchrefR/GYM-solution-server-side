package gym_solution.demo.pattern.factory;

import gym_solution.demo.model.Role;

/**
 * Singleton implementation of {@link RoleFactory}.
 */
public class RoleFactoryImpl implements RoleFactory {

    private static RoleFactoryImpl instance;

    private RoleFactoryImpl() {
    }

    public static synchronized RoleFactoryImpl getInstance() {
        if (instance == null) {
            instance = new RoleFactoryImpl();
        }
        return instance;
    }

    @Override
    public Role addRole(String roleName) {
        return Role.builder()
                .roleName(roleName)
                .build();
    }
}
