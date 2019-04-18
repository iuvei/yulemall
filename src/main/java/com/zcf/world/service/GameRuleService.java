package com.zcf.world.service;

import com.zcf.world.pojo.GameRule;
import com.zcf.world.mapper.GameRuleMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;

import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class GameRuleService{

    @Resource
    private GameRuleMapper gameRulemapper;

    /**
     * 新增一条数据
     *
     * @param gameRule gameRule对象
     */
    public void addGameRule(GameRule gameRule) {
        int count = this.gameRulemapper.insertSelective(gameRule);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteGameRuleById(Integer id) {
        int count = this.gameRulemapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param gameRule gameRule对象
     */
    public void updateGameRule(GameRule gameRule) {
        int count = this.gameRulemapper.updateByPrimaryKeySelective(gameRule);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return GameRule对象集合
     */
    public List<GameRule> getAllGameRule() {
        List<GameRule> list = this.gameRulemapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return GameRule对象
     */
    public GameRule getGameRule(Integer id){
        GameRule GameRule = this.gameRulemapper.selectByPrimaryKey(id);
        if (GameRule == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return GameRule;
    }

}
