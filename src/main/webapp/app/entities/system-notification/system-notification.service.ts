import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISystemNotification } from 'app/shared/model/system-notification.model';

type EntityResponseType = HttpResponse<ISystemNotification>;
type EntityArrayResponseType = HttpResponse<ISystemNotification[]>;

@Injectable({ providedIn: 'root' })
export class SystemNotificationService {
    public resourceUrl = SERVER_API_URL + 'api/system-notifications';

    constructor(protected http: HttpClient) {}

    create(systemNotification: ISystemNotification): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(systemNotification);
        return this.http
            .post<ISystemNotification>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(systemNotification: ISystemNotification): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(systemNotification);
        return this.http
            .put<ISystemNotification>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISystemNotification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISystemNotification[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(systemNotification: ISystemNotification): ISystemNotification {
        const copy: ISystemNotification = Object.assign({}, systemNotification, {
            sendDate:
                systemNotification.sendDate != null && systemNotification.sendDate.isValid() ? systemNotification.sendDate.toJSON() : null,
            errorDate:
                systemNotification.errorDate != null && systemNotification.errorDate.isValid()
                    ? systemNotification.errorDate.toJSON()
                    : null,
            deletedDate:
                systemNotification.deletedDate != null && systemNotification.deletedDate.isValid()
                    ? systemNotification.deletedDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.sendDate = res.body.sendDate != null ? moment(res.body.sendDate) : null;
            res.body.errorDate = res.body.errorDate != null ? moment(res.body.errorDate) : null;
            res.body.deletedDate = res.body.deletedDate != null ? moment(res.body.deletedDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((systemNotification: ISystemNotification) => {
                systemNotification.sendDate = systemNotification.sendDate != null ? moment(systemNotification.sendDate) : null;
                systemNotification.errorDate = systemNotification.errorDate != null ? moment(systemNotification.errorDate) : null;
                systemNotification.deletedDate = systemNotification.deletedDate != null ? moment(systemNotification.deletedDate) : null;
            });
        }
        return res;
    }
}
