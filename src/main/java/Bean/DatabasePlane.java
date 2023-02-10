package Bean;

public class DatabasePlane {
    private final String departure;
    private final String arrival;
    private final Integer departureDate;
    private final String carrier;
    private final Integer flightNO;
    private final Long departureDatetime;
    private final Long arrivalDatetime;
    private final String seat;

    private DatabasePlane(Builder builder) {
        departure = builder.departure;
        arrival = builder.arrival;
        departureDate = builder.departureDate;
        carrier = builder.carrier;
        flightNO = builder.flightNO;
        departureDatetime = builder.departureDatetime;
        arrivalDatetime = builder.arrivalDatetime;
        seat = builder.seat;
    }

    public String getSeat() {
        return seat;
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

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public Integer getDepartureDate() {
        return departureDate;
    }

    public static final class Builder {
        private String departure;
        private String arrival;
        private Integer departureDate;
        private String carrier;
        private Integer flightNO;
        private Long departureDatetime;
        private Long arrivalDatetime;
        private String seat;

        public Builder() {
        }

        public Builder departure(String val) {
            departure = val;
            return this;
        }
        public Builder arrival(String val) {
            arrival = val;
            return this;
        }
        public Builder departureDate(int val) {
            departureDate = val;
            return this;
        }
        public Builder carrier(String val) {
            carrier = val;
            return this;
        }
        public Builder flightNO(int val) {
            flightNO = val;
            return this;
        }
        public Builder departureDatetime(long val) {
            departureDatetime = val;
            return this;
        }
        public Builder arrivalDatetime(long val) {
            arrivalDatetime = val;
            return this;
        }

        public Builder seat(String val) {
            seat = val;
            return this;
        }

        public DatabasePlane build(){
            return new DatabasePlane(this);
        }
    }
}
