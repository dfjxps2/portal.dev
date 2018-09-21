DROP PROCEDURE IF EXISTS UPDATE_USER_ROLE;
CREATE PROCEDURE UPDATE_USER_ROLE (
  IN P_ROLE_ID INT
	, IN P_ROLE_NAME VARCHAR(40)
	, IN P_ROLE_STATE	INT
	, IN P_ROLE_TYPE_ID	INT
	, IN P_MEMBER_COND	VARCHAR(21840)
  , OUT P_ERROR_NO INT
)
BEGIN

	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET P_ERROR_NO=0;
	SET P_ERROR_NO = 1;

	START TRANSACTION;

	UPDATE USER_ROLE SET
    		  ROLE_NAME = P_ROLE_NAME,
    		  ROLE_STATE = P_ROLE_STATE,
    		  ROLE_TYPE_ID = P_ROLE_TYPE_ID,
    		  UPD_TIME = NOW()
  WHERE ROLE_ID = P_ROLE_ID;

	SET @TMP_ROLE_ID = P_ROLE_ID * (-1);

	SET @SQLTEXT = CONCAT('INSERT INTO USER_ROLE_RELA (USER_ID, ROLE_ID, UPD_TIME, CRE_TIME) '
								'SELECT USER_ID, ?, NOW(), NOW() '
								'FROM '
								'	(SELECT DISTINCT U.USER_ID'
								'		FROM SYS_USER U'
								'		JOIN USER_ROLE_RELA UR ON U.USER_ID = UR.USER_ID'
								'		JOIN USER_ROLE R ON UR.ROLE_ID = R.ROLE_ID'
								'		WHERE ', P_MEMBER_COND, ') T');

	PREPARE STMT FROM @SQLTEXT;
	EXECUTE STMT USING @TMP_ROLE_ID;
	DEALLOCATE PREPARE STMT;

	DELETE FROM USER_ROLE_RELA WHERE ROLE_ID = P_ROLE_ID;
	UPDATE USER_ROLE_RELA SET ROLE_ID = -ROLE_ID WHERE ROLE_ID = @TMP_ROLE_ID;


	IF P_ERROR_NO = 0 THEN
		ROLLBACK;
	ELSE
		COMMIT;
	END IF;

END;