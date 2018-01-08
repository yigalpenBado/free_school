-- Table: public.enseignant

-- DROP TABLE public.enseignant;

CREATE TABLE public.enseignant
(
    matricule character varying(10) COLLATE pg_catalog."default" NOT NULL,
    nom character varying(100) COLLATE pg_catalog."default",
    prenom character varying(100) COLLATE pg_catalog."default",
    telephone character varying(15) COLLATE pg_catalog."default",
    CONSTRAINT pk_enseignant PRIMARY KEY (matricule)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.enseignant
    OWNER to postgres;

-- Index: enseignant_pk

-- DROP INDEX public.enseignant_pk;

CREATE UNIQUE INDEX enseignant_pk
    ON public.enseignant USING btree
    (matricule COLLATE pg_catalog."default")
    TABLESPACE pg_default;