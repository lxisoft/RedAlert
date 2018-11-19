import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { RedAlertUiSharedLibsModule, RedAlertUiSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [RedAlertUiSharedLibsModule, RedAlertUiSharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [RedAlertUiSharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RedAlertUiSharedModule {
    static forRoot() {
        return {
            ngModule: RedAlertUiSharedModule
        };
    }
}
