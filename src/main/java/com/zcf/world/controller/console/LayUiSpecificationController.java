package com.zcf.world.controller.console;

import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.common.utils.FileUploadUtils;
import com.zcf.world.pojo.Specification;
import com.zcf.world.service.layui.LayUiSpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
/**
* @author 许宝予
* @date 2019/04/22
*/
@RestController
@RequestMapping("/specification")
public class LayUiSpecificationController {

    @Autowired
    private LayUiSpecificationService LayUispecificationservice;

    @RequestMapping(value ="add",produces = {"application/json;charset=UTF-8"})
    public boolean add(@RequestBody Specification specification) {
        return this.LayUispecificationservice.add(specification);
    }

    @RequestMapping(value ="delete",produces = {"application/json;charset=UTF-8"})
    public boolean delete(@RequestParam Integer id) {
        return this.LayUispecificationservice.delete(id);
    }

    @RequestMapping(value ="update",produces = {"application/json;charset=UTF-8"})
    public boolean update(@RequestBody Specification specification) {
        return this.LayUispecificationservice.update(specification);
    }

    @RequestMapping(value ="query",produces = {"application/json;charset=UTF-8"})
    public LayUiResult querySpecification(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer limit) {
        return this.LayUispecificationservice.query(page,limit);
    }

    @RequestMapping(value ="search",produces = {"application/json;charset=UTF-8"})
    public LayUiResult search(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer limit,
        @RequestParam String keywords) {
        return this.LayUispecificationservice.search(page,limit,keywords);
    }

    @RequestMapping(value ="upload",produces = {"application/json;charset=UTF-8"})
    public Map UploadBrand(@RequestParam("file") MultipartFile file){
        return FileUploadUtils.uploadLayUiImg(file,"img/");
    }
}
