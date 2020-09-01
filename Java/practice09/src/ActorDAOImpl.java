import java.util.ArrayList;
import java.util.List;

import beans.Actors;

public class ActorDAOImpl implements ActorDAO {
    @Override
    public List<Actors> selectActors() {
        ArrayList<ArrayList<String>> table;

        List<Actors> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectAllActor();
        table = sqlsyntax.dbConnection(sql,"select");

        for(ArrayList<String> rec : table){
            Actors actors = new Actors();
            actors.setId(Integer.parseInt(rec.get(0)));
            actors.setName(rec.get(1));
            result.add(actors);
        }

        return result;
    }
    public void insertActors(String name) {
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.insertActor();
        sqlsyntax.dbConnection(sql, "insert", name);
    }

    public List<Actors> selectDvdActors() {
        ArrayList<ArrayList<String>> table;

        List<Actors> result = new ArrayList<>();

        BuildSQL sqlsyntax = new BuildSQL();
        return result;
    }
}
