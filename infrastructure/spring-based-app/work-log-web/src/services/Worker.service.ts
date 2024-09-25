import axios from "axios";

import { Worker } from "@/model/Worker.model";

const baseApiUrl = __VITE_BASE_API_URL_WORKER__;

export default class WorkerService {

  public find(login: string): Promise<Worker> {
    return new Promise<Worker>((resolve, reject) => {
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

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}`)
        .then((res: any) => {
          resolve(res);
        })
        .catch((err: any) => {
          reject(err);
        });
    });
  }

  public delete(login: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${login}`)
        .then((res: any) => {
          resolve(res);
        })
        .catch((err: any) => {
          reject(err);
        });
    });
  }

  public save(entity: Worker): Promise<Worker> {
    return new Promise<Worker>((resolve, reject) => {
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
  
  public update(login: string, entity: Worker): Promise<Worker> {
    return new Promise<Worker>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/${login}`, entity)
        .then((res: any) => {
          resolve(res.data);
        })
        .catch((err: any) => {
          reject(err);
        });
    });
  }

}
