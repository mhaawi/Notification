import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

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
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationNotificationDefinationModule {}