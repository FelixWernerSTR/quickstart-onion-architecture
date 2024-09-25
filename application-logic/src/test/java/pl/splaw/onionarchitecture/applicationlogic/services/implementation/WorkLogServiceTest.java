package pl.splaw.onionarchitecture.applicationlogic.services.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.exisitngWorker;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.nonExisitngWorker;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogStartDateException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogTimeSpentException;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkLogServiceTest {
  
  private WorkLogService workLogService;
  private WorkerRepositoryI workerRepositoryI;
  private WorkLogRepositoryI workLogRepositoryI;
  
  @BeforeEach
  public void init() {
    workerRepositoryI = new WorkerRepositoryStub(exisitngWorker());
    workLogRepositoryI = new WorkLogRepositoryStub(WorkLogRepositoryStub.workLogWithExistingWorker(1l));
    workLogService = new WorkLogService(workLogRepositoryI, workerRepositoryI);
  }
  
  @Test
  public void logWork_Success() throws BaseException {
    
    WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l);
    WorkLog result = workLogService.logWork(newWorkLog);
    
    assertThat(newWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
    assertThat(newWorkLog.getDescription()).isEqualTo(result.getDescription());
    assertThat(newWorkLog.getStartDate()).isEqualTo(result.getStartDate());
    assertThat(newWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
    assertThat(newWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
    assertThat(2).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    
  }
  
  @Test()
  public void logWork_WorkerDontExist() throws BaseException {
    
    WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithNonExistingWorker(2l);
    
    WorkerDontExistsException exception = Assertions.assertThrows(WorkerDontExistsException.class, () -> {
      WorkLog result = workLogService.logWork(newWorkLog);
      assertThat(newWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
      assertThat(newWorkLog.getDescription()).isEqualTo(result.getDescription());
      assertThat(newWorkLog.getStartDate()).isEqualTo(result.getStartDate());
      assertThat(newWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
      assertThat(newWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
      assertThat(2).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    });
    
  }
  
  @Test()
  public void logWork_StartDateFromFuture() throws BaseException {
    
    WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, LocalDate.MAX);
    
    WorkLogStartDateException exception = Assertions.assertThrows(WorkLogStartDateException.class, () -> {
      WorkLog result = workLogService.logWork(newWorkLog);
      
      assertThat(newWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
      assertThat(newWorkLog.getDescription()).isEqualTo(result.getDescription());
      assertThat(newWorkLog.getStartDate()).isEqualTo(result.getStartDate());
      assertThat(newWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
      assertThat(newWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
      assertThat(2).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
      
    });
    
  }
  
  @Test()
  public void logWork_NegativeTimeSpent() throws BaseException {
    
    WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, -1);
    
    WorkLogTimeSpentException exception = Assertions.assertThrows(WorkLogTimeSpentException.class, () -> {
      WorkLog result = workLogService.logWork(newWorkLog);
      
      assertThat(newWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
      assertThat(newWorkLog.getDescription()).isEqualTo(result.getDescription());
      assertThat(newWorkLog.getStartDate()).isEqualTo(result.getStartDate());
      assertThat(newWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
      assertThat(newWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
      assertThat(2).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
      
    });
  }
  
  @Test
  public void editWorkLog_Success() throws BaseException {
    
    WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l);
    WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);
    
    assertThat(Long.valueOf(1l)).isEqualTo(result.getWorkLogId());
    assertThat(editedWorkLog.getDescription()).isEqualTo(result.getDescription());
    assertThat(editedWorkLog.getStartDate()).isEqualTo(result.getStartDate());
    assertThat(editedWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
    assertThat(editedWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
    assertThat(1).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    
  }
  
  @Test()
  public void editWorkLog_WorkLogDontExist() throws BaseException {
    
    WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithNonExistingWorker(2l);
    
    WorkLogDontExistException exception = Assertions.assertThrows(WorkLogDontExistException.class, () -> {
      WorkLog result = workLogService.editWorkLog(2l, editedWorkLog);
      
      assertThat(editedWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
      assertThat(editedWorkLog.getDescription()).isEqualTo(result.getDescription());
      assertThat(editedWorkLog.getStartDate()).isEqualTo(result.getStartDate());
      assertThat(editedWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
      assertThat(editedWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
      assertThat(2).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    });
  }
  
  @Test()
  public void editWorkLog_StartDateFromFuture() throws BaseException {
    
    WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, LocalDate.MAX);
    
    WorkLogStartDateException exception = Assertions.assertThrows(WorkLogStartDateException.class, () -> {
      
      WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);
      
      assertThat(editedWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
      assertThat(editedWorkLog.getDescription()).isEqualTo(result.getDescription());
      assertThat(editedWorkLog.getStartDate()).isEqualTo(result.getStartDate());
      assertThat(editedWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
      assertThat(editedWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
      assertThat(2).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    });
    
  }
  
  @Test()
  public void editWorkLog_NegativeTimeSpent() throws BaseException {
    
    WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, -1);
    
    WorkLogTimeSpentException exception = Assertions.assertThrows(WorkLogTimeSpentException.class, () -> {
      
      WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);
      
      assertThat(editedWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
      assertThat(editedWorkLog.getDescription()).isEqualTo(result.getDescription());
      assertThat(editedWorkLog.getStartDate()).isEqualTo(result.getStartDate());
      assertThat(editedWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
      assertThat(editedWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
      assertThat(2).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    });
    
  }
  
  @Test
  public void deleteWorkLog_Success() throws BaseException {
    
    WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
    WorkLog result = workLogService.deleteWorkLog(1l);
    
    assertThat(existingWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
    assertThat(existingWorkLog.getDescription()).isEqualTo(result.getDescription());
    assertThat(existingWorkLog.getStartDate()).isEqualTo(result.getStartDate());
    assertThat(existingWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
    assertThat(existingWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
    assertThat(getWorkLogRepositoryStub().getWorkLogList().isEmpty()).isTrue();
    
  }
  
  @Test()
  public void deleteWorkLog_WorkLogDontExist() throws BaseException {
    WorkLogDontExistException exception = Assertions.assertThrows(WorkLogDontExistException.class, () -> workLogService.deleteWorkLog(2l));
  }
  
  @Test
  public void findWorkLogById_Success() throws BaseException {
    
    WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
    WorkLog result = workLogService.findWorkLogById(1l);
    
    assertThat(existingWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
    assertThat(existingWorkLog.getDescription()).isEqualTo(result.getDescription());
    assertThat(existingWorkLog.getStartDate()).isEqualTo(result.getStartDate());
    assertThat(existingWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
    assertThat(existingWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
    assertThat(1).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    
  }
  
  @Test()
  public void findWorkLogById_WorkLogDontExist() throws BaseException {
    WorkLogDontExistException exception = Assertions.assertThrows(WorkLogDontExistException.class, () -> workLogService.findWorkLogById(2l));
  }
  
  @Test
  public void reportWorkerWorkLogs_Success() throws BaseException {
    
    WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
    List<WorkLog> resultList = workLogService.reportWorkerWork(exisitngWorker());
    
    assertThat(1).isEqualTo(resultList.size());
    
    WorkLog result = resultList.get(0);
    
    assertThat(existingWorkLog.getWorkLogId()).isEqualTo(result.getWorkLogId());
    assertThat(existingWorkLog.getDescription()).isEqualTo(result.getDescription());
    assertThat(existingWorkLog.getStartDate()).isEqualTo(result.getStartDate());
    assertThat(existingWorkLog.getTimeSpentInSeconds()).isEqualTo(result.getTimeSpentInSeconds());
    assertThat(existingWorkLog.getAssociatedWorker().getLogin()).isEqualTo(result.getAssociatedWorker().getLogin());
    assertThat(1).isEqualTo(getWorkLogRepositoryStub().getWorkLogList().size());
    
  }
  
  @Test
  public void reportWorkerWorkLogs_NoWorkLogs() throws BaseException {
    List<WorkLog> resultList = workLogService.reportWorkerWork(nonExisitngWorker());
    assertThat(resultList.isEmpty()).isTrue();
  }
  
  private WorkLogRepositoryStub getWorkLogRepositoryStub() {
    return (WorkLogRepositoryStub) workLogRepositoryI;
  }
  
}
