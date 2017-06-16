# 2. Найти самый дорогой проект (исходя из ЗП разработчиков). 

SELECT 
    Q.id,
    Q.name AS project_name_with_max_price,
    MAX(Q.price) AS price
FROM
    (SELECT 
        P.id AS id, P.name AS name, SUM(D.salary) AS price
    FROM
        projectIds AS P, developers AS D
    WHERE
        D.id IN (SELECT 
                PR_DEV.developer_id
            FROM
                project_developers AS PR_DEV
            WHERE
                PR_DEV.project_id = P.id)
    GROUP BY P.id) AS Q;