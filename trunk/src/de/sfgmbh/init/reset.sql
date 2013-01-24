--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.lecturer DROP CONSTRAINT lecturer_userid_fkey;
ALTER TABLE ONLY public.lecturer DROP CONSTRAINT lecturer_chairid_fkey;
ALTER TABLE ONLY public.chair DROP CONSTRAINT chair_chairowner_fkey;
ALTER TABLE ONLY public.lecturer DROP CONSTRAINT "FK_userid";
ALTER TABLE ONLY public.roomallocation DROP CONSTRAINT "FK_roomid";
ALTER TABLE ONLY public.course DROP CONSTRAINT "FK_lecturerid";
ALTER TABLE ONLY public.roomallocation DROP CONSTRAINT "FK_courseid";
ALTER TABLE ONLY public.chair DROP CONSTRAINT "FK_chairowner";
ALTER TABLE ONLY public.lecturer DROP CONSTRAINT "FK_chairid";
ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
ALTER TABLE ONLY public."user" DROP CONSTRAINT user_mail_key;
ALTER TABLE ONLY public."user" DROP CONSTRAINT user_login_key;
ALTER TABLE ONLY public.lecturer DROP CONSTRAINT lecturer_userid_key;
ALTER TABLE ONLY public.lecturer DROP CONSTRAINT lecturer_pkey;
ALTER TABLE ONLY public.chair DROP CONSTRAINT chair_pkey;
ALTER TABLE ONLY public.chair DROP CONSTRAINT chair_chairname_key;
ALTER TABLE ONLY public.chair DROP CONSTRAINT chair_chairacronym_key;
ALTER TABLE ONLY public."user" DROP CONSTRAINT "Unique_login";
ALTER TABLE ONLY public.lecturer DROP CONSTRAINT "UNIQUE_userid";
ALTER TABLE ONLY public.room DROP CONSTRAINT "UNIQUE_roomnumber";
ALTER TABLE ONLY public."user" DROP CONSTRAINT "UNIQUE_email";
ALTER TABLE ONLY public.chair DROP CONSTRAINT "UNIQUE_chairname";
ALTER TABLE ONLY public.chair DROP CONSTRAINT "UNIQUE_chairacronym";
ALTER TABLE ONLY public.room DROP CONSTRAINT "PK_roomid";
ALTER TABLE ONLY public.roomallocation DROP CONSTRAINT "PK_roomallocationid";
ALTER TABLE ONLY public.course DROP CONSTRAINT "PK_courseid";
ALTER TABLE ONLY public.building DROP CONSTRAINT "PK_buildingid";
ALTER TABLE public."user" ALTER COLUMN userid DROP DEFAULT;
ALTER TABLE public.roomallocation ALTER COLUMN roomallocationid DROP DEFAULT;
ALTER TABLE public.room ALTER COLUMN roomid DROP DEFAULT;
ALTER TABLE public.lecturer ALTER COLUMN lecturerid DROP DEFAULT;
ALTER TABLE public.course ALTER COLUMN courseid DROP DEFAULT;
ALTER TABLE public.chair ALTER COLUMN chairid DROP DEFAULT;
ALTER TABLE public.building ALTER COLUMN buildingid DROP DEFAULT;
DROP SEQUENCE public.user_userid_seq;
DROP TABLE public."user";
DROP SEQUENCE public.roomallocation_roomallocationid_seq;
DROP TABLE public.roomallocation;
DROP SEQUENCE public.room_roomid_seq;
DROP TABLE public.room;
DROP SEQUENCE public.lecturer_lecturerid_seq;
DROP TABLE public.lecturer;
DROP SEQUENCE public.course_courseid_seq;
DROP TABLE public.course;
DROP SEQUENCE public.chair_chairid_seq;
DROP TABLE public.chair;
DROP SEQUENCE public.building_bulidingid_seq;
DROP TABLE public.building;
DROP EXTENSION plpgsql;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: building; Type: TABLE; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

CREATE TABLE building (
    buildingid integer NOT NULL,
    address character varying(512),
    buildingname character varying(255)
);


ALTER TABLE public.building OWNER TO "WIP-SFGmbH";

--
-- Name: building_bulidingid_seq; Type: SEQUENCE; Schema: public; Owner: WIP-SFGmbH
--

