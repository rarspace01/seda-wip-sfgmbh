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



--
-- Name: building_bulidingid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('building_bulidingid_seq', 1, false);


--
-- Data for Name: chair; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO chair VALUES (103, 'Informationssysteme in Dienstleistungsbereichen', 105, -1, '4.1', 'WI', 'ISDL');
INSERT INTO chair VALUES (107, 'Industrielle Informationssysteme', 150, -1, '4.1', 'WI', 'IIS');
INSERT INTO chair VALUES (100, 'Kommunikationsdienste, Telekommunikationssysteme und Rechnernetze', 101, -1, '', 'WI', 'KTR');
INSERT INTO chair VALUES (110, 'Kulturinformatik', 152, -1, '', 'WI', 'KInf');
INSERT INTO chair VALUES (108, 'Kognitive Systeme', 151, -1, '', 'WI', 'KogSys');
INSERT INTO chair VALUES (105, 'Human Computer Interaction', 153, -1, '', 'WI', 'HCI');
INSERT INTO chair VALUES (109, 'Medieninformatik', 154, -1, '', 'WI', 'MI');
INSERT INTO chair VALUES (104, 'Systementwicklung und Datenbankanwendung', 112, -1, '1', 'WI', 'SEDA');
INSERT INTO chair VALUES (111, 'Distributed Systems Group', 157, -1, '', 'WI', 'DSG');
INSERT INTO chair VALUES (112, 'Softwaretechnik', 158, -1, '', 'WI', 'SWT');
INSERT INTO chair VALUES (101, 'Grundlagen der Informatik', 159, -1, '', 'WI', 'GdI');


--
-- Name: chair_chairid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('chair_chairid_seq', 112, true);


