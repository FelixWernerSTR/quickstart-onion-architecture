package pl.splaw.onionarchitecture.springrestservices.dto.worklog;

import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import pl.splaw.onionarchitecture.springrestservices.serializer.LocalDateDeserializer;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class NewWorkLogRequest {
  
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate startDate;
  private BigInteger timeSpentInSeconds;
  private String associatedWorkerLogin;
  private String description;
  
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
   * @return the associatedWorkerLogin
   */
  public String getAssociatedWorkerLogin() {
    return associatedWorkerLogin;
  }
  
  /**
   * @param associatedWorkerLogin the associatedWorkerLogin to set
   */
  public void setAssociatedWorkerLogin(String associatedWorkerLogin) {
    this.associatedWorkerLogin = associatedWorkerLogin;
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
