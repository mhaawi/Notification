import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { NotificationSharedLibsModule, NotificationSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [NotificationSharedLibsModule, NotificationSharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [NotificationSharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationSharedModule {
    static forRoot() {
        return {
            ngModule: NotificationSharedModule
        };
    }
}
