package com.akk.vhr.mapper;

import com.akk.vhr.model.Position;
import com.akk.vhr.model.RespBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPostions();

    Integer deletePositionsByIds(@Param("ids") Integer[] ids);
}