package com.github.jingou.common.mybatis.ext.handler;

import com.github.jingou.common.CodeDesc;
import com.github.jingou.common.exception.bad400.ParameterInvalidException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author caedmon
 */
public class AutoCodeDescEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private BaseTypeHandler typeHandler = null;

    public AutoCodeDescEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new ParameterInvalidException("class type can not be null");
        }
        if (CodeDesc.class.isAssignableFrom(type)) {
            typeHandler = new CodeDescEnumTypeHandler(type);
        } else {
            typeHandler = new EnumTypeHandler<>(type);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        typeHandler.setNonNullParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return (E) typeHandler.getNullableResult(rs, columnName);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return (E) typeHandler.getNullableResult(rs, columnIndex);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return (E) typeHandler.getNullableResult(cs, columnIndex);
    }
}
