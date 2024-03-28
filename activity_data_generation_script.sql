USE inventory_db_dev_1;


INSERT INTO activity (item_id, activity_id, quantity, time) VALUES (
(SELECT id AS IT_ID FROM item WHERE sku_name ='Ackee'), 
(SELECT id AS ACT_ID FROM activity_def WHERE activity ='Received in Store Warehouse'), 
10,
CURRENT_TIMESTAMP);

