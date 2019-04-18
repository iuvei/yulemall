package com.zcf.world.controller.api;


import com.zcf.world.pojo.Item;
import com.zcf.world.service.ItemService;
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
@RequestMapping("item")
@Api(value = "商品表控制器", tags = {"商品表接口"})
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
                @ApiImplicitParam(name = "brandId", value = "所属品牌id (0不属于)", dataType = "Integer"),
                @ApiImplicitParam(name = "reclassify", value = "二级分类 id  (0不属于)", dataType = "Integer"),
                @ApiImplicitParam(name = "itemName", value = "商品名称", dataType = "String"),
                @ApiImplicitParam(name = "itemIntroduce", value = "商品介绍", dataType = "String"),
                @ApiImplicitParam(name = "purchasingPrice", value = "商品进价", dataType = "Date"),
                @ApiImplicitParam(name = "sellingPrice", value = "商品售价", dataType = "Date"),
                @ApiImplicitParam(name = "saleCount", value = "销量", dataType = "Integer"),
                @ApiImplicitParam(name = "images", value = "商品列表图", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addItem(Item item) {
        this.itemService.addItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteItemById(@PathVariable Integer id) {
        this.itemService.deleteItemById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
            @ApiImplicitParam(name = "brandId", value = "所属品牌id (0不属于)", dataType = "Integer"),
            @ApiImplicitParam(name = "reclassify", value = "二级分类 id  (0不属于)", dataType = "Integer"),
            @ApiImplicitParam(name = "itemName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "itemIntroduce", value = "商品介绍", dataType = "String"),
            @ApiImplicitParam(name = "purchasingPrice", value = "商品进价", dataType = "Date"),
            @ApiImplicitParam(name = "sellingPrice", value = "商品售价", dataType = "Date"),
            @ApiImplicitParam(name = "saleCount", value = "销量", dataType = "Integer"),
            @ApiImplicitParam(name = "images", value = "商品列表图", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateItem(Item item) {
        this.itemService.updateItem(item);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Item> getItem(@PathVariable Integer id) {
        return ResponseEntity.ok(this.itemService.getItem(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Item>> getAllItem() {
       return ResponseEntity.ok(this.itemService.getAllItem());
    }
}
