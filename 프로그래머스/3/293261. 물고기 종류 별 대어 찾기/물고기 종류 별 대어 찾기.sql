WITH MAX_FISH AS (
  SELECT 
    ID,
    FISH_TYPE,
    LENGTH,
    ROW_NUMBER() OVER (PARTITION BY FISH_TYPE ORDER BY LENGTH DESC) AS RN
  FROM FISH_INFO
  WHERE LENGTH IS NOT NULL
)

SELECT 
  M.ID,
  N.FISH_NAME,
  M.LENGTH
FROM MAX_FISH M
JOIN FISH_NAME_INFO N
  ON M.FISH_TYPE = N.FISH_TYPE
WHERE M.RN = 1
ORDER BY M.ID;
