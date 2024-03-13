import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "./auth.service";

export const adminGuard: CanActivateFn = (route, state) => {
  const loginService = inject(AuthService);
  const router = inject(Router);

  if (localStorage.getItem('token') !== null && loginService.isAuthenticated() && loginService.isAdmin()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }};
