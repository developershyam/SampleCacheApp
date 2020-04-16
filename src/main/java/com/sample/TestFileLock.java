package com.sample;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class TestFileLock {

	private static String FILENAME="./count.temp";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Started ................... "+ Thread.currentThread().getName());
		Runnable run = () -> {
			System.out.println("Started ................ "+ Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName() + "#####################" + readFile(1));
			
		};
		
		Thread t1=new Thread(run);
		
		t1.start();
		System.out.println(Thread.currentThread().getName() + "#####################" + readFile(1));

	}
	
	private static synchronized String readFile(int count) {
		
		try{
			System.out.println("readFile-----> "+count);
			File file = new File(FILENAME);
			if (file.createNewFile()) {
	            System.out.println(Thread.currentThread().getName() + "--- File has been created.");
	        } else {
	        	System.out.println(Thread.currentThread().getName() + "--- File already exists.");
	        }
            Path path = Paths.get(FILENAME);
			FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE);
			FileLock lock = fileChannel.lock();
			System.out.println("Lock acquired: " + lock.isValid());
			System.out.println("Lock is shared: " + lock.isShared());
			System.out.println(Thread.currentThread().getName() +"----" + " sleeping" + new Date());
			Thread.sleep(10000);
			// lock.release();
			// lock.close();
			// lock = null;
			String str = "";
			ByteBuffer buffer = ByteBuffer.allocate(20);
	        int noOfBytesRead = fileChannel.read(buffer);
	        System.out.println("Buffer contents: ");
	        while (noOfBytesRead != -1) {
	 
	            buffer.flip();
	            while (buffer.hasRemaining()) {
	            	str += (char) buffer.get();
	            }
	            buffer.clear();
	            noOfBytesRead = fileChannel.read(buffer);
	        }
	        System.out.println("Read file: "+ str);
			System.out.println(Thread.currentThread().getName() +"----" + " wake up " + new Date());
		} catch (Exception ex) {

			System.out.println("~~~~~~~~~~~~~~~~~~Exception~~~~~~~~~~~~~~~~~~" + ex.getMessage());
			try {
				Thread.sleep(1000);
			}catch(Exception ex2) {
			}
			if(count<=5) {
				//readFile(++count);
			}
		}
		
		return "";
	}
	
	

}
