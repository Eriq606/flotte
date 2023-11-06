create table kilometrages(
    id_kilometrage serial primary key,
    id_voiture_kilometrage int,
    date_kilometrage timestamp not null,
    debut_kilometrage decimal not null,
    fin_kilometrage decimal not null,
    foreign key(id_voiture_kilometrage)
        references voitures(id_voiture)
);
create table users(
    id_user serial primary key,
    nom_user varchar(255) not null,
    mdp_user varchar(255) not null
);
create table tokens(
    id_token serial primary key,
    id_user_token int,
    value_token varchar(255) not null,
    expiration_token timestamp not null,
    foreign key(id_user_token)
        references users(id_user)
);

create view v_voiture_marques as
    select *
    from voitures
        join marques on voitures.id_marque_voiture=marques.id_marque;

create or replace view v_kilometrage_voitures as
    select *
    from kilometrages
        join v_voiture_marques on kilometrages.id_voiture_kilometrage=v_voiture_marques.id_voiture;

create or replace view v_valid_tokens as
    select *
    from tokens
    where expiration_token>current_timestamp;