# 5. Найти клиента (customer), который приносит меньше всего прибыли компании (company) для каждой из компаний.

SELECT 
    customer, MIN(c_pays_co) c_pays_min_co, company
FROM
    (SELECT 
        C.id customer, SUM(salary) c_pays_co, CO.id company
    FROM
        customers C
    JOIN projectIds P ON P.customer_id = C.id
    JOIN project_developers P_D ON P_D.project_id = P.id
    JOIN developers D ON D.id = P_D.developer_id
    JOIN companies CO ON CO.id = D.company_id
    GROUP BY CO.id , C.id) AS Q
GROUP BY company