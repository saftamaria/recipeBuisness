create table category(
category_id int auto_increment PRIMARY KEY,
description varchar(100)
);

create table ingredient(
ingredient_id int auto_increment PRIMARY KEY,
description varchar(100),
amount decimal,
uom_id int,
fk_ingredient_unit_of_measurement int UNIQUE FOREIGN KEY REFERENCES unit_of_measurement(unit_of_measurement_id)
);

create table notes(
notes_id int auto_increment PRIMARY KEY,
recipe_notes varchar(100),
fk_notes_recipe int UNIQUE FOREIGN KEY REFERENCES recipe(recipe_id)
);

--create table notes_recipe(
--notes_id int,
--recipe_id int,
--PRIMARY KEY (notes_id, recipe_id),
--CONSTRAINT fk_notes_recipe_notes FOREIGN KEY (notes_id) REFERENCES notes(notes_id),
--CONSTRAINT fk_notes_recipe_recipe FOREIGN KEY (recipe_id) REFERENCES notes(recipe_id)
--)

create table recipe(
recipe_id int auto_increment PRIMARY KEY,
description varchar(300),
preparation_time int,
cooking_time int,
servings int,
source varchar(400),
url varchar(40),
directions varchar(1000),
ingredient_id int,
CONSTRAINT fk_recipe_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id),
image LONGBLOB,
difficulty ENUM('beginner', 'medium', 'advanced')
);

create table recipe_categories(
recipe_id int,
category_id int,
PRIMARY KEY(recipe_id, category_id),
CONSTRAINT fk_recipe_categories_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
CONSTRAINT fk_recipe_categories_category FOREIGN KEY (category_id) REFERENCES category(category_id)
);

create table unit_of_measurement(
unit_of_measurement_id int auto_increment PRIMARY KEY,
description varchar(50)
)