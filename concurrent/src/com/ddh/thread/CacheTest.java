package com.ddh.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ddh on 2018/4/12.
 */
public class CacheTest {
  static  Map<String,Object> cache=new HashMap<>();

    public static void main(String[] args) {
        cache.put(null,null);
        String vlaue=(String) new CacheTest().getData("null");
        System.out.println(vlaue);
    }
    private ReadWriteLock rwl=new ReentrantReadWriteLock();
    public Object getData(String data){
      rwl.readLock().lock();
        Object value= null;
        try {
            value = null;
            value=cache.get(data);
            if(value==null){
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if(value==null){
                        value="aaaaa";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}
