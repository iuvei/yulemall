package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Searchs;
import com.zcf.world.service.SearchsService;
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
@RequestMapping("searchs")
@Api(value = "搜索记录表控制器", tags = {"搜索记录表接口"})
public class SearchsController {

    @Autowired
    private SearchsService searchsService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列   ID", dataType = "Integer"),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "searchs", value = "搜索内容", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addSearchs(Searchs searchs) {
        this.searchsService.addSearchs(searchs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteSearchsById(@PathVariable Integer id) {
        this.searchsService.deleteSearchsById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "标识列   ID", dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "searchs", value = "搜索内容", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateSearchs(Searchs searchs) {
        this.searchsService.updateSearchs(searchs);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Searchs> getSearchs(@PathVariable Integer id) {
        return ResponseEntity.ok(this.searchsService.getSearchs(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Searchs>> getAllSearchs() {
       return ResponseEntity.ok(this.searchsService.getAllSearchs());
    }

    @ApiOperation(value = "根据用户id查询搜索记录")
    @PostMapping(value = "getUserSearchs",produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Searchs>> getUserSearchs(Integer userId) {
        return ResponseEntity.ok(this.searchsService.getUserSearchs(userId));
    }

    @ApiOperation(value = "根据用户ID清除最近搜索")
    @PostMapping(value = "updateUserSearchs",produces = {"application/json;charset=UTF-8"})
    public Body updateUserSearchs(Integer userId) {
        return this.searchsService.updateUserSearchs(userId);
    }
}
