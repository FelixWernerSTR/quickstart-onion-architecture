package pl.splaw.onionarchitecture.databaserepository.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Entity
@Table(name = "WORK_LOG")
public class WorkLogEntity {
  
  @Id
  @SequenceGenerator(sequenceName = "WORK_LOG_SEQUENCE", allocationSize = 1, name = "WORK_LOG_SEQUENCE")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_LOG_SEQUENCE")
  @Column(name = "work_log_id")
  private Long id;
  @Column(name = "start_date")
  private Date startDate;
  @Column(name = "time_spent_in_seconds")
  private BigInteger timeSpentInSeconds;
  @Column(name = "description")
  private String description;
  
  public WorkLogEntity() {
    
  }
  
  @ManyToOne
  @JoinColumn(name = "worker_login", referencedColumnName = "login")
  private WorkerEntity associatedWorker;
  
  public WorkLogEntity(LocalDate startDate, BigInteger timeSpentInSeconds, String description, WorkerEntity associatedWorker) {
    this.startDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    this.timeSpentInSeconds = timeSpentInSeconds;
    this.description = description;
    this.associatedWorker = associatedWorker;
  }
  
  public WorkLogEntity(Long id, LocalDate startDate, BigInteger timeSpentInSeconds, String description, WorkerEntity associatedWorker) {
    this(startDate, timeSpentInSeconds, description, associatedWorker);
    this.setId(id);
  }
  
  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }
  
  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * @return the startDate
   */
  public Date getStartDate() {
    return startDate;
  }
  
  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(Date startDate) {
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
  
  /**
   * @return the associatedWorker
   */
  public WorkerEntity getAssociatedWorker() {
    return associatedWorker;
  }
  
  /**
   * @param associatedWorker the associatedWorker to set
   */
  public void setAssociatedWorker(WorkerEntity associatedWorker) {
    this.associatedWorker = associatedWorker;
  }
  
}
