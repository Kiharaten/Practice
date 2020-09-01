import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import beans.Rentals;

public class RentalDAOImpl implements RentalDAO {
	@Override
	public List<Rentals> selectRentalHistory() {
		ArrayList<ArrayList<String>> table;

        List<Rentals> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectRentalHistory();
        table = sqlsyntax.dbConnection(sql,"select");

        for(ArrayList<String> rec : table){
            Rentals rentals = new Rentals();

            rentals.setId(Integer.parseInt(rec.get(0)));
            rentals.setNumber(Integer.parseInt(rec.get(1)));
            rentals.setDate(rec.get(2));
            rentals.setDiskName(rec.get(3));
            rentals.setFriendName(rec.get(4));
            rentals.setStatusName(rec.get(5));
            result.add(rentals);
        }
		return result;
	}

    @Override
    public List<Rentals> selectRentals() {
        ArrayList<ArrayList<String>> table;

        List<Rentals> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectAllRentaleachNumber();
        table = sqlsyntax.dbConnection(sql,"select");

        for(ArrayList<String> rec : table){
            Rentals rentals = new Rentals();

            rentals.setNumber(Integer.parseInt(rec.get(0)));
            rentals.setDate(rec.get(1));
            rentals.setFriendName(rec.get(2));
            rentals.setCount(Integer.parseInt(rec.get(3)));
            result.add(rentals);
        }

        return result;
    }

    @Override
    public List<Rentals> selectRentalsDetail(String number) {
        ArrayList<ArrayList<String>> table;

        List<Rentals> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectRentalsDetail();
        System.out.println(sqlsyntax);
        table = sqlsyntax.dbConnection(sql,"select", number);

        for(ArrayList<String> rec : table){
            Rentals rentals = new Rentals();

            rentals.setId(Integer.parseInt(rec.get(0)));
            rentals.setNumber(Integer.parseInt(rec.get(1)));
            rentals.setDate(rec.get(2));
            rentals.setDiskName(rec.get(3));
            rentals.setStatusName(rec.get(4));
            rentals.setFriendName(rec.get(5));
            result.add(rentals);
        }
		return result;
    }

	@Override
	public void insertRental(List<String> diskList, String friendName) {

        BuildSQL sqlsyntax = new BuildSQL();
        String sql = sqlsyntax.selectMaxNumber();
		ArrayList<ArrayList<String>> getNumber = sqlsyntax.dbConnection(sql,"select");
        LocalDateTime date = LocalDateTime.now();

        String number = getNumber.get(0).get(0);
        String dateTime = date.toLocalDate() + " " + date.toLocalTime();

        for(String diskName : diskList) {
        	BuildSQL sqlsyntax2 = new BuildSQL();
        	String sql2 = sqlsyntax2.insertRental();
            sqlsyntax2.dbConnection(sql2, "insert", number, dateTime, diskName, friendName);
		}
	}

	@Override
	public void updateRentals(String id) {
		BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.returnRental();
        sqlsyntax.dbConnection(sql, "update", id);
	}
}
