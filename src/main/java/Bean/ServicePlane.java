package Bean;

public class ServicePlane {
    private final String carrier;
    private final Integer flightNO;
    private final Long departureDatetime;
    private final Long arrivalDatetime;
    private final String seat;

    public ServicePlane(String carrier, Integer flightNO, Long departureDatetime, Long arrivalDatetime, String seat) {
        this.carrier = carrier;
        this.flightNO = flightNO;
        this.departureDatetime = departureDatetime;
        this.arrivalDatetime = arrivalDatetime;
        this.seat = seat;
    }

    public String getCarrier() {
        return carrier;
    }

    public Integer getFlightNO() {
        return flightNO;
    }

    public Long getDepartureDatetime() {
        return departureDatetime;
    }

    public Long getArrivalDatetime() {
        return arrivalDatetime;
    }

    public String getSeat() {
        return seat;
    }
}