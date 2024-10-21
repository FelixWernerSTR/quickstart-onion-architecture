package pl.splaw.onionarchitecture.jeedependencyinjection.repository;

import pl.splaw.onionarchitecture.databaserepository.mapper.DomainToEntityMapper;
import pl.splaw.onionarchitecture.databaserepository.repository.WorkLogEntityRepository;
import pl.splaw.onionarchitecture.databaserepository.repository.WorkerEntityRepository;
import pl.splaw.onionarchitecture.databaserepository.repository.impl.WorkLogRepository;
import pl.splaw.onionarchitecture.databaserepository.repository.impl.WorkerRepository;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @ApplicationScoped deactivated because all components to inject are allready = @ApplicationScoped
 *
 * @author Bartek <https://github.com/splaw88>
 */
//@ApplicationScoped
public class RepositoryFactory {
  
  // @Inject
  DomainToEntityMapper domainToEntityMapper;
  
  // @Inject
  WorkerEntityRepository workerEntityRepository;
  
  // @Inject
  WorkLogEntityRepository workLogEntityRepository;
  
  // @Produces
  // @ApplicationScoped
  public WorkerRepositoryI workerRepository() {
    return new WorkerRepository(domainToEntityMapper, workerEntityRepository, workLogEntityRepository);
  }
  
  // @Produces
  // @ApplicationScoped
  public WorkLogRepositoryI workLogRepository() {
    return new WorkLogRepository(domainToEntityMapper, workLogEntityRepository);
  }
}
