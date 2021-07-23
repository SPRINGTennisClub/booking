package com.aubert.booking.controller;

import com.aubert.booking.entity.Booking;
import com.aubert.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class ViewController {
  @Autowired BookingService bookingService;

  @PutMapping("/bookCourt/{numCourt}")
  public String bookCourt(Model model, @PathVariable int numCourt) {
    model.addAttribute("court", numCourt);
    model.addAttribute("bookings", bookingService.getBookingsByCourtNumber(numCourt));
    return "resaCourt";
  }

  @PutMapping("/showAddBooking/{court}")
  public String initBooking(Model model, @PathVariable int court) {
    model.addAttribute(new Booking(court, "", "", "", ""));
    model.addAttribute("jours", bookingService.initJours());
    return "addBooking";
  }

  @PostMapping("/addBooking")
  public String addBooking(Model model, @ModelAttribute("booking") Booking newBooking) {
    if (bookingService.checkBooking(newBooking)) {
      bookingService.addBooking(
          new Booking(
              newBooking.getNumCourt(),
              newBooking.getJour(),
              newBooking.getHeureDebut(),
              newBooking.getHeureFin(),
              newBooking.getPlayerName()));
      //pour retourner à la page de réservation il faut renvoyer le numéro du court et la liste de ses réservations
      model.addAttribute("court", newBooking.getNumCourt());
      model.addAttribute(
          "bookings", bookingService.getBookingsByCourtNumber(newBooking.getNumCourt()));
      return "resaCourt";
    } else {
      return "errorBooking";
    }
  }

  @DeleteMapping("/removeBooking/{booking}")
  public String removeBooking(Model model, @PathVariable Booking booking) {
    bookingService.removeBooking(booking.getId());
    //pour retourner à la page de réservation il faut renvoyer le numéro du court et la liste de ses réservations
    model.addAttribute("court", booking.getNumCourt());
    model.addAttribute("bookings", bookingService.getBookingsByCourtNumber(booking.getNumCourt()));
    return "resaCourt";
  }
}
