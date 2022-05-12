--Fonction minime pour afichier les user par publication--
CREATE OR REPLACE FUNCTION public.info_author_bypublication(
	"PUBLICATION_ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM
(SELECT info_user("authorId")
 FROM publications
 WHERE "publicationId"="PUBLICATION_ID")list;
$BODY$;

ALTER FUNCTION public.info_author_bypublication(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.info_author_bypublication(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.info_author_bypublication(integer) TO postgres;

COMMENT ON FUNCTION public.info_author_bypublication(integer)
    IS 'Selection de l''information de l''author des publications';
	
	
	
	
--fonction pour afficher les informations d'une categorie--
CREATE OR REPLACE FUNCTION public.info_categorie(
	"CATEGORIE_ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(info_cat)
FROM (SELECT * 
	  FROM categories 
	  WHERE "categoryId"="CATEGORIE_ID"
)info_cat;
$BODY$;

ALTER FUNCTION public.info_categorie(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.info_categorie(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.info_categorie(integer) TO postgres;

COMMENT ON FUNCTION public.info_categorie(integer)
    IS 'Selection des informations complete d''une categorie';
	
	
	
	
--Fonction pour aficher les information d'une cateoriezs par son nom--
CREATE OR REPLACE FUNCTION public.info_categorie_byname(
	"NAME" character varying)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (
	SELECT * 
	  FROM categories 
	  WHERE "categoryName"="NAME"
)list;
$BODY$;

ALTER FUNCTION public.info_categorie_byname(character varying)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.info_categorie_byname(character varying) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.info_categorie_byname(character varying) TO postgres;

COMMENT ON FUNCTION public.info_categorie_byname(character varying)
    IS 'Selection des informations complete d''une categorie par son nom';
	
	
	
	
--Fonction pour les informations d'un menu par son id
CREATE OR REPLACE FUNCTION public.info_menu(
	"MENU_ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (
	  SELECT me."menuId" ,me."menuName",me."description",ca."categoryId",ca."categoryName",
	  ca."description" AS "Categorie description",ca."image"
	  FROM menus me JOIN categories ca
	  ON me."categoriId"=ca."categoryId"
	  WHERE me."menuId"="MENU_ID"
)list;
$BODY$;

ALTER FUNCTION public.info_menu(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.info_menu(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.info_menu(integer) TO postgres;

COMMENT ON FUNCTION public.info_menu(integer)
    IS 'Selection des informations sur un menu';
	
	
	
--Fonction pour pour afficher les informations d'une publication--
CREATE OR REPLACE FUNCTION public.info_publication(
	"ID_PUBLICATION" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(information)
FROM (SELECT pb."publicationId",
	  		 pb."menuId",
	  		 pb."authorId" AS "Creator phone",
	  		 pb."description",
	  		 pb."createdAt",
	  		 pb."availableUntil",
	  		 pb."price",
	  		 pb."images",
	  		 us."phoneNumber" AS "User phone",
	  		 us."name" AS "Name",
	  		 us."profile",
	  		 us."balance" AS "Somme en compte",
	  		 l."locationId",
	  		 l."city",
	  		 l."quater"	 ,
	  	(SELECT info_user(pb."authorId")) AS "info author"
	  	FROM publications pb
		JOIN users us
		ON pb."authorId"=us."phoneNumber"
		JOIN locations l
		ON us."locationId"=l."locationId"
		WHERE pb."publicationId"="ID_PUBLICATION"
	)information;
$BODY$;

ALTER FUNCTION public.info_publication(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.info_publication(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.info_publication(integer) TO postgres;

COMMENT ON FUNCTION public.info_publication(integer)
    IS 'Selection des informations complete d''une publication';
	
	
	
--Fonction pour afficher les informations d'un user--
CREATE OR REPLACE FUNCTION public.info_user(
	"PHONE_NUMBER" numeric)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(infos)
FROM (SELECT us."phoneNumber" AS phone,
	  		 us."name" AS "Name",
	  		 us."profile" AS "Profile",
	  		 us."balance" AS "Money",
	  		 lk."locationId",
	  		 lk."city" AS "City",
	  		 lk."quater" AS "Quater"
	  	FROM users us
		JOIN locations lk
		ON us."locationId"=lk."locationId"		
		WHERE us."phoneNumber"="PHONE_NUMBER"
	)infos;
$BODY$;

ALTER FUNCTION public.info_user(numeric)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.info_user(numeric) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.info_user(numeric) TO postgres;

COMMENT ON FUNCTION public.info_user(numeric)
    IS 'Selection des informations complete d''un user';
	
	
	
--Fonction pour afficher les informations d'un user par son nom--
CREATE OR REPLACE FUNCTION public.info_user_byname(
	"NAME" character varying)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(infos)
FROM (SELECT us."phoneNumber" AS phone,
	  		 us."name" AS "Name",
	  		 us."profile" AS "Profile",
	  		 us."balance" AS "Money",
	  		 lk."locationId",
	  		 lk."city" AS "City",
	  		 lk."quater" AS "Quater"
	  	FROM users us
		JOIN locations lk
		ON us."locationId"=lk."locationId"		
		WHERE us."name"="NAME"
	)infos;
$BODY$;

ALTER FUNCTION public.info_user_byname(character varying)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.info_user_byname(character varying) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.info_user_byname(character varying) TO postgres;

COMMENT ON FUNCTION public.info_user_byname(character varying)
    IS 'Selection des informations complete d''un user grace a son nom';
	
	
	
--Fonction pour afficher les abonné d'un utilisateur--
CREATE OR REPLACE FUNCTION public.list_abonnebyuser(
	"PHONE_NUMBER" numeric)
    RETURNS json
    LANGUAGE 'sql'
    COST 1000
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (SELECT fl."followedId", fl."followerId",
		(SELECT info_user(fl."followedId")) AS "info followed"
		FROM followers fl JOIN users us
		ON fl."followedId"=us."phoneNumber"
		WHERE fl."followerId"="PHONE_NUMBER"
	    ORDER BY fl."followedId"
)list;
$BODY$;

ALTER FUNCTION public.list_abonnebyuser(numeric)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_abonnebyuser(numeric) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_abonnebyuser(numeric) TO postgres;

COMMENT ON FUNCTION public.list_abonnebyuser(numeric)
    IS 'Function pour retourner tout les abonnés d''un utilisateur';
	
	
	
--Fonction pour afficher les abonnement d'un utilisateur--
CREATE OR REPLACE FUNCTION public.list_abonnementbyuser(
	"PHONE_NUMBER" numeric)
    RETURNS json
    LANGUAGE 'sql'
    COST 1000
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (SELECT fl."followerId", fl."followedId",
		(SELECT info_user(fl."followerId")) AS "info follower"
		FROM followers fl JOIN users us
		ON fl."followerId"=us."phoneNumber"
		WHERE fl."followedId"="PHONE_NUMBER"
	    ORDER BY fl."followerId"
)list;
$BODY$;

ALTER FUNCTION public.list_abonnementbyuser(numeric)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_abonnementbyuser(numeric) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_abonnementbyuser(numeric) TO postgres;

COMMENT ON FUNCTION public.list_abonnementbyuser(numeric)
    IS 'Function pour retourner tout les abonnements a un utilisateur';
	
	

--Fonction pour tout les categories--
CREATE OR REPLACE FUNCTION public.list_allcategorie(
	)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT  json_agg(ctg)
FROM (SELECT info_categorie("categoryId")
	 FROM categories)ctg
;
$BODY$;

ALTER FUNCTION public.list_allcategorie()
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_allcategorie() TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_allcategorie() TO postgres;

COMMENT ON FUNCTION public.list_allcategorie()
    IS 'Selection de tout les categories';
	
	
--Fonction pour tout les favories--
CREATE OR REPLACE FUNCTION public.list_allfavorites(
	)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (	SELECT ft."createdAt",
		ft."userId",
	  	(SELECT info_publication(ft."publicationId"))	
		FROM favorites ft
		JOIN publications pb
		ON ft."publicationId"=pb."publicationId"
		JOIN users us
		ON ft."userId"=us."phoneNumber"
		ORDER BY pb."publicationId"
)list;		
$BODY$;

ALTER FUNCTION public.list_allfavorites()
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_allfavorites() TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_allfavorites() TO postgres;

COMMENT ON FUNCTION public.list_allfavorites()
    IS 'Selection des informations de tout les favoris';
	
	
--Fonction pour afficher la table followers--
CREATE OR REPLACE FUNCTION public.list_allfollowers(
	)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (  SELECT info_user("followerId") AS "info_Follower",
	  	(SELECT info_user("followedId")) AS "info_followed"
	    FROM followers
		ORDER BY "followedId"
	 )list;		
$BODY$;

ALTER FUNCTION public.list_allfollowers()
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_allfollowers() TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_allfollowers() TO postgres;

COMMENT ON FUNCTION public.list_allfollowers()
    IS 'Function list all followers';
	
	

--Fonction pour tout les publications--
CREATE OR REPLACE FUNCTION public.list_allpublication(
	)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (
	SELECT info_publication("publicationId")
	FROM publications
)list;		
$BODY$;

ALTER FUNCTION public.list_allpublication()
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_allpublication() TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_allpublication() TO postgres;

COMMENT ON FUNCTION public.list_allpublication()
    IS 'Selection de toutes les publications';
	
	
	
--Fonction pour toutes les stories--
CREATE OR REPLACE FUNCTION public.list_allstories(
	)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(st)
FROM public.stories st
LEFT OUTER JOIN public.users us
ON st."authorId" = us."phoneNumber"
;
$BODY$;

ALTER FUNCTION public.list_allstories()
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_allstories() TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_allstories() TO postgres;

COMMENT ON FUNCTION public.list_allstories()
    IS 'Selection de toute les stories';
	
	

--Fonction pour tout les utilisateurs--
CREATE OR REPLACE FUNCTION public.list_alluser(
	)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (
	SELECT info_user("phoneNumber")
	FROM users	
)list;
$BODY$;

ALTER FUNCTION public.list_alluser()
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_alluser() TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_alluser() TO postgres;

COMMENT ON FUNCTION public.list_alluser()
    IS 'Selection de tout les user';
	
	

--Fonction pour aficher tout les user sau l'utilisateurs courant --
CREATE OR REPLACE FUNCTION public.list_allusers_exceptuser(
	"PHONE_NUMBER" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (  SELECT info_user(us."phoneNumber") AS "info_user" FROM
	    users us JOIN locations l
	    ON us."locationId"=l."locationId"
	    WHERE us."phoneNumber" <> "PHONE_NUMBER"
		ORDER BY us."phoneNumber"
	 )list;		
$BODY$;

ALTER FUNCTION public.list_allusers_exceptuser(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_allusers_exceptuser(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_allusers_exceptuser(integer) TO postgres;

COMMENT ON FUNCTION public.list_allusers_exceptuser(integer)
    IS 'Function pour retourner tout les utilisateurs sauf l''utilisateur courant';
	
	
	
--fonction pour afficher tout les users ayant publier sur un menu--
CREATE OR REPLACE FUNCTION public.list_favorites_byuser(
	"PHONE_NUMBER" numeric)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (  SELECT ft."userId",ft."createdAt",
	  	(SELECT info_user(us."phoneNumber")) AS "info users",
	  	(SELECT info_publication(pb."publicationId")) AS "info publications"
		FROM favorites ft
	  	JOIN publications pb
	  	ON ft."publicationId"=pb."publicationId"
	  	JOIN users us
	  	ON pb."authorId"=us."phoneNumber"
		WHERE us."phoneNumber"= "PHONE_NUMBER"
		ORDER BY pb."publicationId")list;		
$BODY$;

ALTER FUNCTION public.list_favorites_byuser(numeric)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_favorites_byuser(numeric) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_favorites_byuser(numeric) TO postgres;

COMMENT ON FUNCTION public.list_favorites_byuser(numeric)
    IS 'Selection les informations completes des favories en fonction d''un user';
	
	
	
--Fonction pour afficher les menues par categories
CREATE OR REPLACE FUNCTION public.list_menu_bycategories(
	"CATEGORY_ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (  SELECT mn."menuId",
	  	mn."menuName",
	  	mn."description",
		(SELECT info_categorie(mn."categoriId")) AS "infos Category"
		FROM menus mn JOIN categories ct
		ON mn."categoriId"=ct."categoryId"
	  	WHERE mn."categoriId"="CATEGORY_ID"
		ORDER BY mn."menuId")list;		
$BODY$;

ALTER FUNCTION public.list_menu_bycategories(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_menu_bycategories(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_menu_bycategories(integer) TO postgres;

COMMENT ON FUNCTION public.list_menu_bycategories(integer)
    IS 'Selection de tout menus d''une categories';
	
	

--Fonction pour afficher les notiications par user
CREATE OR REPLACE FUNCTION public.list_notification_byuser(
	"PHONE_NUMBER" numeric)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(nf)
FROM public.notifications nf
LEFT OUTER JOIN public.users us
ON nf."authorId" = us."phoneNumber"
WHERE nf.receiverid = "PHONE_NUMBER"
$BODY$;

ALTER FUNCTION public.list_notification_byuser(numeric)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_notification_byuser(numeric) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_notification_byuser(numeric) TO postgres;

COMMENT ON FUNCTION public.list_notification_byuser(numeric)
    IS 'Selection de tout les notifications d''un utiliseur';
	
	

--Fonction pour afficher la publication liké --
CREATE OR REPLACE FUNCTION public.list_publication_liked(
	"ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
	SELECT json_agg(list)
	FROM (SELECT L."publicationId"
		  FROM likes L
		  LEFT JOIN publications pub
		  ON L."publicationId"=pub."publicationId"
		  WHERE L."publicationId"="ID")list;
$BODY$;

ALTER FUNCTION public.list_publication_liked(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_publication_liked(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_publication_liked(integer) TO postgres;

COMMENT ON FUNCTION public.list_publication_liked(integer)
    IS 'Selection des publications liké';
	
	
	
--Fonction pour aficher la liste des publication par menu
CREATE OR REPLACE FUNCTION public.list_publications_bymenu(
	"MENU_ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (
	SELECT info_publication(pu."publicationId")
	FROM publications pu JOIN menus me
	ON pu."menuId"=me."menuId"
	WHERE pu."menuId"="MENU_ID"
)list;
$BODY$;

ALTER FUNCTION public.list_publications_bymenu(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_publications_bymenu(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_publications_bymenu(integer) TO postgres;

COMMENT ON FUNCTION public.list_publications_bymenu(integer)
    IS 'Selection de tout les publications par menu';
	


--Ffonction pour afficher la liste des publications par user--
CREATE OR REPLACE FUNCTION public.list_publications_byuser(
	"PHONE_NUMBER" numeric)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (SELECT l."publicationId",
	    (SELECT json_array_length(list_publication_liked(l."publicationId"))) AS "nb_like",
	    (SELECT info_publication(l."publicationId")) AS "infos"
		FROM likes l JOIN publications pub 
		ON l."publicationId"=pub."publicationId"
		JOIN users u ON pub."authorId"=u."phoneNumber"
		WHERE u."phoneNumber"= "PHONE_NUMBER"
		GROUP BY l."publicationId"
		ORDER BY l."publicationId")list;		
$BODY$;

ALTER FUNCTION public.list_publications_byuser(numeric)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_publications_byuser(numeric) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_publications_byuser(numeric) TO postgres;

COMMENT ON FUNCTION public.list_publications_byuser(numeric)
    IS 'Selection des informations completes des categories d''un menu';
	
	

--Fonction pour la liste des utilisateurs avec ses informations et ceux de categoriepar menu--
CREATE OR REPLACE FUNCTION public.list_user_bymenu(
	)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM (SELECT m."menuId", m."menuName", m.description, c."categoryName", 
(SELECT COUNT(l."publicationId") FROM public.likes l 
RIGHT OUTER JOIN public.publications p ON p."publicationId" = l."publicationId" 
AND p."menuId" = m."menuId") AS likeNumber, c.image,
(SELECT json_agg(users) FROM (SELECT u."phoneNumber", u.name, u.profile FROM public.users u 
JOIN public.publications pu ON pu."authorId" = u."phoneNumber"
WHERE pu."menuId" = m."menuId")users) AS users
FROM public.menus m
LEFT OUTER JOIN public.categories c ON m."categoriId" = c."categoryId")list;
$BODY$;

ALTER FUNCTION public.list_user_bymenu()
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.list_user_bymenu() TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.list_user_bymenu() TO postgres;



--Fonction pour les utilisateurs par publication par menu
CREATE OR REPLACE FUNCTION public.user_bypublication_bymenu(
	"MENU_ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT json_agg(list)
FROM
(SELECT info_author_Bypublication(pu."publicationId")
 FROM publications pu JOIN menus me
 ON pu."menuId"=me."menuId"
 WHERE pu."menuId"="MENU_ID")list;
$BODY$;

ALTER FUNCTION public.user_bypublication_bymenu(integer)
    OWNER TO postgres;

GRANT EXECUTE ON FUNCTION public.user_bypublication_bymenu(integer) TO PUBLIC;

GRANT EXECUTE ON FUNCTION public.user_bypublication_bymenu(integer) TO postgres;
