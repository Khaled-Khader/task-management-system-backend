package com.globitel.task.management.system.core.token.jpa;

import com.globitel.task.management.system.core.token.dao.TokenCreator;
import com.globitel.task.management.system.core.token.dao.TokenDao;
import com.globitel.task.management.system.core.token.dao.TokenUpdater;
import com.globitel.task.management.system.core.token.entity.TokenEntity;
import com.globitel.task.management.system.core.token.identity.TokenIdentity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class TokenJpa implements TokenDao {

    private final TokenRepository tokenRepository;

    private final int range;


    @Override
    public TokenIdentity save(TokenIdentity entity) {
        return tokenRepository.save((TokenEntity) entity);
    }

    @Override
    public Optional<TokenIdentity> find(String uuid) {
        return tokenRepository.findById(uuid)
                .map(Function.identity());
    }

    @Override
    public List<TokenIdentity> findAll() {
        return List.copyOf(tokenRepository.findAll());
    }

    @Override
    public TokenCreator creator() {
        return new TokenCreatorImpl();
    }

    @Override
    public TokenUpdater updater(TokenIdentity identity) {
        return new TokenUpdaterImpl((TokenEntity) identity);
    }


    @Override
    public void updateOutOfRangeTokens() {
        tokenRepository.denyOldTokensPerUser(range);
    }


    @Override
    public void deniedAllTokensByUserEntity(UserIdentity userIdentity) {
        tokenRepository.denyAllTokensForUser((UserEntity) userIdentity);
    }

    @Override
    public int deleteTokensByUserEntity(UserIdentity userIdentity) {
        return tokenRepository.deleteByUser((UserEntity) userIdentity);
    }

}
