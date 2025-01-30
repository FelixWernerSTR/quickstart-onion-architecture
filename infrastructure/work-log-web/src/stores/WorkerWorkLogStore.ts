import { defineStore } from "pinia";
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
};
const workerService = new WorkerService();
const workLogService = new WorkLogService();

export const useWorkerWorkLog = defineStore("WorkerWorkLog", {
  state: (): State => ({
    worker: new Worker(),
    worklog: new WorkLog(),
    worklogs: Array.of<WorkLog>(),
    searchLogin: "",
    isWorkerValid: false,
  }),

  actions: {
    getWorkerForLogin(): Promise<void> {
      console.debug("retrieve Worker by pinia action!");
      workerService.find(this.searchLogin).then((res) => {
          console.debug("retrieveWorker res:", res);
          //this.worker = res; //attention update like this disables validate functionality
          this.worker.login = res.login;
          this.worker.name = res.name;
          this.worker.surname = res.surname;
          this.worker.email = res.email;
        }).catch((error) => {
          alert(error);
          console.debug("retrieve Worker fehlgeschlagen!", error);
        });
    },

    setIsWorkerValid(valid: boolean): Promise<boolean> {
      this.isWorkerValid = valid;
      return this.isWorkerValid;
    },

    clearWorker(): Promise<void> {
      this.worker = new Worker();
    },

    clearWorkLog(): Promise<void> {
      this.worklog = new WorkLog();
    },

    async saveWorker() {
      //locked-method
      await workerService.getIsRequestLoading();
      if (workerService.isRequestLoading === true) {
        alert("please wait server is not ready yet!");
      } else {
        await workerService.save(this.worker).then((res) => {
         console.debug("callRestServiceSaveWorker res:", res);
         //this.worker = res; //attention update like this disables validate functionality
         this.worker.login = res.login;
         this.worker.name = res.name;
         this.worker.surname = res.surname;
         this.worker.email = res.email;
       }).catch((error) => {
         alert(error);
         console.debug("callRestServiceSaveWorker failed!", error);
       });
      }
    },

    async updateWorker() {
      //locked-method
      if (this.isRequestLoading === true) {
        alert("please wait server is not ready yet!");
      } else {
        await workerService.update(this.worker.login, this.worker).then((res) => {
        console.debug("callRestServiceUpdateWorker res:", res);
        //this.worker = res  //attention update like this disables validate functionality
        this.worker.login = res.login;
        this.worker.name = res.name;
        this.worker.surname = res.surname;
        this.worker.email = res.email;
      }).catch((error) => {
        alert(error);
        console.debug("callRestServiceUpdateWorker failed!", error);
      });
      }
    },

    async saveWorkLog() {
      //locked-method
      this.worklog.associatedWorkerLogin = this.worker.login;
      if (workLogService.isRequestLoading === true) {
        alert("please wait server is not ready yet!");
      } else {
        await workLogService.save(this.worklog).then((res) => {
        console.debug("callRestServiceSaveWorkLog res:", res);
        //this.worklog = res //attention update like this disables validate functionality
        this.worklog.workLogId = res.workLogId;
        this.worklog.startDate = res.startDate;
        this.worklog.timeSpentInSeconds = res.timeSpentInSeconds;
        this.worklog.associatedWorkerLogin = res.associatedWorkerLogin;
        this.worklog.description = res.description;
      }).catch((error) => {
        alert(error);
        console.debug("callRestServiceSaveWorkLog failed!", error);
      });
      }
    },

    async updateWorkLog() {
      //locked-method
      this.worklog.associatedWorkerLogin = this.worker.login;
      if (this.isRequestLoading === true) {
        alert("please wait server is not ready yet!");
      } else {
        await workLogService.update(this.worklog.workLogId, this.worklog).then((res) => {
        console.debug("callRestServiceUpdateWorkLog res:", res);
        //this.worklog = res //attention update like this disables validate functionality
        this.worklog.workLogId = res.workLogId;
        this.worklog.startDate = res.startDate;
        this.worklog.timeSpentInSeconds = res.timeSpentInSeconds;
        this.worklog.associatedWorkerLogin = res.associatedWorkerLogin;
        this.worklog.description = res.description;
      }).catch((error) => {
        alert(error);
        console.debug("updateWorkLog failed!", error);
      });
      }
    },

    async getWorkLogsForLogin() {
      //locked-method
      if (workLogService.isRequestLoading === true) {
        alert("please wait server is not ready yet!");
      } else {
        console.debug("getWorkLogsForLogin this.searchLogin:",this.searchLogin);
        await workLogService.findWorklogsForLogin(this.searchLogin).then((res) => {
          console.debug("getWorkLogsForLogin res:", res);
          this.worklogs = res;
        }).catch((error) => {
          alert(error);
          console.debug("getWorkLogsForLogin failed!", error);
        });
      }
    },
  },
});
