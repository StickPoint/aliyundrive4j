package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.aliyun.DriveItemEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;

import java.util.List;

/**
 * description: IAliyunDriveDriveService
 *
 * @ClassName : IAliyunDriveDriveService
 * @Date 2022/12/30 10:29
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service
 */
public interface IAliyunDriveDriveService {

    /**
     * 查询所有网盘信息
     * POST访问的是这个接口：https://api.aliyundrive.com/v2/drive/list
     * @param ownerId 传入一个ownerId
     * @return 返回一个网盘集合
     */
    BaseResponseEntity<List<DriveItemEntity>> getDriveList(String ownerId);

    /**
     * 查询所有网盘信息,本质上与上面的接口方法一样，目前暂未发现其他区别
     * POST访问的是这个接口：https://api.aliyundrive.com/v2/drive/list_my_drives
     * @param ownerId 传入一个ownerId
     * @return 返回一个网盘集合
     */
    BaseResponseEntity<DriveItemEntity> getDriveListOfUser(String ownerId);

    /**
     * 默认 drive(网盘)
     * Post请求地址：https://api.aliyundrive.com/v2/drive/get_default_drive
     * 携带token信息直接请求这个接口地址
     * @return 返回的是一个默认的网盘
     */
    BaseResponseEntity<DriveItemEntity> getDefaultDrive();

    /**
     * 根据网盘id查询网盘信息
     * POST请求地址：https://api.aliyundrive.com/v2/drive/get
     * @param driveId 传入一个网盘id
     * @return 返回一个网盘信息
     */
    BaseResponseEntity<DriveItemEntity> getDriveInfoByDriveId(String driveId);


}