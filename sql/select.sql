-- show tables sql of 'DVD rental management system' in JV32 summer sutudy
-- source ~/Desktop/JV32_summerstudy/sql/select.sql;
USE practice09;

-- show created tables and show inserted data.
show tables;
show columns from status;
show columns from gender;
show columns from actor;
show columns from disk;
show columns from friend;
show columns from genre;
show columns from rental;

select * from status;
select * from gender;
select * from actor;
select * from disk;
select * from friend;
select * from genre;
select * from rental;

-- show number of not terminated.
SELECT rental.number as "貸出番号", friend.name as "貸出相手", COUNT(*) as "貸出中の数"
FROM rental
INNER JOIN friend
        ON friend_id = friend.id
WHERE rental.status_id = 1
GROUP BY rental.number, friend.name;

-- show rental status with rental number.
SELECT rental.number as "貸出番号", rental.date as "貸出日時", disk.title as "タイトル", friend.name as "貸出相手", status.name as "状態"
FROM rental
INNER JOIN disk
        ON disk_id = disk.id
INNER JOIN friend
        ON friend_id = friend.id
INNER JOIN status
        ON status_id = status.id
WHERE rental.number = "1";

-- show mailaddress with friend name.
SELECT friend.name as "貸出相手", friend.mail as "メールアドレス"
FROM friend
WHERE friend.name = "NAKAMURA";

-- show disk information with title genre actor
SELECT disk.title, disk.genre, disk.actor
FROM disk 
WHERE   disk.title LIKE "%A%"
    AND disk.genre LIKE "0"
    AND disk.actor LIKE "%,3,%";

-- check selected disk is not rented with disk.title
SELECT status.name as "状態"
FROM rental
INNER JOIN status 
        ON status_id = status.id
WHERE disk_id = (
    SELECT disk.id 
    FROM disk 
    WHERE disk.title = "AKIRA"
    );
-- if you want it can return 0 or null, use in 'if' syntax with changes "status.name => status.id".


-- --- EXAMPLE ZONE ---

-- show all rental status 
SELECT rental.number as "貸出番号", friend.name as "貸出相手", disk.title as "ディスクタイトル", status.name as "状態"
FROM rental
INNER JOIN disk
        ON disk_id = disk.id
INNER JOIN friend
        ON friend_id = friend.id
INNER JOIN status
        ON status_id = status.id;

-- show rental status with friend name (sample name "KIHARA" selected)
SELECT rental.number as "貸出番号", rental.date as "貸出日時", disk.title as "タイトル", friend.name as "貸出相手", status.name as "状態"
FROM rental
INNER JOIN disk
        ON disk_id = disk.id
INNER JOIN friend
        ON friend_id = friend.id
INNER JOIN status
        ON status_id = status.id
WHERE friend.name = "KIHARA";

-- show rental status with rental status
SELECT rental.id as "貸出ID", rental.number as "貸出番号", rental.date as "貸出日", disk.name as "ディスクタイトル", friend.name as "貸出相手", status.name as "状態"
FROM rental
INNER JOIN disk
        ON disk_id = disk.id
INNER JOIN friend
        ON friend_id = friend.id
INNER JOIN status
        ON status_id = status.id
WHERE status.id = "0"
ORDER BY rental.status_id desc;



-- --- DANGER ZONE ---
-- -- -- delete test data from all tables.
-- -- delete from status where id = 0;
-- -- delete from gender where id = 0;
-- -- delete from actor where id = 0;
-- -- delete from genre where id = 0;
-- -- delete from rental where id = 0;
-- -- delete from disk where id = 0;
-- -- delete from friend where id = 0;