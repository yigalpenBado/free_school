/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     27/12/2017 10:54:05                          */
/*==============================================================*/



/*==============================================================*/
/* Table: annee_scolaire                                         */
/*==============================================================*/
create table annee_scolaire (
   numero_annee          integer              not null,
   date_debut            DATE                 null,
   date_fin              DATE                 null,
   constraint PK_ANNEESCOLAIRE primary key (numero_annee)
);

/*==============================================================*/
/* Index: ANNEESCOLAIRE_PK                                      */
/*==============================================================*/
create unique index ANNEESCOLAIRE_PK on annee_scolaire (
numero_annee
);

/*==============================================================*/
/* Table: classe                                                */
/*==============================================================*/
create table classe (
   code_classe           VARCHAR(5)         not null,
   libelle_classe        VARCHAR(30)         null,
   constraint PK_CLASSE primary key (code_classe)
);

/*==============================================================*/
/* Index: CLASSE_PK                                             */
/*==============================================================*/
create unique index CLASSE_PK on classe (
code_classe
);

/*==============================================================*/
/* Table: eleve                                                 */
/*==============================================================*/
create table eleve (
   matricule            VARCHAR(10)         not null,
   nom                  VARCHAR(100)         null,
   prenom               VARCHAR(100)         null,
   date_naissance        DATE                 null,
   sexe                 VARCHAR(100)         null,
   lieu_naissance        VARCHAR(100)         null,
   contact              VARCHAR(15)         null,
   constraint PK_ELEVE primary key (matricule),
   constraint AK_PK_ELEVE_ELEVE unique (matricule)
);

/*==============================================================*/
/* Index: ELEVE_PK                                              */
/*==============================================================*/
create unique index ELEVE_PK on eleve (
matricule
);

/*==============================================================*/
/* Table: evaluation                                            */
/*==============================================================*/
create table evaluation (
   code_matiere          VARCHAR(5)         not null,
   matricule            VARCHAR(10)         not null,
   num_session           integer                 not null,
   note                 real               null,
   date_evaluation       DATE                 null,
   constraint PK_EVALUATION primary key (code_matiere, matricule, num_session)
);

/*==============================================================*/
/* Index: EVALUATION_PK                                         */
/*==============================================================*/
create unique index EVALUATION_PK on evaluation (
code_matiere,
matricule,
num_session
);

/*==============================================================*/
/* Index: ASSOCIATION4_FK                                       */
/*==============================================================*/
create  index ASSOCIATION4_FK on evaluation (
code_matiere
);

/*==============================================================*/
/* Index: ASSOCIATION4_FK2                                      */
/*==============================================================*/
create  index ASSOCIATION4_FK2 on evaluation (
matricule
);

/*==============================================================*/
/* Index: ASSOCIATION5_FK                                       */
/*==============================================================*/
create  index ASSOCIATION5_FK on evaluation (
num_session
);

/*==============================================================*/
/* Table: inscription                                           */
/*==============================================================*/
create table inscription (
   matricule            VARCHAR(10)         not null,
   code_classe           VARCHAR(5)         not null,
   numero_annee          integer                 not null,
   date_inscription      DATE                 null,
   numero_inscription    VARCHAR(100)         null,
   constraint PK_INSCRIPTION primary key (matricule, code_classe, numero_annee)
);

/*==============================================================*/
/* Index: INSCRIPTION_PK                                        */
/*==============================================================*/
create unique index INSCRIPTION_PK on inscription (
matricule,
code_classe,
numero_annee
);

/*==============================================================*/
/* Index: ASSOCIATION1_FK                                       */
/*==============================================================*/
create  index ASSOCIATION1_FK on inscription (
matricule
);

/*==============================================================*/
/* Index: ASSOCIATION1_FK2                                      */
/*==============================================================*/
create  index ASSOCIATION1_FK2 on inscription (
code_classe
);

