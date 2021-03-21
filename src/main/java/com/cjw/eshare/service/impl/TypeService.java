package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.TypeDao;
import com.cjw.eshare.entity.Type;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/6 23:13
 */
@Service
public class
TypeService implements ITypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 创建类型
     * @param typeName
     * @return
     */
    @Override
    public CRModel createType(String typeName) {
        if(!isExitType(typeName)) {
            Type type = new Type();
            type.setName(typeName);
            type.setCreate_at(new Date());
            type.setUpdate_at(new Date());
            typeDao.createType(type);
            return CRModel.success(SuccessDescription.CREATE_TYPE_SUCCESS);
        } else {
            return CRModel.error(ErrorDescription.CREATE_TYPE_ERR);
        }
    }

    /**
     * 判断类型是否存在
     * @param typeName
     * @return
     */
    public boolean isExitType(String typeName) {
        return typeDao.findByName(typeName) == null ? false : true;
    }

    /**
     * 获取全部类型
     * @return
     */
    @Override
    public List<Type> getAllType() {
        List<Type> typeList = new ArrayList<>();
        typeList = typeDao.findAllType();
        return typeList;
    }

    /**
     * 删除文件类型
     * @param id
     * @return
     */
    @Override
    public CRModel deleteType(Integer id) {
        if (null != typeDao.findById(id)) {
            typeDao.deleteType(id);
            return CRModel.success(SuccessDescription.DEL_TYPE_SUCCESS);
        } else {
            return CRModel.error(ErrorDescription.DEL_TYPE_ERR);
        }
    }

    /**
     * 更新文件名称
     * @param id
     * @param newTypeName
     * @return
     */
    @Override
    public CRModel updateType(Integer id, String newTypeName) {
        if (null != typeDao.findById(id)) {
            if (isExitType(newTypeName)) {
                return CRModel.error(ErrorDescription.UPDATE_TYPE_ERR1);
            }
            typeDao.updateTypeById(new Type(id, newTypeName, null, new Date() ));
            return CRModel.success(SuccessDescription.UPDATE_TYPE_SUCCESS);
        } else {
            return CRModel.error(ErrorDescription.UPDATE_TYPE_ERR);
        }
    }
}
