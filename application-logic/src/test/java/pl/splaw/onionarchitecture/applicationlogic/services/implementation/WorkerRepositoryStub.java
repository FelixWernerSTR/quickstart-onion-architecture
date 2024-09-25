package pl.splaw.onionarchitecture.applicationlogic.services.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerRepositoryStub implements WorkerRepositoryI {
  
  public static final String EXISTING_LOGIN = "login";
  public static final String NAME = "name";
  public static final String SURNAME = "surname";
  public static final String EMAIL = "email";
  
  public static final String NON_EXISTING_LOGIN = "non_login";
  public static final String OTHER_NAME = "other_name";
  public static final String OTHER_SURNAME = "other_surname";
  public static final String OTHER_EMAIL = "other_email";
  
  private final List<Worker> workerList;
  
  public static Worker exisitngWorker() {
    return new Worker(EXISTING_LOGIN, NAME, SURNAME, EMAIL);
  }
  
  public static Worker nonExisitngWorker() {
    return new Worker(NON_EXISTING_LOGIN, OTHER_NAME, OTHER_SURNAME, OTHER_EMAIL);
  }
  
  public WorkerRepositoryStub(Worker... workers) {
    workerList = new ArrayList<>();
    workerList.addAll(Arrays.asList(workers));
  }
  
  @Override
  public Worker findWorkerByLogin(String login) {
    return workerList.stream().filter((worker) -> worker.getLogin().equals(login)).findFirst().orElse(null);
  }
  
  @Override
  public Worker saveWorker(Worker worker) {
    workerList.add(worker);
    return worker;
  }
  
  @Override
  public Worker updateWorker(Worker worker) {
    List<Worker> tempList = workerList.stream().filter((workerInList) -> !workerInList.getLogin().equals(worker.getLogin())).collect(Collectors.toList());
    workerList.clear();
    workerList.addAll(tempList);
    workerList.add(worker);
    return worker;
  }
  
  @Override
  public Worker deleteWorker(Worker worker) {
    List<Worker> tempList = workerList.stream().filter((workerInList) -> !workerInList.getLogin().equals(worker.getLogin())).collect(Collectors.toList());
    workerList.clear();
    workerList.addAll(tempList);
    return worker;
  }
  
  /**
   * @return the workerList
   */
  public List<Worker> getWorkerList() {
    return workerList;
  }
  
}
