import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NotificationSharedModule } from 'app/shared';
import {
    SystemNotificationComponent,
    SystemNotificationDetailComponent,
    SystemNotificationUpdateComponent,
    SystemNotificationDeletePopupComponent,
    SystemNotificationDeleteDialogComponent,
    systemNotificationRoute,
    systemNotificationPopupRoute
} from './';

const ENTITY_STATES = [...systemNotificationRoute, ...systemNotificationPopupRoute];

@NgModule({
    imports: [NotificationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SystemNotificationComponent,
        SystemNotificationDetailComponent,
        SystemNotificationUpdateComponent,
        SystemNotificationDeleteDialogComponent,
        SystemNotificationDeletePopupComponent
    ],
    entryComponents: [
        SystemNotificationComponent,
        SystemNotificationUpdateComponent,
        SystemNotificationDeleteDialogComponent,
        SystemNotificationDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationSystemNotificationModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
