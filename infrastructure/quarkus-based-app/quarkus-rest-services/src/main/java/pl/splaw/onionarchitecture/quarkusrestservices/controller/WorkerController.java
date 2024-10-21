package pl.splaw.onionarchitecture.quarkusrestservices.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.quarkusrestservices.dto.worker.EditWorkerRequest;
import pl.splaw.onionarchitecture.quarkusrestservices.dto.worker.NewWorkerRequest;
import pl.splaw.onionarchitecture.quarkusrestservices.mapper.RequestsToDomainMapper;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Path("/worker")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkerController {
  
  @Inject
  private WorkerServiceI workerService;
  @Inject
  private RequestsToDomainMapper requestsToDomainMapper;
  
  @PUT
  public Worker createNewWorker(NewWorkerRequest request) throws BaseException {
    return workerService.createWorker(requestsToDomainMapper.newWorkerRequestToWorker(request));
  }
  
  @POST
  @Path("/{login}")
  public Worker editWorker(@PathParam("login") String workerLogin, EditWorkerRequest request) throws BaseException {
    return workerService.editWorker(workerLogin, requestsToDomainMapper.editWorkerRequestToWorker(request));
  }
  
  @DELETE
  @Path("/{login}")
  public Worker delete(@PathParam("login") String workerLogin) throws BaseException {
    return workerService.deleteWorker(workerLogin);
  }
  
  @GET
  @Path("/{login}")
  public Worker findByLogin(@PathParam("login") String workerLogin) throws BaseException {
    return workerService.findWorkerByLogin(workerLogin);
  }
  
}
