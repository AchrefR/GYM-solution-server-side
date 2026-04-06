package gym_solution.demo.repository;

import gym_solution.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    Role findRoleByRoleName(String admin);
}
