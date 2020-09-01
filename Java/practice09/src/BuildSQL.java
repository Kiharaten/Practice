public class BuildSQL extends DatabaseOperation{
    private String sql;
    private StringBuilder sb = new StringBuilder(100);

    public String selectAllActor() {
        sb.append("SELECT ");
        sb.append("id, ");
        sb.append("name ");
        sb.append("FROM ");
        sb.append("actor;");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String insertActor() {
        sb.append("INSERT INTO actor ( ");
        sb.append("id, ");
        sb.append("name ");
        sb.append(") SELECT ");
        sb.append("max(id) + 1, ");
        sb.append("? ");
        sb.append("FROM ");
        sb.append("actor;");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectAllFriend() {
        sb.append("SELECT ");
        sb.append("id, ");
        sb.append("name, ");
        sb.append("mail ");
        sb.append("FROM ");
        sb.append("friend;");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String insertFriend() {
        sb.append("INSERT INTO friend ( ");
        sb.append("id, ");
        sb.append("name, ");
        sb.append("mail ");
        sb.append(") SELECT  ");
        sb.append("max(id) + 1, ");
        sb.append("?, ");
        sb.append("? ");
        sb.append("FROM ");
        sb.append("friend;");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectAllGenre() {
        sb.append("SELECT ");
        sb.append("id, ");
        sb.append("name ");
        sb.append("FROM ");
        sb.append("Genre;");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String insertGenre() {
        sb.append("INSERT INTO genre ( ");
        sb.append("id, ");
        sb.append("name ");
        sb.append(") SELECT  ");
        sb.append("max(id) + 1, ");
        sb.append("? ");
        sb.append("FROM ");
        sb.append("genre;");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectAllDisk() {
        sb.append("SELECT ");
        sb.append("id, ");
        sb.append("name, ");
        sb.append("genre, ");
        sb.append("actor ");
        sb.append("FROM ");
        sb.append("disk;");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectDisknotLended() {
    	sb.append("SELECT disk.id, disk.name, disk.genre, disk.actor, max(rental.status_id) ");
    	sb.append("FROM disk ");
    	sb.append("LEFT OUTER JOIN rental ");
    	sb.append("ON disk.id = rental.disk_id ");
    	sb.append("GROUP BY disk.id, disk.name, disk.genre, disk.actor ");
    	sb.append("HAVING max(rental.status_id) = 0 or max(rental.status_id) IS NULL; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String insertDisk() {
        sb.append("INSERT INTO disk ( ");
        sb.append("id, ");
        sb.append("name, ");
        sb.append("genre, ");
        sb.append("actor ");
        sb.append(") SELECT  ");
        sb.append("max(id) + 1, ");
        sb.append("?, ");
        sb.append("?, ");
        sb.append("? ");
        sb.append("FROM disk ");
        sb.append("; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectAllRentaleachNumber() {
        sb.append("SELECT rental.number, ");
        sb.append("rental.date, ");
        sb.append("friend.name, ");
        sb.append("count(*) ");
        sb.append("FROM rental ");
        sb.append("INNER JOIN friend ");
        sb.append("ON friend_id = friend.id ");
        sb.append("WHERE rental.status_id = 1 ");
        sb.append("GROUP BY rental.number, ");
        sb.append("rental.date, ");
        sb.append("friend.name; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectRentalsDetail() {
        sb.append("SELECT rental.id, ");
        sb.append("rental.number, ");
        sb.append("rental.date, ");
        sb.append("disk.name, ");
        sb.append("status.name, ");
        sb.append("friend.name ");
        sb.append("FROM rental ");
        sb.append("INNER JOIN disk ");
        sb.append("ON disk_id = disk.id ");
        sb.append("INNER JOIN status ");
        sb.append("ON status_id = status.id ");
        sb.append("INNER JOIN friend ");
        sb.append("ON friend_id = friend.id ");
        sb.append("WHERE rental.number = ?; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;

    }
    public String returnRental() {
        sb.append("UPDATE rental ");
        sb.append("SET   status_id = 0  ");
        sb.append("WHERE rental.id = ? AND status_id = 1; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectMaxNumber() {
        sb.append("SELECT ");
        sb.append("max(number) + 1 ");
        sb.append("FROM ");
        sb.append("rental; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String insertRental() {
    	sb.append("INSERT INTO rental ( ");
        sb.append("id, ");
        sb.append("number, ");
        sb.append("date, ");
        sb.append("disk_id, ");
        sb.append("friend_id, ");
        sb.append("status_id ");
        sb.append(") SELECT ");
        sb.append("max(rental.id) + 1, ");
        sb.append("?, ");
        sb.append("?, ");
        sb.append("(select disk.id from disk where disk.name = ?), ");
        sb.append("(select friend.id from friend where friend.name = ?), ");
        sb.append("1 ");
        sb.append("FROM rental ");
        sb.append("; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }

    public String selectRentalHistory() {
        sb.append("SELECT rental.id, rental.number, rental.date, friend.name, disk.name, status.name ");
        sb.append("FROM rental ");
        sb.append("INNER JOIN disk ");
        sb.append("ON disk_id = disk.id ");
        sb.append("INNER JOIN friend ");
        sb.append("ON friend_id = friend.id ");
        sb.append("INNER JOIN status ");
        sb.append("ON status_id = status.id; ");
        this.sql = sb.toString();

        System.out.println("StringBuilderで作ったSQL文：" + this.sql);
        return this.sql;
    }
}
