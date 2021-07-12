create table if not exists animals (
    id bigserial primary key,
    name varchar(50) not null,
    dateOfBirth date not null,
    sex varchar(10) not null,
    rank varchar(10),
    home boolean,
    animal_type varchar(50)
);

create table if not exists users_animals (
    userId bigint not null,
    animalId bigint not null,
    primary key (userId, animalId),
    foreign key (userId) references users(id),
    foreign key (animalId) references animals(id)
);