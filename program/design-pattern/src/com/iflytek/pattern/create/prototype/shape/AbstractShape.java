package com.iflytek.pattern.create.prototype.shape;

/**
 * 形状抽象类 可克隆
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 16:21
 */
public abstract class AbstractShape implements Cloneable {
    private String id;
    private String type;

    public abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 重写Object类克隆方法
     * 处理CloneNotSupportedException
     */
    @Override
    public AbstractShape clone() {
        AbstractShape clone = null;
        try {
            clone = (AbstractShape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
