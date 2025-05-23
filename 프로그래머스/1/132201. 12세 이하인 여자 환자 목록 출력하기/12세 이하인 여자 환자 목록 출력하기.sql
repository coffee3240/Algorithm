-- 코드를 입력하세요
SELECT PT_NAME, PT_NO, GEND_CD, AGE, IFNULL(TLNO, 'NONE') as TLNO
from patient
where age <= 12
and GEND_CD = 'W'
order by AGE desc, PT_NAME asc;