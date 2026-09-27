import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarService } from './car.service';
import { Car } from './car.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  private readonly carService = inject(CarService);
  private readonly fb = inject(FormBuilder);

  cars: Car[] = [];
  selectedCar: Car | null = null;
  message = '';

  bookingForm = this.fb.nonNullable.group({
    customerName: ['', Validators.required],
    customerEmail: ['', [Validators.required, Validators.email]],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required]
  });

  ngOnInit(): void {
    this.loadCars();
  }

  loadCars(): void {
    this.carService.getAvailableCars().subscribe({
      next: cars => this.cars = cars,
      error: () => this.message = 'Unable to load cars. Please start the Spring Boot backend.'
    });
  }

  selectCar(car: Car): void {
    this.selectedCar = car;
    this.message = '';
  }

  bookCar(): void {
    if (!this.selectedCar || this.bookingForm.invalid) {
      this.bookingForm.markAllAsTouched();
      return;
    }

    const form = this.bookingForm.getRawValue();

    this.carService.createBooking({
      carId: this.selectedCar.id,
      customerName: form.customerName,
      customerEmail: form.customerEmail,
      startDate: form.startDate,
      endDate: form.endDate
    }).subscribe({
      next: () => {
        this.message = 'Booking confirmed successfully.';
        this.bookingForm.reset();
        this.selectedCar = null;
        this.loadCars();
      },
      error: err => {
        this.message = err?.error?.message ?? 'Booking could not be completed.';
      }
    });
  }
}
