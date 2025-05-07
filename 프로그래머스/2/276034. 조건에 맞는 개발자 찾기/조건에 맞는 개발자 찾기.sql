-- 코드를 작성해주세요
select ID, EMAIL, FIRST_NAME, LAST_NAME
from developers
where skill_code & 256 = 256
or skill_code & 1024 = 1024
order by ID asc;