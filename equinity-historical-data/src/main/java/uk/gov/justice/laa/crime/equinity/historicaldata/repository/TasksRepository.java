package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.gov.justice.laa.crime.equinity.historicaldata.model.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
  //  List<Tasks> findByID(Long ID);
}

