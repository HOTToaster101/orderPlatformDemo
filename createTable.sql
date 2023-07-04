CREATE TABLE `orders` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`distance` INT zerofill,
	`status` VARCHAR(10) DEFAULT 'UNASSIGNED',
	PRIMARY KEY (`id`)
);