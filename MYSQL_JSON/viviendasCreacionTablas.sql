-- MySQL Script generated by MySQL Workbench
-- Thu Mar  4 17:40:11 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema viviendas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema viviendas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `viviendas` DEFAULT CHARACTER SET utf8 ;
USE `viviendas` ;

-- -----------------------------------------------------
-- Table `viviendas`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `viviendas`.`Cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NULL,
  `vip` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `viviendas`.`Vivienda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `viviendas`.`Vivienda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `calle` VARCHAR(45) NULL,
  `cp` VARCHAR(45) NULL,
  `metrosCuadrados` DOUBLE NULL,
  `Cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Vivienda_Cliente_idx` (`Cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_Vivienda_Cliente`
    FOREIGN KEY (`Cliente_id`)
    REFERENCES `viviendas`.`Cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `viviendas`.`Poliza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `viviendas`.`Poliza` (
  `id` INT NOT NULL,
  `fechaRenovacion` DATE NULL,
  `precioActual` DOUBLE NULL,
  `precioRenovacion` DOUBLE NULL,
  `descuento` INT NULL,
  `Vivienda_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Poliza_Vivienda1_idx` (`Vivienda_id` ASC) VISIBLE,
  CONSTRAINT `fk_Poliza_Vivienda1`
    FOREIGN KEY (`Vivienda_id`)
    REFERENCES `viviendas`.`Vivienda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `viviendas`.`Tecnico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `viviendas`.`Tecnico` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `viviendas`.`Siniestro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `viviendas`.`Siniestro` (
  `id` INT NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaVisitaTecnico` DATE NULL,
  `resuelto` TINYINT NULL,
  `Poliza_id` INT NOT NULL,
  `Tecnico_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Siniestro_Poliza1_idx` (`Poliza_id` ASC) VISIBLE,
  INDEX `fk_Siniestro_Tecnico1_idx` (`Tecnico_id` ASC) VISIBLE,
  CONSTRAINT `fk_Siniestro_Poliza1`
    FOREIGN KEY (`Poliza_id`)
    REFERENCES `viviendas`.`Poliza` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Siniestro_Tecnico1`
    FOREIGN KEY (`Tecnico_id`)
    REFERENCES `viviendas`.`Tecnico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
