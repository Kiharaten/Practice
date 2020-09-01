import beans.Friends;

import java.util.List;

public interface FriendDAO {
    List<Friends> selectFriends();
    void insertFriends(String name, String mail);
}
