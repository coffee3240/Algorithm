WITH SCORE_SUM AS (
    SELECT EMP_NO, SUM(SCORE) AS SUM_SCORE
    FROM HR_GRADE
    WHERE YEAR = 2022
GROUP BY EMP_NO
)

SELECT 
    S.SUM_SCORE AS SCORE,
    E.EMP_NO,
    E.EMP_NAME,
    E.POSITION,
    E.EMAIL
FROM SCORE_SUM S
JOIN HR_EMPLOYEES E
ON S.EMP_NO = E.EMP_NO
WHERE S.SUM_SCORE = (
    SELECT MAX(SUM_SCORE) FROM SCORE_SUM
)
ORDER BY E.EMP_NO;