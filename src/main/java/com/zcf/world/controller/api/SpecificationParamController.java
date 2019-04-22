package com.zcf.world.controller.api;


import com.zcf.world.pojo.SpecificationParam;
import com.zcf.world.service.SpecificationParamService;
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
@RequestMapping("specificationParam")
@Api(value = "规格参数表控制器", tags = {"规格参数表接口"})
public class SpecificationParamController {

    @Autowired
    private SpecificationParamService specificationParamService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列id", dataType = "Integer"),
                @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
                @ApiImplicitParam(name = "specificationId", value = "规格id", dataType = "Integer"),
                @ApiImplicitParam(name = "spNames", value = "规格参数名称", dataType = "String"),
                @ApiImplicitParam(name = "ceratTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addSpecificationParam(SpecificationParam specificationParam) {
        this.specificationParamService.addSpecificationParam(specificationParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteSpecificationParamById(@PathVariable Integer id) {
        this.specificationParamService.deleteSpecificationParamById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列id", dataType = "Integer"),
            @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
            @ApiImplicitParam(name = "specificationId", value = "规格id", dataType = "Integer"),
            @ApiImplicitParam(name = "spNames", value = "规格参数名称", dataType = "String"),
            @ApiImplicitParam(name = "ceratTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateSpecificationParam(SpecificationParam specificationParam) {
        this.specificationParamService.updateSpecificationParam(specificationParam);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<SpecificationParam> getSpecificationParam(@PathVariable Integer id) {
        return ResponseEntity.ok(this.specificationParamService.getSpecificationParam(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<SpecificationParam>> getAllSpecificationParam() {
       return ResponseEntity.ok(this.specificationParamService.getAllSpecificationParam());
    }
}
