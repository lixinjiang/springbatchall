package cn.bonusli.batch.entity;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author lxj
 */
@Getter
@Setter
public class TestData {
    private int id;
    private String field1;
    private String field2;
    @NotBlank
    private String field3;

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
