package com.aubert.booking.dao;

import com.aubert.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
  List<Booking> findBynumCourt(int courtNumber);

  List<Booking> findByNumCourtAndJourAndHeureDebutAndHeureFinAndPlayerName(
      int numCourt, String jour, String heureDebut, String heureFin, String playerName);
}
