import { IApiParam } from 'app/shared/model/api-param.model';

export const enum ApiType {
    GET = 'GET',
    POST = 'POST'
}

export interface IApiDefination {
    id?: number;
    apiKey?: string;
    apiType?: ApiType;
    apiUrl?: string;
    apiParams?: IApiParam[];
}

export class ApiDefination implements IApiDefination {
    constructor(
        public id?: number,
        public apiKey?: string,
        public apiType?: ApiType,
        public apiUrl?: string,
        public apiParams?: IApiParam[]
    ) {}
}
