select ii.item_id as ITEM_ID, ii.item_name as ITEM_NAME, ii.rarity as RARITY
from item_tree it
join item_info ii
on it.item_id = ii.item_id
where it.parent_item_id in (
    select item_id
    from item_info
    where rarity = 'RARE'
)
order by 1 desc;