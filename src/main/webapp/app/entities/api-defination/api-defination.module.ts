import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NotificationSharedModule } from 'app/shared';
import {
    ApiDefinationComponent,
    ApiDefinationDetailComponent,
    ApiDefinationUpdateComponent,
    ApiDefinationDeletePopupComponent,
    ApiDefinationDeleteDialogComponent,
    apiDefinationRoute,
    apiDefinationPopupRoute
} from './';

const ENTITY_STATES = [...apiDefinationRoute, ...apiDefinationPopupRoute];

@NgModule({
    imports: [NotificationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApiDefinationComponent,
        ApiDefinationDetailComponent,
        ApiDefinationUpdateComponent,
        ApiDefinationDeleteDialogComponent,
        ApiDefinationDeletePopupComponent
    ],
    entryComponents: [
        ApiDefinationComponent,
        ApiDefinationUpdateComponent,
        ApiDefinationDeleteDialogComponent,
        ApiDefinationDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationApiDefinationModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
