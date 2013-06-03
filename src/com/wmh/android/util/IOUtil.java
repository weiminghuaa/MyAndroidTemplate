package com.wmh.android.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * IO流工具类
 * 
 * @author wmh
 * 
 */
public class IOUtil {

	/**
	 * InputStream转成String
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStreamToString(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	/**
	 * byte[]转Bitmap
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static Bitmap inputStreamToBitmap(InputStream is) throws IOException {
		byte[] bytes = inputStreamToByte(is);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * InputStream转为byte[]
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = is.read(buf)) > -1) {
			bos.write(buf, 0, len);
		}
		byte[] bytes = bos.toByteArray();
		bos.close();
		is.close();
		return bytes;
	}
}
