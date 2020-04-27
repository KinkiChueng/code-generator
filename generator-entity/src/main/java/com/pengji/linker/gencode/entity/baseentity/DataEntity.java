package com.pengji.linker.gencode.entity.baseentity;

/**
 * Created by lasia on 2020/4/22.
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pengji.linker.gencode.entity.SortableField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据Entity类
 * @author dell
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    protected String remarks; // 备注
//    protected User createBy; // 创建者
    protected Date createDate; // 创建日期
//    protected User updateBy; // 更新者
    protected Date updateDate; // 更新日期
    protected String enableFlag; // 删除标记（0：正常；1：删除；2：审核）
//    protected TMember createBy2; // 创建者
//    protected TMember updateBy2; // 更新者
    private String createByUserID;
    private String createrImg;
    private String createByName;
    private String content;
    private String likes = "0";
    private String comments = "0";
    private String forwards = "0";
    private String delFlag = "0";

    /**
     *  产品序列号 提入公有父类 lasia 20191121
     */
    private String productSerialNumber;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    private List<Object> batchList;    //用来操作批量处理

    public String getCreateByUserID() {
        return createByUserID;
    }

    public void setCreateByUserID(String createByUserID) {
        this.createByUserID = createByUserID;
    }

    public String getCreaterImg() {
        return createrImg;
    }

    public void setCreaterImg(String createrImg) {
        this.createrImg = createrImg;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
        this.enableFlag = ENABLE_FLAG_NORMAL;
    }

    public DataEntity(String id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    /*
     * @Override public void preInsert() { //
     * 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
     *
     * if (!this.isNewRecord){ setId(IdGen.uuid()); } User user =
     * UserUtils.getUser(); if (StringUtils.isNotBlank(user.getId())){
     * this.updateBy = user; this.createBy = user; }
     *
     * this.updateDate = new Date(); this.createDate = this.updateDate; }
     */

    /**
     * 更新之前执行方法，需要手动调用
     */
    /*
     * @Override public void preUpdate() {
     *
     * User user = UserUtils.getUser(); if
     * (StringUtils.isNotBlank(user.getId())){ this.updateBy = user; }
     *
     * this.updateDate = new Date(); }
     */
    @Length(min = 0, max = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

//    @JsonIgnore
//    public User getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(User createBy) {
//        this.createBy = createBy;
//    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

//    @JsonIgnore
//    public User getUpdateBy() {
//        return updateBy;
//    }
//
//    public void setUpdateBy(User updateBy) {
//        this.updateBy = updateBy;
//    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }
//
//    public TMember getCreateBy2() {
//        return createBy2;
//    }
//
//    public void setCreateBy2(TMember createBy2) {
//        this.createBy2 = createBy2;
//    }
//
//    public TMember getUpdateBy2() {
//        return updateBy2;
//    }
//
//    public void setUpdateBy2(TMember updateBy2) {
//        this.updateBy2 = updateBy2;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getForwards() {
        return forwards;
    }

    public void setForwards(String forwards) {
        this.forwards = forwards;
    }

    public List<Object> getBatchList() {
        if (batchList == null) {
            batchList = new ArrayList<Object>();
        }
        return batchList;
    }

    public void setBatchList(List<Object> batchList) {
        this.batchList = batchList;
    }


    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    public void setProductSerialNumber(String productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }

    private Class<T> entity;

    public List<SortableField> init() {
        List<SortableField> list = new ArrayList<SortableField>();
        /**getClass().getGenericSuperclass()返回表示此 Class 所表示的实体（类、接口、基本类型或 void）
         * 的直接超类的 Type(Class<T>泛型中的类型)，然后将其转换ParameterizedType。。
         *  getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
         *  [0]就是这个数组中第一个了。。
         *  简而言之就是获得超类的泛型参数的实际类型。。*/
        entity = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
//      FieldMeta filed = entity.getAnnotation(FieldMeta.class);

        if (this.entity != null) {

            /**返回类中所有字段，包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段
             * entity.getFields();只返回对象所表示的类或接口的所有可访问公共字段
             * 在class中getDeclared**()方法返回的都是所有访问权限的字段、方法等；
             * 可看API
             * */
            Field[] fields = entity.getDeclaredFields();

            /*for(Field f : fields){
                //获取字段中包含fieldMeta的注解
            	NotNull meta = f.getAnnotation(NotNull.class);
                if(meta!=null){
                    SortableField sf = new SortableField(meta, f);
                    list.add(sf);
                }
            }*/

            //返回对象所表示的类或接口的所有可访问公共方法
            Method[] methods = entity.getMethods();

            for (Method m : methods) {
                NotNull meta = m.getAnnotation(NotNull.class);
                if (meta != null) {
                    SortableField sf = new SortableField(meta, m.getName(), m.getReturnType());
                    list.add(sf);
                }
            }

            //这种方法是新建FieldSortCom类实现Comparator接口，来重写compare方法实现排序
//          Collections.sort(list, new FieldSortCom());
           /* Collections.sort(list, new Comparator<SortableField>() {
                @Override
                public int compare(SortableField s1,SortableField s2) {
                    return s1.getMeta().order()-s2.getMeta().order();
//                  return s1.getName().compareTo(s2.getName());//也可以用compare来比较
                }

            });  */
        }
        return list;

    }


}
