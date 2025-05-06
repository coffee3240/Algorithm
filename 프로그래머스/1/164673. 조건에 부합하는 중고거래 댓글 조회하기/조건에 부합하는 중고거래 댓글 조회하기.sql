select ugb.title as TITLE, ugb.board_id as BOARD_ID, ugr.reply_id as REPLY_ID, ugr.writer_id as WRITER_ID, ugr.contents as CONTENTS, date_format(ugr.created_date, '%Y-%m-%d') as CREATED_DATE
from used_goods_reply as ugr
join used_goods_board as ugb
on ugr.board_id = ugb.board_id
WHERE date_format(ugb.created_date, '%Y-%m') = '2022-10'
order by ugr.created_date asc, ugb.title asc;