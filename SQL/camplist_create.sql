-- データベースを作成する
DROP DATABASE IF EXISTS CampList;
CREATE DATABASE CampList;

-- 使用するデータベースを選択する
USE CampList;

CREATE USER Camp IDENTIFIED BY 'List';
GRANT ALL PRIVILEGES ON CampList.* TO 'Camp';


-- テーブルを作成する Windowsの場合
-- ➀m_loginテーブル
CREATE TABLE m_login(
    login_id VARCHAR(20) NOT NULL,
    salt VARCHAR(100) NOT NULL,
    hash_salt VARCHAR(200) NOT NULL,
    manager_id int NOT NULL,
    PRIMARY KEY (login_id)
)   DEFAULT CHARSET = utf8;

-- ➁campテーブル
CREATE TABLE camp(
    camp_name VARCHAR(30) NOT NULL,
    location VARCHAR(40) NOT NULL,
    tel VARCHAR(15) NOT NULL,
    charge int,
    capacity int,
    PRIMARY KEY (camp_name)
)   DEFAULT CHARSET = utf8;

-- ➂reserveテーブル
CREATE TABLE reserve(
    reserve_id int NOT NULL AUTO_INCREMENT,
    login_id VARCHAR(20) NOT NULL,
    camp_name VARCHAR(30) NOT NULL,
    reserve_date DATE NOT NULL,
    insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (reserve_id),
    FOREIGN KEY (login_id) REFERENCES m_login(login_id),
    FOREIGN KEY (camp_name) REFERENCES camp(camp_name)
)   DEFAULT CHARSET = utf8;

-- テーブルにデータを挿入する
INSERT INTO
  camp(camp_name,location,tel,charge,capacity)
VALUES
  ("モラップキャンプ場","北海道","0123-25-2201",1300,6),
  ("加護坊山キャンプ場","宮城県","0229-39-0404",0,6),
  ("吹上高原キャンプ場","宮城県","0229-86-2493",2100,4),
  ("秋吉台家族旅行村","山口県","087-62-1110",3700,6),
  ("大島ビーチファミリー","山口県","0820-75-1803",3500,6);

INSERT INTO
  m_login(login_id,salt,hash_salt,manager_id)
VALUES
  ("テスト","UxaTFvAgcaB9uNPzKg+XdQ==","6z8MbUHRUPTW/jO5KyGl1fAE0ZP3H4vflRzMVRXyXWw=",0),
  ("管理者","v6Xr7tipCk7kTVDgv2Os8A==","NeagMPwhWjWodATRTeDdZH0BWj3cfXo2UOfiawBLmjc=",1);

INSERT INTO
  reserve(login_id,camp_name,reserve_date)
VALUES
  ("テスト","モラップキャンプ場","2025-02-24");