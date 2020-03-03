## Part 1: Test it with SQL
CREATE TABLE job (
id INTEGER PRIMARY KEY /* id int PK (not AUTO_INCREMENT within MySQL */,
employer VARCHAR(255),
name VARCHAR(255),
skills VARCHAR(255)
);

## Part 2: Test it with SQL
SELECT name FROM employer WHERE location="St. Louis City";

## Part 3: Test it with SQL
DROP TABLE job;

## Part 4: Test it with SQL
SELECT name, description
FROM skill
INNER JOIN job ON skill.id = job.id  /* Might need a couple of internal queries, if skill and job not directly connected... but they probably are */
WHERE job.name IS NOT NULL
ORDER BY skill ASC;