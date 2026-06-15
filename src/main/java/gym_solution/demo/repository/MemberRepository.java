package gym_solution.demo.repository;

import gym_solution.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUser_Email(String email);

    List<Member> findByTrainer_TrainerId(Long trainerId);
}
