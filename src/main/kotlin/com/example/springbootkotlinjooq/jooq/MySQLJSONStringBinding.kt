package com.example.springbootkotlinjooq.jooq

import org.jooq.*
import org.jooq.conf.ParamType
import org.jooq.impl.DSL

import java.sql.SQLException
import java.sql.SQLFeatureNotSupportedException
import java.sql.Types
import java.util.Objects

class MySQLJSONStringBinding : Binding<Any, String> {

    override fun converter(): Converter<Any, String> {
        return object : Converter<Any, String> {
            override fun from(databaseObject: Any?): String {
                return if (databaseObject == null) "" else databaseObject as String
            }

            override fun to(userObject: String): Any {
                return userObject
            }

            override fun fromType(): Class<Any> {
                return Any::class.java
            }

            override fun toType(): Class<String> {
                return String::class.java
            }
        }
    }

    @Throws(SQLException::class)
    override fun sql(ctx: BindingSQLContext<String>) {
        if (ctx.render().paramType() == ParamType.INLINED) {
            ctx.render().visit(DSL.inline(ctx.convert(converter()).value())).sql("")
        } else {
            ctx.render().sql("?")
        }
    }

    @Throws(SQLException::class)
    override fun register(ctx: BindingRegisterContext<String>) {
        ctx.statement().registerOutParameter(ctx.index(), Types.VARCHAR)
    }

    @Throws(SQLException::class)
    override fun set(ctx: BindingSetStatementContext<String>) {
        ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null))
    }

    @Throws(SQLException::class)
    override fun set(ctx: BindingSetSQLOutputContext<String>) {
        throw SQLFeatureNotSupportedException()
    }

    @Throws(SQLException::class)
    override fun get(ctx: BindingGetResultSetContext<String>) {
        ctx.convert(converter()).value(ctx.resultSet().getString(ctx.index()))
    }

    @Throws(SQLException::class)
    override fun get(ctx: BindingGetStatementContext<String>) {
        ctx.convert(converter()).value(ctx.statement().getString(ctx.index()))
    }

    @Throws(SQLException::class)
    override fun get(ctx: BindingGetSQLInputContext<String>) {
        throw SQLFeatureNotSupportedException()
    }
}
