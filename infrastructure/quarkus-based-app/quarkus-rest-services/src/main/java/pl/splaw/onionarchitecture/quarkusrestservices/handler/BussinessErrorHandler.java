package pl.splaw.onionarchitecture.quarkusrestservices.handler;

import java.util.stream.Stream;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogExistException;

@Provider
public class BussinessErrorHandler implements ExceptionMapper<BaseException> {
  
  @Override
  public Response toResponse(BaseException ex) {
    ResponseBody bodyOfResponse;
    try {
      bodyOfResponse = new ResponseBody(ErrorMapping.getByClass(ex.getClass()).getCode(), ex.getMessage());
    } catch (UnhandledBaseException e) {
      bodyOfResponse = new ResponseBody("300", ex.getMessage());
    }
    return Response.status(Response.Status.BAD_REQUEST).entity(bodyOfResponse).build();
  }
  
  private static class ResponseBody {
    private String code;
    private String msg;
    
    public String getCode() {
      return code;
    }
    
    public String getMsg() {
      return msg;
    }
    
    public ResponseBody(String code, String msg) {
      this.code = code;
      this.msg = msg;
    }
  }
  
  private enum ErrorMapping {
    WORKER_DONT_EXISTS(WorkerDontExistsException.class, "100"),
    WORKER_EXISTS(WorkerExistsException.class, "101"),
    WORK_LOG_DONT_EXIST(WorkLogDontExistException.class, "200"),
    WORK_LOG_EXIST(WorkLogExistException.class, "201");
    
    private final Class<?> clazz;
    private final String code;
    
    ErrorMapping(Class<?> clazz, String code) {
      this.clazz = clazz;
      this.code = code;
    }
    
    public static ErrorMapping getByClass(Class<?> clazz) throws UnhandledBaseException {
      return Stream.of(ErrorMapping.values())
          .filter((errorMapping) -> errorMapping.getClazz().equals(clazz))
          .findFirst()
          .orElseThrow(UnhandledBaseException::new);
    }
    
    public Class<?> getClazz() {
      return clazz;
    }
    
    public String getCode() {
      return code;
    }
  }
  
  private static class UnhandledBaseException extends Exception {
  }
}
