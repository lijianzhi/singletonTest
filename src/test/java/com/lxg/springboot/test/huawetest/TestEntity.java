package com.lxg.springboot.test.huawetest;

/**
 * @ClassName TestEntity
 * @Description: TODO
 * @Author 77984
 * @Date 2021/2/5
 * @Version V1.0
 **/
public class TestEntity {
    private String scalingIds;

    public TestEntity(String scalingIds) {
        this.scalingIds = scalingIds;
    }

    public String getScalingIds() {
        return scalingIds;
    }

    public void setScalingIds(String scalingIds) {
        this.scalingIds = scalingIds;
    }

    public TestEntity() {
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "scalingIds='" + scalingIds + '\'' +
                '}';
    }
}
