import java.util.List;

import beans.Disks;

public interface DiskDAO {
	List<Disks> selectDisks();
	List<Disks> selectDisksnotLended();
	void insertDisks(String name,String genre,String actor);
}
