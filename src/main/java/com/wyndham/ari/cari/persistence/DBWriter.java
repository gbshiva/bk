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

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class DBWriter implements CacheWriter {

	private final String url;
	private volatile OutputStream out;
	private volatile boolean write = true;
	private static DataSource ds;

	public DBWriter(String iurl) {
		url = iurl;
	}

	public CacheWriter clone(final Ehcache ehcache)
			throws CloneNotSupportedException {
		throw new CloneNotSupportedException("CsvWriter cannot be cloned!");
	}

	public void init() {
		try {
			ds = setupDataSource(url);
			
		} catch (Exception e) {
			throw new CacheException("Couldn't conenct to DB "+ url, e);
		}
	}

	public void dispose() throws CacheException {
		try {

			//
		} catch (Exception e) {
			throw new CacheException("Couldn't close db connection"+e);
		}
	}

	public synchronized void write(final Element element) throws CacheException {

		if (!write) {
			return;
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

	public static DataSource setupDataSource(String connectURI) {
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				connectURI, null);
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, null);
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<PoolableConnection>(
				poolableConnectionFactory);
		poolableConnectionFactory.setPool(connectionPool);
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<PoolableConnection>(
				connectionPool);
		return dataSource;

	}

}