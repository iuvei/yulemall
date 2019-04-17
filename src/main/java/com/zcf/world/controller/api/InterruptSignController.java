package com.zcf.world.controller.api;


import com.zcf.world.pojo.InterruptSign;
import com.zcf.world.service.InterruptSignService;
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
@RequestMapping("interruptSign")
@Api(value = "断签记录表控制器", tags = {"断签记录表接口"})
public class InterruptSignController {

    @Autowired
    private InterruptSignService interruptSignService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "userid", value = "断签用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "interruptTime", value = "断签时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addInterruptSign(InterruptSign interruptSign) {
        this.interruptSignService.addInterruptSign(interruptSign);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteInterruptSignById(@PathVariable Integer id) {
        this.interruptSignService.deleteInterruptSignById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "断签用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "interruptTime", value = "断签时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateInterruptSign(InterruptSign interruptSign) {
        this.interruptSignService.updateInterruptSign(interruptSign);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<InterruptSign> getInterruptSign(@PathVariable Integer id) {
        return ResponseEntity.ok(this.interruptSignService.getInterruptSign(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<InterruptSign>> getAllInterruptSign() {
       return ResponseEntity.ok(this.interruptSignService.getAllInterruptSign());
    }
}
