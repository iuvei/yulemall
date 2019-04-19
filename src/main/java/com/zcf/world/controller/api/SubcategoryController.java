package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Subcategory;
import com.zcf.world.service.SubcategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 许宝予
 * @date 2019/04/18
 */
@RestController
@RequestMapping("subcategory")
@Api(value = "二级分类控制器", tags = {"二级分类接口"})
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "categoryId", value = "一级分类id", dataType = "Integer"),
            @ApiImplicitParam(name = "subcategory", value = "二级分类名称", dataType = "String"),
            @ApiImplicitParam(name = "img", value = "二级分类图片", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addSubcategory(Subcategory subcategory) {
        this.subcategoryService.addSubcategory(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteSubcategoryById(@PathVariable Integer id) {
        this.subcategoryService.deleteSubcategoryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "categoryId", value = "一级分类id", dataType = "Integer"),
            @ApiImplicitParam(name = "subcategory", value = "二级分类名称", dataType = "String"),
            @ApiImplicitParam(name = "img", value = "二级分类图片", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateSubcategory(Subcategory subcategory) {
        this.subcategoryService.updateSubcategory(subcategory);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Subcategory> getSubcategory(@PathVariable Integer id) {
        return ResponseEntity.ok(this.subcategoryService.getSubcategory(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<List<Subcategory>> getAllSubcategory() {
        return ResponseEntity.ok(this.subcategoryService.getAllSubcategory());
    }

    @ApiOperation(value = "首页分类")
    @PostMapping(value = "getShowHome", produces = {"application/json;charset=UTF-8"})
    public Body getShowHome() {
        return this.subcategoryService.getShowHome();
    }

    @ApiOperation(value = "根据一级分类查询二级分类")
    @PostMapping(value = "getCategoryList", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<List<Subcategory>> getCategoryList(Integer categoryId) {
        return ResponseEntity.ok(this.subcategoryService.getCategoryList(categoryId));
    }
}
