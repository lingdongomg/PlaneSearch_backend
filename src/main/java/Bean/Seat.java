package Bean;

public class Seat {
    private final String carrier;
    private final Integer flightNO;
    private final String departure;
    private final String arrival;
    private final Integer departureDate;
    private String seatF;
    private String seatC;
    private String seatY;

    public Seat(String carrier, Integer flightNO, String departure, String arrival, Integer departureDate) {
        this.carrier = carrier;
        this.flightNO = flightNO;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public Integer getFlightNO() {
        return flightNO;
    }

    public String getDeparture() {
        return departure;
    }


    public String getArrival() {
        return arrival;
    }

    public Integer getDepartureDate() {
        return departureDate;
    }

    public String getSeatF() {
        return seatF;
    }

    public String getSeatC() {
        return seatC;
    }

    public String getSeatY() {
        return seatY;
    }
}
