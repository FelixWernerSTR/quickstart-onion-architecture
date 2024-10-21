package pl.splaw.onionarchitecture.jeedependencyinjection.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkLogService;
import pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerService;
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

@ApplicationScoped
public class ApplicationLogicFactory {
  
  @Inject
  WorkLogRepositoryI workLogRepositoryI;
  
  @Inject
  WorkerRepositoryI workerRepositoryI;
  
  @Produces
  @ApplicationScoped
  public WorkLogServiceI workLogService() {
    return new WorkLogService(workLogRepositoryI, workerRepositoryI);
  }
  
  @Produces
  @ApplicationScoped
  public WorkerServiceI workerService() {
    return new WorkerService(workerRepositoryI);
  }
}