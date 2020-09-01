import java.util.ArrayList;
import java.util.List;

import beans.Genres;

public class GenreDAOImpl implements GenreDAO{
	@Override
	public List<Genres> selectGenres() {
		ArrayList<ArrayList<String>> table;

        List<Genres> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectAllGenre();
        table = sqlsyntax.dbConnection(sql,"select");

        for(ArrayList<String> rec : table){
        	Genres genres = new Genres();

        	genres.setId(Integer.parseInt(rec.get(0)));
        	genres.setName(rec.get(1));
            result.add(genres);
        }

        return result;
	}

	@Override
	public void insertGenres(String name) {
	    BuildSQL sqlsyntax = new BuildSQL();

	    String sql = sqlsyntax.insertGenre();
	    sqlsyntax.dbConnection(sql, "insert", name);
    }
}
