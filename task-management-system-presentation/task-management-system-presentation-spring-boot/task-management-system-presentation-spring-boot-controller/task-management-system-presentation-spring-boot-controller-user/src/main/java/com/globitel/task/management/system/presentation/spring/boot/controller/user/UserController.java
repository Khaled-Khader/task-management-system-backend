package com.globitel.task.management.system.presentation.spring.boot.controller.user;

import com.globitel.task.management.system.core.user.factory.request.*;
import com.globitel.task.management.system.core.user.factory.response.*;
import com.globitel.task.management.system.core.user.factory.UserUseCaseFactory;
import com.globitel.task.management.system.core.user.factory.security.request.LoginRequest;
import com.globitel.task.management.system.presentation.spring.boot.aop.ActivityLog;
import com.globitel.task.management.system.presentation.spring.boot.security.TMSSpringAuthenticator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCaseFactory userUseCaseFactory;
    private final TMSSpringAuthenticator tmsSpringAuthenticator;

    @ActivityLog("CREATE USER")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest  createUserRequest) {
        return userUseCaseFactory.createUserUseCase().execute(createUserRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<FindAllUsersResponse> findAllUsers() {
        return userUseCaseFactory.findAllUsersUseCase().execute();
    }

    @ActivityLog("DELETE USER")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public DeleteUserByUserNameResponse deleteUser(@RequestBody DeleteUserByUserNameRequest deleteUserByUserNameRequest) {
        return userUseCaseFactory.deleteUserByUserNameUseCase().execute(deleteUserByUserNameRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public UserResponse findUserByUuid(@PathVariable String uuid) {
        return userUseCaseFactory.findUserByUuidUseCase()
                .execute(uuid);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("name")
    public List<FindAllUsersResponse> findAllUsersByName(@RequestParam(name = "filter") String filter) {
        return userUseCaseFactory.findAllUsersByNameUseCase().execute(new FindAllUsersByRequest(filter));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("role")
    public List<FindAllUsersResponse> findAllUsersByUserRole(@RequestParam(name = "filter") String filter) {
        return userUseCaseFactory.findAllUsersByUserRoleUseCase().execute(new FindAllUsersByRequest(filter));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("status")
    public List<FindAllUsersResponse> findAllUsersByUserStatus(@RequestParam(name = "filter") String filter) {
        return userUseCaseFactory.findAllUsersByUserStatusUseCase().execute(new FindAllUsersByRequest(filter));
    }

    @ActivityLog("EDIT USER DETAILS")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("admin")
    public UserResponse editUserDetails(@RequestBody EditUserDetailsRequest editUserDetailsRequest) {
        return userUseCaseFactory.editUserDetailsUseCase().execute(editUserDetailsRequest);
    }


    @ActivityLog(value = "USER LOGIN")
    @PostMapping("login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = tmsSpringAuthenticator.authenticate(loginRequest);

        ResponseCookie cookie = ResponseCookie
                .from("access_token", loginResponse.token())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Strict")
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );
        return ResponseEntity.ok(
                new  UserResponse(
                        loginResponse.uuid(),
                        loginResponse.name(),
                        loginResponse.username(),
                        loginResponse.email(),
                        loginResponse.userStatus(),
                        loginResponse.userRole()
                )
        );
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("data")
    public ViewUserDataResponse viewUserData() {
        return userUseCaseFactory.viewUserDataUseCase().execute();
    }

    @ActivityLog("EDIT USER DATA")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("edit/data")
    public UserUpdatedResponse  editUserData(@RequestBody EditDataForUserRequest editDataForUserRequest) {
        return userUseCaseFactory.editUserDataForUserUseCase().execute(editDataForUserRequest);
    }

    @ActivityLog(value = "USER LOGOUT")
    @PostMapping("logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie
                .from("access_token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ZERO)
                .sameSite("Strict")
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("me")
    public UserResponse getCurrentUser() {
        return userUseCaseFactory.getCurrentUserUseCase().execute();
    }


}
