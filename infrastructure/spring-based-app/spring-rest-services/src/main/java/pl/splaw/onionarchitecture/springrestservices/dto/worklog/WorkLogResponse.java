package pl.splaw.onionarchitecture.springrestservices.dto.worklog;

import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.springrestservices.serializer.LocalDateSerializer;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkLogResponse {
  
  private Long workLogId;
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate startDate;
  private BigInteger timeSpentInSeconds;
  private Worker associatedWorker;
  private String description;
  
  public WorkLogResponse() {
  }
  
  /**
   * @param workLogId
   * @param startDate
   * @param timeSpentInSeconds
   * @param associatedWorker
   * @param description
   */
  public WorkLogResponse(Long workLogId, LocalDate startDate, BigInteger timeSpentInSeconds, Worker associatedWorker, String description) {
    super();
    this.workLogId = workLogId;
    this.startDate = startDate;
    this.timeSpentInSeconds = timeSpentInSeconds;
    this.associatedWorker = associatedWorker;
    this.description = description;
  }
  
  /**
   * @return the workLogId
   */
  public Long getWorkLogId() {
    return workLogId;
  }
  
  /**
   * @param workLogId the workLogId to set
   */
  public void setWorkLogId(Long workLogId) {
    this.workLogId = workLogId;
  }
  
  /**
   * @return the startDate
   */
  public LocalDate getStartDate() {
    return startDate;
  }
  
  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }
  
  /**
   * @return the timeSpentInSeconds
   */
  public BigInteger getTimeSpentInSeconds() {
    return timeSpentInSeconds;
  }
  
  /**
   * @param timeSpentInSeconds the timeSpentInSeconds to set
   */
  public void setTimeSpentInSeconds(BigInteger timeSpentInSeconds) {
    this.timeSpentInSeconds = timeSpentInSeconds;
  }
  
  /**
   * @return the associatedWorker
   */
  public Worker getAssociatedWorker() {
    return associatedWorker;
  }
  
  /**
   * @param associatedWorker the associatedWorker to set
   */
  public void setAssociatedWorker(Worker associatedWorker) {
    this.associatedWorker = associatedWorker;
  }
  
  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
