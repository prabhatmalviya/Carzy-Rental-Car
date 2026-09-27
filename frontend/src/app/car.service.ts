import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car, BookingRequest } from './car.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CarService {
  private readonly http = inject(HttpClient);
  private readonly api = 'http://localhost:8080/api';

  getAvailableCars(): Observable<Car[]> {
    return this.http.get<Car[]>(`${this.api}/cars/available`);
  }

  createBooking(request: BookingRequest): Observable<unknown> {
    return this.http.post(`${this.api}/bookings`, request);
  }
}
