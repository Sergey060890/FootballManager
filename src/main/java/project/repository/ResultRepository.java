package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
