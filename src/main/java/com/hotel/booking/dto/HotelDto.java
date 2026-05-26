package com.hotel.booking.dto;
import lombok.Data;
@Data
public class HotelDto {
    private Long id;
    private String name;
    private String description;
    private String city;
    private String address;
    private Long ownerId;
}
