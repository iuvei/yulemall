package com.zcf.world.controller.api;


import com.zcf.world.DTO.CommentsDTO;
import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Comments;
import com.zcf.world.service.CommentsService;
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
@RequestMapping("comments")
@Api(value = "评论控制器", tags = {"评论接口"})
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
                @ApiImplicitParam(name = "comments", value = "评论内容", dataType = "String"),
                @ApiImplicitParam(name = "images", value = "图片", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
                @ApiImplicitParam(name = "reamrk", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addComments(Comments comments) {
        this.commentsService.addComments(comments);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteCommentsById(@PathVariable Integer id) {
        this.commentsService.deleteCommentsById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "Integer"),
            @ApiImplicitParam(name = "comments", value = "评论内容", dataType = "String"),
            @ApiImplicitParam(name = "images", value = "图片", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除N未删Y已删", dataType = "String"),
            @ApiImplicitParam(name = "reamrk", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateComments(Comments comments) {
        this.commentsService.updateComments(comments);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(this.commentsService.getComments(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Comments>> getAllComments() {
       return ResponseEntity.ok(this.commentsService.getAllComments());
    }

    @ApiOperation(value = "根据商品id 查询商品评论")
    @PostMapping(value = "getItemComments",produces = {"application/json;charset=UTF-8"})
    public Body getItemComments(Integer itemId) {
        return this.commentsService.getItemComments(itemId);
    }

}
