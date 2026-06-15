package gym_solution.demo.repository;

import gym_solution.demo.model.BodyMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyMetricRepository extends JpaRepository<BodyMetric, Long> {

    /** A member's measurements, oldest first (good for charting). */
    List<BodyMetric> findByMember_MemberIdOrderByDateAsc(Long memberId);
}
