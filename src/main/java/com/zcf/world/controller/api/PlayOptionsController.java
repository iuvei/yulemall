package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.PlayOptions;
import com.zcf.world.service.PlayOptionsService;
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
* @date 2019/04/24
*/
@RestController
@RequestMapping("playOptions")
@Api(value = "游戏内容选项控制器", tags = {"游戏内容选项接口"})
public class PlayOptionsController {

    @Autowired
    private PlayOptionsService playOptionsService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "titles", value = "标题(如：冠亚和)", dataType = "String"),
                @ApiImplicitParam(name = "contents", value = "内容(如：冠军大)", dataType = "String"),
                @ApiImplicitParam(name = "odds", value = "赔率", dataType = "Date"),
                @ApiImplicitParam(name = "types", value = "类型(1.两面 2.1~5名 3.6~10名 4.冠亚和)", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addPlayOptions(PlayOptions playOptions) {
        this.playOptionsService.addPlayOptions(playOptions);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deletePlayOptionsById(@PathVariable Integer id) {
        this.playOptionsService.deletePlayOptionsById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "titles", value = "标题(如：冠亚和)", dataType = "String"),
            @ApiImplicitParam(name = "contents", value = "内容(如：冠军大)", dataType = "String"),
            @ApiImplicitParam(name = "odds", value = "赔率", dataType = "Date"),
            @ApiImplicitParam(name = "types", value = "类型(1.两面 2.1~5名 3.6~10名 4.冠亚和)", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "添加时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updatePlayOptions(PlayOptions playOptions) {
        this.playOptionsService.updatePlayOptions(playOptions);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<PlayOptions> getPlayOptions(@PathVariable Integer id) {
        return ResponseEntity.ok(this.playOptionsService.getPlayOptions(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<PlayOptions>> getAllPlayOptions() {
       return ResponseEntity.ok(this.playOptionsService.getAllPlayOptions());
    }

    @ApiOperation(value = "根据types查询游戏基本信息")
    @PostMapping(value = "getTypesPlay",produces = {"application/json;charset=UTF-8"})
    public Body getTypesPlay(String types) {
        return this.playOptionsService.getTypesPlay(types);
    }
}