package com.zcf.world.service;

import com.zcf.world.pojo.Announcement;
import com.zcf.world.mapper.AnnouncementMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/13
*/
@Service
public class AnnouncementService{

    @Autowired
    private AnnouncementMapper announcementmapper;

    /**
     * 新增一条数据
     *
     * @param announcement announcement对象
     */
    public void addAnnouncement(Announcement announcement) {
        int count = this.announcementmapper.insertSelective(announcement);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteAnnouncementById(Integer id) {
        int count = this.announcementmapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param announcement announcement对象
     */
    public void updateAnnouncement(Announcement announcement) {
        int count = this.announcementmapper.updateByPrimaryKeySelective(announcement);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Announcement对象集合
     */
    public List<Announcement> getAllAnnouncement() {
        List<Announcement> list = this.announcementmapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Announcement对象
     */
    public Announcement getAnnouncement(Integer id){
        Announcement Announcement = this.announcementmapper.selectByPrimaryKey(id);
        if (Announcement == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Announcement;
    }

}
