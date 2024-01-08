.read data.sql


CREATE TABLE bluedog AS
  SELECT color, pet FROM students WHERE color = "blue" AND pet="dog";

CREATE TABLE bluedog_songs AS
  SELECT color, pet, song FROM students WHERE color = "blue" AND pet="dog";


CREATE TABLE matchmaker AS
  SELECT s1.pet, s1.song, s1.color, s2.color FROM students as s1, students as s2 WHERE s1.pet = s2.pet AND s1.song = s2.song AND s1.time < s2.time;


CREATE TABLE sevens AS
  SELECT s.seven FROM students as s, numbers as n WHERE s.number=7 AND n.'7' = "True" AND s.time = n.time;


CREATE TABLE smallest_int_having AS
  SELECT time, smallest FROM students GROUP BY smallest HAVING count(smallest) = 1;