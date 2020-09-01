import java.util.List;

import beans.Genres;

public interface GenreDAO {
	List<Genres> selectGenres();
	void insertGenres(String name);
}
