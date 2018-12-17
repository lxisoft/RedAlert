import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { CrimeStopperSharedLibsModule, CrimeStopperSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [CrimeStopperSharedLibsModule, CrimeStopperSharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [CrimeStopperSharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CrimeStopperSharedModule {
    static forRoot() {
        return {
            ngModule: CrimeStopperSharedModule
        };
    }
}
