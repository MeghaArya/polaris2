USE inventory_db_dev_1;

INSERT INTO activity_def (activity, activity_desc, ware, floor, returned, damage, time) VALUES ('Received in Store Warehouse', 'Shipment Arrived in the Store Warehouse', 'positive', 'n/a', 'n/a', 'n/a', CURRENT_TIMESTAMP);

INSERT INTO activity_def (activity, activity_desc, ware, floor, returned, damage, time) VALUES ('In Floor', 'Move items to the floor of the store', 'negative', 'positive', 'n/a', 'n/a', CURRENT_TIMESTAMP);

INSERT INTO activity_def (activity, activity_desc, ware, floor, returned, damage, time) VALUES ('Sold', 'Item(s) was sold', 'n/a', 'negative', 'n/a', 'n/a', CURRENT_TIMESTAMP);

INSERT INTO activity_def (activity, activity_desc, ware, floor, returned, damage, time) VALUES ('Damaged', 'Item(s) was marked as damaged', 'n/a', 'n/a', 'n/a', 'positive', CURRENT_TIMESTAMP);

INSERT INTO activity_def (activity, activity_desc, ware, floor, returned, damage, time) VALUES ('Returned', 'Item was returned', 'n/a', 'n/a', 'positive', 'n/a', CURRENT_TIMESTAMP);
