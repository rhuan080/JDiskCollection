package com.jarraydisk.arraydisklist.ioManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class Block<T>{

	private int indexBegin;
	private int length;
	private String pathTmpFile;
	private File tmpFile;

	public Block(String pathTmpFile) {

		this.indexBegin=0;
		this.length=0;
		this.pathTmpFile = pathTmpFile;
		
		this.tmpFile = new File(pathTmpFile);
		this.tmpFile.deleteOnExit();
		
	}

	public Block(int indexBegin, String pathTmpFile) {

		this.indexBegin=indexBegin;
		this.length=0;
		this.pathTmpFile = pathTmpFile;
		
		this.tmpFile = new File(pathTmpFile);
		this.tmpFile.deleteOnExit();
	
	}

	public boolean save(T o) {
		ObjectOutputStream oos = null;
		try {				 

			oos = new AppendableObjectOutputStream( new FileOutputStream(this.tmpFile,true) );
			oos.writeObject(o);
			this.length ++;
			return true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public T read(int index) {
		
		ObjectInputStream ois = null;
		int i = indexBegin;
		int size = indexBegin + length;
		
		try {
			ois = new AppendableObjectInputStream(new FileInputStream(this.tmpFile));
			
			while( i < size) {
				T o = (T) ois.readObject();
				if(i == index ) return o;
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public int getIndexBegin() {
		return indexBegin;
	}

	public void setIndexBegin(int indexBegin) {
		this.indexBegin = indexBegin;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getPathTmpFile() {
		return pathTmpFile;
	}

	public void setPathTmpFile(String pathTmpFile) {
		this.pathTmpFile = pathTmpFile;
	}
	
    
	
}