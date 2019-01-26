import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SourceType } from 'app/shared/model/source-type.model';
import { SourceTypeService } from './source-type.service';
import { SourceTypeComponent } from './source-type.component';
import { SourceTypeDetailComponent } from './source-type-detail.component';
import { SourceTypeUpdateComponent } from './source-type-update.component';
import { SourceTypeDeletePopupComponent } from './source-type-delete-dialog.component';
import { ISourceType } from 'app/shared/model/source-type.model';

@Injectable({ providedIn: 'root' })
export class SourceTypeResolve implements Resolve<ISourceType> {
    constructor(private service: SourceTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISourceType> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SourceType>) => response.ok),
                map((sourceType: HttpResponse<SourceType>) => sourceType.body)
            );
        }
        return of(new SourceType());
    }
}

export const sourceTypeRoute: Routes = [
    {
        path: '',
        component: SourceTypeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'notificationApp.sourceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SourceTypeDetailComponent,
        resolve: {
            sourceType: SourceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.sourceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SourceTypeUpdateComponent,
        resolve: {
            sourceType: SourceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.sourceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SourceTypeUpdateComponent,
        resolve: {
            sourceType: SourceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.sourceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sourceTypePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SourceTypeDeletePopupComponent,
        resolve: {
            sourceType: SourceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationApp.sourceType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
