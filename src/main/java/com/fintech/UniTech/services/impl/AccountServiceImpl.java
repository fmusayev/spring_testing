package com.fintech.UniTech.services.impl;

import com.fintech.UniTech.domain.Account;
import com.fintech.UniTech.domain.Transfer;
import com.fintech.UniTech.dto.AccountDto;
import com.fintech.UniTech.dto.TransferDto;
import com.fintech.UniTech.dto.TransferResultDto;
import com.fintech.UniTech.enums.AccountStatus;
import com.fintech.UniTech.exception.DeActiveAccountException;
import com.fintech.UniTech.exception.InsufficientBalanceException;
import com.fintech.UniTech.exception.NonExistAccountException;
import com.fintech.UniTech.exception.SameAccountException;
import com.fintech.UniTech.repository.AccountRepository;
import com.fintech.UniTech.repository.TransferRepository;
import com.fintech.UniTech.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AccountDto> getUserAccounts(Long userId) {
        return accountRepository.findAccountByUserIdAndStatus(userId, AccountStatus.ACTIVE).stream()
                .map(account -> modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransferResultDto transfer(Long userId, TransferDto transferDto) {

        Account fromAccount = accountRepository.findByIdAndUserId(transferDto.getFromAccount(), userId).orElseThrow(
                () -> new NonExistAccountException(
                        "Authorized user does not have account indicated in property `fromAccount`"));
        if (fromAccount.getBalance() < transferDto.getAmount())
            throw new InsufficientBalanceException("Please check balance");
        if (transferDto.getFromAccount().equals(transferDto.getToAccount()))
            throw new SameAccountException("Unable to transfer to same account");
        if (fromAccount.getStatus().equals(AccountStatus.INACTIVE))
            throw new DeActiveAccountException("Unable to transfer from inactive account");

        try {
            Account toAccount = accountRepository.getById(transferDto.getToAccount());

            if (toAccount.getStatus().equals(AccountStatus.INACTIVE))
                throw new DeActiveAccountException("Account is not active");

            fromAccount.setBalance(fromAccount.getBalance() - transferDto.getAmount());
            toAccount.setBalance(toAccount.getBalance() + transferDto.getAmount());

            accountRepository.saveAllAndFlush(List.of(fromAccount, toAccount));
            Transfer transfer = transferRepository.save(modelMapper.map(transferDto, Transfer.class));

            return new TransferResultDto(transfer.getId());
        } catch (EntityNotFoundException e) {
            throw new NonExistAccountException("Account not exists");
        }
    }
}
