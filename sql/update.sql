-- create tables sql of 'DVD rental management system' in JV32 summer sutudy
-- source ~/Desktop/JV32_summerstudy/sql/update.sql;

-- update data 

-- update status with disk title when returned disk.(sample title is "STAR WARS")
UPDATE rental
SET   status_id = 0 
WHERE rental.status_id = 1 AND rental.id = 1;

-- -- update test data at all tables.
-- UPDATE status
-- SET    id = 0,
--        name = "Kiharaten"
-- WHERE id = 0;

-- UPDATE gender
-- SET    id = 0,
--        name = "Kiharaten"
-- WHERE id = 0;

-- UPDATE actor
-- SET    id = 1,
--        name = "Kiharaten",
--        age = 1,
--        gender = 1
-- WHERE id = 0;

-- UPDATE genre
-- SET    id = 1,
--        name = "Kiharaten"
-- WHERE id = 0;

-- UPDATE disk
-- SET    id = 0,
--        title = "Kiharaten",
--        genre = 1,
--        actor = "1"
-- WHERE id = 0;

-- UPDATE friend
-- SET    id = 0,
--        name = "Kiharaten",
--        mail = "Kiharaten@hoge.com"
-- WHERE id = 0;

-- UPDATE rental
-- SET    id = 1,
--        number = 1,
--        date = '1212-12-12 12:12:12',
--        disk_id = 0,
--        friend_id = 0,
--        status_id = 0
-- WHERE id = 0;