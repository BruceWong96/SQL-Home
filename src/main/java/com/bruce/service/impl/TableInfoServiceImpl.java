package com.bruce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bruce.mapper.TableInfoMapper;
import com.bruce.model.entity.TableInfo;
import com.bruce.service.TableInfoService;
import com.google.gson.Gson;
import lombok.var;
import org.springframework.stereotype.Service;

/**
 * @Author Bruce Wong
 *
 */
@Service
public class TableInfoServiceImpl extends ServiceImpl<TableInfoMapper, TableInfo> implements TableInfoService {

    private final static Gson gson = new Gson();

    @Override
    public void validAndHandleTableInfo(TableInfo tableInfo, boolean add) {

    }
}
