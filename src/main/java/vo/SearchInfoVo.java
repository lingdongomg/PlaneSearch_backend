package vo;

import java.util.List;

public class SearchInfoVo {
    /**
     * 代理人列表
     */
    private String agencies;

    /**
     * 人数
     */
    private int people;

    /**
     * 前端发来的所有需要查询的航班的信息
     */
    List<BaseFlightInfo> planeList;

    /**
     * 页码
     */
    private Integer k;

    /**
     * 用户输入的总记录数
     */
    private Integer kkkk;

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getKkkk() {
        return kkkk;
    }

    public void setKkkk(Integer kkkk) {
        this.kkkk = kkkk;
    }

    public String getAgencies() {
        return agencies;
    }

    public void setAgencies(String agencies) {
        this.agencies = agencies;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public List<BaseFlightInfo> getPlaneList() {
        return planeList;
    }

    public void setPlaneList(List<BaseFlightInfo> planeList) {
        this.planeList = planeList;
    }

    public SearchInfoVo() {
    }

    public SearchInfoVo(String agencies, int personNum, List<BaseFlightInfo> searchOptionFlightInfos) {
        this.agencies = agencies;
        this.people = personNum;
        this.planeList = searchOptionFlightInfos;
    }

}
