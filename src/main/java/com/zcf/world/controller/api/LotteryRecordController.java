package com.zcf.world.controller.api;


import com.zcf.world.pojo.LotteryRecord;
import com.zcf.world.service.LotteryRecordService;
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
* @date 2019/04/17
*/
@RestController
@RequestMapping("lotteryRecord")
@Api(value = "开奖记录表控制器", tags = {"开奖记录表接口"})
public class LotteryRecordController {

    @Autowired
    private LotteryRecordService lotteryRecordService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "issue", value = "期号", dataType = "Integer"),
                @ApiImplicitParam(name = "lotterynum", value = "开奖号码", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "kaiTime", value = "开奖时间", dataType = "Date"),
                @ApiImplicitParam(name = "fengTime", value = "封盘时间", dataType = "Date"),
                @ApiImplicitParam(name = "type", value = "开奖状态(0未开奖1已开奖)", dataType = "String"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addLotteryRecord(LotteryRecord lotteryRecord) {
        this.lotteryRecordService.addLotteryRecord(lotteryRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteLotteryRecordById(@PathVariable Integer id) {
        this.lotteryRecordService.deleteLotteryRecordById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "issue", value = "期号", dataType = "Integer"),
            @ApiImplicitParam(name = "lotterynum", value = "开奖号码", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "kaiTime", value = "开奖时间", dataType = "Date"),
            @ApiImplicitParam(name = "fengTime", value = "封盘时间", dataType = "Date"),
            @ApiImplicitParam(name = "type", value = "开奖状态(0未开奖1已开奖)", dataType = "String"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateLotteryRecord(LotteryRecord lotteryRecord) {
        this.lotteryRecordService.updateLotteryRecord(lotteryRecord);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<LotteryRecord> getLotteryRecord(@PathVariable Integer id) {
        return ResponseEntity.ok(this.lotteryRecordService.getLotteryRecord(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<LotteryRecord>> getAllLotteryRecord() {
       return ResponseEntity.ok(this.lotteryRecordService.getAllLotteryRecord());
    }
}
