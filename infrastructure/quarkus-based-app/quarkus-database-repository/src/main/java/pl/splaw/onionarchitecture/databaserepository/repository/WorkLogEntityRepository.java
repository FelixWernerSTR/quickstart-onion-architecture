package pl.splaw.onionarchitecture.databaserepository.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.splaw.onionarchitecture.databaserepository.model.WorkLogEntity;
import pl.splaw.onionarchitecture.databaserepository.model.WorkerEntity;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkLogEntityRepository extends CrudRepository<WorkLogEntity, Long> {
  
  WorkLogEntity findWorkLogEntityById(Long id);
  
  List<WorkLogEntity> findWorkLogEntityByAssociatedWorker(WorkerEntity associatedWorker);
  
  Long deleteByAssociatedWorker_Login(@Param("login") String login);
}
