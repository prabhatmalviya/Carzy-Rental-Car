export interface Car {
  id: number;
  brand: string;
  model: string;
  year: number;
  pricePerDay: number;
  available: boolean;
  fuelType: string;
  transmission: string;
}

export interface BookingRequest {
  carId: number;
  customerName: string;
  customerEmail: string;
  startDate: string;
  endDate: string;
}
