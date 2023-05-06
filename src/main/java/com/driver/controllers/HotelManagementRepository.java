package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.*;

@Repository
public class HotelManagementRepository {
    HashMap<String , Hotel> hotelDb = new HashMap<>();
    HashMap<String , User> userDb = new HashMap<>();
    HashMap<Integer , List<Booking>> bookingDb = new HashMap<>();



    public String addHotel(Hotel hotel) {
        if(hotel.getHotelName()==null || hotel==null || hotelDb.containsKey(hotel.getHotelName()))
        {
            return "FAILURE";
        }
        hotelDb.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }

    public int addUser(User user) {
        userDb.put(user.getName(),user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        List<String> l= new ArrayList<>();
        int max=Integer.MIN_VALUE;
        for (String name :hotelDb.keySet()) {
            max=Math.max(hotelDb.get(name).getFacilities().size(),max);
        }


        for (String name :hotelDb.keySet()) {
            if(hotelDb.get(name).getFacilities().size()==max)
            {
                l.add(name);
            }
        }

        if(max==0)
            return "";
        Collections.sort(l);
        return l.get(0);
    }

    public int bookARoom(Booking booking) {
//        int key = booking.getBookingAadharCard();
//        List<Booking> l;
//        if(!bookingDb.containsKey(key))
//        {
//            l = new ArrayList<>();
//        }
//        else {
//            l =bookingDb.get(booking.getBookingAadharCard());
//        }
//        l.add(booking);
//        bookingDb.put(key, l);
//        if (hotelDb.get(booking.getHotelName()).getAvailableRooms() < booking.getNoOfRooms()) {
//            return -1;
//        }
//        int ans = booking.getNoOfRooms() * hotelDb.get(booking.getHotelName()).getPricePerNight();
//        return ans;

        if(hotelDb.containsKey(booking.getHotelName())){
            Hotel hotel = hotelDb.get(booking.getHotelName());
            if(hotel.getAvailableRooms() >= booking.getNoOfRooms()){
                booking.setBookingId(String.valueOf(UUID.randomUUID()));
                booking.setAmountToBePaid(hotel.getPricePerNight() * booking.getNoOfRooms());
                hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());
                hotelDb.put(hotel.getHotelName() , hotel);
                if(bookingDb.containsKey(booking.getBookingAadharCard())){
                    List<Booking> bookingList = bookingDb.get(booking.getBookingAadharCard());
                    bookingList.add(booking);
                    bookingDb.put(booking.getBookingAadharCard(),bookingList);
                } else{
                    List<Booking> bookingList = new ArrayList<>();
                    bookingList.add(booking);
                    bookingDb.put(booking.getBookingAadharCard(),bookingList);
                }
                return booking.getAmountToBePaid();
            } else {
                return -1;
            }
        }
        return -1;
    }

    public int getBookings(Integer aadharCard) {
        int count  =0 ;
        for (Integer num :bookingDb.keySet()) {
            if(num==aadharCard)
                return bookingDb.get(aadharCard).size();
        }
        return 0;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        if(hotelDb.containsKey(hotelName))
        {
            Hotel hotel = hotelDb.get(hotelName);
            List<Facility> l=hotel.getFacilities();
            for (Facility f :newFacilities) {
                if (!l.contains(f))
                {
                    l.add(f);
                }
            }
            hotelDb.put(hotelName,hotel);
            return hotel;
        }
        return null;
    }
}
