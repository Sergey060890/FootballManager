package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Substitution;

/**
 * SubstitutionRepository
 */
@Repository
public interface SubstitutionRepository extends JpaRepository<Substitution, Integer> {
}