/*==============================================================*/
/* Index: ASSOCIATION2_FK                                       */
/*==============================================================*/
create  index ASSOCIATION2_FK on inscription (
numero_annee
);

/*==============================================================*/
/* Table: Matiere                                               */
/*==============================================================*/
create table matiere (
   code_matiere          VARCHAR(100)         not null,
   intitule_matiere      VARCHAR(100)         null,
   constraint PK_MATIERE primary key (code_matiere),
   constraint AK_PK_MATIERE_MATIERE unique (code_matiere)
);

/*==============================================================*/
/* Index: MATIERE_PK                                            */
/*==============================================================*/
create unique index MATIERE_PK on matiere (
code_matiere
);

/*==============================================================*/
/* Table: MatiereClasse                                         */
/*==============================================================*/
create table matiere_classe (
   code_matiere          VARCHAR(100)         not null,
   code_classe           VARCHAR(100)         not null,
   coefficient          integer                 null,
   constraint PK_MATIERECLASSE primary key (code_matiere, code_classe)
);

/*==============================================================*/
/* Index: MATIERECLASSE_PK                                      */
/*==============================================================*/
create unique index MATIERECLASSE_PK on matiere_classe (
code_matiere,
code_classe
);

/*==============================================================*/
/* Index: ASSOCIATION6_FK                                       */
/*==============================================================*/
create  index ASSOCIATION6_FK on matiere_classe (
code_matiere
);

/*==============================================================*/
/* Index: ASSOCIATION6_FK2                                      */
/*==============================================================*/
create  index ASSOCIATION6_FK2 on matiere_classe (
code_classe
);

/*==============================================================*/
/* Table: session_annee                                            */
/*==============================================================*/
create table session_annee (
   num_session           integer                 not null,
   numero_annee           integer                 not null,
   libelle_session       VARCHAR(100)         null,
   constraint PK_SESSION primary key (num_session)
);

/*==============================================================*/
/* Index: SESSION_PK                                            */
/*==============================================================*/
create unique index SESSION_PK on session_annee (
num_session
);

/*==============================================================*/
/* Index: ASSOCIATION3_FK                                       */
/*==============================================================*/
create  index ASSOCIATION3_FK on session_annee (
numero_annee
);

alter table evaluation
   add constraint FK_EVALUATI_ASSOCIATI_ELEVE foreign key (matricule)
      references eleve (matricule)
      on delete restrict on update restrict;

alter table evaluation
   add constraint FK_EVALUATI_ASSOCIATI_MATIERE foreign key (code_matiere)
      references matiere (code_matiere)
      on delete restrict on update restrict;

alter table evaluation
   add constraint FK_EVALUATI_ASSOCIATI_SESSION foreign key (num_session)
      references session_annee (num_session)
      on delete restrict on update restrict;

alter table inscription
   add constraint FK_INSCRIPT_ASSOCIATI_CLASSE foreign key (code_classe)
      references classe (code_classe)
      on delete restrict on update restrict;

alter table inscription
   add constraint FK_INSCRIPT_ASSOCIATI_ELEVE foreign key (matricule)
      references eleve (matricule)
      on delete restrict on update restrict;

alter table inscription
   add constraint FK_INSCRIPT_ASSOCIATI_ANNEESCO foreign key (numero_annee)
      references annee_scolaire (numero_annee)
      on delete restrict on update restrict;

alter table matiere_classe
   add constraint FK_MATIEREC_ASSOCIATI_CLASSE foreign key (code_classe)
      references classe (code_classe)
      on delete restrict on update restrict;

alter table matiere_classe
   add constraint FK_MATIEREC_ASSOCIATI_MATIERE foreign key (code_matiere)
      references matiere (code_matiere)
      on delete restrict on update restrict;

alter table session_annee
   add constraint FK_SESSION_ASSOCIATI_ANNEESCO foreign key (numero_annee)
      references annee_scolaire (numero_annee)
      on delete restrict on update restrict;

