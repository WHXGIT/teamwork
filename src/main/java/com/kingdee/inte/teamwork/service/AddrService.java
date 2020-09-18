package com.kingdee.inte.teamwork.service;

import java.io.IOException;

/**
 * description: 地址服务
 *
 * @author Administrator
 * @date 2020/7/28 10:40
 */
public interface AddrService {

	String getGeoCoder(String address, String provider) throws IOException;

	String getReverseGeoCoder(String location, String provider) throws IOException;

	String getTimezone(String addrOrLocation, String provider) throws IOException;

	String getPosition(String ip, String provider) throws IOException;

	String getNavigation(String address, String provider) throws IOException;

	String getGoogleJS() throws IOException;
}
