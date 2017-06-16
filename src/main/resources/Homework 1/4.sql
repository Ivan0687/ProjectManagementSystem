# 4. Добавить поле (cost - стоимость) в таблицу Projects .

ALTER TABLE `ivan_homework`.`projectIds`
ADD COLUMN `cost` INT NULL AFTER `customer_id`;

UPDATE `ivan_homework`.`projectIds`
SET 
    cost = (SELECT 
            SUM(salary)
        FROM
            ivan_homework.developers
        WHERE
            id IN (SELECT 
                    developer_id
                FROM
                    ivan_homework.project_developers
				WHERE
                    project_id =  
                            ivan_homework.projectIds.id));
                            
