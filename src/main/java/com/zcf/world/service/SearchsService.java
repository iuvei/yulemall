package com.zcf.world.service;

import com.github.pagehelper.PageHelper;
import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Searchs;
import com.zcf.world.mapper.SearchsMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/19
*/
@Service
public class SearchsService{

    @Resource
    private SearchsMapper searchsmapper;

    /**
     * 新增一条数据
     *
     * @param searchs searchs对象
     */
    public void addSearchs(Searchs searchs) {
        if (searchs.getCreatTime() == null){
            searchs.setCreatTime(new Date());
        }
        if (searchs.getUpdateTime() == null){
            searchs.setUpdateTime(new Date());
        }
        searchs.setDeleted("N");
        int count = this.searchsmapper.insertSelective(searchs);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteSearchsById(Integer id) {

        Example example = new Example(Searchs.class);
        example.createCriteria().andEqualTo("id",id);
        List<Searchs> list = this.searchsmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Searchs searchs = new Searchs();
        searchs.setId(list.get(0).getId());
        searchs.setDeleted("Y");
        searchs.setUpdateTime(new Date());
        updateSearchs(searchs);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param searchs searchs对象
     */
    public void updateSearchs(Searchs searchs) {
        searchs.setUpdateTime(new Date());
        int count = this.searchsmapper.updateByPrimaryKeySelective(searchs);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Searchs对象集合
     */
    public List<Searchs> getAllSearchs() {
        Example example = new Example(Searchs.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Searchs> list = this.searchsmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Searchs对象
     */
    public Searchs getSearchs(Integer id){
        Searchs Searchs = this.searchsmapper.selectByPrimaryKey(id);
        if (Searchs == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Searchs;
    }

    /**
     * 根据用户id  查询搜索记录  取前五
     */
    public List<Searchs> getUserSearchs(Integer userId){
        PageHelper.startPage(1,5);
        Example example = new Example(Searchs.class);
        example.createCriteria().andEqualTo("deleted","N")
                .andEqualTo("userId",userId);
        example.setOrderByClause("creat_time desc");
        List<Searchs> list = this.searchsmapper.selectByExample(example);
        return list;
    }

    /**
     * 根据用户ID清除最近搜索
     */
    public Body updateUserSearchs(Integer userId){
        int i = searchsmapper.updateUserSearchs(userId);
        if (i>0){
            return Body.newInstance(200,"清除成功");
        }else {
            return Body.newInstance(100,"清除失败，请联系管理员");
        }
    }

}
