package cn.displayboard.business.service;

import java.util.List;

public interface BusinessService {
	
	/*
	 * 给用户上传展板
	 * 
	 */
	public boolean doFileUpload(List<Object> params);

	/*
	 * 用户上传热门展板
	 */
	public boolean doHotZhanbanUpload(List<Object> params);
}
