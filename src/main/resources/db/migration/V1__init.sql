CREATE TABLE student (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   surname VARCHAR(255) NULL,
   age VARCHAR(255) NULL,
   phone_number VARCHAR(255) NULL,
   CONSTRAINT pk_student PRIMARY KEY (id)
);
CREATE TABLE teacher (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   surname VARCHAR(255) NULL,
   subject VARCHAR(255) NULL,
   CONSTRAINT pk_teacher PRIMARY KEY (id)
);

INSERT INTO teacher(name,surname,subject) VALUES('Chester','Matthews','Economics');
INSERT INTO teacher(name,surname,subject) VALUES('Hannah','Collins','Sociology');
INSERT INTO teacher(name,surname,subject) VALUES('Irene','Robinson','Art');
INSERT INTO teacher(name,surname,subject) VALUES('Sadie','Glass','Mathematics');
INSERT INTO teacher(name,surname,subject) VALUES('Greta','Andrews','English IV');
INSERT INTO teacher(name,surname,subject) VALUES('Enoch','Thompson','Speech');
INSERT INTO teacher(name,surname,subject) VALUES('Hazel','Richardson','Health');
INSERT INTO teacher(name,surname,subject) VALUES('Anabelle','Bryson','Mathematics');
INSERT INTO teacher(name,surname,subject) VALUES('Ethan','Thorpe','Health');
INSERT INTO teacher(name,surname,subject) VALUES('Stacy','Rogers','French');
INSERT INTO teacher(name,surname,subject) VALUES('Leroy','Chadwick','Sociology');
INSERT INTO teacher(name,surname,subject) VALUES('Lynn','Robinson','Mathematics');
INSERT INTO teacher(name,surname,subject) VALUES('Britney','Irwin','Ecology');
INSERT INTO teacher(name,surname,subject) VALUES('Tyler','Adams','German');
INSERT INTO teacher(name,surname,subject) VALUES('Cecilia','Porter','Spanish');
INSERT INTO teacher(name,surname,subject) VALUES('Stella','Eagle','Ancient Civilizations');
INSERT INTO teacher(name,surname,subject) VALUES('Hank','Spencer','Handwriting');
INSERT INTO teacher(name,surname,subject) VALUES('Mina','Dempsey','LOGIC');
INSERT INTO teacher(name,surname,subject) VALUES('Rosalie','Steer','Resource Program');
INSERT INTO teacher(name,surname,subject) VALUES('Harmony','Whatson','Sociology');
INSERT INTO teacher(name,surname,subject) VALUES('Eduardo','Quinn','LOGIC');
INSERT INTO teacher(name,surname,subject) VALUES('Marla','Eddison','Resource Program');
INSERT INTO teacher(name,surname,subject) VALUES('Marvin','Noach','Ancient Civilizations');
INSERT INTO teacher(name,surname,subject) VALUES('Rick','Downing','Music');


