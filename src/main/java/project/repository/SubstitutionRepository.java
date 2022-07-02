package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Substitution;

public interface SubstitutionRepository extends JpaRepository<Substitution, Integer> {
}
