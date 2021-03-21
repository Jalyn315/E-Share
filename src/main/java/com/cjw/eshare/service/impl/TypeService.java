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

import java.util.Date;

/**
 * @author cj.w
 * @date 2021/1/6 23:13
 */
@Service
public class
TypeService implements ITypeService {

    @Autowired
    private TypeDao typeDao;


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



    public boolean isExitType(String typeName) {
        return typeDao.findByName(typeName) == null ? false : true;
    }

}
