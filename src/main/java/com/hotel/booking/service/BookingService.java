package com.hotel.booking.service;
import com.hotel.booking.dto.BookingDto;
import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto, String userEmail);
    List<BookingDto> getUserBookings(String userEmail);
    BookingDto cancelBooking(Long bookingId, String userEmail);
}
