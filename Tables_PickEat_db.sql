CREATE TABLE IF NOT EXISTS categories
(
    categoryId SERIAL NOT NULL,
    categoryName VARCHAR(50) NOT NULL,
    description text,
    image text  NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (categoryId)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS categories
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS comments
(
    commentId SERIAL NOT NULL,
    authorId numeric(9,0) NOT NULL,
    publicationId integer NOT NULL,
    createAt date NOT NULL,
    content text NOT NULL,
    CONSTRAINT comments_pkey PRIMARY KEY (commentId),
    CONSTRAINT comment_author_fk FOREIGN KEY (authorId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT comment_publication_fk FOREIGN KEY (publicationId)
        REFERENCES publications (publicationId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS comments
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS complements
(
    complementId SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT complents_pkey PRIMARY KEY (complementId)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS complements
    OWNER to postgres;

	
CREATE TABLE IF NOT EXISTS eatingSystems
(
    systemId integer NOT NULL,
    designation VARCHAR(50),
    CONSTRAINT eatingSystems_pkey PRIMARY KEY (systemId)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS eatingSystems
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS favorites
(
    userId numeric(9,0) NOT NULL,
    publicationId integer NOT NULL,
    createdAt date NOT NULL,
    CONSTRAINT favorites_pkey PRIMARY KEY (userId, publicationId),
    CONSTRAINT favorite_publication_fk FOREIGN KEY (publicationId)
        REFERENCES publications (publicationId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT favorite_user_fk FOREIGN KEY (userId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS favorites
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS flavors
(
    flavorsId SERIAL NOT NULL,
    desgnation VARCHAR(10) NOT NULL,
    CONSTRAINT flavors_pkey PRIMARY KEY (flavorsId)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS flavors
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS followers
(
    followerId numeric(9,0) NOT NULL,
    followedId numeric(9,0) NOT NULL,
    startAt date NOT NULL,
    CONSTRAINT followers_pkey PRIMARY KEY (followerId,followedId),
    CONSTRAINT followed_user_fk FOREIGN KEY (followedId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT follower_user_fk FOREIGN KEY (followerId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS followers
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS likes
(
    publicationId SERIAL NOT NULL,
    userId numeric(9,0) NOT NULL,
    likedAt date NOT NULL,
    CONSTRAINT likes_pkey PRIMARY KEY (publicationId, userId, likedAt),
    CONSTRAINT like_publication_fk FOREIGN KEY (publicationId)
        REFERENCES publications (publicationId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT like_user_fk FOREIGN KEY (userId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS likes
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS locations
(
    locationId SERIAL NOT NULL ,
    city VARCHAR(100) NOT NULL,
    quater VARCHAR(100)  NOT NULL,
    CONSTRAINT locations_pkey PRIMARY KEY (locationId)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS locations
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS menus
(
    menuId SERIAL NOT NULL,
    menuName VARCHAR(50) NOT NULL,
    description text ,
    categoriId integer NOT NULL,
    CONSTRAINT menus_pkey PRIMARY KEY (menuId),
    CONSTRAINT menu_category_fk FOREIGN KEY (categoriId)
        REFERENCES categories (categoryId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS menus
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS notifications
(
    authorId numeric(9,0) NOT NULL,
    receiverid numeric(9,0) NOT NULL,
    title VARCHAR(200) NOT NULL,
    notificationId SERIAL NOT NULL ,
    message text  NOT NULL,
    CONSTRAINT notifications_pkey PRIMARY KEY (notificationId),
    CONSTRAINT notification_author_fk FOREIGN KEY (authorId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT notification_receiver_fk FOREIGN KEY (receiverid)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS notifications
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS nutrients
(
    nutrientId SERIAL NOT NULL,
    designation VARCHAR(50) NOT NULL,
    CONSTRAINT nutrients_pkey PRIMARY KEY (nutrientId)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS nutrients
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS orders
(
    orderId SERIAL NOT NULL,
    authorId numeric(9,0) NOT NULL,
    receiverId numeric(9,0) NOT NULL,
    publicationId integer NOT NULL,
    status boolean NOT NULL,
    orderDate date NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (orderId),
    CONSTRAINT order_author_fk FOREIGN KEY (authorId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT order_publication_fk FOREIGN KEY (publicationId)
        REFERENCES publications (publicationId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT order_receiver_fk FOREIGN KEY (receiverId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS orders
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS publications
(
    publicationId SERIAL NOT NULL,
    menuId integer NOT NULL,
    authorId numeric(9,0) NOT NULL,
    description text NOT NULL,
    createdAt date NOT NULL,
    images json NOT NULL,
    availableUntil date NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT publications_pkey PRIMARY KEY (publicationId),
    CONSTRAINT publication_menu_fk FOREIGN KEY (menuId)
        REFERENCES menus (menuId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT publication_user_fk FOREIGN KEY (authorId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS publications
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS regions
(
    regionId integer NOT NULL,
    name character varying(50) NOT NULL,
    description text,
    CONSTRAINT regions_pkey PRIMARY KEY (regionId)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS regions
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS stories
(
    authorId numeric(9,0) NOT NULL,
    menuId integer NOT NULL,
    storyId SERIAL NOT NULL,
    createdAt date NOT NULL,
    image text NOT NULL,
    CONSTRAINT stories_pkey PRIMARY KEY (authorId, storyId),
    CONSTRAINT story_menu_fk FOREIGN KEY (menuId)
        REFERENCES menus (menuId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT story_user_fk FOREIGN KEY (authorId)
        REFERENCES users (phoneNumber) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS stories
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS typesOfMeals
(
    typeId SERIAL NOT NULL,
    designation VARCHAR(50) NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS typesOfMeals
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS users
(
    phoneNumber numeric(9,0) NOT NULL,
    name character varying(20) NOT NULL,
    profile text,
    balance double precision NOT NULL,
    locationId integer NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (phoneNumber),
    CONSTRAINT user_location_fk FOREIGN KEY (locationId)
        REFERENCES locations (locationId) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS users
    OWNER to postgres;
