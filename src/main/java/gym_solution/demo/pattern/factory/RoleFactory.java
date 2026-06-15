package gym_solution.demo.pattern.factory;

import gym_solution.demo.model.Role;

/**
 * Factory abstraction for building {@link Role} instances.
 */
public interface RoleFactory {

    /**
     * Build a (non-persisted) Role with the given name.
     *
     * @param roleName the role name, e.g. "ADMIN", "MEMBER", "TRAINER"
     * @return a new Role instance
     */
    Role addRole(String roleName);
}
