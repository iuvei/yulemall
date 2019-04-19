package com.zcf.world.controller.api;


import com.zcf.world.pojo.Youlike;
import com.zcf.world.service.YoulikeService;
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
@RequestMapping("youlike")
@Api(value = "猜你喜欢控制器", tags = {"猜你喜欢接口"})
public class YoulikeController {

    @Autowired
    private YoulikeService youlikeService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
                @ApiImplicitParam(name = "names", value = "猜你喜欢", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除(N未删除Y已删)", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addYoulike(Youlike youlike) {
        this.youlikeService.addYoulike(youlike);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteYoulikeById(@PathVariable Integer id) {
        this.youlikeService.deleteYoulikeById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列 ID", dataType = "Integer"),
            @ApiImplicitParam(name = "names", value = "猜你喜欢", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除(N未删除Y已删)", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateYoulike(Youlike youlike) {
        this.youlikeService.updateYoulike(youlike);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Youlike> getYoulike(@PathVariable Integer id) {
        return ResponseEntity.ok(this.youlikeService.getYoulike(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Youlike>> getAllYoulike() {
       return ResponseEntity.ok(this.youlikeService.getAllYoulike());
    }

    @ApiOperation(value = "猜你喜欢取八条数据")
    @PostMapping(value = "getYoulikeShow",produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Youlike>> getYoulikeShow(){
        return ResponseEntity.ok(this.youlikeService.getYoulikeShow());
    }
}
