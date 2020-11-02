package com.nogroup.municipality.manager.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;

import com.google.common.io.Resources;
import com.nogroup.municipality.manager.data.embedded.VFile;

public class FileBundle {

	public static byte[] file2bytes(File file) throws IOException {
		byte[] bytesArray = new byte[(int) file.length()]; 
		
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray); //read file into bytes[]
		fis.close();
		
		return bytesArray ;
	}
	
	public static byte[] decode(String s) {
		return Base64.getDecoder().decode(s) ;
	}
	public static String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}
	
	public static VFile create(String name,String extension,File f) throws IOException {
		VFile vf= new VFile() ;
		vf.setContent(encode(file2bytes(f)));
		vf.setExtension(extension);
		vf.setName(name);
		return vf ;
	}
	
	public static VFile readFileFromResource(final String fileName, Charset charset) throws IOException {
		VFile vf = new VFile() ;
		vf.setName(fileName);
		vf.setContent(encode(readResource(fileName,charset).getBytes()));
		return vf;
		
	}
	public static String readResource(final String fileName, Charset charset) throws IOException {
        return Resources.toString(Resources.getResource(fileName), charset);
	}
}
