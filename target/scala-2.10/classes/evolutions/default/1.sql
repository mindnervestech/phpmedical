# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table assistent_register (
  assitent_id               integer auto_increment not null,
  doctor_register_doctor_id integer,
  constraint pk_assistent_register primary key (assitent_id))
;

create table bucket_doctors (
  doctor_id                 integer auto_increment not null,
  name                      varchar(255),
  speciality                varchar(255),
  email                     varchar(255),
  mobile_number             bigint,
  location                  varchar(255),
  user_mail_id              varchar(255),
  patient                   integer,
  constraint pk_bucket_doctors primary key (doctor_id))
;

create table clinic (
  id_clinic                 integer auto_increment not null,
  clinic_name               varchar(255),
  land_line_number          bigint,
  mobile_number             bigint,
  address                   varchar(255),
  location                  varchar(255),
  email                     varchar(255),
  constraint pk_clinic primary key (id_clinic))
;

create table country (
  id_country                integer auto_increment not null,
  coutry_name               varchar(255),
  isdcode                   integer,
  person_id_person          integer,
  id_currency               integer,
  constraint pk_country primary key (id_country))
;

create table country_language (
  country_language_id       integer auto_increment not null,
  country_id_country        integer,
  languages_id_languages    integer,
  constraint pk_country_language primary key (country_language_id))
;

create table currency (
  id_currency               integer auto_increment not null,
  currencycol               varchar(255),
  currency_symbol           varchar(255),
  constraint pk_currency primary key (id_currency))
;

create table custom_template (
  id_customer_template      integer auto_increment not null,
  name                      varchar(255),
  icon                      varchar(255),
  category                  integer,
  type                      integer,
  idPerson                  integer,
  idParentTemplate          integer,
  constraint pk_custom_template primary key (id_customer_template))
;

create table customtamplate_templatefield (
  idCustomTemplate          integer,
  idTemplateField           integer,
  sequence                  integer)
;

create table delagate_task (
  delegate_id               integer auto_increment not null,
  idSource                  integer,
  idTarget                  integer,
  access_type               integer,
  delegate_type             integer,
  url                       varchar(255),
  constraint pk_delagate_task primary key (delegate_id))
;

create table dignostic_lab (
  id_diagnostic_lab         integer auto_increment not null,
  country_id_country        integer,
  lab_name                  varchar(255),
  land_line_number          varchar(255),
  mobile_number             varchar(255),
  address                   varchar(255),
  location                  varchar(255),
  email                     varchar(255),
  constraint pk_dignostic_lab primary key (id_diagnostic_lab))
;

create table doctor_clinic (
  doctor_clinic_id          integer auto_increment not null,
  person_id_person          integer,
  clinic_id_clinic          integer,
  days_of_week              integer,
  time_to_start             datetime,
  time_to_stop              datetime,
  number_of_patients        integer,
  percentage_overbook       integer,
  constraint pk_doctor_clinic primary key (doctor_clinic_id))
;

create table doctor_clinic_schedule (
  id                        bigint auto_increment not null,
  doc_id                    varchar(255),
  clinic_id                 varchar(255),
  day                       varchar(255),
  form                      varchar(255),
  totime                    varchar(255),
  shift                     varchar(255),
  constraint pk_doctor_clinic_schedule primary key (id))
;

create table doctor_register (
  doctor_id                 integer auto_increment not null,
  person_id_person          integer,
  flag                      tinyint(1) default 0,
  speciality                varchar(255),
  constraint pk_doctor_register primary key (doctor_id))
;

create table field (
  id_field                  integer auto_increment not null,
  type                      integer,
  description               varchar(255),
  constraint pk_field primary key (id_field))
;

create table languages (
  id_languages              integer auto_increment not null,
  name                      varchar(255),
  name_in_language          varchar(255),
  person_id_person          integer,
  constraint pk_languages primary key (id_languages))
;

create table messages (
  message_id                integer auto_increment not null,
  idSender                  integer,
  idRecipient               integer,
  massage                   varchar(255),
  date                      datetime,
  is_read                   varchar(255),
  constraint pk_messages primary key (message_id))
;

create table patient_register (
  patient_id                integer auto_increment not null,
  constraint pk_patient_register primary key (patient_id))
;

