package com.carzy.rental.service;

import com.carzy.rental.dto.BookingRequest;
import com.carzy.rental.entity.Booking;
import com.carzy.rental.entity.BookingStatus;
import com.carzy.rental.entity.Car;
import com.carzy.rental.repository.BookingRepository;
import com.carzy.rental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;

    public BookingService(BookingRepository bookingRepository, CarRepository carRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
    }

    public Booking createBooking(BookingRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        if (!car.isAvailable()) {
            throw new IllegalStateException("Selected car is not available");
        }

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        if (days == 0) {
            days = 1;
        }

        Booking booking = new Booking();
        booking.setCar(car);
        booking.setCustomerName(request.getCustomerName());
        booking.setCustomerEmail(request.getCustomerEmail());
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setTotalAmount(car.getPricePerDay().multiply(BigDecimal.valueOf(days)));
        booking.setStatus(BookingStatus.CONFIRMED);

        car.setAvailable(false);
        carRepository.save(car);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
