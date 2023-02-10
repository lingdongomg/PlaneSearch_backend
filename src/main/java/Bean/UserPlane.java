package Bean;

public class UserPlane {
    private final String departure;
    private final String arrival;
    private final Integer departureDate;

    public UserPlane(String departure, String arrival, Integer departureDate) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
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
}