CREATE SEQUENCE building_bulidingid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.building_bulidingid_seq OWNER TO "WIP-SFGmbH";

--
-- Name: building_bulidingid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: WIP-SFGmbH
--

ALTER SEQUENCE building_bulidingid_seq OWNED BY building.buildingid;


--
-- Name: chair; Type: TABLE; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

CREATE TABLE chair (
    chairid integer NOT NULL,
    chairname character varying(255) NOT NULL,
    chairowner integer,
    buildingid integer,
    chairlevel character varying(55),
    faculty character varying(255) DEFAULT 'n/a'::character varying NOT NULL,
    chairacronym character varying(55) NOT NULL
);


ALTER TABLE public.chair OWNER TO "WIP-SFGmbH";

--
-- Name: chair_chairid_seq; Type: SEQUENCE; Schema: public; Owner: WIP-SFGmbH
--

CREATE SEQUENCE chair_chairid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chair_chairid_seq OWNER TO "WIP-SFGmbH";

--
-- Name: chair_chairid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: WIP-SFGmbH
--

ALTER SEQUENCE chair_chairid_seq OWNED BY chair.chairid;


--
-- Name: course; Type: TABLE; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

CREATE TABLE course (
    courseid bigint NOT NULL,
    lecturerid bigint NOT NULL,
    courseacronym character varying(128) DEFAULT 'n/a'::character varying NOT NULL,
    coursename character varying(256) DEFAULT 'n/a'::character varying NOT NULL,
    sws double precision DEFAULT 0 NOT NULL,
    coursekind character varying(32) DEFAULT 'n/a'::character varying NOT NULL,
    coursedescription text,
    expectedattendees integer DEFAULT 0 NOT NULL,
    lecturerenabled boolean DEFAULT false NOT NULL
);


ALTER TABLE public.course OWNER TO "WIP-SFGmbH";

--
-- Name: course_courseid_seq; Type: SEQUENCE; Schema: public; Owner: WIP-SFGmbH
--

CREATE SEQUENCE course_courseid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_courseid_seq OWNER TO "WIP-SFGmbH";

--
-- Name: course_courseid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: WIP-SFGmbH
--

ALTER SEQUENCE course_courseid_seq OWNED BY course.courseid;


--
-- Name: lecturer; Type: TABLE; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

CREATE TABLE lecturer (
    lecturerid bigint NOT NULL,
    userid bigint NOT NULL,
    chairid bigint NOT NULL
);


ALTER TABLE public.lecturer OWNER TO "WIP-SFGmbH";

--
-- Name: lecturer_lecturerid_seq; Type: SEQUENCE; Schema: public; Owner: WIP-SFGmbH
--

CREATE SEQUENCE lecturer_lecturerid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lecturer_lecturerid_seq OWNER TO "WIP-SFGmbH";

--
-- Name: lecturer_lecturerid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: WIP-SFGmbH
--

ALTER SEQUENCE lecturer_lecturerid_seq OWNED BY lecturer.lecturerid;


--
-- Name: room; Type: TABLE; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

CREATE TABLE room (
    roomnumber character varying(255) NOT NULL,
    buildingid integer DEFAULT (-1) NOT NULL,
    level character varying(55) DEFAULT 'n/a'::character varying NOT NULL,
    seats integer DEFAULT 0 NOT NULL,
    pcseats integer DEFAULT 0 NOT NULL,
    beamer integer DEFAULT 0 NOT NULL,
    visualizer integer DEFAULT 0 NOT NULL,
    overheads integer DEFAULT 0 NOT NULL,
    chalkboards integer DEFAULT 0 NOT NULL,
    whiteboards integer DEFAULT 0 NOT NULL,
    roomid bigint NOT NULL
);


ALTER TABLE public.room OWNER TO "WIP-SFGmbH";

--
-- Name: room_roomid_seq; Type: SEQUENCE; Schema: public; Owner: WIP-SFGmbH
--

CREATE SEQUENCE room_roomid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_roomid_seq OWNER TO "WIP-SFGmbH";

--
-- Name: room_roomid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: WIP-SFGmbH
--

ALTER SEQUENCE room_roomid_seq OWNED BY room.roomid;


--
-- Name: roomallocation; Type: TABLE; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

