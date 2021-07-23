package com.aubert.booking.service;

import com.aubert.booking.dao.BookingRepository;
import com.aubert.booking.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class BookingService {
  @Autowired BookingRepository bookingRepository;

  public List<Booking> getBookingsByCourtNumber(int courtNumber) {
    return bookingRepository.findBynumCourt(courtNumber);
  }

  public List<String> initJours() {
    ArrayList<String> jours = new ArrayList<>();
    jours.add("Lundi");
    jours.add("Mardi");
    jours.add("Mercredi");
    jours.add("Jeudi");
    jours.add("Vendredi");
    jours.add("Samedi");
    return jours;
  }

  public void addBooking(Booking newBooking) {
      bookingRepository.save(newBooking);
  }
  //quand la résa n'existe pas on retourne true
  public boolean checkBooking (Booking newBooking){
    List<Booking> bookingList =
            bookingRepository.findByNumCourtAndJourAndHeureDebutAndHeureFinAndPlayerName(
                    newBooking.getNumCourt(),
                    newBooking.getJour(),
                    newBooking.getHeureDebut(),
                    newBooking.getHeureFin(),
                    newBooking.getPlayerName());
    return (bookingList.isEmpty());
  }

  public void removeBooking(int id){
    bookingRepository.deleteById(id);
  }
}
