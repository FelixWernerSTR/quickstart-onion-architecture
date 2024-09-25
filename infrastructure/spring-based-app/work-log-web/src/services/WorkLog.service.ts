import axios from "axios";

import { WorkLog } from "@/model/WorkLog.model";

const baseApiUrl = __VITE_BASE_API_URL_WORKLOG__;

export default class WorkLogService {

  public findWorklogsForLogin(login: string): Promise<WorkLog[]> {
    return new Promise<WorkLog[]>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${login}`)
        .then((res: any) => {
          resolve(res.data);
        })
        .catch((err: any) => {
          reject(err);
        });
    });
  }

  public delete(id: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then((res: any) => {
          resolve(res);
        })
        .catch((err: any) => {
          reject(err);
        });
    });
  }

  public save(entity: WorkLog): Promise<WorkLog> {
    return new Promise<WorkLog>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then((res: any) => {
          resolve(res.data);
        })
        .catch((err: any) => {
          reject(err);
        });
    });
  }
  
  public update(workLogId: string, entity: WorkLog): Promise<WorkLog> {
    return new Promise<WorkLog>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/${workLogId}`, entity)
        .then((res: any) => {
          resolve(res.data);
        })
        .catch((err: any) => {
          reject(err);
        });
    });
  }

}
