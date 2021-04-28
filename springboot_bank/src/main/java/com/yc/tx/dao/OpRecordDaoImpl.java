package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 19:55
 */
@Repository
public class OpRecordDaoImpl implements OpRecordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord values(null,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, opRecord.getAccountid(),
                opRecord.getOpmoney(), opRecord.getOptime(), opRecord.getOptype(), opRecord.getTransferid());
        return result;
    }

    @Override
    public List<OpRecord> findAll() {
        String sql = "select * from oprecord ";
        List<OpRecord> list = this.jdbcTemplate.query(sql, (rs, num) -> {
            OpRecord o = new OpRecord();
            o.setId(rs.getInt("id"));
            o.setAccountid(rs.getInt("accountid"));
            o.setOptype(rs.getString("optype"));
            o.setOpmoney(rs.getDouble("opmoney"));
            o.setOptime(rs.getTimestamp("optime"));
            o.setTransferid(rs.getString("transferid"));
            return o;
        });
        return list;
    }

    @Override
    public List<OpRecord> findByAccountId(int accountid) {
        String sql = "select * from oprecord where accountid=? ";
        List<OpRecord> list = this.jdbcTemplate.query(sql, (rs, num) -> {
            OpRecord o = new OpRecord();
            o.setId(rs.getInt("id"));
            o.setAccountid(rs.getInt("accountid"));
            o.setOptype(rs.getString("optype"));
            o.setOpmoney(rs.getDouble("opmoney"));
            o.setOptime(rs.getTimestamp("optime"));
            o.setTransferid(rs.getString("transferid"));
            return o;
        }, accountid);
        return list;
    }

}
