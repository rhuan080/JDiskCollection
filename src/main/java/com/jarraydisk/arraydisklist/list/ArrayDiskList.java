package com.jarraydisk.arraydisklist.list;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.jarraydisk.arraydisklist.ioManager.Block;

public class ArrayDiskList<T extends Serializable> implements List<T>{
	
	private String tmpFolder;
	
	private final long WIDTH_BLOCK_DEFAULT = 1000; 

	private List<Block<T>> blocks;
	private long widthBlock;
	
	public ArrayDiskList() throws IOException {
		
		this.tmpFolder = System.getProperty("java.io.tmpdir");
		this.blocks = new ArrayList<Block<T>>();
		this.widthBlock = WIDTH_BLOCK_DEFAULT;
		
		if(! new File(tmpFolder).isDirectory()) throw new IOException();
		
	}
	
	public ArrayDiskList(long widthBlock) throws IOException {
		
		this.tmpFolder = System.getProperty("java.io.tmpdir");
		this.blocks = new ArrayList<Block<T>>();
		this.widthBlock = widthBlock;
		
		if(! new File(tmpFolder).isDirectory()) throw new IOException();
		
	}
	
	
	
	public int size() {
		
		if(blocks.isEmpty()) return 0;
		return blocks.get(blocks.size()-1).getIndexBegin() + blocks.get(blocks.size()-1).getLength(); 		
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.size() == 0);
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean add(T e) {
		// TODO Auto-generated method stub
		
		if(!(e instanceof Serializable)) throw new IllegalArgumentException ("The object must be serializable");
		
		Block<T> block = null;
		
		if( blocks.isEmpty()) {
			block = createBlock();
			blocks.add(block);
		}
		else {
			block = blocks.get(blocks.size()-1);
			
			if(block.getLength() >= widthBlock) {
				block = createBlock();
				blocks.add(block);
			}
		}
		
		return block.save(e);	
		
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		
		for (Block block : blocks) {
			for(int i = block.getIndexBegin(); i < block.getLength(); i++) {
				T o = (T) block.read(i);
				if( o.equals(c)) return true;
			}
			
		}
		
		return false;
	}

	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		for(T o : c) {
			if( !add(o) ) return false;
		}
		
		return true;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		
		throw new UnsupportedOperationException("Operation not supported");
	
	}

	public boolean removeAll(Collection<?> c) {
		
		throw new UnsupportedOperationException("Operation not supported");
	
	}

	public boolean retainAll(Collection<?> c) {
		
		throw new UnsupportedOperationException("Operation not supported");
	
	}

	public void clear() {
		
		blocks = new ArrayList<Block<T>>();
		
	}

	public T get(int index) {
		// TODO Auto-generated method stub
		Block lastBlock = blocks.isEmpty() ? null : blocks.get( blocks.size()-1 );
		if(index < 0 || lastBlock == null 
				     || index >= lastBlock.getIndexBegin() + lastBlock.getLength()) {
			throw new IndexOutOfBoundsException ("Index out of bounds.");
		}
		
		Block block = null;
		
		for(Block block2 : blocks) {
			
			if( index >= block2.getIndexBegin() 
					&& index < block2.getIndexBegin() + block2.getLength()) {
				block = block2;
				break;
			}
		}
		
		return (T) block.read(index);
		
	}

	public T set(int index, T element) {
		
		throw new UnsupportedOperationException("Operation not supported");
	
	}

	public void add(int index, T element) {
		
		throw new UnsupportedOperationException("Operation not supported");
		
	}

	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		
		int index = -1;
		for (Block block : blocks) {
			for(int i = block.getIndexBegin(); i < block.getLength(); i++) {				
				index++;
				T obj = (T) block.read(i);
				if( obj.equals(o)) return index;			
			}			
		}
		
		return -1;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Block<T> createBlock() {
		
		Block<T> lastBlock = blocks.isEmpty() ? null : blocks.get( blocks.size()-1 );
		
		int index = blocks.size() + 1; 
		String fileName = "ArrayBlock_"+ Long.toString(new Date().getTime())
									+"_"+Integer.toString(this.hashCode())+ Integer.toString(index)+".block";
		
		int indexBegin = (lastBlock == null)? 0 : lastBlock.getIndexBegin() + lastBlock.getLength();				
		
		return new Block<T>(indexBegin, tmpFolder+ "/" + fileName);
		
	}
	

	

}
