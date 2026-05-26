package com.hotel.booking.service.impl;

import com.hotel.booking.dto.HotelDto;
import com.hotel.booking.entity.Hotel;
import com.hotel.booking.entity.User;
import com.hotel.booking.exception.ResourceNotFoundException;
import com.hotel.booking.repository.HotelRepository;
import com.hotel.booking.repository.UserRepository;
import com.hotel.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public HotelDto createHotel(HotelDto hotelDto, String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setCity(hotelDto.getCity());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setOwner(owner);

        Hotel savedHotel = hotelRepository.save(hotel);
        return mapToDto(savedHotel);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
        return mapToDto(hotel);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public HotelDto updateHotel(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
        
        hotel.setName(hotelDto.getName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setCity(hotelDto.getCity());
        hotel.setAddress(hotelDto.getAddress());
        
        Hotel updatedHotel = hotelRepository.save(hotel);
        return mapToDto(updatedHotel);
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
        hotelRepository.delete(hotel);
    }

    private HotelDto mapToDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setCity(hotel.getCity());
        dto.setAddress(hotel.getAddress());
        dto.setOwnerId(hotel.getOwner().getId());
        return dto;
    }
}
