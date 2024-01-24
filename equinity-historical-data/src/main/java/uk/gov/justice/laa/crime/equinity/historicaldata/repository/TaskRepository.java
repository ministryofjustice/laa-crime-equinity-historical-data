package uk.gov.justice.laa.crime.equinity.historicaldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.gov.justice.laa.crime.equinity.historicaldata.model.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
  //  List<Tasks> findByID(Long ID);
}

