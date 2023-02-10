package Bean;

import java.io.Serializable;
import java.util.List;

// 传入的消息
public class UserBean implements Serializable {
    private List<UserPlane> planeList;
    private Integer k;
    private Integer people;
    private String agencies;

    public List<UserPlane> getPlaneList() {
        return planeList;
    }

    public void setPlaneList(List<UserPlane> planeList) {
        this.planeList = planeList;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getAgencies() {
        return agencies;
    }

    public void setAgencies(String agencies) {
        this.agencies = agencies;
    }

    public UserBean(List<UserPlane> planeList, Integer k, Integer people, String agencies) {
        this.planeList = planeList;
        this.k = k;
        this.people = people;
        this.agencies = agencies;
    }
}
