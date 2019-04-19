package com.zcf.world.service.layui;

import com.zcf.world.pojo.Searchs;
import com.zcf.world.mapper.SearchsMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/19
*/
@Service
public class LayUiSearchsService{

    @Resource
    private SearchsMapper LayUiSearchsMapper;

    /**
    *新增数据
    */
    public boolean add(Searchs searchs) {
        if (searchs.getCreatTime() == null){
            searchs.setCreatTime(new Date());
        }
        if (searchs.getUpdateTime() == null){
            searchs.setUpdateTime(new Date());
        }
        searchs.setDeleted("N");
        return this.LayUiSearchsMapper.insert(searchs) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Searchs.class);
        example.createCriteria().andEqualTo("id",id);
        List<Searchs> list = this.LayUiSearchsMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Searchs searchs = new Searchs();
        searchs.setId(list.get(0).getId());
        searchs.setDeleted("Y");
        searchs.setUpdateTime(new Date());
        return this.update(searchs);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Searchs searchs) {
        searchs.setUpdateTime(new Date());
        return this.LayUiSearchsMapper.updateByPrimaryKeySelective(searchs) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Searchs> list = this.LayUiSearchsMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Searchs.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Searchs> list = this.LayUiSearchsMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
