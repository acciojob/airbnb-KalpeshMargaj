package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {

    HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();

    public String addHotel(Hotel hotel) {
        String ans = hotelManagementRepository.addHotel(hotel);
        return ans;
    }

    public int addUser(User user) {
        int ans = hotelManagementRepository.addUser(user);
        return ans;
    }

    public String getHotelWithMostFacilities() {
        String ans = hotelManagementRepository.getHotelWithMostFacilities();
        return ans;
    }

    public Integer bookARoom(Booking booking) {
        Integer ans = hotelManagementRepository.bookARoom(booking);
        return ans;
    }

    public int getBookings(Integer aadharCard) {
        return hotelManagementRepository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotelManagementRepository.updateFacilities(newFacilities,hotelName);
    }
}
