public class DaoFactory {
    public ActorDAO getActorDAO() {
        return new ActorDAOImpl();
    }
    public DiskDAO getDiskDAO() {
        return new DiskDAOImpl();
    }
    public FriendDAO getFriendDAO(){
        return new FriendDAOImpl();
    }
    public GenreDAO getGenreDAO() {
    	return new GenreDAOImpl();
    }
    public RentalDAO getRentalDAO() {
        return new RentalDAOImpl();
    }
}
