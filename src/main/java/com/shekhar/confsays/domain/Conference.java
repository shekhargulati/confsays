package com.shekhar.confsays.domain;

import java.util.Date;
import java.util.List;

public class Conference {

    private String id;

    private String title;

    private String bannerImgUrl;

    private String description;

    private List<String> hashtags;

    private Date startDate;

    private Date endDate;

    private String conferenceUrl;

    private boolean track = true;

    private String agendaUrl;
    private String urlFragment;

    private List<String> speakers;

    public Conference() {
    }

    public Conference(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getAgendaUrl() {
        return agendaUrl;
    }

    public void setAgendaUrl(String agendaUrl) {
        this.agendaUrl = agendaUrl;
    }

    public String getBannerImgUrl() {
        return bannerImgUrl;
    }

    public void setBannerImgUrl(String bannerImgUrl) {
        this.bannerImgUrl = bannerImgUrl;
    }

    public String getConferenceUrl() {
        return conferenceUrl;
    }

    public void setConferenceUrl(String conferenceUrl) {
        this.conferenceUrl = conferenceUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTrack() {
        return track;
    }

    public void setTrack(boolean track) {
        this.track = track;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrlFragment(String urlFragment) {
        this.urlFragment = urlFragment;
    }

    public String getUrlFragment() {
        return urlFragment;
    }

    public List<String> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<String> speakers) {
        this.speakers = speakers;
    }
}


