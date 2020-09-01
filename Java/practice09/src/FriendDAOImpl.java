import java.util.ArrayList;
import java.util.List;

import beans.Friends;

public class FriendDAOImpl implements FriendDAO {
    @Override
    public List<Friends> selectFriends() {
        ArrayList<ArrayList<String>> table;

        List<Friends> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectAllFriend();
        table = sqlsyntax.dbConnection(sql,"select");

        for(ArrayList<String> rec : table){
            Friends friends = new Friends();

            friends.setId(Integer.parseInt(rec.get(0)));
            friends.setName(rec.get(1));
            friends.setMail(rec.get(2));
            result.add(friends);
        }

        return result;
    }

    public void insertFriends(String name, String mail) {
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.insertFriend();
        sqlsyntax.dbConnection(sql, "insert", name, mail);
    }
}