CREATE TABLE roomallocation (
    roomallocationid bigint NOT NULL,
    courseid bigint NOT NULL,
    roomid bigint NOT NULL,
    semester character varying(32) NOT NULL,
    day integer NOT NULL,
    "time" bigint NOT NULL,
    approved character varying DEFAULT 'waiting'::character varying NOT NULL,
    orgamessage text,
    comment text
);


ALTER TABLE public.roomallocation OWNER TO "WIP-SFGmbH";

--
-- Name: roomallocation_roomallocationid_seq; Type: SEQUENCE; Schema: public; Owner: WIP-SFGmbH
--

CREATE SEQUENCE roomallocation_roomallocationid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roomallocation_roomallocationid_seq OWNER TO "WIP-SFGmbH";

--
-- Name: roomallocation_roomallocationid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: WIP-SFGmbH
--

ALTER SEQUENCE roomallocation_roomallocationid_seq OWNED BY roomallocation.roomallocationid;


--
-- Name: user; Type: TABLE; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

CREATE TABLE "user" (
    userid bigint NOT NULL,
    login character varying(128) NOT NULL,
    pass character varying(128) NOT NULL,
    salt character varying(128) DEFAULT 'n/a'::character varying NOT NULL,
    mail character varying(128) DEFAULT 'n/a'::character varying NOT NULL,
    class character varying(128) DEFAULT 'guest'::character varying NOT NULL,
    fname character varying(128) DEFAULT 'n/a'::character varying NOT NULL,
    lname character varying(128) DEFAULT 'n/a'::character varying NOT NULL,
    lastlogin bigint DEFAULT 0 NOT NULL,
    disabled boolean DEFAULT false
);


ALTER TABLE public."user" OWNER TO "WIP-SFGmbH";

--
-- Name: user_userid_seq; Type: SEQUENCE; Schema: public; Owner: WIP-SFGmbH
--

CREATE SEQUENCE user_userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_userid_seq OWNER TO "WIP-SFGmbH";

--
-- Name: user_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: WIP-SFGmbH
--

ALTER SEQUENCE user_userid_seq OWNED BY "user".userid;


--
-- Name: buildingid; Type: DEFAULT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY building ALTER COLUMN buildingid SET DEFAULT nextval('building_bulidingid_seq'::regclass);


--
-- Name: chairid; Type: DEFAULT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY chair ALTER COLUMN chairid SET DEFAULT nextval('chair_chairid_seq'::regclass);


--
-- Name: courseid; Type: DEFAULT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY course ALTER COLUMN courseid SET DEFAULT nextval('course_courseid_seq'::regclass);


--
-- Name: lecturerid; Type: DEFAULT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY lecturer ALTER COLUMN lecturerid SET DEFAULT nextval('lecturer_lecturerid_seq'::regclass);


--
-- Name: roomid; Type: DEFAULT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY room ALTER COLUMN roomid SET DEFAULT nextval('room_roomid_seq'::regclass);


--
-- Name: roomallocationid; Type: DEFAULT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY roomallocation ALTER COLUMN roomallocationid SET DEFAULT nextval('roomallocation_roomallocationid_seq'::regclass);


--
-- Name: userid; Type: DEFAULT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY "user" ALTER COLUMN userid SET DEFAULT nextval('user_userid_seq'::regclass);


--
-- Data for Name: building; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO building VALUES (1, 'An der Weberei 5', 'ERBA');
INSERT INTO building VALUES (2, 'Feldkirchen Str.17', 'FEKI');


--
-- Name: building_bulidingid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('building_bulidingid_seq', 1, false);


