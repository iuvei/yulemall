package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Orderlist;
import com.zcf.world.service.OrderlistService;
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
@RequestMapping("orderlist")
@Api(value = "订单表控制器", tags = {"订单表接口"})
public class OrderlistController {

    @Autowired
    private OrderlistService orderlistService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列  id", dataType = "Integer"),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
                @ApiImplicitParam(name = "spNames", value = "规格参数id(，隔开)", dataType = "String"),
                @ApiImplicitParam(name = "addressId", value = "地址id", dataType = "Integer"),
                @ApiImplicitParam(name = "buyNumbers", value = "订单号", dataType = "String"),
                @ApiImplicitParam(name = "num", value = "数量", dataType = "Integer"),
                @ApiImplicitParam(name = "totalMoney", value = "总价", dataType = "Date"),
                @ApiImplicitParam(name = "type", value = "订单状态（1待付款2待发货3待收货4待评论5售后记录）", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addOrderlist(Orderlist orderlist) {
        this.orderlistService.addOrderlist(orderlist);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteOrderlistById(@PathVariable Integer id) {
        this.orderlistService.deleteOrderlistById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列  id", dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
            @ApiImplicitParam(name = "spNames", value = "规格参数id(，隔开)", dataType = "String"),
            @ApiImplicitParam(name = "addressId", value = "地址id", dataType = "Integer"),
            @ApiImplicitParam(name = "buyNumbers", value = "订单号", dataType = "String"),
            @ApiImplicitParam(name = "num", value = "数量", dataType = "Integer"),
            @ApiImplicitParam(name = "totalMoney", value = "总价", dataType = "BigDecimal"),
            @ApiImplicitParam(name = "type", value = "订单状态（1待付款2待发货3待收货4待评论5售后记录）", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
            @ApiImplicitParam(name = "moneys", value = "实付金额", dataType = "BigDecimal"),
    })
    public ResponseEntity<Void> updateOrderlist(Orderlist orderlist) {
        this.orderlistService.updateOrderlist(orderlist);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Orderlist> getOrderlist(@PathVariable Integer id) {
        return ResponseEntity.ok(this.orderlistService.getOrderlist(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Orderlist>> getAllOrderlist() {
       return ResponseEntity.ok(this.orderlistService.getAllOrderlist());
    }

    @ApiOperation(value = "根据用户id、状态 查询订单信息")
    @PostMapping(value = "getUserOrderList",produces = {"application/json;charset=UTF-8"})
    public Body getUserOrderList(Integer userId, Integer type) {
        return this.orderlistService.getUserOrderList(userId, type);
    }
}
