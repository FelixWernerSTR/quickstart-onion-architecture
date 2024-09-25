export class Worker {
  
  public login: string;
  public name: string;
  public surname: string;
  public email: string;
  
  constructor(login?: string, name?: string, surname?: string, email?: string) {
      this.login = login;
      this.name = name;
      this.surname = surname;
      this.email = email;
  }
}
