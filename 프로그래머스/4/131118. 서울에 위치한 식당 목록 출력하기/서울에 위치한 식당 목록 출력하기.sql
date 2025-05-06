select ri.rest_id as REST_ID, ri.rest_name as REST_NAME, ri.food_type as FOOD_TYPE, ri.favorites as FAVORITES, ri.address as ADDRESS, round(avg(rr.review_score), 2) as SCORE
from rest_info ri
join rest_review rr
on ri.rest_id = rr.rest_id
where ri.address like '서울%'
group by ri.rest_id
order by SCORE desc, FAVORITES desc