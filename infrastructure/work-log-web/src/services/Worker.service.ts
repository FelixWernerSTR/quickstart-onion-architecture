import axios from "axios";

import { Worker } from "@/model/Worker.model";

const baseApiUrl = __VITE_BASE_API_URL_WORKER__;

export default class WorkerService {
  
  isRequestLoading: boolean;

  public setIsRequestLoading(loading: boolean): Promise<boolean> {
    this.isRequestLoading = loading;
    return this.isRequestLoading;
  }

  public getIsRequestLoading(): Promise<boolean> {
    return this.isRequestLoading;
  }

  public find(login: string): Promise<Worker> {
    this.setIsRequestLoading(true);
    return new Promise<Worker>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${login}`)
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

  public retrieve(): Promise<any> {
    this.setIsRequestLoading(true);
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}`)
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

  public delete(login: string): Promise<any> {
    this.setIsRequestLoading(true);
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${login}`)
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

  public save(entity: Worker): Promise<Worker> {
    this.setIsRequestLoading(true);
    return new Promise<Worker>((resolve, reject) => {
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
  
  public update(login: string, entity: Worker): Promise<Worker> {
    this.setIsRequestLoading(true);
    return new Promise<Worker>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/${login}`, entity)
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
