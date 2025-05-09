select
    ID,
    (CASE
        WHEN size_of_colony <= 100 THEN 'LOW'
        WHEN size_of_colony <= 1000 THEN 'MEDIUM'
        ELSE 'HIGH'
    END) as SIZE
from ecoli_data
order by id;