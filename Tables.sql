create table User(
	username varchar(30) PRIMARY KEY,
    name varchar(30),
    password varchar(20));
    
create table Recipes(
	rID integer PRIMARY KEY AUTO_INCREMENT,
    name varchar(30),
    description varchar(100),
    instructions varchar(1000),
    calories integer,
    image varchar(30),
    owner varchar(30),
    foreign key (owner) references User(username) on delete cascade on update cascade);
    
create table Categories(
	category varchar(50) PRIMARY KEY);
    
create table Ingredients(
	name varchar(30) PRIMARY KEY,
    price integer);
    
create table RecipeIngredients(
	rID integer,
    ingredient varchar(30),
    primary key(rId, ingredient),
    foreign key (rId) references Recipes(rID) on delete cascade on update cascade,
    foreign key (ingredient) references Ingredients(name) on delete cascade on update cascade);
    
create table RecipeCategories(
	rID integer,
    category varchar(50),
    primary key (rID, category),
    foreign key (rID) references Recipes(rID) on delete cascade on update cascade,
    foreign key (category) references Categories(category) on delete cascade on update cascade);