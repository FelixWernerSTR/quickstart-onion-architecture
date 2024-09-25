package pl.splaw.onionarchitecture.applicationlogic.services.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.EMAIL;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.EXISTING_LOGIN;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.NAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.NON_EXISTING_LOGIN;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.OTHER_EMAIL;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.OTHER_NAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.OTHER_SURNAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.SURNAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.exisitngWorker;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.nonExisitngWorker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerExistsException;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerServiceTest {
  
  private WorkerService workerService;
  private WorkerRepositoryI workerRepositoryI;
  
  @BeforeEach
  public void init() {
    workerRepositoryI = new WorkerRepositoryStub(exisitngWorker());
    workerService = new WorkerService(workerRepositoryI);
  }
  
  @Test
  public void findWorker_Success() {
    try {
      Worker result = workerService.findWorkerByLogin(EXISTING_LOGIN);
      assertThat(EXISTING_LOGIN).isEqualTo(result.getLogin());
      assertThat(NAME).isEqualTo(result.getName());
      assertThat(SURNAME).isEqualTo(result.getSurname());
      assertThat(EMAIL).isEqualTo(result.getEmail());
      
    } catch (Exception e) {
      fail(String.format("Error in deleting worker: %s", e.getMessage()));
    }
  }
  
  @Test()
  public void findWorker_WorkerDontExist() throws BaseException {
    
    WorkerDontExistsException exception = Assertions.assertThrows(WorkerDontExistsException.class, () -> workerService.findWorkerByLogin(NON_EXISTING_LOGIN));
    
  }
  
  @Test
  public void createWorker_Success() {
    try {
      Worker newWorker = nonExisitngWorker();
      Worker result = workerService.createWorker(newWorker);
      assertThat(newWorker.getLogin()).isEqualTo(result.getLogin());
      assertThat(newWorker.getName()).isEqualTo(result.getName());
      assertThat(newWorker.getSurname()).isEqualTo(result.getSurname());
      assertThat(newWorker.getEmail()).isEqualTo(result.getEmail());
      assertThat(2).isEqualTo(getWorkerRepositoryStub().getWorkerList().size());
    } catch (Exception e) {
      fail(String.format("Error in creating worker: %s", e.getMessage()));
    }
  }
  
  @Test()
  public void createWorker_WorkerExist() throws BaseException {
    WorkerExistsException exception = Assertions.assertThrows(WorkerExistsException.class, () -> workerService.createWorker(exisitngWorker()));
  }
  
  @Test
  public void editWorker_Success() {
    try {
      Worker editedWorker = nonExisitngWorker();
      Worker result = workerService.editWorker(EXISTING_LOGIN, editedWorker);
      assertThat(EXISTING_LOGIN).isEqualTo(result.getLogin());
      assertThat(OTHER_NAME).isEqualTo(result.getName());
      assertThat(OTHER_SURNAME).isEqualTo(result.getSurname());
      assertThat(OTHER_EMAIL).isEqualTo(result.getEmail());
      assertThat(1).isEqualTo(getWorkerRepositoryStub().getWorkerList().size());
    } catch (Exception e) {
      fail(String.format("Error in editing worker: %s", e.getMessage()));
    }
  }
  
  @Test()
  public void editWorker_WorkerDontExist() throws BaseException {
    WorkerDontExistsException exception = Assertions.assertThrows(WorkerDontExistsException.class, () -> workerService.editWorker(NON_EXISTING_LOGIN, null));
  }
  
  @Test
  public void deleteWorker_Success() {
    try {
      Worker result = workerService.deleteWorker(EXISTING_LOGIN);
      assertThat(EXISTING_LOGIN).isEqualTo(result.getLogin());
      assertThat(NAME).isEqualTo(result.getName());
      assertThat(SURNAME).isEqualTo(result.getSurname());
      assertThat(EMAIL).isEqualTo(result.getEmail());
      assertThat(getWorkerRepositoryStub().getWorkerList().isEmpty());
      
    } catch (Exception e) {
      fail(String.format("Error in deleting worker: %s", e.getMessage()));
    }
  }
  
  @Test()
  public void deleteWorker_WorkerDontExist() throws BaseException {
    WorkerDontExistsException exception = Assertions.assertThrows(WorkerDontExistsException.class, () -> workerService.deleteWorker(NON_EXISTING_LOGIN));
  }
  
  private WorkerRepositoryStub getWorkerRepositoryStub() {
    return (WorkerRepositoryStub) workerRepositoryI;
  }
  
}
