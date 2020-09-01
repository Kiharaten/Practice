import beans.Actors;

import java.util.List;

public interface ActorDAO {
    List<Actors> selectActors();
    void insertActors(String name);
    List<Actors> selectDvdActors();
}
