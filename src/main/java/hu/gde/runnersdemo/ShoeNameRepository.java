package hu.gde.runnersdemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeNameRepository extends JpaRepository<ShoeNameEntity,Long > {
}
