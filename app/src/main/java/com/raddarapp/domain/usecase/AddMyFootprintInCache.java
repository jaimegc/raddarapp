package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyFootprintsRepository;
import com.raddarapp.domain.model.MyFootprint;

import javax.inject.Inject;

public class AddMyFootprintInCache extends RosieUseCase {

    public static final String USE_CASE_ADD_MY_FOOTPRINT_IN_CACHE_FROM_NOTIFICATION = "addMyFootprintInCacheFromNotification";
    public static final String USE_CASE_ADD_MY_FOOTPRINT_IN_CACHE_FROM_NOTIFICATION_FOREGROUND = "addMyFootprintInCacheFromNotificationForeground";

    private final MyFootprintsRepository myFootprintsRepository;

    @Inject
    public AddMyFootprintInCache(MyFootprintsRepository myFootprintsRepository) {
        this.myFootprintsRepository = myFootprintsRepository;
    }

    @UseCase(name = USE_CASE_ADD_MY_FOOTPRINT_IN_CACHE_FROM_NOTIFICATION)
    public void addMyFootprintFromNotification(String content) throws Exception {
        MyFootprint myFootprint = myFootprintsRepository.addMyFootprintInCacheFromNotification(content);
        notifySuccess(myFootprint);
    }

    @UseCase(name = USE_CASE_ADD_MY_FOOTPRINT_IN_CACHE_FROM_NOTIFICATION_FOREGROUND)
    public void addMyFootprintInCacheFromNotificationForeground(String content) throws Exception {
        MyFootprint myFootprint = myFootprintsRepository.addMyFootprintInCacheFromNotificationForeground(content);
        notifySuccess(myFootprint);
    }
}
