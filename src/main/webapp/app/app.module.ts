import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ngx-webstorage';

import { ControleEstacionamentoSharedModule, UserRouteAccessService } from './shared';
import { ControleEstacionamentoAppRoutingModule} from './app-routing.module';
import { ControleEstacionamentoHomeModule } from './home/home.module';
import { ControleEstacionamentoAdminModule } from './admin/admin.module';
import { ControleEstacionamentoAccountModule } from './account/account.module';
import { ControleEstacionamentoEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        ControleEstacionamentoAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        ControleEstacionamentoSharedModule,
        ControleEstacionamentoHomeModule,
        ControleEstacionamentoAdminModule,
        ControleEstacionamentoAccountModule,
        ControleEstacionamentoEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class ControleEstacionamentoAppModule {}
