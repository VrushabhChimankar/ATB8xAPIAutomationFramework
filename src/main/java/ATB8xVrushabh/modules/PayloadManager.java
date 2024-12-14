package ATB8xVrushabh.modules;

import ATB8xVrushabh.pojos.*;
import com.google.gson.Gson;

public class PayloadManager {

    Gson gson;

    public String CreateBookingPayladAsString() {


        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-02-01");
        bookingDates.setCheckout("2024-02-01");

        booking.setBookingDates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        gson = new Gson();
        String jsonStringPayload = gson.toJson(booking);
        System.out.println(jsonStringPayload);
        return jsonStringPayload;

    }


       public BookingResponse bookingResponse(String responseString) {

           gson = new Gson();
           BookingResponse bookingResponse1 = gson.fromJson(responseString, BookingResponse.class);
           return bookingResponse1;

        }


        public Booking GetResponseFromJSON(String getResponse){

        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;


    }

    public String setAuthPayload(){

        //Get token
        //Auth object to json String
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        gson = new Gson();

        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the: " + jsonPayloadString);
        return jsonPayloadString;

    }


    public String getTokenFromJSON(String TokenResp){

        gson = new Gson();
        TokenResponse tokenResponse = gson.fromJson(TokenResp, TokenResponse.class);
        return tokenResponse.getToken();

    }

    public String fullUpdatePayloadAsString() {

        Booking booking = new Booking();
        booking.setFirstname("Vrushabh");
        booking.setLastname("Chimankar");
        booking.setTotalprice(118);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);

    }















}
