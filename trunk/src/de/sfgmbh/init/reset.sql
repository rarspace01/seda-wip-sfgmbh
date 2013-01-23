/*
Navicat PGSQL Data Transfer

Source Server         : 141.13.6.76
Source Server Version : 90201
Source Host           : 141.13.6.76:5433
Source Database       : WIP-SFGmbH
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90201
File Encoding         : 65001

Date: 2013-01-23 16:13:12
*/


-- ----------------------------
-- Sequence structure for "public"."building_bulidingid_seq"
-- ----------------------------
DROP SEQUENCE "public"."building_bulidingid_seq";
CREATE SEQUENCE "public"."building_bulidingid_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for "public"."chair_chairid_seq"
-- ----------------------------
DROP SEQUENCE "public"."chair_chairid_seq";
CREATE SEQUENCE "public"."chair_chairid_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 15
 CACHE 1;

-- ----------------------------
-- Sequence structure for "public"."course_courseid_seq"
-- ----------------------------
DROP SEQUENCE "public"."course_courseid_seq";
CREATE SEQUENCE "public"."course_courseid_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 26
 CACHE 1;

-- ----------------------------
-- Sequence structure for "public"."lecturer_lecturerid_seq"
-- ----------------------------
DROP SEQUENCE "public"."lecturer_lecturerid_seq";
CREATE SEQUENCE "public"."lecturer_lecturerid_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 20
 CACHE 1;

-- ----------------------------
-- Sequence structure for "public"."room_roomid_seq"
-- ----------------------------
DROP SEQUENCE "public"."room_roomid_seq";
CREATE SEQUENCE "public"."room_roomid_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 40
 CACHE 1;

-- ----------------------------
-- Sequence structure for "public"."roomallocation_roomallocationid_seq"
-- ----------------------------
DROP SEQUENCE "public"."roomallocation_roomallocationid_seq";
CREATE SEQUENCE "public"."roomallocation_roomallocationid_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 45
 CACHE 1;

-- ----------------------------
-- Sequence structure for "public"."user_userid_seq"
-- ----------------------------
DROP SEQUENCE "public"."user_userid_seq";
CREATE SEQUENCE "public"."user_userid_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 54
 CACHE 1;

