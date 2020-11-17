package com.imerish.project.business.repository;

import com.imerish.project.business.domain.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long> {
    List<Worker> findAll();
}
