select
    count(*) as FISH_COUNT
    
from FISH_INFO as fi
join FISH_NAME_INFO as fni 
on fi.FISH_TYPE = fni.FISH_TYPE

where fni.FISH_NAME in ('BASS', 'SNAPPER');