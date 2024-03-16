USE inventory_db_dev_1;


INSERT INTO activity (item_id, activity_id, quantity, time) VALUES (
(SELECT id AS IT_ID FROM item WHERE sku_name ='Mango'), 
(SELECT id AS ACT_ID FROM activity_def WHERE activity ='Sold'), 
500,
CURRENT_TIMESTAMP);

