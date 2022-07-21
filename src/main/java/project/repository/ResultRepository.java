package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Result;
/**
 * ResultRepository
 */
@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
}
