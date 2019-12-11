-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema oop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema oop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `oop` ;

-- -----------------------------------------------------
-- Table `oop`.`entity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`entity` (
  `entity_id` CHAR(10) NOT NULL,
  `name` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `description` VARCHAR(1000) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `entity_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`entity_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`agreement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`agreement` (
  `agreement_id` CHAR(10) NOT NULL,
  INDEX `fk_agreement_entity1_idx` (`agreement_id` ASC) VISIBLE,
  PRIMARY KEY (`agreement_id`),
  CONSTRAINT `fk_agreement_entity1`
    FOREIGN KEY (`agreement_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`article` (
  `article_id` CHAR(10) NOT NULL,
  `link` VARCHAR(1000) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`article_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`country` (
  `country_id` CHAR(10) NOT NULL,
  INDEX `fk_country_entity1_idx` (`country_id` ASC) VISIBLE,
  PRIMARY KEY (`country_id`),
  CONSTRAINT `fk_country_entity1`
    FOREIGN KEY (`country_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`event` (
  `event_id` CHAR(10) NOT NULL,
  `time` DATE NULL,
  INDEX `fk_event_entity1_idx` (`event_id` ASC) VISIBLE,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `fk_event_entity1`
    FOREIGN KEY (`event_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`fact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`fact` (
  `subject_id` CHAR(10) NOT NULL,
  `object_id` CHAR(10) NOT NULL,
  `relationship` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `fact_id` CHAR(10) NOT NULL,
  `article_id` CHAR(10) NOT NULL,
  INDEX `fk_fact_entity1_idx` (`subject_id` ASC) VISIBLE,
  INDEX `fk_fact_entity2_idx` (`object_id` ASC) VISIBLE,
  INDEX `fk_fact_article1_idx` (`article_id` ASC) VISIBLE,
  PRIMARY KEY (`fact_id`),
  CONSTRAINT `fk_fact_entity1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fact_entity2`
    FOREIGN KEY (`object_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fact_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `oop`.`article` (`article_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`location` (
  `location_id` CHAR(10) NOT NULL,
  `country` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  INDEX `fk_location_entity1_idx` (`location_id` ASC) VISIBLE,
  PRIMARY KEY (`location_id`),
  CONSTRAINT `fk_location_entity1`
    FOREIGN KEY (`location_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`organization` (
  `org_id` CHAR(10) NOT NULL,
  `truso` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `ngaythanhlap` DATE NULL DEFAULT NULL,
  INDEX `fk_organization_entity1_idx` (`org_id` ASC) VISIBLE,
  PRIMARY KEY (`org_id`),
  CONSTRAINT `fk_organization_entity1`
    FOREIGN KEY (`org_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `oop`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oop`.`person` (
  `person_id` CHAR(10) NOT NULL,
  `nghenghiep` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `tuoi` INT(11) NULL DEFAULT NULL,
  INDEX `fk_person_entity_idx` (`person_id` ASC) VISIBLE,
  PRIMARY KEY (`person_id`),
  CONSTRAINT `fk_person_entity`
    FOREIGN KEY (`person_id`)
    REFERENCES `oop`.`entity` (`entity_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
