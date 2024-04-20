package org.ie.mizdooni.model;

import java.util.ArrayList;

public class ReservationHistoryResponseModel extends BaseModel{
    ArrayList<ReservationHistoryItemModel> reservationHistory;

    public ReservationHistoryResponseModel(ArrayList<ReservationHistoryItemModel> reservationHistory) {
        this.reservationHistory = reservationHistory;
    }

    public ArrayList<ReservationHistoryItemModel> getReservationHistory() {
        return reservationHistory;
    }

    public void setReservationHistory(ArrayList<ReservationHistoryItemModel> reservationHistory) {
        this.reservationHistory = reservationHistory;
    }
}
