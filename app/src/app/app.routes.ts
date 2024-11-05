import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CreateRaffleComponent } from './pages/create-raffle/create-raffle.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { MyRafflesComponent } from './pages/my-raffles/my-raffles.component';
import { ModifyRaffleComponent } from './pages/modify-raffle/modify-raffle.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
    {
        path: 'sign-in',
        component: SignInComponent
    },
    {
        path: 'home',
        component: HomeComponent,
    },
    {
        path: 'admin',
        canActivate: [() => true],//TODO: Implement AuthGuard
        children: [
            {
                path: '',
                redirectTo: 'my-raffles',
                pathMatch: 'full'
            },
            {
                path: 'my-raffles',
                component: MyRafflesComponent
            },
            {
                path: 'create-raffle',
                component: CreateRaffleComponent
            },
        ]
    },
    {
      path: 'raffle/:id',
      children: [
        {
          path: 'edit',
          component: ModifyRaffleComponent
        }
      ]
    }
];
