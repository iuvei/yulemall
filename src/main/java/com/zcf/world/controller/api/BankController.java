package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Bank;
import com.zcf.world.service.BankService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
* @author 许宝予
* @date 2019/04/22
*/
@RestController
@RequestMapping("bank")
@Api(value = "用户银行卡控制器", tags = {"用户银行卡接口"})
public class BankController {

    @Autowired
    private BankService bankService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "bankCard", value = "银行卡", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addBank(Bank bank) {
        this.bankService.addBank(bank);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteBankById(@PathVariable Integer id) {
        this.bankService.deleteBankById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "bankCard", value = "银行卡", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateBank(Bank bank) {
        this.bankService.updateBank(bank);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Bank> getBank(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bankService.getBank(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Bank>> getAllBank() {
       return ResponseEntity.ok(this.bankService.getAllBank());
    }

    @ApiOperation(value = "根据用户id获取银行卡")
    @PostMapping(value = "getBankList",produces = {"application/json;charset=UTF-8"})
    public Body getBankList(Integer userId) {
        return this.bankService.getBankList(userId);
    }
}
