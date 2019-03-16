import { Moment } from 'moment';

export const enum StatusType {
    READ = 'READ',
    UN_READ = 'UN_READ',
    NEW = 'NEW'
}

export interface ISystemNotification {
    id?: number;
    content?: string;
    status?: StatusType;
    sendDate?: Moment;
    sourceId?: string;
    sourceType?: string;
    sourceName?: string;
    applicationKey?: string;
    error?: string;
    errorShortName?: string;
    errorDate?: Moment;
    deleted?: boolean;
    deletedDate?: Moment;
}

export class SystemNotification implements ISystemNotification {
    constructor(
        public id?: number,
        public content?: string,
        public status?: StatusType,
        public sendDate?: Moment,
        public sourceId?: string,
        public sourceType?: string,
        public sourceName?: string,
        public applicationKey?: string,
        public error?: string,
        public errorShortName?: string,
        public errorDate?: Moment,
        public deleted?: boolean,
        public deletedDate?: Moment
    ) {
        this.deleted = this.deleted || false;
    }
}
