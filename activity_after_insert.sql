CREATE DEFINER=`admin`@`%` TRIGGER `activity_after_insert` AFTER INSERT ON `activity` FOR EACH ROW BEGIN
-- Declare a ware variables
    DECLARE ware_count INT;
    DECLARE item_q INT;
    DECLARE ware_value VARCHAR(30);
    DECLARE floor_count INT;
    DECLARE floor_value VARCHAR(30);
	DECLARE damage_count INT;
    DECLARE damage_value VARCHAR(30);
	DECLARE returned_count INT;
    DECLARE returned_value VARCHAR(30);


    -- Extract value from query and put into variables
    
    SELECT ware INTO ware_count FROM inventory_counts WHERE id = NEW.item_id;
    SELECT quantity INTO item_q FROM item WHERE id = NEW.item_id;
    SELECT ware INTO ware_value FROM activity_def WHERE id = NEW.activity_id;
    
	-- Extract value from query and put into variables
    SELECT floor INTO floor_count FROM inventory_counts WHERE id = NEW.item_id;
    SELECT floor INTO floor_value FROM activity_def WHERE id = NEW.activity_id;
    
	-- Extract value from query and put into variables
    SELECT damage INTO damage_count FROM inventory_counts WHERE id = NEW.item_id;
    SELECT damage INTO damage_value FROM activity_def WHERE id = NEW.activity_id;
    
	-- Extract value from query and put into variables
    SELECT returned INTO returned_count FROM inventory_counts WHERE id = NEW.item_id;
    SELECT returned INTO returned_value FROM activity_def WHERE id = NEW.activity_id;
    
    
    -- Compare if ware value is equal to positive
     IF (SELECT ware_value = 'positive') THEN
		UPDATE inventory_counts i
		SET i.ware = ware_count + new.quantity
		WHERE i.item_id = new.item_id;
        UPDATE item k
        SET k.quantity = ware_item_q + new.quantity
        WHERE k.id = new.item_id;
	END IF;
    
	IF (SELECT ware_value = 'negative') THEN
		UPDATE inventory_counts i
		SET i.ware = ware_count - new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
	IF (SELECT floor_value = 'positive') THEN
		UPDATE inventory_counts i
		SET i.floor = floor_count + new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
	IF (SELECT floor_value = 'negative') THEN
		UPDATE inventory_counts i
		SET i.floor = floor_count - new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
	IF (SELECT returned_value = 'positive') THEN
		UPDATE inventory_counts i
		SET i.returned = returned_count + new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
    IF (SELECT damage_value = 'positive') THEN
		UPDATE inventory_counts i
		SET i.damage = damage_count + new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
END