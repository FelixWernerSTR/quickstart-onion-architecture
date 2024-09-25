import {defineStore} from "pinia";
import WorkerService from "@/services/Worker.service";
import WorkLogService from "@/services/WorkLog.service";
import { Worker } from "@/model/Worker.model";
import { WorkLog } from "@/model/WorkLog.model";


//gutes Beispiel wie man Pinia mit Typescript verwendet:
//https://runthatline.com/pinia-typescript-type-state-actions-getters/
type State = {
  worker: Worker;
  worklog: WorkLog;
  worklogs: Array<WorkLog>;
  searchLogin: String;
  isRequestLoading: boolean;
};
const workerService = new WorkerService();
const workLogService = new WorkLogService();

export const useWorkerWorkLog = defineStore("WorkerWorkLog", {
  state: (): State => ({
    worker: new Worker(),
    worklog: new WorkLog(),
    worklogs: Array.of<WorkLog>(),
    searchLogin: '',
    isWorkerValid: false
  }),

  getters: {
    requestLoading() {
      return this.isRequestLoading;
    },
  },

  actions: {
    getWorkerForLogin(): Promise<void> {
      console.debug("retrieveWorker by pinia action!");
      workerService.find(this.searchLogin).then((res) => {
      console.debug("retrieveWorker res:", res);
      this.worker = res;
      }).catch((error) => {
        alert(error);
        console.debug("retrieveWorker fehlgeschlagen!", error);
      });
    },
    
    setIsWorkerValid(valid:boolean): Promise<boolean> {
      this.isWorkerValid = valid;
      return this.isWorkerValid;
    },
    
    setIsRequestLoading(loading:boolean): Promise<boolean> {
      this.isRequestLoading = loading;
      return this.isRequestLoading;
    },
    
    getIsRequestLoading() : Promise<boolean>{
      return this.isRequestLoading;
    },
    
    clearWorker() : Promise<void>{
      this.worker = new Worker();
    },
    
    clearWorkLog() : Promise<void>{
      this.worklog = new WorkLog();
    },
    
    async saveWorker() { //locked-method
      await this.getIsRequestLoading();
      if(this.isRequestLoading===true){
        alert("please wait server is not ready yet!");
      }else{
      await this.callRestServiceSaveWorker();
      }
    },
    async callRestServiceSaveWorker(): Promise<void> {
      this.setIsRequestLoading(true);
      console.debug("callRestServiceSaveWorker this.isRequestLoading: ", this.isRequestLoading);
      workerService.save(this.worker).then((res) => {
      console.debug("callRestServiceSaveWorker res:", res);
      //this.worker = res; //attention update like this disables validate functionality
      this.worker.login=res.login;
      this.worker.name=res.name;
      this.worker.surname=res.surname;
      this.worker.email=res.email;
      
      }) .catch((error) => {
      alert(error);
      console.debug("callRestServiceSaveWorker failed!", error);
      });
      this.setIsRequestLoading(false);
    },
    
    async updateWorker() { //locked-method
      await this.getIsRequestLoading();
      if(this.isRequestLoading===true){
        alert("please wait server is not ready yet!");
      }else{
      await this.callRestServiceUpdateWorker();
      }
    },
    async callRestServiceUpdateWorker(): Promise<void> {
      await this.setIsRequestLoading(true);
      console.debug("callRestServiceUpdateWorker this.isRequestLoading: ", this.isRequestLoading);
      await workerService.update(this.worker.login,this.worker).then((res) => {
      console.debug("callRestServiceUpdateWorker res:", res);
      //this.worker = res  //attention update like this disables validate functionality
      this.worker.login=res.login;
      this.worker.name=res.name;
      this.worker.surname=res.surname;
      this.worker.email=res.email;
      }) .catch((error) => {
      alert(error);
      console.debug("callRestServiceUpdateWorker failed!", error);
      });
      await this.setIsRequestLoading(false);
    },
    
    async saveWorkLog() { //locked-method
      this.worklog.associatedWorkerLogin = this.worker.login;
      await this.getIsRequestLoading();
      if(this.isRequestLoading===true){
        alert("please wait server is not ready yet!");
      }else{
      await this.callRestServiceSaveWorkLog();
      }
    },
    async callRestServiceSaveWorkLog(): Promise<void> {
      await this.setIsRequestLoading(true);
      console.debug("callRestServiceSaveWorkLog this.isRequestLoading: ", this.isRequestLoading);
      console.debug("callRestServiceSaveWorkLog this.workLog:", this.worklog);
      
      await workLogService.save(this.worklog).then((res) => {
      console.debug("callRestServiceSaveWorkLog res:", res);
      //this.worklog = res //attention update like this disables validate functionality
      this.worklog.workLogId=res.workLogId;
      this.worklog.startDate=res.startDate;
      this.worklog.timeSpentInSeconds=res.timeSpentInSeconds;
      this.worklog.associatedWorkerLogin=res.associatedWorkerLogin;
      this.worklog.description=res.description;
      }) .catch((error) => {
      alert(error);
      console.debug("callRestServiceSaveWorkLog failed!", error);
      });
      await this.setIsRequestLoading(false);
    },
    
    async updateWorkLog() { //locked-method
       this.worklog.associatedWorkerLogin = this.worker.login;
       await this.getIsRequestLoading();
       if(this.isRequestLoading===true){
        alert("please wait server is not ready yet!");
       }else{
       await this.callRestServiceUpdateWorkLog();
       }
     },
     async callRestServiceUpdateWorkLog(): Promise<void> {
       await this.setIsRequestLoading(true);
       console.debug("callRestServiceUpdateWorkLog this.isRequestLoading: ", this.isRequestLoading);
       console.debug("callRestServiceUpdateWorkLog this.workLog:", this.worklog);
       
       await workLogService.update(this.worklog.workLogId,this.worklog).then((res) => {
       console.debug("callRestServiceUpdateWorkLog res:", res);
       //this.worklog = res //attention update like this disables validate functionality
       this.worklog.workLogId=res.workLogId;
       this.worklog.startDate=res.startDate;
       this.worklog.timeSpentInSeconds=res.timeSpentInSeconds;
       this.worklog.associatedWorkerLogin=res.associatedWorkerLogin;
       this.worklog.description=res.description;
       }) .catch((error) => {
       alert(error);
       console.debug("callRestServiceUpdateWorkLog failed!", error);
       });
       await this.setIsRequestLoading(false);
     },
     
     async getWorkLogsForLogin() { //locked-method
        await this.getIsRequestLoading();
        if(this.isRequestLoading===true){
          alert("please wait server is not ready yet!");
        }else{
        await this.callRestServiceWorkLogsForLogin();
        }
      },
      async callRestServiceWorkLogsForLogin(): Promise<void> {
        await this.setIsRequestLoading(true);
        console.debug("callRestServiceWorkLogsForLogin this.isRequestLoading: ", this.isRequestLoading);
        console.debug("callRestServiceWorkLogsForLogin this.searchLogin:", this.searchLogin);
        
        await workLogService.findWorklogsForLogin(this.searchLogin).then((res) => {
        console.debug("callRestServiceWorkLogsForLogin res:", res);
        this.worklogs = res;
        //
        }) .catch((error) => {
        alert(error);
        console.debug("callRestServiceWorkLogsForLogin failed!", error);
        });
        await this.setIsRequestLoading(false);
      },
           
  },
});
