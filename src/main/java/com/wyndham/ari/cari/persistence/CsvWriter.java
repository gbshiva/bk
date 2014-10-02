package com.wyndham.ari.cari.persistence;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Collection;


public class CsvWriter implements CacheWriter {

  private final          File         file;
  private       volatile OutputStream out;
  private       volatile boolean      write = true;


  public CsvWriter(final File file) {
    this.file = file;
  }

  public CacheWriter clone(final Ehcache ehcache) throws CloneNotSupportedException {
    throw new CloneNotSupportedException("CsvWriter cannot be cloned!");
  }

  public void init() {
    try {
     // if(file.createNewFile()) {
      //  out = new BufferedOutputStream(new FileOutputStream(file));
     // } else {
        out = new BufferedOutputStream(new FileOutputStream(file, true));
      //}
    } catch (IOException e) {
      throw new CacheException("Couldn't create file " + file, e);
    }
  }

  public void dispose() throws CacheException {
    try {
      if (out != null) {
        out.close();
      }
    } catch (IOException e) {
      throw new CacheException("Couldn't close file " + file, e);
    }
  }

  public synchronized void write(final Element element) throws CacheException {

    if(!write) {
      return;
    }

      try {
        
        out.write(("\"" + element.getObjectValue().toString() + "\",").getBytes("UTF-8"));
      } catch(Exception e){
    	  e.printStackTrace();
      }
    try {
      out.write("\n".getBytes());
      out.flush();
    } catch (IOException e) {
      throw new CacheException("Couldn't flush to file " + file);
    }
  }

  public synchronized void writeAll(final Collection<Element> elements) throws CacheException {
    for (Element element : elements) {
      write(element);
    }
  }

  public synchronized void delete(final CacheEntry cacheEntry) throws CacheException {
    // noop
  }

  public synchronized void deleteAll(final Collection<CacheEntry> cacheEntries) throws CacheException {
    // noop
  }

public void throwAway(Element arg0, SingleOperationType arg1,
		RuntimeException arg2) {
	// TODO Auto-generated method stub
	
}
}