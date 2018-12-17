import { NgModule } from '@angular/core';

import { CrimeStopperSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [CrimeStopperSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [CrimeStopperSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CrimeStopperSharedCommonModule {}
