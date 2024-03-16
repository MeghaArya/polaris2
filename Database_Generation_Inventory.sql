USE inventory_db_dev_1;

CREATE TABLE `activity` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `item_id` INT UNSIGNED,
  `activity_id` INT UNSIGNED,
  `quantity` INT UNSIGNED,
  `time` timestamp
);


CREATE TABLE `activity_def` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `activity` varchar(80),
  `activity_desc` varchar(200),
  `ware` varchar(80),
  `floor` varchar(80),
  `returned` varchar(80),
  `damage` varchar(80),
  `time` timestamp
);


CREATE TABLE `item` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `sku_id` varchar(20),
  `quantity` INT UNSIGNED,
  `department` varchar(80),
  `subdepartment` varchar(80),
  `store_id` INT UNSIGNED,
  `sku_name` varchar(80),
  `sku_desc` varchar(200),
  `upc_id` varchar(20),
  `time` timestamp
);


CREATE TABLE `inventory_counts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `item_id` INT UNSIGNED,
  `ware` INT UNSIGNED,
  `floor` INT UNSIGNED,
  `returned` INT UNSIGNED,
  `damage` INT UNSIGNED,
  `time` timestamp
);


CREATE TABLE `capacity` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `item_id` INT UNSIGNED,
  `order_min` INT UNSIGNED,
  `warehouse_min` INT UNSIGNED,
  `warehouse_max` INT UNSIGNED,
  `floor_min` INT UNSIGNED,
  `floor_max` INT UNSIGNED,
  `time` timestamp
);

ALTER TABLE `activity` ADD FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);

ALTER TABLE `activity` ADD FOREIGN KEY (`activity_id`) REFERENCES `activity_def` (`id`);

ALTER TABLE `inventory_counts` ADD FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);

ALTER TABLE `capacity` ADD FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);


