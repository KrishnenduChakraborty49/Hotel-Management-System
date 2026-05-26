package com.hotel.booking.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class RoomDto {
    private Long id;
    private Long hotelId;
    private String roomType;
    private BigDecimal pricePerNight;
    private Integer maxOccupancy;
    private Integer totalCount;
}
