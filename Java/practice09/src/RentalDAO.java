import java.util.List;

import beans.Rentals;

public interface RentalDAO {
    List<Rentals> selectRentalHistory();
    List<Rentals> selectRentals();
    List<Rentals> selectRentalsDetail(String number);
    void updateRentals(String id);
    void insertRental(List<String> diskList, String friendName);

}