--
-- Data for Name: chair; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO chair VALUES (2, 'Industrielle Informationssysteme', 38, 1, '4', 'WI', 'IIS');
INSERT INTO chair VALUES (3, 'Soziale Netzwerke', NULL, 1, NULL, 'WIAI', 'SNA');
INSERT INTO chair VALUES (4, 'Systementwicklung und Datenbankanwendung', NULL, 1, NULL, 'WIAI', 'SEDA');
INSERT INTO chair VALUES (5, 'Kommunikationsdienste, Telekommunikationsdienste und Rechnernetze', NULL, 1, NULL, 'WIAI', 'KTR');
INSERT INTO chair VALUES (6, 'Grundlagen der Informatik', NULL, 1, NULL, 'WIAI', 'GDI');
INSERT INTO chair VALUES (7, 'Softwaretechnik und Programmiersprachen', NULL, 1, 'R4', 'WI', 'SWT');
INSERT INTO chair VALUES (14, 'Mobile Systeme', 42, -1, '5', 'WI', 'MOBSYS');
INSERT INTO chair VALUES (15, 'Tödliches Spielen', NULL, -1, '666', 'WI', 'TS');
INSERT INTO chair VALUES (19, 'Informationssysteme in Dienstleistungsbereichen', NULL, -1, '04', 'WI', 'ISDL');
INSERT INTO chair VALUES (16, 'Binäres Treiben', 65, -1, '04', 'WI', 'BTR');


--
-- Name: chair_chairid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('chair_chairid_seq', 99, true);


