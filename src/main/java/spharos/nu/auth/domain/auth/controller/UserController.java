package spharos.nu.auth.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import spharos.nu.auth.domain.auth.service.UserService;
import spharos.nu.auth.global.apiresponse.ApiResponse;
import spharos.nu.auth.utils.jwt.JwtProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Users", description = "회원가입 및 로그인 등등 유저 관련 기본적으로 필요한 메소드")
public class UserController {
	private final UserService userService;
	private final JwtProvider jwtProvider;

	@PutMapping()
	@Operation(summary = "회원 탈퇴", description = "해당 회원의 isWithdraw 를 true 로 변경")
	public ResponseEntity<ApiResponse<Void>> withdrawUser(@RequestHeader("Authorization") String token) {
		String uuid = jwtProvider.getUuid(token);
		userService.withdraw(uuid);
		return ApiResponse.success(null, "회원 탈퇴 성공");
	}
}
