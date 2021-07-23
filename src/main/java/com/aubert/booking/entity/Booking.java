package com.aubert.booking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "reservation")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int numCourt;
    @Column
    private String jour;
    @Column
    private String heureDebut;
    @Column
    private String heureFin;
    @Column
    private String playerName;

    public Booking(int numCourt, String jour, String heureDebut, String heureFin, String playerName) {
        this.numCourt = numCourt;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.playerName = playerName;
    }
}
