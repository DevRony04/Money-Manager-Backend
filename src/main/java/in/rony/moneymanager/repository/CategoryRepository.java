package in.rony.moneymanager.repository;

import in.rony.moneymanager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    // Find all categories by profile ID
    List<CategoryEntity> findByProfileId(Long profileId);

    // Find category by ID and profile ID
   Optional<CategoryEntity> findByIdAndProfileId(Long id, Long profileId);

    // Find categories by type and profile ID
   List<CategoryEntity> findByTypeAndProfileId(String type, Long profileId);

    // Check if a category with the same name exists for a profile
    Boolean existsByNameAndProfileId(String name, Long profileId);
}
