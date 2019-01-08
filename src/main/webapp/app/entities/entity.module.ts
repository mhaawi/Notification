import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { NotificationApplicationModule } from './application/application.module';
import { NotificationNotificationDefinationModule } from './notification-defination/notification-defination.module';
import { NotificationApiDefinationModule } from './api-defination/api-defination.module';
import { NotificationApiParamModule } from './api-param/api-param.module';
import { NotificationSourceTypeModule } from './source-type/source-type.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        NotificationApplicationModule,
        NotificationNotificationDefinationModule,
        NotificationApiDefinationModule,
        NotificationApiParamModule,
        NotificationSourceTypeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationEntityModule {}
