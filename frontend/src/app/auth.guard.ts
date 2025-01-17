import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "./auth.service";
import {concatMap} from "rxjs";

export const authGuard: CanActivateFn = (route, state) => {
  const loginService = inject(AuthService);
  const router = inject(Router);

  if (localStorage.getItem('token') !== null && loginService.isAuthenticated()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
