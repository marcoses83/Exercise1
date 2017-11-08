package com.spotahome.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SeatType {
    private Seat seat;

    public Seat getSeat() {
        return seat;
    }

    public void setChairHome(Seat seat) {
        setSeat(seat);
    }

    public void setChairOffice(Seat seat) {
        setSeat(seat);
    }

    public void setSofaLivingRoom(Seat seat) {
        setSeat(seat);
    }

    private void setSeat(Seat seat) {
        this.seat = seat;
    }
}
