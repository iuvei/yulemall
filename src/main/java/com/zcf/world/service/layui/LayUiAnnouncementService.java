package com.zcf.world.service.layui;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.mapper.AnnouncementMapper;
import com.zcf.world.pojo.Announcement;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/13
*/
@Service
public class LayUiAnnouncementService{

    @Resource
    private AnnouncementMapper LayUiAnnouncementMapper;

    /**
    *新增数据
    */
    public boolean add(Announcement announcement) {
        return this.LayUiAnnouncementMapper.insert(announcement) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        return this.LayUiAnnouncementMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Announcement announcement) {
        return this.LayUiAnnouncementMapper.updateByPrimaryKeySelective(announcement) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Announcement> list = this.LayUiAnnouncementMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Announcement.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Announcement> list = this.LayUiAnnouncementMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
