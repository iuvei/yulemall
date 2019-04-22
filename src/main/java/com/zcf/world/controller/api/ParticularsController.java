package com.zcf.world.controller.api;


import com.zcf.world.pojo.Particulars;
import com.zcf.world.service.ParticularsService;
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
@RequestMapping("particulars")
@Api(value = "售后申请控制器", tags = {"售后申请接口"})
public class ParticularsController {

    @Autowired
    private ParticularsService particularsService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "orderlistId", value = "订单id", dataType = "Integer"),
                @ApiImplicitParam(name = "buyNumbers", value = "订单号", dataType = "String"),
                @ApiImplicitParam(name = "type", value = "服务类型(1换货2退款退货)", dataType = "Integer"),
                @ApiImplicitParam(name = "particulars", value = "详细说明", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addParticulars(Particulars particulars) {
        this.particularsService.addParticulars(particulars);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteParticularsById(@PathVariable Integer id) {
        this.particularsService.deleteParticularsById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "orderlistId", value = "订单id", dataType = "Integer"),
            @ApiImplicitParam(name = "buyNumbers", value = "订单号", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "服务类型(1换货2退款退货)", dataType = "Integer"),
            @ApiImplicitParam(name = "particulars", value = "详细说明", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateParticulars(Particulars particulars) {
        this.particularsService.updateParticulars(particulars);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Particulars> getParticulars(@PathVariable Integer id) {
        return ResponseEntity.ok(this.particularsService.getParticulars(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Particulars>> getAllParticulars() {
       return ResponseEntity.ok(this.particularsService.getAllParticulars());
    }



}
