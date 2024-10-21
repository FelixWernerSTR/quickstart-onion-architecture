package pl.splaw.onionarchitecture.quarkusrestservices.controller;

import java.util.List;

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
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.quarkusrestservices.dto.worklog.NewWorkLogRequest;
import pl.splaw.onionarchitecture.quarkusrestservices.dto.worklog.UpdateWorkLogRequest;
import pl.splaw.onionarchitecture.quarkusrestservices.dto.worklog.WorkLogResponse;
import pl.splaw.onionarchitecture.quarkusrestservices.mapper.DomainToResponseMapper;
import pl.splaw.onionarchitecture.quarkusrestservices.mapper.RequestsToDomainMapper;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Path("/work-log")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkLogController {
  
  @Inject
  private WorkLogServiceI workLogService;
  @Inject
  private WorkerServiceI workerService;
  @Inject
  private DomainToResponseMapper domainToResponseMapper;
  @Inject
  private RequestsToDomainMapper requestsToDomainMapper;
  
  @PUT
  public WorkLogResponse createNewWorkLog(NewWorkLogRequest request) throws BaseException {
    Worker worker = workerService.findWorkerByLogin(request.getAssociatedWorkerLogin());
    return domainToResponseMapper.mapToWorkLogResponse(workLogService.logWork(requestsToDomainMapper.newWorkLogRequestToWorkLog(request, worker)));
  }
  
  @POST
  @Path("/{id}")
  public WorkLogResponse editWorkLog(@PathParam("id") Long workLogId, UpdateWorkLogRequest request) throws BaseException {
    return domainToResponseMapper.mapToWorkLogResponse(workLogService.editWorkLog(workLogId, requestsToDomainMapper.updateWorkLogRequestToWorkLog(request)));
  }
  
  @DELETE
  @Path("/{id}")
  public WorkLogResponse delete(@PathParam("id") Long workLogId) throws BaseException {
    return domainToResponseMapper.mapToWorkLogResponse(workLogService.deleteWorkLog(workLogId));
  }
  
  @GET
  @Path("/{login}")
  public List<WorkLogResponse> listWorkLogForWorkerLogin(@PathParam("login") String workerLogin) throws BaseException {
    Worker worker = workerService.findWorkerByLogin(workerLogin);
    return domainToResponseMapper.mapToWorkLogResponseList(workLogService.reportWorkerWork(worker));
  }
  
}
