import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NotificationSharedModule } from 'app/shared';
import {
    SourceTypeComponent,
    SourceTypeDetailComponent,
    SourceTypeUpdateComponent,
    SourceTypeDeletePopupComponent,
    SourceTypeDeleteDialogComponent,
    sourceTypeRoute,
    sourceTypePopupRoute
} from './';

const ENTITY_STATES = [...sourceTypeRoute, ...sourceTypePopupRoute];

@NgModule({
    imports: [NotificationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SourceTypeComponent,
        SourceTypeDetailComponent,
        SourceTypeUpdateComponent,
        SourceTypeDeleteDialogComponent,
        SourceTypeDeletePopupComponent
    ],
    entryComponents: [SourceTypeComponent, SourceTypeUpdateComponent, SourceTypeDeleteDialogComponent, SourceTypeDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationSourceTypeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
