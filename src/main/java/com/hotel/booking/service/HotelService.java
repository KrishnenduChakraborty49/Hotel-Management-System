package com.hotel.booking.service;
import com.hotel.booking.dto.HotelDto;
import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto, String ownerEmail);
    HotelDto getHotelById(Long id);
    List<HotelDto> getAllHotels();
    HotelDto updateHotel(Long id, HotelDto hotelDto);
    void deleteHotel(Long id);
}
