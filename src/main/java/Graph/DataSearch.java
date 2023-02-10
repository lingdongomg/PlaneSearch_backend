package Graph;

import Bean.DatabasePlane;
import Bean.Tariff;
import Bean.UserPlane;
import Database.SearchData.SearchData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DataSearch {
    public HashMap<String, PriorityQueue<Tariff>> SearchTariff(String departure, String arrival) {
        SearchData searchData = new SearchData();
        return (HashMap<String, PriorityQueue<Tariff>>) searchData.searchTariff(departure, arrival);
    }

    public List<DatabasePlane> SearchPlane(UserPlane plane, int people) {
        SearchData searchData = new SearchData();
        return searchData.searchPlane(plane.getDeparture(), plane.getArrival(), plane.getDepartureDate(), people);
    }

    public Map<String, Integer> SearchFare(UserPlane plane) {
        SearchData searchData = new SearchData();
        return searchData.searchFare(plane.getDeparture(), plane.getArrival());
    }
}
