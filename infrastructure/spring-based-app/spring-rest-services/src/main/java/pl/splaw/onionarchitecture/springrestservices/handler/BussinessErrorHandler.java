package pl.splaw.onionarchitecture.springrestservices.handler;

import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogExistException;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@ControllerAdvice
public class BussinessErrorHandler extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(value = { BaseException.class })
  protected ResponseEntity<Object> handleError(BaseException ex, WebRequest request) {
    ResponseBody bodyOfResponse;
    try {
      bodyOfResponse = new ResponseBody(ErrorMaping.getByClass(ex.getClass()).getCode(), ex.getMessage());
    } catch (UnhandledBaseException e) {
      bodyOfResponse = new ResponseBody("300", ex.getMessage());
    }
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
  
  private class ResponseBody {
    
    private String code;
    private String msg;
    
    /**
     * @return the code
     */
    public String getCode() {
      return code;
    }
    
    /**
     * @return the msg
     */
    public String getMsg() {
      return msg;
    }
    
    /**
     * @param code
     * @param msg
     */
    public ResponseBody(String code, String msg) {
      this.code = code;
      this.msg = msg;
    }
  }
  
  private static enum ErrorMaping {
    WORKER_DONT_EXISTS(WorkerDontExistsException.class, "100"),
    WORKER_EXISTS(WorkerExistsException.class, "101"),
    WORK_LOG_DONT_EXIST(WorkLogDontExistException.class, "200"),
    WORK_LOG_EXIST(WorkLogExistException.class, "201");
    
    private final Class clazz;
    private final String code;
    
    private ErrorMaping(Class clazz, String code) {
      this.clazz = clazz;
      this.code = code;
    }
    
    public static ErrorMaping getByClass(Class clazz) throws UnhandledBaseException {
      return Stream.of(ErrorMaping.values())
          .filter((errorMaping) -> errorMaping.getClazz().equals(clazz))
          .findFirst()
          .orElseThrow(() -> new UnhandledBaseException());
    }
    
    /**
     * @return the clazz
     */
    public Class getClazz() {
      return clazz;
    }
    
    /**
     * @return the code
     */
    public String getCode() {
      return code;
    }
  }
  
  private static class UnhandledBaseException extends Exception {
  }
}
