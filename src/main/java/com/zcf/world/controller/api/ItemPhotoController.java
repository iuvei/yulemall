package com.zcf.world.controller.api;


import com.zcf.world.pojo.ItemPhoto;
import com.zcf.world.service.ItemPhotoService;
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
* @date 2019/04/18
*/
@RestController
@RequestMapping("itemPhoto")
@Api(value = "商品详情图控制器", tags = {"商品详情图接口"})
public class ItemPhotoController {

    @Autowired
    private ItemPhotoService itemPhotoService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列  ID", dataType = "Integer"),
                @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
                @ApiImplicitParam(name = "img", value = "商品图片", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addItemPhoto(ItemPhoto itemPhoto) {
        this.itemPhotoService.addItemPhoto(itemPhoto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteItemPhotoById(@PathVariable Integer id) {
        this.itemPhotoService.deleteItemPhotoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列  ID", dataType = "Integer"),
            @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
            @ApiImplicitParam(name = "img", value = "商品图片", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateItemPhoto(ItemPhoto itemPhoto) {
        this.itemPhotoService.updateItemPhoto(itemPhoto);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<ItemPhoto> getItemPhoto(@PathVariable Integer id) {
        return ResponseEntity.ok(this.itemPhotoService.getItemPhoto(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<ItemPhoto>> getAllItemPhoto() {
       return ResponseEntity.ok(this.itemPhotoService.getAllItemPhoto());
    }
}
