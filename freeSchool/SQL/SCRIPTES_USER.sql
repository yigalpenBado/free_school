/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     28/12/2017 09:28:54                          */
/*==============================================================*/




/*==============================================================*/
/* Table: Profil                                                */
/*==============================================================*/
create table Profil (
   code_profil          INT4                 not null,
   libelle_profil       VARCHAR(254)         null,
   constraint PK_PROFIL primary key (code_profil)
);

/*==============================================================*/
/* Index: PROFILE_PK                                            */
/*==============================================================*/
create unique index PROFILE_PK on Profil (
code_profil
);

/*==============================================================*/
/* Table: Utilisateur                                           */
/*==============================================================*/
create table Utilisateur (
   code_user            INT4                 not null,
   login                VARCHAR(254)         null,
   nom                  VARCHAR(254)         null,
   prenom               VARCHAR(254)         null,
   psswrd               VARCHAR(254)         null,
   telephone            VARCHAR(254)         null,
   mail                 VARCHAR(254)         null,
   dateCreation         DATE                 null,
   constraint PK_UTILISATEUR primary key (code_user),
   constraint AK_USER_PK_UTILISAT unique (code_user)
);

/*==============================================================*/
/* Table: user_profil                                           */
/*==============================================================*/
create table user_profil (
   code_user            INT4                 not null,
   code_profil          INT4                 not null,
   date_attribution     DATE                 null,
   constraint PK_USER_PROFIL primary key (code_user, code_profil)
);

/*==============================================================*/
/* Index: USERPROFILE_PK                                        */
/*==============================================================*/
create unique index USERPROFILE_PK on user_profil (
code_user,
code_profil
);

/*==============================================================*/
/* Index: USERPROFI_FK                                          */
/*==============================================================*/
create  index USERPROFI_FK on user_profil (
code_user
);

alter table user_profil
   add constraint FK_USER_PRO_USERPROFI_PROFIL foreign key (code_profil)
      references Profil (code_profil)
      on delete restrict on update restrict;

alter table user_profil
   add constraint FK_USER_PRO_USERPROFI_UTILISAT foreign key (code_user)
      references Utilisateur (code_user)
      on delete restrict on update restrict;

