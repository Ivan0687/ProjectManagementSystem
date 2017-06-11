# 3. Вычислить общую ЗП всех Java разработчиков. 

SELECT 
    SUM(salary) AS java_dev_salaries
FROM
    ivan_homework.developers
WHERE
    id IN (SELECT 
            developer_id
        FROM
            ivan_homework.developer_skills
        WHERE
            skill_id in (SELECT 
                    id
                FROM
                    ivan_homework.skills where name = 'java'));