--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO course VALUES (129, 134, 'SEDA-EbIS-1', 'Entwicklung betrieblicher Informationssysteme', 2, 'Tutorium', '', 25, true);
INSERT INTO course VALUES (130, 154, 'MI-AuD-B', 'Algorithmen und Datenstrukturen	', 2, 'Vorlesung', '', 25, true);
INSERT INTO course VALUES (131, 154, 'MI-IR1-M', ' Information Retrieval 1 (Grundlagen, Modelle und Anwendungen)', 2, 'Vorlesung', '', 25, true);
INSERT INTO course VALUES (132, 157, 'DSG-EidI-M', 'Einf. in die Informatik', 2, 'Vorlesung', '', 50, true);
INSERT INTO course VALUES (133, 157, 'DSG-DSAM-M', 'Distributed Systems Architecture and Middleware', 2, 'Vorlesung', '', 25, true);
INSERT INTO course VALUES (102, 22, 'WG-B', 'Wissenschaftliche Grundlagen', 2, 'Vorlesung', '', 30, true);
INSERT INTO course VALUES (103, 22, 'BA-B', 'Boolische Algebra', 6, 'Vorlesung', '', 90, true);
INSERT INTO course VALUES (134, 112, 'SEDA-EbIS2-M', 'Entwicklung betrieblicher Informationssysteme', 2, 'Vorlesung', '', 25, true);
INSERT INTO course VALUES (108, 22, 'EinfMi-B', 'Einf. in die Medieninformatik', 4, 'Vorlesung', '', 120, true);
INSERT INTO course VALUES (106, 22, 'BA-T-B', 'Boolische Algebra Tutorium', 4, 'Tutorium', '', 25, true);
INSERT INTO course VALUES (107, 22, 'BA-T-B', 'Boolische Algebra Tutorium', 4, 'Tutorium', '', 40, true);
INSERT INTO course VALUES (135, 112, 'SEDA-EbIS3-M', 'Entwicklung betrieblicher Informationssysteme', 2, 'Vorlesung', '', 25, true);
INSERT INTO course VALUES (115, 101, 'KTR-MAKV-M', 'Modellierung und Analyse von Kommunikationsnetzen und Verteilten Systemen', 4, 'Vorlesung', '', 10, false);
INSERT INTO course VALUES (114, 101, 'KTR-Mobi-M', 'Mobilkommunikation', 4, 'Vorlesung', '', 10, false);
INSERT INTO course VALUES (113, 101, 'KTR-MMK-M', 'Multimedia-Kommunikation in Hochgeschwindigkeitsnetzen', 4, 'Vorlesung', '', 5, false);
INSERT INTO course VALUES (116, 101, 'KTR-GIK-M', 'Grundbausteine der Internet-Kommunikation', 4, 'Vorlesung', '', 8, false);
INSERT INTO course VALUES (112, 105, 'ISDL-SOA', 'SOA-Governance and Evaluation', 4, 'Vorlesung', '', 25, true);
INSERT INTO course VALUES (136, 101, 'KTR-DatK-B', 'Datenkommunikation', 2, 'Vorlesung', '', 2, true);
INSERT INTO course VALUES (111, 105, 'ISDL-SaaS', 'Cloud, Consumerization, In-memory Computing', 2, 'Vorlesung', '', 20, true);
INSERT INTO course VALUES (117, 150, 'IIS-E-Biz-E', 'Electronic Business', 2, 'Vorlesung', '', 50, true);
INSERT INTO course VALUES (119, 150, 'IIS-IBS-M', 'Innerbetriebliche Systeme', 2, 'Vorlesung', '', 50, true);
INSERT INTO course VALUES (120, 150, 'IIS-IOS-M', 'Interorganisationssysteme', 2, 'Vorlesung', '', 50, true);
INSERT INTO course VALUES (121, 150, 'IIS-MODS-M', 'Modulare und On-Demand-Systeme', 2, 'Vorlesung', '	', 50, true);
INSERT INTO course VALUES (118, 150, 'IIS-EAM-B', 'Enterprise Architecture Management', 2, 'Vorlesung', '', 50, true);
INSERT INTO course VALUES (110, 105, 'ISDL-3-M', 'IT-Value', 4, 'Vorlesung', '', 60, true);
INSERT INTO course VALUES (109, 105, 'ISDL-2-M', 'Optimierung IT-lastiger Prozesse', 4, 'Vorlesung', '', 60, true);
INSERT INTO course VALUES (100, 105, 'ISDL-1-M', 'Standards und Netzwerke', 4, 'Vorlesung', 'Dezentrales Standardisierungsproblem', 60, true);
INSERT INTO course VALUES (122, 153, 'HCI-IS-B', 'Interaktive Systeme', 2, 'Vorlesung', '', 50, true);
INSERT INTO course VALUES (123, 153, 'HCI-IS-B', 'Interaktive Systeme', 2, 'Tutorium', '', 50, true);
INSERT INTO course VALUES (124, 151, 'KogSys-IA-B', 'Intelligente Agenten', 2, 'Vorlesung', '', 25, true);
INSERT INTO course VALUES (125, 151, 'KogSys-IA-B', 'Intelligente Agenten', 2, 'Tutorium', '', 25, true);
INSERT INTO course VALUES (126, 112, 'SEDA-DMS-B', 'Datenmanagementsysteme', 2, 'Vorlesung', '', 50, true);
INSERT INTO course VALUES (127, 137, 'SEDA-DMS-B', 'Datenmanagementsysteme', 2, 'Tutorium', '', 25, true);
INSERT INTO course VALUES (128, 112, 'SEDA-EbIS1-M', 'Entwicklung betrieblicher Informationssysteme', 2, 'Tutorium', '', 25, true);


--
-- Name: course_courseid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('course_courseid_seq', 137, true);


--
-- Data for Name: lecturer; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO lecturer VALUES (104, 102, 103);
INSERT INTO lecturer VALUES (105, 103, 103);
INSERT INTO lecturer VALUES (106, 104, 103);
INSERT INTO lecturer VALUES (108, 106, 103);
INSERT INTO lecturer VALUES (152, 150, 107);
INSERT INTO lecturer VALUES (155, 153, 105);
INSERT INTO lecturer VALUES (153, 151, 108);
INSERT INTO lecturer VALUES (116, 114, 104);
INSERT INTO lecturer VALUES (123, 121, 104);
INSERT INTO lecturer VALUES (156, 154, 109);
INSERT INTO lecturer VALUES (160, 158, 112);
INSERT INTO lecturer VALUES (161, 159, 101);
INSERT INTO lecturer VALUES (114, 112, 104);
INSERT INTO lecturer VALUES (101, 42, 103);
INSERT INTO lecturer VALUES (159, 157, 111);
INSERT INTO lecturer VALUES (107, 105, 103);
INSERT INTO lecturer VALUES (103, 101, 100);
INSERT INTO lecturer VALUES (102, 22, 100);
INSERT INTO lecturer VALUES (158, 156, 100);
INSERT INTO lecturer VALUES (136, 134, 104);
INSERT INTO lecturer VALUES (148, 146, 103);
INSERT INTO lecturer VALUES (154, 152, 110);
INSERT INTO lecturer VALUES (139, 137, 104);


