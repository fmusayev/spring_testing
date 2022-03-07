package com.fintech.UniTech.controller;

import com.fintech.UniTech.dto.AccountDto;
import com.fintech.UniTech.dto.TransferDto;
import com.fintech.UniTech.dto.TransferResultDto;
import com.fintech.UniTech.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/users/{user_id}/accounts")
    public List<AccountDto> getUserAccounts(@PathVariable("user_id") Long userId) {
        return accountService.getUserAccounts(userId);
    }

    @PostMapping("/users/{user_id}/accounts/transfers")
    public ResponseEntity<TransferResultDto> transfer(@PathVariable("user_id") Long userId,
                                                      @RequestBody TransferDto transferDto) {
        return ResponseEntity.ok(accountService.transfer(userId, transferDto));
    }
}
