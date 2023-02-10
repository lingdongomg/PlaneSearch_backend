package vo;

/**
 * 前端传来的一段航程的信息
 */
public class BaseFlightInfo {
    @Override
    public String toString() {
        return "BaseFlightInfo{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureTime=" + departureDate +
                '}';
    }

    /**
     * 起飞城市
     */
    private String departure;

    /**
     * 到达城市
     */
    private String arrival;

    /**
     * 起飞时间
     */
    private String departureDate;

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public BaseFlightInfo(String departure, String arrivalCity, String departureTime) {
        this.departure = departure;
        this.arrival = arrivalCity;
        this.departureDate = departureTime;
    }

    public BaseFlightInfo() {
    }
}