-- ----------------------------
-- Table structure for "public"."building"
-- ----------------------------
DROP TABLE "public"."building";
CREATE TABLE "public"."building" (
"buildingid" int4 DEFAULT nextval('building_bulidingid_seq'::regclass) NOT NULL,
"address" varchar(512),
"buildingname" varchar(255)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO "public"."building" VALUES ('1', 'An der Weberei 5', 'ERBA');
INSERT INTO "public"."building" VALUES ('2', 'Feldkirchen Str.17', 'FEKI');

-- ----------------------------
-- Table structure for "public"."chair"
-- ----------------------------
DROP TABLE "public"."chair";
CREATE TABLE "public"."chair" (
"chairid" int4 DEFAULT nextval('chair_chairid_seq'::regclass) NOT NULL,
"chairname" varchar(255) NOT NULL,
"chairowner" int4,
"buildingid" int4,
"chairlevel" varchar(55),
"faculty" varchar(255) DEFAULT 'n/a'::character varying NOT NULL,
"chairacronym" varchar(55) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of chair
-- ----------------------------
INSERT INTO "public"."chair" VALUES ('2', 'Industrielle Informationssysteme', '38', '1', '4', 'WI', 'IIS');
INSERT INTO "public"."chair" VALUES ('3', 'Soziale Netzwerke', null, '1', null, 'WIAI', 'SNA');
INSERT INTO "public"."chair" VALUES ('4', 'Systementwicklung und Datenbankanwendung', null, '1', null, 'WIAI', 'SEDA');
INSERT INTO "public"."chair" VALUES ('5', 'Kommunikationsdienste, Telekommunikationsdienste und Rechnernetze', null, '1', null, 'WIAI', 'KTR');
INSERT INTO "public"."chair" VALUES ('6', 'Grundlagen der Informatik', null, '1', null, 'WIAI', 'GDI');
INSERT INTO "public"."chair" VALUES ('7', 'Softwaretechnik und Programmiersprachen', null, '1', 'R4', 'WI', 'SWT');
INSERT INTO "public"."chair" VALUES ('14', 'Mobile Systeme', '42', '-1', '5', 'WI', 'MOBSYS');
INSERT INTO "public"."chair" VALUES ('15', 'Tödliches Spielen', null, '-1', '666', 'WI', 'TS');

-- ----------------------------
-- Table structure for "public"."course"
-- ----------------------------
DROP TABLE "public"."course";
CREATE TABLE "public"."course" (
"courseid" int8 DEFAULT nextval('course_courseid_seq'::regclass) NOT NULL,
"lecturerid" int8 NOT NULL,
"courseacronym" varchar(128) DEFAULT 'n/a'::character varying NOT NULL,
"coursename" varchar(256) DEFAULT 'n/a'::character varying NOT NULL,
"sws" float8 DEFAULT 0 NOT NULL,
"coursekind" varchar(32) DEFAULT 'n/a'::character varying NOT NULL,
"coursedescription" text,
"expectedattendees" int4 DEFAULT 0 NOT NULL,
"lecturerenabled" bool DEFAULT false NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO "public"."course" VALUES ('1', '1', 'PARA1', 'Paranormales Training', '12', 'Vorlesung', 'Keine Angaben', '250', 't');
INSERT INTO "public"."course" VALUES ('2', '1', 'PARA2', 'Paranormales Training', '2', 'Übung', 'Keine Angaben', '33', 'f');
INSERT INTO "public"."course" VALUES ('5', '2', 'AN1', 'Spezielles Lernen', '4', 'Vorlesung', 'k. A.', '2', 't');
INSERT INTO "public"."course" VALUES ('6', '2', 'AN2', 'Spezielles Lernen', '2', 'Tutorium', 'k. A.', '2', 't');
INSERT INTO "public"."course" VALUES ('7', '22', 'TEST', 'Test', '2', 'Tutorium', '', '4', 't');
INSERT INTO "public"."course" VALUES ('8', '1', 'TEST2', 'Karaoke', '6', 'Übung', 'Test', '9', 'f');
INSERT INTO "public"."course" VALUES ('9', '38', 'TT2', 'Karaoke2', '0', 'Vorlesung', 'asfd
sdf', '666', 't');
INSERT INTO "public"."course" VALUES ('11', '22', 'Test2', 'sdaf', '1', 'Vorlesung', '', '1', 't');
INSERT INTO "public"."course" VALUES ('12', '42', 'JUT1', 'JUnit Tests', '4', 'Vorlesung', 'JUnit Tests', '10', 't');
INSERT INTO "public"."course" VALUES ('13', '22', 'KiWi', 'Kein Name', '3', 'Übung', '', '333', 't');
INSERT INTO "public"."course" VALUES ('14', '43', 'TEST', 'TEST', '4', 'Vorlesung', 'TEST', '50', 't');
INSERT INTO "public"."course" VALUES ('15', '43', 'TEST', 'Testveranstaltung', '2', 'Übung', '', '20', 't');
INSERT INTO "public"."course" VALUES ('16', '43', 'TEST3', 'Testtutorium', '4', 'Tutorium', '', '15', 't');
INSERT INTO "public"."course" VALUES ('17', '43', 'FERT', 'Fertigungstechnik', '2', 'Vorlesung', '', '100', 't');
INSERT INTO "public"."course" VALUES ('18', '43', 'FEST', 'Festigkeitslehre', '2', 'Übung', '', '30', 't');
INSERT INTO "public"."course" VALUES ('19', '22', 'KiWi-V', 'KiWi Vorlesung', '100', 'Vorlesung', 'Kiwis des Todes', '666', 't');
INSERT INTO "public"."course" VALUES ('20', '22', 'KiWi2', 'na', '3', 'Vorlesung', '', '999', 't');
INSERT INTO "public"."course" VALUES ('21', '22', 'KiWi3', 'Test3', '4', 'Vorlesung', '', '222', 't');
INSERT INTO "public"."course" VALUES ('22', '29', 'MobIS', 'Modellierung betrieblicher Informationssysteme', '2', 'Vorlesung', '', '50', 't');
INSERT INTO "public"."course" VALUES ('23', '29', 'EbIS-1', 'Fortgeschrittene Anwendungssysteme zur Datenvearbeitung', '2', 'Vorlesung', '', '30', 't');
INSERT INTO "public"."course" VALUES ('25', '38', 'IAWS-MSS-M', 'Management Support Systeme', '2', 'Vorlesung', '', '50', 't');
INSERT INTO "public"."course" VALUES ('26', '38', 'IAWS-ERP-M', 'Enterprise Resource Planning', '2', 'Vorlesung', '', '50', 't');

-- ----------------------------
-- Table structure for "public"."lecturer"
-- ----------------------------
DROP TABLE "public"."lecturer";
CREATE TABLE "public"."lecturer" (
"lecturerid" int8 DEFAULT nextval('lecturer_lecturerid_seq'::regclass) NOT NULL,
"userid" int8 NOT NULL,
"chairid" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lecturer
-- ----------------------------
INSERT INTO "public"."lecturer" VALUES ('1', '1', '5');
INSERT INTO "public"."lecturer" VALUES ('2', '2', '6');
INSERT INTO "public"."lecturer" VALUES ('4', '22', '2');
INSERT INTO "public"."lecturer" VALUES ('8', '27', '7');
INSERT INTO "public"."lecturer" VALUES ('10', '29', '4');
INSERT INTO "public"."lecturer" VALUES ('14', '35', '3');
INSERT INTO "public"."lecturer" VALUES ('15', '36', '5');
INSERT INTO "public"."lecturer" VALUES ('16', '38', '2');
INSERT INTO "public"."lecturer" VALUES ('19', '42', '14');
INSERT INTO "public"."lecturer" VALUES ('20', '43', '4');

-- ----------------------------
-- Table structure for "public"."room"
-- ----------------------------
DROP TABLE "public"."room";
CREATE TABLE "public"."room" (
"roomnumber" varchar(255) NOT NULL,
"buildingid" int4 DEFAULT (-1) NOT NULL,
"level" varchar(55) DEFAULT 'n/a'::character varying NOT NULL,
"seats" int4 DEFAULT 0 NOT NULL,
"pcseats" int4 DEFAULT 0 NOT NULL,
"beamer" int4 DEFAULT 0 NOT NULL,
"visualizer" int4 DEFAULT 0 NOT NULL,
"overheads" int4 DEFAULT 0 NOT NULL,
"chalkboards" int4 DEFAULT 0 NOT NULL,
"whiteboards" int4 DEFAULT 0 NOT NULL,
"roomid" int8 DEFAULT nextval('room_roomid_seq'::regclass) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO "public"."room" VALUES ('WE5/05.005', '1', '5.0', '20', '0', '1', '0', '0', '0', '0', '2');
INSERT INTO "public"."room" VALUES ('003', '1', '01', '30', '30', '2', '0', '0', '0', '0', '3');
INSERT INTO "public"."room" VALUES ('WE5/0.020', '1', 'EG', '120', '0', '2', '0', '0', '0', '0', '4');
INSERT INTO "public"."room" VALUES ('WE5/0.019', '1', 'EG', '180', '0', '2', '0', '0', '0', '0', '5');
INSERT INTO "public"."room" VALUES ('WE5/02.068', '1', '2.1', '20', '0', '0', '0', '0', '0', '0', '27');
INSERT INTO "public"."room" VALUES ('DD3/9', '1', '1.44', '33', '0', '0', '0', '0', '4', '0', '33');
INSERT INTO "public"."room" VALUES ('WE5/99', '1', '1.55', '600', '50', '1', '1', '5', '1', '1', '35');
INSERT INTO "public"."room" VALUES ('T0/3', '1', 'T0', '500', '1', '2', '2', '1', '1', '1', '39');
INSERT INTO "public"."room" VALUES ('2', '1', '31', '40', '0', '2', '2', '2', '1', '0', '40');

-- ----------------------------
-- Table structure for "public"."roomallocation"
-- ----------------------------
DROP TABLE "public"."roomallocation";
CREATE TABLE "public"."roomallocation" (
"roomallocationid" int8 DEFAULT nextval('roomallocation_roomallocationid_seq'::regclass) NOT NULL,
"courseid" int8 NOT NULL,
"roomid" int8 NOT NULL,
"semester" varchar(32) NOT NULL,
"day" int4 NOT NULL,
"time" int8 NOT NULL,
"approved" varchar DEFAULT 'waiting'::character varying NOT NULL,
"orgamessage" text,
"comment" text
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of roomallocation
-- ----------------------------
INSERT INTO "public"."roomallocation" VALUES ('1', '1', '3', 'SS 13', '1', '2', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('2', '1', '35', 'SS 13', '1', '3', 'counter', '', null);
INSERT INTO "public"."roomallocation" VALUES ('3', '2', '33', 'SS 13', '1', '1', 'counter', 'Ich muss Ihnen leider folgenden Gegenvorschlag unterbreiten.', null);
INSERT INTO "public"."roomallocation" VALUES ('5', '6', '2', 'SS 13', '2', '5', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('9', '2', '3', 'SS 13', '1', '3', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('12', '11', '3', 'SS 13', '1', '5', 'accepted', '', null);
INSERT INTO "public"."roomallocation" VALUES ('17', '12', '27', 'SS 13', '1', '2', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('20', '15', '3', 'SS 13', '3', '6', 'accepted', 'Sehr geehrter Herr Serno,
anbei ein Vorschlag zur Konfliktlösung.
MfG
Ihr Verwaltungsteam', null);
INSERT INTO "public"."roomallocation" VALUES ('22', '17', '35', 'SS 13', '1', '4', 'accepted', 'Test, Test', null);
INSERT INTO "public"."roomallocation" VALUES ('23', '17', '35', 'SS 13', '1', '2', 'accepted', 'Sehr geehrter Herr Serno,
anbei ein Gegenvorschlag', null);
INSERT INTO "public"."roomallocation" VALUES ('24', '18', '35', 'SS 13', '1', '1', 'waiting', null, null);
INSERT INTO "public"."roomallocation" VALUES ('25', '18', '33', 'SS 13', '1', '2', 'waiting', null, null);
INSERT INTO "public"."roomallocation" VALUES ('26', '19', '35', 'SS 13', '1', '5', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('27', '20', '2', 'SS 13', '1', '1', 'accepted', '', null);
INSERT INTO "public"."roomallocation" VALUES ('29', '19', '35', 'SS 13', '2', '1', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('30', '19', '35', 'SS 13', '2', '2', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('39', '22', '35', 'SS 13', '2', '3', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('40', '23', '3', 'SS 13', '4', '4', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('41', '25', '27', 'SS 13', '3', '3', 'accepted', null, null);
INSERT INTO "public"."roomallocation" VALUES ('42', '26', '35', 'SS 13', '2', '4', 'waiting', null, null);
INSERT INTO "public"."roomallocation" VALUES ('43', '19', '27', 'SS 13', '2', '5', 'waiting', null, null);
INSERT INTO "public"."roomallocation" VALUES ('44', '19', '27', 'SS 13', '4', '5', 'accepted', '', null);

-- ----------------------------
-- Table structure for "public"."user"
-- ----------------------------
DROP TABLE "public"."user";
CREATE TABLE "public"."user" (
"userid" int8 DEFAULT nextval('user_userid_seq'::regclass) NOT NULL,
"login" varchar(128) NOT NULL,
"pass" varchar(128) NOT NULL,
"salt" varchar(128) DEFAULT 'n/a'::character varying NOT NULL,
"mail" varchar(128) DEFAULT 'n/a'::character varying NOT NULL,
"class" varchar(128) DEFAULT 'guest'::character varying NOT NULL,
"fname" varchar(128) DEFAULT 'n/a'::character varying NOT NULL,
"lname" varchar(128) DEFAULT 'n/a'::character varying NOT NULL,
"lastlogin" int8 DEFAULT 0 NOT NULL,
"disabled" bool DEFAULT false
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "public"."user" VALUES ('1', 'hstad', '3d22f87a9655e2bc971e4f53bb623006dbc2fc9ab57b03e52df964895573d2c1', '10p06od', 'h@s.de', 'lecturer', 'Hannes', 'Stadler', '1358291445', 't');
INSERT INTO "public"."user" VALUES ('2', 'akupf', 'none_set', 'none_set', 'test@test.de', 'lecturer', 'Anna', 'Kupfer', '0', 'f');
INSERT INTO "public"."user" VALUES ('8', 'Verw', '3e280c39a065b49322088ae34b31fdb9052374b07764e65e2bbee279705a410e', 'j2t66r', 'Verwaltung@uni-bamberg.de', 'orga', '', '', '1358951354', 'f');
INSERT INTO "public"."user" VALUES ('22', 'Doz', '8430df002cc316b8d8973aa1f749958a6fdf43eb250179f127fae755868f7665', '3ki7555', 'asf@asdf.de', 'lecturer', 'DozVorname', 'DozNachname', '1358952896', 'f');
INSERT INTO "public"."user" VALUES ('27', 'profTest', 'd410260b4b689f52160aeefc2ef4e11d174695febbafd01ea634ec5fb5380ed0', '3e80ttk', 'p@te.st', 'lecturer', 'P', 'Test', '0', 'f');
INSERT INTO "public"."user" VALUES ('29', 'Sinz', '591d162438cd1528aadbe9784f8d0f84d3400faf1fbfba3f8038d772ed73c192', '3f16lft', 'e.sinz@uni-bamberg.de', 'lecturer', 'Elmar J.', 'Sinz', '1358874274', 'f');
INSERT INTO "public"."user" VALUES ('35', 'Test3', '28c5d2b2dded3936f260680b5f282a9033cc143601d42a9aa0ec060fd00a2bda', '257tar8', 'asfd@test.de', 'lecturer', '', 'asfd', '0', 't');
INSERT INTO "public"."user" VALUES ('36', 'asdfasdf', 'c340602c1f1927da5e8d79f6481207a95f2aed7d296aaf852433b60f7e209560', '8vbtha', 'asdf@asfd.de', 'lecturer', '', 'asdf', '0', 't');
INSERT INTO "public"."user" VALUES ('38', 'Ferstl', '5a20d7a5dfc82ab6f50506e215cbc348f6f2685c8b209cdd1282566a7604b1ba', '36vkv0a', 'o.ferstl@uni-bamberg.de', 'lecturer', 'Otto', 'Ferstl', '0', 'f');
INSERT INTO "public"."user" VALUES ('39', 'denis', '0daa5f04411e3f93a75e7a7172dc234ad23806166be0c14173128af39b1e3f9a', '1aqqf1p', 'denis.hamann@stud.uni-bamberg.de', 'orga', 'Denis', 'Hamann', '1358953494', 'f');
INSERT INTO "public"."user" VALUES ('42', 'denis2', '3635aab2c8de16602d81c6c1eade9fea7d99514819a678d0e00639cef4ebce62', '3fghbln', 'denis2@test.com', 'lecturer', 'Denis', 'Hamann', '1358953217', 'f');
INSERT INTO "public"."user" VALUES ('43', 'mserno', 'a940b2c9112014c03cbd92d4cc7ddfbe332a5d5ce7b93df1c6a02662213a7919', '1emk244', 'mario.serno@uni-bamberg.de', 'lecturer', 'Mario', 'Serno', '1358892842', 'f');
INSERT INTO "public"."user" VALUES ('44', 'KK-', 'db45d1fd08471b1314c48745ea426b7d34a3f3f2edec1a182ded3495ccb6c786', '2svufgk', 'asdf@test.de', 'orga', 'TestK1', 'TestK1', '0', 'f');
INSERT INTO "public"."user" VALUES ('45', 'KK2', 'fd9f64014d81ffeb273b5699837834631d09d01064cd23b33ace75e2d8b87194', '1kbptcc', 'alskfdj@asdfk.de', 'orga', 'asdfkl', 'lökö', '0', 't');
INSERT INTO "public"."user" VALUES ('46', 'asfdasfd', '2689c8c6109329f2d5ebebb99eb5011c9ed351fddfb8b17b784ca958f7cc773f', '2qsb6fb', 'asfd@kkd.de', 'orga', 'sadf', 'safdsf', '0', 'f');
INSERT INTO "public"."user" VALUES ('47', 'asfdFFö>', '2520dd66a06643dd0918a254f3fa6a7f6375cec4fcfe62b88a189bcbac7aa9cf', '3u3mf9k', 'ssdE3@asdlf.de', 'orga', 'as', 'asdf', '0', 't');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."building_bulidingid_seq" OWNED BY "building"."buildingid";
ALTER SEQUENCE "public"."chair_chairid_seq" OWNED BY "chair"."chairid";
ALTER SEQUENCE "public"."course_courseid_seq" OWNED BY "course"."courseid";
ALTER SEQUENCE "public"."lecturer_lecturerid_seq" OWNED BY "lecturer"."lecturerid";
ALTER SEQUENCE "public"."room_roomid_seq" OWNED BY "room"."roomid";
ALTER SEQUENCE "public"."roomallocation_roomallocationid_seq" OWNED BY "roomallocation"."roomallocationid";
ALTER SEQUENCE "public"."user_userid_seq" OWNED BY "user"."userid";

-- ----------------------------
-- Primary Key structure for table "public"."building"
-- ----------------------------
ALTER TABLE "public"."building" ADD PRIMARY KEY ("buildingid");

-- ----------------------------
-- Uniques structure for table "public"."chair"
-- ----------------------------
ALTER TABLE "public"."chair" ADD UNIQUE ("chairacronym");
ALTER TABLE "public"."chair" ADD UNIQUE ("chairname");

-- ----------------------------
-- Primary Key structure for table "public"."chair"
-- ----------------------------
ALTER TABLE "public"."chair" ADD PRIMARY KEY ("chairid");

-- ----------------------------
-- Primary Key structure for table "public"."course"
-- ----------------------------
ALTER TABLE "public"."course" ADD PRIMARY KEY ("courseid");

-- ----------------------------
-- Uniques structure for table "public"."lecturer"
-- ----------------------------
ALTER TABLE "public"."lecturer" ADD UNIQUE ("userid");

-- ----------------------------
-- Primary Key structure for table "public"."lecturer"
-- ----------------------------
ALTER TABLE "public"."lecturer" ADD PRIMARY KEY ("lecturerid");

-- ----------------------------
-- Uniques structure for table "public"."room"
-- ----------------------------
ALTER TABLE "public"."room" ADD UNIQUE ("roomnumber");

-- ----------------------------
-- Primary Key structure for table "public"."room"
-- ----------------------------
ALTER TABLE "public"."room" ADD PRIMARY KEY ("roomid");

-- ----------------------------
-- Primary Key structure for table "public"."roomallocation"
-- ----------------------------
ALTER TABLE "public"."roomallocation" ADD PRIMARY KEY ("roomallocationid");

-- ----------------------------
-- Uniques structure for table "public"."user"
-- ----------------------------
ALTER TABLE "public"."user" ADD UNIQUE ("login");
ALTER TABLE "public"."user" ADD UNIQUE ("mail");

-- ----------------------------
-- Primary Key structure for table "public"."user"
-- ----------------------------
ALTER TABLE "public"."user" ADD PRIMARY KEY ("userid");

-- ----------------------------
-- Foreign Key structure for table "public"."chair"
-- ----------------------------
ALTER TABLE "public"."chair" ADD FOREIGN KEY ("chairowner") REFERENCES "public"."user" ("userid") ON DELETE SET NULL ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."course"
-- ----------------------------
ALTER TABLE "public"."course" ADD FOREIGN KEY ("lecturerid") REFERENCES "public"."lecturer" ("userid") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."lecturer"
-- ----------------------------
ALTER TABLE "public"."lecturer" ADD FOREIGN KEY ("userid") REFERENCES "public"."user" ("userid") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."lecturer" ADD FOREIGN KEY ("chairid") REFERENCES "public"."chair" ("chairid") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."roomallocation"
-- ----------------------------
ALTER TABLE "public"."roomallocation" ADD FOREIGN KEY ("roomid") REFERENCES "public"."room" ("roomid") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."roomallocation" ADD FOREIGN KEY ("courseid") REFERENCES "public"."course" ("courseid") ON DELETE RESTRICT ON UPDATE CASCADE;
