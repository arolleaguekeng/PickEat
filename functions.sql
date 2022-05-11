--NB: Toutes ses fonctions retournent des object json--
--Fonction d'affichage des informations sur la categories--

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

COMMENT ON FUNCTION public.info_categorie(integer)
    IS 'Selection des informations complete d''une categorie';
	
--Fonction d'afffichage des informations sur la publication--

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
	  		 l."quater"	  	 
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

COMMENT ON FUNCTION public.info_publication(integer)
    IS 'Selection des informations complete d''une publication';

--Ffonction d'affichage des informations sur le user--

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

COMMENT ON FUNCTION public.info_user(numeric)
    IS 'Selection des informations complete d''un user';
	
--Fonction d'affichage de la liste des abonné a un utilisateur--

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

COMMENT ON FUNCTION public.list_abonnebyuser(numeric)
    IS 'Function pour retourner tout les abonnés d''un utilisateur';
	
--Liste des abonnements d'un utilisateur--

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

COMMENT ON FUNCTION public.list_abonnementbyuser(numeric)
    IS 'Function pour retourner tout les abonnements a un utilisateur';
	
--Liste de tout les categories--

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

COMMENT ON FUNCTION public.list_allcategorie()
    IS 'Selection de tout les categories';
	
--Liste de tout les favoris--

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

COMMENT ON FUNCTION public.list_allfavorites()
    IS 'Selection des informations de tout les favoris';
	
--Liste de tout les followers--

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

COMMENT ON FUNCTION public.list_allfollowers()
    IS 'Function list all followers';
	
--Lilste de tout les publications--

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

COMMENT ON FUNCTION public.list_allpublication()
    IS 'Selection de toutes les publications';
	
--Liste de tout les stories--

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

COMMENT ON FUNCTION public.list_allstories()
    IS 'Selection de toute les stories';
	
--Liste des toutb les utilisateurs sauf l'utilisateur courant--

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

COMMENT ON FUNCTION public.list_allusers_exceptuser(integer)
    IS 'Function pour retourner tout les utilisateurs sauf l''utilisateur courant';
	
--Liste de tout les publications favorites du user--

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

COMMENT ON FUNCTION public.list_favorites_byuser(numeric)
    IS 'Selection les informations completes des favories en fonction d''un user';
	
--Liste de tout les menu par categories--

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

COMMENT ON FUNCTION public.list_menu_bycategories(integer)
    IS 'Selection de tout menus d''une categories';
	
--Liste de tout les notifications par utilisateur--

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

COMMENT ON FUNCTION public.list_notification_byuser(numeric)
    IS 'Selection de tout les notifications d''un utiliseur';
	
--Id de tout les publications liké

CREATE OR REPLACE FUNCTION public.list_publication_liked(
	"ID" integer)
    RETURNS json
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
	SELECT json_agg(L."publicationId")
	FROM likes L
	JOIN publications pub
	ON L."publicationId"=pub."publicationId"
	WHERE L."publicationId"="ID";
$BODY$;

ALTER FUNCTION public.list_publication_liked(integer)
    OWNER TO postgres;

COMMENT ON FUNCTION public.list_publication_liked(integer)
    IS 'Selection des publications liké';
	
--Liste de tout  les publications d'un user( tout les informations concernant)

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

COMMENT ON FUNCTION public.list_publications_byuser(numeric)
    IS 'Selection des informations completes des categories d''un menu';