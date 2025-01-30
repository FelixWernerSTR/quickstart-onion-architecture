import axios from "axios";

import { WorkLog } from "@/model/WorkLog.model";

const baseApiUrl = __VITE_BASE_API_URL_WORKLOG__;

export default class WorkLogService {
  
  isRequestLoading: boolean;
  
  public setIsRequestLoading(loading: boolean): Promise<boolean> {
    this.isRequestLoading = loading;
    return this.isRequestLoading;
  }

  public getIsRequestLoading(): Promise<boolean> {
    return this.isRequestLoading;
  }

  public findWorklogsForLogin(login: string): Promise<WorkLog[]> {
    this.setIsRequestLoading(true);
    return new Promise<WorkLog[]>((resolve, reject) => {
      axios.get(`${baseApiUrl}/${login}`).then((res: any) => {
          this.setIsRequestLoading(false);
          resolve(res.data);
        }).catch((err: any) => {
          this.setIsRequestLoading(false);
          reject(err);
        });
    });
  }

  public delete(id: string): Promise<any> {
    this.setIsRequestLoading(true);
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then((res: any) => {
          this.setIsRequestLoading(false);
          resolve(res);
        })
        .catch((err: any) => {
          this.setIsRequestLoading(false);
          reject(err);
        });
    });
  }

  public save(entity: WorkLog): Promise<WorkLog> {
    this.setIsRequestLoading(true);
    return new Promise<WorkLog>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then((res: any) => {
          this.setIsRequestLoading(false);
          resolve(res.data);
        })
        .catch((err: any) => {
          this.setIsRequestLoading(false);
          reject(err);
        });
    });
  }
  
  public update(workLogId: string, entity: WorkLog): Promise<WorkLog> {
    this.setIsRequestLoading(true);
    return new Promise<WorkLog>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/${workLogId}`, entity)
        .then((res: any) => {
          this.setIsRequestLoading(false);
          resolve(res.data);
        })
        .catch((err: any) => {
          this.setIsRequestLoading(false);
          reject(err);
        });
    });
  }

}
