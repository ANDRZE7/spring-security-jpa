-- merge users (insert them if not exist)
MERGE INTO USERS AS T USING (SELECT 'admin' AS NAME, 'admin@noemail.com' AS EMAIL
                             UNION ALL
                             SELECT 'user' AS NAME, 'user@noimail.com' AS EMAIL) AS S
ON T.name = S.name
WHEN NOT MATCHED THEN
    INSERT (name, first_name, last_name, password, email, is_active)
    VALUES  (S.NAME, S.NAME, S.NAME, S.NAME, S.EMAIL, TRUE);

-- merge roles, insert when not exits
MERGE INTO ROLES AS T USING
    (SELECT 'ROLE_ADMIN' AS name
     UNION ALL
     SELECT 'ROLE_DB_ADMIN' AS name
     UNION ALL
     SELECT 'ROLE_USER' AS NAME) AS S
ON T.name = S.name
WHEN NOT MATCHED THEN
    INSERT (name) VALUES(S.name);

-- merge user_roles (many to many relation between users and roles tables)
MERGE INTO USER_ROLES AS T USING
    (SELECT U.id AS user_id, R.id AS role_id FROM USERS U
     INNER JOIN ROLES R
     ON ( U.NAME = 'admin' AND R.NAME ='ROLE_ADMIN' )
     OR ( U.NAME = 'admin' AND R.NAME ='ROLE_DB_ADMIN' )
     OR ( U.NAME = 'user' AND R.NAME ='ROLE_USER' )) AS S
ON T.USER_ID = S.user_id AND T.ROLE_ID = S.role_id
WHEN NOT MATCHED THEN
    INSERT (USER_ID, ROLE_ID)
    VALUES (S.user_id, S.role_id);
