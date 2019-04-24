package com.zcf.world.controller;

import com.zcf.world.service.SignRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerController {

    @Autowired
    private SignRecordService signRecordService;

    @Scheduled(cron = "0 1 0 1 * ? ")
    public void oumeione() {
        //获取当前时间
        /**
         * 设置每月 一号  00 01 00 自动调用
         */
        signRecordService.updateDayOrMonth();
    }

    /**
     * 每天 23:58 分执行
     */
    @Scheduled(cron = "0 58 23 * * ?")
    public void updateTypeDQ() {
        //获取当前时间
        /**
         * 每天 23:58 分执行
         */
        signRecordService.updateTypeDQ();
    }
}