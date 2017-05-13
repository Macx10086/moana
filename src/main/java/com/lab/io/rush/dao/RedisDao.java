package com.lab.io.rush.dao;

import java.util.List;
import java.util.Set;

public interface RedisDao {
	public boolean existsKey( final String key);
	 public boolean addKey(final String key,final String value);
	 public boolean addSetKey(final String key,final String value);
	 public String getValue(final String key);
	 public boolean existsKeyInSet(final String key,final String value);
	 public Set<byte[]>setMembers(final String key);
	 public boolean deleteValueInSet(final String key,final String value);
	public boolean moveValueToRushed(final String srckey, final String dstkey, final String value, final int id);
	public String watch(final String key);
	public String rushTicket(final int id,final String email);
}
