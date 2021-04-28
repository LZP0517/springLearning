package com.yc.tx.controllers;

import com.yc.tx.bean.Accounts;
import com.yc.tx.service.AccountService;
import com.yc.tx.vo.AccountVo;
import com.yc.tx.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-24 20:57
 */
@Controller
@Slf4j //日志
@Api(value = "账户操作接口", tags = {"账户操作接口", "控制层"})
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccounts", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "开户", notes = "根据金额, 来完成开户操作，注意此时的金额表示要存的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "money", value = "开户金额", required = true, dataType = "double")})
    public @ResponseBody
    ResultVo openAccounts(AccountVo accountVo) {

        log.debug("用户请求开户,存入:" + accountVo.getMoney());
        ResultVo rv = new ResultVo();
        try {
            Accounts a = new Accounts();
            double money = 1;
            if (accountVo.getMoney() != null && accountVo.getMoney() > 0) {
                money = accountVo.getMoney();
            }
            Integer id = accountService.openAccount(a, money);
            a.setAccountId(id);
            a.setBalance(money);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }


    @RequestMapping(value = "/deposite", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "存款", notes = "根据账户编号及金额, 来完成存款操作，注意此时的金额表示要存的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "int"),
            @ApiImplicitParam(name = "money", value = "存款金额", required = true, dataType = "double")})
    public @ResponseBody
    ResultVo deposite(AccountVo accountVo) {
        log.debug("用户存入:" + accountVo.getMoney());
        ResultVo rv = new ResultVo();
        try {
            Accounts a = new Accounts();
            a.setAccountId(accountVo.getAccountId());
            Accounts accounts = accountService.deposite(a, accountVo.getMoney(), "deposite", null);
            rv.setCode(1);
            rv.setData(accounts);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }


    @RequestMapping(value = "/withdraw", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "取款", notes = "根据账户编号及金额, 来完成取款操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "int"),
            @ApiImplicitParam(name = "money", value = "取款金额", required = true, dataType = "double")})
    public @ResponseBody
    ResultVo withdraw(AccountVo accountVo) {
        log.debug("用户取出:" + accountVo.getMoney());
        ResultVo rv = new ResultVo();
        try {
            Accounts a = new Accounts();
            a.setAccountId(accountVo.getAccountId());
            Accounts accounts = accountService.withdraw(a, accountVo.getMoney(), "withdraw", null);
            rv.setCode(1);
            rv.setData(accounts);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }


    @RequestMapping(value = "/transfer", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "java.lang.Integer"),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true, dataType = "int"),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true, dataType = "double")})
    public @ResponseBody
    ResultVo transfer(AccountVo accountVo) {
        log.debug("用户转账:" + accountVo.getMoney());
        ResultVo rv = new ResultVo();
        try {
            Accounts a = new Accounts();
            a.setAccountId(accountVo.getAccountId());
            Accounts v = new Accounts();
            v.setAccountId(accountVo.getInAccountId());
            Accounts accounts = accountService.transfer(v, a, accountVo.getMoney());
            rv.setCode(1);
            rv.setData(accounts);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Accounts> FindAll() {
        List<Accounts> list = accountService.findAll();
        return list;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public @ResponseBody
    ResultVo<Accounts> FindById(AccountVo accountVo) {
        ResultVo rv = new ResultVo();
        try {
            Accounts a = new Accounts();
            a.setAccountId(accountVo.getAccountId());
            Accounts accounts = accountService.showBalance(a);
            rv.setCode(1);
            rv.setData(accounts);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }
}
