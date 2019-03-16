import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ISystemNotification } from 'app/shared/model/system-notification.model';
import { SystemNotificationService } from './system-notification.service';

@Component({
    selector: 'jhi-system-notification-update',
    templateUrl: './system-notification-update.component.html'
})
export class SystemNotificationUpdateComponent implements OnInit {
    systemNotification: ISystemNotification;
    isSaving: boolean;
    sendDate: string;
    errorDate: string;
    deletedDate: string;

    constructor(protected systemNotificationService: SystemNotificationService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ systemNotification }) => {
            this.systemNotification = systemNotification;
            this.sendDate = this.systemNotification.sendDate != null ? this.systemNotification.sendDate.format(DATE_TIME_FORMAT) : null;
            this.errorDate = this.systemNotification.errorDate != null ? this.systemNotification.errorDate.format(DATE_TIME_FORMAT) : null;
            this.deletedDate =
                this.systemNotification.deletedDate != null ? this.systemNotification.deletedDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.systemNotification.sendDate = this.sendDate != null ? moment(this.sendDate, DATE_TIME_FORMAT) : null;
        this.systemNotification.errorDate = this.errorDate != null ? moment(this.errorDate, DATE_TIME_FORMAT) : null;
        this.systemNotification.deletedDate = this.deletedDate != null ? moment(this.deletedDate, DATE_TIME_FORMAT) : null;
        if (this.systemNotification.id !== undefined) {
            this.subscribeToSaveResponse(this.systemNotificationService.update(this.systemNotification));
        } else {
            this.subscribeToSaveResponse(this.systemNotificationService.create(this.systemNotification));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemNotification>>) {
        result.subscribe((res: HttpResponse<ISystemNotification>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
