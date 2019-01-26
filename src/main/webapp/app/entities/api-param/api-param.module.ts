import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NotificationSharedModule } from 'app/shared';
import {
    ApiParamComponent,
    ApiParamDetailComponent,
    ApiParamUpdateComponent,
    ApiParamDeletePopupComponent,
    ApiParamDeleteDialogComponent,
    apiParamRoute,
    apiParamPopupRoute
} from './';

const ENTITY_STATES = [...apiParamRoute, ...apiParamPopupRoute];

@NgModule({
    imports: [NotificationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApiParamComponent,
        ApiParamDetailComponent,
        ApiParamUpdateComponent,
        ApiParamDeleteDialogComponent,
        ApiParamDeletePopupComponent
    ],
    entryComponents: [ApiParamComponent, ApiParamUpdateComponent, ApiParamDeleteDialogComponent, ApiParamDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationApiParamModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
