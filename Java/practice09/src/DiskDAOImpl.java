import java.util.ArrayList;
import java.util.List;

import beans.Disks;

public class DiskDAOImpl implements DiskDAO {
    @Override
    public List<Disks> selectDisks() {
        ArrayList<ArrayList<String>> table;

        List<Disks> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectAllDisk();
        table = sqlsyntax.dbConnection(sql,"select");

        for(ArrayList<String> rec : table){
            Disks disks = new Disks();
            disks.setId(Integer.parseInt(rec.get(0)));
            disks.setName(rec.get(1));
            disks.setGenre(rec.get(2));
            disks.setActor(rec.get(3));
            result.add(disks);
        }

        return result;
    }

    public List<Disks> selectDisksnotLended() {
        ArrayList<ArrayList<String>> table;

        List<Disks> result = new ArrayList<>();
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.selectDisknotLended();
        table = sqlsyntax.dbConnection(sql,"select");

        for(ArrayList<String> rec : table){
            Disks disks = new Disks();
            disks.setId(Integer.parseInt(rec.get(0)));
            disks.setName(rec.get(1));
            disks.setGenre(rec.get(2));
            disks.setActor(rec.get(3));
            result.add(disks);
        }

        return result;
    }

    @Override
    public void insertDisks(String name, String genre, String actor) {
        BuildSQL sqlsyntax = new BuildSQL();

        String sql = sqlsyntax.insertDisk();
        sqlsyntax.dbConnection(sql, "insert", name, genre, actor);
    }
}
