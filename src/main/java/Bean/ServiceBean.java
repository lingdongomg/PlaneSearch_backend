package Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(alphabetic = true)
public class ServiceBean {
    @JsonProperty("planeList")
    private List<ServicePlane> planeList;

    public List<ServicePlane> getPlaneList() {
        return planeList;
    }

    public Integer getSumprice() {
        return sumprice;
    }

    @JsonProperty("agencies")
    private StringBuilder agencies;
    @JsonProperty("sumprice")
    private Integer sumprice;

    public ServiceBean(Integer sumprice) {
        this.sumprice = sumprice;
        this.planeList = new ArrayList<>();
        this.agencies = new StringBuilder();
    }

    public void addPlane(ServicePlane plane) {
        planeList.add(plane);
    }

    public void addAgencies(String agencies) {
        this.agencies.append(agencies).append(",");
    }

    public String getAgencies() {
        return agencies.substring(0, agencies.length() - 1);
    }

    public void setPlaneList(List<ServicePlane> planeList) {
        this.planeList = planeList;
    }

    public void setAgencies(StringBuilder agencies) {
        this.agencies = agencies;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(sumprice + "\t" + agencies.toString() + "\t");
        for (ServicePlane plane : planeList) {
            s.append(plane.getCarrier()).append(plane.getFlightNO()).append(plane.getDepartureDatetime()).append(plane.getArrivalDatetime()).append(",");
        }
        return s.toString();
    }
}
