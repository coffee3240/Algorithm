-- 코드를 작성해주세요
select ID, EMAIL, FIRST_NAME, LAST_NAME
from developers
where (skill_code & (select code from skillcodes where name = 'C#')) > 0
or (skill_code & (select code from skillcodes where name = 'Python')) > 0
order by ID asc;