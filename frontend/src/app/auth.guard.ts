import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {LoginService} from "./login.service";

export const authGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);
  let guard: boolean = false;

  if (localStorage.getItem('token') !== null) {
    loginService.checkalive(localStorage.getItem('token') ?? '').subscribe(res => {
      guard = res.status;
    })
  } else {
    router.navigateByUrl('/login');
  }


  return guard;
};
