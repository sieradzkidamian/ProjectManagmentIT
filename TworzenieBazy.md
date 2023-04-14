```
CREATE TABLE steps (
	"id" serial NOT NULL,
	"step_number" integer NOT NULL,
	"name" varchar(256) NOT NULL,
	"description" varchar(1024) NOT NULL,
	"image" bytea NOT NULL,
	"course_id" integer NOT NULL,
	PRIMARY KEY (id));
```

```
CREATE TABLE courses ( 
	"id" serial NOT NULL,
	"name" VARCHAR(64) NOT NULL, 
	"description" varchar(1024) NOT NULL,
	"image" bytea NOT NULL,
	PRIMARY KEY (id));
```

```
CREATE TABLE roles (
	"id" serial NOT NULL,
	"name" varchar(256) NOT NULL,
	"description" varchar(1024) NOT NULL,
	PRIMARY KEY (id));
```

```
CREATE TABLE users ( 
	"id" serial NOT NULL, 
	"email" VARCHAR(256) NOT NULL,
	"login" VARCHAR(64) NOT NULL, 
	"password" TEXT NOT NULL, 
	"role_id" INTEGER NOT NULL,
	PRIMARY KEY (id));
```

```
CREATE TABLE users_data ( 
	"id" serial NOT NULL, 
	"date" date NOT NULL,
	"height_cm" integer NOT NULL,
	"weight_g" integer NOT NULL,
	"user_id" integer NOT NULL, 
	PRIMARY KEY (id));
```

```
CREATE TABLE assignments ( 
	"id" serial NOT NULL, 
	"cours_id" integer NOT NULL,
	"user_id" integer NOT NULL,
	PRIMARY KEY (id));
```
