package pl.splaw.onionarchitecture.databaserepository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Entity
@Table(name = "WORKER")
public class WorkerEntity {
  
  @Id
  @Column(name = "login")
  private String login;
  @Column(name = "name")
  private String name;
  @Column(name = "surname")
  private String surname;
  @Column(name = "email")
  private String email;
  
  public WorkerEntity() {
  }
  
  /**
   * @param login
   * @param name
   * @param surname
   * @param email
   */
  public WorkerEntity(String login, String name, String surname, String email) {
    super();
    this.login = login;
    this.name = name;
    this.surname = surname;
    this.email = email;
  }
  
  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }
  
  /**
   * @param login the login to set
   */
  public void setLogin(String login) {
    this.login = login;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * @return the surname
   */
  public String getSurname() {
    return surname;
  }
  
  /**
   * @param surname the surname to set
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }
  
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
}