create table person (
  id_person                 integer auto_increment not null,
  role                      integer,
  name                      varchar(255),
  email_id                  varchar(255),
  password                  varchar(255),
  mobile_number             bigint,
  gender                    varchar(6),
  date_of_birth             datetime,
  location                  varchar(255),
  blood_group               varchar(255),
  allergic_to               varchar(255),
  cloud_type                integer,
  cloud_login_id            varchar(255),
  cloud_login_password      varchar(255),
  patient                   integer,
  assistent_assitent_id     integer,
  doctor                    integer,
  constraint ck_person_gender check (gender in ('Male','Female')),
  constraint pk_person primary key (id_person))
;

create table template_field (
  id_template_field         integer auto_increment not null,
  idField                   integer,
  field_name                varchar(255),
  value                     varchar(255),
  value_query               varchar(255),
  list_query                varchar(255),
  update_query              varchar(255),
  idPerson                  integer,
  constraint pk_template_field primary key (id_template_field))
;


create table dignostic_lab_patient_register (
  dignostic_lab_id_diagnostic_lab integer not null,
  patient_register_patient_id    integer not null,
  constraint pk_dignostic_lab_patient_register primary key (dignostic_lab_id_diagnostic_lab, patient_register_patient_id))
;

create table doctor_register_clinic (
  doctor_register_doctor_id      integer not null,
  clinic_id_clinic               integer not null,
  constraint pk_doctor_register_clinic primary key (doctor_register_doctor_id, clinic_id_clinic))
;

create table patient_register_dignostic_lab (
  patient_register_patient_id    integer not null,
  dignostic_lab_id_diagnostic_lab integer not null,
  constraint pk_patient_register_dignostic_lab primary key (patient_register_patient_id, dignostic_lab_id_diagnostic_lab))
;

create table patient_register_doctor_register (
  patient_register_patient_id    integer not null,
  doctor_register_doctor_id      integer not null,
  constraint pk_patient_register_doctor_register primary key (patient_register_patient_id, doctor_register_doctor_id))
;
alter table assistent_register add constraint fk_assistent_register_doctorRegister_1 foreign key (doctor_register_doctor_id) references doctor_register (doctor_id) on delete restrict on update restrict;
create index ix_assistent_register_doctorRegister_1 on assistent_register (doctor_register_doctor_id);
alter table country add constraint fk_country_person_2 foreign key (person_id_person) references person (id_person) on delete restrict on update restrict;
create index ix_country_person_2 on country (person_id_person);
alter table country add constraint fk_country_currency_3 foreign key (id_currency) references currency (id_currency) on delete restrict on update restrict;
create index ix_country_currency_3 on country (id_currency);
alter table country_language add constraint fk_country_language_country_4 foreign key (country_id_country) references country (id_country) on delete restrict on update restrict;
create index ix_country_language_country_4 on country_language (country_id_country);
alter table country_language add constraint fk_country_language_languages_5 foreign key (languages_id_languages) references languages (id_languages) on delete restrict on update restrict;
create index ix_country_language_languages_5 on country_language (languages_id_languages);
alter table custom_template add constraint fk_custom_template_person_6 foreign key (idPerson) references person (id_person) on delete restrict on update restrict;
create index ix_custom_template_person_6 on custom_template (idPerson);
alter table custom_template add constraint fk_custom_template_customTemplate_7 foreign key (idParentTemplate) references custom_template (id_customer_template) on delete restrict on update restrict;
create index ix_custom_template_customTemplate_7 on custom_template (idParentTemplate);
alter table customtamplate_templatefield add constraint fk_customtamplate_templatefield_customTemplate_8 foreign key (idCustomTemplate) references custom_template (id_customer_template) on delete restrict on update restrict;
create index ix_customtamplate_templatefield_customTemplate_8 on customtamplate_templatefield (idCustomTemplate);
alter table customtamplate_templatefield add constraint fk_customtamplate_templatefield_templateField_9 foreign key (idTemplateField) references template_field (id_template_field) on delete restrict on update restrict;
create index ix_customtamplate_templatefield_templateField_9 on customtamplate_templatefield (idTemplateField);
alter table delagate_task add constraint fk_delagate_task_sperson_10 foreign key (idSource) references person (id_person) on delete restrict on update restrict;
create index ix_delagate_task_sperson_10 on delagate_task (idSource);
alter table delagate_task add constraint fk_delagate_task_tperson_11 foreign key (idTarget) references person (id_person) on delete restrict on update restrict;
create index ix_delagate_task_tperson_11 on delagate_task (idTarget);
alter table dignostic_lab add constraint fk_dignostic_lab_country_12 foreign key (country_id_country) references country (id_country) on delete restrict on update restrict;
create index ix_dignostic_lab_country_12 on dignostic_lab (country_id_country);
alter table doctor_clinic add constraint fk_doctor_clinic_person_13 foreign key (person_id_person) references person (id_person) on delete restrict on update restrict;
create index ix_doctor_clinic_person_13 on doctor_clinic (person_id_person);
alter table doctor_clinic add constraint fk_doctor_clinic_clinic_14 foreign key (clinic_id_clinic) references clinic (id_clinic) on delete restrict on update restrict;
create index ix_doctor_clinic_clinic_14 on doctor_clinic (clinic_id_clinic);
alter table doctor_register add constraint fk_doctor_register_person_15 foreign key (person_id_person) references person (id_person) on delete restrict on update restrict;
create index ix_doctor_register_person_15 on doctor_register (person_id_person);
alter table languages add constraint fk_languages_person_16 foreign key (person_id_person) references person (id_person) on delete restrict on update restrict;
create index ix_languages_person_16 on languages (person_id_person);
alter table messages add constraint fk_messages_sperson_17 foreign key (idSender) references person (id_person) on delete restrict on update restrict;
create index ix_messages_sperson_17 on messages (idSender);
alter table messages add constraint fk_messages_tperson_18 foreign key (idRecipient) references person (id_person) on delete restrict on update restrict;
create index ix_messages_tperson_18 on messages (idRecipient);
alter table person add constraint fk_person_assistent_19 foreign key (assistent_assitent_id) references assistent_register (assitent_id) on delete restrict on update restrict;
create index ix_person_assistent_19 on person (assistent_assitent_id);
alter table template_field add constraint fk_template_field_field_20 foreign key (idField) references field (id_field) on delete restrict on update restrict;
create index ix_template_field_field_20 on template_field (idField);
alter table template_field add constraint fk_template_field_person_21 foreign key (idPerson) references person (id_person) on delete restrict on update restrict;
create index ix_template_field_person_21 on template_field (idPerson);



