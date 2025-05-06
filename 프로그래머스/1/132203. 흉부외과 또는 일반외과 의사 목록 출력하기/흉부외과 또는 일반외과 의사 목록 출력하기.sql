-- 코드를 입력하세요
SELECT DR_NAME, DR_ID, MCDP_CD, date_format(HIRE_YMD, '%Y-%m-%d') as HIRE_YML
from doctor
where mcdp_cd in ('CS', 'GS')
order by hire_ymd desc, dr_name asc;