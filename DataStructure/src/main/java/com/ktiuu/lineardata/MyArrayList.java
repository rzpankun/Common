package com.ktiuu.lineardata;

import org.omg.CORBA.Object;

import java.util.Iterator;

/**
 * @Create by pankun
 * @DATE 2020/6/15
 */
public class MyArrayList<T> implements Iterable<T>{
    private static final int DEFAULT_CAPACITY = 16;
    private int theSize;
    private T[] theItems;

    public MyArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    public void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return theSize == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public T get(int index){
        if(index > 0 || index >= size()){
            throw  new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public T set(int index , T t){
        if(index < 0 || index >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldValue = theItems[index];
        theItems[index] = t;
        return oldValue;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < size()){
            return;
        }
        T[] old = theItems;
        theItems = (T[])new Object[newCapacity];
        for(int i = 0 ; i < size() ; i ++){
            theItems[i] = old[i];
        }
    }

    public boolean add(T t){
        add(size(), t);
        return true;
    }

    public void add(int index , T t){
        if(size() == theItems.length){
            ensureCapacity(theItems.length * 2 + 1);
        }
        for (int i = size() ; i >index ; i --){
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = t;
        theSize++;
    }

    public T remove(int index){
        T t = get(index);
        for(int i = index ; i < size() -1 ; i ++){
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return t;
    }




    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T>{
        private int current = 0;
        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new RuntimeException();
            }
            return theItems[current ++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
