package com.example.bipanacsd214test2b;

public class Reservation_Details {

        private int ReservationID;
        private String CustomerName;
        private String ReservationDate;
        private int NumOfGuests;

        public Reservation_Details(int reservationID, String customerName, String reservationDate, int numOfGuests) {
            this.ReservationID = reservationID;
            this.CustomerName = customerName;
            this.ReservationDate = reservationDate;
            this.NumOfGuests = numOfGuests;

        }

        public int getReservationID() {
            return ReservationID;
        }

        public void setReservationID(int reservationID) {
            ReservationID = reservationID;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }

        public String getReservationDate() {
            return ReservationDate;
        }

        public void setReservationDate(String reservationDate) {
            ReservationDate = reservationDate;
        }

        public int getNumOfGuests() {
            return NumOfGuests;
        }

        public void setNumOfGuests(int numOfGuests) {
            NumOfGuests = numOfGuests;
        }
    }



