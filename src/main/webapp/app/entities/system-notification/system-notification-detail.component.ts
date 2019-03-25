import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISystemNotification } from 'app/shared/model/system-notification.model';

@Component({
    selector: 'jhi-system-notification-detail',
    templateUrl: './system-notification-detail.component.html'
})
export class SystemNotificationDetailComponent implements OnInit {
    systemNotification: ISystemNotification;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemNotification }) => {
            this.systemNotification = systemNotification;
        });
    }

    previousState() {
        window.history.back();
    }
}
