package com.code.generator.utils;

public class EntityUtils {

//    @SuppressWarnings("rawtypes")
//    public static void preInsert(BaseEntity entity) {
//        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
//        if (entity.getIsNewRecord()) {
//            if (EmptyUtil.isNullOrEmpty(entity.getId())) {
//                entity.setId(IdGen.nextSFID());
//            }
//        }
//        // System.out.println("--------getIsNewRecord:" +
//        // entity.getIsNewRecord());
//        User user = UserUtils.getUser();
//        if (StringUtils.isNotBlank(user.getId())) {
//            entity.setUpdateBy(user);
//            entity.setCreateBy(user);
//        }
//        Date d = new Date();
//        entity.setUpdateDate(d);
//        entity.setCreateDate(d);
//    }
//
//    @SuppressWarnings("rawtypes")
//    public static void preUpdate(DataEntity entity) {
//
//        User user = UserUtils.getUser();
//        if (StringUtils.isNotBlank(user.getId())) {
//            entity.setUpdateBy(user);
//        }
//        entity.setUpdateDate(new Date());
//
//    }
}
