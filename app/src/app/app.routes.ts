import { Routes } from '@angular/router';
import { CreateRaffleComponent } from '@pages/create-raffle/create-raffle.component';
import { ModifyRaffleComponent } from '@pages/modify-raffle/modify-raffle.component';
import { MyRafflesComponent } from '@pages/my-raffles/my-raffles.component';
import { NotFoundComponent } from '@pages/not-found/not-found.component';
import { PayReminderConfigurationComponent } from '@pages/pay-reminder-configuration/pay-reminder-configuration.component';
import { SignInComponent } from '@pages/sign-in/sign-in.component';
import { SignUpComponent } from '@pages/sign-up/sign-up.component';
import { authGuard } from '@shared/guards/auth.guard';
import { roleGuard } from '@shared/guards/role.guard';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
    },
    {
        path: 'home',
        loadComponent: () => import('@pages/home/home.component').then(m => m.HomeComponent)
    },
    {
        path: 'raffles',
        loadComponent: () => import('@pages/raffles/raffles.component').then(m => m.RafflesComponent)
    },
    {
        path: 'raffle/:id',
        children: [
            {
                path: 'tickets',
                loadComponent: () => import('@pages/raffle-tickets/raffle-tickets.component').then(m => m.RaffleTicketsComponent)
            }
        ]
    },
    {
        path: 'admin',
        canActivate: [authGuard, roleGuard],
        children: [
            {
                path: '',
                redirectTo: 'my-raffles',
                pathMatch: 'full',
            },
            {
                path: 'my-raffles',
                component: MyRafflesComponent,
            },
            {
                path: 'create-raffle',
                component: CreateRaffleComponent,
            },
            {
                path: 'reminder-config',
                component: PayReminderConfigurationComponent,
            },
            {
                path: 'raffle/:id',
                children: [
                    {
                        path: 'edit',
                        component: ModifyRaffleComponent,
                    }
                ]
            }
        ]
    },
    {
        path: 'sign-in',
        component: SignInComponent,
    },
    {
        path: 'sign-up',
        component: SignUpComponent,
    },
    {
        path: '**',
        component: NotFoundComponent,
    }
];
