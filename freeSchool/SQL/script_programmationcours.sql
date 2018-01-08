-- Table: programmation_cours

-- DROP TABLE programmation_cours;

CREATE TABLE programmation_cours
(
  code_classe character varying(5) NOT NULL,
  numero_annee integer NOT NULL,
  code_matiere character varying(5) NOT NULL,
  num_program integer NOT NULL,
  jour character varying(15),
  matricule character varying(10),
  heure_debut time without time zone,
  heure_fin time without time zone,
  CONSTRAINT pk_programmation_cours PRIMARY KEY (num_program),
  CONSTRAINT fk_programm_associati_anneesco FOREIGN KEY (numero_annee)
      REFERENCES annee_scolaire (numero_annee) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_programm_associati_classe FOREIGN KEY (code_classe)
      REFERENCES classe (code_classe) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_programm_associati_enseigna FOREIGN KEY (matricule)
      REFERENCES enseignant (matricule) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_programm_associati_matiere FOREIGN KEY (code_matiere)
      REFERENCES matiere (code_matiere) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE programmation_cours
  OWNER TO postgres;

-- Index: association10_fk

-- DROP INDEX association10_fk;

CREATE INDEX association10_fk
  ON programmation_cours
  USING btree
  (matricule COLLATE pg_catalog."default");

-- Index: association7_fk

-- DROP INDEX association7_fk;

CREATE INDEX association7_fk
  ON programmation_cours
  USING btree
  (code_matiere COLLATE pg_catalog."default");

-- Index: association8_fk

-- DROP INDEX association8_fk;

CREATE INDEX association8_fk
  ON programmation_cours
  USING btree
  (code_classe COLLATE pg_catalog."default");

-- Index: association9_fk

-- DROP INDEX association9_fk;

CREATE INDEX association9_fk
  ON programmation_cours
  USING btree
  (numero_annee);