--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO course VALUES (5, 2, 'AN1', 'Spezielles Lernen', 4, 'Vorlesung', 'k. A.', 2, true);
INSERT INTO course VALUES (6, 2, 'AN2', 'Spezielles Lernen', 2, 'Tutorium', 'k. A.', 2, true);
INSERT INTO course VALUES (2, 1, 'PARA2', 'Paranormales Training', 2, 'Übung', 'Keine Angaben', 33, false);
INSERT INTO course VALUES (1, 1, 'PARA1', 'Paranormales Training', 12, 'Vorlesung', 'Keine Angaben', 250, true);
INSERT INTO course VALUES (8, 1, 'TEST2', 'Karaoke', 6, 'Übung', 'Test', 9, false);
INSERT INTO course VALUES (9, 38, 'TT2', 'Karaoke2', 0, 'Vorlesung', 'asfd
sdf', 666, true);
INSERT INTO course VALUES (7, 22, 'TEST', 'Test', 2, 'Tutorium', '', 4, true);
INSERT INTO course VALUES (11, 22, 'Test2', 'sdaf', 1, 'Vorlesung', '', 1, true);
INSERT INTO course VALUES (13, 22, 'KiWi', 'Kein Name', 3, 'Übung', '', 333, true);
INSERT INTO course VALUES (14, 43, 'TEST', 'TEST', 4, 'Vorlesung', 'TEST', 50, false);
INSERT INTO course VALUES (15, 43, 'TEST', 'Testveranstaltung', 2, 'Übung', '', 20, false);
INSERT INTO course VALUES (16, 43, 'TEST3', 'Testtutorium', 4, 'Tutorium', '', 15, true);
INSERT INTO course VALUES (17, 43, 'FERT', 'Fertigungstechnik', 2, 'Vorlesung', '', 100, false);
INSERT INTO course VALUES (18, 43, 'FEST', 'Festigkeitslehre', 2, 'Übung', '', 30, false);
INSERT INTO course VALUES (12, 42, 'JUT1', 'JUnit Tests', 4, 'Vorlesung', 'JUnit Tests', 10, true);


--
-- Name: course_courseid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('course_courseid_seq', 99, true);


--
-- Data for Name: lecturer; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO lecturer VALUES (1, 1, 5);
INSERT INTO lecturer VALUES (2, 2, 6);
INSERT INTO lecturer VALUES (8, 27, 7);
INSERT INTO lecturer VALUES (10, 29, 4);
INSERT INTO lecturer VALUES (14, 35, 3);
INSERT INTO lecturer VALUES (15, 36, 5);
INSERT INTO lecturer VALUES (16, 38, 2);
INSERT INTO lecturer VALUES (25, 65, 16);
INSERT INTO lecturer VALUES (20, 43, 4);
INSERT INTO lecturer VALUES (4, 22, 2);
INSERT INTO lecturer VALUES (19, 42, 14);
INSERT INTO lecturer VALUES (23, 63, 14);
INSERT INTO lecturer VALUES (24, 64, 16);


--
-- Name: lecturer_lecturerid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('lecturer_lecturerid_seq', 99, true);


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO room VALUES ('WE5/01.003', 1, '1.99', 30, 30, 2, 0, 0, 0, 0, 3);
INSERT INTO room VALUES ('WE5/02.068', 1, '2.1', 20, 0, 0, 0, 0, 0, 0, 27);
INSERT INTO room VALUES ('DD3/9', 1, '1.44', 33, 0, 0, 0, 0, 4, 0, 33);
INSERT INTO room VALUES ('DD3/99', 1, '1.55', 600, 50, 1, 1, 5, 1, 1, 35);
INSERT INTO room VALUES ('WE5/02.069', 1, '2.1', 20, 0, 0, 0, 0, 0, 0, 36);
INSERT INTO room VALUES ('WE5/0.019', 1, 'EG', 180, 0, 2, 0, 0, 0, 0, 5);
INSERT INTO room VALUES ('WE5/0.020', 1, 'EG', 120, 0, 2, 0, 0, 0, 0, 4);
INSERT INTO room VALUES ('WE5/05.005', 1, '5.0', 20, 0, 1, 0, 0, 0, 0, 2);


--
-- Name: room_roomid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('room_roomid_seq', 99, true);


--
-- Data for Name: roomallocation; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO roomallocation VALUES (11, 11, 3, 'SS 13', 1, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (12, 11, 3, 'SS 13', 1, 5, 'accepted', '', NULL);
INSERT INTO roomallocation VALUES (16, 7, 3, 'SS 13', 2, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (9, 2, 3, 'SS 13', 1, 3, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (13, 11, 3, 'SS 13', 2, 3, 'denied', 'Dies ist ein Test...
Test!', NULL);
INSERT INTO roomallocation VALUES (15, 7, 3, 'SS 13', 2, 2, 'denied', 'Test', NULL);
INSERT INTO roomallocation VALUES (25, 18, 33, 'SS 13', 1, 2, 'waiting', NULL, NULL);
INSERT INTO roomallocation VALUES (22, 17, 35, 'SS 13', 1, 4, 'accepted', 'Test, Test', NULL);
INSERT INTO roomallocation VALUES (8, 5, 4, 'SS 13', 3, 3, 'denied', NULL, NULL);
INSERT INTO roomallocation VALUES (1, 1, 3, 'SS 13', 1, 2, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (2, 1, 3, 'SS 13', 1, 3, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (5, 6, 2, 'SS 13', 2, 5, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (3, 2, 5, 'SS 13', 1, 1, 'counter', '', NULL);
INSERT INTO roomallocation VALUES (4, 5, 3, 'SS 13', 1, 4, 'counter', 'blub', NULL);
INSERT INTO roomallocation VALUES (10, 11, 3, 'SS 13', 1, 1, 'denied', NULL, NULL);
INSERT INTO roomallocation VALUES (17, 12, 27, 'SS 13', 1, 2, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (18, 13, 35, 'SS 13', 7, 7, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (21, 16, 2, 'SS 13', 6, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (19, 14, 35, 'SS 13', 1, 1, 'denied', NULL, NULL);
INSERT INTO roomallocation VALUES (14, 7, 3, 'SS 13', 1, 1, 'denied', 'GG!', NULL);
INSERT INTO roomallocation VALUES (20, 15, 3, 'SS 13', 3, 6, 'accepted', 'Sehr geehrter Herr Serno,
anbei ein Vorschlag zur Konfliktlösung.
MfG
Ihr Verwaltungsteam', NULL);
INSERT INTO roomallocation VALUES (23, 17, 35, 'SS 13', 1, 2, 'accepted', 'Sehr geehrter Herr Serno,
anbei ein Gegenvorschlag', NULL);
INSERT INTO roomallocation VALUES (24, 18, 35, 'SS 13', 1, 1, 'waiting', NULL, NULL);


--
-- Name: roomallocation_roomallocationid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('roomallocation_roomallocationid_seq', 99, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO "user" VALUES (1, 'hstad', '3d22f87a9655e2bc971e4f53bb623006dbc2fc9ab57b03e52df964895573d2c1', '10p06od', 'h@s.de', 'lecturer', 'Hannes', 'Stadler', 1358291445, true);
INSERT INTO "user" VALUES (2, 'akupf', 'none_set', 'none_set', 'test@test.de', 'lecturer', 'Anna', 'Kupfer', 0, false);
INSERT INTO "user" VALUES (27, 'profTest', 'd410260b4b689f52160aeefc2ef4e11d174695febbafd01ea634ec5fb5380ed0', '3e80ttk', 'p@te.st', 'lecturer', 'P', 'Test', 0, false);
INSERT INTO "user" VALUES (29, 'Sinz', '591d162438cd1528aadbe9784f8d0f84d3400faf1fbfba3f8038d772ed73c192', '3f16lft', 'e.sinz@uni-bamberg.de', 'lecturer', 'Elmar J.', 'Sinz', 1358874274, false);
INSERT INTO "user" VALUES (35, 'Test3', '28c5d2b2dded3936f260680b5f282a9033cc143601d42a9aa0ec060fd00a2bda', '257tar8', 'asfd@test.de', 'lecturer', '', 'asfd', 0, true);
INSERT INTO "user" VALUES (36, 'asdfasdf', 'c340602c1f1927da5e8d79f6481207a95f2aed7d296aaf852433b60f7e209560', '8vbtha', 'asdf@asfd.de', 'lecturer', '', 'asdf', 0, true);
INSERT INTO "user" VALUES (38, 'Ferstl', '5a20d7a5dfc82ab6f50506e215cbc348f6f2685c8b209cdd1282566a7604b1ba', '36vkv0a', 'o.ferstl@uni-bamberg.de', 'lecturer', 'Otto', 'Ferstl', 0, false);
INSERT INTO "user" VALUES (44, 'KK-', 'db45d1fd08471b1314c48745ea426b7d34a3f3f2edec1a182ded3495ccb6c786', '2svufgk', 'asdf@test.de', 'orga', 'TestK1', 'TestK1', 0, false);
INSERT INTO "user" VALUES (45, 'KK2', 'fd9f64014d81ffeb273b5699837834631d09d01064cd23b33ace75e2d8b87194', '1kbptcc', 'alskfdj@asdfk.de', 'orga', 'asdfkl', 'lökö', 0, true);
INSERT INTO "user" VALUES (46, 'asfdasfd', '2689c8c6109329f2d5ebebb99eb5011c9ed351fddfb8b17b784ca958f7cc773f', '2qsb6fb', 'asfd@kkd.de', 'orga', 'sadf', 'safdsf', 0, false);
INSERT INTO "user" VALUES (47, 'asfdFFö>', '2520dd66a06643dd0918a254f3fa6a7f6375cec4fcfe62b88a189bcbac7aa9cf', '3u3mf9k', 'ssdE3@asdlf.de', 'orga', 'as', 'asdf', 0, true);
INSERT INTO "user" VALUES (65, 'ANNKU', 'd9c857191f291eb5e546693de69ec800e01470356c635bd43af719acb3b9e6f9', '190i8jj', 'Anna@abc.de', 'lecturer', 'Ana', 'Kupfer2', 1359021948, false);
INSERT INTO "user" VALUES (63, 'TestDoz10', '0a22012836cdfb6b0225be23a6af1495e44f72dccf672743b54ae5b50155d747', 'ho3d6u', 'sadflk@asfd.de', 'lecturer', 'sdf', 'asdf', 0, true);
INSERT INTO "user" VALUES (43, 'mserno', 'a940b2c9112014c03cbd92d4cc7ddfbe332a5d5ce7b93df1c6a02662213a7919', '1emk244', 'mario.serno@uni-bamberg.de', 'lecturer', 'Mario', 'Serno', 1359032024, false);
INSERT INTO "user" VALUES (42, 'denis2', '3635aab2c8de16602d81c6c1eade9fea7d99514819a678d0e00639cef4ebce62', '3fghbln', 'denis2@test.com', 'lecturer', 'Denis', 'Hamann', 1358975101, false);
INSERT INTO "user" VALUES (8, 'Verw', '3e280c39a065b49322088ae34b31fdb9052374b07764e65e2bbee279705a410e', 'j2t66r', 'Verwaltung@uni-bamberg.de', 'orga', '', '', 1359033693, false);
INSERT INTO "user" VALUES (64, 'ANKU', '182d6c07afd7cf9fd7d7b5853205404834b3ff9498ebb1fae509d695ed4edf33', '2r3l6a5', 'abc@anna.de', 'lecturer', 'Anna', 'Kupfer1', 1359021111, false);
INSERT INTO "user" VALUES (22, 'Doz', '8430df002cc316b8d8973aa1f749958a6fdf43eb250179f127fae755868f7665', '3ki7555', 'asf@asdf.de', 'lecturer', 'DozVorname', 'DozNachname', 1359036097, false);
INSERT INTO "user" VALUES (39, 'denis', '0daa5f04411e3f93a75e7a7172dc234ad23806166be0c14173128af39b1e3f9a', '1aqqf1p', 'denis.hamann@stud.uni-bamberg.de', 'orga', 'Denis', 'Hamann', 1359040018, false);


--
-- Name: user_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('user_userid_seq', 99, true);


--
-- Name: PK_buildingid; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY building
    ADD CONSTRAINT "PK_buildingid" PRIMARY KEY (buildingid);


--
-- Name: PK_courseid; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY course
    ADD CONSTRAINT "PK_courseid" PRIMARY KEY (courseid);


--
-- Name: PK_roomallocationid; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY roomallocation
    ADD CONSTRAINT "PK_roomallocationid" PRIMARY KEY (roomallocationid);


--
-- Name: PK_roomid; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY room
    ADD CONSTRAINT "PK_roomid" PRIMARY KEY (roomid);


--
-- Name: UNIQUE_chairacronym; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY chair
    ADD CONSTRAINT "UNIQUE_chairacronym" UNIQUE (chairacronym);


--
-- Name: UNIQUE_chairname; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY chair
    ADD CONSTRAINT "UNIQUE_chairname" UNIQUE (chairname);


--
-- Name: UNIQUE_email; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT "UNIQUE_email" UNIQUE (mail);


--
-- Name: UNIQUE_roomnumber; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY room
    ADD CONSTRAINT "UNIQUE_roomnumber" UNIQUE (roomnumber);


--
-- Name: UNIQUE_userid; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY lecturer
    ADD CONSTRAINT "UNIQUE_userid" UNIQUE (userid);


--
-- Name: Unique_login; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT "Unique_login" UNIQUE (login);


--
-- Name: chair_chairacronym_key; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY chair
    ADD CONSTRAINT chair_chairacronym_key UNIQUE (chairacronym);


--
-- Name: chair_chairname_key; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY chair
    ADD CONSTRAINT chair_chairname_key UNIQUE (chairname);


--
-- Name: chair_pkey; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY chair
    ADD CONSTRAINT chair_pkey PRIMARY KEY (chairid);


--
-- Name: lecturer_pkey; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY lecturer
    ADD CONSTRAINT lecturer_pkey PRIMARY KEY (lecturerid);


--
-- Name: lecturer_userid_key; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY lecturer
    ADD CONSTRAINT lecturer_userid_key UNIQUE (userid);


--
-- Name: user_login_key; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_login_key UNIQUE (login);


--
-- Name: user_mail_key; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_mail_key UNIQUE (mail);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: WIP-SFGmbH; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (userid);


--
-- Name: FK_chairid; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY lecturer
    ADD CONSTRAINT "FK_chairid" FOREIGN KEY (chairid) REFERENCES chair(chairid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: FK_chairowner; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY chair
    ADD CONSTRAINT "FK_chairowner" FOREIGN KEY (chairowner) REFERENCES "user"(userid) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: FK_courseid; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY roomallocation
    ADD CONSTRAINT "FK_courseid" FOREIGN KEY (courseid) REFERENCES course(courseid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: FK_lecturerid; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY course
    ADD CONSTRAINT "FK_lecturerid" FOREIGN KEY (lecturerid) REFERENCES lecturer(userid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: FK_roomid; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY roomallocation
    ADD CONSTRAINT "FK_roomid" FOREIGN KEY (roomid) REFERENCES room(roomid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: FK_userid; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY lecturer
    ADD CONSTRAINT "FK_userid" FOREIGN KEY (userid) REFERENCES "user"(userid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: chair_chairowner_fkey; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY chair
    ADD CONSTRAINT chair_chairowner_fkey FOREIGN KEY (chairowner) REFERENCES "user"(userid) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: lecturer_chairid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY lecturer
    ADD CONSTRAINT lecturer_chairid_fkey FOREIGN KEY (chairid) REFERENCES chair(chairid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: lecturer_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: WIP-SFGmbH
--

ALTER TABLE ONLY lecturer
    ADD CONSTRAINT lecturer_userid_fkey FOREIGN KEY (userid) REFERENCES "user"(userid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

