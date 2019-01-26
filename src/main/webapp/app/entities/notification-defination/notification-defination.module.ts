import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NotificationSharedModule } from 'app/shared';
import {
    NotificationDefinationComponent,
    NotificationDefinationDetailComponent,
    NotificationDefinationUpdateComponent,
    NotificationDefinationDeletePopupComponent,
    NotificationDefinationDeleteDialogComponent,
    notificationDefinationRoute,
    notificationDefinationPopupRoute
} from './';

const ENTITY_STATES = [...notificationDefinationRoute, ...notificationDefinationPopupRoute];

@NgModule({
    imports: [NotificationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NotificationDefinationComponent,
        NotificationDefinationDetailComponent,
        NotificationDefinationUpdateComponent,
        NotificationDefinationDeleteDialogComponent,
        NotificationDefinationDeletePopupComponent
    ],
    entryComponents: [
        NotificationDefinationComponent,
        NotificationDefinationUpdateComponent,
        NotificationDefinationDeleteDialogComponent,
        NotificationDefinationDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationNotificationDefinationModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
