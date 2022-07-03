CREATE DATABASE mileage;


CREATE TABLE `T_REVIEW` (
	`reviewId`	varchar(36)	NOT NULL,
	`content`	varchar(1000)	NULL,
	`userId`	varchar(36)	NULL,
	`placeId`	varchar(36)	NULL,
	`registDt`	datetime	NULL,
	`updtDt`	datetime	NULL,
	`deleteYn`	char(1)	NULL,
	`point`	int	NULL
);

CREATE TABLE `T_REVIEW_PHOTO` (
	`reviewPhotoId`	varchar(36)	NOT NULL,
	`reviewId`	varchar(36)	NOT NULL,
	`photoId`	varchar(36)	NULL,
	`registDt`	datetime	NULL
);

CREATE TABLE `T_USER_POINT` (
	`userId`	varchar(36)	NOT NULL,
	`point`	int	NULL
);

CREATE TABLE `T_HISTORY` (
	`historyId`	varchar(36)	NOT NULL,
	`reviewId`	varchar(36)	NOT NULL,
	`userEvent`	varchar(10)	NULL,
	`existingPoints`	int	NULL,
	`idPoint`	int	NULL,
	`totPoint`	int	NULL,
	`registDt`	datetime	NULL
);

ALTER TABLE `T_USER_POINT` CONVERT TO CHARSET UTF8;
ALTER TABLE `T_REVIEW` CONVERT TO CHARSET UTF8;
ALTER TABLE `T_REVIEW_PHOTO` CONVERT TO CHARSET UTF8;
ALTER TABLE `T_HISTORY` CONVERT TO CHARSET UTF8;

ALTER TABLE `T_REVIEW` ADD CONSTRAINT `PK_T_REVIEW` PRIMARY KEY (
	`reviewId`
);

ALTER TABLE `T_REVIEW_PHOTO` ADD CONSTRAINT `PK_T_REVIEW_PHOTO` PRIMARY KEY (
	`reviewPhotoId`,
	`reviewId`
);

ALTER TABLE `T_USER_POINT` ADD CONSTRAINT `PK_T_USER_POINT` PRIMARY KEY (
	`userId`
);

ALTER TABLE `T_HISTORY` ADD CONSTRAINT `PK_T_HISTORY` PRIMARY KEY (
	`historyId`,
	`reviewId`
);

ALTER TABLE `T_REVIEW_PHOTO` ADD CONSTRAINT `FK_T_REVIEW_TO_T_REVIEW_PHOTO_1` FOREIGN KEY (
	`reviewId`
)
REFERENCES `T_REVIEW` (
	`reviewId`
);

ALTER TABLE `T_HISTORY` ADD CONSTRAINT `FK_T_REVIEW_TO_T_HISTORY_1` FOREIGN KEY (
	`reviewId`
)
REFERENCES `T_REVIEW` (
	`reviewId`
);


CREATE INDEX IDX_PID_UID ON t_review(placeId, userId);

