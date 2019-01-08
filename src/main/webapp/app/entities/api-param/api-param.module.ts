import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

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
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationApiParamModule {}
