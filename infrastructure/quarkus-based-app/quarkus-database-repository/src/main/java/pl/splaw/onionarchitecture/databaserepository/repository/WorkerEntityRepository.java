package pl.splaw.onionarchitecture.databaserepository.repository;

import org.springframework.data.repository.CrudRepository;

import pl.splaw.onionarchitecture.databaserepository.model.WorkerEntity;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkerEntityRepository extends CrudRepository<WorkerEntity, Long> {
  
  WorkerEntity findWorkerEntityByLogin(String login);
}
