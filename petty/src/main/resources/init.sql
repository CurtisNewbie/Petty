CREATE TABLE IF NOT EXISTS hospital_record_detail (
    id INT AUTO_INCREMENT,
    hospital_record_Pk INT NOT NULL,
    type INT(3) NOT NULL,
    date DATETIME NOT NULL,
    remarks VARCHAR(1000) DEFAULT '',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS hospital_record (
    id INT AUTO_INCREMENT,
    date DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS med_detail (
    id INT AUTO_INCREMENT,
    date DATETIME NOT NULL,
    remarks VARCHAR(1000) DEFAULT ''
);

CREATE TABLE IF NOT EXISTS poo_detail (
    id INT AUTO_INCREMENT,
    date DATETIME NOT NULL,
    remarks VARCHAR(1000) DEFAULT ''
);

CREATE TABLE IF NOT EXISTS weight_detail (
    id INT AUTO_INCREMENT,
    date DATETIME NOT NULL,
    weight FLOAT NOT NULL,
    remarks VARCHAR(1000) DEFAULT ''
);
