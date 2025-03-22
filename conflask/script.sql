select * from member;

CREATE TABLE `member` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(500) NOT NULL,
    `tel` VARCHAR(50) NOT NULL UNIQUE
);

DROP TABLE member;

INSERT INTO member (name, tel) VALUES ('홍길동', '010-1111-1111');

commit;