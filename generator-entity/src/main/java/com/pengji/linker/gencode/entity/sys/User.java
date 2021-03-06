package com.pengji.linker.gencode.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.pengji.linker.gencode.entity.annotation.ExcelField;
import com.pengji.linker.gencode.entity.baseentity.DataEntity;
import com.pengji.linker.gencode.entity.cols.SupCol;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 用户Entity
 *
 * @author Michael
 * @version 2013-12-05
 */
public class User extends DataEntity<User> {

    private static final long serialVersionUID = 1L;
    private Office company; // 归属公司
    private Office office; // 归属部门
    private String loginName;// 登录名
    private String password;// 密码
    private String no; // 工号
    private String name; // 姓名
    private String email; // 邮箱
    private String phone; // 手机
    private String mobile; // 电话，座机
    private String userType;// 用户类型
    private String loginIp; // 最后登陆IP
    private Date loginDate; // 最后登陆日期
    private String loginFlag; // 是否允许登陆
    private String photo; // 头像

    private String oldLoginName;// 原登录名
    private String newPassword; // 新密码

    private String oldLoginIp; // 上次登陆IP
    private Date oldLoginDate; // 上次登陆日期
    private String mtype;        //商户类型  boos:供货商  others:加盟商
    private Role role; // 根据角色查询用户条件
    private String referrer;    //推荐人

    private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表
    private String mGuid; // 关联AMbaby账号使用
    private int memberID;// 关联Ambaby账号ID

    private String zxsb;

    // 昵称
    private String nickName;
    // 个性签名
    private String sign;

    private Integer isopenlive = 0;// 是否为直播用户 0:否，1:是
    private String livePassword;// 直播账号密码

    private String userExRole;                // buyer 采购商，supplier 供货商
    private String companyName;                //企业名称

    public User() {
        super();
        this.loginFlag = "1";
    }

    public User(String id) {
        super(id);
    }


    public String getUserExRole() {
        return userExRole;
    }

    public void setUserExRole(String userExRole) {
        this.userExRole = userExRole;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getIsopenlive() {
        return isopenlive;
    }

    public void setIsopenlive(Integer isopenlive) {
        this.isopenlive = isopenlive;
    }

    public String getLivePassword() {
        return livePassword;
    }

    public void setLivePassword(String livePassword) {
        this.livePassword = livePassword;
    }

    public String getZxsb() {
        return zxsb;
    }

    public void setZxsb(String zxsb) {
        this.zxsb = zxsb;
    }

    public User(String id, String loginName) {
        super(id);
        this.loginName = loginName;
    }

    public User(Role role) {
        super();
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    @SupCol(isUnique = "true", isHide = "true")
    @ExcelField(title = "ID", type = 1, align = 2, sort = 1)
    public String getId() {
        return id;
    }

    @JsonIgnore
    @NotNull(message = "归属公司不能为空")
    @ExcelField(title = "归属公司", align = 2, sort = 20)
    public Office getCompany() {
        return company;
    }

    public void setCompany(Office company) {
        this.company = company;
    }

    @JsonIgnore
    @NotNull(message = "归属部门不能为空")
    @ExcelField(title = "归属部门", align = 2, sort = 25)
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Length(min = 1, max = 100, message = "登录名长度必须介于 1 和 100 之间")
    @ExcelField(title = "登录名", align = 2, sort = 30)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @JsonIgnore
    @Length(min = 1, max = 100, message = "密码长度必须介于 1 和 100 之间")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(min = 1, max = 100, message = "姓名长度必须介于 1 和 100 之间")
    @ExcelField(title = "姓名", align = 2, sort = 40)
    public String getName() {
        return name;
    }

    @Length(min = 1, max = 100, message = "工号长度必须介于 1 和 100 之间")
    @ExcelField(title = "工号", align = 2, sort = 45)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Email(message = "邮箱格式不正确")
    @Length(min = 0, max = 200, message = "邮箱长度必须介于 1 和 200 之间")
    @ExcelField(title = "邮箱", align = 1, sort = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 0, max = 200, message = "电话长度必须介于 1 和 200 之间")
    @ExcelField(title = "电话", align = 2, sort = 60)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(min = 0, max = 200, message = "手机长度必须介于 1 和 200 之间")
    @ExcelField(title = "手机", align = 2, sort = 70)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ExcelField(title = "备注", align = 1, sort = 900)
    public String getRemarks() {
        return remarks;
    }

    @Length(min = 0, max = 100, message = "用户类型长度必须介于 1 和 100 之间")
    @ExcelField(title = "用户类型", align = 2, sort = 80, dictType = "sys_user_type")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @ExcelField(title = "创建时间", type = 0, align = 1, sort = 90)
    public Date getCreateDate() {
        return createDate;
    }

    @ExcelField(title = "最后登录IP", type = 1, align = 1, sort = 100)
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelField(title = "最后登录日期", type = 1, align = 1, sort = 110)
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getOldLoginName() {
        return oldLoginName;
    }

    public void setOldLoginName(String oldLoginName) {
        this.oldLoginName = oldLoginName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldLoginIp() {
        if (oldLoginIp == null) {
            return loginIp;
        }
        return oldLoginIp;
    }

    public void setOldLoginIp(String oldLoginIp) {
        this.oldLoginIp = oldLoginIp;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getOldLoginDate() {
        if (oldLoginDate == null) {
            return loginDate;
        }
        return oldLoginDate;
    }

    public void setOldLoginDate(Date oldLoginDate) {
        this.oldLoginDate = oldLoginDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonIgnore
    // @ExcelField(title = "拥有角色", align = 1, sort = 800, fieldType =
    // RoleListType.class)
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @JsonIgnore
    public List<String> getRoleIdList() {
        List<String> roleIdList = Lists.newArrayList();
        for (Role role : roleList) {
            roleIdList.add(role.getId());
        }
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        roleList = Lists.newArrayList();
        for (String roleId : roleIdList) {
            Role role = new Role();
            role.setId(roleId);
            roleList.add(role);
        }
    }

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(String id) {
        return id != null && "1".equals(id);
    }

    @Override
    public String toString() {
        return id;
    }

    public String getmGuid() {
        return mGuid;
    }

    public void setmGuid(String mGuid) {
        this.mGuid = mGuid;
    }

    /**
     * @return the memberID
     */
    public int getMemberID() {
        return memberID;
    }

    /**
     * @param memberID the memberID to set
     */
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}