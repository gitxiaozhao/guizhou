package com.rainier.mapper;

import com.rainier.model.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelMapper {
    void addLabel(Label label);

    Label getLabelByName(Label label);

    void updateLabel(Label label);

    Integer getLabelCount(@Param("name") String name);

    List<Label> getLabelList(@Param("name")String name, @Param("pageState") Integer pageState, @Param("pageSize")Integer pageSize);

    void deleteLabel(@Param("labelids") List labelids);

    List<Label> getAllLabel();

    Label getLabelById(@Param("labelid")Integer labelid);
}
