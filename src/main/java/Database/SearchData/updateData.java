package Database.SearchData;

import Bean.Seat;
import GlobalVar.Lock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class updateData {
    public void updateSeat(List<Seat> seats) {
        Lock.lock = true;
        try {
            Thread.sleep(10000);
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:plane1.db");
            connection.setAutoCommit(false);
            String sql;
            Statement statement = connection.createStatement();
            for (Seat seat : seats) {
                if(seat.getSeatF()!=null){
                    sql = String.format("update seatinfo%d set seatF='%s' where carrier='%s' and flightNO=%d and departure='%s' and arrival='%s' and departureDate=%d",
                            seat.getDepartureDate() / 100, seat.getSeatF(), seat.getCarrier(),
                            seat.getFlightNO(), seat.getDeparture(), seat.getArrival(), seat.getDepartureDate());
                    statement.executeLargeUpdate(sql);
                }
                if(seat.getSeatY()!=null){
                    sql = String.format("update seatinfo%d set seatY='%s' where carrier='%s' and flightNO=%d and departure='%s' and arrival='%s' and departureDate=%d",
                            seat.getDepartureDate() / 100, seat.getSeatY(), seat.getCarrier(),
                            seat.getFlightNO(), seat.getDeparture(), seat.getArrival(), seat.getDepartureDate());
                    statement.executeLargeUpdate(sql);
                }
                if(seat.getSeatC()!=null){
                    sql = String.format("update seatinfo%d set seatC='%s' where carrier='%s' and flightNO=%d and departure='%s' and arrival='%s' and departureDate=%d",
                            seat.getDepartureDate() / 100, seat.getSeatC(), seat.getCarrier(),
                            seat.getFlightNO(), seat.getDeparture(), seat.getArrival(), seat.getDepartureDate());
                    statement.executeLargeUpdate(sql);
                }
            }
            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Lock.lock = false;
        try {
            Thread.sleep(10000);
            Connection connection = DriverManager.getConnection("jdbc:sqlite:plane2.db");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql;
            for (Seat seat : seats) {
                if(seat.getSeatF()!=null){
                    sql = String.format("update seatinfo%d set seatF='%s' where carrier='%s' and flightNO=%d and departure='%s' and arrival='%s' and departureDate=%d",
                            seat.getDepartureDate() / 100, seat.getSeatF(), seat.getCarrier(),
                            seat.getFlightNO(), seat.getDeparture(), seat.getArrival(), seat.getDepartureDate());
                    statement.executeLargeUpdate(sql);
                }
                if(seat.getSeatY()!=null){
                    sql = String.format("update seatinfo%d set seatY='%s' where carrier='%s' and flightNO=%d and departure='%s' and arrival='%s' and departureDate=%d",
                            seat.getDepartureDate() / 100, seat.getSeatY(), seat.getCarrier(),
                            seat.getFlightNO(), seat.getDeparture(), seat.getArrival(), seat.getDepartureDate());
                    statement.executeLargeUpdate(sql);
                }
                if(seat.getSeatC()!=null){
                    sql = String.format("update seatinfo%d set seatC='%s' where carrier='%s' and flightNO=%d and departure='%s' and arrival='%s' and departureDate=%d",
                            seat.getDepartureDate() / 100, seat.getSeatC(), seat.getCarrier(),
                            seat.getFlightNO(), seat.getDeparture(), seat.getArrival(), seat.getDepartureDate());
                    statement.executeLargeUpdate(sql);
                }
            }
            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
