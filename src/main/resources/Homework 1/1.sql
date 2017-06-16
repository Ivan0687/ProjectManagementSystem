# Добавить разаработчикам поле (salary - зарплата). 

ALTER TABLE `ivan_homework`.`developers` 
ADD COLUMN `salary` INT NULL AFTER `company_id`;


UPDATE `ivan_homework`.`developers` 
SET 
    salary = (SELECT 
            SUM(tax)
        FROM
            ivan_homework.skillIds
        WHERE
            id IN (SELECT 
                    skill_id
                FROM
                    ivan_homework.developer_skills
                WHERE
                    developer_id =  
                            ivan_homework.developers.id));