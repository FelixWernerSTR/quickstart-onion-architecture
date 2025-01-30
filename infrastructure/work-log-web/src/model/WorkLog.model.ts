import { Worker } from './Worker.model';

export class WorkLog {

    workLogId: number;
    startDate: Date;
    timeSpentInSeconds: number;
    associatedWorkerLogin: String;
    description: string;
    
    constructor(workLogId: number, startDate: Date, timeSpentInSeconds: number, associatedWorkerLogin: String, description: string) {
        this.workLogId = workLogId;
        this.startDate = startDate;
        this.timeSpentInSeconds = timeSpentInSeconds;
        this.associatedWorkerLogin = associatedWorkerLogin;
        this.description = description;
    }   
    
}