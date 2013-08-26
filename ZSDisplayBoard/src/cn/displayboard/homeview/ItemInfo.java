package cn.displayboard.homeview;

import java.util.Map;

public class ItemInfo {
	private Map<String,Object> imgLeftMap, imgRightMap;

	public ItemInfo(Map<String,Object> imgLeftMap, Map<String,Object> imgRightMap) {
		this.imgLeftMap = imgLeftMap;
		this.imgRightMap = imgRightMap;
	}

	public Map<String,Object> getimgLeftMap() {
		return imgLeftMap;
	}

	public Map<String,Object> getimgRightMap() {
		return imgRightMap;
	}

}
