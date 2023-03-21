






CREATE TABLE GARDENER (
	gardener_id int NOT NULL,
    email varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    PRIMARY KEY (gardener_id)
);


INSERT INTO GARDENER (gardener_id, email, username)
VALUES (1, "grdnr1@grdnr.com", "grdnr1");
INSERT INTO GARDENER (gardener_id, email, username)
VALUES (2, "grdnr2@grdnr.com", "grdnr2");

ALTER TABLE GARDENER CHANGE gardener_id gardener_id int auto_increment;

INSERT INTO GARDENER (email, username)
VALUES ("grdnr3@grdnr.com", "grdnr3");



# timestamp format - YYYY-MM-DD hh:mm:ss
create table post (
    post_id int auto_increment,
    description varchar(255),
    gardener_id int,
    datetime timestamp default current_timestamp,
    primary key (post_id),
    foreign key (gardener_id) references gardener(gardener_id)
);

insert into post (description, gardener_id) values ('desc1', 2);

create table ggroup (
    group_id int auto_increment,
    name varchar(255) not null,
    primary key (group_id)
);

create table gardener_groups (
    group_id int,
    gardener_id int,
    #role varchar(255), # --> darznieka loma --> varetu but izmantots lai glabatu informaciju par darznieka saistibu ar grupu (piemeram, vai darznieks ir administrators vai parast lietotajs)
    foreign key (group_id) references ggroup(group_id),
    foreign key (gardener_id) references gardener(gardener_id)
);

create table address (
    address_id int auto_increment,
    street varchar(255) not null,
    gardener_id int,
    unique(gardener_id),
    primary key (address_id),
    foreign key (gardener_id) references gardener(gardener_id)
);

insert into ggroup (name) values ('grp1');
insert into ggroup (name) values ('grp2');


insert into gardener_groups (gardener_id, group_id) values (1, 1); # darznieks ar id 1 pievienots grupai ar id 1
insert into gardener_groups (gardener_id, group_id) values (2, 2); # darznieks ar id 2 pievienots grupai ar id 2
insert into gardener_groups (gardener_id, group_id) values (2, 1); # darznieks ar id 2 pievienots grupai ar id 1



# iegut grupas kuraas ir darznieks ar lietotajvardu 'grdnr2'
select ggroup.group_id, ggroup.name from gardener_groups, ggroup, gardener
where gardener_groups.gardener_id = gardener.gardener_id and
gardener_groups.group_id = ggroup.group_id and
gardener.username = 'grdnr2';


select * from gardener inner join post on gardener.gardener_id = post.gardener_id;

select * from post where
datetime BETWEEN '2023-01-31 10:00:34' AND '2023-01-31 10:01:34';

UPDATE POST
SET description = 'desc example'
WHERE post_id = 1;

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

#The first user namhm has role USER and password is codejava. And the second user admin has role ADMIN with password is nimda. Both users are enabled.
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('namhm', '$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu', 'ROLE_USER', 1);

INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin', '$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy', 'ROLE_ADMIN', 1);

alter table gardener add column avatarImageUrl varchar(255);


alter table gardener add column password varchar(255) NOT NULL;
alter table gardener add column avatar_image_url varchar(255) DEFAULT "/images/blank-profile2.png";

alter table post add column imageurl varchar(855) NOT NULL;
alter table gardener add column role varchar(45) NOT NULL;
alter table gardener add column enabled tinyint(4) DEFAULT NULL;
alter table gardener change enabled enabled tinyint(4) DEFAULT "1";












