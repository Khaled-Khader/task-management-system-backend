package com.globitel.task.management.system.core.token.jpa;

import com.globitel.task.management.system.core.token.entity.TokenEntity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {


    @Modifying
    @Transactional
    @Query(value = """
    UPDATE tokens t
    JOIN (
        SELECT uuid
        FROM (
            SELECT
                uuid,
                ROW_NUMBER() OVER (
                    PARTITION BY user_uuid
                    ORDER BY created_at DESC
                ) AS rn
            FROM tokens
        ) ranked
        WHERE ranked.rn > :range
    ) old_tokens
    ON t.uuid = old_tokens.uuid
    SET t.token_status = 'DENIED'
    """, nativeQuery = true)
    void denyOldTokensPerUser(@Param("range") int range);

    @Modifying
    @Transactional
   @Query(value = """

update TokenEntity t
set t.tokenStatus='DENIED'
where t.user=:user
        """
)
    void denyAllTokensForUser(@Param("user") UserEntity userEntity);


    int deleteByUser(UserEntity user);
}
