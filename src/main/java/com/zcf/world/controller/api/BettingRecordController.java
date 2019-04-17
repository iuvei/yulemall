package com.zcf.world.controller.api;


import com.zcf.world.pojo.BettingRecord;
import com.zcf.world.service.BettingRecordService;
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
@RequestMapping("bettingRecord")
@Api(value = "用户投注记录表控制器", tags = {"用户投注记录表接口"})
public class BettingRecordController {

    @Autowired
    private BettingRecordService bettingRecordService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否撤单(N未删Y已删)", dataType = "String"),
                @ApiImplicitParam(name = "toumoney", value = "投注金额", dataType = "Integer"),
                @ApiImplicitParam(name = "issue", value = "期号", dataType = "String"),
                @ApiImplicitParam(name = "gameid", value = "玩法id", dataType = "Integer"),
                @ApiImplicitParam(name = "touccontent", value = "投注内容", dataType = "String"),
                @ApiImplicitParam(name = "type", value = "开奖状态(0未开奖1中奖2未中奖)", dataType = "String"),
                @ApiImplicitParam(name = "getmoney", value = "获得金额", dataType = "Date"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addBettingRecord(BettingRecord bettingRecord) {
        this.bettingRecordService.addBettingRecord(bettingRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteBettingRecordById(@PathVariable Integer id) {
        this.bettingRecordService.deleteBettingRecordById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否撤单(N未删Y已删)", dataType = "String"),
            @ApiImplicitParam(name = "toumoney", value = "投注金额", dataType = "Integer"),
            @ApiImplicitParam(name = "issue", value = "期号", dataType = "String"),
            @ApiImplicitParam(name = "gameid", value = "玩法id", dataType = "Integer"),
            @ApiImplicitParam(name = "touccontent", value = "投注内容", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "开奖状态(0未开奖1中奖2未中奖)", dataType = "String"),
            @ApiImplicitParam(name = "getmoney", value = "获得金额", dataType = "Date"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateBettingRecord(BettingRecord bettingRecord) {
        this.bettingRecordService.updateBettingRecord(bettingRecord);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<BettingRecord> getBettingRecord(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bettingRecordService.getBettingRecord(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<BettingRecord>> getAllBettingRecord() {
       return ResponseEntity.ok(this.bettingRecordService.getAllBettingRecord());
    }
}
