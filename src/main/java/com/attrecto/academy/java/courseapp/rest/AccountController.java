package com.attrecto.academy.java.courseapp.rest;

import com.attrecto.academy.java.courseapp.model.dto.LoginDto;
import com.attrecto.academy.java.courseapp.model.dto.MinimalUserDto;
import com.attrecto.academy.java.courseapp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@Tag(name = "Account API")
public class AccountController {
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
    @GetMapping(value= "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Give back information about the logged user" ,security = {@SecurityRequirement(name = "token")})
	public MinimalUserDto me() {
		return accountService.getLoggedUser();
	}
	
    @PostMapping(value= "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a login token")
	public String login(@RequestBody @Valid LoginDto loginDto) {
		return accountService.generateJwtToken(loginDto);
	}
}