alter table dignostic_lab_patient_register add constraint fk_dignostic_lab_patient_register_dignostic_lab_01 foreign key (dignostic_lab_id_diagnostic_lab) references dignostic_lab (id_diagnostic_lab) on delete restrict on update restrict;

alter table dignostic_lab_patient_register add constraint fk_dignostic_lab_patient_register_patient_register_02 foreign key (patient_register_patient_id) references patient_register (patient_id) on delete restrict on update restrict;

alter table doctor_register_clinic add constraint fk_doctor_register_clinic_doctor_register_01 foreign key (doctor_register_doctor_id) references doctor_register (doctor_id) on delete restrict on update restrict;

alter table doctor_register_clinic add constraint fk_doctor_register_clinic_clinic_02 foreign key (clinic_id_clinic) references clinic (id_clinic) on delete restrict on update restrict;

alter table patient_register_dignostic_lab add constraint fk_patient_register_dignostic_lab_patient_register_01 foreign key (patient_register_patient_id) references patient_register (patient_id) on delete restrict on update restrict;

alter table patient_register_dignostic_lab add constraint fk_patient_register_dignostic_lab_dignostic_lab_02 foreign key (dignostic_lab_id_diagnostic_lab) references dignostic_lab (id_diagnostic_lab) on delete restrict on update restrict;

alter table patient_register_doctor_register add constraint fk_patient_register_doctor_register_patient_register_01 foreign key (patient_register_patient_id) references patient_register (patient_id) on delete restrict on update restrict;

alter table patient_register_doctor_register add constraint fk_patient_register_doctor_register_doctor_register_02 foreign key (doctor_register_doctor_id) references doctor_register (doctor_id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table assistent_register;

drop table bucket_doctors;

drop table clinic;

drop table country;

drop table country_language;

drop table currency;

drop table custom_template;

drop table customtamplate_templatefield;

drop table delagate_task;

drop table dignostic_lab;

drop table dignostic_lab_patient_register;

drop table doctor_clinic;

drop table doctor_clinic_schedule;

drop table doctor_register;

drop table doctor_register_clinic;

drop table patient_register_doctor_register;

drop table field;

drop table languages;

drop table messages;

drop table patient_register;

drop table patient_register_dignostic_lab;

drop table person;

drop table template_field;

SET FOREIGN_KEY_CHECKS=1;

