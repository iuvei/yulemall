package com.zcf.world.service;

import com.zcf.world.DTO.GuanYaHeDTO;
import com.zcf.world.DTO.LiangMianDTO;
import com.zcf.world.DTO.LiuShiDTO;
import com.zcf.world.DTO.YiWuDTO;
import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.PlayOptions;
import com.zcf.world.mapper.PlayOptionsMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 许宝予
 * @date 2019/04/24
 */
@Service
public class PlayOptionsService {

    @Resource
    private PlayOptionsMapper playOptionsmapper;

    /**
     * 新增一条数据
     *
     * @param playOptions playOptions对象
     */
    public void addPlayOptions(PlayOptions playOptions) {
        if (playOptions.getCreatTime() == null) {
            playOptions.setCreatTime(new Date());
        }
        if (playOptions.getUpdateTime() == null) {
            playOptions.setUpdateTime(new Date());
        }
        playOptions.setDeleted("N");
        int count = this.playOptionsmapper.insertSelective(playOptions);
        if (count != 1) {
            throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deletePlayOptionsById(Integer id) {

        Example example = new Example(PlayOptions.class);
        example.createCriteria().andEqualTo("id", id);
        List<PlayOptions> list = this.playOptionsmapper.selectByExample(example);
        if (list.size() != 1) {
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        PlayOptions playOptions = new PlayOptions();
        playOptions.setId(list.get(0).getId());
        playOptions.setDeleted("Y");
        playOptions.setUpdateTime(new Date());
        updatePlayOptions(playOptions);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param playOptions playOptions对象
     */
    public void updatePlayOptions(PlayOptions playOptions) {
        playOptions.setUpdateTime(new Date());
        int count = this.playOptionsmapper.updateByPrimaryKeySelective(playOptions);
        if (count != 1) {
            throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return PlayOptions对象集合
     */
    public List<PlayOptions> getAllPlayOptions() {
        Example example = new Example(PlayOptions.class);
        example.createCriteria().andEqualTo("deleted", "N");
        List<PlayOptions> list = this.playOptionsmapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return PlayOptions对象
     */
    public PlayOptions getPlayOptions(Integer id) {
        PlayOptions PlayOptions = this.playOptionsmapper.selectByPrimaryKey(id);
        if (PlayOptions == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return PlayOptions;
    }

    /**
     * 根据types查询游戏基本信息
     */
    public Body getTypesPlay(String types) {
        Example example = new Example(PlayOptions.class);
        example.createCriteria().andEqualTo("deleted", "N")
                .andEqualTo("types", types);
        List<PlayOptions> list = this.playOptionsmapper.selectByExample(example);
        Map<String, Object> map = new HashMap<>();
        LiangMianDTO liangMianDTO = new LiangMianDTO();
        YiWuDTO yiWuDTO = new YiWuDTO();
        LiuShiDTO liuShiDTO = new LiuShiDTO();
        GuanYaHeDTO guanYaHeDTO = new GuanYaHeDTO();
        for (int i = 0; i < list.size(); i++) {
            if (types.equals("1")){
                List list1 = new ArrayList();
                if (list.get(i).getTitles().equals("冠亚和")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("冠军")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("亚军")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第三名")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第四名")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第五名")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第六名")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第七名")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第八名")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第九名")) {
                    list1.add(list.get(i));
                } else if (list.get(i).getTitles().equals("第十名  ")) {
                    list1.add(list.get(i));
                }
                liangMianDTO.setShi(list1);
                map.put("liangMianDTO",liangMianDTO);
            }else if (types.equals("2")){
                if (list.get(i).getTitles().equals("冠军")) {
                    List list2 = new ArrayList();
                    list2.add(list.get(i));
                    yiWuDTO.setGJun(list2);
                } else if (list.get(i).getTitles().equals("亚军")) {
                    List list3 = new ArrayList();
                    list3.add(list.get(i));
                    yiWuDTO.setYJun(list3);
                } else if (list.get(i).getTitles().equals("第三名")) {
                    List list4 = new ArrayList();
                    list4.add(list.get(i));
                    yiWuDTO.setSan(list4);
                } else if (list.get(i).getTitles().equals("第四名")) {
                    List list5 = new ArrayList();
                    list5.add(list.get(i));
                    yiWuDTO.setSi(list5);
                } else if (list.get(i).getTitles().equals("第五名")) {
                    List list6 = new ArrayList();
                    list6.add(list.get(i));
                    yiWuDTO.setWu(list6);
                }
                map.put("yiWuDTO",yiWuDTO);
            }else if (types.equals("3")){
                if (list.get(i).getTitles().equals("第六名")) {
                    List list3 = new ArrayList();
                    list3.add(list.get(i));
                    liuShiDTO.setLiu(list3);
                } else if (list.get(i).getTitles().equals("第七名")) {
                    List list3 = new ArrayList();
                    list3.add(list.get(i));
                    liuShiDTO.setQi(list3);
                } else if (list.get(i).getTitles().equals("第八名")) {
                    List list3 = new ArrayList();
                    list3.add(list.get(i));
                    liuShiDTO.setBa(list3);
                } else if (list.get(i).getTitles().equals("第九名")) {
                    List list3 = new ArrayList();
                    list3.add(list.get(i));
                    liuShiDTO.setJiu(list3);
                } else if (list.get(i).getTitles().equals("第十名  ")) {
                    List list3 = new ArrayList();
                    list3.add(list.get(i));
                    liuShiDTO.setShi(list3);
                }
                map.put("liuShiDTO",liuShiDTO);
            }else if (types.equals("4")){
                if (list.get(i).getTitles().equals("冠亚和")) {
                    List list2 = new ArrayList();
                    list2.add(list.get(i));
                    guanYaHeDTO.setGyaHe(list2);
                }
                map.put("guanYaHeDTO",guanYaHeDTO);
            }
        }
        return Body.newInstance(map);
    }


}
