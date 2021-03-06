package com.zcf.world.service.layui;

import com.zcf.world.pojo.GameRule;
import com.zcf.world.mapper.GameRuleMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class LayUiGameRuleService{

    @Resource
    private GameRuleMapper LayUiGameRuleMapper;

    /**
    *新增数据
    */
    public boolean add(GameRule gameRule) {
        return this.LayUiGameRuleMapper.insert(gameRule) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        return this.LayUiGameRuleMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(GameRule gameRule) {
        return this.LayUiGameRuleMapper.updateByPrimaryKeySelective(gameRule) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<GameRule> list = this.LayUiGameRuleMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(GameRule.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<GameRule> list = this.LayUiGameRuleMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
