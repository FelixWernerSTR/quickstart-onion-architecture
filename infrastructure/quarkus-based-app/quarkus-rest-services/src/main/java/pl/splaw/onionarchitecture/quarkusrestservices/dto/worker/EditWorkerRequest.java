package pl.splaw.onionarchitecture.quarkusrestservices.dto.worker;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class EditWorkerRequest {
  
  private String name;
  private String surname;
  private String email;
  
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
