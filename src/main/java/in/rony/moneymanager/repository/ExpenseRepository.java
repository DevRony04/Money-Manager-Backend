package in.rony.moneymanager.repository;

import in.rony.moneymanager.entity.ExpenseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    // Find all expenses by profile ID, ordered by date descending
    List<ExpenseEntity> findByProfileIdOrderByDateDesc(Long profileId);

    // Find the top 5 most recent expenses by profile ID
    List<ExpenseEntity> findTop5ByProfileIdOrderByDateDesc(Long profileId);

    @Query("SELECT SUM(e.amount) FROM ExpenseEntity e WHERE e.profile.id = :profileId")
    BigDecimal findTotalExpenseByProfileId(@Param("profileId") Long profileId);

    // Search expenses by profile ID, date range, and keyword in name (case-insensitive), with sorting
    List<ExpenseEntity> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
            Long profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );
    // Find expenses by profile ID within a date range
    List<ExpenseEntity> findByProfileIdAndDateBetween(Long profileId, LocalDate startDate, LocalDate endDate);

    // Find expenses by profile ID and exact date
    List<ExpenseEntity> findByProfileIdAndDate(Long profileId, LocalDate date);

}
