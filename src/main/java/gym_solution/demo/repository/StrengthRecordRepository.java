package gym_solution.demo.repository;

import gym_solution.demo.model.StrengthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrengthRecordRepository extends JpaRepository<StrengthRecord, Long> {

    /** A member's lift records, oldest first (good for charting progression). */
    List<StrengthRecord> findByMember_MemberIdOrderByDateAsc(Long memberId);
}
