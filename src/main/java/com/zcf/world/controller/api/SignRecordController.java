package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.SignRecord;
import com.zcf.world.service.SignRecordService;
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
* @date 2019/04/23
*/
@RestController
@RequestMapping("signRecord")
@Api(value = "用户签到记录表控制器", tags = {"用户签到记录表接口"})
public class SignRecordController {

    @Autowired
    private SignRecordService signRecordService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "count", value = "连续签到天数", dataType = "Integer"),
                @ApiImplicitParam(name = "numDay", value = "本月总共签到天数", dataType = "Integer"),
                @ApiImplicitParam(name = "type", value = "是否签到(1签到2未签到)", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addSignRecord(SignRecord signRecord) {
        this.signRecordService.addSignRecord(signRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteSignRecordById(@PathVariable Integer id) {
        this.signRecordService.deleteSignRecordById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "count", value = "连续签到天数", dataType = "Integer"),
            @ApiImplicitParam(name = "numDay", value = "本月总共签到天数", dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "是否签到(1签到2未签到)", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateSignRecord(SignRecord signRecord) {
        this.signRecordService.updateSignRecord(signRecord);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<SignRecord> getSignRecord(@PathVariable Integer id) {
        return ResponseEntity.ok(this.signRecordService.getSignRecord(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<SignRecord>> getAllSignRecord() {
       return ResponseEntity.ok(this.signRecordService.getAllSignRecord());
    }

    @ApiOperation(value = "初始化月签到数据")
    @PostMapping(value = "updateDayOrMonth",produces = {"application/json;charset=UTF-8"})
    public Body updateDayOrMonth() {
        return this.signRecordService.updateDayOrMonth();
    }

    @ApiOperation(value = "用户签到接口")
    @PostMapping(value = "updateTypeOfSign",produces = {"application/json;charset=UTF-8"})
    public Body updateTypeOfSign(Integer id) {
        return this.signRecordService.updateTypeOfSign(id);
    }



}
