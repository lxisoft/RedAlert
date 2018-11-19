import { NgModule } from '@angular/core';

import { RedAlertUiSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [RedAlertUiSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [RedAlertUiSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class RedAlertUiSharedCommonModule {}
