-- insert test data sql of 'DVD rental management system' in JV32 summer sutudy
-- source ~/Desktop/JV32_samplesql/sql/insert.sql
-- --- DANGER ZONE ---
USE practice09;

-- insert test data to tables
INSERT INTO status (
    id, name
) VALUES 
    (0, "返却済"),
    (1, "貸出中")
;

INSERT INTO gender (
    id, name 
) VALUES
    (0, "女性"),
    (1, "男性"),
    (2, "その他")
;

INSERT INTO disk (
    id, title, genre, actor
) VALUES 
    (1, "STAR WARS", 0, ",0,1,2,"),
    (2, "AKIRA", 0, ",3,4,5,"),
    (3, "HARRY POTTER", 1, ",34,55,22,"),
    (4, "ALICE IN WONDERLAND", 1, ",405,66,74,"),
    (5, "PROMARE", 3, ",32,67,84,"),
    (6, "SUMMER WARS", 3, ",321,432,543,"),
    (7, "MY NEIGHBOR TOTORO", 12, ",12,34,56,"),
    (8, "SPILITED AWAY", 12, ",77,88,99,"),
    (9, "HOWL'S MOVING CATSTLE", 12, ",1233,2344,3455,"),
    (10, "POKETMONSTAR MEWYWO'S REVENGE", 2, ",1044,2955,3422,")
;

INSERT INTO friend (
    id, name, mail
) VALUES 
    (1, "KIHARA", "kihara@hoge.com"),
    (2, "TANAKA", "tanaka@hoge.com"),
    (3, "NAKAYAMA", "nakayama@hoge.com"),
    (4, "NAKAMURA", "nakamura@hoge.com"),
    (5, "HUKUDA", "hukuda@hoge.com")
;

INSERT INTO rental (
    id, number, date, disk_id, friend_id, status_id
) VALUES 
    (1, 1, '2020-01-01 00:00:00', 1, 1, 1),
    (2, 1, '2020-01-01 00:00:00', 2, 1, 1),
    (3, 1, '2020-01-01 00:00:00', 3, 1, 1),
    (4, 1, '2020-01-01 00:00:00', 4, 1, 1),
    (5, 1, '2020-01-01 00:00:00', 5, 1, 1)
;



-- insert real data one by one data.
-- INSERT INTO status (
--     id, name
-- ) VALUES 
--     (0, "返却済"),
--     (1, "貸出中")
-- ;

-- INSERT INTO gender (
--     id, name 
-- ) VALUES
--     (0, "女性"),
--     (1, "男性"),
--     (2, "その他")
-- ;

-- INSERT INTO actor (
--     id,
--     name,
--     age,
--     gender
-- ) VALUES (
--     0,
--     "TEST",
--     0,
--     0
-- );

-- INSERT INTO genre (
--     id,
--     name
-- ) VALUES (
--     0,
--     "TEST"
-- );

-- INSERT INTO disk (
--     id,
--     title,
--     genre,
--     actor
-- ) VALUES (
--     0,
--     "TEST",
--     0,
--     "0"
-- );

-- INSERT INTO friend (
--     id,
--     name,
--     mail
-- ) VALUES (
--     0,
--     "TEST",
--     "test@hoge.com"
-- );

-- INSERT INTO rental (
--     id,
--     number,
--     date,
--     disk_id,
--     friend_id,
--     status_id
-- ) VALUES (
--     0,
--     0,
--     '2020-01-01 00:00:00',
--     0,
--     0,
--     1
-- );