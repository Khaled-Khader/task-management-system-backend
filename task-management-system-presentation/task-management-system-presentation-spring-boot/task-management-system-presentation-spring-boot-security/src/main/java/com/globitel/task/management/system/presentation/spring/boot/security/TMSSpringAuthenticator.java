package com.globitel.task.management.system.presentation.spring.boot.security;

import com.globitel.task.management.system.core.shared.exception.UnAuthorizedException;
import com.globitel.task.management.system.core.shared.exception.data.DefaultErrorMessage;
import com.globitel.task.management.system.core.user.factory.UserUseCaseFactory;
import com.globitel.task.management.system.core.user.factory.response.LoginResponse;
import com.globitel.task.management.system.core.user.factory.security.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class TMSSpringAuthenticator {

    private final AuthenticationManager authenticationManager;
    private final UserUseCaseFactory userUseCaseFactory;

    public LoginResponse authenticate(LoginRequest loginRequest){
        Authentication authentication;
        try{
            authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.userName(),
                                    loginRequest.password()
                            )
                    );
        }catch(Exception e){
            throw new UnAuthorizedException(DefaultErrorMessage.of("AUTHENTICATION_FAILED", "TMS-USER-UC-DF-1000"));
        }


        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        assert userDetails != null;
        String username= userDetails.getUsername();

        return userUseCaseFactory.returnUserDataAfterAuthenticatedUseCase().execute(username);

    }

}
