package com.globitel.task.management.system.core.token.dao;

import com.globitel.task.management.system.core.shared.dao.Dao;
import com.globitel.task.management.system.core.token.identity.TokenIdentity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

public interface TokenDao extends Dao<TokenIdentity, TokenCreator, TokenUpdater> {
    void updateOutOfRangeTokens();
    void deniedAllTokensByUserEntity(UserIdentity userIdentity);
    int deleteTokensByUserEntity(UserIdentity userIdentity);
}
