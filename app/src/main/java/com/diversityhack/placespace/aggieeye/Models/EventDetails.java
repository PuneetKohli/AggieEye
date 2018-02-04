package com.diversityhack.placespace.aggieeye.Models;

/**
 * Created by Srujan on 6/8/2017.
 */

public class EventDetails {

  private String        name;
  private String        address;
  private EventCategory category;
  private double        latitude;
  private double        longitude;
  private String        time;
  private boolean       isAR;
  private int           loves;
  private boolean       doesLove;
  private String        host;
  private String        about;
  private String        organization;

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setLatLng(double lat, double lng) {
    latitude = lat;
    longitude = lng;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public EventCategory getCategory() {
    return category;
  }

  public void setCategory(EventCategory category) {
    this.category = category;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public boolean isAR() {
    return isAR;
  }

  public void setAR(boolean AR) {
    isAR = AR;
  }

  public int getLoves() {
    return loves;
  }

  public void setLoves(int loves) {
    this.loves = loves;
  }

  public boolean isDoesLove() {
    return doesLove;
  }

  public void setDoesLove(boolean doesLove) {
    this.doesLove = doesLove;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }
}
