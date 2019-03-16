import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SystemNotification } from 'app/shared/model/system-notification.model';
import { SystemNotificationService } from './system-notification.service';
import { SystemNotificationComponent } from './system-notification.component';
import { SystemNotificationDetailComponent } from './system-notification-detail.component';
import { SystemNotificationUpdateComponent } from './system-notification-update.component';
import { SystemNotificationDeletePopupComponent } from './system-notification-delete-dialog.component';
import { ISystemNotification } from 'app/shared/model/system-notification.model';

@Injectable({ providedIn: 'root' })
export class SystemNotificationResolve implements Resolve<ISystemNotification> {
    constructor(private service: SystemNotificationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISystemNotification> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SystemNotification>) => response.ok),
                map((systemNotification: HttpResponse<SystemNotification>) => systemNotification.body)
            );
        }
        return of(new SystemNotification());
    }
}

export const systemNotificationRoute: Routes = [
    {
        path: '',
        component: SystemNotificationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'notificationApp.systemNotification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SystemNotificationDetailComponent,
        resolve: {
            systemNotification: SystemNotificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.systemNotification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SystemNotificationUpdateComponent,
        resolve: {
            systemNotification: SystemNotificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.systemNotification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SystemNotificationUpdateComponent,
        resolve: {
            systemNotification: SystemNotificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.systemNotification.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const systemNotificationPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SystemNotificationDeletePopupComponent,
        resolve: {
            systemNotification: SystemNotificationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.systemNotification.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
