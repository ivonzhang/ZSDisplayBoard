package cn.displayboard.asynctaskLoadingImageView;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class AsyncImageLoader { /*
	//Cache for image(Type String is the URL of image,the second parameter is soft reference) 
	private HashMap<String, SoftReference<Bitmap>> imageCache = null; 
	private Activity context; 
	public AsyncImageLoader(Activity context){ 
	this.context = context; 
	imageCache = new HashMap<String, SoftReference<Bitmap>>(); 
	} 
	public Bitmap loadImage(final ImageView imageView,final String imageURL,final ImageCallBack imageCallBack){ 
	//If the cache contains the reference of bitmap then return 
	if (imageCache.containsKey(imageURL)) { 
	SoftReference<Bitmap> bitmapReference = imageCache.get(imageURL); 
	Bitmap bitmap = bitmapReference.get(); 
	if (bitmap != null) { 
	return bitmap; 
	} 
	} 
	//Second cache,search local SD card 
	else { 
	String fileName = StringUtil.namePicture(imageURL);//获取文件名 
	boolean isExist = SystemUtils.findPhotoFromSDCard(Constant.INFO_PATH, fileName); 
	if (isExist) {//是否在SD卡存在图片 
	Bitmap bitmap = SystemUtils.getPhotoFromSDCard(Constant.INFO_PATH, fileName); 
	return bitmap; 
	} 
	} 
	final Handler myHandler = new Handler(){ 
	@Override 
	public void handleMessage(Message msg) 
	{ 
	imageCallBack.setImage(imageView, (Bitmap)msg.obj); 
	} 
	}; 
	//If the bitmap not exists in cache or SD card,then get it from net 
	new Thread(){ 
	@Override 
	public void run() { 
	// TODO Auto-generated method stub 
	boolean isNetwork = SystemUtils.checkNetwork(context); 
	if (isNetwork) { 
	InputStream photoStream = HttpRequest.getImageStream(imageURL);//这里是我自己写的一个类，目的是通过URL地址从服务器获取图片输入流 
	Bitmap bitmap; 
	try { 
	bitmap = ImageTools.getResizeBitmap(photoStream, 128, 128); 
	if (bitmap != null) { 
	String fileName = StringUtil.namePicture(imageURL); 
	//Save image to SD card 
	SystemUtils.savePhotoToSDCard(bitmap, fileName, Constant.INFO_PATH); 
	//Put soft reference to cache 
	imageCache.put(imageURL, new SoftReference<Bitmap>(bitmap)); 
	//Send message to update UI 
	Message message = myHandler.obtainMessage(0, bitmap); 
	myHandler.sendMessage(message); 
	} 
	} catch (Exception e) { 
	// TODO Auto-generated catch block 
	e.printStackTrace(); 
	} 
	} 
	} 
	}.start(); 
	return null; 
	} 
	*//** 
	* Interface for load image 
	* @author Ryan 
	* 
	*//* 
	public interface ImageCallBack{ 
	//Set image for imageview through bitmap 
	public void setImage(ImageView imageView,Bitmap bitmap); 
	} */
	} 
