package com.erichgamma.api.player.repository;

import com.erichgamma.api.player.model.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlayerJpqlRepository   {
    @Query("SELECT DISTINCT new map(p.position AS position) FROM player p")
    List<Map<String, Object>> getOnPosition();

    @Query("SELECT DISTINCT new map (IFNULL(NULLIF(p.position, ''), '신입') AS 포지션) FROM player p ")
    List<Map<String, Object>> getOnPositionNotNull();

    @Query("SELECT new map (p.teamId.teamId AS teamId , p.position AS position ) FROM player p where p.position = 'GK' AND p.teamId.teamId = 'k02'")
    List<Map<String ,Object>> getOnPositionAndTeamId();

//    @Query("SELECT new map (p.teamId.teamId as PteamId , t.teamId as teamId , t.regionName AS regionName, p.position AS position) FROM player p JOIN team t on t.teamId = p.teamId.teamId\n" +
//            "                          where p.position = 'GK' AND t.regionName = '수원'")
    @Query("SELECT new map(p.playerName AS 이름,\n" +
            "       IFNULL(NULLIF(p.height, ''), '0') AS 키,\n" +
            "       IFNULL(NULLIF(p.weight, ''), '0') AS 몸무게) FROM player p WHERE p.teamId.teamId =\n" +
            "                                                              (SELECT t.teamId FROM team t WHERE t.regionName = '서울')")
    List<Map<String ,Object>> getOnPositionAndTeamId7();

    @Query("SELECT new map (p.playerName AS playerName,\n" +
            "       IFNULL(NULLIF(p.height, ''), '0') AS 키,\n" +
            "       IFNULL(NULLIF(p.weight, ''), '0') AS 몸무게 " +
            " ,CASE WHEN (p.height = '') OR p.weight = '' THEN 'NONE'\n" +
            "           ELSE CAST(round(CAST(p.weight AS DOUBLE) / CAST(POW(CAST(p.height AS DOUBLE) / 100.0, 2) AS DOUBLE), 2) AS string) \n" +
            "           END AS BMI) " +
            "FROM player p JOIN team t ON t.teamId = p.teamId.teamId \n" +
            "WHERE t.regionName = '서울'")
    List<Map<String, Object>> getOnHeightAndWeight();

    @Query("SELECT new map (p.position AS position , p.teamId.teamId AS teamId, p.playerName AS playerName )FROM player p\n" +
            "WHERE p.position = 'GK'\n" +
            "AND p.teamId.teamId IN ('K02', 'K10')\n" +
            "ORDER BY\n" +
            "    (SELECT t.teamName\n" +
            "     FROM team t\n" +
            "     WHERE t.teamId = p.teamId.teamId),\n" +
            "    p.playerName")
    List<Map<String , Object>> getOnPositionAndTeamId10();

    @Query("SELECT new map(p.teamId.teamId as teamId , p.playerName as playerName ," +
            " p.position as position, p.name  as name, p.backNo as backNo, p.nickname as nickname," +
            " p.solar , p.ePlayerName) FROM player p ")
    List<Map<String , Object>> getOnCountAll(Pageable pageable);

    @Query("SELECT new MAP ((SELECT t.teamName \n" +
            "        FROM team t\n" +
            "        WHERE t.teamId = p.teamId.teamId) AS 소속팀,\n" +
            "    p.playerName AS 선수명,\n" +
            "    p.backNo AS 등번호\n" +
            ")FROM player p\n" +
            "WHERE p.position = 'MF'")
    List<Map<String , Object>> getOnPositionAndTeamId20();



    @Query("SELECT new MAP ((SELECT t.teamName \n" +
            "        FROM team t\n" +
            "        WHERE t.teamId = p.teamId.teamId) as 소속팀,\n" +
            "    p.playerName,\n" +
            "    p.backNo\n" +
            ")FROM player p\n" +
            "ORDER BY p.height\n" )
    List<Map<String , Object>> getOnPositionAndTeamId21(Pageable pageable);

    @Query(value = "SELECT new map( p.position AS position ,p.teamId.teamId as teamId  ) FROM player p WHERE p.position = 'GK'\n" +
            "                       AND p.teamId.teamId =\n" +
            "                           (SELECT t.teamId from team t where t.regionName = '수원')")
    List<Map<String , Object>> getOnPositionAndTeamId2();

    @Query("SELECT new map (p.teamId.teamId as teamId , p.height as height , p.playerName as playerName) FROM player p\n" +
            "WHERE p.height != ''     \n"  +
            "  AND p.playerName LIKE '고%'\n" +
            "  AND p.teamId.teamId =\n" +
            "      (SELECT t.teamId FROM team t WHERE t.regionName = '수원')")
    List<Map<String , Object>> getOnPositionAndTeamIdAndHeight();

    @Query("SELECT new map (p.teamId.teamId as teamId, p.height as height , p.playerName asplayerName ) FROM player p JOIN team t on t.teamId = p.teamId.teamId\n" +
            "         WHERE p.height != '' \n" +
            "           AND p.playerName LIKE '고%'\n" +
            "           AND t.regionName= '수원'")
    List<Map<String , Object>> getOnPositionAndTeamIdAndHeight2();

    @Query("SELECT new map (p.playerName as playerName,p.position as position,p.teamId.teamId as teamId, p.height as height ) FROM player p WHERE p.position = 'MF'\n" +
            "                              AND p.height != '' \n " +
            "                              AND p.teamId.teamId IN\n" +
            "                                  (SELECT t.teamId from team t WHERE t.teamName IN ('삼성블루윙즈', '드래곤즈'))")
    List<Map<String , Object>> getOnPositionAndHeightAndTeamId();

    @Query("SELECT new map (p.position AS position, p.teamId.teamId as teamId )FROM player p WHERE p.position = 'GK'\n" +
            "                       AND p.teamId.teamId =\n" +
            "                           (SELECT t.teamId FROM team t WHERE t.regionName = '수원')")
    List<Map<String, Object>> getPositionAndeRegion();



//    @Query("SELECT new map(p)\n" +
//            "FROM player p\n" +
//            "JOIN (SELECT p2.team_id, ROUND(AVG(CAST((p2.height), 2)))avg\n" +
//            "      FROM player p2\n" +
//            "      where p2.height != ''\n" +
//            "      GROUP BY p2.team_id) tavg USING (team_id)\n" +
//            "WHERE p.height != ''\n" +
//            "  AND p.height < tavg.avg;")
    @Query(value = "SELECT new  map (p.height AS height , p.teamId.teamId AS teamId ," +
            "p.ePlayerName AS ePlayerName,p.playerId AS playerId)\n" +
            "FROM player p\n" +
            "WHERE cast(p.height AS double) < cast( (SELECT  ROUND(AVG(cast( tp.height AS double )), 2)\n" +
            "                  FROM player tp\n" +
            "                  WHERE p.teamId.teamId = tp.teamId.teamId) AS double )")
    List<Map<String, Object>> getHeightAndTeamId();


}
