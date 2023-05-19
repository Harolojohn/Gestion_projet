import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = 'fdsfr54sag47sgddsgs58dgfrtd5s'; // Remplacez par votre token d'accès

    // Clonez la requête et ajoutez l'en-tête d'autorisation
    const modifiedRequest = request.clone({
      setHeaders: {
        'accept': 'application/json',
        'Authorization': 'Bearer fdsfr54sag47sgddsgs58dgfrtd5s'
      }
    });

    // Passez la requête modifiée au prochain gestionnaire
    return next.handle(modifiedRequest);
  }
}
