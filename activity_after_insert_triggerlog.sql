CREATE DEFINER=`admin`@`%` TRIGGER `activity_after_insert` AFTER INSERT ON `activity` FOR EACH ROW BEGIN
-- Declare a ware variables
    DECLARE ware_count INT;
    DECLARE ware_value VARCHAR(30);
    DECLARE floor_count INT;
    DECLARE floor_value VARCHAR(30);
	DECLARE returned_count INT;
    DECLARE returned_value VARCHAR(30);

    -- Extract value from query and put into variables
    SELECT ware INTO ware_count FROM inventory_counts WHERE id = NEW.item_id;
    #INSERT INTO trigger_log (log)
	#	VALUES (CONCAT('Trigger: activity_after_insert. - Added ware count into varaible.  Ware Count: ', ware_count));
    SELECT ware INTO ware_value FROM activity_def WHERE id = NEW.activity_id;
    # INSERT INTO trigger_log (log)
	#	VALUES (CONCAT('Trigger: activity_after_insert. - Added ware value into varaible.  Ware Value: ', ware_value));
    
	-- Extract value from query and put into variables
    SELECT floor INTO floor_count FROM inventory_counts WHERE id = NEW.item_id;
    #INSERT INTO trigger_log (log)
	#	VALUES (CONCAT('Trigger: activity_after_insert. - Added returned count into varaible.  Floor Count: ', floor_count));
    SELECT floor INTO floor_value FROM activity_def WHERE id = NEW.activity_id;
     #INSERT INTO trigger_log (log)
	#	VALUES (CONCAT('Trigger: activity_after_insert. - Added returned value into varaible.  Floor Value: ', floor_value));
    
    
	-- Extract value from query and put into variables
    SELECT returned INTO returned_count FROM inventory_counts WHERE id = NEW.item_id;
    #INSERT INTO trigger_log (log)
	#	VALUES (CONCAT('Trigger: activity_after_insert. - Added returned count into varaible.  Returned Count: ', returned_count));
    SELECT returned INTO returned_value FROM activity_def WHERE id = NEW.activity_id;
    # INSERT INTO trigger_log (log)
	#	VALUES (CONCAT('Trigger: activity_after_insert. - Added returned value into varaible.  Returned Value: ', returned_value));
    
    
    -- Compare if ware value is equal to positive
     IF (SELECT ware_value = 'positive') THEN
		#INSERT INTO trigger_log (log)
		 #  VALUES ('Trigger: activity_after_insert. - Found comparison to TRUE.  Ware Value: ');
		UPDATE inventory_counts i
		SET i.ware = ware_count + new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
	IF (SELECT ware_value = 'negative') THEN
		#INSERT INTO trigger_log (log)
		 #  VALUES ('Trigger: activity_after_insert. - Found comparison to TRUE.  Ware Value: ');
		UPDATE inventory_counts i
		SET i.ware = ware_count - new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
	IF (SELECT floor_value = 'positive') THEN
		#INSERT INTO trigger_log (log)
		 #  VALUES ('Trigger: activity_after_insert. - Found comparison to TRUE.  Floor Value: ');
		UPDATE inventory_counts i
		SET i.floor = floor_count + new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
	IF (SELECT returned_value = 'positive') THEN
		#INSERT INTO trigger_log (log)
		 #  VALUES ('Trigger: activity_after_insert. - Found comparison to TRUE.  Returned Value: ');
		UPDATE inventory_counts i
		SET i.returned = returned_count + new.quantity
		WHERE i.item_id = new.item_id;
	END IF;
    
END