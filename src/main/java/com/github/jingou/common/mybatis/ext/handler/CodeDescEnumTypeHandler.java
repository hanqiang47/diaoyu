package com.github.jingou.common.mybatis.ext.handler;

import com.github.jingou.common.CodeDesc;
import com.github.jingou.common.exception.bad400.ParameterInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author caedmon
 * @since 1.0
 */
@Slf4j
public class CodeDescEnumTypeHandler<E extends Enum<?> & CodeDesc> extends BaseTypeHandler<CodeDesc> {

    private Class<E> type;

    public CodeDescEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new ParameterInvalidException("class type can not be null");
        }
        this.type = type;
    }

    private E codeOf(int code) {
        try {
            return CodeDescEnumConvertor.codeOf(type, code);
        } catch (Exception ex) {
            throw new ParameterInvalidException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CodeDesc parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return rs.wasNull() ? null : codeOf(code);
    }

    @Override
    public CodeDesc getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return rs.wasNull() ? null : codeOf(code);
    }

    @Override
    public CodeDesc getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return cs.wasNull() ? null : codeOf(code);
    }
}
