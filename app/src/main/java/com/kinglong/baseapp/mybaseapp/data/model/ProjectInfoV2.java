package com.kinglong.baseapp.mybaseapp.data.model;

//import com.activeandroid.Model;
//import com.activeandroid.annotation.Column;
//import com.fasterxml.jackson.annotation.SerializedName;
//import com.nd.hy.android.educloud.constants.DBColumn;

//import com.fasterxml.jackson.annotation.SerializedName;

import com.google.gson.annotations.SerializedName;

import com.kinglong.baseapp.mybaseapp.db.DbFlowDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;


@Table(database = DbFlowDataBase.class)
public class ProjectInfoV2 extends BaseModel implements Serializable {

    @Column
    @PrimaryKey(autoincrement = true)
    long did;


    @Column(name = "projectId")
     String projectId;
    @Column
    @SerializedName("ShowFourWords")
     boolean isShowFourWords;
    @Column
    @SerializedName("Title")
     String title;
    @SerializedName("Domain")
    @Column
     String domain;
    @SerializedName("UserProtocolUrl")
    @Column
     String userProtocolUrl;
    @SerializedName("CanReg")
    @Column
     boolean canReg;
    @SerializedName("RegAccountType")
    @Column
     String regAccountType;
    @SerializedName("DefaultRegAccountType")
    @Column
     String defaultRegAccountType;
//    @SerializedName("Modules")
//    @Column(isJsonText = true, collection = ArrayList.class, element = ProjectModuleV2.class)
//    private List<ProjectModuleV2> moduleList;
//    @Column(isJsonText = true, collection = ArrayList.class, element = SubRank.class)
//    @SerializedName("Ranks")
//    private List<SubRank> rankList;
//    @Column
//    @SerializedName("ShowWeather")
//    private boolean isShowWeather;
//    @Column(isJsonText = true)
//    @SerializedName("Weather")
//    private Weather weather;

    @SerializedName("AppTemplateTopType")
    @Column
     int appTemplateTopType;//首页上方区域显示类型 0 学习计划 ，1 推荐文章

    @SerializedName("AppFooter")
    @Column
     String appFooter;//组织信息


    @SerializedName("ShowCourseHours")
    @Column
     boolean showCourseHours;//课程是否显示学时

    @SerializedName("ShowCoursePoint")
    @Column
     boolean showCoursePoint;//课程是否显示积分


    @SerializedName("ShowCourseRequireAndOptions")
    @Column
     boolean showCourseRequireAndOptions;//课程是否显示必修选修

    @SerializedName("ShowCourseStudyCount")
    @Column
     boolean showCourseStudyCount;//课程是否显示学习人数


    @SerializedName("ProjectEnable")
    @Column
     boolean projectEnable;//课程是否显示学习人数

    public boolean isProjectEnable() {
        return projectEnable;
    }

    public void setProjectEnable(boolean projectEnable) {
        this.projectEnable = projectEnable;
    }

    public void setIsShowFourWords(boolean isShowFourWords) {
        this.isShowFourWords = isShowFourWords;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setUserProtocolUrl(String userProtocolUrl) {
        this.userProtocolUrl = userProtocolUrl;
    }

//    public void setModuleList(List<ProjectModuleV2> moduleList) {
//        this.moduleList = moduleList;
//    }
//
//    public void setRankList(List<SubRank> rankList) {
//        this.rankList = rankList;
//    }
//
//    public void setIsShowWeather(boolean isShowWeather) {
//        this.isShowWeather = isShowWeather;
//    }
//
//    public void setWeather(Weather weather) {
//        this.weather = weather;
//    }

    public boolean isShowCourseHours() {
        return showCourseHours;
    }

    public void setShowCourseHours(boolean showCourseHours) {
        this.showCourseHours = showCourseHours;
    }

    public boolean isShowCoursePoint() {
        return showCoursePoint;
    }

    public void setShowCoursePoint(boolean showCoursePoint) {
        this.showCoursePoint = showCoursePoint;
    }

    public boolean isShowCourseRequireAndOptions() {
        return showCourseRequireAndOptions;
    }

    public void setShowCourseRequireAndOptions(boolean showCourseRequireAndOptions) {
        this.showCourseRequireAndOptions = showCourseRequireAndOptions;
    }

    public boolean isShowCourseStudyCount() {
        return showCourseStudyCount;
    }

    public void setShowCourseStudyCount(boolean showCourseStudyCount) {
        this.showCourseStudyCount = showCourseStudyCount;
    }

    public String getAppFooter() {
        return appFooter;
    }

    public void setAppFooter(String appFooter) {
        this.appFooter = appFooter;
    }

    public int getAppTemplateTopType() {
        return appTemplateTopType;
    }

    public void setAppTemplateTopType(int appTemplateTopType) {
        this.appTemplateTopType = appTemplateTopType;
    }

    public boolean isShowFourWords() {
        return isShowFourWords;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getUserProtocolUrl() {
        return userProtocolUrl;
    }

    public boolean isCanReg() {
        return canReg;
    }

    public String getRegAccountType() {
        return regAccountType;
    }

    public String getDefaultRegAccountType() {
        return defaultRegAccountType;
    }

//    public List<ProjectModuleV2> getModuleList() {
//        return moduleList;
//    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCanReg(boolean canReg) {
        this.canReg = canReg;
    }

    public void setRegAccountType(String regAccountType) {
        this.regAccountType = regAccountType;
    }

    public void setDefaultRegAccountType(String defaultRegAccountType) {
        this.defaultRegAccountType = defaultRegAccountType;
    }

//    public List<SubRank> getRankList() {
//        return rankList;
//    }
//
//    public boolean isShowWeather() {
//        return isShowWeather;
//    }
//
//    public Weather getWeather() {
//        return weather;
//    }
}
