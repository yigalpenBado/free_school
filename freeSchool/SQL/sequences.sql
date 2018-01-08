/* classe -> code_classe=sequence(1); matiere -> code_matiere=sequence(2)
profil -> code_profil=sequence(3); programmation_cours -> num_program=sequence(4);
 session_annee -> num_session=sequence(5)
utilisateur -> code_user=sequence(6)*/

/****** Classe  ***********/
 create sequence seqCodeClasse start with 1 increment by 1;

ALTER SEQUENCE seqCodeClasse OWNED BY classe.code_classe;

ALTER TABLE classe ALTER COLUMN code_classe 
SET DEFAULT nextval('seqCodeClasse'::regclass);

/****** matiere  ***********/
 create sequence seqCodeMatiere start with 1 increment by 1;

ALTER SEQUENCE seqCodeMatiere OWNED BY matiere.code_matiere;

ALTER TABLE matiere ALTER COLUMN code_matiere 
SET DEFAULT nextval('seqCodeMatiere'::regclass);

/****** profil  ***********/
 create sequence seqCodeProfil start with 1 increment by 1;

ALTER SEQUENCE seqCodeProfil OWNED BY profil.code_profil;

ALTER TABLE profil ALTER COLUMN code_profil 
SET DEFAULT nextval('seqCodeProfil'::regclass);

/****** session-annee  ***********/
 create sequence seqNumSession start with 1 increment by 1;

ALTER SEQUENCE seqNumSession OWNED BY session_annee.num_session;

ALTER TABLE session_annee ALTER COLUMN num_session 
SET DEFAULT nextval('seqNumSession'::regclass);

/****** programmation_cours  ***********/
 create sequence seqNumProgram start with 1 increment by 1;

ALTER SEQUENCE seqNumProgram OWNED BY programmation_cours.num_program;

ALTER TABLE programmation_cours ALTER COLUMN num_program 
SET DEFAULT nextval('programmation_cours'::regclass);

/****** utilisateur  ***********/
 create sequence seqcodeUser start with 1 increment by 1;

ALTER SEQUENCE seqcodeUser OWNED BY utilisateur.code_user;

ALTER TABLE utilisateur ALTER COLUMN code_user 
SET DEFAULT nextval('seqcodeUser'::regclass);

/*annee_scolaire -> numero_annee=identifiant concatené; 
eleve -> matricule=identifiant concatené;
enseignant -> matricule=identifiant concatené;*/