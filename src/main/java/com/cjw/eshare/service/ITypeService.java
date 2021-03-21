package com.cjw.eshare.service;

import com.cjw.eshare.entity.Type;
import com.cjw.eshare.model.CRModel;

import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/6 23:09
 */
public interface ITypeService {
    CRModel createType(String typeName);

    /**
     * 获取全部类型
     * @return
     */
    List<Type> getAllType();

    /**
     * 删除类型
     * @param id
     * @return
     */
    CRModel deleteType(Integer id);

    /**
     * 更新文件名称
     * @param id
     * @param newTypeName
     * @return
     */
    CRModel updateType(Integer id, String newTypeName);
}
