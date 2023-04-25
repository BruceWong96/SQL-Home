package com.bruce.core.model.vo;

import com.bruce.core.schema.TableSchema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 */
@Data
public class GenerateVO {
    private TableSchema tableSchema;

    private String createSql;

    private List<Map<String, Object>> dataList;

    private String insertSql;

    private String dataJson;

    private String JavaEntityCode;

    private String JavaObjectCode;

    private String typescriptTypeCode;

    private static final long serialVersionUID = 685973833556094288L;
}