--
-- Name: lecturer_lecturerid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('lecturer_lecturerid_seq', 161, true);


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO room VALUES ('WE5/1.003', 0, '1', 60, 30, 2, 0, 0, 0, 0, 102);
INSERT INTO room VALUES ('WE5/2.068', 0, '2.1', 20, 0, 2, 0, 0, 0, 0, 100);
INSERT INTO room VALUES ('WE5/0.019', 0, '0', 250, 0, 2, 0, 1, 0, 0, 101);
INSERT INTO room VALUES ('WE5/5.005', 0, '5', 20, 0, 1, 0, 0, 1, 0, 104);
INSERT INTO room VALUES ('WE5/4.016', 0, '4', 60, 30, 2, 0, 0, 0, 1, 105);
INSERT INTO room VALUES ('WE5/2.020', 0, '2.1', 20, 0, 2, 0, 0, 0, 0, 106);
INSERT INTO room VALUES ('WE5/2.003', 0, '2.2', 15, 0, 2, 0, 0, 0, 0, 108);


--
-- Name: room_roomid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('room_roomid_seq', 108, true);


--
-- Data for Name: roomallocation; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO roomallocation VALUES (100, 100, 101, 'SS 13', 1, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (129, 117, 102, 'WS 13/14', 2, 4, 'accepted', NULL, 'accepted_at_1359118687');
INSERT INTO roomallocation VALUES (127, 119, 102, 'WS 13/14', 1, 1, 'denied', 'Sehr geehrter Prof.Overhage, leider gab es einen Konflift, daher sende ich Ihnen diese Gegenvorschlag.', 'counter_at_1359118724');
INSERT INTO roomallocation VALUES (133, 125, 102, 'SS 13', 3, 2, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (130, 118, 102, 'SS 13', 2, 4, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (131, 122, 102, 'SS 13', 2, 5, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (132, 123, 102, 'SS 13', 3, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (134, 124, 102, 'SS 13', 3, 3, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (135, 128, 102, 'SS 13', 3, 4, 'waiting', NULL, NULL);
INSERT INTO roomallocation VALUES (137, 127, 102, 'SS 13', 4, 5, 'accepted', NULL, 'accepted_at_1359125513');
INSERT INTO roomallocation VALUES (138, 129, 102, 'SS 13', 4, 1, 'counter', 'SgH Teusch, leider kann Ihnen dieser Raum zugewiesen werden. Ich unterbreite Ihnen hiermit einen Gegenvorschlag.MfG', 'counter_at_1359125569');
INSERT INTO roomallocation VALUES (139, 130, 102, 'SS 13', 5, 5, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (140, 131, 102, 'SS 13', 5, 6, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (105, 102, 108, 'SS 13', 1, 4, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (111, 108, 101, 'SS 13', 1, 5, 'denied', 'Das geht hier nicht!', 'counter_at_1359082542');
INSERT INTO roomallocation VALUES (117, 109, 102, 'SS 13', 1, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (109, 103, 101, 'SS 13', 1, 2, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (118, 111, 102, 'SS 13', 1, 2, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (121, 113, 104, 'SS 13', 1, 3, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (120, 112, 102, 'SS 13', 1, 3, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (122, 114, 102, 'SS 13', 1, 4, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (123, 115, 102, 'SS 13', 1, 5, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (124, 116, 101, 'SS 13', 2, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (119, 111, 102, 'SS 13', 2, 3, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (116, 109, 101, 'SS 13', 5, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (125, 121, 102, 'SS 13', 2, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (126, 120, 102, 'SS 13', 2, 2, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (128, 119, 100, 'WS 13/14', 2, 4, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (141, 133, 102, 'SS 13', 4, 6, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (142, 132, 102, 'SS 13', 5, 1, 'accepted', NULL, NULL);
INSERT INTO roomallocation VALUES (143, 135, 102, 'SS 13', 4, 3, 'accepted', NULL, 'accepted_at_1359127568');
INSERT INTO roomallocation VALUES (144, 134, 102, 'SS 13', 3, 5, 'counter', 'SgH Prof.Sinz,
leider gab es bereits eine Raumanfrage zu diesem Raum. Aus diesem Grund sende ich Ihnen hiermit einen Gegenvorschlag.
MfG Verwaltung', 'counter_at_1359127623');
INSERT INTO roomallocation VALUES (136, 126, 102, 'SS 13', 3, 4, 'accepted', NULL, 'accepted_at_1359128162');
INSERT INTO roomallocation VALUES (145, 136, 102, 'SS 13', 3, 4, 'waiting', NULL, NULL);


--
-- Name: roomallocation_roomallocationid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('roomallocation_roomallocationid_seq', 145, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: WIP-SFGmbH
--

INSERT INTO "user" VALUES (137, 'matthiasW', '8c035d72f049a6a0dec0f5f6e358599094657e45e4a27d19d256314766c2fa80', '3cafob9', 'matthias.wolf@uni-bamberg.de', 'lecturer', 'Matthias', 'Wolf', 0, false);
INSERT INTO "user" VALUES (101, 'udoK', '2128c84a8c133adae9e752dab0bb51909cb5dc43080c56af399ac7f6dfe9df2f', '2hrtspv', 'udo.krieger@uni-bamberg.de', 'lecturer', 'Udo', 'Krieger', 1359128237, false);
INSERT INTO "user" VALUES (8, 'Verw', '3e280c39a065b49322088ae34b31fdb9052374b07764e65e2bbee279705a410e', 'j2t66r', 'Verwaltung@uni-bamberg.de', 'orga', '', '', 1359128316, false);
INSERT INTO "user" VALUES (39, 'denis', '0daa5f04411e3f93a75e7a7172dc234ad23806166be0c14173128af39b1e3f9a', '1aqqf1p', 'denis.hamann@stud.uni-bamberg.de', 'orga', 'Denis', 'Hamann', 1359125685, false);
INSERT INTO "user" VALUES (159, 'michaelM', '2003e49b27670c9e45bb9b5b57f1eb89ba9cf75a2684d61f553cc16a9de4b913', '2h99199', 'michael.mendler@uni-bamberg.de', 'lecturer', 'Michael', 'Mendler', 0, false);
INSERT INTO "user" VALUES (112, 'elmarS', '783c38e90dd3446445392a32e172e7dd56e7f886a1016ee1a887e2ca4c503b3d', '1qf33p', 'elmar.sinz@uni-bamberg.de', 'lecturer', 'Elmar', 'Sinz', 1359126278, false);
INSERT INTO "user" VALUES (42, 'denis2', '3635aab2c8de16602d81c6c1eade9fea7d99514819a678d0e00639cef4ebce62', '3fghbln', 'denis2@test.com', 'lecturer', 'Denis', 'Hamann', 1359126373, false);
INSERT INTO "user" VALUES (157, 'guidoW', '6dada59fd582886e276306826b355ee811a72bfb0a412d4194b2f39944424151', '3p4dqmh', 'guido.wirtz@uni-bamberg.de', 'lecturer', 'Gudio', 'Wirtz', 1359127081, false);
INSERT INTO "user" VALUES (102, 'svenL', '47e6ac51400553504c9fcfd090ce6803bc2ca6b7df217c04cfb9f43a27971cb1', '57p86s', 'sven.laumer@uni-bamberg.de', 'lecturer', 'Sven', 'Laumer', 0, false);
INSERT INTO "user" VALUES (105, 'timW', '48f68bc3b26081484d226c9cb237f1883e3374a3ec55fadedba5ef0095e1b984', '28gsurs', 'tim.weitzel@uni-bamberg.de', 'lecturer', 'Tim', 'Weitzel', 1359127841, false);
INSERT INTO "user" VALUES (134, 'andreeT', 'ee40588357f9fbf64baee787b57246116302c3d40958effa29db3360caf75200', '3o8fi2m', 'andree.teusch@uni-bamberg.de', 'lecturer', 'Andree', 'Teusch', 0, false);
INSERT INTO "user" VALUES (156, 'marcelG', '8e6bd610a0ac2c34d6fe1cbc85611c894f1c983ab9ba30d4082265e7bbb9b85d', 'csaduc', 'marcel.grossmann@uni-bamberg.de', 'lecturer', 'Marcel', 'Grossmann', 1359107248, false);
INSERT INTO "user" VALUES (114, 'domenikB', '25e93280bb71a0b193ec491a8bebfc6c9f8d23af742d9214f3b496dba132563f', '3hpjogd', 'domenik.bork@uni-bamberg.de', 'lecturer', 'Domenik', 'Bork', 0, false);
INSERT INTO "user" VALUES (103, 'bernhardM', '4758170464d9e842fc6abc1f5e0739877e535dfa10b60a4eda6bd6712189b3d6', '2uttqlj', 'bernhard.moos@uni-bamberg.de', 'lecturer', 'Bernhard', 'Moos', 0, false);
INSERT INTO "user" VALUES (104, 'frankS', '5ff2d5a930b84908e744082b1b5c46b21a9644f22ca61db0806673adbeda6b77', '1o8amr2', 'frank.schlosser@uni-bamberg.de', 'lecturer', 'Frank', 'Schlosser', 0, false);
INSERT INTO "user" VALUES (106, 'danielB', '8636deb6a7ad9f7d0140b16fba72e5ede465071e353641e4a2d62ceda14f47c3', 'uign0i', 'daniel.beimborn@uni-bamberg.de', 'lecturer', 'Daniel', 'Beimborn', 0, false);
INSERT INTO "user" VALUES (121, 'reginaH', '7c4d8bb89720a945cc060677050a91bdeedc724c13893dc4cfb896d7da58049d', '2eilvrp', 'regina.henninges@uni-bamberg.de', 'lecturer', 'Regina', 'Henninges', 0, false);
INSERT INTO "user" VALUES (153, 'tomG', '27a650d7a85f1648b14c5da929914151007061e4eb697acb0c93f83e1b4e9912', 'ij6lfi', 'tom.gross@uni-bamberg.de', 'lecturer', 'Tom', 'Gross', 1359119392, false);
INSERT INTO "user" VALUES (151, 'uteS', '528960c1fcacf3bdfcd17e4a3ad13cfde223e378e7e79c8c819ae2e9337390c6', '3acp6v9', 'ute.schmid@uni-bamberg.de', 'lecturer', 'Ute', 'Schmid', 1359120036, false);
INSERT INTO "user" VALUES (146, 'jochenM', '4cb4e8392275534f37210a673be315fc9b8f3d94fa07252efc61768d88de839c', '3snl18g', 'jochen.Malinowski@uni-bamberg.de', 'lecturer', 'Jochen', 'Malinowski', 0, false);
INSERT INTO "user" VALUES (152, 'christophS', '16c7fa67e4cbac34e20b455c9b9c5cecffd770bba7d09f4f04f4e7838c76612a', '1umpscq', 'christoph.schlieder@uni-bamberg.de', 'lecturer', 'Christoph', 'Schliederer', 0, false);
INSERT INTO "user" VALUES (154, 'andreasH', '0db07c2a3282ad24c1f7da28fa13f4e09c969ebc5e1743a9f4c945be5d3018b6', '1idre8k', 'andreas.henrich@uni-bamberg.de', 'lecturer', 'Andres', 'Henrich', 1359125764, false);
INSERT INTO "user" VALUES (158, 'geraldL', 'ad979d05bafa9d82a2c2d8f97edf0118bdc57c8fb901efe5976bf9f07fca28b1', 'bsd193', 'gerald.luettgen@uni-bamberg.de', 'lecturer', 'Gerald', 'L.', 0, false);
INSERT INTO "user" VALUES (22, 'Doz', '8430df002cc316b8d8973aa1f749958a6fdf43eb250179f127fae755868f7665', '3ki7555', 'max@mustermann.de', 'lecturer', 'Max', 'Mustermann', 1359128332, false);
INSERT INTO "user" VALUES (150, 'svenO', 'b8f64c69d9249787e6de1c64a80a2e0af16c0d21864df30f178c7fb8b819dd51', '3tockeq', 'sven.overhage@uni-bamberg.de', 'lecturer', 'Sven', 'Overhage', 1359118752, false);


--
-- Name: user_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: WIP-SFGmbH
--

SELECT pg_catalog.setval('user_userid_seq', 159, true);


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

