create table Konoha;
use Konoha;

create table Ninja(
	id int primary key auto_increment,
    nombre Varchar(80) not null,
    rango varchar(80),
    aldea varchar(50)
);

create table Mision(
	id int primary key auto_increment,
    descripcion varchar(150),
    rango varchar(80),
    recompensa varchar(200)
);

create table MisionNinja(
	id_ninja int,
    id_mision int,
    fecha_inicio date,
    fecha_fin date,
    foreign key (id_ninja) references Ninja(id),
    foreign key (id_mision) references Mision(id),
    primary key (id_ninja, id_mision)
);

create table Habilidad (
	id int primary key auto_increment,
    id_ninja int,
    foreign key (id_ninja) references Ninja(id),
    nombre varchar(100),
    descripcion varchar(150)
);