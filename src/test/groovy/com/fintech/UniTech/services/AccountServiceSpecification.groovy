package com.fintech.UniTech.services

import com.fintech.UniTech.domain.Account
import com.fintech.UniTech.domain.Transfer
import com.fintech.UniTech.domain.User
import com.fintech.UniTech.dto.TransferDto
import com.fintech.UniTech.dto.TransferResultDto
import com.fintech.UniTech.enums.AccountStatus
import com.fintech.UniTech.exception.InsufficientBalanceException
import com.fintech.UniTech.repository.AccountRepository
import com.fintech.UniTech.repository.TransferRepository
import com.fintech.UniTech.services.impl.AccountServiceImpl
import org.mockito.Mock
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import spock.lang.Specification

import javax.security.auth.login.AccountNotFoundException


class AccountServiceSpecification extends Specification {
    AccountRepository accountRepository = Mock()
    TransferRepository transferRepository = Mock()
    ModelMapper mapper = new ModelMapper()

    AccountService accountService
    def setup() {
        accountService = new AccountServiceImpl(accountRepository, transferRepository, mapper)
    }

    def "testAccountToAccountTransferSuccess"() {
        given:
        User loggedUser = new User()
        loggedUser.id = 1

        TransferDto transferDto = new TransferDto(1, 2, 10.0)

        Account fromAccount = new Account(1, loggedUser, "account_1", 100.0, AccountStatus.ACTIVE)
        Account toAccount = new Account(2, loggedUser, "account_2", 100.0, AccountStatus.ACTIVE)

        when:
        TransferResultDto result = accountService.transfer(loggedUser.id, transferDto)

        then:
        1 * accountRepository.findByIdAndUserId(*_) >> Optional.of(fromAccount)
        1 * accountRepository.getById(*_) >> toAccount
        1 * transferRepository.save(*_) >> new Transfer(1, fromAccount.id, toAccount.id, transferDto.amount)

        fromAccount.balance == 90.0
        toAccount.balance == 110.0
        result.transferId > 0
    }

    def "testAccountToAccountTransferInsufficientBalance"() {
        given:
        User loggedUser = new User()
        loggedUser.id = 1

        TransferDto transferDto = new TransferDto(1, 2, 1000.0)

        Account fromAccount = new Account(1, loggedUser, "account_1", 100.0, AccountStatus.ACTIVE)
        Account toAccount = new Account(2, loggedUser, "account_2", 100.0, AccountStatus.ACTIVE)

        when:
        TransferResultDto result = accountService.transfer(loggedUser.id, transferDto)

        then:
        1 * accountRepository.findByIdAndUserId(*_) >> Optional.of(fromAccount)
        0 * accountRepository.getById(*_) >> toAccount
        0 * transferRepository.save(*_) >> new Transfer(1, fromAccount.id, toAccount.id, transferDto.amount)

        thrown(InsufficientBalanceException.class)
    }

    def "testAccountToAccountTransferSameAccount"() {
    }
}