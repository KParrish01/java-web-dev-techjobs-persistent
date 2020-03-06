## Part 1: Test it with SQL
CREATE TABLE job (
id INTEGER PRIMARY KEY /* id int PK (not AUTO_INCREMENT within MySQL since done in AbstractEntity*/,
employer VARCHAR(255),
name VARCHAR(255),
skills VARCHAR(255)
);

## Part 2: Test it with SQL
SELECT name FROM employer WHERE location="St. Louis City";

## Part 3: Test it with SQL
DROP TABLE job;

## Part 4: Test it with SQL
SELECT DISTINCT skill.name, skill.description FROM skill
INNER JOIN job_skills ON job_skills.skills_id = skill.id
INNER JOIN job ON job_skills.jobs_id = job.id
WHERE job.name IS NOT NULL AND job.employer_id IS NOT NULL
ORDER BY skill.name ASC;
