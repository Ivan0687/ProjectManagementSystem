# 6. Вычислить, среднюю ЗП программистов в проекте, который приносит наименьшую прибыль.  компании?
#  средняя зарплата программистов которые работают в компании на самом неприбыльном проекте

SELECT 
    project_id, AVG(salary) AS avg_sal, company_id
FROM
    (SELECT 
        *
    FROM
        developers D
    JOIN (SELECT 
        *
    FROM
        project_developers AS P_D
    JOIN (SELECT 
        project, MIN(p_brings_co) p_brings_min_co, company
    FROM
        (SELECT 
        P.id project, SUM(salary) p_brings_co, CO.id company
    FROM
        customers C
    JOIN projectIds P ON P.customer_id = C.id
    JOIN project_developers P_D ON P_D.project_id = P.id
    JOIN developers D ON D.id = P_D.developer_id
    JOIN companies CO ON CO.id = D.company_id
    GROUP BY CO.id , P.id) AS Q
    GROUP BY company) AS W ON W.project = P_D.project_id) AS E ON E.developer_id = D.id
        AND E.company = D.company_id) AS SOLUTION
GROUP BY SOLUTION.company_id , SOLUTION.project_id