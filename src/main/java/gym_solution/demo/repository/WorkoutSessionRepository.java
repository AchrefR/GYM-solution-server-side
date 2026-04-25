package gym_solution.demo.repository;

import gym_solution.demo.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession,Long> {
}
