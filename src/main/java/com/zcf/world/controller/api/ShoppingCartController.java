package com.zcf.world.controller.api;


import com.zcf.world.DTO.ShoppingCartDTO;
import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.ShoppingCart;
import com.zcf.world.service.ShoppingCartService;
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
* @date 2019/04/19
*/
@RestController
@RequestMapping("shoppingCart")
@Api(value = "购物车控制器", tags = {"购物车接口"})
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
                @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "spId", value = "规格参数id", dataType = "Integer"),
                @ApiImplicitParam(name = "num", value = "个数/数量", dataType = "Integer"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除(N未删除Y已删)", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCartService.addShoppingCart(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteShoppingCartById(@PathVariable Integer id) {
        this.shoppingCartService.deleteShoppingCartById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
            @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "spId", value = "规格参数id", dataType = "Integer"),
            @ApiImplicitParam(name = "num", value = "个数/数量", dataType = "Integer"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除(N未删除Y已删)", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCartService.updateShoppingCart(shoppingCart);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Integer id) {
        return ResponseEntity.ok(this.shoppingCartService.getShoppingCart(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<ShoppingCart>> getAllShoppingCart() {
       return ResponseEntity.ok(this.shoppingCartService.getAllShoppingCart());
    }

    @ApiOperation(value = "根据用户id 查询 购物车")
    @PostMapping(value = "getShopDTO",produces = {"application/json;charset=UTF-8"})
    public List<ShoppingCartDTO> getShopDTO(Integer userId) {
        return this.shoppingCartService.getShopDTO(userId);
    }

    @ApiOperation(value = "购物车数量加减")
    @PostMapping(value = "updateShoppingCartNum",produces = {"application/json;charset=UTF-8"})
    public Body updateShoppingCartNum(Integer id, Integer or) {
        return this.shoppingCartService.updateShoppingCartNum(id,or);
    }
}
