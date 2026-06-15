package gym_solution.demo.repository;

import gym_solution.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {

    Optional<Trainer> findByUser_Email(String email);
}
