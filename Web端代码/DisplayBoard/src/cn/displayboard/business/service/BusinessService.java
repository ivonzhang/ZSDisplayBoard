package cn.displayboard.business.service;

import java.util.List;

public interface BusinessService {
	
	/*
	 * ���û��ϴ�չ��
	 * 
	 */
	public boolean doFileUpload(List<Object> params);

	/*
	 * �û��ϴ�����չ��
	 */
	public boolean doHotZhanbanUpload(List<Object> params);
}
