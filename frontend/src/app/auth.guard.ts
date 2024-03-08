import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {LoginService} from "./login.service";
import {concatMap} from "rxjs";

export const authGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);
  let guard: boolean = false;

  if (localStorage.getItem('token') !== null) {
    guard = loginService.checkalive(localStorage.getItem('token') ?? '').pipe( concatMap(res => {
      return res.status;
    }))
  } else {
    console.log('check');
  }
  console.log('guard', guard)

  return guard;
};
