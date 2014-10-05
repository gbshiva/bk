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
import java.util.Collection;
import java.sql.*;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.AggrCacheService;
import com.wyndham.ari.dao.Delivery;

public class DBWriter implements CacheWriter {

	private String url = null;
	static Logger logger = Logger.getLogger(DBWriter.class);
	private volatile boolean write = true;
	private Connection conn = null;

	public DBWriter(String iurl, String user, String pass) {
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			url = iurl;
			conn = DriverManager.getConnection(url, user, user);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public CacheWriter clone(final Ehcache ehcache)
			throws CloneNotSupportedException {
		throw new CloneNotSupportedException("CsvWriter cannot be cloned!");
	}

	public void init() {
		try {
			// ds = setupDataSource(url);

		} catch (Exception e) {
			throw new CacheException("Couldn't conenct to DB " + url, e);
		}
	}

	public void dispose() throws CacheException {
		try {

			//
		} catch (Exception e) {
			throw new CacheException("Couldn't close db connection" + e);
		}
	}

	public synchronized void write(final Element element) throws CacheException {

		try {
			
			Delivery dlvry = (Delivery) element.getObjectValue();
			PreparedStatement insert = null;
			 String insertstmt = " insert into ARI_DELIVERY_HEADER(REQUEST_ID, BRAND_ID,PROPERTY_ID,MSG_STATUS_ID, MSG_TIMESTAMP,MSG_SUBJECT_ID) values (?,?,?,?,?,?)";
			 insert = conn.prepareStatement(insertstmt);
			 insert.setInt(1, (int) dlvry.getReqId());
			 insert.setString(2, dlvry.getBrandId());
			 insert.setString(3, dlvry.getPropertyId());
			 insert.setInt(4, dlvry.getMessageStatusId());
			 insert.setTimestamp(5, new Timestamp(dlvry.getsourceTimeStamp_long()));
			 insert.setInt(6, 1);
			 insert.execute();
			 insert.close();
			
			
			
			
			
			
		} catch (Exception ex) {
			logger.error(ex);
		}

	}

	public synchronized void writeAll(final Collection<Element> elements)
			throws CacheException {
		for (Element element : elements) {
			write(element);
		}
	}

	public synchronized void delete(final CacheEntry cacheEntry)
			throws CacheException {
		// noop
	}

	public synchronized void deleteAll(final Collection<CacheEntry> cacheEntries)
			throws CacheException {
		// noop
	}

	public void throwAway(Element arg0, SingleOperationType arg1,
			RuntimeException arg2) {
		// TODO Auto-generated method stub

	}

}