INSERT INTO student(name,surname,age,phone_number) VALUES ('Leroy','Leroy Hamilton',31,'6-100-320-7307');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Jasmine','Jasmine Tait',24,'3-080-563-5075');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Laila','Laila Wright',29,'3-845-567-8100');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Barney','Barney Baker',29,'8-857-142-2453');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Ronald','Ronald Nayler',39,'1-810-675-7245');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Skylar','Skylar Downing',34,'1-246-764-3470');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Wendy','Wendy Beal',26,'1-764-813-0242');
INSERT INTO student(name,surname,age,phone_number) VALUES ('John','John Edwards',36,'3-428-410-2385');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Danny','Danny Eagle',38,'0-570-373-3036');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Jayden','Jayden Tennant',33,'5-207-454-0411');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Bryon','Bryon Zaoui',27,'5-675-352-1870');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Alan','Alan Leigh',38,'7-608-334-7605');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Janice','Janice Wilson',26,'2-086-703-6772');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Miley','Miley Chadwick',31,'4-147-234-3071');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Rhea','Rhea Ingham',40,'0-826-501-4653');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Rihanna','Rihanna Nurton',23,'3-372-530-4676');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Manuel','Manuel Vincent',37,'1-310-726-2332');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Samara','Samara Gilbert',42,'3-238-460-6828');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Harry','Harry Mann',42,'4-351-307-1275');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Lara','Lara Dobson',28,'6-227-441-8371');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Stacy','Stacy Potter',41,'6-135-881-6664');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Chester','Chester Allington',44,'6-575-561-6322');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Javier','Javier Lee',26,'4-434-035-6034');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Joy','Joy Rogan',20,'2-074-146-6105');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Janelle','Janelle Smith',35,'4-034-287-6040');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Enoch','Enoch Edler',23,'0-653-825-5166');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Elijah','Elijah Murray',33,'0-544-741-8704');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Felicity','Felicity Quinton',22,'5-030-077-5228');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Joyce','Joyce Ellis',35,'0-534-433-2368');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Shelby','Shelby Henderson',32,'0-650-063-1351');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Doris','Doris Eagle',44,'0-812-888-3551');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Payton','Payton Vince',22,'1-027-216-2675');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Kieth','Kieth Egerton',27,'3-204-584-4350');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Gabriel','Gabriel Brown',44,'0-372-416-3863');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Clint','Clint Long',28,'4-842-285-4616');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Carter','Carter Newton',24,'7-677-724-6316');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Charlize','Charlize Cavanagh',26,'3-800-018-6183');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Roger','Roger Ellery',41,'4-707-756-6508');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Percy','Percy Morris',35,'0-454-664-8108');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Boris','Boris Rose',31,'2-455-577-2055');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Jack','Jack Shea',21,'8-655-782-4308');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Liam','Liam Stone',43,'5-071-744-4562');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Rose','Rose Mann',23,'3-081-013-1040');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Brad','Brad Rixon',36,'8-851-053-2153');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Angela','Angela Umney',35,'7-652-678-6661');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Harvey','Harvey Davies',40,'5-208-635-4748');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Mavis','Mavis Russell',38,'8-423-632-0768');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Hayden','Hayden Shields',43,'6-553-801-0122');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Beatrice','Beatrice Rixon',38,'0-110-765-3833');
INSERT INTO student(name,surname,age,phone_number) VALUES ('John','John Brennan',39,'1-600-731-3556');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Makenzie','Makenzie Shaw',31,'1-431-570-7511');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Carmella','Carmella Vallins',36,'7-864-141-6727');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Chris','Chris Parsons',20,'2-514-278-1580');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Alma','Alma Gibbons',38,'1-430-675-5080');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Alessia','Alessia Mitchell',40,'8-535-740-1668');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Marvin','Marvin Rosenbloom',21,'5-671-134-4615');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Marvin','Marvin Myatt',26,'0-437-844-6011');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Mary','Mary Coates',42,'1-408-227-2634');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Georgia','Georgia Reading',25,'5-744-633-2551');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Josh','Josh Nurton',20,'8-718-018-1301');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Carla','Carla Whitehouse',32,'3-727-527-1536');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Lorraine','Lorraine Simmons',22,'2-825-358-7305');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Abbey','Abbey Reading',32,'1-671-236-0471');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Benny','Benny Owen',29,'8-562-618-0073');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Kimberly','Kimberly Bailey',46,'4-661-183-2165');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Abdul','Abdul Reid',26,'8-320-100-8622');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Cadence','Cadence Wigley',43,'3-734-240-3234');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Michael','Michael Roman',23,'2-217-886-6584');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Jade','Jade Carson',45,'1-682-264-2578');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Chuck','Chuck Connor',36,'6-425-885-7386');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Drew','Drew Mccormick',41,'5-053-163-2237');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Keira','Keira Allington',20,'0-608-204-2885');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Ryan','Ryan Mcleod',36,'6-676-173-5473');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Marie','Marie Booth',24,'0-063-788-7261');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Angel','Angel Tate',34,'7-676-601-7815');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Cassidy','Cassidy Forth',43,'7-406-751-1456');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Caleb','Caleb Roberts',45,'0-610-853-2418');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Hailey','Hailey Smith',27,'7-463-866-8486');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Harmony','Harmony Spencer',46,'1-578-188-4023');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Eden','Eden Price',45,'3-302-621-1023');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Owen','Owen Gregory',27,'0-115-823-7838');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Anabelle','Anabelle Jordan',36,'4-135-424-8260');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Kurt','Kurt Eddison',42,'1-237-118-0558');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Andie','Andie Cartwright',42,'2-483-623-0484');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Josh','Josh Casey',45,'7-501-154-7834');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Abdul','Abdul Tait',34,'6-404-444-3857');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Liam','Liam Tanner',39,'8-015-734-8616');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Rosalie','Rosalie Morgan',26,'1-510-180-3232');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Cecilia','Cecilia Eaton',31,'3-682-348-0112');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Lara','Lara Thorpe',41,'7-446-410-7567');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Noah','Noah Farrant',32,'1-332-606-7461');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Priscilla','Priscilla Thatcher',45,'3-604-576-3022');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Dalia','Dalia Owen',34,'7-075-872-1461');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Doug','Doug Willis',27,'6-106-315-3554');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Johnny','Johnny Taylor',32,'1-328-815-2701');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Michael','Michael Harris',31,'1-636-517-5131');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Barry','Barry Brett',34,'7-800-572-0000');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Marvin','Marvin Olson',26,'5-061-365-5423');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Ethan','Ethan Andrews',40,'5-323-214-4157');
INSERT INTO student(name,surname,age,phone_number) VALUES ('Jacob','Jacob Graham',33,'3-508-718-0571');

